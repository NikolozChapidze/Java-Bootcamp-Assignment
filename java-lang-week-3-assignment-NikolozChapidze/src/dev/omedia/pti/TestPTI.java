package dev.omedia.pti;

import java.util.*;

public class TestPTI {
    public static void test(){
        Collection<Vehicle> vehicles = new ArrayList<>(){};
        for (int i = 0; i < 100; i++) {
            vehicles.add(new Vehicle());
        }
        Scanner scanner = new Scanner(System.in);

        Map<EngineType, Double> engineMaxEmission = new HashMap<>();
        System.out.print("input petrolEngineMaxEmission: ");
        engineMaxEmission.put(EngineType.PETROL,scanner.nextDouble());

        System.out.print("input dieselEngineMaxEmission : ");
        engineMaxEmission.put(EngineType.DIESEL,scanner.nextDouble());

        System.out.print("input hybridEngineMaxEmission : ");
        engineMaxEmission.put(EngineType.HYBRID,scanner.nextDouble());

        System.out.print("input brakingEfficiencyMinRequirement  : ");
        double brakingEfficiencyMinRequirement  = scanner.nextDouble();

        InspectionRules inspectionRules = new InspectionRules(engineMaxEmission,brakingEfficiencyMinRequirement);
        InspectionCenter.inspectionRules = inspectionRules;
        InspectionCenter inspectionCenter = new InspectionCenter();

        Map<Vehicle, List <InspectionFaultyTypes>> result = inspectionCenter.inspection(vehicles);

        int emissionFailed = 0;
        int brakingFailed = 0;
        int twoFailed = 0;
        int oneFailed = 0;
        int noneFailed = 0;
        for(final var inspectionresult : result.values()){
            if(inspectionresult.size() == 3){
                System.err.println("something wrong");
            }
            if(inspectionresult.size() == 2){
                twoFailed++;
            }else{
                if(inspectionresult.contains(InspectionFaultyTypes.NONE)){
                    noneFailed++;
                    continue;
                }else{
                    oneFailed++;
                }
            }
            if(inspectionresult.contains(InspectionFaultyTypes.BRAKING)){
                brakingFailed++;
            }
            if(inspectionresult.contains(InspectionFaultyTypes.EMISSION)){
                emissionFailed++;
            }
        }

        System.out.println("\nPassed the inspection: " + noneFailed );
        System.out.println("Failed to pass inspection: " + (oneFailed+twoFailed) );
        System.out.println("Only 1 failure: " + oneFailed);
        System.out.println("Only 2 failure: " + twoFailed);
        System.out.println("EMISSION failure: " + emissionFailed);
        System.out.println("BRAKING failure: " + brakingFailed);
    }
}
