package fr.eni.clinique.BO;

public class Animal {

	private int CodeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private String espece;
	private long codeClient;
	private String tatouage;
	private String antecedents;
	private Boolean archive;
	private Client client;

	public Animal(String nomAnimal, String sexe, String couleur, String race, String espece, long codeClient,
			String tatouage, String antecedents, Boolean archive, Client client) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
		this.client = client;
	}

	public Animal(int CodeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece,
			long codeClient, String tatouage, String antecedents, Boolean archive, Client client) {
		super();
		this.CodeAnimal = CodeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
		this.client = client;
	}

	public Animal() {

	}

	public int getCodeAnimal() {
		return CodeAnimal;
	}

	public void setCodeAnimal(int idcodeAnimal) {
		this.CodeAnimal = idcodeAnimal;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	public long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(long codeClient) {
		this.codeClient = codeClient;
	}

	public String getTatouage() {
		return tatouage;
	}

	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}

	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return nomAnimal;
	}

}
