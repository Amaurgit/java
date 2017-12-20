package fr.eni.clinique.IHM;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.DAL.DALException;

public class ClientDynamicList extends AbstractTableModel {

	private List<Client> clients = new ArrayList<>();

	private String[] entetes = { "Nom", "Prenom", "Code Postal", "Ville" };

	public ClientDynamicList(String motCle) throws BLLException, DALException {
		updateData(motCle);

	}


	public int getRowCount() {
		return clients.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;
		if (rowIndex < clients.size()) {
			switch (columnIndex) {

			case 0:
				result = clients.get(rowIndex).getNomClient();
				break;
			case 1:
				result = clients.get(rowIndex).getPrenomClient();
				break;
			case 2:
				result = clients.get(rowIndex).getCodePostal();
				break;
			case 3:
				result = clients.get(rowIndex).getVille();
				break;
			case 4:

			}
		}
		return result;

	}

	public Client getValueAt(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < clients.size()) {
			return clients.get(rowIndex);
		} else {
			return null;
		}
	}

	public void addClient(Client client) {
		clients.add(client);
		fireTableRowsInserted(clients.size() - 1, clients.size() - 1);
	}

	public void removeClient(int rowIndex) {
		clients.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateData(String motCle) throws BLLException, DALException {
	
		clients = AnimalClientManager.getInstance().getClientByName(motCle);
		fireTableDataChanged();
		
	}

}
