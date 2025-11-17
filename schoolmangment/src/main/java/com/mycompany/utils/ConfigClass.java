package com.mycompany.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigClass {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigClass.class.getClassLoader().getResourceAsStream("newproperties.properties")) {
            if (input != null) {
                //System.err.println("inputinputinput "+input.available());
                props.load(input);
            } else {
                throw new RuntimeException("app.properties not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load app.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
} 

