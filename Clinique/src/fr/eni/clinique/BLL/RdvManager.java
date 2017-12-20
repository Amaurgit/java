package fr.eni.clinique.BLL;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.DAL.DAOFactory;
import fr.eni.clinique.DAL.RdvDao;

public class RdvManager {
	private static RdvManager instance;
	private RdvDao daoRdv;

	private RdvManager() {
		daoRdv = DAOFactory.getRdvDAO();
	}

	public static RdvManager getInstance() {
		if (instance == null) {
			instance = new RdvManager();
		}
		return instance;
	}

	public List<Rdv> getRdv() throws BLLException {
		try {
			return daoRdv.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération du RDV", e);
		}
	}

	public void addClient(Rdv rdv) throws BLLException {

		try {
			daoRdv.insert(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'insertion du RDV", e);
		}
	}

	public void validerRdv(Rdv rdv) throws BLLException {
	}

	public void updateRdv(Rdv rdv) throws BLLException {
		try {
			validerRdv(rdv);
			daoRdv.update(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour d'un RDV:" + rdv, e);
		}
	}

	public void removeRdv(Personnel veto, Animal animal, Timestamp date) throws BLLException {
		try {
			daoRdv.delete(veto, animal, date);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un RDV :" + animal.getCodeAnimal() + veto.getCodePers(),
					e);
		}
	}

	public List<Rdv> selectAllRdvByVet(Personnel personnel) throws BLLException, DALException {

		try {
			return daoRdv.selectAllRdvByVet(personnel);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un RDV, id:" + personnel, e);
		}

	}

	public List<Rdv> selectAllRdvByVetAndDate(Personnel personnel, Date date) throws DALException, BLLException {

		try {
			return daoRdv.selectAllRdvByVetAndDate(personnel, date);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération des RDV par veto et date :" + personnel + date, e);
		}

	}

	public void insert(Rdv rdv) throws BLLException {
		try {
			daoRdv.insert(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'ajout d'un RDV, id:" + rdv, e);
		}
	}
}
