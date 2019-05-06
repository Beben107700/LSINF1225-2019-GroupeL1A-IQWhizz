package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class Ecran_Menu extends AppCompatActivity {

    private Button themesbtn, quickbtn, completebtn;
    private ImageButton settingsbtn, resultsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran__menu);

        themesbtn = (Button)findViewById(R.id.ecranmenutopic);
        quickbtn=(Button)findViewById(R.id.ecranmenuquick);
        completebtn=(Button) findViewById(R.id.ecranmenucomplete);
        resultsbtn=(ImageButton)findViewById(R.id.ecranmenuresults);
        settingsbtn = (ImageButton)findViewById(R.id.ecranmenusettings);
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
