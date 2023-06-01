package util;

import components.Spritesheet;
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

    public static void init() {
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
            Logger.logException(e);
        }
    }

    public static void setConfigValue(String section, String key, String value) {
        configLoader.setValue(section, key, value);
    }

    public static String getConfigValue(String section, String key) {
        return configLoader.getValue(section, key);
    }

    public static void removeConfigValue(String section, String key) {
        configLoader.removeValue(section, key);
    }
}