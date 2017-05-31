package com.example.sergisalnic.green_way_01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class a2_Main_login extends AppCompatActivity {

    Button enviar;
    TextView txt_signin;
    EditText text_email,text_pass;
    public boolean mesage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_activity_login);
        TraerReferencias();
        msToast("Ya tienes cuenta? Accede a GreenWay!");
    }
    public void msToast(String text) { //método para mostrar mensajes
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void go_signup(View view){
        Intent intent = new Intent(a2_Main_login.this, a3_Main_signup.class);
        startActivity(intent);
    }
    public void go_menu(View view){
        Intent intent = new Intent(a2_Main_login.this, A0_MainActivity_menu.class);
        startActivity(intent);
    }
    public void TraerReferencias() {
        enviar =  (Button) findViewById(R.id.btnLogin);
        txt_signin = (TextView) findViewById(R.id.link_to_register);
        text_email= (EditText) findViewById(R.id.txtEmail);
        text_pass=(EditText) findViewById(R.id.txtPass);
    }
    public void login(View view){
        String name = text_email.getText().toString();
        String pass = text_pass.getText().toString();
        String url = "http://172.17.129.113:8080/users_manager/v1/login";
        new RetrieveFeedTask().execute(url,name,pass);
        System.out.println(mesage+" este es el mensaje");
    }
    public void issucces(Boolean checker){
        if(checker){
            Intent intent = new Intent(a2_Main_login.this,A0_MainActivity_menu.class);
            startActivity(intent);
        }else{

        }
    }
    class RetrieveFeedTask extends AsyncTask<String, Void, String> {
        public  String aux = "";
        public JSONObject json = new JSONObject();
        protected String doInBackground(String... urls) {
            try {
                json.put("name",urls[1]);
                json.put("password",urls[2]);
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "success";
        }

        protected void onPostExecute(String urls) {
            if (!aux.equals("")) {
                try {
                    JSONObject json = new JSONObject(aux);
                    System.out.println(json);
                    getallthecvaluesfromjson(json);
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }

        }
        private void getallthecvaluesfromjson(JSONObject json){
            try {
                mesage = json.getBoolean("respuesta");
                issucces(mesage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
}