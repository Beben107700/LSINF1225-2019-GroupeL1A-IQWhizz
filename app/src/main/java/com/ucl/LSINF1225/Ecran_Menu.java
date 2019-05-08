package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TextView;

public class Ecran_Menu extends AppCompatActivity {

    private Button themesbtn, quickbtn, completebtn;
    private ImageButton settingsbtn, resultsbtn;
    private TextView  nom_joueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran__menu);

        themesbtn = (Button)findViewById(R.id.ecranmenutopic);
        quickbtn=(Button)findViewById(R.id.ecranmenuquick);
        completebtn=(Button) findViewById(R.id.ecranmenucomplete);
        resultsbtn=(ImageButton)findViewById(R.id.ecranmenuresults);
        settingsbtn = (ImageButton)findViewById(R.id.ecranmenusettings);
        nom_joueur = (TextView)findViewById(R.id.name_player);
        nom_joueur.setText(Global.name_connect);
    }

    public void goToThemes(View view) {
        Intent intent = new Intent(this, Themes.class);
        startActivity(intent);
    }
    public void goToSettings(View view) {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
    public void goToQuick(View view) {
        DatabaseManager db = new DatabaseManager(this) ;
        int IDR = db.make_init_test(Global.mail_connect,1.0,0);
        Global.idr_actif = IDR;
        Intent intent = new Intent(this, question.class);
        startActivity(intent);
    }
    public void goToComplete(View view) {
        Intent intent = new Intent(this, question.class);
        startActivity(intent);
    }
    public void goToResults(View view) {
        //Intent intent = new Intent(this, my_results.class);
        //startActivity(intent);
    }
}
