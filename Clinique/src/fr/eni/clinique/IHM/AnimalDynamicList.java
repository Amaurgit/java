package fr.eni.clinique.IHM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.RdvManager;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;

public class AnimalDynamicList extends AbstractTableModel {

	private List<Animal> animaux = new ArrayList<>();

	private String[] entetes = { "Nom", "Sexe", "Couleur", "Race", "Espece", "Tatouage" };

	public AnimalDynamicList(Client client) throws BLLException {
		updateData(client);

	}

	public int getRowCount() {
		return animaux.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;
		if (rowIndex < animaux.size()) {
			switch (columnIndex) {

			case 0:
				result = animaux.get(rowIndex).getNomAnimal();
				break;
			case 1:
				result = animaux.get(rowIndex).getSexe();
				break;
			case 2:
				result = animaux.get(rowIndex).getCouleur();
				break;
			case 3:
				result = animaux.get(rowIndex).getRace();
				break;
			case 4:
				result = animaux.get(rowIndex).getEspece();
				break;
			case 5:
				result = animaux.get(rowIndex).getTatouage();
				break;

			}
		}
		return result;

	}

	public Animal getValueAt(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < animaux.size()) {
			return animaux.get(rowIndex);
		} else {
			return null;
		}
	}

	public void addAnimal(Animal animal) {
		animaux.add(animal);
		fireTableRowsInserted(animaux.size() - 1, animaux.size() - 1);
	}

	public void removeAnimal(int rowIndex) {
		animaux.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateData(Client client) throws BLLException {
		try {
			animaux = AnimalClientManager.getInstance().getAnimalsByClient(client);

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

}
