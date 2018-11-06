package com.example.lamur.exchangesstudents;


import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;


public class MainactivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.signin));
        text= mActivity.findViewById(R.id.username);
        text.setText("admin");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }


    @Test
    @UiThreadTest
    public void checkPassWord() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView4));
        text= mActivity.findViewById(R.id.password);
        text.setText("password1");
        String name= text.getText().toString();
        assertNotEquals("password",name);
    }

    @Test
    @UiThreadTest
    public void checkEmail() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView3));
        text= mActivity.findViewById(R.id.email);
        text.setText("email1");
        String name= text.getText().toString();
        assertNotEquals("email",name);
    }


}
