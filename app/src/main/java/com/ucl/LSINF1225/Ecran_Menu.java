package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Ecran_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran__menu);
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
        Intent intent = new Intent(this, Quick_test.class);
        startActivity(intent);
    }
    public void goToComplete(View view) {
        Intent intent = new Intent(this, Complete_test.class);
        startActivity(intent);
    }
    public void goToResults(View view) {
        Intent intent = new Intent(this, my_results.class);
        startActivity(intent);
    }
}
