package com.example.myapplication.Model;
//package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main Interacting Class between userinterface and programm logic
 *
 */
public class Scenario {

//==============fields

    ArrayList<Action> states; // actions/answers taken previously
    Case vcase;
    private ArrayList<Action> answeroptions;    // selectable actions at current state

//===============constr

    public Scenario() {
        states = new ArrayList<Action>();
        ArrayList<Action> answeroptions = null;
    }

    public Scenario(Case vcase) {
        this.vcase = vcase;
        states = new ArrayList<Action>();
        ArrayList<Action> answeroptions = null;
    }

    public Scenario(ArrayList<Action> states, Case vcase) {
        this.states = states;
        this.vcase = vcase;
        ArrayList<Action> answeroptions = null;
    }

//==========gettersetter

    public ArrayList<Action> getStates() {
        return states;
    }

    public void setStates(ArrayList<Action> states) {
        this.states = states;
    }

    public Case getVcase() {
        return vcase;
    }

    public void setVcase(Case vcase) {
        this.vcase = vcase;
    }

    public ArrayList<Action> getAnsweroptions() { return answeroptions; }

    public void setAnsweroptions(ArrayList<Action> answeroptions) { this.answeroptions = answeroptions; }

    //==================methods

    public String[] getXAnswers(int x){  //returns first X possible answers , returns X answers and "" if not enough answers;   for gui
        String[] sa = new String[x];
        if (this.hasContinuation()) {
            for (int i = 0; i < x; i++) {
                if (answeroptions.size() <= (i + 1)) {
                    sa[i] = answeroptions.get(i).getDescription();
                } else {
                    sa[i] = ""; //disable in that case
                }
            }
            return sa;
        }
        for (int i = 0; i < x; i++){
            sa[i] = "";
        }
        return sa;
    }

    public String getcurrentState() {// get state of current action
        if (states.size() > 0 && getLastAction()!=null) {
            return (getLastAction()).getState();
        }
        return "no states";
    }

    public String getcurrentDescription() {//get description (action taken which lead to current state)
        if (states.size() > 0 && getLastAction()!=null) {
            return (getLastAction()).getDescription();
        }
        return "no description";
    }

    public Action getLastAction() {//get last selected action
        if (states.size() > 0) {
            return states.get(states.size() - 1);
        }
        return null;
    }


    public boolean hasContinuation() { // checks if situation has more steps/answers for it to be solved/solvable
        Action a = this.getLastAction();
        if (a != null && a.getCotinuationList(vcase.getDescription()) != null &&a.getCotinuationList(vcase.getDescription()).size() > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getContinuationKeys(){ //get db - action keys for answers
        Action a = getLastAction();
        if (a != null && this.hasContinuation() ) {
            return a.getCotinuationList(this.getVcase().getDescription());
        }
        return null; /// or null
    }

    public void addState(Action a) {// a = Action selected
        if (a==null) {return;}
        states.add(a);  //add a to selection history
        this.answeroptions = a.dbLoadActionList(this.getContinuationKeys());//update possible new answeroptions
    }

    public void selectAction(int i){    //index of the selected answer
        if (this.answeroptions!=null && i<answeroptions.size() && i>=0 ) {
            this.addState(answeroptions.get(i));
        }
    }

    public static Scenario randomScenario(){
        int comp = DBdummy.countcases();
        Random rand = new Random();
        int i = rand.nextInt(comp);
        if(i<0 || i>=comp) {
            return null; //out of bounce
        }
        Scenario scen = Scenario.choosenScenario(i);
        return scen;
    }


    public static Scenario choosenScenario(int i) {
        int comp = DBdummy.countcases();
        if(i<0 || i>comp) {
            return null; //out of bounce
        }
        Case c = new Case();
        c=c.dbLoadCase(i);

        Scenario scen = Scenario.buildScenarion(c);
        return scen;
    }

    private static Scenario buildScenarion(Case c) {
        if(c==null) {
            return null;
        }
        Scenario s = new Scenario(c);
        s.setAnsweroptions(DBdummy.getListAction(c.getResponses()));

//        int respsize = c.getResponses().size();
//        Random rand = new Random();
//        int randindex = rand.nextInt(respsize);
//        int key = c.getResponses().get(randindex);
//        Action a = new Action();
//        a=a.dbLoadAction(key);
//        s.addState(a);
        return s;
    }

    public ArrayList<String> getAnswerOptionDescriptions(){
        return Action.listDescriptions(getAnsweroptions());
    }

    public ArrayList<String> getAnswerOptionStates(){
        return Action.listStates(getAnsweroptions());
    }

}