package com.example.lamur.exchangesstudents;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;

    BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        DBHelper dbhelper = new DBHelper(ctx);
        if(method.equals("addUser"))
        {
            String username = params[1];
            String mdp = params[2];
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            dbhelper.addUser(username,mdp);
            return "User ajouté";

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String aVoid) {
        super.onCancelled(aVoid);
    }
}
