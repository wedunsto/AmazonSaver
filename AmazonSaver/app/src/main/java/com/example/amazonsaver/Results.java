package com.example.amazonsaver;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Results extends AppCompatActivity {
    private String url; //Amazon result URL from user search
    private RecyclerView recyclerView; //Object to edit the contents of the RecyclerView
    private ParseAdapter adapter; //Object to attached adapter to RecyclerView
    private ArrayList<ParseItem> parseItems = new ArrayList<>();; //Collection of result images and titles

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.results);//Sets the content view to results
        Bundle receiveFromFirst = getIntent().getExtras(); //Receive extras from the intent
        url = receiveFromFirst.getString("url"); //Receive String URL from intent

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ParseAdapter(parseItems,this);
        recyclerView.setAdapter(adapter); //Set adapter to RecyclerView
        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(url).get(); //Connect to Amazon website
                Elements links = doc.select("div.sg-col-inner"); //Parse url using cssQuery
                int size = links.size(); //Stores the number of search results
                Elements productUrls = links; //Parse url using cssQuery
                for(int j=0; j<4;j++){
                    productUrls.remove(0); //Remove element used in UI
                }
                for(int i=0; i<size; i++){
                    String imgUrl = links.select("img.s-image").eq(i).attr("src"); //Store each result's image
                    String title = links.select("h2").eq(i).text(); //Store each result's title
                    String productUrl = productUrls.eq(i).select("a.a-link-normal").attr("abs:href");//Stores the URL to the product
                    parseItems.add(new ParseItem(imgUrl,title,productUrl)); //Add each search result image and title to ArrayList
                }
            } catch (IOException e) {
                Log.d("debug",e.getMessage());
            }
            return null;
        }
    }
}
