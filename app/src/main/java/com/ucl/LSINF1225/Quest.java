package com.ucl.LSINF1225;

import android.content.Context;

public class Quest {
    public static String[] getChoix(Context context, String IDQ){
        DatabaseManager databaseManager = new DatabaseManager(context);
        String[] str = databaseManager.getChoix(IDQ);
        databaseManager.close();
        return str;
    }

    public static String get_texte(Context context,double IDQ){
        DatabaseManager databaseManager = new DatabaseManager(context);
        String str = databaseManager.get_question_texte(IDQ);
        databaseManager.close();
        return str;
    }

    public static double[] get_choix_idc(Context context,double IDQ){
        DatabaseManager databaseManager = new DatabaseManager(context);
        double[] doubles = databaseManager.get_choix_idc(IDQ);
        databaseManager.close();
        return doubles;
    }
}
