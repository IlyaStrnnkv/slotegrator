package ui.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public final class ParametersProvider {

    public static Properties getPropertiesXml() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(getProperty("config.location.env")));
        } catch (IOException exception) {
            throw new IllegalArgumentException("Property file not found");
        }
        return properties;
    }
}
