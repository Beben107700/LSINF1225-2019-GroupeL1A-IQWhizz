package com.ucl.LSINF1225;

import android.content.Context;

public class Choix {

    public static String get_texte(Context contexte,double IDC){
        DatabaseManager databaseManager = new DatabaseManager(contexte);
        String s = databaseManager.get_choix_texte(IDC);
        databaseManager.close();
        return s;
    }
}
