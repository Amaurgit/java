package fr.eni.clinique.BO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rdv {

	private Timestamp dateRdv;
	private Personnel personnel;
	private Animal animal;
	

	public String convertDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		return dateFormat.format(date);
	}

	public String convertTimeStampToString(Timestamp date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		return dateFormat.format(date);
	}

	public Rdv() {
		super();
	}

	public Timestamp getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Timestamp dateRdv) {
		this.dateRdv = dateRdv;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return "Rdv [dateRdv=" + dateRdv + ", personnel=" + personnel + ", animal=" + animal + "]";
	}

}
