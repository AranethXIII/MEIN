package com.example.myapplication.Model;

import java.util.ArrayList;

/**
 * Main Interacting Class between userinterface and programm logic
 *
 */
public class Scenario {

//==============fields

    ArrayList<Action> state; // actions/answers taken previously
    Case vcase;

//===============constr

    public Scenario() {
        state = new ArrayList<Action>();
    }

    public Scenario(Case vcase) {
        this.vcase = vcase;
        state = new ArrayList<Action>();
    }

    public Scenario(ArrayList<Action> state, Case vcase) {
        this.state = state;
        this.vcase = vcase;
    }

//==========gettersetter

    public ArrayList<Action> getState() {
        return state;
    }

    public void setState(ArrayList<Action> state) {
        this.state = state;
    }

    public Case getVcase() {
        return vcase;
    }

    public void setVcase(Case vcase) {
        this.vcase = vcase;
    }

//==================methods

    public String getcurrentState() {
        if (state.size() > 0) {
            return (state.get(state.size()- 1)).getState();
        }
        return "no state";
    }

    public String getcurrentDescription() {
        if (state.size() > 0) {
            return (state.get(state.size()- 1)).getDescription();
        }
        return "no description";
    }

    public Action getLastAction() {
        if (state.size() > 0) {
            return state.get(state.size() - 1);
        }
        return null;
    }

    public boolean hasContinuation() { // checks if situation has more steps/answers for it to be solved/solvable
        Action a = this.getLastAction();
        if (a != null && a.getCotinuationList(vcase.getDescription()).size() > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getContinuation(){
        Action a = getLastAction();
        if (a != null && this.hasContinuation() ) {
            return a.getCotinuation().get(getVcase().getDescription());
        }
        return null; /// or null
    }

    public void addState(Action a) {
        state.add(a);
    }

    Scenario randomScenario(){
        //TODO
        return null;
        //random scenario/case out of all cases
    }

    Scenario choosenScenario(int index) {
        //TODO
        return null;
        //choose scenario case out of listr of all cases
    }

}
