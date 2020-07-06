package com.example.amazonsaver;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class GatherUserData extends AppCompatActivity {
    private String amazonUrl; //Stores the results Amazon URL

    public  GatherUserData(){
        amazonUrl = "";
    }

    public void GetAmazonSearchURL(TextInputEditText UserSearch){ //Method to create the Amazon URL string
        String UserSearchString = UserSearch.getText().toString(); //Stores the String from the TextInputEditText
        UserSearchString = UserSearchString.replace(" ","+"); //Replaces the spaces in search with +
        amazonUrl = "https://www.amazon.com/s?k="+UserSearchString+"&ref=nb_sb_noss_2"; //Stores the complete URL for an Amazon search
    }

    public String getAmazonUrl(){
        return this.amazonUrl; //Get the Amazon results URL
    }
}
