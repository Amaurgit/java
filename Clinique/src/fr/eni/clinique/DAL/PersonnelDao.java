package fr.eni.clinique.DAL;

import java.sql.SQLException;
import java.util.List;

import fr.eni.clinique.BO.Personnel;

public interface PersonnelDao {

	public void insert(Personnel personnel) throws DALException;

	public List<Personnel> selectAll() throws DALException;

	public Personnel selectById(Integer CodePers) throws DALException;

	public void update(Personnel personnel) throws DALException;

	public void delete(int codePersonnel) throws DALException, SQLException;

}
