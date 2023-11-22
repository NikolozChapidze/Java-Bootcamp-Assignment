package dev.omedia.domain;

public interface GenericEntity <L>{
    void update(L source);
    Long getId();
//    L createNewInstance();

}
