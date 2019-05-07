package com.ucl.LSINF1225;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.TextView;

public class question extends AppCompatActivity {

    private TextView Timer_text;
    private CountDownTimer CountdownTimer;
    private long Time_left_milli = 180000; //3min
    private boolean isfinished;

    private TextView question_text;
    private Button C1;
    private Button C2;
    private Button C3;
    private Button C4;
    private DatabaseManager db_manager;
    private String q_string;
    private String[] C_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Timer_text = findViewById(R.id.the_timer);
        start_countdown();
        db_manager = new DatabaseManager(this);
        q_string = db_manager.getQuestion()[0];
        C_string = db_manager.getChoix(db_manager.getQuestion()[1]);

        question_text = findViewById(R.id.titre_question);
        C1 = findViewById(R.id.button_choix1);
        C2 = findViewById(R.id.button_choix2);
        C3 = findViewById(R.id.button_choix3);
        C4 = findViewById(R.id.button_choix4);
        question_text.setText(q_string);
        C1.setText(C_string[0]);
        C2.setText(C_string[1]);
        C3.setText(C_string[2]);
        C4.setText(C_string[3]);




    }
    // Inspiré de https://www.youtube.com/watch?v=zmjfAcnosS0
    private void start_countdown(){
        CountdownTimer = new CountDownTimer(Time_left_milli, 1000) {
            @Override
            public void onTick(long l) {
                Time_left_milli = l;
                update_timer();
            }

            @Override
            public void onFinish() {
                Timer_text.setText(getString(R.string.Done));
            }
        }.start();


    }
    private void update_timer(){

        int Minutes = (int) Time_left_milli / 60000;
        int Secondes = (int) Time_left_milli % 60000 / 1000;
        String Timelefttext;

        Timelefttext = "" + Minutes;
        Timelefttext += ":";
        if (Secondes<10)Timelefttext += "0";
        Timelefttext += Secondes;

        Timer_text.setText(Timelefttext);
    }

}
