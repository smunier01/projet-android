package com.example.excilys.projetmessages.tasks;

import com.example.excilys.projetmessages.mappers.InputStreamToString;
import com.example.excilys.projetmessages.mappers.JsonToMessages;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;

import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.UUID;

public class RestService {

    static public final String BASE_URL = "https://training.loicortola.com/chat-rest/2.0";

    static public void auth(final String username, final String password) {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
    }

    static public String doPost(String path, JSONObject param) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String jsonString = null;

        try {
            url = new URL(BASE_URL + path);

            StringBuilder sb = new StringBuilder();

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(param.toString());
            writer.close();

            int HttpResult = urlConnection.getResponseCode();

            System.out.println(HttpResult);
            /*
            if(HttpResult == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println("" + sb.toString());

            }else{
                System.out.println(urlConnection.getResponseMessage());
            }
            */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    static public String doGet(String path) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        String jsonString = null;

        try {
            url = new URL(BASE_URL + path);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            jsonString = InputStreamToString.convert(in);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;

    }

    static public boolean connect() {

        boolean result = false;

        try {

            String jsonString = doGet("/connect");

            JSONObject response = new JSONObject(jsonString);

            result = (response.getInt("status") == 200);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    static public ArrayList<HashMap<String, String> > getMessages(int limit, int offset) {

        ArrayList<HashMap<String, String> > result;

        String jsonString = doGet("/messages?&limit=" + limit + "&offset=" + offset);

        result = JsonToMessages.convert(jsonString);

        Collections.reverse(result);

        return result;
    }

    static public void sendMessage(String username, String message) {

        try {
            JSONObject param = new JSONObject();
            param.put("uuid", UUID.randomUUID().toString());
            param.put("login", username);
            param.put("message", message);

            doPost("/messages", param);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    static public void register(String username, String password) {
        try {

            System.out.println(username + " " + password);

            JSONObject param = new JSONObject();
            param.put("login", username);
            param.put("password", password);

            doPost("/register", param);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
