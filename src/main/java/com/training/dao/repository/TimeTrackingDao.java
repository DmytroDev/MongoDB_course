package com.training.dao.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.training.data.Data;

import java.time.LocalDateTime;

import static com.training.data.Constants.formatter;
import static com.training.data.Constants.random;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class TimeTrackingDao {

    private DBCollection table;

    public TimeTrackingDao(DBCollection table) {
        this.table = table;
    }

    public void createAndSaveTimeTracking(int number) {
        BasicDBObject document = new BasicDBObject();

        LocalDateTime date = LocalDateTime.of(2017, 10, 20, 0 ,0).minusDays(random.nextInt(90));

        document
                .append("_id", Data.getTimeTrackingIDs().get(number))
                .append("description", "description_" + number)
                .append("taskId", Data.getTimeTrackingIDs().get(number))
                .append("projectId", Data.getProjectIDs().get(number))
                .append("date", date.format(formatter))
                .append("hoursSpent", 1 + number%10);

        table.insert(document);
    }

}
