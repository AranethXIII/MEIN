package com.example.myapplication.Model;

import java.util.ArrayList;

public class DBdummy {//generic possible

//	public DBdummy() {
//	}

    public static ArrayList<Case> dbcases = initCasesDB2();
    public static ArrayList<Action> dbactions = initActionsDB2();

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
        String sa[] = {"Schlaganfall","Ersticken","Autounfall","Ohnmacht"};
        //					0				1			2			3
        //case description

        for(String s : sa) {
            cl.add(new Case(s));
        }

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

        Action a31 = new Action("volle genesung","absulut das richtige mmachen");
        Action a32 = new Action("nit tot","crm");
        Action a33 = new Action("nit erstickt","seitenlage");
        Action a34 = new Action("nit erfroren","zu decken");

        a3.putContinuation("testcase", 4);		//add ideces of follow up answers for testcase of aswer/Action a3
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

    static ArrayList<Case> initCasesDB2() {
        ArrayList<Case> cl = new ArrayList<Case>();
        String sa[] = {"Schlaganfall","Ersticken - Festkörper im Hals","Autounfall","Ohnmacht"};
        //					0				1			2			3
        Case c1 = new Case(sa[1]);
        cl.add(c1);
        c1.responses.add(0);
        c1.responses.add(1);
        c1.responses.add(2);
        c1.responses.add(3);


        return cl;
    }

    static ArrayList<Case> initCasesDB3() {
        ArrayList<Case> cl = new ArrayList<Case>();
        String sa[] = {"Schlaganfall","Ersticken - Festkörper im Hals","Autounfall","Ohnmacht"};
        //					0				1			2			3
        //case description

        for(String s : sa) {
            cl.add(new Case(s));
        }
        return cl;
    }

    static ArrayList<Action> initActionsDB2() {
        ArrayList<Action> al = new ArrayList<Action>();

        String sfa = "falsche Antwort";
        String sa[] = {"abwarten auf besserung","zu trinken geben","ein video machen"};
//									0					1					2
        for(String s : sa) {
            al.add(new Action(sfa,s));
        }

        String sr = "richtig - Festkörper ist aber weitehin im Hals";
        Action a1 = new Action(sr,"fünf mal zwischen Schulterblätter klopfen");
        al.add(a1);
        //	3
        a1.putContinuation("Ersticken - Festkörper im Hals",4);
        a1.putContinuation("Ersticken - Festkörper im Hals",5);
        a1.putContinuation("Ersticken - Festkörper im Hals",6);
        a1.putContinuation("Ersticken - Festkörper im Hals",7);
        //====================
        String sa2[] = {"feste auf den Rücken schlagen","aufgeben hilft alles nichts","weiter des auf den Rücken klopfen"};
        //					4									5								6
        for(String s : sa2) {
            al.add(new Action(sfa,s));
        }

        String sr2 = "nach jedem schlag ueberpruefen";
        Action a2 = new Action(sr,sr2);
        al.add(a2);
        //	7
        a2.putContinuation("Ersticken - Festkörper im Hals",8);
        a2.putContinuation("Ersticken - Festkörper im Hals",9);
        a2.putContinuation("Ersticken - Festkörper im Hals",10);
        a2.putContinuation("Ersticken - Festkörper im Hals",11);
//==================
        String sa3[] = {"mit der Hand versuchen zu entfernen","über den Rücken streichen zur beruhigung","Mund zu Mund beatmung"};
//										8								9										10
        for(String s : sa3) {
            al.add(new Action(sfa,s));
        }

        String sr3 = "Notruf rufen";
        Action a3 = new Action("richtig Person gerettet",sr3);
        al.add(a3);	//11

//		a3.putContinuation("Ersticken - Festkörper im Hals",8);
//		a3.putContinuation("Ersticken - Festkörper im Hals",9);
//		a3.putContinuation("Ersticken - Festkörper im Hals",10);
//		a3.putContinuation("Ersticken - Festkörper im Hals",11);
        //==========


        return al;
    }

    static ArrayList<Action> initActionsDB3() {
        ArrayList<Action> al = new ArrayList<Action>();
        String sfa = "falsche Antwort";

        Action a1 = new Action(sfa,"einfach weitergehen");
        al.add(a1);
        Action a2 = new Action(sfa,"gaffen");
        al.add(a2);
        Action a3 = new Action("richtig","hilfe leisten");
        al.add(a3);
        Action a4 = new Action(sfa,"nen andere machts schon");
        al.add(a4);


        return al;
    }

}
