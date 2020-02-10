package net.devatom.classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Vehicule implements Serializable{
	private static final long serialVersionUID = -1418300503868535731L;
	private Calendar dateImmat;
	private String immat;
	private int kilometrage;
	private List<Entretien> entretiens;
	private String telProprio; 
	
	public Vehicule() {
		super();
		dateImmat = null;
		immat = "";
		kilometrage = 0;
		entretiens = new ArrayList<Entretien>();
		setTelProprio("");
	}

	public Vehicule(Calendar pDateImmat, String pImmat, int pKilometrage) {
		this();
		
		setDateImmat(pDateImmat);
		this.immat = pImmat;
		this.kilometrage = pKilometrage;
	}
	
	public Vehicule(String pDateImmat, String pImmat, int pKilometrage) {
		this();
		
		setDateImmat(pDateImmat);
		this.immat = pImmat;
		this.kilometrage = pKilometrage;
	}
	
	public Calendar getDateImmat() {
		return dateImmat;
	}
	
	public String getDateImmatToStr() {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);  
		String strDteImmat=formatter.format(getDateImmat().getTime());
		return strDteImmat;
	}
	
	public String getImmat() {
		return immat;
	}
	
	public int getKilometrage() {
		return kilometrage;
	}
	
	public void setDateImmat(Calendar pDateImmat) {
		this.dateImmat = pDateImmat;
	}
	
	public void setDateImmat(String pDateImmat){
		this.dateImmat = Calendar.getInstance(Locale.FRANCE);
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE); 
		try {
			this.dateImmat.setTime(formatter.parse(pDateImmat));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setImmat(String immat) {
		this.immat = immat;
	}
	
	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}
	
	public void addEntretien(Entretien pEntretien){
		this.entretiens.add(pEntretien);
	}
	
	public ArrayList<HashMap<String, Entretien>> getEntretiens(){
		ArrayList<HashMap<String, Entretien>> listEntretiens = new ArrayList<HashMap<String, Entretien>>();
		HashMap<String, Entretien> unEntretien;
		
		for (Entretien ent : entretiens){
			unEntretien = new HashMap<String, Entretien>();
			unEntretien.put("entretien", ent);
			listEntretiens.add(unEntretien);
		}
		return listEntretiens;
	}

	@Override
	public String toString() {
		return this.immat + " (" + String.valueOf(this.kilometrage) + " km)";
	}

	public String getTelProprio() {
		return telProprio;
	}

	public void setTelProprio(String telProprio) {
		this.telProprio = telProprio;
	}
}
