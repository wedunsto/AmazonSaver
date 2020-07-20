package com.example.amazonsaver;

public class ParseItem {
    private String imageUrl; //Holds the URL to result image
    private String title; //Holds the result title
    private String productUrl; //Hold the URL to the result page

    public ParseItem(String imageUrl, String title, String productUrl){ //Overloaded constructor
        this.imageUrl = imageUrl; //sets the imageUrl
        this.title = title; //Sets the title
        this.productUrl = productUrl; //Sets the productUrl
    }

    public String getImageUrl(){ //Get result's image URL
        return this.imageUrl;
    } //Return url to product image
    public String getTitle(){ //Get result's title
        return this.title;
    } //Return title of product
    public String getProductUrl(){return this.productUrl; } //Return URL to product page
}
