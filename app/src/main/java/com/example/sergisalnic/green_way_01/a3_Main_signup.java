package com.example.sergisalnic.green_way_01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class a3_Main_signup extends AppCompatActivity {

    Button enviar;
    TextView txt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_activity_signup);
        TraerReferencias();
        msToast("No tienes cuenta? Crea una Aqui!");
    }
    public void go_login(View view){
        Intent intent = new Intent(a3_Main_signup.this, a2_Main_login.class);
        startActivity(intent);
    }
    public void TraerReferencias() {
        enviar =  (Button) findViewById(R.id.btnLogin);
        txt_login = (TextView) findViewById(R.id.link_to_login);
    }
    public void msToast(String text) { //método para mostrar mensajes
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
