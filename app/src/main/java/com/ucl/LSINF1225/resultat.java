package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class resultat extends AppCompatActivity {

    private TextView TextName, TextQI;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        TextName = (TextView)findViewById(R.id.TextName);
        TextQI = (TextView)findViewById(R.id.TextQI);
        delete = (Button)findViewById(R.id.delete);

        // TextName.setText("Nom d'utilisateur BDD");

    }
}
