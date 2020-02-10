package net.devatom.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.devatom.classes.Vehicule;
import net.devatom.classes.parcAutomobile;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class DbVehiculeHandler{
    private static final String TABLE_VEHICULES = "vehicules";

    private static final String KEY_ID = "veh_id";
    private static final String KEY_IMMAT = "veh_immat";
    private static final String KEY_DATEIMMAT = "veh_dateimmat";
    private static final String KEY_KILOMETRAGE = "veh_kilometrage";
    
    private static String SQLQuery = "";
	public static void create(SQLiteDatabase pDatabase) {
		Log.d("CarGest Database", "Création de la base");
        SQLQuery = "CREATE TABLE " + TABLE_VEHICULES + "(";
        SQLQuery += KEY_ID + " INTEGER PRIMARY KEY,";
        SQLQuery += KEY_IMMAT + " VARCHAR(20),";
        SQLQuery += KEY_DATEIMMAT + " VARCHAR(20),";
        SQLQuery += KEY_KILOMETRAGE + " INTEGER";
        SQLQuery += ")";
        pDatabase.execSQL(SQLQuery);
        
        Calendar dteImmat = Calendar.getInstance();
		dteImmat.set(2008, Calendar.APRIL, 1);
		Vehicule maGolf = new Vehicule(dteImmat, "7458TM33", 143200);
		
		dteImmat.set(1998, Calendar.MAY, 20);
		Vehicule maR21 = new Vehicule(dteImmat, "9482MG33", 225152);

        Log.d(parcAutomobile.maDatabase.getDatabaseName(), "Inserting ..");
        insert(pDatabase, maGolf);       
        insert(pDatabase, maR21);
	}
	
	public static void alter(SQLiteDatabase pDatabase, int oldVersion, int newVersion) {
		Log.d(parcAutomobile.maDatabase.getDatabaseName(), "Mise à jour de la base");
		if (oldVersion != newVersion){
			drop(pDatabase);
			create(pDatabase);
		}
	}
	
	public static void downgrade(SQLiteDatabase pDatabase, int oldVersion, int newVersion) {
		Log.d(parcAutomobile.maDatabase.getDatabaseName(), "Downgrade de la base");
	}


	public static void drop(SQLiteDatabase pDatabase) {
		pDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICULES);
	}

	public static void insert(SQLiteDatabase pDatabase, Vehicule pVehicule) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, getNewId(pDatabase));
        values.put(KEY_IMMAT, pVehicule.getImmat());
        values.put(KEY_DATEIMMAT, pVehicule.getDateImmatToStr());
        values.put(KEY_KILOMETRAGE, pVehicule.getKilometrage());
 
        pDatabase.insert(TABLE_VEHICULES, null, values);
	}

	public static void update(SQLiteDatabase pDatabase) {
	}
	
	public static void delete(SQLiteDatabase pDatabase) {
	}

	public static Vehicule getVehicule(SQLiteDatabase pDatabase, String pImmat) {
		Vehicule unVehicule = null;
		SQLQuery = "SELECT " + KEY_IMMAT + ", " + KEY_DATEIMMAT + ", " + KEY_KILOMETRAGE + " "; 
        SQLQuery += "FROM " + TABLE_VEHICULES + " WHERE " + KEY_IMMAT + " = '" + pImmat + "'";
		Cursor SQLResult = pDatabase.rawQuery(SQLQuery, null);
		if (SQLResult != null){
			SQLResult.moveToFirst();
			unVehicule = new Vehicule();
            unVehicule.setImmat(SQLResult.getString(0).trim());
            unVehicule.setDateImmat(SQLResult.getString(1).trim());
            unVehicule.setKilometrage(SQLResult.getInt(2));
		}
		SQLResult.close();
		return unVehicule;
	}

	public static List<Vehicule> getAll(SQLiteDatabase pDatabase) {
		List<Vehicule> vehiculesList = new ArrayList<Vehicule>();
        SQLQuery = "SELECT " + KEY_IMMAT + ", " + KEY_DATEIMMAT + ", " + KEY_KILOMETRAGE + " "; 
        SQLQuery += "FROM " + TABLE_VEHICULES;

        Cursor SQLResult = pDatabase.rawQuery(SQLQuery, null);
 
        if (SQLResult.moveToFirst()) {
            do {
                Vehicule unVehicule = new Vehicule();
                unVehicule.setImmat(SQLResult.getString(0).trim());
                unVehicule.setDateImmat(SQLResult.getString(1).trim());
                unVehicule.setKilometrage(Integer.parseInt(SQLResult.getString(2).trim()));
                vehiculesList.add(unVehicule);
            } while (SQLResult.moveToNext());
        }
        SQLResult.close();
        return vehiculesList;
	}

	private static int getNewId(SQLiteDatabase pDatabase){
		Cursor SQLResult = pDatabase.rawQuery("SELECT IFNULL(MAX(veh_id), 0) + 1 FROM " + TABLE_VEHICULES, null);
		int retVal = 0;
		if (SQLResult != null){
			SQLResult.moveToFirst();
			retVal = SQLResult.getInt(0);
			SQLResult.close();
		}
		Log.d(parcAutomobile.maDatabase.getDatabaseName(), "New Id Vehicule : " + String.valueOf(retVal));
		return retVal;
	}
	
	public static int count(SQLiteDatabase pDatabase) {
        return pDatabase.rawQuery("SELECT * FROM " + TABLE_VEHICULES, null).getCount();
	}
}
