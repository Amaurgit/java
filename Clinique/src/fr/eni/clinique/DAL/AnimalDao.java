package fr.eni.clinique.DAL;

import java.util.List;

import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;

public interface AnimalDao {
	
	
	public void insert(Animal animal, Integer CodeClient) throws DALException;

	public List<Animal> selectAll() throws DALException;

	public Animal selectById(Integer codeAnimal) throws DALException;

	public void update(Animal animal) throws DALException;

	public void delete(Integer codeAnimal) throws DALException;
	
	public List<Animal> selectAnimalsById(int codeClient) throws DALException;
	
	public List<Animal> selectAnimalsByClient(Client client) throws DALException;
	
	public List<String> selectAllRace() throws DALException;
	
	public List<String> selectEspeceByRace(String string) throws DALException;
	
	
}
