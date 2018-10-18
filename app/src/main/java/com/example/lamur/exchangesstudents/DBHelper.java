package com.example.lamur.exchangesstudents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "services.db";
    public static final String TABLE_USERS= "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_MDP = "mdp";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USERNAME +
                " TEXT," + COLUMN_MDP + " INTEGER" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_username());
        values.put(COLUMN_MDP, user.getMdp());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String username, String mdp){
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
        User user = new User();

        if(cursor.moveToFirst()){
            user.set_id(Integer.parseInt(cursor.getString(0)));
            user.set_username(cursor.getString(1));
            user.setMdp(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
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


}
