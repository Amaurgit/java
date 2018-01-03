package fr.eni.rallye.test;

public class Concurrent {

	private String nom;
	private String prenom;
	private String nationalite;

	public Concurrent(String nom, String prenom, String nationalite) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	public String infosConcurrent() {
		return "		Concurrent [nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + "]"
				+ System.lineSeparator();

	}

}
