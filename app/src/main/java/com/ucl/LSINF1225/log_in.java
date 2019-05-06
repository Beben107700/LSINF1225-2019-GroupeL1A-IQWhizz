package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class log_in extends AppCompatActivity {

    private ImageButton log_in;
    private EditText mail_e;
    private EditText mdp_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.log_in = (ImageButton)findViewById(R.id.log_in);

        mail_e = (EditText)findViewById(R.id.editEmail);
        mdp_e = (EditText)findViewById(R.id.editPassword);


    }

    public void goToEM(View v){
        //Fct qui s'éxécute qd tu pousses sur login

//        String mail = mail_e.getText().toString();
//        String mdp = mdp_e.getText().toString();

        Intent intent = new Intent(getApplicationContext(), Ecran_Menu.class);
        startActivity(intent);
    }
}
