package com.training.dao.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.training.data.Data;

import java.time.LocalDateTime;

import static com.training.data.Constants.formatter;
import static com.training.data.Constants.random;
import static com.training.utils.Utils.getRandomManagerId;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class TaskDao {

    private DBCollection table;
    private final String STATUS_OPEN = "open";
    private final String STATUS_IN_PROGRESS = "in progress";
    private final String STATUS_COMPLETED = "completed";
    private final String STATUS_REOPENED = "reopened";
    private final String STATUS_CLOSED = "closed";

    public TaskDao(DBCollection table) {
        this.table = table;
    }

    public void createAndSaveTask(int number) {
        BasicDBObject document = new BasicDBObject();
        LocalDateTime startDate = LocalDateTime.of(2017, 10, 20, 0 ,0).minusDays(random.nextInt(90));
        LocalDateTime endDate = startDate.plusHours(1 + random.nextInt(24 * 30));

        document
                .append("_id", Data.getTasksIDs().get(number))
                .append("name", "name_" + number)
                .append("startDate", startDate.format(formatter))
                .append("endDate", endDate.format(formatter))
                .append("status", getRandomStatus(number))
                .append("description", number % 3333 == 0 ? "description: deadline" : "description for project_" + number)
                .append("project", Data.getProjectIDs().get(1 + random.nextInt(20_000)))
                .append("responsiblePerson", getRandomManagerId());
        table.insert(document);
    }

    private String getRandomStatus(int number) {
        String status;
        switch(number%10) {
            case 0:
            case 1:
                status = STATUS_OPEN;
                break;
            case 2:
            case 3:
                status = STATUS_COMPLETED;
                break;
            case 4:
                status = STATUS_REOPENED;
                break;
            case 5:
                status = STATUS_CLOSED;
            default:
                status = STATUS_IN_PROGRESS;
        }
        return status;
    }

}
