package com.example.amazonsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText UserSearch; //Variable used to store user user search
    private Button Submit; //Variable used to store the submit button
    private GatherUserData gatherUserData; //Object to create Amazon URl based on user input

    //Initialize your activity, set initial content view, retrieve content components
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.search_page);//Sets the initial content view when run
        UserSearch = findViewById(R.id.SearchText); //Stores the TextInputEditText component in a variable
        Submit = findViewById(R.id.submit); //Store the Submit button component in a variable

        gatherUserData = new GatherUserData();
        Submit.setOnClickListener(SumbitButton); //Set button eventhandler
    }

    private View.OnClickListener SumbitButton = new View.OnClickListener() { //Create button eventhandler
        @Override
        public void onClick(View view) {
            gatherUserData.GetAmazonSearchURL(UserSearch); //Create Amazon results URL based on user input
            Intent goToResults = new Intent(MainActivity.this, Results.class); //Start Results activity
            goToResults.putExtra("url",gatherUserData.getAmazonUrl()); //Pass the Amazon results URL to the next Activity
            startActivity(goToResults); //Execute Intent
        }
    };
}