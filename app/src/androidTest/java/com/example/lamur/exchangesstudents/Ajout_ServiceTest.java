package com.example.lamur.exchangesstudents;


import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;


public class Ajout_ServiceTest {

    @Rule
    public ActivityTestRule<Ajout_service> ajout_service_ActivityTestRule = new ActivityTestRule<Ajout_service>(Ajout_service.class);
    private Ajout_service ajout_service = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        ajout_service = ajout_service_ActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_NomService_Ajout_Service() throws Exception{

        assertNotNull(ajout_service.findViewById(R.id.date));

        text = ajout_service.findViewById(R.id.date);
        text.setText("nom_service");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_TauxHoraire_Ajout_Service() throws Exception{

        assertNotNull(ajout_service.findViewById(R.id.taux_horaire));

        text = ajout_service.findViewById(R.id.taux_horaire);
        text.setText("33");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

}
