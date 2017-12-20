package fr.eni.clinique.DAL;

import java.util.List;

import fr.eni.clinique.BO.Client;

public interface ClientDao {

	
	
	public void update(Client client) throws DALException;
	
	public List<Client> selectAll() throws DALException;
	
	public void delete(Integer codeClient) throws DALException;
	
	public Client selectById(Integer codeClient) throws DALException;
	
	public void insert(Client client) throws DALException;
	
	public List<Client> selectByMotCle(String motCle) throws DALException;
	
	public List<Client> selectAllWithAnimal() throws DALException;
	
}
