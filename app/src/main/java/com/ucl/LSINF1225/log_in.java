package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        public void goToMenu (View view){
            Intent intent = new Intent (this, menu.class);
            startActivity(intent);
        }

    }
}