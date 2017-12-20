package fr.eni.clinique.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;

public class AnimalDaoImpl implements AnimalDao {

	public Connection connection = null;

	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	public static Animal getAnimal(ResultSet resultSet) throws SQLException {
		Animal animal = new Animal();
		animal.setCodeAnimal(resultSet.getInt("CodeAnimal"));
		animal.setNomAnimal(resultSet.getString("NomAnimal"));
		animal.setSexe(resultSet.getString(("Sexe")));
		animal.setCouleur(resultSet.getString("Couleur"));
		animal.setRace(resultSet.getString("Race"));
		animal.setEspece(resultSet.getString("Espece"));
		animal.setCodeClient(resultSet.getInt("CodeClient"));
		animal.setTatouage(resultSet.getString("Tatouage"));
		animal.setAntecedents(resultSet.getString("Antecedents"));
		animal.setArchive(resultSet.getBoolean("Archive"));

		return animal;
	}

	private void FillStatementFromArticle(PreparedStatement statement, Animal animal) throws SQLException {

		statement.setString(1, animal.getNomAnimal());
		statement.setString(2, String.valueOf(animal.getSexe()));
		statement.setString(3, animal.getCouleur());
		statement.setString(4, animal.getRace());
		statement.setString(5, animal.getEspece());
		statement.setLong(6, animal.getCodeClient());
		statement.setString(7, animal.getTatouage());
		statement.setString(8, animal.getAntecedents());
		statement.setBoolean(9, animal.getArchive());

	}

	public void insert(Animal animal, Integer codeClient) throws DALException {

		openConnection();

		String sql = "INSERT INTO Animaux ([NomAnimal],[Sexe],[Couleur]"
				+ ",[Race],[Espece],[CodeClient],[Tatouage],[Antecedents],[Archive]) VALUES " + "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			FillStatementFromArticle(statement, animal);
			statement.setInt(6, codeClient);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				animal.setCodeAnimal(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un article : " + animal, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un article : " + animal, e);
			}
		}

	}

	public List<String> selectEspeceByRace(String string) throws DALException {
		openConnection();

		String sql = "select Espece FROM Races WHERE Race = ?";
		List<String> especes = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, string);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				especes.add(resultSet.getString("Espece"));
			}
			return especes;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de toutes les espèces par race", e);

		}
	}

	public List<String> selectAllRace() throws DALException {
		openConnection();

		String sql = "select Race from Races;";
		List<String> races = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				races.add(resultSet.getString("Race"));
			}
			return races;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de toutes les espèces", e);

		}
	}

	public List<Animal> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Animaux";
		List<Animal> animaux = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				animaux.add(getAnimal(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les animaux", e);

		}
	}

	public List<Animal> selectAnimalsById(int codeClient) throws DALException {
		openConnection();

		List<Animal> animaux = new LinkedList<>();
		String sql = "SELECT * FROM Animaux where codeClient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codeClient);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				animaux.add(getAnimal(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des animaux : " + codeClient, e);
		}
	}

	public List<Animal> selectAnimalsByClient(Client client) throws DALException {
		openConnection();

		List<Animal> animaux = new LinkedList<>();
		String sql = "SELECT * FROM Animaux where codeClient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, client.getCodeClient());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				animaux.add(getAnimal(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des animaux : " + client.getCodeClient(), e);
		}
	}

	public Animal selectById(Integer CodeAnimal) throws DALException {
		openConnection();
		String sql = "SELECT * FROM Animaux where CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CodeAnimal);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getAnimal(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un article : " + CodeAnimal, e);
		}
	}

	public void update(Animal animal) throws DALException {
		openConnection();
		String sql = "UPDATE Animaux SET NomAnimal=?,Sexe=?,Couleur=?"
				+ ",Race=?,Espece=?,CodeClient=?,Tatouage=?,Antecedents=?,Archive=? WHERE CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromArticle(statement, animal);
			statement.setInt(10, animal.getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un article : " + animal, e);
		}
	}

	public void delete(Integer CodeAnimal) throws DALException {
		openConnection();

		String sql = "DELETE FROM Animaux WHERE CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CodeAnimal);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression d'un article : " + CodeAnimal, e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();

	}

}
