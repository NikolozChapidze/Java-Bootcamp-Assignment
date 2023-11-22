package dev.omedia.pti;

import java.util.Objects;
import java.util.Random;

public class Engine {
    private final EngineType type;
    private final Integer volumeInCC;
    private final Double emission;

//    for testing
//    public Engine(EngineType type, int volumeInCC, double emission) {
//        this.type = type;
//        this.volumeInCC = volumeInCC;
//        this.emission = emission;
//    }

    public Engine() {
        this(EngineType.values()[(int) (Math.random() * EngineType.values().length)]);
    }

    public Engine(EngineType type) {
        this.type       = type;
        this.volumeInCC = this.type.hasEmission() ? (int)(Math.random()*(7000-1000+1)+1000) : null;
        this.emission   = this.type.hasEmission() ? Math.random()*(10.0 - 0.1 + 1 ) + 0.1 : null;
    }

    public EngineType getType() {
        return type;
    }

    public Integer getVolumeInCC() {
        return this.type.hasEmission() ? volumeInCC : 0;
    }

    public Double getEmission() {
        return emission;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type=" + type +
                ", volumeInCC=" + volumeInCC +
                ", emission=" + emission +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(type,volumeInCC,emission);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Engine
                &&  Objects.equals(this.type, ((Engine) obj).type)
                &&  Objects.equals(this.emission, ((Engine) obj).emission)
                &&  Objects.equals(this.volumeInCC, ((Engine) obj).volumeInCC));
    }
}
