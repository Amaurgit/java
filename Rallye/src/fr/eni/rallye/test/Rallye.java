package fr.eni.rallye.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

	// TODO
	public Classement[] getClassementGeneral() {
		Classement[] classementsGeneral = new Classement[20];

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);

		Calendar cal1 = calendar;
		Calendar cal2 = calendar;
		Calendar cal3 = calendar;
		int indexClassement = 0;

		for (int i = 0; i < speciales.size(); i++) {
			for (int j = 0; j < speciales.get(i).getClassement().length; j++) {
				if (speciales.get(i).getClassement()[j].getEquipage().getDossard() == 1) {
					Calendar dateToAdd1 = new GregorianCalendar();
					//dateToAdd1.setTime(speciales.get(i).getClassement()[j].getTemps());

					cal1.add(Calendar.HOUR, dateToAdd1.get(Calendar.HOUR));
					cal1.add(Calendar.MINUTE, dateToAdd1.get(Calendar.MINUTE));
					cal1.add(Calendar.SECOND, dateToAdd1.get(Calendar.SECOND));
					System.out.println("heure:" + dateToAdd1.get(Calendar.HOUR) + " minutes:"
							+ dateToAdd1.get(Calendar.MINUTE) + " secondes: " + dateToAdd1.get(Calendar.SECOND));

					// System.out.println("temps dossard 1 : " + cal1.getTime());

				}
				if (speciales.get(i).getClassement()[j].getEquipage().getDossard() == 2) {
					Calendar dateToAdd2 = new GregorianCalendar();
					dateToAdd2.setTime(speciales.get(i).getClassement()[j].getTemps());

					cal2.add(Calendar.HOUR, dateToAdd2.get(Calendar.HOUR));
					cal2.add(Calendar.MINUTE, dateToAdd2.get(Calendar.MINUTE));
					cal2.add(Calendar.SECOND, dateToAdd2.get(Calendar.SECOND));
					// System.out.println("temps dossard 2 : " + cal2.getTime());

				}
				if (speciales.get(i).getClassement()[j].getEquipage().getDossard() == 3) {
					Calendar dateToAdd3 = new GregorianCalendar();
					dateToAdd3.setTime(speciales.get(i).getClassement()[j].getTemps());

					cal3.add(Calendar.HOUR, dateToAdd3.get(Calendar.HOUR));
					cal3.add(Calendar.MINUTE, dateToAdd3.get(Calendar.MINUTE));
					cal3.add(Calendar.SECOND, dateToAdd3.get(Calendar.SECOND));
					// System.out.println("temps dossard 3 : " + cal3.getTime());

				}
				indexClassement++;
				for (int k = 0; k < speciales.size(); k++) {
					if (speciales.get(i).getClassement()[j].getEquipage().getDossard() == 1)
						classementsGeneral[indexClassement] = new Classement(cal1.getTime(),
								speciales.get(i).getClassement()[j].getEquipage());
				}
			}

		}

		return classementsGeneral;
	}

}
