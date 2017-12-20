package fr.eni.clinique.IHM;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BLL.RdvManager;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;

public class RdvDynamicList extends AbstractTableModel {

	private List<Rdv> rdvs = new ArrayList<>();

	private String[] entetes = { "Heure", "Nom du client", "Code animal", "Race" };

	public RdvDynamicList(Personnel veto) throws BLLException {
		updateData(veto);
	}

	public RdvDynamicList(Personnel veto, Date date) throws BLLException, DALException {

		updateDataByVetAndDate(veto, date);
	}

	public int getRowCount() {
		return rdvs.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		if (rowIndex < rdvs.size()) {
			switch (columnIndex) {

			case 0:
				result = format.format(rdvs.get(rowIndex).getDateRdv());
				break;
			case 1:
				result = rdvs.get(rowIndex).getAnimal().getClient().getNomClient();
				break;
			case 2:
				result = rdvs.get(rowIndex).getAnimal().getNomAnimal();
				break;
			case 3:
				result = rdvs.get(rowIndex).getAnimal().getRace();
				break;
			// case 4:
			// result = rdvs.get(rowIndex).getAnimal().getCodeAnimal();
			}
		}
		return result;

	}

	public Rdv getValueAt(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < rdvs.size()) {
			return rdvs.get(rowIndex);
		} else {
			return null;
		}
	}

	public void addRdv(Rdv rdv) {
		rdvs.add(rdv);
		fireTableRowsInserted(rdvs.size() - 1, rdvs.size() - 1);
	}

	public void removeRdv(int rowIndex) {
		rdvs.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateData(Personnel veto) throws BLLException {
		try {
			rdvs = RdvManager.getInstance().selectAllRdvByVet(veto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

	public void updateDataByVetAndDate(Personnel veto, Date date) throws BLLException {
		try {
			rdvs = RdvManager.getInstance().selectAllRdvByVetAndDate(veto, date);
		} catch (DALException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

}
