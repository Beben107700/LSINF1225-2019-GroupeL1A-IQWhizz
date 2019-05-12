package com.ucl.LSINF1225;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class resultat extends AppCompatActivity {

    private TextView TextName, TextQI, TextResults;
    private Button delete;
    private DatabaseManager databaseManager;
    private int IDR;
    private Cursor cursor;
    private int bonnesreponses = 0;
    private int totalrep=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        TextName = (TextView)findViewById(R.id.TextName);
        TextQI = (TextView)findViewById(R.id.TextQI);
        delete = (Button)findViewById(R.id.delete);
        TextResults = (TextView)findViewById(R.id.Table_Results);

        TextName.setText(Global.name_connect);


        IDR = Global.idr_actif;
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
        cursor.close();

    }
}
