package com.example.lamur.exchangesstudents;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class Complete_Profil_Test {



    @Rule
    public ActivityTestRule<Complete_Profil> complete_profilActivityTestRule = new ActivityTestRule<Complete_Profil>(Complete_Profil.class);
    private Complete_Profil complete_profil = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        complete_profil = complete_profilActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_CompanyName() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.company_name));

        text = complete_profil.findViewById(R.id.company_name);
        text.setText("company_name");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_Phone_Id() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.phone_id));

        text = complete_profil.findViewById(R.id.phone_id);
        text.setText("phone_id");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_Adresse_id() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.adresse_id));

        text = complete_profil.findViewById(R.id.adresse_id);
        text.setText("adresse_id");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_Ville_id() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.ville_id));

        text = complete_profil.findViewById(R.id.ville_id);
        text.setText("ville_id");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_CodePostal_id() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.cp_id));

        text = complete_profil.findViewById(R.id.cp_id);
        text.setText("cp_id");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

    @Test
    @UiThreadTest
    public void check_Description() throws Exception{

        assertNotNull(complete_profil.findViewById(R.id.description));

        text = complete_profil.findViewById(R.id.description);
        text.setText("description");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }

}
