package fr.eni.clinique.BLL;

import java.nio.charset.Charset;

public class Gestion_Connexion {

	PersonnelManager pm = PersonnelManager.getInstance();

	public boolean isVetOk(int codePersonnel, String mdp) throws BLLException {

		if (pm.getPersonnelById(codePersonnel).getRole().equals("vet")
				&& pm.getPersonnelById(codePersonnel).getMotPasse().equals(mdp)) {
			return true;
		}
		return false;
	}

	public boolean isSecOk(int codePersonnel, String mdp) throws BLLException {

		if (pm.getPersonnelById(codePersonnel).getRole().equals("sec")
				&& pm.getPersonnelById(codePersonnel).getMotPasse().equals(mdp)) {
			return true;
		}
		return false;
	}

	public boolean isAdmOk(int codePersonnel, String mdp) throws BLLException {

		if (pm.getPersonnelById(codePersonnel).getRole().equals("adm")
				&& pm.getPersonnelById(codePersonnel).getMotPasse().equals(mdp)) {
			return true;
		}
		return false;
	}
}
