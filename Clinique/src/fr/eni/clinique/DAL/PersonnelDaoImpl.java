package fr.eni.clinique.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.eni.clinique.BO.Personnel;

public class PersonnelDaoImpl implements PersonnelDao {

	public Connection connection = null;

	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	private Personnel getPersonnels(ResultSet resultSet) throws SQLException {
		Personnel personnel = new Personnel();

		personnel.setCodePers(resultSet.getInt("CodePers"));
		personnel.setNom(resultSet.getString("Nom"));
		personnel.setMotPasse(resultSet.getString(("MotPasse")));
		personnel.setRole(resultSet.getString("Role"));
		personnel.setArchive(resultSet.getBoolean("Archive"));

		return personnel;
	}

	private void FillStatementFromArticle(PreparedStatement statement, Personnel personnel) throws SQLException {

		statement.setString(1, personnel.getNom());
		statement.setString(2, personnel.getMotPasse());
		statement.setString(3, personnel.getRole());
		statement.setBoolean(4, personnel.getArchive());

	}

	public void insert(Personnel personnel) throws DALException {

		openConnection();

		String sql = "INSERT INTO Personnels([Nom],[MotPasse],[Role],[Archive]) VALUES (?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			FillStatementFromArticle(statement, personnel);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				personnel.setCodePers(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un personnel : " + personnel, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un personnel : " + personnel, e);
			}
		}

	}

	public List<Personnel> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels WHERE Archive = 0";
		List<Personnel> personnels = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				personnels.add(getPersonnels(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les personnels", e);

		}
	}

	public Personnel selectById(Integer CodePers) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels where CodePers=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CodePers);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getPersonnels(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un personnel : " + CodePers, e);
		}
	}

	public void update(Personnel personnel) throws DALException {
		openConnection();
		String sql = "UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, Archive=?  WHERE CodePers=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromArticle(statement, personnel);
			statement.setInt(5, personnel.getCodePers());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un personnel : " + personnel, e);
		}
	}

	public void delete(int codePersonnel) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET Archive=1 WHERE CodePers=?";

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codePersonnel);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression d'un personnel : " + codePersonnel, e);
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
