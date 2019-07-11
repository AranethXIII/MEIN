package com.example.myapplication.View;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Firestore.FirestoreWrapper;
import com.example.myapplication.Model.Action;
import com.example.myapplication.Model.Case;
import com.example.myapplication.R;
import com.google.api.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView tex = findViewById(R.id.Questionfield);

        /*
        Action action=new Action();
        action.setStates("blah");
        action.setDescription("the humans are dead");
        HashMap<String, ArrayList<String>> map=new HashMap<>();
        ArrayList<String> l= new ArrayList<>();
        l.add("1");
        l.add("2");
        map.put("Action1",l);
        action.setCotinuation(map);
        action.write("1");


        Action action2=new Action();
        final FirestoreWrapper<Action> wrapper =action2.load("1");

        wrapper.getStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer ==2){
                    tex.setText(wrapper.getData().getValue().toString());
                }
            }
        });

        Case case1 = new Case();
        case1.setDescription("case1");
        case1.setResponses(new ArrayList<String>());
        case1.addResponse("a");
        case1.addResponse("b");
        case1.addResponse("c");
        case1.write("1");

        Case case3=new Case();
        case3.setDescription("case2");
        case1.setResponses(new ArrayList<String>());
        case1.addResponse("Fisch");
        case1.addResponse("Schinken");
        case1.addResponse("Banane");
        case1.write("2");

        Case case2 = new Case();
        final FirestoreWrapper<Case> c = case2.load("1");
        c.getStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 2) {
                    tex.append(c.getData().getValue().getDescription());
                    for (String s : c.getData().getValue().getResponses()) {
                        tex.append(" " + s);
                    }
                    tex.append("\n");
                }
            }
        });

        final FirestoreWrapper<Case> c3= case2.load("2");
        c3.getStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 2) {
                    tex.append(c3.getData().getValue().getDescription());
                    for (String s : c3.getData().getValue().getResponses()) {
                        tex.append(" " + s);
                    }
                    tex.append("\n");
                }
            }
        });*/
        Case testLoad = new Case();
        ArrayList<FirestoreWrapper<Case>> caseList = testLoad.getAll();

        for (final FirestoreWrapper<Case> c : caseList) {
            c.getStatus().observeForever(new Observer<Integer>() {
                public void onChanged(Integer integer) {
                    if (integer == 2) {
                        tex.append(c.getData().getValue().getDescription());
                        for (String s : c.getData().getValue().getResponses()) {
                            tex.append(" " + s);
                        }
                        tex.append("\n");
                    }
                }
            });
        }
        //TODO use Model.Scenario class for backend interaction
    }

}
