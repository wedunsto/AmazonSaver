package com.example.amazonsaver;

public class ParseItem {
    private String imageUrl; //Holds the URL to result image
    private String title; //Holds the result title

    public ParseItem(String imageUrl, String title){ //Overloaded constructor
        this.imageUrl = imageUrl; //sets the imageUrl
        this.title = title; //Sets the title
    }

    public String getImageUrl(){ //Get result's image URL
        return this.imageUrl;
    }
    public String getTitle(){ //Get result's title
        return this.title;
    }
}
