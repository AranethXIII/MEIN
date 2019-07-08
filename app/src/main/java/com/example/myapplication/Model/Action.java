package com.example.myapplication.Model;

import androidx.annotation.NonNull;

import com.example.myapplication.Firestore.FirestoreConnection;
import com.example.myapplication.Firestore.FirestoreWrapper;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Action extends FirestoreConnection {

//==============fields

    String dbId;
    String state; // situation
    String description; // action taken
    HashMap<String, ArrayList<String>> continuation; // follow up actions

//==========methods

    public void putContinuation(String c, String a) {        //adds follow up action for specific case
        ArrayList<String> ala = this.continuation.get(c);
        if (ala == null) {
            ala = new ArrayList<String>();
            ala.add(a);
            this.continuation.put(c, ala);
        } else {
            ala.add(a);
        }
    }

    public ArrayList<String> getCotinuationList(String c) {
        return continuation.get(c);
    }
    // get next Answers/Options for Case

    /**
     * writes this object into the db
     */
    public void write(String id) {

        Action action = new Action();
        action.setState(this.state);
        action.setDescription(this.description);
        action.setCotinuation(this.continuation);
        FirestoreWrapper<Action> wrapper = new FirestoreWrapper<>();
        wrapper.setData(action);

        addDocument(wrapper, id);

    }

    public FirestoreWrapper<Action> load(String id) {
        FirestoreWrapper<Action> wrapper = new FirestoreWrapper<>();
        getDocument(wrapper, getCollectionPath() + "/" + id);
        return wrapper;
    }

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

    public HashMap<String, ArrayList<String>> getCotinuation() {
        return continuation;
    }

    public void setCotinuation(HashMap<String, ArrayList<String>> h) {
        this.continuation = h;
    }

//==============constr

    public Action() {
        this.continuation = new HashMap<String, ArrayList<String>>();
    }

    public Action(String state, String description) {
        this.state = state;
        this.description = description;
        this.continuation = new HashMap<String, ArrayList<String>>();
    }


//===========firestore methods

    @Override
    protected String getCollectionPath() {
        return "/Action";
    }

    @Override
    protected Map<String, Object> toMap(@NonNull Object objectToConvert) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("state", this.state);
        result.put("description", this.description);
        result.put("continuation", this.continuation);
        return result;
    }

    @Override
    protected Object toObject(@NonNull Map map) {
        Action action = new Action();
        action.setState((String) map.get("state"));
        action.setDescription((String) map.get("description"));
        action.setCotinuation((HashMap<String, ArrayList<String>>) map.get("contunuation"));
        return action;
    }

//==========tostring

    @Override
    public String toString() {
        return "Action [state=" + state + ", description=" + description + "]";
    }

}