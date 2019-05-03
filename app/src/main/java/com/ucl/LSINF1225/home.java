package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    private ImageButton loginbutton, signupbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loginbutton = (ImageButton)findViewById(R.id.homeloginbutton);
        signupbutton= (ImageButton)findViewById(R.id.homesignupbutton);

    }
    public void goToSignIn(View v ){
        //Fct qui s'éxécute quand tu pousses sur signup
        Intent intent = new Intent(getApplicationContext(), sing_in.class);
        startActivity(intent);

    }
    public void goToLogIn(View v){
        //Fct qui s'éxécute qd tu pousses sur login
        Intent intent = new Intent(getApplicationContext(), log_in.class);
        startActivity(intent);
    }

}
