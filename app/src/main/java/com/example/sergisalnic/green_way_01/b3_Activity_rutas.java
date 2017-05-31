package com.example.sergisalnic.green_way_01;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class b3_Activity_rutas extends AppCompatActivity {
    public static JSONArray JRUTES;
    LinearLayout lineal_contenedor;
    public Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b3_activity_rutas);
        mContext=this;
        TraerReferencias();
        getallrutes();
        TextView tv_titulo = new TextView(this);
    }
    public  void getallrutes(){
        GetRutes geet= new GetRutes();
        String url = "http://172.17.129.113:8080/users_manager/v1/rutes";
        geet.execute(url);
    }

    public  void recojerrutas() throws JSONException {
        int lenght = JRUTES.length();
        int i=0;

        while(i<lenght){
            JSONObject json = JRUTES.getJSONObject(i);
            int id = json.getInt("id");
            String nombre = json.getString("nombre");
            String descripcion = json.getString("description");
            //lineal_contenedor.addView(et);
            puttextinlayout(nombre,id,descripcion);
            i++;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private  void puttextinlayout(String name, int id, String texto){



        /*
        A0_Datos_ruta.nombre = name;
        A0_Datos_ruta.descripcion = texto;
        A0_Datos_ruta.id = id;
        */

        //List listApp = getPackageManager().getInstalledApplications(0);

        //<editor-fold desc="Quim stuff">
        LinearLayout llayout = new LinearLayout(this);
        TextView tv_titulo = new TextView(this);
        TextView tv_descrip = new TextView(this);
       Button btn = new Button(this);

        llayout.addView(tv_titulo);
        llayout.addView(btn);
        llayout.addView(tv_descrip);

        tv_titulo.setText(name);
        btn.setText("go to " + name);
        tv_descrip.setText(texto);
        //</editor-fold>

            /*
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(pk.packageName);
                    startActivity(LaunchIntent);
                }
            );

*/          lineal_contenedor.addView(llayout);

    }
    public void TraerReferencias() {

        lineal_contenedor=(LinearLayout) findViewById(R.id.contenedor_rutas);
    }
    class GetRutes extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String data = "";
            InputStream istream = null;
            HttpURLConnection urlConnection = null;
            try {

                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                istream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(istream));
                StringBuffer sb = new StringBuffer();
                String line ="";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                data = sb.toString();
                br.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray json = new JSONArray(s);
                b3_Activity_rutas.JRUTES=json;
                recojerrutas();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

