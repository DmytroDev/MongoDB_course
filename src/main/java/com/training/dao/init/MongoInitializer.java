package com.training.dao.init;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.training.data.Data;

import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class MongoInitializer {

    private MongoClient mongoClient;
    private DB db;

    public MongoInitializer(Properties prop) {
        try {
            mongoClient = new MongoClient(prop.getProperty("host"), Integer.valueOf(prop.getProperty("port")));
            db = mongoClient.getDB(prop.getProperty("dbname"));
            new Data().init();
        } catch (UnknownHostException e) {
            System.err.println("Don't connect!");
        }
    }

    public void close(){
        mongoClient.close();
    }

    public DBCollection getTableByName(String tableName) {
        return db.getCollection(tableName);
    }

}

