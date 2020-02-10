package net.devatom.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import net.devatom.database.DatabaseHandler;

public abstract class parcAutomobile {
	
	static List<Vehicule> mesVehicules = null;
	
	public static DatabaseHandler maDatabase = null;
	
	
	public static void initVehicules(Context pContext){
		maDatabase = new DatabaseHandler(pContext);
		mesVehicules = new ArrayList<Vehicule>();

		Calendar dteImmat = Calendar.getInstance();
		dteImmat.set(2008, Calendar.APRIL, 1);
		Vehicule maGolf = new Vehicule(dteImmat, "7458TM33", 143200);
		
		dteImmat.set(1998, Calendar.MAY, 20);
		Vehicule maR21 = new Vehicule(dteImmat, "9482MG33", 225152);


		mesVehicules.add(maGolf);
		
		Entretien unEntretien = null;
		
		unEntretien = new Entretien("25/04/2008", 51000);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("12/07/2009", 68955);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("10/08/2010", 85124);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("12/10/2011", 99960);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("02/09/2012", 111000);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("06/09/2013", 123125);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("25/10/2014", 131250);
		maGolf.addEntretien(unEntretien);

		unEntretien = new Entretien("12/08/2014", 140000);
		maGolf.addEntretien(unEntretien);

		/* Pour la R21 */
		unEntretien = new Entretien("25/04/1999", 151000);
		maR21.addEntretien(unEntretien);

		unEntretien = new Entretien("12/07/2001", 168955);
		maR21.addEntretien(unEntretien);

		unEntretien = new Entretien("10/08/2002", 185124);
		maR21.addEntretien(unEntretien);

		unEntretien = new Entretien("12/10/2004", 199960);
		maR21.addEntretien(unEntretien);

		unEntretien = new Entretien("02/09/2006", 211000);
		maR21.addEntretien(unEntretien);

		unEntretien = new Entretien("06/09/2008", 223125);
		maR21.addEntretien(unEntretien);

		mesVehicules.add(maR21);
	}
	
	public static Vehicule getVehicule(String pImmat){
		Vehicule retVal = null; 
		retVal = maDatabase.getVehicule(pImmat);
		return retVal;
	}
	
	public static List<Vehicule> getVehicules(){
		return maDatabase.getAllVehicules();
	}
}
