package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class sing_in extends AppCompatActivity {

    private DatabaseManager databaseManager;

    private EditText mail_e;
    private EditText age_e;
    private EditText nom_e;
    private EditText mdp_e;
    private RadioGroup genre_e;
    private RadioButton selected;

    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        this.create = (Button)findViewById(R.id.create);

        mail_e= (EditText)findViewById(R.id.mail_t);
        nom_e = (EditText)findViewById(R.id.firstname_t);
        mdp_e = (EditText)findViewById(R.id.mdp_t);
        age_e = (EditText)findViewById(R.id.age_t);
        genre_e = (RadioGroup)findViewById(R.id.gender_t);

        databaseManager = new DatabaseManager(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = (RadioButton) findViewById(genre_e.getCheckedRadioButtonId());

                databaseManager.insert_Utilisateur(mail_e.getText().toString(),
                        Integer.valueOf(age_e.getText().toString()),
                        nom_e.getText().toString(),
                        mdp_e.getText().toString(),
                        selected.getText().toString());
                databaseManager.close();

                Intent act = new Intent(getApplicationContext(),log_in.class);
                startActivity(act);


            }
        });


    }

    public void goToEM(View v){
        //Fct qui s'éxécute qd tu pousses sur login
        Intent intent = new Intent(getApplicationContext(), Ecran_Menu.class);
        startActivity(intent);
    }


}
