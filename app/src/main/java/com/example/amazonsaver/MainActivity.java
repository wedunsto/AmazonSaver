package com.example.amazonsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText UserSearch; //Variable used to store user user search
    private Button Submit; //Variable used to store the submit button
    private GatherUserData gatherUserData; //Object used to get data from user
    private ConnectToAmazon connectToAmazon; //Object used to connect to Amazon and parse results

    //Initialize your activity, set initial content view, retrieve content components
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.search_page);//Sets the initial content view when run
        UserSearch = findViewById(R.id.SearchText); //Stores the TextInputEditText component in a variable
        Submit = findViewById(R.id.submit); //Store the Submit button component in a variable
        Submit.setOnClickListener(SumbitButton); //Set button eventhandler

        gatherUserData = new GatherUserData();
        connectToAmazon = new ConnectToAmazon();
    }

    private View.OnClickListener SumbitButton = new View.OnClickListener() { //Create button eventhandler
        @Override
        public void onClick(View view) {
            setContentView(R.layout.search_results);//Change the layout
            TextView debug = findViewById(R.id.textview);
            ConnectAndParseAmazon(debug);
        }
    };

    private void ConnectAndParseAmazon(TextView textView){
        final String url = gatherUserData.GetAmazonSearchURL(UserSearch);
        final StringBuilder results = new StringBuilder();
        final TextView debug = textView;

        new Thread(new Runnable() { //Create a new Thread to run URL
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get(); //Connect to Amazon website
                    Elements links = doc.select("a[href]");
                    for(Element link : links){
                        results.append(link.attr("href")+"\n");
                    }
                    debug.setText(results.toString());
                } catch (IOException e) {
                    results.append(e.getMessage()+"\n");
                }
            }
        }).start(); //Start new Thread
    }
}