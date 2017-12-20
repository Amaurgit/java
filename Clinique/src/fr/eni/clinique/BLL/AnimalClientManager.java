package fr.eni.clinique.BLL;

import java.util.List;

import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.DAL.AnimalDao;
import fr.eni.clinique.DAL.ClientDao;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.DAL.DAOFactory;

public class AnimalClientManager {

	private static AnimalClientManager instance;
	private AnimalDao daoAnimal;
	private ClientDao daoClient;

	public AnimalClientManager() {
		daoAnimal = DAOFactory.getAnimalDAO();
		daoClient = DAOFactory.getClientDAO();
	}

	public static AnimalClientManager getInstance() {
		if (instance == null) {
			instance = new AnimalClientManager();
		}
		return instance;
	}

	// CLIENT

	private void validerClient(Client client) {
		// TODO Auto-generated method stub

	}

	public List<Client> getClient() throws BLLException {
		try {
			return daoClient.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération du client", e);
		}
	}

	public void addClient(Client client) throws BLLException {
		try {
			validerClient(client);
			daoClient.insert(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'ajout d'un client:" + client, e);
		}
	}

	public void updateClient(Client client) throws BLLException {
		try {
			validerClient(client);
			daoClient.update(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour d'un client:" + client, e);
		}
	}

	public void removeClient(int codeClient) throws BLLException {
		try {
			daoClient.delete(codeClient);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un client, id:" + codeClient, e);
		}
	}

	public List<Client> getClientByName(String motCle) throws BLLException {

		try {
			return daoClient.selectByMotCle(motCle);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un client, id:" + motCle, e);
		}
	}

	public Client getClientById(int codeClient) throws BLLException {
		try {
			return daoClient.selectById(codeClient);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un article, id:" + codeClient, e);
		}
	}
	// ANIMAUX

	private void validerAnimal(Animal animal) {
		// TODO Auto-generated method stub

	}

	public List<Animal> getAnimalsbyId(int codeClient) throws DALException {

		return daoAnimal.selectAnimalsById(codeClient);
	}

	public List<Animal> getAnimal() throws BLLException {
		try {
			return daoAnimal.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de l'animal", e);
		}
	}

	public void addAnimal(Animal animal, Integer codeClient) throws BLLException {
		try {
			validerAnimal(animal);
			daoAnimal.insert(animal, codeClient);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'ajout d'un animal:" + animal, e);
		}
	}

	public void updateAnimal(Animal animal) throws BLLException {
		try {
			validerAnimal(animal);
			daoAnimal.update(animal);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour d'un animal:" + animal, e);
		}
	}

	public void removeAnimal(int codeAnimal) throws BLLException {
		try {
			daoAnimal.delete(codeAnimal);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un animal, id:" + codeAnimal, e);
		}
	}

	public Animal getAnimalById(int codeAnimal) throws BLLException {
		try {
			return daoAnimal.selectById(codeAnimal);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un animal, id:" + codeAnimal, e);
		}
	}

	public List<Animal> getAnimalsByClient(Client client) throws BLLException {

		try {
			return daoAnimal.selectAnimalsByClient(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un animal, id:" + client, e);
		}
	}

	public List<String> getAllRace() throws BLLException {

		try {
			return daoAnimal.selectAllRace();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'une espece", e);
		}
	}

	public List<String> getEspeceByRace(String string) throws BLLException {

		try {
			return daoAnimal.selectEspeceByRace(string);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'une espece race : " + string, e);
		}

	}
}
