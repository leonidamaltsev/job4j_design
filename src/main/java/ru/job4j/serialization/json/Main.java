package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Engine engine = new Engine(false, 30, new RocketEngine("hydrogen"),
                new String[] {"Saturn", "silver"});

        final Gson gson = new GsonBuilder().create();
        String gsonString = gson.toJson(engine);
        System.out.println(gsonString);


        final Engine engineMod = gson.fromJson(gsonString, Engine.class);
        System.out.println(engineMod);
    }
}
