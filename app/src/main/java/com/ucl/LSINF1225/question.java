package com.ucl.LSINF1225;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class question extends AppCompatActivity {

    private TextView Timer_text;
    private CountDownTimer CountdownTimer;
    private long Time_left_milli = 180000; //3min
    private boolean isfinished;

    private TextView question_text;
    private DatabaseManager db_manager;
    private String q_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Timer_text = findViewById(R.id.the_timer);
        start_countdown();
        db_manager = new DatabaseManager(this);
        q_string = db_manager.getQuestion();
        question_text = findViewById(R.id.titre_question);
        question_text.setText(q_string);

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
