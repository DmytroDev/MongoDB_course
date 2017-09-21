package com.training.data;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.training.data.Constants.PROJECT_AMOUNT;
import static com.training.data.Constants.TASK_AMOUNT;
import static com.training.data.Constants.TIME_TRACKING_AMOUNT;
import static com.training.data.Constants.USER_AMOUNT;
import static com.training.data.Constants.random;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Data {

    private static Map<Integer, ObjectId> userIDs;
    private static Map<Integer, ObjectId> projectIDs;
    private static Map<Integer, ObjectId> tasksIDs;
    private static Map<Integer, ObjectId> timeTrackingIDs;

    public void init() {
        this.userIDs = generateIDsList(USER_AMOUNT);
        this.projectIDs = generateIDsList(PROJECT_AMOUNT);
        this.tasksIDs = generateIDsList(TASK_AMOUNT);
        this.timeTrackingIDs = generateIDsList(TIME_TRACKING_AMOUNT);
        //this.managerIds =
    }

    private final static List<String> userNames = Stream.of(
            "Arnold",
            "David",
            "Ivan",
            "Peter",
            "Olga",
            "Galina",
            "Vasiliy",
            "Sergiy",
            "Pollina",
            "Katy"
    ).collect(Collectors.toList());

    public static Map<Integer, ObjectId> getUserIDs() {
        return userIDs;
    }

    public static Map<Integer, ObjectId> getProjectIDs() {
        return projectIDs;
    }

    public static Map<Integer, ObjectId> getTasksIDs() {
        return tasksIDs;
    }

    public static Map<Integer, ObjectId> getTimeTrackingIDs() {
        return timeTrackingIDs;
    }

    public static String getRandomUserName() {
        return userNames.get(random.nextInt(10));
    }

    private Map<Integer, ObjectId> generateIDsList(int amount) {
        Map<Integer, ObjectId> userIDsMap = new HashMap<>();
        IntStream.rangeClosed(1, amount).forEach(i -> userIDsMap.put(i, ObjectId.get()));

        return userIDsMap;
    }

}
