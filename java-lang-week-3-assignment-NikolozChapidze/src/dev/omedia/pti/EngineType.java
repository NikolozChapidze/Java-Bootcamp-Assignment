package dev.omedia.pti;

public enum EngineType {
    PETROL,
    DIESEL,
    ELECTRIC,
    HYBRID;

    public boolean hasEmission(){
        return this != ELECTRIC;
    }
}
