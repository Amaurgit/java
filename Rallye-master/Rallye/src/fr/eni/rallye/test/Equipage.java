package fr.eni.rallye.test;

public class Equipage {
	private int dossard;
	private Concurrent pilote;
	private Concurrent coPilote;

	public Equipage(int dossard, String prenomPilote, String nomCopilote, String nationalitePilote,
			String prenomCopilote, String nomPilote, String nationaliteCopilote) {
		this.dossard = dossard;
		pilote = new Concurrent(nomPilote, prenomPilote, nationalitePilote);
		coPilote = new Concurrent(nomCopilote, prenomCopilote, nationaliteCopilote);

	}

	public int getDossard() {
		return dossard;
	}

	public String infosEquipage() {
		return "	Equipage [dossard=" + dossard + "]" + System.lineSeparator() + pilote.infosConcurrent()
				+ coPilote.infosConcurrent() ;
	}

}
