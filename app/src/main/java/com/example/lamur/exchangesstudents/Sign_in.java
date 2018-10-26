package com.example.lamur.exchangesstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sign_in extends AppCompatActivity {

    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        dbhelper = (DBHelper)this.getIntent().getSerializableExtra("DbHelper");

    }
}
