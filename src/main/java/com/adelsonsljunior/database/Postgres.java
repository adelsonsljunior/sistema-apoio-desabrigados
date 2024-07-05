package com.adelsonsljunior.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {

    public static Connection getConnection() {
        String driver = "org.postgresql.Driver";
        Credentials credentials = Credentials.getInstance();
        String url = "jdbc:postgresql://" + credentials.getHost() + ":" + credentials.getPort() + "/"
                + credentials.getDbname();

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Erro ao conectar com banco de dados!");
    }

}
