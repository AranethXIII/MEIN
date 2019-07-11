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
        result.put("states", this.state);
        result.put("description", this.description);
        result.put("continuation", this.continuation);
        return result;
    }

    @Override
    protected Object toObject(@NonNull Map map) {
        Action action = new Action();
        action.setState((String) map.get("states"));
        action.setDescription((String) map.get("description"));
        action.setCotinuation((HashMap<String, ArrayList<String>>) map.get("contunuation"));
        return action;
    }

//==========tostring

    @Override
    public String toString() {
        return "Action [states=" + state + ", description=" + description + "]";
    }

    static ArrayList<String> listDescriptions(ArrayList<Action> ala) {
        if (ala==null) {return null;}
        ArrayList<String> als = new ArrayList<String>();
        for (Action a: ala) {
            als.add(a.getDescription());
        }
        return als;
}

    static ArrayList<String> listStates(ArrayList<Action> ala) {
        if (ala==null) {return null;}
        ArrayList<String> als = new ArrayList<String>();
        for (Action a: ala) {
            als.add(a.getState());
        }
        return als;
    }


    synchronized Action dbLoadAction(String dbkey){
        Action a = null;
        a = a.load(dbkey).getData().getValue();
        for (int i=0 ; i<5 || a!=null;i++){
            if (a==null) {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //TODO - Observer
        return a;
    }

    ArrayList<Action> dbLoadActionList(ArrayList<String> dbkeys) {
        if (dbkeys==null) {return null;}
        ArrayList<Action> ala = new ArrayList<Action>();
        for (String key : dbkeys) {
            //observer ?
            ala.add(dbLoadAction(key));
        }
        return ala;
    }




}