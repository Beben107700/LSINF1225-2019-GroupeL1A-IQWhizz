package com.ucl.LSINF1225;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bdd";
    private static final int DATABASE_VERSION = 22;


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
                + "   genre text)";
        db.execSQL(strSql);

        String strSql2 = "create table Test ("
                + "   IDT double primary key not null,"
                + "   type text not null,"
                + "   theme text not null,"
                + "   nquestion integer not null)";
        db.execSQL(strSql2);

        String strsql5 = "create table Question ("
                + "   IDQ double primary key not null,"
                + "   texte text not null,"
                + "   image blob,"
                + "   theme text not null)";
        db.execSQL(strsql5);

        String strSql1 = "create table Recap ("
                + "   IDR integer primary key autoincrement,"
                + "   mail text not null references Utilisateur(mail),"
                + "   IDT double not null references Test(IDT),"
                + "   nduel integer )";
        db.execSQL(strSql1);

        String strSql4 = "create table Choix ("
                + "   IDC double primary key not null,"
                + "   IDQ double not null references Question(IDQ),"
                + "   etat text not null,"
                + "   possibilite text not null)";
        db.execSQL(strSql4);

        String strSql3 = "create table Reponses ("
                + "   IDR integer not null references Recap(IDR),"
                + "   tempsr text,"
                + "   IDC double references Choix(IDC),"
                + "   IDQ double not null references Question(IDQ),"
                + "   UNIQUE(IDR,IDQ))";
        db.execSQL(strSql3);

        this.init_Question(db);
        this.init_Choix(db);
        this.init_Test(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL = "drop table Reponses";
        db.execSQL(strSQL);

        String strSQL1 = "drop table Recap";
        db.execSQL(strSQL1);

        String strSQL2 = "drop table Choix";
        db.execSQL(strSQL2);

        String strSQL3 = "drop table Test";
        db.execSQL(strSQL3);

        String strSQL4 = "drop table Question";
        db.execSQL(strSQL4);

        String strSQL5 = "drop table Utilisateur";
        db.execSQL(strSQL5);

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

    public String get_Password(String mail){
        String[] arg = new String[]{mail};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select mdp From Utilisateur Where mail = ?", arg);
        cursor.moveToFirst();
        String pass = cursor.getString(0);
        cursor.close();
        return pass;
    }

    public String get_Name(String mail){
        String[] arg = new String[]{mail};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select nom From Utilisateur Where mail = ?", arg);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        cursor.close();
        return name;
    }

    public String[] getQuestion(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select texte From Question", null);
        cursor.moveToFirst();
        String q = cursor.getString(0);
        cursor = db.rawQuery("Select IDQ From Question", null);
        cursor.moveToFirst();
        String id = cursor.getString(0);
        cursor.close();
        return new String[]{q, id};
    }

    public String[] getChoix(String IDQ){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ID = new String[]{IDQ};
        Cursor cursor = db.rawQuery("Select possibilite From Choix where IDQ = ?", ID);
        cursor.moveToFirst();
        String[] c = new String[4];
        for (int i=0; i<4; i++){
            c[i] = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return c;
    }

    public int make_recap(String mail, double IDT, int nduel){
        mail  =  mail.replace("'","''");
        String strSQL = "insert into Recap(mail,IDT,nduel) values( '" + mail + "'," + IDT + "," + nduel + ")";
        this.getWritableDatabase().execSQL(strSQL);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select MAX(IDR) From Recap ",null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        return i;
    }

    public void make_init_question(int IDR, double IDQ){
        String strSql = "insert into Reponses(IDR, tempsr, IDC, IDQ) values ("+ IDR +",null, null, "+ IDQ +")";
        this.getWritableDatabase().execSQL(strSql);
    }

    public int make_init_test(String mail, double IDT, int nduel){
        int IDR = this.make_recap(mail, IDT, nduel);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select nquestion From Test Where IDT = "+ IDT ,null );
        cursor.moveToFirst();
        int n = cursor.getInt(0);
        cursor = db.rawQuery("Select IDQ from Question Order by random() Limit "+ n +"",null);
        cursor.moveToFirst();
        String strsql;
        do{
            double IDQ = cursor.getDouble(0);
            make_init_question(IDR,IDQ);
        }while(cursor.moveToNext());
        cursor.close();
        return IDR;
    }

    public String get_question_texte(double IDQ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select texte From Question Where IDQ = "+ IDQ +"", null);
        cursor.moveToFirst();
        String texte = cursor.getString(0);
        cursor.close();
        return texte;
    }

    public double[] get_choix_idc(double IDQ){
        double[] tableau = new double[4];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select IDC from Choix where IDQ = " + IDQ,null);
        cursor.moveToFirst();
        for(int i = 0; i < 4; i++){
            tableau[i]= cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return tableau ;
    }

    public String get_choix_texte(double IDC){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select possibilite From Choix Where IDC = "+ IDC , null);
        cursor.moveToFirst();
        String texte = cursor.getString(0);
        cursor.close();
        return texte;
    }

    public void set_reponse(int IDR, double IDQ, double IDC){
        String strsql = "Update Reponses set IDC = "+ IDC +" where IDR = "+IDR+" AND IDQ = " + IDQ ;
        this.getWritableDatabase().execSQL(strsql);
    }


    public void init_Question(SQLiteDatabase db){
        String strsql = "INSERT INTO Question (\n" +
                "                         theme,\n" +
                "                         image,\n" +
                "                         texte,\n" +
                "                         IDQ\n" +
                "                     )\n" +
                "                     VALUES (\n" +
                "                         'Mathematics',\n" +
                "                         NULL,\n" +
                "                         '1+6',\n" +
                "                         1\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Mathematics',\n" +
                "                         NULL,\n" +
                "                         '11*3',\n" +
                "                         2\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Mathematics',\n" +
                "                         NULL,\n" +
                "                         '7*2',\n" +
                "                         3\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Mathematics',\n" +
                "                         NULL,\n" +
                "                         '16/4',\n" +
                "                         4\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Mathematics',\n" +
                "                         NULL,\n" +
                "                         '14-3',\n" +
                "                         5\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Logic',\n" +
                "                         NULL,\n" +
                "                         'Couleur du ciel',\n" +
                "                         6\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Logic',\n" +
                "                         NULL,\n" +
                "                         'Jour apres hier',\n" +
                "                         7\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Logic',\n" +
                "                         NULL,\n" +
                "                         'l eau bout a',\n" +
                "                         8\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Logic',\n" +
                "                         NULL,\n" +
                "                         'les pates sont faites avec',\n" +
                "                         9\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Logic',\n" +
                "                         NULL,\n" +
                "                         'Element des nuages',\n" +
                "                         10\n" +
                "                     );";
        db.execSQL(strsql);
    }

    public void init_Choix(SQLiteDatabase db){
        String strsql = "INSERT INTO Choix (\n" +
                "                      possibilite,\n" +
                "                      etat,\n" +
                "                      IDQ,\n" +
                "                      IDC\n" +
                "                  )\n" +
                "                  VALUES (\n" +
                "                      2,\n" +
                "                      'faux',\n" +
                "                      1,\n" +
                "                      1.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      9,\n" +
                "                      'faux',\n" +
                "                      1,\n" +
                "                      1.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      7,\n" +
                "                      'vrai',\n" +
                "                      1,\n" +
                "                      1.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      8,\n" +
                "                      'faux',\n" +
                "                      1,\n" +
                "                      1.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      313,\n" +
                "                      'faux',\n" +
                "                      2,\n" +
                "                      2.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      33,\n" +
                "                      'vrai',\n" +
                "                      2,\n" +
                "                      2.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      3,\n" +
                "                      'faux',\n" +
                "                      2,\n" +
                "                      2.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      133,\n" +
                "                      'faux',\n" +
                "                      2,\n" +
                "                      2.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      14,\n" +
                "                      'vrai',\n" +
                "                      3,\n" +
                "                      3.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      17,\n" +
                "                      'faux',\n" +
                "                      3,\n" +
                "                      3.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      45,\n" +
                "                      'faux',\n" +
                "                      3,\n" +
                "                      3.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      15,\n" +
                "                      'faux',\n" +
                "                      3,\n" +
                "                      3.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      3,\n" +
                "                      'faux',\n" +
                "                      4,\n" +
                "                      4.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      8,\n" +
                "                      'faux',\n" +
                "                      4,\n" +
                "                      4.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      4,\n" +
                "                      'vrai',\n" +
                "                      4,\n" +
                "                      4.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      5,\n" +
                "                      'faux',\n" +
                "                      4,\n" +
                "                      4.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      10,\n" +
                "                      'faux',\n" +
                "                      5,\n" +
                "                      5.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      12,\n" +
                "                      'faux',\n" +
                "                      5,\n" +
                "                      5.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      17,\n" +
                "                      'faux',\n" +
                "                      5,\n" +
                "                      5.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      11,\n" +
                "                      'vrai',\n" +
                "                      5,\n" +
                "                      5.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'jaune',\n" +
                "                      'faux',\n" +
                "                      6,\n" +
                "                      6.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'bleu',\n" +
                "                      'vrai',\n" +
                "                      6,\n" +
                "                      6.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'vert',\n" +
                "                      'faux',\n" +
                "                      6,\n" +
                "                      6.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'orange',\n" +
                "                      'faux',\n" +
                "                      6,\n" +
                "                      6.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'hier',\n" +
                "                      'faux',\n" +
                "                      7,\n" +
                "                      7.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'aujourd hui',\n" +
                "                      'vrai',\n" +
                "                      7,\n" +
                "                      7.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'demain',\n" +
                "                      'faux',\n" +
                "                      7,\n" +
                "                      7.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'mardi',\n" +
                "                      'faux',\n" +
                "                      7,\n" +
                "                      7.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      '10 degres',\n" +
                "                      'faux',\n" +
                "                      8,\n" +
                "                      8.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      '100 degres',\n" +
                "                      'vrai',\n" +
                "                      8,\n" +
                "                      8.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      '75 degres',\n" +
                "                      'faux',\n" +
                "                      8,\n" +
                "                      8.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      '1000 degres',\n" +
                "                      'faux',\n" +
                "                      8,\n" +
                "                      8.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'riz',\n" +
                "                      'faux',\n" +
                "                      9,\n" +
                "                      9.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'blé',\n" +
                "                      'vrai',\n" +
                "                      9,\n" +
                "                      9.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'quinoa',\n" +
                "                      'faux',\n" +
                "                      9,\n" +
                "                      9.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'lentilles',\n" +
                "                      'faux',\n" +
                "                      9,\n" +
                "                      9.4\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'oxygène',\n" +
                "                      'faux',\n" +
                "                      10,\n" +
                "                      10.1\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'eau',\n" +
                "                      'vrai',\n" +
                "                      10,\n" +
                "                      10.2\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'fer',\n" +
                "                      'faux',\n" +
                "                      10,\n" +
                "                      10.3\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      'azote',\n" +
                "                      'faux',\n" +
                "                      10,\n" +
                "                      10.4\n" +
                "                  );\n";
        db.execSQL(strsql);
    }

    public void init_Test(SQLiteDatabase db){
        String strsql = " INSERT INTO Test (\n" +
                "                     nquestion,\n" +
                "                     theme,\n" +
                "                     type,\n" +
                "                     IDT\n" +
                "                 )\n" +
                "                 VALUES (\n" +
                "                     5,\n" +
                "                     'Math',\n" +
                "                     'Rapide',\n" +
                "                     1\n" +
                "                 ),\n" +
                "                 (\n" +
                "                     40,\n" +
                "                     'Math',\n" +
                "                     'Long',\n" +
                "                     2\n" +
                "                 ),\n" +
                "                 (\n" +
                "                     40,\n" +
                "                     'Logique',\n" +
                "                     'Long',\n" +
                "                     3\n" +
                "                 ),\n" +
                "                 (\n" +
                "                     5,\n" +
                "                     'Logique',\n" +
                "                     'Rapide',\n" +
                "                     4\n" +
                "                 );\n";
        db.execSQL(strsql);
    }


}
