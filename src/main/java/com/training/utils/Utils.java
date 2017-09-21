package com.training.utils;

import com.training.data.Data;
import org.bson.types.ObjectId;

import static com.training.data.Constants.random;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Utils {

    public static ObjectId getRandomManagerId() {
        return Data.getUserIDs().get(1 + random.nextInt(10_000));
    }

}
