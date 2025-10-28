package se.sprinto.hakan.adventuregame.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    private String resourceName = "appInfo.properties";

    private ConfigManager() {
        properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource file " + resourceName + " does not exist");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load " + resourceName);
        }
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }

    public String getVersionAndName() {
        return "Author: " + getValue("author") + "\nVersion: " + getValue("version");
    }

}
