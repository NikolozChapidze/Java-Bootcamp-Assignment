package dev.omedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
    INSTANCE;
    private final String jdbcUrl = "jdbc:postgresql://localhost:12345/postgres";
    private final String user="postgres";
    private final String password="postgres";


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,user,password);
    }

}
