package com.training.dao.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.training.data.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.training.data.Constants.NUMBER_RANGE_FOR_MANAGERS;
import static com.training.data.Constants.formatter;
import static com.training.data.Constants.random;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class UserDao {

    private DBCollection table;

    public UserDao(DBCollection table) {
        this.table = table;
    }

    public void createAndSaveUser(int number) {
        BasicDBObject document = new BasicDBObject();
        document
                .append("_id", Data.getUserIDs().get(number))
                .append("firstName", Data.getRandomUserName())
                .append("lastName", number % 310 == 0 ? "Z" : "lastName_" + number)
                .append("dateOfBirth", LocalDateTime.of(2000, 1, 1, 0 ,0).minusDays(random.nextInt(14_000)).format(formatter))
                .append("email", "email_" + number + "@gmail.com")
                .append("phoneNumbers", createPhoneList())
                .append("position", number <= NUMBER_RANGE_FOR_MANAGERS ? "manager" : number % 2 == 0 ? "developer" : "QA")
                .append("department", "department_" + number)
                .append("workStatus", number%2 == 0 ? "fired" : "working")
                .append("photo", "http://image_repository/photo_" + number)
                .append("login", "secretLogin_" + number)
                .append("iWantToWorkUserId", getIWantToWorkUserId());

        table.insert(document);
    }

    private List<String> createPhoneList() {
        return IntStream.range(0, 1 + random.nextInt(3))
                .mapToObj(i -> "+380" + createPhoneNumber())
                .collect(Collectors.toList());
    }

    private String createPhoneNumber() {
        StringBuilder builder = new StringBuilder();
        IntStream.range(1, 8).forEach(i -> builder.append(random.nextInt(10)));

        return builder.toString();
    }

    // field - "Users I want to work with"
    public static ObjectId getIWantToWorkUserId() {
        return Data.getUserIDs().get(1 + random.nextInt(10));
    }
}
