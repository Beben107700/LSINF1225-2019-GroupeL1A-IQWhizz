package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class settings extends AppCompatActivity {

    private ImageButton deleteButton;
    EditText name, email, password;
    RadioButton male, female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Associate vars
        name = (EditText)findViewById(R.id.editText);
        email = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText3);

        male = (RadioButton)findViewById(R.id.radioButton);
        female = (RadioButton)findViewById(R.id.radioButton2);

        deleteButton = (ImageButton)findViewById(R.id.delete);

        //ICI FAUT PRENDRE LA DATABASE
        String nom = "Ben";
        String Email = "ben.delcoigne@gmail.com";
        String password = "AHAHAH";





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void buttonPress(View view){
        String outname = name.getText().toString();
        String outemail = email.getText().toString();
        String outpassword = password.getText().toString();

        //FAUT ENVOYER A LA BDD
    }

    public void pressDelete(View v ){
        //Fct qui s'éxécute quand tu pousses sur "delete profile"
    }
}
