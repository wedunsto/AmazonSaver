package com.example.amazonsaver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ConnectToAmazon {
    public Document doc; //Variable used to connect to Amazon website
    private Elements links; //Variable to parse Amazon website
    private StringBuilder results; //Variable used to hold parsed content

    public ConnectToAmazon(){ //Class constructor to get URL and connect to Amazon
        doc = null; //create empty Document variable
        links = null; //create empty Elements variable
        results = new StringBuilder();
    }

    public void ConnectToAmazon(String URL){ //Connect to Amazon result URL
        final String url = URL; //make the URL final to access in the Thread
        new Thread(new Runnable() { //Create a new Thread to run URL
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect(url).get(); //Connect to Amazon website
                    System.out.println("test1");
                } catch (IOException e) {
                    results.append(e.getMessage()+"\n");
                }
            }
        }).start(); //Start new Thread
    }

    public void ParseAmazon(Document doc){
        links = doc.select("a[href]");
        for(Element link : links){
            results.append(link.attr("href")+"\n");
        }
    }

    public Document getDoc(){
        System.out.println("test2");
        return this.doc;
    }
}
