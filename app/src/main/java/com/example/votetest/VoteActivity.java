package com.example.votetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class VoteActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


    }

    public void send (View v){
        View view = getLayoutInflater().inflate(R.layout.activity_vote, null);

        spinner=(Spinner)view.findViewById(R.id.spinner_fruits);

        String selectedFruit = spinner.getSelectedItem().toString();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("vote")
                .document("test")
                .update(selectedFruit, FieldValue.increment(1.0));

        Toast.makeText(VoteActivity.this, selectedFruit+"に投票しました。", Toast.LENGTH_SHORT).show();

    }

}