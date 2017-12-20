package fr.eni.clinique.DAL;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;

public interface RdvDao {
	public void update(Rdv rdv) throws DALException;

	public List<Rdv> selectAll() throws DALException;

	public void delete(Personnel veto, Animal animal, Timestamp date) throws DALException;

	public Rdv selectById(Integer codeVeto) throws DALException;

	public void insert(Rdv rdv) throws DALException;

	public List<Rdv> selectAllRdvByVet(Personnel personnel) throws DALException;

	public List<Rdv> selectAllRdvByVetAndDate(Personnel personnel, Date date) throws DALException;
}