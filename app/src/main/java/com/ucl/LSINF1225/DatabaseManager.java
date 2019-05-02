package com.ucl.LSINF1225;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bdd";
    private static final int DATABASE_VERSION = 1;


    public DatabaseManager( Context context){
        super(context, DATABASE_NAME, null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table Utilisateur ("
                + "   mail text primary key not null,"
                + "   age integer not null,"
                + "   nom text not null,"
                + "   mdp text not null,"
                + "   genre text not null)";
        db.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL = "drop table Utilisateur";
        db.execSQL(strSQL);
        this.onCreate(db);
    }

    public void insert_Utilisateur(String mail, int age, String nom ,String mdp, String genre ){
        mail  =  mail.replace("'","''");
        mdp   =   mdp.replace("'","''");
        genre = genre.replace("'","''");
        nom   =   nom.replace("'","''");
        String strSQL = "insert into Utilisateur(mail,age,nom,mdp,genre) values ('"
                + mail+ "', " + age +  ",'" + nom + "','" + mdp +"','" + genre  +"')";
        this.getWritableDatabase().execSQL(strSQL);

    }
}
