package com.example.amazonsaver;

public class ParseItem {
    private String imageUrl; //Holds the URL to result image
    private String title; //Holds the result title
    private String productUrl; //Holds the URL to the result page
    private String price; //Holds the result price

    public ParseItem(String imageUrl, String title, String productUrl, String price){ //Overloaded constructor
        this.imageUrl = imageUrl; //sets the imageUrl
        this.title = title; //Sets the title
        this.productUrl = productUrl; //Sets the productUrl
        this.price = price; //Sets the price
    }

    public String getImageUrl(){ //Get result's image URL
        return this.imageUrl;
    } //Return url to product image
    public String getTitle(){ //Get result's title
        return this.title;
    } //Return title of product
    public String getProductUrl(){return this.productUrl; } //Return URL to product page
    public String getPrice(){return  this.price;} //Return price of product
}
