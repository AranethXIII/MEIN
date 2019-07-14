package com.example.myapplication.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.myapplication.Firestore.FirestoreConnection;
import com.example.myapplication.Firestore.FirestoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;

public class Case {

//	public static final int COUNTCASES = 2; // TODO - kepp constant updated in realation to db

//==============fields

    String description; // the emergency situation
    ArrayList<Integer> responses; // viable responses to this situation, action ids in db

//==========gettersetter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Integer> responses) {
        this.responses = responses;
    }

    public void addResponse(int a) {
        responses.add(a);
    }

//===============constr

    @Override
    public String toString() {
        return "Case [description=" + description + "]";
    }

    public Case() {
        responses = new ArrayList<Integer>();
    }

    public Case(String description) {
        this.description = description;
        responses = new ArrayList<Integer>();
    }

    Case dbLoadCase(int dbkey) {
        Case c = null;

        try {
            c = DBdummy.getCase(dbkey);
        } catch (IndexOutOfBoundsException e) {
        }

        return c;
    }

}

