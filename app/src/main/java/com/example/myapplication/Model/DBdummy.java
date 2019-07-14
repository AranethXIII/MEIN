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
        ArrayList<Case> cl = new ArrayList<Case>();

//		//TODO init the db
//		String sa[] = {"case1","case2","case3"};
//		//case description
//
//		for(String s : sa) {
//			c.add(new Case(s));
//		}

        Case c1 = new Case("testcase");

        cl.add(c1);
        c1.responses.add(0);	// add indeces of action db array for answeroptions
        c1.responses.add(1);
        c1.responses.add(2);
        c1.responses.add(3);

        return cl;
    }

    static ArrayList<Action> initActionsDB() {
        ArrayList<Action> al = new ArrayList<Action>();


        Action a1 = new Action("is tot","einfach weitergehen");
        Action a2 = new Action("is tot","gaffen");
        Action a3 = new Action("lebt noch","hilfe leisten");
        Action a4 = new Action("is tot","nen andere machts schon");

        Action a31 = new Action("volle genesung","absulut das richtige machen");
        Action a32 = new Action("nit tot","crm");
        Action a33 = new Action("nit erstickt","seitenlage");
        Action a34 = new Action("nit erfroren","zu decken");

        a3.putContinuation("testcase", 4);		//add indeces of follow up answers for testcase of answer/Action a3
        a3.putContinuation("testcase", 5);
        a3.putContinuation("testcase", 6);
        a3.putContinuation("testcase", 7);

        al.add(a1);
        al.add(a2);
        al.add(a3);
        al.add(a4);
        al.add(a31);
        al.add(a32);
        al.add(a33);
        al.add(a34);

        return al;
    }

}
