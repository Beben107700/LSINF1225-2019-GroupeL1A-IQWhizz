package com.ucl.LSINF1225;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MyResults extends AppCompatActivity {
    private TextView Text;
    private int IDR;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_results);

        Text = (TextView)findViewById(R.id.textView3);


        IDR = Global.idr_actif;
        databaseManager = new DatabaseManager(this);
        SQLiteDatabase db = databaseManager.getReadableDatabase();
        //Cursor Recaps = db.rawQuery("Select IDR From Recap Where Mail = " + Global.mail_connect,null);
        Cursor Recaps = db.rawQuery("Select IDR, mail From Recap",null);
        Recaps.moveToFirst();

        do{
            int IDR = Recaps.getInt(0);
            int bonnesreponses=0;
            int totalrep=0;
            Cursor Questions = db.rawQuery("Select IDQ, IDC From Reponses Where IDR = " + IDR,null);
            Questions.moveToFirst();
            do{
                double IDQuestion = Questions.getDouble(0);
                double IDC = Questions.getDouble(1);
                Cursor etat = db.rawQuery("Select etat From Choix Where IDC = " + IDC,null);
                etat.moveToFirst();
                String state = etat.getString(0);
                etat.close();
                if(state.equals("vrai")){bonnesreponses++;}
                totalrep ++;
                //Printing
            }while(Questions.moveToNext());
            Text.setText(Text.getText() + "\n Test numéro: " + IDR + " Par " +Recaps.getString(1) + "\n           Resultats: "+ bonnesreponses + "/" +totalrep);
        }while(Recaps.moveToNext());

        /*IDR = Global.idr_actif;
        databaseManager = new DatabaseManager(this);
        SQLiteDatabase db = databaseManager.getReadableDatabase();
        cursor = db.rawQuery("Select IDQ, IDC From Reponses Where IDR = " + IDR,null);
        cursor.moveToFirst();
        int i = 0;
        do{
            i++;
            double IDQuestion = cursor.getDouble(0);
            double IDC = cursor.getDouble(1);
            Cursor etat = db.rawQuery("Select etat From Choix Where IDC = " + IDC,null);
            etat.moveToFirst();
            String state = etat.getString(0);
            etat.close();
            if(state.equals("vrai")){bonnesreponses++;}
            totalrep ++;
            //Printing
            TextResults.setText(TextResults.getText() + "Question numéro: "+i + " - "+state +"\n");

        }while(cursor.moveToNext());
        TextQI.setText(bonnesreponses + "/" +totalrep);
        cursor.close();*/
    }
}
