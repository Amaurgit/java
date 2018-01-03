/**
 * 
 */
package fr.eni.rallye.test;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author bmartin
 *
 */
public class ModuleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * définition d'un Rallye
		 */
		Rallye rallyeCorse = new Rallye("Corse FR", new GregorianCalendar(2012, 05, 10).getTime());
		/*
		 * associer les spéciales
		 */
		Speciale es;
		es = new Speciale("ES1 (Pénitencier Coti – Agosta plage)",
				new GregorianCalendar(2012, 05, 10, 14, 13, 00).getTime(), 25.89, Type_Epreuve.Qualif);
		rallyeCorse.ajouterSpeciale(es);
		es = new Speciale("ES2 (Sarrola – Plage de Liamone)", new GregorianCalendar(2012, 05, 10, 16, 19, 00).getTime(),
				26.70, Type_Epreuve.Speciale);
		rallyeCorse.ajouterSpeciale(es);
		es = new Speciale("ES3 (Le Fangu – ND de la Serra)", new GregorianCalendar(2012, 05, 11, 8, 27, 00).getTime(),
				27.53, Type_Epreuve.Speciale);
		rallyeCorse.ajouterSpeciale(es);
		System.out.println("**Composition du Rallye de Corse");
		System.out.println("********************************");
		System.out.println(rallyeCorse.infosRallye(true));

		/*
		 * définir les équipes
		 */
		Equipe eqCitroen = new Equipe("Citroen", "FR", true);
		Equipage loeb = new Equipage(1, "LOEB", "Sebastien", "FR", "ELENA", "Daniel", "FR");
		eqCitroen.ajouterEquipage(loeb);
		eqCitroen.ajouterEquipage(new Equipage(2, "HIRVONEN", "Mikko", "FL", "LEHTINEN", "Jarma", "FL"));
		Equipe eqFord = new Equipe("Ford", "USA", true);
		eqFord.ajouterEquipage(new Equipage(3, "PILOTE", "Pilote", "USA", "COPILOTE", "Copilote", "USA"));

		System.out.println("**Composition de l'équipe Citroen");
		System.out.println("*********************************");
		System.out.println(eqCitroen.infosEquipe());
		System.out.println("**Composition de l'équipe Ford");
		System.out.println("******************************");
		System.out.println(eqFord.infosEquipe());

		/*
		 * saisir les résultats par spéciale
		 */
		Resultat resultat = null;
		resultat = new Resultat(eqCitroen.getEquipage(1), rallyeCorse.getSpeciale("ES2 (Sarrola – Plage de Liamone)"),
				new GregorianCalendar(0, 0, 0, 0, 3, 33).getTime());
		resultat = new Resultat(eqCitroen.getEquipage(2), rallyeCorse.getSpeciale("ES2 (Sarrola – Plage de Liamone)"),
				new GregorianCalendar(0, 0, 0, 0, 3, 43).getTime());
		resultat = new Resultat(eqFord.getEquipage(3), rallyeCorse.getSpeciale("ES2 (Sarrola – Plage de Liamone)"),
				new GregorianCalendar(0, 0, 0, 0, 3, 40).getTime());

		resultat = new Resultat(eqCitroen.getEquipage(1), rallyeCorse.getSpeciale("ES3 (Le Fangu – ND de la Serra)"),
				new GregorianCalendar(0, 0, 0, 0, 4, 20).getTime());
		resultat = new Resultat(eqCitroen.getEquipage(2), rallyeCorse.getSpeciale("ES3 (Le Fangu – ND de la Serra)"),
				new GregorianCalendar(0, 0, 0, 0, 4, 25).getTime());
		resultat = new Resultat(eqFord.getEquipage(3), rallyeCorse.getSpeciale("ES3 (Le Fangu – ND de la Serra)"),
				new GregorianCalendar(0, 0, 0, 0, 4, 16).getTime());

		// /*
		// * visualiser le classement par spéciale
		// */
		System.out.println("**Classement de la spéciale ES2");
		System.out.println("*******************************");
		Resultat[] resultats = rallyeCorse.getSpeciale("ES2 (Sarrola – Plage de Liamone)").getClassement();
		StringBuffer buffer = new StringBuffer();
		for (Resultat result : resultats) {
			if (result != null)
				buffer.append(String.format("%s", result.infosResultat()));
		}
		System.out.println(buffer.toString());

		System.out.println("**Classement de la spéciale ES3");
		System.out.println("*******************************");
		resultats = rallyeCorse.getSpeciale("ES3 (Le Fangu – ND de la Serra)").getClassement();
		buffer = new StringBuffer();
		for (Resultat result : resultats) {
			if (result != null)
				buffer.append(String.format("%s", result.infosResultat()));
		}
		System.out.println(buffer.toString());

		/*
		 * visualiser le classement general
		 */
		System.out.println("**Classement general du Rallye de Corse");
		System.out.println("***************************************");
		Classement[] classementGeneral = rallyeCorse.getClassementGeneral();
		buffer = new StringBuffer();
		for (Classement result : classementGeneral) {
			if (result != null)
				buffer.append(String.format("%s", result.infosClassement()));
		}
		System.out.println(buffer.toString());

		System.out.println("Appuyez sur entrée pour sortir du test...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();

	}

}
