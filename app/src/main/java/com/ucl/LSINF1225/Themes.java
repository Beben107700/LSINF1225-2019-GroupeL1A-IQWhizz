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
        ButtonMath = (RadioButton)findViewById(R.id.ButtonMath);
        ButtonBio = (RadioButton)findViewById(R.id.ButtonBio);
        ButtonPhys = (RadioButton)findViewById(R.id.ButtonPhys);
        ButtonChe = (RadioButton)findViewById(R.id.ButtonChe);
        ButtonLog = (RadioButton)findViewById(R.id.ButtonLog);
        ButtonCul = (RadioButton)findViewById(R.id.ButtonCul);
        ButtonHis = (RadioButton)findViewById(R.id.ButtonHist);
        ButtonOth = (RadioButton)findViewById(R.id.ButtonOth);


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
    public void GoToTest (View view){
        // Intent intent = new Intent(this, Test.class);
        // startActivity(intent);
    }
}
