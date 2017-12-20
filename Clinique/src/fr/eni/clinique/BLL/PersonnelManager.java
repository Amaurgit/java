package fr.eni.clinique.BLL;

import java.sql.SQLException;
import java.util.List;

import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.DAL.DAOFactory;
import fr.eni.clinique.DAL.PersonnelDao;

public class PersonnelManager {

	private static PersonnelManager instance;
	private PersonnelDao personnelDao;

	private PersonnelManager() {
		personnelDao = DAOFactory.getPersonnelDAO();
	}

	public static PersonnelManager getInstance() {
		if (instance == null) {
			instance = new PersonnelManager();
		}
		return instance;
	}

	public void addPersonnel(Personnel personnel) throws BLLException {

		try {
			personnelDao.insert(personnel);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'insertion d'un personnel," + personnel, e);
		}
	}

	public List<Personnel> getPersonnel() throws BLLException {

		try {
			return personnelDao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération des personnels, e)");
		}
	}

	public Personnel getPersonnelById(int CodePers) throws BLLException {

		try {
			return personnelDao.selectById(CodePers);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération du personnel, id : " + CodePers, e);
		}
	}

	public void updatePersonnel(Personnel personnel) throws BLLException {

		try {
			personnelDao.update(personnel);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour du personnel, " + personnel, e);
		}
	}

	public void deletePersonnel(int codePersonnel) throws BLLException, SQLException {

		try {
			personnelDao.delete(codePersonnel);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un personnel, " + codePersonnel, e);
		}
	}
}
