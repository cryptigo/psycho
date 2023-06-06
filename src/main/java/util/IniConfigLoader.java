package util;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IniConfigLoader {
    private Map<String, Map<String, Object>> data;
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
                        data.get(section).put(key, parseValue(value));
                    }
                }
            }
        }

        reader.close();
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(configFile);

        for (Map.Entry<String, Map<String, Object>> sectionEntry : data.entrySet()) {
            writer.write("[" + sectionEntry.getKey() + "]\n");
            Map<String, Object> sectionData = sectionEntry.getValue();
            for (Map.Entry<String, Object> entry : sectionData.entrySet()) {
                writer.write(entry.getKey() + "=" + formatValue(entry.getValue()) + "\n");
            }
            writer.write("\n");
        }

        writer.close();
    }

    public String getValue(String section, String key) {
        Map<String, Object> sectionData = data.get(section);
        if (sectionData != null) {
            Object value = sectionData.get(key);
            if (value != null) {
                return value.toString();
            }
        }
        return null;
    }

    public int getValueInt(String section, String key) {
        return Integer.parseInt(getValue(section, key));
    }

    public float getValueFloat(String section, String key) {
        return Float.parseFloat(getValue(section, key));
    }

    public Vector2f getValueVector2f(String section, String key) {
        String value = getValue(section, key);
        if (value != null) {
            String[] components = value.split(",");
            if (components.length == 2) {
                float x = Float.parseFloat(components[0].trim());
                float y = Float.parseFloat(components[1].trim());
                return new Vector2f(x, y);
            }
        }
        return new Vector2f();
    }

    public Vector3f getValueVector3f(String section, String key) {
        String value = getValue(section, key);
        if (value != null) {
            return parseVector3f(value);
        }
        return null;
    }

    public Vector4f getValueVector4f(String section, String key) {
        String value = getValue(section, key);
        if (value != null) {
            return parseVector4f(value);
        }
        return null;
    }

    public void setValue(String section, String key, Object value) {
        data.computeIfAbsent(section, k -> new HashMap<>()).put(key, value);
    }

    public void removeValue(String section, String key) {
        Map<String, Object> sectionData = data.get(section);
        if (sectionData != null) {
            sectionData.remove(key);
        }
    }

    public String getFilename() {
        return configFile;
    }

    private Object parseValue(String value) {
        if (isVector4fValue(value)) {
            return parseVector4f(value);
        } else if (isVector3fValue(value)) {
            return parseVector3f(value);
        } else if (isVector2fValue(value)) {
            return parseVector2f(value);
        } else if (isIntegerValue(value)) {
            return Integer.parseInt(value);
        } else if (isFloatValue(value)) {
            return Float.parseFloat(value);
        }
        return value;
    }

    private String formatValue(Object value) {
        if (value instanceof Vector4f) {
            return formatVector4f((Vector4f) value);
        } else if (value instanceof Vector3f) {
            return formatVector3f((Vector3f) value);
        } else if (value instanceof Vector2f) {
            return formatVector2f((Vector2f) value);
        }
        return value.toString();
    }

    private boolean isVector4fValue(String value) {
        // Regular expression pattern for Vector4f format: "(x, y, z, w)"
        String vector4fPattern = "\\((-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?)\\)";
        return value.matches(vector4fPattern);
    }

    private boolean isVector3fValue(String value) {
        // Regular expression pattern for Vector3f format: "(x, y, z)"
        String vector3fPattern = "\\((-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?)\\)";
        return value.matches(vector3fPattern);
    }

    private boolean isVector2fValue(String value) {
        // Regular expression pattern for Vector2f format: "(x, y)"
        String vector2fPattern = "\\((-?\\d+(\\.\\d+)?), (-?\\d+(\\.\\d+)?)\\)";
        return value.matches(vector2fPattern);
    }

    private boolean isIntegerValue(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloatValue(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Vector4f parseVector4f(String value) {
        value = value.replace("(", "").replace(")", "");
        String[] components = value.split(",");
        float x = Float.parseFloat(components[0].trim());
        float y = Float.parseFloat(components[1].trim());
        float z = Float.parseFloat(components[2].trim());
        float w = Float.parseFloat(components[3].trim());
        return new Vector4f(x, y, z, w);
    }

    private Vector3f parseVector3f(String value) {
        value = value.replace("(", "").replace(")", "");
        String[] components = value.split(",");
        float x = Float.parseFloat(components[0].trim());
        float y = Float.parseFloat(components[1].trim());
        float z = Float.parseFloat(components[2].trim());
        return new Vector3f(x, y, z);
    }

    private Vector2f parseVector2f(String value) {
        String[] components = value.substring(1, value.length() - 1).split(",");
        float x = Float.parseFloat(components[0].trim());
        float y = Float.parseFloat(components[1].trim());
        return new Vector2f(x, y);
    }

    private String formatVector4f(Vector4f vector) {
        return String.format("(%f, %f, %f, %f)", vector.x, vector.y, vector.z, vector.w);
    }

    private String formatVector3f(Vector3f vector) {
        return String.format("(%f, %f, %f)", vector.x, vector.y, vector.z);
    }

    private String formatVector2f(Vector2f vector) {
        return String.format("(%f, %f)", vector.x, vector.y);
    }
}