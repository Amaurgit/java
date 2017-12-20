package fr.eni.clinique.DAL;

public class DAOFactory {

	public static AnimalDao getAnimalDAO() {
		return new AnimalDaoImpl();
	}

	public static PersonnelDao getPersonnelDAO() {
		return new PersonnelDaoImpl();
	}

	public static ClientDao getClientDAO() {
		return new ClientDaoImpl();
	}

	public static RdvDao getRdvDAO() {
		return new RdvDaoImpl();
	}
}