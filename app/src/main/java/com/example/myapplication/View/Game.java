package com.example.myapplication.View;



import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Firestore.FirestoreWrapper;
import com.example.myapplication.Model.Action;
import com.example.myapplication.R;
import com.google.api.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView tex=findViewById(R.id.Questionfield);


        Action action=new Action();
        action.setState("blah");
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
    }
}
