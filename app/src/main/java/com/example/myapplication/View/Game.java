package com.example.myapplication.View;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Scenario;
import com.example.myapplication.R;
public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView tex = findViewById(R.id.textView2);

        Scenario scenario;

        //TODO use Model.Scenario class for backend interaction
    }

}
