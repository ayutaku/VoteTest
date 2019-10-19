package com.example.votetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class VoteActivity extends AppCompatActivity {

    Spinner spinner;

    int ticket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //add↓
       // Intent i=getIntent();
       // String genre= i.getStringExtra("genre");
        //add↑


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        SharedPreferences preferences = getSharedPreferences("vote",MODE_PRIVATE);
        ticket = preferences.getInt("ticket",0);
        ((TextView)findViewById(R.id.leftTicket)).setText("残り"+ticket+"枚");

        View view = getLayoutInflater().inflate(R.layout.activity_vote, null);

        //if(genre=="fruits") {
          //  spinner = (Spinner) view.findViewById(R.id.spinner_fruits);
        //}else if(genre=="fish"){
          //  spinner = (Spinner) view.findViewById(R.id.spinner_fish);

       // }
       // spinner = (Spinner) view.findViewById(R.id.spinner_fruits);

       // ArrayAdapter adapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

         // if(genre=="fruits") {
           //   adapt.add("ばなな");
             // adapt.add("りんご");
        //}else if(genre=="fish") {
          //    adapt.add("あゆ");
            //  adapt.add("まぐろ");
          //}else {
            //  adapt.add("さとう");
              //adapt.add("しお");

       //}

         // spinner.setAdapter(adapt);

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
        spinner=(Spinner)findViewById(R.id.spinner_fruits);

        if(ticket>0) {
            String selectedThing= spinner.getSelectedItem().toString();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
            String date = sdf.format(calendar.getTime());

            Map<String,Object> qData = new HashMap<String,Object>();
            qData.put("vote","vote");




            FirebaseFirestore db =FirebaseFirestore.getInstance();
            db.collection("rikotenNo1")
                    .document("category1")
                    .collection(selectedThing)
                    .document(date)
                    .set(qData);


            ticket--;
            ((TextView) findViewById(R.id.leftTicket)).setText("残り" + ticket + "枚");
            SharedPreferences preferences = getSharedPreferences("vote", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("ticket", ticket);
            editor.commit();


            Toast.makeText(VoteActivity.this, selectedThing + "に投票しました。", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(VoteActivity.this, "投票券がありません。", Toast.LENGTH_SHORT).show();
        }

    }

}