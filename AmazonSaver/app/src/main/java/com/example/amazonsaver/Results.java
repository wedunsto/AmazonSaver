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
                Elements links = doc.select("div.s-main-slot.s-result-list.s-search-results.sg-row > " +
                        "div.sg-col-4-of-24.sg-col-4-of-12.sg-col-4-of-36.s-result-item.s-asin.sg-col-4-of-28.sg-col-4-of-16.sg-col.sg-col-4-of-20.sg-col-4-of-32"); //Parse url using cssQuery
                int size = links.size(); //Stores the number of search results
                for(int i=0; i<size; i++){
                    String imgUrl = links.select("img.s-image").eq(i).attr("src"); //Store each result's image
                    String title = links.select("h2").eq(i).text(); //Store each result's title
                    String productUrl = links.eq(i).select("a.a-link-normal.s-no-outline").attr("abs:href");//Stores the URL to the product
                    String price = links.select("span.a-offscreen").eq(i).text(); //Store each result's price
                    ParseItem result = new ParseItem(imgUrl,title,productUrl,price); // Stores each search result
                    parseItems.add(result); //Add each search result image and title to ArrayList
                }
            } catch (IOException e) {
                Log.d("debug",e.getMessage());
            }
            return null;
        }
    }
}
