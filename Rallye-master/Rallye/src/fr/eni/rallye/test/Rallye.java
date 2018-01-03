package fr.eni.rallye.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rallye {

	private Date date;
	private String pays;
	private List<Speciale> speciales = new ArrayList<Speciale>(20);

	public Rallye(String pays, Date date) {
		super();
		this.date = date;
		this.pays = pays;
	}

	public void ajouterSpeciale(Speciale speciale) {
		speciales.add(speciale);
	}

	public String infosRallye(boolean inclureSpeciales) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY hh:mm");
		String string = "Rallye [date=" + sdf.format(date) + ", pays=" + pays + "]" + System.lineSeparator();

		if (inclureSpeciales) {
			string = "Rallye [date=" + sdf.format(date) + ", pays=" + pays + "]" + System.lineSeparator();
			for (Speciale speciale : speciales) {
				string += speciale.infosSpeciale();

			}
		}
		return string;
	}

	public Speciale getSpeciale(String string) {

		Map<String, Speciale> specialesMap = new HashMap<>();

		for (int i = 0; i < speciales.size(); i++) {
			specialesMap.put(speciales.get(i).getNom(), speciales.get(i));
		}
		return specialesMap.get(string);

	}

	public Classement[] getClassementGeneral() {
		for (int i = 0; i < speciales.size(); i++) {
			Classement[] classements;
			speciales.get(i).getClassement();

		}
		return null;
	}

}
