package net.serenitybdd.core.webdriver.driverproviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CapabilitiesConverter {
    public static Properties capabilitiesToProperties(Capabilities capabilities) {
        Properties properties = new Properties();
        capabilities.asMap().forEach(
                (key, value) -> properties.setProperty(key,value.toString())
        );
        return properties;
    }

    public static Map<String, Object> optionsToMap(String options) {
        Map<String, Object> driverOptions = new HashMap<>();
        if (!options.isEmpty()) {
            String driverOptionsInJsonFormat = options
                    .replace("\\\"", "\"")
                    .replace("\\n", System.lineSeparator());

            driverOptionsInJsonFormat = StringUtils.strip(driverOptionsInJsonFormat);

            driverOptions = new Gson().fromJson(driverOptionsInJsonFormat, new TypeToken<HashMap<String, Object>>() {
            }.getType());
        }

        return driverOptions;
    }
}
