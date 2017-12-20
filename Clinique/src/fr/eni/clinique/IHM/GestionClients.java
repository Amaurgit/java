package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionClients extends JFrame {

	private JPanel contentPane;
	private JTextField codeClientField;
	private JTextField nomClientField;
	private JTextField prenomClientField;
	private JTextField adresseClientField;
	private JTextField adresse2ClientField;
	private JTextField codePostalClientField;
	private JTextField villeClientField;
	private JTable table;
	private Animal selectedAnimal;

	private Client client = new Client();
	private Client clientModif = new Client();
	private AnimalClientManager acm = AnimalClientManager.getInstance();

	public GestionClients(Client client) throws BLLException, DALException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		List<Client> clients = AnimalClientManager.getInstance().getClient();
		AnimalDynamicList adl = new AnimalDynamicList(client);
		setBounds(100, 100, 665, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 93, 123, 142, 101, 17, 0 };
		gbl_panel.rowHeights = new int[] { 0, 20, 28, 0, 24, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					RechercheClient rechercheClient = new RechercheClient();
					rechercheClient.setVisible(true);
					dispose();

				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/find.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterClient ajouterClient = new AjouterClient();
				ajouterClient.setVisible(true);
			}
		});
		btnNewButton_1
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/plus.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					acm.removeClient(Integer.valueOf(codeClientField.getText()));
					updateTextFields(getFirstClient(), codeClientField, nomClientField, prenomClientField,
							adresseClientField, adresse2ClientField, codePostalClientField, villeClientField);
					adl.updateData(client);

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_2
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/minus.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton boutonModifierClient = new JButton("Valider");

		boutonModifierClient
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/icon.png")));
		GridBagConstraints gbc_boutonModifierClient = new GridBagConstraints();
		gbc_boutonModifierClient.insets = new Insets(0, 0, 5, 5);
		gbc_boutonModifierClient.gridx = 3;
		gbc_boutonModifierClient.gridy = 0;
		panel.add(boutonModifierClient, gbc_boutonModifierClient);

		JButton AnnulerChangements = new JButton("Annuler");

		AnnulerChangements.setIcon(
				new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/go-back-button.png")));
		GridBagConstraints gbc_AnnulerChangements = new GridBagConstraints();
		gbc_AnnulerChangements.fill = GridBagConstraints.HORIZONTAL;
		gbc_AnnulerChangements.insets = new Insets(0, 0, 5, 0);
		gbc_AnnulerChangements.gridx = 4;
		gbc_AnnulerChangements.gridy = 0;
		panel.add(AnnulerChangements, gbc_AnnulerChangements);

		JLabel lblCode = new JLabel("Code :");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 0;
		gbc_lblCode.gridy = 2;
		panel.add(lblCode, gbc_lblCode);

		codeClientField = new JTextField();
		codeClientField.setEditable(false);
		codeClientField.setText(String.valueOf(client.getCodeClient()));
		GridBagConstraints gbc_adresse2ClientField = new GridBagConstraints();
		gbc_adresse2ClientField.anchor = GridBagConstraints.NORTH;
		gbc_adresse2ClientField.insets = new Insets(0, 0, 5, 5);
		gbc_adresse2ClientField.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse2ClientField.gridx = 1;
		gbc_adresse2ClientField.gridy = 2;
		panel.add(codeClientField, gbc_adresse2ClientField);
		codeClientField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable(adl);
		scrollPane.setViewportView(table);

		JLabel lblNom = new JLabel(" Nom :");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 3;
		panel.add(lblNom, gbc_lblNom);

		nomClientField = new JTextField();

		nomClientField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (nomClientField.getText().length() > 20) {
					nomClientField.setText(nomClientField.getText().substring(0, 20));
				} else if (nomClientField.getText().equals("")) {
					nomClientField.setText("A DEFINIR");
				}
				clientModif.setNomClient(nomClientField.getText());
			}
		});
		nomClientField.setToolTipText("Merci de renseigner un nom");
		nomClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (nomClientField.getText().length() > 20) {
					nomClientField.setText(nomClientField.getText().substring(0, 20));
				} else if (nomClientField.getText().equals("")) {
					nomClientField.setText("A DEFINIR");

				}
				clientModif.setNomClient(nomClientField.getText());
			}
		});

		nomClientField.setColumns(10);
		nomClientField.setText(client.getNomClient());
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		panel.add(nomClientField, gbc_textField_1);

		JLabel lblPrenom = new JLabel("Prenom :");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 4;
		panel.add(lblPrenom, gbc_lblPrenom);

		prenomClientField = new JTextField();
		prenomClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clientModif.setPrenomClient(prenomClientField.getText());
			}
		});
		prenomClientField.setColumns(10);
		prenomClientField.setText(client.getPrenomClient());
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		panel.add(prenomClientField, gbc_textField_2);

		JLabel lblAdresse = new JLabel("Adresse :");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 0;
		gbc_lblAdresse.gridy = 5;
		panel.add(lblAdresse, gbc_lblAdresse);

		adresseClientField = new JTextField();
		adresseClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clientModif.setAdresse1(adresseClientField.getText());
			}
		});
		adresseClientField.setColumns(10);
		adresseClientField.setText(client.getAdresse1());
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 5;
		panel.add(adresseClientField, gbc_textField_3);
		gbc_adresse2ClientField.insets = new Insets(0, 0, 5, 5);
		gbc_adresse2ClientField.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse2ClientField.gridx = 2;
		gbc_adresse2ClientField.gridy = 7;

		JLabel lblComplmentAdresse = new JLabel("Compl\u00E9ment adresse :");
		GridBagConstraints gbc_lblComplmentAdresse = new GridBagConstraints();
		gbc_lblComplmentAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplmentAdresse.anchor = GridBagConstraints.EAST;
		gbc_lblComplmentAdresse.gridx = 0;
		gbc_lblComplmentAdresse.gridy = 6;
		panel.add(lblComplmentAdresse, gbc_lblComplmentAdresse);

		adresse2ClientField = new JTextField();
		adresse2ClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clientModif.setAdresse2(adresse2ClientField.getText());
			}
		});
		adresse2ClientField.setText(client.getAdresse2());
		GridBagConstraints gbc_gbctextField6 = new GridBagConstraints();
		gbc_gbctextField6.anchor = GridBagConstraints.NORTH;
		gbc_gbctextField6.fill = GridBagConstraints.HORIZONTAL;
		gbc_gbctextField6.insets = new Insets(0, 0, 5, 5);
		gbc_gbctextField6.gridx = 1;
		gbc_gbctextField6.gridy = 6;
		panel.add(adresse2ClientField, gbc_gbctextField6);
		adresse2ClientField.setColumns(10);

		JLabel lblCodePostal = new JLabel("Code postal :");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 7;
		panel.add(lblCodePostal, gbc_lblCodePostal);

		codePostalClientField = new JTextField(6);
		codePostalClientField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (codePostalClientField.getText().length() > 6) {
					codePostalClientField.setText(codePostalClientField.getText().substring(0, 6));
				}
			}
		});
		codePostalClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (codePostalClientField.getText().length() > 6) {
					codePostalClientField.setText(codePostalClientField.getText().substring(0, 6));
				}
				clientModif.setCodePostal(codePostalClientField.getText());
			}

		});
		codePostalClientField.setColumns(10);
		codePostalClientField.setText(client.getCodePostal());
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.NORTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 7;
		panel.add(codePostalClientField, gbc_textField_4);

		JLabel lblVille = new JLabel("Ville :");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 8;
		panel.add(lblVille, gbc_lblVille);

		villeClientField = new JTextField();
		villeClientField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (villeClientField.getText().length() > 25) {
					villeClientField.setText(villeClientField.getText().substring(0, 25));
				}
			}
		});
		villeClientField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (villeClientField.getText().length() > 25) {
					villeClientField.setText(villeClientField.getText().substring(0, 25));
				}

				clientModif.setVille(villeClientField.getText());
			}
		});
		villeClientField.setColumns(10);
		villeClientField.setText(client.getVille());
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.anchor = GridBagConstraints.NORTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 8;
		panel.add(villeClientField, gbc_textField_5);

		// populate CLIENT initial

		client.setNomClient(nomClientField.getText());
		client.setPrenomClient(prenomClientField.getText());
		client.setAdresse1(adresseClientField.getText());
		client.setAdresse2(adresse2ClientField.getText());
		client.setCodePostal(codePostalClientField.getText());
		client.setVille(villeClientField.getText());

		JLabel lblNewLabel_2 = new JLabel("Gestion Animal :");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 8;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton btnNewButton_7 = new JButton("Ajouter");
		btnNewButton_7
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/plus.png")));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					EcranDialAjoutAnimal edaa = new EcranDialAjoutAnimal(
							acm.getClientById(Integer.valueOf(codeClientField.getText())));
					edaa.setVisible(true);

				} catch (NumberFormatException | BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});

		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_7.gridx = 2;
		gbc_btnNewButton_7.gridy = 9;
		panel.add(btnNewButton_7, gbc_btnNewButton_7);

		JButton btnNewButton_5 = new JButton("Supprimer");
		btnNewButton_5
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/minus.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					acm.removeAnimal(selectedAnimal.getCodeAnimal());
					adl.updateData(client);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 9;
		panel.add(btnNewButton_5, gbc_btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Modifier");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					EcranDialModifAnimal edma = new EcranDialModifAnimal(selectedAnimal);
					edma.setVisible(true);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton_6
				.setIcon(new ImageIcon(GestionClients.class.getResource("/fr/eni/clinique/IHM/ressources/pencil.png")));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_6.gridx = 4;
		gbc_btnNewButton_6.gridy = 9;
		panel.add(btnNewButton_6, gbc_btnNewButton_6);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = table.getSelectedRow();
					selectedAnimal = adl.getValueAt(index);
				}
			}
		});

		boutonModifierClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					clientModif.setCodeClient(client.getCodeClient());
					clientModif.setNomClient(nomClientField.getText());
					clientModif.setPrenomClient(prenomClientField.getText());
					clientModif.setAdresse1(adresseClientField.getText());
					clientModif.setAdresse2(adresse2ClientField.getText());
					clientModif.setCodePostal(codePostalClientField.getText());
					clientModif.setVille(villeClientField.getText());
					clientModif.setArchive(0);

					System.out.println(client.getCodeClient() + nomClientField.getText() + prenomClientField.getText()
							+ adresseClientField.getText() + codePostalClientField.getText()
							+ villeClientField.getText());

					acm.updateClient(clientModif);

				} catch (BLLException e1) {
					e1.printStackTrace();
				}

			}
		});

		AnnulerChangements.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				nomClientField.setText(client.getNomClient());
				prenomClientField.setText(client.getPrenomClient());
				adresseClientField.setText(client.getAdresse1());
				adresse2ClientField.setText(client.getAdresse2());
				codePostalClientField.setText(client.getCodePostal());
				villeClientField.setText(client.getVille());
				client.setCodeClient(Integer.valueOf(codeClientField.getText()));
				client.setArchive(0);
			}

		});
	}

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

	private void updateTextFields(Client client, JTextField codeClient, JTextField nom, JTextField prenom,
			JTextField adresse1, JTextField adresse2, JTextField codePostal, JTextField ville) {

		nom.setText(client.getNomClient());
		prenom.setText(client.getPrenomClient());
		adresse1.setText(client.getAdresse1());
		adresse2.setText(client.getAdresse2());
		codePostal.setText(client.getCodePostal());
		ville.setText(client.getVille());

	}

}
