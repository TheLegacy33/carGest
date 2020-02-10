package net.devatom.classes;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

public class Entretien implements Serializable{
	private static final long serialVersionUID = -1629253338538165707L;

	private Calendar dateEntretien;
	private int kilometrage;
	private HashMap<TypeFiltre, Boolean> changedFilters;
	private boolean changedOil;
	
	public Entretien() {
		this.dateEntretien = Calendar.getInstance();
		this.kilometrage = 0;
		this.changedFilters = new HashMap<TypeFiltre, Boolean>();
		
		for (TypeFiltre typf : TypeFiltre.values()){
			this.changedFilters.put(typf, Boolean.FALSE);
		}
		
		/*this.changedFilters.put(TypeFiltre.AIR, Boolean.FALSE);
		this.changedFilters.put(TypeFiltre.CARBURANT, Boolean.FALSE);
		this.changedFilters.put(TypeFiltre.HUILE, Boolean.FALSE);
		this.changedFilters.put(TypeFiltre.HABITACLE, Boolean.FALSE);*/
		this.changedOil = false;
	}
	
	public Entretien(Calendar pDateEntretien, int pKilometrage){
		this();
		this.dateEntretien = Calendar.getInstance();
		this.dateEntretien = pDateEntretien;
		this.kilometrage = pKilometrage;
	}
	
	public Entretien(String pDateEntretien, int pKilometrage){
		this();
		this.dateEntretien = Calendar.getInstance();
		setDateEntretien(pDateEntretien);
		this.kilometrage = pKilometrage;
	}

	public void changeFilter(TypeFiltre pFiltre){
		this.changedFilters.put(pFiltre, Boolean.TRUE);
	}
	
	public void changeOil(){
		this.changedOil = true;
	}
	
	public boolean isOilChanged(){
		return this.changedOil;
	}
	
	public boolean isFilterChanged(TypeFiltre pFiltre){
		return this.changedFilters.get(pFiltre);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap<TypeFiltre, Boolean>> getFiltersStatus(){
		ArrayList<HashMap<TypeFiltre, Boolean>> filtres = new ArrayList<HashMap<TypeFiltre, Boolean>>();
		for (Entry<TypeFiltre, Boolean> item : this.changedFilters.entrySet()){
			filtres.add((HashMap<TypeFiltre, Boolean>) item);
		}
		return filtres;
	}
	
	public Calendar getDateEntretien() {
		return dateEntretien;
	}
	
	public String getDateEntretienToStr() {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);  
		String strDteImmat=formatter.format(getDateEntretien().getTime());
		return strDteImmat;
	}

	public int getKilometrage() {
		return kilometrage;
	}
	
	public void setDateEntretien(Calendar pDateEntretien) {
		this.dateEntretien = pDateEntretien;
	}
	
	public void setDateEntretien(String pDateEntretient){
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE); 
		try {
			this.dateEntretien.setTime(formatter.parse(pDateEntretient));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setKilometrage(int pKilometrage) {
		this.kilometrage = pKilometrage;
	}
	
	@Override
	public String toString() {
		return "Le " + getDateEntretienToStr() + " à " + this.kilometrage + " km";
	}
}
