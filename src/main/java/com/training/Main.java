package com.training;

import com.training.dao.init.MongoInitializer;
import com.training.dao.repository.ProjectDao;
import com.training.dao.repository.TaskDao;
import com.training.dao.repository.TimeTrackingDao;
import com.training.dao.repository.UserDao;
import com.training.data.Constants;

import java.util.stream.IntStream;

import static com.training.data.Constants.PROJECTS;
import static com.training.data.Constants.PROJECT_AMOUNT;
import static com.training.data.Constants.TASKS;
import static com.training.data.Constants.TASK_AMOUNT;
import static com.training.data.Constants.TIME_TRACKING;
import static com.training.data.Constants.TIME_TRACKING_AMOUNT;
import static com.training.data.Constants.USERS;
import static com.training.data.Constants.USER_AMOUNT;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Population of data began .....");
        MongoInitializer initializer = new MongoInitializer(Constants.getDBProperties());

        // create and save Users
        UserDao userDao = new UserDao(initializer.getTableByName(USERS));
        IntStream.range(1, USER_AMOUNT + 1).forEach(i -> userDao.createAndSaveUser(i));

        // create and save Projects
        ProjectDao projectDao = new ProjectDao(initializer.getTableByName(PROJECTS));
        IntStream.range(1, PROJECT_AMOUNT + 1).forEach(i -> projectDao.createAndSaveProject(i));

        // create and save Tasks
        TaskDao taskDao = new TaskDao(initializer.getTableByName(TASKS));
        IntStream.range(1, TASK_AMOUNT + 1).forEach(i -> taskDao.createAndSaveTask(i));

        // create and save Time_tracking
        TimeTrackingDao timeTrackingDao = new TimeTrackingDao(initializer.getTableByName(TIME_TRACKING));
        IntStream.range(1, TIME_TRACKING_AMOUNT + 1).forEach(i -> timeTrackingDao.createAndSaveTimeTracking(i));

        initializer.close();
    }

}
