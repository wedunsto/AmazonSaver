package com.example.amazonsaver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProductPage extends AppCompatActivity {
    String productUrl; //String variable to store URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle receiveFromResults = getIntent().getExtras(); //Receive extras from the intent
        productUrl = receiveFromResults.getString("productpage"); //Receive string URL to product page from intent
        Intent BrowserUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(productUrl)); //Create an intent to pass to browser
        startActivity(BrowserUrl); //Launch product url in browser
    }
}
