package com.example.lamur.exchangesstudents;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;


public class Sign_UpTest {

    @Rule
    public ActivityTestRule<Sign_up> sign_UpActivityTestRule = new ActivityTestRule<Sign_up>(Sign_up.class);
    private Sign_up sign_Up = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        sign_Up = sign_UpActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_UserName_SignUp() throws Exception{

        assertNotNull(sign_Up.findViewById(R.id.username));

        text = sign_Up.findViewById(R.id.username);
        text.setText("user2");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_Password_SignUp() throws Exception{

        assertNotNull(sign_Up.findViewById(R.id.mdp));

        text = sign_Up.findViewById(R.id.mdp);
        text.setText("password2");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }
}
