package com.ucl.LSINF1225;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class question extends AppCompatActivity {

    private TextView Timer_text;
    private TextView Texte_question;
    private CountDownTimer CountdownTimer;
    private long Time_left_milli = 180000; //3min
    private boolean isfinished;

    private Button p1;
    private Button p2;
    private Button p3;
    private Button p4;
    private DatabaseManager databaseManager;
    boolean done = false;
    int IDR;
    double IDQ;
    double[] tab;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Timer_text = findViewById(R.id.the_timer);
        start_countdown();
        final Context cont = this;

        Texte_question = (TextView)findViewById(R.id.titre_question);
        p1 = (Button)findViewById(R.id.pos1);
        p2 = (Button)findViewById(R.id.pos2);
        p3 = (Button)findViewById(R.id.pos3);
        p4 = (Button)findViewById(R.id.pos4);

        IDR = Global.idr_actif;
        databaseManager = new DatabaseManager(this);
        SQLiteDatabase db = databaseManager.getReadableDatabase();
        cursor = db.rawQuery("Select IDQ From Reponses Where IDR = " + IDR,null);
        cursor.moveToFirst();

        IDQ = cursor.getDouble(0);
        Texte_question.setText(Quest.get_texte(this,IDQ));
        tab =Quest.get_choix_idc(this,IDQ);
        p1.setText(Choix.get_texte(this,tab[0]));
        p2.setText(Choix.get_texte(this,tab[1]));
        p3.setText(Choix.get_texte(this,tab[2]));
        p4.setText(Choix.get_texte(this,tab[3]));
        p1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Reponse.set(cont,IDR,IDQ,tab[0]);
                if(cursor.moveToNext()){
                    next_quest(cursor);
                }
                else{
                    startResult();
                }
            }
        });
        p2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Reponse.set(cont,IDR,IDQ,tab[1]);
                if(cursor.moveToNext()){
                    next_quest(cursor);
                }
                else{startResult();}
            }
        });
        p3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Reponse.set(cont,IDR,IDQ,tab[2]);
                if(cursor.moveToNext()){
                    next_quest(cursor);
                }
                else{startResult();}
            }
        });
        p4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Reponse.set(cont,IDR,IDQ,tab[3]);
                if(cursor.moveToNext()){
                    next_quest(cursor);
                }
                else{startResult();}
            }
        });

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


    private void next_quest(Cursor cursor){
        IDQ = cursor.getDouble(0);
        Texte_question.setText(Quest.get_texte(this,IDQ));
        tab =Quest.get_choix_idc(this,IDQ);
        done = false;
        p1.setText(Choix.get_texte(this,tab[0]));
        p2.setText(Choix.get_texte(this,tab[1]));
        p3.setText(Choix.get_texte(this,tab[2]));
        p4.setText(Choix.get_texte(this,tab[3]));
    }

    private void startResult(){
        Intent intent = new Intent(getApplicationContext(), resultat.class);
        startActivity(intent);
    }
}