package com.example.lamur.exchangesstudents;


import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_UserName_MainActivity() throws Exception{

        assertNotNull(mainActivity.findViewById(R.id.signin));

        text = mainActivity.findViewById(R.id.username);
        text.setText("user1");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }


    @Test
    @UiThreadTest
    public void check_Password_MainActivity() throws Exception{

        assertNotNull(mainActivity.findViewById(R.id.signin));

        text = mainActivity.findViewById(R.id.password);
        text.setText("password1");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }


}
