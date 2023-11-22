package dev.omedia.bootcamp;

import dev.omedia.bootcamp.annotations.CSV;
import dev.omedia.bootcamp.annotations.Column;

@CSV(source = Data.SOURCE_FILE)
public class Data {
    public static final String SOURCE_FILE = "./src/test/resources/a.csv";

    @Column(name = "id")
    private int id;

    public Data(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
