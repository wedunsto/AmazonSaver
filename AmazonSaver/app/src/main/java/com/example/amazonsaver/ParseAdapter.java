package com.example.amazonsaver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {
    private ArrayList<ParseItem> parseItems; //Contain a collection of result image URLs and titles
    private Context context; //Holds the activity

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context){ //Overloaded constructor
        this.parseItems = parseItems; //Sets the ArrayList
        this.context = context; //Sets the Context
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position); //Get each ParseItem from ArrayList
        holder.textView.setText(parseItem.getTitle()); //Set CardView title to ParseItem's title
        Picasso.get().load(parseItem.getImageUrl()).into(holder.imageView); //Set CardView ImageView to ParseItem's image
    }

    @Override
    public int getItemCount() {
        return parseItems.size(); //Count the number of parse results
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView; //Connect to Card ImageView
        TextView textView; //Connect to Card TextView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); //Get the position of the selected item
            ParseItem parseItem = parseItems.get(position); //Get the result at the desired location

            Intent sendUrl = new Intent(context, ProductPage.class); //Intent to send page URL to WebView
            sendUrl.putExtra("productpage",parseItem.getProductUrl()); //Sends product page URL to WebPage Class
            context.startActivity(sendUrl); //Starts intent
        }
    }
}
