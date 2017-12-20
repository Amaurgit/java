package fr.eni.clinique.IHM;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;

import fr.eni.clinique.BLL.Gestion_Connexion;
import fr.eni.clinique.BLL.PersonnelManager;

public class TestIHM extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					EcranDialConnexion edc = new EcranDialConnexion();
					edc.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
