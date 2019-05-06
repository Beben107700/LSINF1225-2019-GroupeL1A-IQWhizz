package com.ucl.LSINF1225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Themes extends AppCompatActivity {

    private Button StartButton;
    private RadioGroup ButtonGroup;
    private RadioButton ButtonMath, ButtonBio, ButtonPhys, ButtonChe, ButtonLog, ButtonCul, ButtonHis, ButtonOth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StartButton = (Button)findViewById(R.id.StartButton);
        ButtonGroup = (RadioGroup)findViewById(R.id.ButtonGroup);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
    }
    public void buttonclick (View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.ButtonMath:
                if (checked)
                    break;
            case R.id.ButtonBio:
                if (checked)
                    break;
            case R.id.ButtonPhys:
                if (checked)
                    break;
            case R.id.ButtonChe:
                if (checked)
                    break;
            case R.id.ButtonLog:
                if (checked)
                    break;
            case R.id.ButtonCul:
                if (checked)
                    break;
            case R.id.ButtonHist:
                if (checked)
                    break;
            case R.id.ButtonOth:
                if (checked)
                    break;
        }

    }
}
