package com.example.lamur.exchangesstudents;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class NoteRdv_Test {

    @Rule
    public ActivityTestRule<NoteRdv> noteRdvActivityTestRule = new ActivityTestRule<NoteRdv>(NoteRdv.class);
    private NoteRdv noteRdv = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        noteRdv = noteRdvActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check_note() throws Exception{

        assertNotNull(noteRdv.findViewById(R.id.note));

        text = noteRdv.findViewById(R.id.note);
        text.setText("note");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }


    @Test
    @UiThreadTest
    public void check_commentaire() throws Exception{

        assertNotNull(noteRdv.findViewById(R.id.commentaire));

        text = noteRdv.findViewById(R.id.commentaire);
        text.setText("commentaire");

        String name = text.getText().toString();

        assertNotEquals(text,name);
    }



}
