package com.example.myapplication.Model;

//import androidx.annotation.NonNull;

//import com.example.myapplication.Firestore.FirestoreConnection;
//import com.example.myapplication.Firestore.FirestoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;

public class Action {

//==============fields


    String state; // situation
    String description; // action taken
    HashMap<String, ArrayList<Integer>> continuation; // follow up actions

//==========methods

    public void putContinuation(String c, Integer a) {        //adds follow up action for specific case
        ArrayList<Integer> ala = this.continuation.get(c);
        if (ala == null) {
            ala = new ArrayList<Integer>();
            ala.add(a);
            this.continuation.put(c, ala);
        } else {
            ala.add(a);
        }
    }

    public ArrayList<Integer> getCotinuationList(String c) {
        return continuation.get(c);
    }
    // get next Answers/Options for Case

    /**
     * writes this object into the db
     */


// ============getter setter
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, ArrayList<Integer>> getCotinuation() {
        return continuation;
    }

    public void setCotinuation(HashMap<String, ArrayList<Integer>> h) {
        this.continuation = h;
    }

//==============constr

    public Action() {
        this.continuation = new HashMap<String, ArrayList<Integer>>();
    }

    public Action(String state, String description) {
        this.state = state;
        this.description = description;
        this.continuation = new HashMap<String, ArrayList<Integer>>();
    }

//==========tostring

    @Override
    public String toString() {
        return "Action [states=" + state + ", description=" + description + "]";
    }

    public static ArrayList<String> listDescriptions(ArrayList<Action> ala) {
        if (ala == null) {
            return null;
        }
        ArrayList<String> als = new ArrayList<String>();
        for (Action a : ala) {
            als.add(a.getDescription());
        }
        return als;
    }

    static ArrayList<String> listStates(ArrayList<Action> ala) {
        if (ala == null) {
            return null;
        }
        ArrayList<String> als = new ArrayList<String>();
        for (Action a : ala) {
            als.add(a.getState());
        }
        return als;
    }


    //======

    Action dbLoadAction(int key) {
        //TODO6
        Action a = null;
        try {
            a = DBdummy.getAction(key);
        } catch (IndexOutOfBoundsException e) {
        }

        return a;
    }

    ArrayList<Action> dbLoadActionList(ArrayList<Integer> dbkeys) {
        if (dbkeys == null) {
            return null;
        }
        ArrayList<Action> ala = new ArrayList<Action>();
        for (int key : dbkeys) {
            ala.add(dbLoadAction(key));
        }
        return ala;
    }

}