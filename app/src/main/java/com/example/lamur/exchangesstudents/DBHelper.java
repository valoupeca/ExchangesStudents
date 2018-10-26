package com.example.lamur.exchangesstudents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Services.db";
    public static final String TABLE_USERS= "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_MDP = "mdp";
    public static final String COLUMN_ROLE = "role";

    public static final String TABLE_SERVICES= "services";
    public static final String SERVICE_ID = "_id";
    public static final String SERVICE_USERNAME = "type_service";
    public static final String TAUX_HORAIRE = "taux_horaires";


    public DBHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USERNAME +
                " TEXT," + COLUMN_MDP + " INTEGER," +
                COLUMN_ROLE + " TEXT"+ ")";

        String CREATE_SERVICES_TABLE =  "CREATE TABLE " +
                TABLE_SERVICES + "("
                + SERVICE_ID + " INTEGER PRIMARY KEY," +
                SERVICE_USERNAME +
                " TEXT," + TAUX_HORAIRE + " DOUBLE" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_SERVICES_TABLE);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public void addUser(String username, String mdp){


            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_MDP, mdp);

            db.insert(TABLE_USERS, null, values);
            db.close();

    }



    public boolean isReal(String username, String mdp){
       SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_USERS
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                + " AND "
                + COLUMN_MDP
                + " = \""
                + mdp
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            cursor.close();
            db.close();
            return true;

        } else {
            cursor.close();
            db.close();
            return false;
        }


    }

    public String infoUser(String username, String mdp) {
        SQLiteDatabase db = this.getReadableDatabase();

        String role = "";
        String query = "Select role FROM "
                + TABLE_USERS
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                + " AND "
                + COLUMN_MDP
                + " = \""
                + mdp
                + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            role = cursor.getString(0);
            cursor.close();
            db.close();
            return role;

        } else {
            cursor.close();
            db.close();
            return "";
        }
    }

    public boolean deleteUser(String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_USERS
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + UserName
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean isAdmin(String username, String mdp)
    {

        if(username.equals("admin") && mdp.equals("admin"))
        {
            return true;

        }else
        {
            return false;
        }
    }

    public void listUser()
    {
       /*
        ArrayList<User> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_USERS;

        Cursor cursor = db.rawQuery(query, null);
        User user = new User();

        while(cursor.moveToFirst()){
            user.set_id(Integer.parseInt(cursor.getString(0)));
            user.set_username(cursor.getString(1));
            user.setMdp(cursor.getString(2));
            cursor.close();
            list.add(user)     ;
        }
        db.close();

        return list;
        */

    }
}
