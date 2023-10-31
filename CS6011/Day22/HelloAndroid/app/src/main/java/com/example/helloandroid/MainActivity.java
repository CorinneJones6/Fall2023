package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String MsTag = "MainActivity:Cj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(view);
            }
        });
    }

    public void handleButton(View view) {
        Log.d(MsTag, "Button was pressed...");
        TextView tv = findViewById(R.id.outputInfoId);
        tv.setText("Hello from Corinne!");
    }
}