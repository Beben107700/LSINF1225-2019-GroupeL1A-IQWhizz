package com.ucl.LSINF1225;

import android.content.Context;

public class Recap {

    public static int init_test(Context context, String mail, double IDT, int nduel){
        DatabaseManager databaseManager = new DatabaseManager(context);
        int i =databaseManager.make_init_test(mail, IDT, nduel);
        databaseManager.close();
        return i;
    }
}
