package ru.javawebinar.topjava.graduation;

public class Profiles {
    public static final String
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    public static final String ACTIVE_DB = HSQL_DB;

    public static String getActiveDbProfile(){
        return ACTIVE_DB;
    }
}
