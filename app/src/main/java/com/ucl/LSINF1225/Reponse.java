package com.ucl.LSINF1225;

import android.app.admin.DeviceAdminInfo;
import android.content.Context;
import android.provider.ContactsContract;

public class Reponse {

    public static void set(Context context, int IDR, double IDQ, double IDC){
        DatabaseManager databaseManager = new DatabaseManager(context);
        databaseManager.set_reponse(IDR, IDQ, IDC);
        databaseManager.close();
    }

}
