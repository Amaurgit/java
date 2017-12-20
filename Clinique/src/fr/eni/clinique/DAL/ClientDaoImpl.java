package fr.eni.clinique.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.eni.clinique.BO.Client;

public class ClientDaoImpl implements ClientDao {

	public Connection connection = null;

	public void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	private void FillStatementFromClient(PreparedStatement statement, Client client) throws SQLException {
		statement.setString(1, client.getNomClient());
		statement.setString(2, client.getPrenomClient());
		statement.setString(3, client.getAdresse1());
		statement.setString(4, client.getAdresse2());
		statement.setString(5, client.getCodePostal());
		statement.setString(6, client.getVille());
		statement.setString(7, client.getNumTel());
		statement.setString(8, client.getAssurance());
		statement.setString(9, client.getEmail());
		statement.setString(10, client.getRemarque());
		statement.setInt(11, client.getArchive());
	}

	public void update(Client client) throws DALException {
		openConnection();

		String sql = "UPDATE Clients SET NomClient=?,PrenomClient=?,Adresse1=?"
				+ ",Adresse2=?,CodePostal=?,Ville=?,NumTel=?,Assurance=?,Email=?, Remarque=?, Archive=? WHERE CodeClient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromClient(statement, client);
			statement.setInt(12, client.getCodeClient());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un client : " + client, e);
		}
	}

	public List<Client> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients";
		List<Client> clients = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				clients.add(getClients(resultSet));
			}
			return clients;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les clients", e);
		}
	}

	public void delete(Integer codeClient) throws DALException {
		openConnection();

		String sql = "UPDATE Clients SET Archive=1 WHERE codeClient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codeClient);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un client : " + codeClient, e);
		}
	}

	public Client selectById(Integer codeClient) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients where codeClient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codeClient);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getClients(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un client : " + codeClient, e);
		}
	}

	public static Client getClients(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setCodeClient(resultSet.getInt("CodeClient"));
		client.setNomClient(resultSet.getString("NomClient"));
		client.setPrenomClient(resultSet.getString("PrenomClient"));
		client.setAdresse1(resultSet.getString("Adresse1"));
		client.setAdresse2(resultSet.getString("Adresse2"));
		client.setCodePostal(resultSet.getString("CodePostal"));
		client.setVille(resultSet.getString("Ville"));
		client.setNumTel(resultSet.getString("NumTel"));
		client.setAssurance(resultSet.getString("Assurance"));
		client.setEmail(resultSet.getString("Email"));
		client.setRemarque(resultSet.getString("Remarque"));
		client.setArchive(resultSet.getInt("Archive"));

		return client;
	}

	public void insert(Client client) throws DALException {

		openConnection();

		String sql = "INSERT INTO Clients ([NomClient],[PrenomClient],[Adresse1]"
				+ ",[Adresse2],[CodePostal],[Ville],[NumTel],[Assurance],[Email],[Remarque],[Archive]) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			FillStatementFromClient(statement, client);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				client.setCodeClient((rs.getInt(1)));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un client : " + client, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un client : " + client, e);
			}
		}

	}

	public List<Client> selectByMotCle(String motCle) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients WHERE NomClient like '%'+?+'%' AND Archive=0";
		List<Client> clients = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, motCle);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clients.add(getClients(resultSet));
			}
			return clients;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des clients par nom", e);
		} finally {

		}

	}

	public List<Client> selectAllWithAnimal() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients c INNER JOIN Animaux a ON c.CodeClient = a.CodeClient";
		List<Client> clients = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int codeClientPrecedent = -1;
			Client newClient = null;
			while (rs.next()) {
				// regarde quel est le client de l'enregistrement en courss
				int code = rs.getInt("CodeClient");
				// est-ce que le client est le mÃªme que le prÃ©cÃ©dent ?
				if (code != codeClientPrecedent) {
					// S'il s'agit d'un client diffÃ©rent,
					// est-ce que le client prÃ©cÃ©dent n'est pas null ?
					if (newClient != null) {
						// S'il n'est pas null, l'ajoute Ã  la liste
						clients.add(newClient);
					}
					// crÃ©e le nouveau client
					newClient = getClients(rs);

					codeClientPrecedent = code;
				}
				// ajoute l'animal au client (grÃ¢ce Ã  la fonction map de AnimalDao)
				newClient.ajouterAnimal(AnimalDaoImpl.getAnimal(rs));
			}

			// ne pas oublier d'ajouter le dernier client crÃ©Ã© Ã  la liste
			clients.add(newClient);

		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les clients", e);

		}

		return clients;

	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();
	}

}