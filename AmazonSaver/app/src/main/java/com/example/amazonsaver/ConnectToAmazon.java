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
}
