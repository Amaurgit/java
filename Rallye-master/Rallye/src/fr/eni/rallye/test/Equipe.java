package fr.eni.rallye.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Equipe {
	private String nom;
	private String nationalite;
	private Boolean constructeur;
	private List<Equipage> equipages = new ArrayList<Equipage>(3);

	public Equipe(String nom, String nationalite, Boolean constructeur) {
		super();
		this.nom = nom;
		this.nationalite = nationalite;
		this.constructeur = constructeur;
	}

	public void ajouterEquipage(Equipage equipage) {

		equipages.add(equipage);
	}

	public Equipage getEquipage(int dossard) {

		for (int i = 0; i < equipages.size(); i++) {
			if (equipages.get(i).getDossard() == dossard) {

			}
			return equipages.get(i);
		}
		return null;
	}

	public String infosEquipe() {
		String string = "Equipe [nom=" + nom + ", nationalite=" + nationalite + ", constructeur=" + constructeur + "]"
				+ System.lineSeparator();

		for (Equipage equipage : equipages) {
			string += equipage.infosEquipage();

		}
		return string;

	}

}
