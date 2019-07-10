package com.example.myapplication.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.myapplication.Firestore.FirestoreConnection;
import com.example.myapplication.Firestore.FirestoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Case extends FirestoreConnection {


    public static final int COUNTCASES = 2; //TODO - kepp constant updated in realation to db

//==============fields

    String description; // the emergency situation
    ArrayList<String> responses; // viable responses to situation

//==========gettersetter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<String> responses) {
        this.responses = responses;
    }

    public void addResponse(String a) {
        responses.add(a);
    }

//===============constr

    public Case() {
        responses = new ArrayList<String>();
    }

    public Case(String description) {
        this.description = description;
        responses = new ArrayList<String>();
    }

//==============firestore methods

    @Override
    protected String getCollectionPath() {
        return "/Case";
    }

    @Override
    protected Map<String, Object> toMap(@NonNull Object objectToConvert) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description", this.description);
        result.put("responses", this.responses);
        return result;
    }

    @Override
    protected Object toObject(@NonNull Map map) {
        Case acase = new Case();
        acase.setDescription((String) map.get("description"));
        acase.setResponses((ArrayList<String>) map.get("responses"));
        return acase;
    }

    public void write(String id) {
        Case temp = new Case(this.description);
        temp.setResponses(this.responses);
        FirestoreWrapper<Case> wrapper = new FirestoreWrapper<>();
        wrapper.setData(temp);

        addDocument(wrapper, id);
    }

    public FirestoreWrapper<Case> load(String id) {
        FirestoreWrapper<Case> wrapper = new FirestoreWrapper<>();
        getDocument(wrapper, getCollectionPath() + "/" + id);
        return wrapper;
    }

    public ArrayList<FirestoreWrapper<Case>> getAll() {
        if (CaseList.size() ==0)
            loadAll();
        //returns list of all cases in db
        return CaseList;
    }

    public ArrayList<String> getAllDescriptions() {
       return null;
        //returns list of descriptions from all cases in db
    }

    private void loadAll() {
        final ArrayList<FirestoreWrapper<Case>> al = new ArrayList<>();
        Case temp = new Case();
        for (int i = 1; i < 3; i++) {
            al.add(temp.load("" + i));
        }
        CaseList.addAll(al);

    }

    private ArrayList<FirestoreWrapper<Case>> CaseList = new ArrayList<>();

    static Case dbLoadCase(String dbkey){
        Case c = new Case();

        c = c.load(dbkey).getData().getValue();
        //TODO - Observer
        return c;
    }

}
