package fr.eni.clinique.TOOLS;

import java.util.List;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BO.Client;

public class GetFirstClient {
	public static Client getFirstClient() throws BLLException {
		List<Client> clients = AnimalClientManager.getInstance().getClient();

		boolean trouve = false;
		int i = 0;
		while (i < clients.size() && !trouve) {
			if (clients.get(i).getArchive() == 0) {
				trouve = true;
				break;
			} else {
				i++;
			}
		}

		if (trouve) {
			return clients.get(i);
		} else {
			return null;
		}
	}
}
