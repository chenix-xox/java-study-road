package com.xxx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chenix
 * @create 2024-02-13 23:54
 */
public class JsonUtils {

    public static String getJson(Object object) {
        return getJson(object,"yyyy-MM-dd hh:mm:ss");
    }


    public static String getJson(Object object, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        om.setDateFormat(sdf);
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
