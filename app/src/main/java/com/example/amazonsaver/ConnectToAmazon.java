package com.example.amazonsaver;

import android.util.Log;
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
import java.util.ArrayList;

public class ConnectToAmazon extends AppCompatActivity {
    private String resultsUrl; //Store the Amazon search result URL
    private ArrayList<ParseItem> parseItems; //Store the collection of ParseItems

    public ConnectToAmazon(String resultsUrl, ArrayList<ParseItem> parseItems){ //Class constructor to get URL and connect to Amazon
        this.resultsUrl = resultsUrl;
        this.parseItems = parseItems;
    }

    public void ConnectAndParseAmazon(){ //Parse Amazon url
        new Thread(new Runnable() { //Create a new Thread to run URL
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(resultsUrl).get(); //Connect to Amazon website
                    Elements links = doc.select("div.sg-col-inner"); //Parse url using cssQuery
                    int size = links.size(); //Stores the number of search results
                    for(int i=0; i<size; i++){
                        String imgUrl = links.select("div.sg-col-inner").select("img").eq(i).attr("src"); //Store each result's image
                        String title = links.select("h2.a-size-mini a-spacing-none a-color-base s-line-clamp-4").select("span").eq(i).text(); //Store each result's title
                        parseItems.add(new ParseItem(imgUrl,title)); //Add each search result image and title to ArrayList
                       //Log.d("debug", imgUrl+" "+title);
                    }
                } catch (IOException e) {
                   Log.d("debug",e.getMessage());
                }
            }
        }).start(); //Start new Thread
    }
}
