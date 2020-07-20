package com.example.amazonsaver;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductPage extends AppCompatActivity {
    String productUrl; //String variable to store URL
    WebView webView; //Variable to access webview element

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productpage);

        webView = findViewById(R.id.webview); //Connect to the WebView element on the Results layout
        Bundle receiveFromResults = getIntent().getExtras(); //Receive extras from the intent
        productUrl = receiveFromResults.getString("productpage"); //Receive string URL to product page from intent

        webView.loadUrl(productUrl);
    }
}
