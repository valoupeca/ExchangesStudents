package com.example.lamur.exchangesstudents;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class Search_Disponibilite_Test {

    @Rule
    public ActivityTestRule<Search_Disponibilite> search_disponibiliteActivityTestRule = new ActivityTestRule<Search_Disponibilite>(Search_Disponibilite.class);
    private Search_Disponibilite search_disponibilite = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        search_disponibilite = search_disponibiliteActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_searchByS() throws Exception{

        assertNotNull(search_disponibilite.findViewById(R.id.searchByS));

        text = search_disponibilite.findViewById(R.id.searchByS);
        text.setText("searchByS");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_searchByD() throws Exception{

        assertNotNull(search_disponibilite.findViewById(R.id.searchByD));

        text = search_disponibilite.findViewById(R.id.searchByD);
        text.setText("searchByD");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_searchByR() throws Exception{

        assertNotNull(search_disponibilite.findViewById(R.id.searchByR));

        text = search_disponibilite.findViewById(R.id.searchByR);
        text.setText("searchByR");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }


}
