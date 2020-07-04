package com.example.amazonsaver;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ConnectToAmazon extends AppCompatActivity {

    public Document doc; //Variable used to connect to Amazon website
    private GatherUserData gatherUserData; ////Object used to get data from user

    public ConnectToAmazon(){ //Class constructor to get URL and connect to Amazon
        doc = null; //create empty Document variable
        gatherUserData = new GatherUserData();
    }

    public void ConnectAndParseAmazon(TextView textView, TextInputEditText UserSearch){ //Parse Amazon url
        final String url = gatherUserData.GetAmazonSearchURL(UserSearch); //store the Amazon user search url
        final StringBuilder results = new StringBuilder(); //create a string to hold all resulting parses
        final TextView debug = textView;

        new Thread(new Runnable() { //Create a new Thread to run URL
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get(); //Connect to Amazon website
                    Elements links = doc.select("a[href]"); //Parse url using cssQuery
                    for(Element link : links){ //Append parse results to string
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
