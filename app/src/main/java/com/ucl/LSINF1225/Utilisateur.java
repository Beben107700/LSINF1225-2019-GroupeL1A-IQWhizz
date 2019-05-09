package com.ucl.LSINF1225;

import android.content.Context;

public class Utilisateur{
    public static String get_password(Context context, String mail){
        DatabaseManager databaseManager = new DatabaseManager(context);
        String pass = databaseManager.get_Password(mail);
        databaseManager.close();
        return pass;
    }

    public static String get_name(Context context, String mail){
        DatabaseManager databaseManager = new DatabaseManager(context);
        String nom = databaseManager.get_Name(mail);
        databaseManager.close();
        return nom;
    }

    public static void insert(Context context, String mail, int age, String nom ,String mdp, String genre ){
        DatabaseManager databaseManager = new DatabaseManager(context);
        databaseManager.insert_Utilisateur(mail,age,nom,mdp,genre);
        databaseManager.close();
    }

}
