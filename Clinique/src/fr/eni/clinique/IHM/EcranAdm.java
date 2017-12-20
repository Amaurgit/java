package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.TOOLS.GetFirstClient;

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
import java.io.ObjectInputStream.GetField;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class EcranAdm extends JFrame {
	private JPanel contentPane;
	GetFirstClient gfc = new GetFirstClient();

	private EcranAdm() {
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
		mnNewMenu_1.setEnabled(true);
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmPriseDeRdv = new JMenuItem("Prise de RDV");
		mntmPriseDeRdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					EcranRdv ecranRdv = new EcranRdv();
					ecranRdv.setVisible(true);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu_1.add(mntmPriseDeRdv);

		JMenuItem mntmGestionDesClients = new JMenuItem("Gestion des clients");
		mntmGestionDesClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					GestionClients gestionClient = new GestionClients(gfc.getFirstClient());
					gestionClient.setVisible(true);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu_1.add(mntmGestionDesClients);

		JMenu mnAgendas = new JMenu("Agendas");
		mnAgendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnAgendas.setEnabled(true);
		menuBar.add(mnAgendas);

		JMenuItem mntmAccsAgendas = new JMenuItem("Acc\u00E8s agendas");
		mntmAccsAgendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Agenda agenda;
				try {
					agenda = new Agenda();
					agenda.setVisible(true);

				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		mnAgendas.add(mntmAccsAgendas);

		JMenu mnGestionDuPersonnel = new JMenu("Gestion du personnel");
		mnGestionDuPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnGestionDuPersonnel.setEnabled(true);
		menuBar.add(mnGestionDuPersonnel);

		JMenuItem mntmAccsGestionPersonnel = new JMenuItem("Acc\u00E8s gestion personnel");
		mntmAccsGestionPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					GestionPersonnel gdp = new GestionPersonnel();
					gdp.setVisible(true);

				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnGestionDuPersonnel.add(mntmAccsGestionPersonnel);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private static EcranAdm INSTANCE = new EcranAdm();

	public static EcranAdm getInstance() {
		return INSTANCE;
	}

}
