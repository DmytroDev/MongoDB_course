package com.training.dao.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.training.data.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.training.data.Constants.formatter;
import static com.training.data.Constants.random;
import static com.training.utils.Utils.getRandomManagerId;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class ProjectDao {

    private DBCollection table;
    public ProjectDao(DBCollection table) {
        this.table = table;
    }

    public void createAndSaveProject(int number) {
        BasicDBObject document = new BasicDBObject();
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0 ,0).minusDays(random.nextInt(1_825));  // 5 years
        LocalDateTime endDate = startDate.plusDays(1 + random.nextInt(3_650));

        document
                .append("_id", Data.getProjectIDs().get(number))
                .append("projectName", "projectName_" + number)
                .append("projectManager", getRandomManagerId())
                .append("startDate", startDate.format(formatter))
                .append("endDate", endDate.format(formatter))
                .append("status", number % 2 == 0 ? "open" : number % 3 == 0 ? "closed" : "postponed")
                .append("participants", generateParticipantsList())
                .append("budget", 10_000 + random.nextInt(5_000_000));

        table.insert(document);
    }

    private List<ObjectId> generateParticipantsList() {
        return IntStream.range(0, 1 + random.nextInt(5))
                .mapToObj(i -> Data.getUserIDs().get(1 + random.nextInt(100_000)))
                .collect(Collectors.toList());
    }

}
