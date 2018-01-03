package fr.eni.rallye.test;

import java.util.Date;

public class Classement {

	private Date cumulTemps;
	private Equipage equipage;

	public Classement(Date cumulTemps, Equipage equipage) {
		super();
		this.cumulTemps = cumulTemps;
		this.equipage = equipage;
	}

	public Date getCumulTemps() {
		return cumulTemps;
	}

	public String infosClassement() {
		return "Classement [" + equipage.infosEquipage() + "cumulTemps=" + cumulTemps + "]";
	}

}
