package util;

import components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static IniConfigLoader configLoader;

    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<>();

    public static void initConfig() {
        configLoader = new IniConfigLoader("assets/config/engine.ini");
        try {
            configLoader.load();
        } catch (IOException e) {
            Logger.logError("Failed to load config file: '" + configLoader.getFilename() + "'");
            Logger.logException(e);
        }
    }

    public static Shader getShader(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaders.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaders.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static void addSpritesheet(String resourceName, Spritesheet spritesheet) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }

    public static Spritesheet getSpritesheet(String resourceName) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            Logger.logError("Tried to access spritesheet: '" + resourceName + "' and it has not been added to the asset pool.");
            assert false : "";
        }
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }

    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture();
            texture.init(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    public static void saveConfig() {
        try {
            configLoader.save();
        } catch (IOException e) {
            Logger.logError("Failed to save config file: 'assets/config/engine.ini'");
            Logger.logFatalException(e);
        }
    }

    public static void setConfigValue(String section, String key, Object value) {
        configLoader.setValue(section, key, value);
    }

    public static Object getConfigValue(String section, String key) {
        return configLoader.getValue(section, key);
    }

    public static void removeConfigValue(String section, String key) {
        configLoader.removeValue(section, key);
    }

    public static int getConfigValueInt(String section, String key) {
        return configLoader.getValueInt(section, key);
    }

    public static float getConfigValueFloat(String section, String key) {
        return configLoader.getValueFloat(section, key);
    }

    public static Vector2f getConfigValueVector2d(String section, String key) {
        return configLoader.getValueVector2f(section, key);
    }

    public static Vector3f getConfigValueVector3f(String section, String key) {
        return configLoader.getValueVector3f(section, key);
    }

    public static Vector4f getConfigValueVector4f(String section, String key) {
        return configLoader.getValueVector4f(section, key);
    }

}