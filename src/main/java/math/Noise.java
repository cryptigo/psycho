package math;

import java.util.Random;

public class Noise {
    private static final int DEFAULT_SEED = 0;
    private static final int DEFAULT_OCTAVES = 4;
    private static final double DEFAULT_PERSISTENCE = 0.5;
    private static final double DEFAULT_FREQUENCY = 1.0;

    private int seed;
    private int octaves;
    private double persistence;
    private double frequency;
    private Random random;

    public Noise() {
        this(DEFAULT_SEED, DEFAULT_OCTAVES, DEFAULT_PERSISTENCE, DEFAULT_FREQUENCY);
    }

    public Noise(int seed, int octaves, double persistence, double frequency) {
        this.seed = seed;
        this.octaves = octaves;
        this.persistence = persistence;
        this.frequency = frequency;
        this.random = new Random(seed);
    }

//    public double generateNoise(double x, double y, double z) {
//        double total = 0;
//        double amplitude = 1;
//        double maxValue = 0;
//        double currente
//    }
}
