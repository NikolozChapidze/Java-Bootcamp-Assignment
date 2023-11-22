package dev.omedia.pti;

import java.util.Map;

public class InspectionRules {
    private Map<EngineType,Double> engineMaxEmission;
    private double brakingEfficiencyMinRequirement;
    private double minivanEmissionImmunity;

    {
        minivanEmissionImmunity = Math.random()*(2.0 - 0.5 + 1 ) + 0.5;
    }

    public InspectionRules(Map<EngineType, Double> engineMaxEmission, double brakingEfficiencyMinRequirement) {
        this.engineMaxEmission = engineMaxEmission;
        this.brakingEfficiencyMinRequirement = brakingEfficiencyMinRequirement;
    }

    public double getEngineMaxEmission(EngineType type) {
        return engineMaxEmission.get(type);
    }

    public void setEngineMaxEmission(EngineType type, Double value) {
        this.engineMaxEmission.put(type,value);
    }

    public double getBrakingEfficiencyMinRequirement() {
        return brakingEfficiencyMinRequirement;
    }

    public void setBrakingEfficiencyMinRequirement(double brakingEfficiencyMinRequirement) {
        this.brakingEfficiencyMinRequirement = brakingEfficiencyMinRequirement;
    }

    public double getMinivanEmissionImmunity() {
        return minivanEmissionImmunity;
    }

    public void setMinivanEmissionImmunity(double minivanEmissionImmunity) {
        this.minivanEmissionImmunity = minivanEmissionImmunity;
    }

    @Override
    public String toString() {
        return "InspectionRules{" +
                "engineMaxEmission=" + engineMaxEmission +
                ", brakingEfficiencyMinRequirement=" + brakingEfficiencyMinRequirement +
                ", minivanEmissionImmunity=" + minivanEmissionImmunity +
                '}';
    }
}
