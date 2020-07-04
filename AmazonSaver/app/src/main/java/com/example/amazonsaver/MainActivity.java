package com.example.amazonsaver;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText UserSearch; //Variable used to store user user search
    private Button Submit; //Variable used to store the submit button
    private ConnectToAmazon connectToAmazon; //Object used to connect to Amazon and parse results

    //Initialize your activity, set initial content view, retrieve content components
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Contains data from app most recent used
        setContentView(R.layout.search_page);//Sets the initial content view when run
        UserSearch = findViewById(R.id.SearchText); //Stores the TextInputEditText component in a variable
        Submit = findViewById(R.id.submit); //Store the Submit button component in a variable
        connectToAmazon = new ConnectToAmazon();

        Submit.setOnClickListener(SumbitButton); //Set button eventhandler
    }

    private View.OnClickListener SumbitButton = new View.OnClickListener() { //Create button eventhandler
        @Override
        public void onClick(View view) {
            setContentView(R.layout.search_results);//Change the layout
            TextView debug = findViewById(R.id.textview);
            connectToAmazon.ConnectAndParseAmazon(debug,UserSearch);
        }
    };
}