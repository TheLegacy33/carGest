package net.devatom.classes;

public class Filtre {
	private TypeFiltre type;
	private DureeVie dureeVie;
	
	public Filtre() {
		this.type = null;
		this.dureeVie = new DureeVie();
	}
	
	public Filtre(TypeFiltre pType, int pDureeMois, long pDureeKil){
		this.type = pType;
		this.setDureeVie(pDureeMois, pDureeKil);
	}
	
	public TypeFiltre getType() {
		return type;
	}

	public void setType(TypeFiltre pType) {
		this.type = pType;
	}
	
	public DureeVie getDureeVie() {
		return dureeVie;
	}
	
	public void setDureeVie(int pDureeMois, long pDureeKil) {
		this.dureeVie = new DureeVie(pDureeMois, pDureeKil);
	}

	public String toString(){
		return "Filtre : " + this.type.name() + ", " + this.getDureeVie().getDuree();
	}
	
	private class DureeVie{
		private int dureeEnMois;
		private long dureeEnKilometres;
		
		public DureeVie() {
			this.dureeEnMois = 0;
			this.dureeEnKilometres = 0;
		}

		public DureeVie(int pDureeMois, long pDureeKil) {
			this.dureeEnKilometres = pDureeKil;
			this.dureeEnMois = pDureeMois;
		}
		
		public String getDuree(){
			String retVal;
			retVal = String.valueOf(this.dureeEnMois) + " mois ou " + String.valueOf(this.dureeEnKilometres) + " kms";
			
			return retVal;
		}
	}
}
