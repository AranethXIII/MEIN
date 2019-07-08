package com.example.myapplication.Model;

import androidx.annotation.NonNull;

import com.example.myapplication.Firestore.FirestoreConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Case extends FirestoreConnection {

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

    static ArrayList<Case> getAll(){
        //TODO
        //returns list of all cases in db
        return null;
    }

    static ArrayList<String> getAllDescriptions(){
        return null;
        //TODO
        //returns list of descriptions from all cases in db
    }

}
