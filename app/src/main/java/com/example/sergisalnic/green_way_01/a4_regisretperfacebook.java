package com.example.sergisalnic.green_way_01;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class a4_regisretperfacebook extends AsyncTask<String, Void, String> {
    public String aux="";
    String token ="";
    protected String doInBackground(String... urls) {
        try {
            JSONObject json = new JSONObject();
            try {
                json.put("ID",urls[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                json.put("Token",urls[2]);
                token=urls[2];
            } catch (JSONException e) {
                e.printStackTrace();
            }
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Content-Type","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new
                    OutputStreamWriter(os, "UTF-8"));
            writer.write(json.toString());
            System.out.println(json.toString());
            writer.close();
            os.close();
            conn.connect();
            //int response = conn.getResponseCode();
            InputStream is = conn.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            aux= sBuilder.toString();
            //aux = convertStreamToString(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    protected void onPostExecute(String urls) {
        if (!aux.equals("")) {
            try {
                JSONObject json = new JSONObject(aux);
                System.out.println(json);
                String tablename="mi_bd2";
                a5_bdadmin bd = new a5_bdadmin(null, tablename, null, 1);
               int echo = bd.insert(token);
                System.out.println(echo);
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
    }
}
