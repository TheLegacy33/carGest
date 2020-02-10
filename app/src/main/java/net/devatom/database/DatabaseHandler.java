package net.devatom.database;

import java.util.List;
import net.devatom.classes.Vehicule;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DatabaseHandler extends SQLiteOpenHelper {
    // Version de la base de données
    private static final int DATABASE_VERSION = 8;
 
    // Nom de la base de données
    private static final String DATABASE_NAME = "carGest";
    
    public DatabaseHandler(Context pContext) {
        super(pContext, DATABASE_NAME, null, DATABASE_VERSION);
    	Log.d(getDatabaseName(), pContext.getDatabasePath(getDatabaseName()).toString());
    }
 
    // Création des tables 
    @Override
    public void onCreate(SQLiteDatabase pDatabase) {
    	DbVehiculeHandler.create(pDatabase);
    }
 
    //Mise à jour des tables
    @Override
    public void onUpgrade(SQLiteDatabase pDatabase, int oldVersion, int newVersion) {
    	DbVehiculeHandler.alter(pDatabase, oldVersion, newVersion);
    }
 
    @Override
    public void onDowngrade(SQLiteDatabase pDatabase, int oldVersion, int newVersion) {
    	DbVehiculeHandler.downgrade(pDatabase, oldVersion, newVersion);
    }
    
    public int getDbVersion(){
    	return this.getReadableDatabase().getVersion();
    }
    /**
     * CRUD(Create, Read, Update, Delete)
     */
 
    public void addVehicule(Vehicule pVehicule) {
        DbVehiculeHandler.insert(getWritableDatabase(), pVehicule);
    }
 
    public Vehicule getVehicule(String pImmat){
    	return DbVehiculeHandler.getVehicule(getReadableDatabase(), pImmat);
    }
     
    public List<Vehicule> getAllVehicules() {
        return DbVehiculeHandler.getAll(getReadableDatabase());
    }
 
   public int updateVehicule(Vehicule pVehicule) {
	   return 0;
   }
 
    public void deleteVehicule(Vehicule pVehicule) {
    }
 
    public int getNbVehicules() {
    	return DbVehiculeHandler.count(getReadableDatabase());
    }
    
    public void closeDb(){
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen()){
			db.close();
		}
    }
}	
