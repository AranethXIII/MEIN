package com.example.myapplication.View;


import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Scenario;

import com.example.myapplication.R;


public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //TODO use Model.Scenario class for backend interaction

        //=======example


        Scenario s1 = Scenario.randomScenario();

        System.out.println(s1.getVcase());

        System.out.println(s1.getAnsweroptions());

        System.out.println("select option 1");
        s1.selectAction(0);

        System.out.println(s1.getcurrentDescription());
        System.out.println(s1.getcurrentState());
        System.out.println(s1.getAnsweroptions());

        System.out.println();

        System.out.println("Scenario 2");

        Scenario s2 = Scenario.randomScenario();

        System.out.println(s2.getVcase());

        System.out.println(s2.getAnswerOptionDescriptions());

        System.out.println("select option 3");
        s2.selectAction(2);

        System.out.println(s2.getcurrentDescription());
        System.out.println(s2.getcurrentState());
        System.out.println(s2.getAnswerOptionDescriptions());

        System.out.println("select option 1");
        s2.selectAction(0);

        System.out.println(s2.getcurrentDescription());
        System.out.println(s2.getcurrentState());
        System.out.println(s2.getAnswerOptionDescriptions());


        //=======
    }

}
