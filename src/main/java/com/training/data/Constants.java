package com.training.data;

import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Constants {
    // amount
    public static final int USER_AMOUNT = 100_000;
    public static final int PROJECT_AMOUNT = 20_000;
    public static final int TASK_AMOUNT = 200_000;
    public static final int TIME_TRACKING_AMOUNT = 1_000_000;
    public static final int NUMBER_RANGE_FOR_MANAGERS = 10_000;

    // DB constants
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String DB_NAME = "dbname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    // Tables names:
    public static final String USERS = "users";
    public static final String PROJECTS = "projects";
    public static final String TASKS = "tasks";
    public static final String TIME_TRACKING = "time_tracking";

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Random random = new Random();

    public static Properties getDBProperties() {
        Properties prop = new Properties();
        prop.setProperty(HOST, "localhost");
        prop.setProperty(PORT, "27017");
        prop.setProperty(DB_NAME, "pm");
        prop.setProperty(LOGIN, "root");
        prop.setProperty(PASSWORD, "root");
        return prop;
    }

}
