package com.adelsonsljunior.database;

import io.github.cdimascio.dotenv.Dotenv;

public class Credentials {

    private final String port, dbname, host, username, password;
    private static Credentials instance;

    public Credentials() {
        Dotenv dotenv = Dotenv.configure().load();
        this.port = dotenv.get("DB_PORT");
        this.host = dotenv.get("DB_HOST");
        this.dbname = dotenv.get("DB_NAME");
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASSWORD");
    }

    public static Credentials getInstance() {
        if (instance == null) {
            instance = new Credentials();
        }
        return instance;
    }

    public String getPort() {
        return port;
    }

    public String getDbname() {
        return dbname;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
