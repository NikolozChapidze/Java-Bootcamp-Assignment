package dev.omedia.pti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InspectionCenter {
    public static InspectionRules inspectionRules;

    public List<InspectionFaultyTypes> inspection(Vehicle vehicle){
        List<InspectionFaultyTypes> result = new ArrayList<>();

        if(vehicle.getEngine().getType() != EngineType.ELECTRIC) {
            double maxEmission = inspectionRules.getEngineMaxEmission(vehicle.getEngine().getType());
            maxEmission += vehicle.isMinivan() ? inspectionRules.getMinivanEmissionImmunity() : 0;

            if (vehicle.getEngine().getEmission() > maxEmission) {
                result.add(InspectionFaultyTypes.EMISSION);
            }
        }

        if (inspectionRules.getBrakingEfficiencyMinRequirement() >= vehicle.getBrakingEfficiency()){
            result.add(InspectionFaultyTypes.BRAKING);
        }

        if(result.size() == 0)
            result.add(InspectionFaultyTypes.NONE);

        return result;
    }

    public Map< Vehicle, List < InspectionFaultyTypes >> inspection(Iterable <Vehicle> vehicles){
        Map < Vehicle, List < InspectionFaultyTypes >> result = new HashMap<>();
        vehicles.forEach(v -> result.put(v,this.inspection(v)));
        return result;
    }
}
