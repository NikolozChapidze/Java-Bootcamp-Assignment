package dev.omedia.flyWeigh;

import java.util.HashMap;
import java.util.Map;

public class CarFactory {
    static Map<String, Engine> engineMap = new HashMap<>();

    public static Engine getEngine(String name, int manufactureYear, String type){
        Engine result = engineMap.get(name);
        if(result == null){
            result = new Engine(name,manufactureYear,type);
            engineMap.put(name,result);
        }
        return result;
    }
}
