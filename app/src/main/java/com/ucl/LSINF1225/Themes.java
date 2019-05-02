package com.ucl.LSINF1225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Themes extends AppCompatActivity {

    private Button StartButton;
    private RadioGroup ButtonGroup;
    private RadioButton ButtonMath, ButtonBio, ButtonPhys, ButtonChe, ButtonLog, ButtonCul, ButtonHis, ButtonOth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
    }
}
