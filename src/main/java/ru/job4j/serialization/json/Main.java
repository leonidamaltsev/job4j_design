package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JSONObject jsonFuel = new JSONObject("{\"fuel\":\"hydrogen\"}");
        List<String> list = new ArrayList<>();
        list.add("Saturn");
        list.add("silver");
        JSONArray jsonStatuses = new JSONArray(list);

        final Engine engine = new Engine(false, 30, new RocketEngine("hydrogen"),
                new String[] {"Saturn", "silver"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enginetype", engine.isEnginetype());
        jsonObject.put("rocketEngine", engine.getRocketEngine());
        jsonObject.put("trust", 30);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(engine).toString());
    }
}
