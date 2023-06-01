package util;

import org.joml.Vector4f;

public enum GLColor {
    // Basic colors
    BLACK(0.0f, 0.0f, 0.0f),
    WHITE(1.0f, 1.0f, 1.0f),
    RED(1.0f, 0.0f, 0.0f),
    GREEN(0.0f, 1.0f, 0.0f),
    BLUE(0.0f, 0.0f, 1.0f),
    YELLOW(1.0f, 1.0f, 0.0f),
    MAGENTA(1.0f, 0.0f, 1.0f),
    CYAN(0.0f, 1.0f, 1.0f),
    GRAY(0.5f, 0.5f, 0.5f);

    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    private GLColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 1;
    }

    private GLColor(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }
}
