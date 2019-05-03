package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class log_in extends AppCompatActivity {

    private ImageButton log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.log_in = (ImageButton) findViewById(R.id.log_in);

    }

    public void goToEM(View v){
        //Fct qui s'éxécute qd tu pousses sur login
        Intent intent = new Intent(getApplicationContext(), Ecran_Menu.class);
        startActivity(intent);
    }
}
