package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        public void goToLog_in (View view){
            Intent intent = new Intent (this, log_in.class);
            startActivity(intent);
        }

        public void goToSign_in (View view){
            Intent intent = new Intent (this, sign_in.class);
            startActivity(intent);
        }

    }


}
