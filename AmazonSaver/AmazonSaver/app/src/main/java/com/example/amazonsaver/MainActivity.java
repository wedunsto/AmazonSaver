package com.example.amazonsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText UserSearch; //Variable used to store user user search
    WebView debug;

    //Initialize your activity, set initial content view, retrieve content components
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.activity_main);//Sets the initial content view when run
        UserSearch = findViewById(R.id.SearchText); //Stores the TextInputEditText component in a variable
    }

    public String GetAmazonSearchURL(){
        String UserSearchString = UserSearch.getText().toString(); //Stores the String from the TextInputEditText
        UserSearchString = UserSearchString.replace(" ","+"); //Replaces the spaces in search with +
        String AmazonSearch = "https://www.amazon.com/s?k="+UserSearchString+"&ref=nb_sb_noss_2"; //Stores the complete URL for an Amazon search
        return AmazonSearch; //Returns the Amazon URL
    }

    public void Test(View view) {
        setContentView(R.layout.search_result);//Sets the initial content view when run
        debug = findViewById(R.id.webview);
        debug.loadUrl(GetAmazonSearchURL());
    }
}