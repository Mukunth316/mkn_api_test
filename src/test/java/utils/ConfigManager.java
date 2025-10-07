package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    public static String getBaseURI() {
        return properties.getProperty("baseURI");
    }

    public static String getAuthToken() {
        return properties.getProperty("token");
    }

    public static String getJSONAsString() {
        return properties.getProperty("createBookingJSONAsString");
    }
    public static String getInvalidJSONAsString() {
        return properties.getProperty("missingRoomIDJSONAsString");
    }


    public static String getBookingEndPoint() {
        return properties.getProperty("createBookingJSONAsString");
    }

}
