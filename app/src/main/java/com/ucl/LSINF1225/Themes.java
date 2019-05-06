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
    private RadioButton selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        this.StartButton = (Button)findViewById(R.id.StartButton);
        ButtonGroup = (RadioGroup)findViewById(R.id.ButtonGroup);
        ButtonMath = (RadioButton)findViewById(R.id.ButtonMath);
        ButtonBio = (RadioButton)findViewById(R.id.ButtonBio);
        ButtonPhys = (RadioButton)findViewById(R.id.ButtonPhys);
        ButtonChe = (RadioButton)findViewById(R.id.ButtonChe);
        ButtonLog = (RadioButton)findViewById(R.id.ButtonLog);
        ButtonCul = (RadioButton)findViewById(R.id.ButtonCul);
        ButtonHis = (RadioButton)findViewById(R.id.ButtonHist);
        ButtonOth = (RadioButton)findViewById(R.id.ButtonOth);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = (RadioButton)findViewById(ButtonGroup.getCheckedRadioButtonId());
                // Utiliser la BDD pour lancer le bon test par rapport au RadioButton qui est coché
                // Intent intent = new Intent(getApplicationContext(), Test.class);
                // startActivity(intent);

            }
        });

    }
}
