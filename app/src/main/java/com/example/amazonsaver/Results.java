package com.example.amazonsaver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

public class Results extends Activity {
    private String url; //Amazon result URL from user search
    private RecyclerView recyclerView; //Object to edit the contents of the RecyclerView

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.results);//Sets the content view to results
        Bundle receiveFromFirst = getIntent().getExtras(); //Receive extras from the intent
        url = receiveFromFirst.getString("url"); //Receive String URL from intent
        Log.d("debug", url);
    }
}
