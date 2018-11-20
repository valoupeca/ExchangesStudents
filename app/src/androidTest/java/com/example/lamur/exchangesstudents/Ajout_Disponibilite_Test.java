package com.example.lamur.exchangesstudents;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class Ajout_Disponibilite_Test {



    @Rule
    public ActivityTestRule<Ajout_disponibilite> ajout_disponibiliteActivityTestRule = new ActivityTestRule<Ajout_disponibilite>(Ajout_disponibilite.class);
    private Ajout_disponibilite ajout_disponibilite = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        ajout_disponibilite = ajout_disponibiliteActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_Recherche() throws Exception{

        assertNotNull(ajout_disponibilite.findViewById(R.id.search_id));

        text = ajout_disponibilite.findViewById(R.id.search_id);
        text.setText("search_id");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

}
