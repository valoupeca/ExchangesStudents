package com.example.lamur.exchangesstudents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper{

    private static DBHelper sInstance;

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

    public static synchronized DBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DBHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
       // db.setForeignKeyConstraintsEnabled(true);
    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                COLUMN_USERNAME + " TEXT," +
                COLUMN_MDP +  " TEXT," +
                COLUMN_ROLE + " TEXT"+
                ")";


        String CREATE_SERVICES_TABLE =  "CREATE TABLE " +
                TABLE_SERVICES + "("
                + SERVICE_ID + " INTEGER PRIMARY KEY," +
                SERVICE_USERNAME +
                " TEXT," + TAUX_HORAIRE + " DOUBLE" + ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_SERVICES_TABLE);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
            onCreate(db);
        }

    }

    public void addOrUpdateUser(String username, String mdp, String role){


            SQLiteDatabase db = getWritableDatabase();
          db.beginTransaction();
          try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_MDP, mdp);
            values.put(COLUMN_ROLE,role);
              int rows = db.update(TABLE_USERS, values, COLUMN_USERNAME + "= ?", new String[]{username});

              if (rows == 1) {
                  // Get the primary key of the user we just updated
                  String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                          COLUMN_USERNAME, TABLE_USERS, COLUMN_ID);
                  Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(username)});
                  try {
                      if (cursor.moveToFirst()) {
                          db.setTransactionSuccessful();
                      }
                  } finally {
                      if (cursor != null && !cursor.isClosed()) {
                          cursor.close();
                      }
                  }
              } else {
                  // user with this userName did not already exist, so insert new user
                  db.insertOrThrow(TABLE_USERS, null, values);
                  db.setTransactionSuccessful();
              }
          } catch (Exception e) {
              Log.d(TAG, "Error while trying to add or update user");
          } finally {
              db.endTransaction();
          }

    }



    public boolean isReal(String username, String mdp){

        String IsReel =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        username,
                        COLUMN_MDP,
                        mdp);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(IsReel, null);

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
        SQLiteDatabase db = getReadableDatabase();

        String role = "";


        String infoUser =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        username,
                        COLUMN_MDP,
                        mdp);


        Cursor cursor = db.rawQuery(infoUser, null);
        try {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_ROLE)) != null) {
                    role = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
                } else {
                    role = "Unknown";
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return  role;
    }


    public boolean deleteUser(String UserName){
        SQLiteDatabase db = getWritableDatabase();
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
