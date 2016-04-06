package com.example.excilys.projetmessages.mappers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class JsonToMessages {
    public static ArrayList<HashMap<String, String>> convert(String json) {

        ArrayList<HashMap<String, String> > result = new ArrayList<>();

        try {

            JSONArray response = new JSONArray(json);

            for (int i = 0; i < response.length(); i++) {

                JSONObject c = response.getJSONObject(i);

                HashMap<String, String> tmp = new HashMap<>();
                tmp.put("login", c.getString("login"));
                tmp.put("message", c.getString("message"));

                result.add(tmp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
