package fr.eni.rallye.test;

import java.awt.Window.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Speciale {

	private Date jour_Heure;
	private double distance;
	private String nom;
	private Type_Epreuve type;
	private List<Resultat> resultats = new ArrayList<>(50);

	public Speciale(String nom, Date jour_Heure, double distance, Type_Epreuve type) {
		super();
		this.jour_Heure = jour_Heure;
		this.distance = distance;
		this.nom = nom;
		this.type = type;
	}

	public void AjouterResultat(Resultat resultat) {
		resultats.add(resultat);

	}

	public String getNom() {
		return nom;
	}

	public String infosSpeciale() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY hh:mm");
		return "	Speciale [Nom=" + nom + ", jour_Heure= " + sdf.format(jour_Heure) + ", distance=" + distance
				+ ", type=" + type + "]" + System.lineSeparator();
	}

	public Resultat[] getClassement() {
		Date cumulTemps1;
		Date cumulTemps2;
		Date cumulTemps3;
		Resultat[] resultatsTab = new Resultat[resultats.size()];
		resultats.sort(Comparator.comparing(Resultat::getTemps));
		for (int i = 0; i < resultats.size(); i++) {
			resultatsTab[i] = resultats.get(i);
			if (resultats.get(i).getEquipage().getDossard() == 1) {

			}
		}
		Classement (resultats.get(i).getEquipage(),resultats.get(i).getTemps())


		return resultatsTab;
	}

}
