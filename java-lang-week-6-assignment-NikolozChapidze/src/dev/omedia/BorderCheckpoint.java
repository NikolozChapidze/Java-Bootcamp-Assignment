package dev.omedia;

import dev.omedia.enums.ActionType;
import dev.omedia.enums.AgeRange;
import dev.omedia.enums.BorderCrossType;
import dev.omedia.enums.FileTypes;
import dev.omedia.exceptions.*;
import dev.omedia.models.BorderCross;
import dev.omedia.models.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BorderCheckpoint {
    private final String personFile = "src/resources/person.csv";
    private final String borderCrossFile = "src/resources/bordercross.csv";
    private final String cilpritFile = "src/resources/Culprit.csv";
    private final String statiscicscrosstypeFile = "src/resources/StatiscicsCrossType.csv";
    private final String mostvisitedFile = "src/resources/StatiscicsMostVisited.csv";
    private final String nameRegex = "^[a-zA-Z\\s]*$";
    private final String geoRegex  = "^[\\p{IsGeorgian}\\s]*$";
    private final String p_numberRegex  = "[A-Z]{3}\\d{6}";

    private HashMap<String, Person> personDB;
    private HashMap<String, BorderCross> personLastCross;
    private HashMap<AgeRange, int[]> crossTypeStatistics;
    private HashMap<AgeRange, ArrayList<HashMap<String,Integer>>> visitCounter;

    public BorderCheckpoint() {
        personDB = new HashMap<>();
        personLastCross = new HashMap<>();
        crossTypeStatistics = new HashMap<>();
        visitCounter    = new HashMap<>();
        for (var ageRange : AgeRange.values()) {
            crossTypeStatistics.put(ageRange,new int[BorderCrossType.values().length]);
            ArrayList<HashMap<String,Integer>> temp = new ArrayList<>();
            for(var ignored : BorderCrossType.values()){
                temp.add(new HashMap<>());
            }
            visitCounter.put(ageRange,temp);
        }
    }

    public void readPeopleData() {
        try {
            Files.readAllLines(Path.of(personFile))
                    .stream()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(this::toPerson)
                    .forEach(this::newPerson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readCrossingData() {
        try {
            Files.readAllLines(Path.of(borderCrossFile))
                    .stream()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(this::toBorderCross)
                    .forEach(this::takeAction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void newPerson(Person person) {
        try {
            checkPersonNumberFormat(FileTypes.PERSON, person.getP_id(), person.getP_id());
            checkPersonNameFormat(person.getP_id(),person.getName());
            personDB.put(person.getP_id(),person);
        } catch (FormatException e) {
            Logger.logError(String.valueOf(e));
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    private void takeAction(BorderCross borderCross) {
        try {
            checkCrossType(borderCross.getCross_id(), borderCross.getCross_type());
            checkPersonNumberFormat(FileTypes.BORDERCROSS, borderCross.getCross_id(), borderCross.getP_id());
            checkPersonExistence(borderCross.getCross_id(), borderCross.getP_id());
            if(personLastCross.containsKey(borderCross.getP_id()) &&
                    samePersonLastCross(borderCross.getAction(), borderCross.getP_id())){
                writeInCilprit(borderCross);
            }else{
                Person person = personDB.get(borderCross.getP_id());
                personLastCross.put(person.getP_id(),borderCross);

                int[] crossStats = crossTypeStatistics.get(person.getAgeRange());
                crossStats[borderCross.getCross_type().ordinal()]++;

                HashMap<String, Integer> map = visitCounter.get(person.getAgeRange()).get(borderCross.getCross_type().ordinal());
                map.put(borderCross.getCountry_code(),map.getOrDefault(borderCross.getCountry_code(),1));
            }
        }catch (FormatException exception){
            Logger.logError(String.valueOf(exception));
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }


    private void writeInCilprit(BorderCross cross) {
        try {
            BorderCross old = personLastCross.get(cross.getP_id());
            Person person = personDB.get(old.getP_id());
            Files.write(Paths.get(cilpritFile)
                    , String.format("%s - %s, %s, %s, %s, %s, %s",
                            old.getC_date(),cross.getC_date(),old.getCross_type(),old.getAction(),
                            person.getP_id(),person.getName(),person.getB_date()).getBytes()
                    , StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean samePersonLastCross(ActionType action, String p_id) {
        return personLastCross.get(p_id).getAction() == action;
    }


    private BorderCross toBorderCross(String[] arguments) {
        String cross_id = arguments[0];
        BorderCrossType cross_type = BorderCrossType.valueOf(arguments[1]);
        String p_id = arguments[2];
        String v_number = arguments[3];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate c_date = LocalDate.parse(arguments[4], dateTimeFormatter);
        ActionType action = ActionType.valueOf(arguments[5]);
        String country_code = arguments[6];
        return new BorderCross(cross_id, cross_type, p_id, v_number, c_date, action, country_code);
    }

    public Person toPerson(String[] arguments) {
        String p_id = arguments[0];
        String name = arguments[1];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate b_date = LocalDate.parse(arguments[2], dateTimeFormatter);
        return new Person(p_id, name, b_date);
    }

    private void checkPersonExistence(String cross_id, String p_id) throws NoSuchPersonException {
        if(!personDB.containsKey(p_id)){
            throw new NoSuchPersonException(String.format("%s, %s, %s",FileTypes.BORDERCROSS,cross_id,p_id));
        }
    }

    private void checkCrossType(String cross_id, BorderCrossType cross_type) throws CrossTypeFormatException {
        if(cross_type != BorderCrossType.AIR && cross_type != BorderCrossType.LAND){
            throw new CrossTypeFormatException(String.format("%s, %s, %s",FileTypes.BORDERCROSS,cross_id,cross_type));
        }
    }

    private void checkPersonNumberFormat(FileTypes fileType, String u_id, String p_number) throws PersonDocumentNumberFormatException {
        if(!(p_number.length() == 11 || (p_number.length() == 9 && p_number.matches(p_numberRegex)))){
            throw new PersonDocumentNumberFormatException(String.format("%s, %s, %s",fileType,u_id,p_number));
        }
    }

    private void checkPersonNameFormat(String p_id, String p_name) throws PersonNameFormatException {
        if(!(p_name.matches(nameRegex) || p_name.matches(geoRegex))){
            throw new PersonNameFormatException(String.format("%s, %s, %s",FileTypes.PERSON,p_id,p_name));
        }
    }

    public void generateStatistics() {
        StringBuilder crossingStatistics = new StringBuilder();
        StringBuilder mostVisitedStatistics = new StringBuilder();
        for (var ageRange : AgeRange.values()){
            int[] stats = crossTypeStatistics.get(ageRange);
            ArrayList<HashMap<String, Integer>> visitCounts = visitCounter.get(ageRange);
            String air = getMostVisited(visitCounts.get(BorderCrossType.AIR.ordinal()));
            String land = getMostVisited(visitCounts.get(BorderCrossType.LAND.ordinal()));

            crossingStatistics.append(String.format("%s,%s,%s\n",
                    ageRange.getRange(),stats[BorderCrossType.AIR.ordinal()],stats[BorderCrossType.LAND.ordinal()]));
            mostVisitedStatistics.append(String.format("%s,%s,%s\n",
                    ageRange.getRange(),air,land));
        }
        try {

            Files.write(Paths.get(statiscicscrosstypeFile)
                    , crossingStatistics.toString().getBytes()
                    , StandardOpenOption.APPEND);
            Files.write(Paths.get(mostvisitedFile)
                    , mostVisitedStatistics.toString().getBytes()
                    , StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMostVisited(HashMap<String, Integer> visits){
        return visits.entrySet().stream()
                .max(this::compareEntry)
                .map(Map.Entry::getKey).orElse("notFound");
    }

    private int compareEntry(Map.Entry<String, Integer> e, Map.Entry<String, Integer> e1) {
        return e.getValue().compareTo(e1.getValue());
    }

}
