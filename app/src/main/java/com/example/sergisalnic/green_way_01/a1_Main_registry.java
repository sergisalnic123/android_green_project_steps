package com.example.sergisalnic.green_way_01;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/*
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
*/


public class a1_Main_registry extends AppCompatActivity {

    ImageButton but_face, but_googl;
    Button but_sign, but_log;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comprovaruser();
        setContentView(R.layout.a1_activity_registry);
        TraerReferencias();
        //  FacebookSdk.sdkInitialize(getApplicationContext());
        msToast("Registrate para a GreenWay");

    }

    public void go_menu(View view) {
        Intent intent = new Intent(a1_Main_registry.this, A0_MainActivity_menu.class);
        startActivity(intent);
    }

    public void go_login(View view) {
        Intent intent = new Intent(a1_Main_registry.this, a2_Main_login.class);
        startActivity(intent);
    }

    public void go_signup(View view) {
        Intent intent = new Intent(a1_Main_registry.this, a3_Main_signup.class);
        startActivity(intent);
    }

    public void TraerReferencias() {
        but_face = (ImageButton) findViewById(R.id.imageButton);
        but_googl = (ImageButton) findViewById(R.id.imageButton2);
        but_log = (Button) findViewById(R.id.button);
        but_sign = (Button) findViewById(R.id.button2);
    }
    public void msToast(String text) { //método para mostrar mensajes
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
    /*

    private CallbackManager callbackManager;
    List<String> permissionNeeds= Arrays.asList( "email");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comprovaruser();
        setContentView(R.layout.activity_registry);
        TraerReferencias();
        FacebookSdk.sdkInitialize(getApplicationContext());

    }
    private void comprovaruser(){
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                permissionNeeds);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(Main_registry.this,MainActivity_menu.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        //LoginManager.getInstance().logInWithPublishPermissions(this,Arrays.asList("public_profile"));
        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    public void loginfacebook(View view){
        Fblogin();
    }
    private void Fblogin() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                permissionNeeds);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>(){
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken= loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        String URL="http://172.17.129.113:8080/users_manager/v1/resface";
                        String id = profile.getId();
                        String token = accessToken.getToken();
                        new regisretperfacebook().execute(URL,id,token);
                        Intent intent = new Intent(Main_registry.this,MainActivity_menu.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

*/
