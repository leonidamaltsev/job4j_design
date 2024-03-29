package ru.job4j.serialization.json;

public class RocketEngine {
    private final String fuel;

    public RocketEngine(String fuel) {
        this.fuel = fuel;
    }

    public String getFuel() {
        return fuel;
    }

    @Override
    public String toString() {
        return "RocketEngine{"
                + "fuel='" + fuel + '\''
                + '}';
    }
}
