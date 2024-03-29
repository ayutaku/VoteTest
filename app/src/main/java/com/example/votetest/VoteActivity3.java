package com.example.votetest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

    public class VoteActivity3 extends AppCompatActivity {

        Spinner spinner;

        int ticket;



        @Override
        protected void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vote3);

            SharedPreferences preferences = getSharedPreferences("vote",MODE_PRIVATE);
            ticket = preferences.getInt("ticket",0);
            ((TextView)findViewById(R.id.leftTicket)).setText("残り"+ticket+"枚");

            View view = getLayoutInflater().inflate(R.layout.activity_vote3, null);


        }

        public void plus (View v){
            ticket++;
            ((TextView) findViewById(R.id.leftTicket)).setText("残り" + ticket + "枚");
            SharedPreferences preferences = getSharedPreferences("vote", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("ticket", ticket);
            editor.commit();

        }

        public void send (View v){
            spinner=(Spinner)findViewById(R.id.spinner_drink);

            if(ticket>0) {
                String selectedThing = spinner.getSelectedItem().toString();
                FirebaseFirestore db =FirebaseFirestore.getInstance();
                db.collection("vote")
                        .document("test")
                        .update(selectedThing, FieldValue.increment(1.0));

                ticket--;
                ((TextView) findViewById(R.id.leftTicket)).setText("残り" + ticket + "枚");
                SharedPreferences preferences = getSharedPreferences("vote", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("ticket", ticket);
                editor.commit();


                Toast.makeText(VoteActivity3.this, selectedThing + "に投票しました。", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(VoteActivity3.this, "投票券がありません。", Toast.LENGTH_SHORT).show();
            }

        }


    }
