package fr.eni.clinique.IHM;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Personnel;

public class PersonnelDynamicList extends AbstractTableModel {

	private List<Personnel> personnels = new ArrayList<>();
	private String[] entetes = { "Nom", "Role", " Mot de passe" };

	public PersonnelDynamicList() throws BLLException {
		updateData();

	}

	public int getRowCount() {
		return personnels.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;

		if (rowIndex < personnels.size()) {
			switch (columnIndex) {

			case 0:
				result = personnels.get(rowIndex).getNom();
				break;
			case 1:
				result = personnels.get(rowIndex).getRole();
				break;
			case 2:
				result = "*********";
				break;
			default:
			}
		}
		return result;

	}

	public Personnel getValueAt(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < personnels.size()) {
			return personnels.get(rowIndex);
		} else {
			return null;
		}

	}

	public void addPersonnel(Personnel personnel) {
		personnels.add(personnel);
		fireTableRowsInserted(personnels.size() - 1, personnels.size() - 1);
	}

	public void removePersonnel(int rowIndex) {
		personnels.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateData() throws BLLException {
		personnels = PersonnelManager.getInstance().getPersonnel();
		fireTableDataChanged();
	}

}
