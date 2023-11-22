[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7896635&assignment_repo_type=AssignmentRepo)
# java-lang-week-3

---
---

# PTI

### pti.1

**`dev.omedia.pti` package-ში**

**შექმენით** enum `EngineType` (ძრავის ტიპი)  
`PETROL`,  
`DIESEL`,  
`ELECTRIC`,  
`HYBRID` ტიპებით.
- მეთოდები  
  `hasEmission` -(აქვს თუ არა გამონაბოლქვი) რომელიც აბრუნებს `false` თუ ტიპი არის `ELECTRIC`, სხვა ყველა შემთხვევაში `true`
<br><br>  

**შექმენით** კლასი `Engine` (ძრავი)
- ველები  
  `type` - `EngineType`-ს ტიპის  
  `volumeInCC` - ძრავის მოცულობა კუბურ სანტიმეტრებში. - მთელი რიცხვი.  
  `emission` - მავნე აირების გამონაბოლქვი. მიანიჭეთ შემთხვევითი(random) რიცხვი, 0.1-დან 10.0-მდე შაულედში. 
- კონსტრუქტორი  
  1-პარამეტრიანი კონსტრუქტორი რომელის პარამეტრის მნიშვნელობას მიანიჭებს type-ს. თუ არ აქვს გამონაბოლქვი, `volumeInCC`-ს მიანიჭებს `null`-ს სხვა შემთხვევაში, შემთხვევით(random) მთელ რიცხვს 1000-დან 7000-მდე შუალედში. თუ არ აქვს გამონაბოლქვი, მაშინ `emission`-საც მიანიჭეთ `null`. 

  უაპარამეტრო კონსტრუქტორი რომელიც გამოიძახებს პარაეტრიან კონსტრუქტორს შემდეგი პარამეტრით  
  `EngineType.values()[(int) (Math.random() * EngineType.values().length)]`

- მეთოდები  
  მხოლოდ `getter`-ები  
  `toString`, `hashCode`,`equals`  
<br><br>  

**შექმენით** კლასი `Vehicle` (ავტოტრანსპორტი)
- ველები 
  `vin` - ავტომობილის საიდენტიფიკაციო ნომენი (უნიკალური). მიანიჭეთ UUID სტრიქონი. 
  `engine` - ძრავი.
  `seats` - რამდენი დასაჯდომი ადგილი აქვს.
  `brakingEfficiency` - დამუხრუჭების ეფექტურობა პროცენტებში.  
- ინიციალიზაციის ბლოკი.  
  ძრავს მიანიჭეთ მნიშვნელობა;  
  `seats` - მიანიჭეთ შემთხვევითად 2,4,5,7,8,10 რიცხვებიდან ერთ-ერთს; თუ ძრავის მოცულობა <=2200 მაშინ 2,4,5 დან ერთ-ერთი შემთხვევითად.  
  `brakingEfficiency` - მიანიჭეთ შემთხვევითი რიცხვი (Random)  0-დან 100-მდე შუალდედში.  
- მეთოდები  
  მხოლოდ `getter`-ები  
  `isMinivan` - დაბრუნებს `true` თუ დასაჯდომი ადგილი >5ზე;  
  `toString`, `hashCode`, `equals`
<br><br>  

**შექმენით** კლასი `InspectionRules` (ინსპექტირების წესები)
- ველები  
  `petrolEngineMaxEmission` - ბენზინის ძრავის დასაშვები მავნე აირების გამონაბოლქვი (ზედა ზღვარი)  
  `dieselEngineMaxEmission` - დიზელის ძრავის დასაშვები მავნე აირების გამონაბოლქვი (ზედა ზღვარი)  
  `hybridEngineMaxEmission` - ჰიბრიდი ძრავის დასაშვები მავნე აირების გამონაბოლქვი (ზედა ზღვარი)  
  `brakingEfficiencyMinRequirement` - დამუხრუჭების დასაშვები ეფექტურობა (ქვედა ზღვარი პროცენტებში)  
  `minivanEmissionImmunity` - მინივენების დასაშვები მავნე აირების გამონაბოლქვი, რომელიც აღემატება შეაბასმისი ძრავის დასაშვები მავნე აირების გამონაბოლქვს minivanEmissionImmunity-ით. მიანიჭეთ შემთხვევითი(random) რიცხვი, 0.5-დან 2.0-მდე შაულედში.

შენიშვნა: _`petrolEngineMaxEmission`,`dieselEngineMaxEmission`,`hybridEngineMaxEmission`- ის ნაცვლად შეგიძლიათ გამოიყენოთ მხოლოდ ერთი `Map<EngineType,Double>` ტიპის ველი `engineMaxEmission`_
- მეთოდები  
  `getter, setter`-ები  
  `toString`
<br><br>  

**შექმენით** enum `InspectionFaultyTypes` (ინსპექტირების წუნის ტიპი)  
`NONE`,  `EMISSION`, `BRAKING` ტიპებით
<br><br>  

**შექმენით** კლასი `InspectionCenter` (ინსპექტირების ცენტრი) 
- მეთოდები  
  `inspection` - რომელსაც პარამეტრად გადაეცემა Vehicle, და აბრუნებს List<InspectionFaultyTypes>.
   - **თუ** ავტომობილმა ვერ გაიარა **გამონაბოლქვის ტესტი** (*ანუ ძრავის emission > შესაბამისი ძრავის ტიპის გამონაბოლქვის შეზღუდვაზე rule-დან*), მაშინ დაბრუნებულ List-ში უნდა იყოს `InspectionFaultyTypes.EMISSION`
   - **თუ** ავტომობილმა ვერ გაიარა **დამუხრუჭების ტესტი** (*ანუ brakingEfficiencyMinRequirement >= ავტომობილის დამუხრუჭების ეფექტურობაზე*)  მაშინ დაბრუნებულ List-ში უნდა იყოს `InspectionFaultyTypes.BRAKING`
   - **თუ** ავტომობილმა გაიარა ორივე ტესტი მაშინ დაბრუნებულ List-ში უნდა იყოს მხოლოდ `InspectionFaultyTypes.NONE`
  **შენიშვნა**: 
    1. *ამ მეთოდმა უნდა დააბრუნის ყველა წუნი, რომელიც აღმოაჩნდა ავტომობილს*. 
    2. *ელექტროძრავიანი ავტომობილები წარმატებით გადის გამონაბოქვის ტესტს*.

  `inspection` რომელსაც პარამეტრად გადაეცემა `Iterable < Vehicle >` და აბრუნებს `Map < Vehicle, List < InspectionFaultyTypes >>`. აბრუნებს ავტომობილს და შესაბამისად მის ყველა წუნს. თუ წუნი არ აქვს List< InspectionFaultyTypes > იქნება 1 ელემენტი - `InspectionFaultyTypes.NONE`
<br><br>  

#### გატესტვა

შექმენით კლასი `TestPTI` დაამატეთ `test` მეთოდი.  
`test` მეთოდში :
1. შექმენით Vehicle-ბის კოლექცია.
1. შეავსეთ 100 ავტომობილით.
1. კონსოლიდან შეიტანეთ `petrolEngineMaxEmission` ,  `dieselEngineMaxEmission` , `hybridEngineMaxEmission`, `brakingEfficiencyMinRequirement`  მნიშვნელობები ( _დაგჭირდებათ `java.util.Scanner`_).
1. შექმენით `InspectionRules` ობიექტი (instance) . გადაეცით შესაბამისი მნიშვნელობები.
1. `InspectionCenter.rule`-ს მიანიჭეთ მნიშვნელობა
1. შექმენით `InspectionCenter` ობიექტი (instance).
1. გამოიძახეთ  `inspection` მეთოდი (iterable პარამეტრიანი)
1. დაბეჭდეთ შემდეგი სტატისკია: 
    1. რამდენმა ავტომობილმა გაიარა ინსპეტირება
    1. რამდენმა ავტომობილმა ვერ გაიარა ინსპეტირება
    1. რამდენ ავტომობილს ქონდა მხოლოდ 1 წუნი
    1. რამდენ ავტომობილს ქონდა 2 წუნი
    1. რამდენ ავტომობილს ქონდა მხოლოდ `EMISSION` წუნი
    1. რამდენ ავტომობილს ქონდა მხოლოდ `BRAKING` წუნი

**შენიშვნა**: _ბეჭდვისას წინ მიაწერეთ რა იბეჭდება._

---

### pti.2

`dev.omedia.pti.sort` package-ში
**შექმენით** კლასი VehicleSorter  
- ველები  
`vehicles` - `List < Vehicle >`-ს ტიპის 

- კონსტრუქტორი 
  1 პარამეტრიანი რომლითაც  `vehicles`-ს მიანიჭებთ მნიშვნელობას  
- მეთოდები  
  `sortByEngineVolumeASC` - დაალაგებს `vehicles`-ს ძრავის მოცულობის ზრდადობის მიხედვით. თუ მოცულობა null ია მაშინ ეგეთი ავტომობილები იყვნენ თავში. დააბრუნებს დალაგებულ კოლექციას

  `sortByEngineVolumeDESC` - დაალაგებს `vehicles`-ს ძრავის მოცულობის კლებადობის მიხედვით. თუ მოცულობა null ია მაშინ ეგეთი ავტომობილები იყვნენ ბოლოში. დააბრუნებს დალაგებულ კოლექციას

  `sortByEngineEmissionASC` - დაალაგებს `vehicles`-ს ძრავის გამონაბოლქვის ზრდადობის მიხედვით. თუ გამონაბოლქვი `null` ია მაშინ ეგეთი ავტომობილები იყვნენ თავში. დააბრუნებს დალაგებულ კოლექციას

  `sortByEngineEmissionDESC` - დაალაგებს `vehicles`-ს ძრავის გამონაბოლქვის კლებადობის მიხედვით. თუ გამონაბოლქვი `null` ია მაშინ ეგეთი ავტომობილები იყვნენ ბოლოში. დააბრუნებს დალაგებულ კოლექციას

  `sortBySeatsASC` - დაალაგებს `vehicles`-ს ადგილების ზრდადობის მიხედვით. დააბრუნებს დალაგებულ კოლექციას

  `sortBySeatsDESC` - დაალაგებს `vehicles`-ს ადგილების კლებადობის მიხედვით. დააბრუნებს დალაგებულ კოლექციას 
  
  `sortByBrakingEfficiencyASC` - დაალაგებს `vehicles`-ს დამუხრუჭების ეფექტურობის ზრდადობის მიხედვით. დააბრუნებს დალაგებულ კოლექციას

  `sortByBrakingEfficiencyDESC` - დაალაგებს `vehicles`-ს დამუხრუჭების ეფექტურობის კლებადობის მიხედვით. დააბრუნებს დალაგებულ კოლექციას

  **`sortBySeatsOrThenBrakingEfficiencyOrThenEngineEmissionBy (boolean seatsASC, boolean brakingEfficiencyASC, boolean engineEmissionASC)` დაალაგებს `vehicles`-ს
  **ჯერ** ადგილების მიხედვით და თუ `seatsASC` არის `true` მაშინ ადგილების ზრდადობით თუ არადა კლებადობით. თუ თანაბარია ადგილების რადენობა შემდეგ დამუხრუჭების ეფექტურობის მიხედვით თუ `brakingEfficiencyASC` არის `true` მაშინ დამუხრუჭების ეფექტურობის ზრდადობით თუ არადა კლებადობით. თუ თანაბარია დამუხრუჭების ეფექტურობა შემდეგ ძრავის გამონაბოლქვის მიხედვით. თუ `engineEmissionASC` არის `true` მაშინ ძრავის გამონაბოლქვის ზრდადობით თუ არადა კლებადობით.

**გატესტვა**  
შექმენით კლასი `TestPTISort` დაამატეთ `test` მეთოდი
`test` მეთოდში გამოიძახეთ თითოეული sort მეთოდი და ნახეთ რამდენად სწორად მუშაობს

---
---

# Olympic Games


`dev.omedia.olympic` package-ში შექმენით enum `Country` (ქვეყანა)  
  **შესაბამისად** `AFGHANISTAN`,`THE_GAMBIA`,`NORTH_MACEDONIA`,`ALBANIA`,`GEORGIA`,`NORWAY`,`ALGERIA`,`GERMANY`,`OMAN`,`AMERICAN_SAMOA`,`GHANA`,`PAKISTAN`,`ANDORRA`,`GREECE`,`PALAU`,`ANGOLA`,`GRENADA`,`PALESTINE`,`ANTIGUA_AND_BARBUDA`,`GUAM`,`PANAMA`,`ARGENTINA`,`GUATEMALA`,`PAPUA_NEW_GUINEA`,`ARMENIA`,`GUINEA`,`PARAGUAY`,`ARUBA`,`GUINEA_BISSAU`,`PERU`,`AUSTRALIA`,`GUYANA`,`PHILIPPINES`,`AUSTRIA`,`HAITI`,`POLAND`,`AZERBAIJAN`,`HONDURAS`,`PORTUGAL`,`THE_BAHAMAS`,`HONG_KONG`,`PUERTO_RICO`,`BAHRAIN`,`HUNGARY`,`QATAR`,`BANGLADESH`,`ICELAND`,`ROMANIA`,`BARBADOS`,`INDIA`,`RUSSIAN_FEDERATION`,`BELARUS`,`INDONESIA`,`RWANDA`,`BELGIUM`,`IRAN`,`SAINT_KITTS_AND_NEVIS`,`BELIZE`,`IRAQ`,`SAINT_LUCIA`,`BENIN`,`IRELAND`,`SAINT_VINCENT_AND_THE_GRENADINES`,`BERMUDA`,`ISRAEL`,`SAMOA`,`BHUTAN`,`ITALY`,`SAN_MARINO`,`BOLIVIA`,`JAMAICA`,`SAO_TOME_AND_PRINCIPE`,`BOSNIA_AND_HERZEGOVINA`,`JAPAN`,`SAUDI_ARABIA`,`BOTSWANA`,`JORDAN`,`SENEGAL`,`BRAZIL`,`KAZAKHSTAN`,`SERBIA`,`BRITISH_VIRGIN_ISLANDS`,`KENYA`,`SEYCHELLES`,`BRUNEI_DARUSSALAM`,`KIRIBATI`,`SIERRA_LEONE`,`BULGARIA`,`KOREA_NORTH`,`SINGAPORE`,`BURKINA_FASO`,`KOREA_SOUTH`,`SLOVAKIA`,`BURUNDI`,`KOSOVO`,`SLOVENIA`,`CAMBODIA`,`KUWAIT`,`SOLOMON_ISLANDS`,`CAMEROON`,`KYRGYZSTAN`,`SOMALIA`,`CANADA`,`LAOS`,`SOUTH_AFRICA`,`CAPE_VERDE`,`LATVIA`,`SOUTH_SUDAN`,`CAYMAN_ISLANDS`,`LEBANON`,`SPAIN`,`CENTRAL_AFRICAN_REPUBLIC`,`LESOTHO`,`SRI_LANKA`,`CHAD`,`LIBERIA`,`SUDAN`,`CHILE`,`LIBYA`,`SURINAME`,`CHINA`,`LIECHTENSTEIN`,`SWEDEN`,`COLOMBIA`,`LITHUANIA`,`SWITZERLAND`,`COMOROS`,`LUXEMBOURG`,`SYRIA`,`CONGO`,`MADAGASCAR`,`TAIWAN_CHINESE_TAIPEI`,`MALAWI`,`TAJIKISTAN`,`REPUBLIC_OF_THE_COOK_ISLANDS`,`MALAYSIA`,`TANZANIA`,`COSTA_RICA`,`MALDIVES`,`THAILAND`,`COTE_D_IVOIRE`,`MALI`,`TOGO`,`CROATIA`,`MALTA`,`TONGA`,`CUBA`,`MARSHALL_ISLANDS`,`TRINIDAD_AND_TOBAGO`,`CYPRUS`,`MAURITANIA`,`TUNISIA`,`CZECH_REPUBLIC`,`MAURITIUS`,`TURKEY`,`DENMARK`,`MEXICO`,`TURKMENISTAN`,`DJIBOUTI`,`FED_STATES_OF_MICRONESIA`,`TUVALU`,`DOMINICA`,`MOLDOVA`,`UGANDA`,`DOMINICAN_REPUBLIC`,`MONACO`,`UKRAINE`,`EAST_TIMOR_TIMOR_LESTE`,`MONGOLIA`,`UNITED_ARAB_EMIRATES`,`ECUADOR`,`MONTENEGRO`,`UNITED_KINGDOM_GREAT_BRITAIN`,`EGYPT`,`MOROCCO`,`UNITED_STATES`,`EL_SALVADOR`,`MOZAMBIQUE`,`URUGUAY`,`EQUATORIAL_GUINEA`,`MYANMAR_BURMA`,`UZBEKISTAN`,`ERITREA`,`NAMIBIA`,`VANUATU`,`ESTONIA`,`NAURU`,`VENEZUELA`,`ESWATINI`,`NEPAL`,`VIETNAM`,`ETHIOPIA`,`NETHERLANDS`,`VIRGIN_ISLANDS_US`,`FIJI`,`NEW_ZEALAND`,`YEMEN`,`FINLAND`,`NICARAGUA`,`ZAMBIA`,`FRANCE`,`NIGER`,`ZIMBABWE`,`GABON`,`NIGERIA` ტიპებით;
<br><br>  
  
`dev.omedia.olympic` package-ში შექმენით enum `MedalType` (მედლის ტიპი)  
  შესაბამისად `GOLD`, `SILVER`, `BRONZE` ტიპებით;
<br><br>

`dev.omedia.olympic` package-ში შექმენით enum `OlympicGameSeason` (ოლიმპიური თამაშის სეზონი)  
შესაბამისად `WINTER`, `SUMMER` ტიპებით;
<br><br>

`dev.omedia.olympic` package-ში შექმენით enum `OlympicGame` (ოლიმპიური თამაში)  
  **შესაბამისად** `BASKETBALL_3X3`,`ARCHERY`,`ARTISTIC_GYMNASTICS`,`ARTISTIC_SWIMMING`,`ATHLETICS`,`BADMINTON`,`BASEBALL_SOFTBALL`,`BASKETBALL`,`BEACH_VOLLEYBALL`,`BMX`,`BOXING`,`CANOE`,`DIVING`,`EQUESTRIAN`,`FENCING`,`FOOTBALL`,`GOLF`,`HANDBALL`,`HOCKEY`,`JUDO`,`KARATE`,`MARATHON_SWIMMING`,`MODERN_PENTATHLON`,`MOUNTAIN_BIKE`,`RHYTHMIC_GYMNASTICS`,`ROAD_CYCLING`,`ROWING`,`RUGBY`,`SAILING`,`SHOOTING`,`SKATEBOARDING`,`SPORT_CLIMBING`,`SURFING`,`SWIMMING`,`TABLE_TENNIS`,`TAEKWONDO`,`TENNIS`,`TRACK_CYCLING`,`TRAMPOLINE`,`TRIATHLON`,`VOLLEYBALL`,`WATER_POLO`,`WEIGHTLIFTING`,`WRESTLING`  (_რომლის შემთხვევაშიც `getSeason` დააბრუნებს  **`OlympicGameSeason.SUMMER`**-ს_)
**და** `ALPINE_SKIING`,`BIATHLON`,`BOBSLEIGH`,`CROSS_COUNTRY_SKIING`,`CURLING`,`FIGURE_SKATING`,`FREESTYLE_SKIING`,`ICE_HOCKEY`,`LUGE`,`NORDIC_COMBINED`,`SHORT_TRACK_SPEED_SKATING`,`SKELETON`,`SKI_JUMPING`,`SNOWBOARD`,`SPEED_SKATING`,  (_რომლის შემთხვევაშიც `getSeason` დააბრუნებს  **`OlympicGameSeason.WINTER`**-ს_) ტიპებით
- მეთოდები
  `getSeason` - დააბრუნებს ოლიმპიური თამაშის სეზონს
<br><br>

`dev.omedia.olympic` package-ში შექმენით კლასი `Game` (თამაში) \
- ველები:
  `olympicGame` - თამაში  
  `year` - წელი
- კონსტრუქტორი  
  all-args კოსნტრუქტორი - თუ olympicGame არ  შეესაბამება წელს დაბეჭდეთ შეცდომის ტექსტი `არაწორი ოლიპიური თამაშის წელი: ${პარამეტრად გადმოცემული წელი}` და გამოიძახეთ System.exit(1);
  შესაბამის წლის გამოთვლისას გაითვალისწინეთ:
    1. თუ წელი > წლევანდელ წელზე დაბეჭდეთ შეცდომის ტექსტი `მომავლის თარიღი: ჯერ ოლიპიური თამაში ${პარამეტრად გადმოცემული წელი} წელს ჯერ არ ჩატარებულა.` და გამოიძახეთ `System.exit(1);`
    2. 4-ის ჯერადი წლები რომელიც > 1896. და ამ დროს `olympicGame`-ს სეზონი უნდა იყოს ზაფხული
    3. ყველა 2-ის ჯერადი წელი რომელიც არაა 4-ის ჯერადი, ასევე ეს წელი > 1924 და ამ დროს `olympicGame`-ს სეზონი უნდა იყოს ზამთარი.
    4. სხვა შემთხვევაში დაბეჭდეთ შეცდომის ტექსტი `არაწორი ოლიპიური თამაშის წელი: ${პარამეტრად გადმოცემული წელი}` და გამოიძახეთ `System.exit(1);`
- მეთოდები  
  getter მეთოდები  
  `toString`,`hashCode, `equals`
<br><br>

`dev.omedia.olympic` package-ში შექმენით კლასი `Medal` (მედალი)  
- ველები:  
  `game` - თამაში  
  `type` - მედლის ტიპი  
  `country` - ქვეყანა  
- მეთოდები  
  getter მეთოდები  
  `toString`, `hashCode`, `equals`
<br><br>  
  
`dev.omedia.olympic` package-ში შექმენით კლასი `OlympicGameGenerator` (ოლიმპიური თამაშის გენერატორი)
- მეთოდები სტატიკური  
  `generateGamesForYear` რომელსაც გადაეცემა წელი, და დააბრუნებს თამაშების კოლექციას (`Set<Game>`) რომელიც შესაბამის წელს ჩატარდა. თუ ზამთრის სეზონის წელი იყო, მაშინ ყველა ზამთრის სეზონის `OlympicGame`-სთვის უნდა დაგენერირდეს თამაში. - შესაბამისად ზაფხულზეც.  
  `generateMedalsForGames` , რომელსაც პარამეტრად გადაეცემა თამაშების კოლექცია (`Collection<Game>`) და მედლის ტიპი. დააბრუნებს მედლების კოლექციას (`Set<Medal>`). მეთოდი თითოეული თამაშისთვის შექმნის Medal -ის instances და შეავსებს `game` და `type` ველებს შესაბამისი პარამეტრებით. ხოლო `country`-ს შეავსებს შემთხვევითად.  
  `generateOlympicMedalsForYear` სადაც პარამეტრად გადმოეცემა წლ**ებ**ი და დააბრუნებს მედლების კოლექციას (`Set<Medal>`) . დაბრუნებულ კოლექციაში აუცილებლად უნდა იყოს თოთოეულ წელზე 3ვე მედლის ობიექტი.
  _შენიშვნა: დაგჭირდებათ `generateMedalsForGames` & `generateGamesForYear` მეთოდები_


### გატესტვა
შექმენით კლასი `TestOlympics` და დაუმატეთ `test` მეთოდი.  
  `test` მეთოდში
  1. გამოიძახეთ generateOlympicMedalsForYear მეთოდი.
  1. დაფილტრეთ შედეგი და დაბეჭდეთ რაოდენობა სულ რამდენი მედალი გაიცა 2000 წლის შემდეგ. იგივე ოპერაცია ჩაატარეთ თითოეულ მედალზე.
  1. შედეგიდან გამომდინარე დაალაგეთ ქვეყნები საერთო მედლების რაოდენობის მიხედვით (კლებადობით) და დაბეჭდეთ შემდეგი ფორმატით: `${ქვეყნის სახელი} : ${მედლების საერთო რაოდენობა} , ` და ა.შ.
იგივე ოპერაცია ჩაატარეთ თითოეულ მედალზე.

**ბეჭდვისას წინ მიაწერეთ რა იბეჭდება.**

---
---

**შენიშვნა**: _დაამატეთ თუ რამე კლასი გჭირდებათ._
**`main` მეთოდში გამოიძახეთ თითოეული Test კლასის `test` მეთოდი**

---
---
<br/>

## Stream Exercises
1. 
    Write a program where the customer enters a sentence **(don’t consider punctuation)**, then
define and display alphabetically a **sequence of unique words**. `Use lambda functions`. 
2. 
    Create `any class` (containing your name, surname, etc.) that will have its own properties.
    Create a `collection` containing `objects` of this class - where several objects of the class must
    have the same last name. **Use lambda functions to select from collection the first object with
the same last name.** 
3. Create the class `Invoice`.
   Class `Invoice` includes `four` instance variables-  
   `partNumber (type String)`  
   `partDescription (type String)`  
   `quantity of the item being purchased (type int)`,  
   `pricePerltem (type double)` and corresponding get methods.  
   Perform the following queries on the array of Invoice objects and display the results:
   1) Use streams to sort the Invoice objects by `part Description`, then display the results.
   2) Use streams to sort the Invoice objects by `pricePerItem`, then display the results.
   3) Use streams to `map` each Invoice to its `part Description and quantity, sort the results by quantity, then display the results.`
   4) Use streams to `map` each Invoice to its `part Description and the value of the Invoice` (i.e., quantity * pricePertem). `Order` the results by `Invoice` value.
   5) Modify Part (**iv**) to select the `Invoice` values in the range `$200 to $500.`
   6) Find any one `Invoice` in which the part Description `contains` the word `“saw”`
