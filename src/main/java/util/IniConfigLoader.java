package util;

import org.joml.Vector4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IniConfigLoader {
    private Map<String, Map<String, String>> data;
    private String configFile;

    public IniConfigLoader(String configFile) {
        this.configFile = configFile;
        data = new HashMap<>();
    }

    public void load() throws IOException {
        data.clear();
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        String line;
        String section = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            // Check for comments
            if (line.isEmpty() || line.startsWith(";") || line.startsWith("#")) {
                continue;
            }

            if (line.startsWith("[")) {
                int endIndex = line.indexOf(']');
                if (endIndex != -1) {
                    section = line.substring(1, endIndex);
                    data.put(section, new HashMap<>());
                }
            } else {
                if (section != null) {
                    int delimiterIndex = line.indexOf('=');
                    if (delimiterIndex != -1) {
                        String key = line.substring(0, delimiterIndex).trim();
                        String value = line.substring(delimiterIndex + 1).trim();
                        data.get(section).put(key, value);
                    }
                }
            }
        }

        reader.close();
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(configFile);

        for (Map.Entry<String, Map<String, String>> sectionEntry : data.entrySet()) {
            writer.write("[" + sectionEntry.getKey() + "]\n");
            Map<String, String> sectionData = sectionEntry.getValue();
            for (Map.Entry<String, String> entry : sectionData.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.write("\n");
        }

        writer.close();
    }

    public String getValue(String section, String key) {
        Map<String, String> sectionData = data.get(section);
        if (sectionData != null) {
            return sectionData.get(key);
        }
        return null;
    }

    public int getValueInt(String section, String key) {
        return Integer.parseInt(getValue(section, key));
    }

    public float getValueFloat(String section, String key) {
        return Float.parseFloat(getValue(section, key));
    }

    public void setValue(String section, String key, String value) {
        data.computeIfAbsent(section, k -> new HashMap<>()).put(key, value);
    }

    public void removeValue(String section, String key) {
        Map<String, String> sectionData = data.get(section);
        if (sectionData != null) {
            sectionData.remove(key);
        }
    }

    public String getFilename() {
        return configFile;
    }

}
