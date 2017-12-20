package fr.eni.clinique.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;

public class RdvDaoImpl implements RdvDao {
	Animal animal;
	Personnel veto;

	public Connection connection = null;

	public void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	private void FillStatementFromRdv(PreparedStatement statement, Rdv rdv) throws SQLException {
		statement.setInt(1, rdv.getPersonnel().getCodePers());
		statement.setTimestamp(2, rdv.getDateRdv());
		statement.setInt(3, rdv.getAnimal().getCodeAnimal());

	}

	public void insert(Rdv rdv) throws DALException {

		openConnection();

		String sql = "INSERT INTO Agendas ([CodeVeto],[DateRdv],[CodeAnimal]) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromRdv(statement, rdv);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un nouveau Rdv : " + rdv, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Erreur à l'insertion d'un nouveau Rdv : " + rdv, e);
			}
		}

	}

	public void update(Rdv rdv) throws DALException {
		openConnection();

		String sql = "UPDATE Agendas SET CodeVeto=?,DateRdv=?,CodeAnimal=? WHERE CodeVeto=? AND CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromRdv(statement, rdv);
			statement.setInt(4, rdv.getPersonnel().getCodePers());
			statement.setInt(5, rdv.getAnimal().getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un RDV : " + rdv, e);
		}
	}

	public List<Rdv> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Agendas";
		List<Rdv> rdvs = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				rdvs.add(getRdvs(resultSet));
			}
			return rdvs;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les RDVs", e);
		}
	}

	public List<Rdv> selectAllRdvByVet(Personnel personnel) throws DALException {
		openConnection();
		String sql = "SELECT * FROM Agendas a Inner Join Animaux an ON a.CodeAnimal = an.CodeAnimal Inner Join Clients c  ON an.CodeClient = c.CodeClient WHERE CodeVeto = ?";
		List<Rdv> rdvs = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, personnel.getCodePers());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Rdv rdv = getRdvs(resultSet);
				rdv.setPersonnel(personnel);
				Animal a = AnimalDaoImpl.getAnimal(resultSet);
				a.setClient(ClientDaoImpl.getClients(resultSet));
				rdv.setAnimal(a);

				rdvs.add(rdv);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des rdvs par veterinaire", e);
		}

		return rdvs;
	}

	public List<Rdv> selectAllRdvByVetAndDate(Personnel personnel, Date date) throws DALException {
		openConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateQuery = sdf.format(date);

		String sql = "SET DATEFORMAT ymd"
				+ " SELECT * FROM Agendas a Inner Join Animaux an ON a.CodeAnimal = an.CodeAnimal Inner Join Clients c  ON an.CodeClient = c.CodeClient "
				+ "WHERE CodeVeto = ? and CONVERT(VARCHAR(25), DateRdv, 126) LIKE '" + dateQuery + "%'";
		List<Rdv> rdvs = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, personnel.getCodePers());

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Rdv rdv = getRdvs(resultSet);
				rdv.setPersonnel(personnel);
				Animal a = AnimalDaoImpl.getAnimal(resultSet);
				a.setClient(ClientDaoImpl.getClients(resultSet));
				rdv.setAnimal(a);

				rdvs.add(rdv);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des rdvs par veterinaire et par date", e);
		}

		return rdvs;
	}

	public void delete(Personnel veto, Animal animal, Timestamp date) throws DALException {
		openConnection();

		String sql = "DELETE From Agendas WHERE CodeVeto=? AND CodeAnimal=? AND DateRdv=? ";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, veto.getCodePers());
			statement.setInt(2, animal.getCodeAnimal());
			statement.setTimestamp(3, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(
					"Erreur lors de la suppression d'un RDV : " + veto.getCodePers() + animal.getCodeAnimal(), e);
		}
	}

	public Rdv selectByIdAndAnimal(Integer codeAnimal, Integer codeVeto) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Agendas WHERE CodeAnimal=? AND CodeVeto=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codeAnimal);
			statement.setInt(2, codeVeto);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getRdvs(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un RDV : " + codeAnimal + codeVeto, e);
		}
	}

	public Rdv selectById(Integer codeVeto) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Agendas WHERE CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, codeVeto);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getRdvs(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un RDV : " + codeVeto, e);
		}
	}

	public Rdv getRdvs(ResultSet resultSet) throws SQLException {
		Rdv rdv = new Rdv();
		rdv.setDateRdv(resultSet.getTimestamp("DateRdv"));

		// Rdv rdv = new Rdv();
		// animal = new Animal();
		// // client.setCodeClient(resultSet.getInt("CodeClient"));
		// rdv.setAnimal.(resultSet.getInt("CodeAnimal"));
		// rdv.setDateRdv(resultSet.getTimestamp("DateRdv"));
		// rdv.setPersonnel.(resultSet.getInt("CodeVeto"));
		return rdv;
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();
	}

}