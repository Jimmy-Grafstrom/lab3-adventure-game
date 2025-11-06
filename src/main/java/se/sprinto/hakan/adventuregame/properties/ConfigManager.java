package se.sprinto.hakan.adventuregame.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager instance;
    private final Properties properties;

    private final String resourceName = "appInfo.properties";

    private ConfigManager() {
        properties = new Properties();

/** Använder InputStream för att ta emot ström av data från classpath,
 *  kan antingen vara en lös fil eller en jar fil*/
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource file " + resourceName + " does not exist");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load " + resourceName);
        }
    }

/**  Synchronized betyder att metoden är trådsäker.
      - Om 2 användare skulle använda metoden samtidigt
        så kommer endast en tråd att aktiveras
 */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Hjälpmetod för att hämta enstaka properties
     */
    private String getValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }

    /**
     * Bygger ihop properties till ett svar
     */
    public String getVersionAndName() {
        return "Author: " + getValue("author") + "\nVersion: " + getValue("version");
    }

}
