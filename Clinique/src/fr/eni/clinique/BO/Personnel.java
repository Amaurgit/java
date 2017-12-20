package fr.eni.clinique.BO;

public class Personnel {

	private int CodePers;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;

	public Personnel(int CodePers, String nom, String motPasse, String role, boolean archive) {
		this.CodePers = CodePers;
		this.nom = nom;
		this.motPasse = motPasse;
		this.archive = archive;
	}

	public Personnel(String nom, String motPasse, String role, boolean archive) {
		this.nom = nom;
		this.motPasse = motPasse;
		this.archive = archive;
	}

	public Personnel() {

	}

	public int getCodePers() {
		return CodePers;
	}

	public void setCodePers(int CodePers) {
		this.CodePers = CodePers;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public boolean getArchive() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return nom;
	}

}
