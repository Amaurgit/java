package fr.eni.rallye.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resultat {
	private Date temps;
	private Equipage equipage;
	private Speciale speciale;

	public Resultat(Equipage equipage, Speciale speciale, Date temps) {
		super();
		this.temps = temps;
		this.equipage = equipage;
		this.speciale = speciale;
		speciale.AjouterResultat(this);

	}

	public Date getTemps() {
		return temps;
	}

	public Equipage getEquipage() {
		return equipage;
	}

	public Speciale getSpeciale() {
		return speciale;
	}

	public String infosResultat() {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

		return speciale.infosSpeciale() + equipage.infosEquipage() + "Resultat [temps=" + sdf.format(temps) + "]"
				+ System.lineSeparator();
	}

}
