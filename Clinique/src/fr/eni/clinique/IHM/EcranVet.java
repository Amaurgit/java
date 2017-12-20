package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.DAL.DALException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComponent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class EcranVet extends JFrame {

	private JPanel contentPane;

	private EcranVet() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 613);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmConnexiondeconnexion = new JMenuItem("Connexion/Deconnexion");
		mntmConnexiondeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EcranDialConnexion connex = EcranDialConnexion.getInstance();
				connex.afficheConnexion(connex);
				connex.setLocation(300, 300);
			}

		});
		mnNewMenu.add(mntmConnexiondeconnexion);

		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmFermer);

		JMenu mnNewMenu_1 = new JMenu("Gestion des RDV/Clients");
		mnNewMenu_1.setEnabled(false);
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmPriseDeRdv = new JMenuItem("Prise de RDV");
		mntmPriseDeRdv.setEnabled(false);
		mnNewMenu_1.add(mntmPriseDeRdv);

		JMenuItem mntmGestionDesClients = new JMenuItem("Gestion des clients");
		mntmGestionDesClients.setEnabled(false);
		mnNewMenu_1.add(mntmGestionDesClients);

		JMenu mnAgendas = new JMenu("Agendas");
		mnAgendas.setEnabled(true);
		menuBar.add(mnAgendas);

		JMenuItem mntmAccsAgendas = new JMenuItem("Acc\u00E8s agendas");
		mntmAccsAgendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Agenda agenda = new Agenda();
					agenda.setVisible(true);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnAgendas.add(mntmAccsAgendas);

		JMenu mnGestionDuPersonnel = new JMenu("Gestion du personnel");
		mnGestionDuPersonnel.setEnabled(false);
		menuBar.add(mnGestionDuPersonnel);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private static EcranVet INSTANCE = new EcranVet();

	public static EcranVet getInstance() {
		return INSTANCE;
	}

	public void afficheEcranVet(EcranVet ecran) {
		ecran.setVisible(true);
	}

}
