package com.example.myapplication.Model;

import java.util.ArrayList;

public class DBdummy {//generic possible

//	public DBdummy() {
//	}

    public static ArrayList<Case> dbcases = initCasesDB();
    public static ArrayList<Action> dbactions = initActionsDB();

    public static Action getAction(int dbkey) {
        return dbactions.get(dbkey);
    }

    public static int countcases() {
        if (dbcases!=null)return dbcases.size();
        return 0;
    }

    public static int countaction() {
        if (dbactions!=null)return dbactions.size();
        return 0;
    }

    public static Case getCase(int dbkey) {
        return dbcases.get(dbkey);
    }

    public static ArrayList<Case> getListCase(ArrayList<Integer> dbkeys) {

        ArrayList<Case> al = new ArrayList<>();

        for(int i : dbkeys) {
            try {
                al.add(dbcases.get(i));
            }
            catch(IndexOutOfBoundsException e) {}
        }

        return al;
    }

    public static ArrayList<Action> getListAction(ArrayList<Integer> dbkeys) {

        ArrayList<Action> al = new ArrayList<>();

        for(int i : dbkeys) {
            try {
                al.add(dbactions.get(i));
            }
            catch(IndexOutOfBoundsException e) {}
        }

        return al;
    }


    //===========normal getter setter

    public static ArrayList<Case> getDbcases() {
        return dbcases;
    }

    public static void setDbcases(ArrayList<Case> dbcases) {
        DBdummy.dbcases = dbcases;
    }

    public static ArrayList<Action> getDbactions() {
        return dbactions;
    }

    public static void setDbactions(ArrayList<Action> dbactions) {
        DBdummy.dbactions = dbactions;
    }

    public static void addAction(Action a) {
        dbactions.add(a);
    }

    public static void addCase(Case c) {
        dbcases.add(c);
    }

//	public static ArrayList<String> getAllCaseID() {
//		return null;
//	}

//	public static HashMap<String, ArrayList<String>> getActionCotinuationIDMap(String dbkey) {
//		return null;
//		//returns
//	}

//	public static HashMap<String, ArrayList<Action>> getActionCotinuationMap(String dbkey) {
//		return null;
//		// getActionCotinuationIDMap()
//		// build map
//	}

    static ArrayList<Case> initCasesDB() {
        ArrayList<Case> c = new ArrayList<Case>();

        //TODO init the db
        String sa[] = {"case1","case2","case3"};
        //case description

        for(String s : sa) {
            c.add(new Case(s));
        }

        return c;
    }

    static ArrayList<Action> initActionsDB() {
        ArrayList<Action> a = new ArrayList<Action>();

        //TODO init the db

        return a;
    }

}

