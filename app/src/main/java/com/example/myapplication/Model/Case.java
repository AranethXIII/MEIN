package com.example.myapplication.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.myapplication.Firestore.FirestoreConnection;
import com.example.myapplication.Firestore.FirestoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;

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

    ArrayList<Case> getAll() {
        //it's not fetching the data , then give the command for fetching it
        if (!working)
            loadAll();
        //wait until everything has been loaded
        while (!done) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Case> list = new ArrayList<>();
        for (FirestoreWrapper<Case> c : CaseList) {
            list.add(c.getData().getValue());
        }

        //returns list of all cases in db
        return list;
    }

    ArrayList<String> getAllDescriptions() {
        ArrayList<String> list = new ArrayList<>();
        if (done) {
            for (FirestoreWrapper<Case> c : CaseList) {
                list.add(c.getData().getValue().getDescription());
            }
            return list;
        } else {
            //it's not fetching the data , then give the command for fetching it
            if (!working)
                loadAll();
            //wait until everything has been loaded
            while (!done) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (FirestoreWrapper<Case> c : CaseList) {
                list.add(c.getData().getValue().getDescription());
            }
            return list;
        }
        //returns list of descriptions from all cases in db
    }

    private void loadAll() {
        working = true;
        final ArrayList<FirestoreWrapper<Case>> al = new ArrayList<>();
        Case temp = new Case();
        while (!done) {
            al.add(temp.load("" + counter));
            al.get(counter).getStatus().observeForever(new Observer<Integer>() {
                int i = counter;

                @Override
                public void onChanged(Integer integer) {
                    if (integer == 2) {
                        al.get(i).getStatus().removeObserver(this);
                    }
                    if (integer == 3)
                        done = true;
                    al.get(i).getStatus().removeObserver(this);
                }
            });
            counter++;
            //slow it down and give the download time to check if there are none left
            try {
                wait(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //just making sure there aren't any empty ones added while the program waited for the status to be error
        boolean clean = false;
        for (int i = al.size() - 1; i >= 0 && !clean; i--) {
            if (al.get(i).getData().getValue() == null) {
                al.remove(i);
            } else {
                clean = true;
            }
        }
        CaseList.addAll(al);
    }

    private boolean working = false;
    private boolean done = false;
    private int counter = 1;
    private ArrayList<FirestoreWrapper<Case>> CaseList = new ArrayList<>();
}
