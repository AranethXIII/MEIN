package com.example.myapplication.View;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Scenario;

import com.example.myapplication.R;


public class Game extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton aw1;
    RadioButton aw2;
    RadioButton aw3;
    RadioButton aw4;
    TextView tex;
    Button button;
    Scenario scenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        radioGroup= findViewById(R.id.radioGrp);
        aw1=findViewById(R.id.aw);
        aw2=findViewById(R.id.aw2);
        aw3=findViewById(R.id.aw3);
        aw4=findViewById(R.id.aw4);
        tex=findViewById(R.id.textView2);
        button=findViewById(R.id.next);


        //TODO use Model.Scenario class for backend interaction

        //=======example

        scenario=Scenario.randomScenario();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGroup.getCheckedRadioButtonId() !=-1) {
                    scenario.selectAction(radioGroup.getCheckedRadioButtonId());
                }
            }
        });

        fillField();

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

    void fillField(){
        tex.setText(scenario.getVcase().getDescription());
        aw1.setText(scenario.getAnswerOptionDescriptions().get(0));
        aw2.setText(scenario.getAnswerOptionDescriptions().get(1));
        aw3.setText(scenario.getAnswerOptionDescriptions().get(2));
        aw4.setText(scenario.getAnswerOptionDescriptions().get(3));
    }
}
