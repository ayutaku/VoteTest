package com.example.votetest;

import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class SelectgenreActivity  extends AppCompatActivity {

    //add this page
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgenre);
    }

    public void decision(View v) {
        //add↓
        spinner = (Spinner) findViewById(R.id.spinner_genre);
        String genre= (String) spinner.getSelectedItem();
//add↑
        // intent.putExtra(genre,genre);//add　　is this true?

        if (genre .equals("fruits")) {
            Intent intent = new Intent(this, VoteActivity.class);

            startActivity(intent);
            MultiDex.install(this);
        } else if (genre .equals("fish")) {
            Intent intent = new Intent(this, VoteActivity2.class);

            startActivity(intent);
            MultiDex.install(this);
        } else if (genre.equals("drink")) {
            Intent intent = new Intent(this, VoteActivity3.class);

            startActivity(intent);
            MultiDex.install(this);
        }
    }
}


