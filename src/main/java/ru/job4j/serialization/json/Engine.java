package ru.job4j.serialization.json;

import java.util.Arrays;

public class Engine {
    private final boolean enginetype;
    private final int thrust;
    private final RocketEngine rocketEngine;
    private final String[] statuses;

    public Engine(boolean enginetype, int thrust, RocketEngine rocketEngine, String[] statuses) {
        this.enginetype = enginetype;
        this.thrust = thrust;
        this.rocketEngine = rocketEngine;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "enginetype=" + enginetype
                + ", thrust=" + thrust
                + ", rocketEngine=" + rocketEngine
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}

