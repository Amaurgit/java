package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.DAL.DALException;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EcranDialAjoutAnimal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomClientField;
	private JTextField nomAnimalField;
	private JTextField couleurField;
	private JTextField tatouageField;
	private Animal animal = new Animal();
	private AnimalClientManager acm = AnimalClientManager.getInstance();

	public EcranDialAjoutAnimal(Client client) throws BLLException {

		setBounds(100, 100, 469, 257);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 99, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblClient = new JLabel("Client :");
			GridBagConstraints gbc_lblClient = new GridBagConstraints();
			gbc_lblClient.insets = new Insets(0, 0, 5, 5);
			gbc_lblClient.gridx = 0;
			gbc_lblClient.gridy = 0;
			contentPanel.add(lblClient, gbc_lblClient);

			nomClientField = new JTextField();
			nomClientField.setText(client.getNomClient() + " " + client.getPrenomClient());
			nomClientField.setEditable(false);

			GridBagConstraints gbc_nomClientField = new GridBagConstraints();
			gbc_nomClientField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nomClientField.gridwidth = 5;
			gbc_nomClientField.insets = new Insets(0, 0, 5, 0);
			gbc_nomClientField.gridx = 0;
			gbc_nomClientField.gridy = 1;

			contentPanel.add(nomClientField, gbc_nomClientField);
			nomClientField.setColumns(10);

			JLabel lblNom = new JLabel("Nom :");
			GridBagConstraints gbc_lblNom = new GridBagConstraints();
			gbc_lblNom.anchor = GridBagConstraints.EAST;
			gbc_lblNom.insets = new Insets(0, 0, 5, 5);
			gbc_lblNom.gridx = 0;
			gbc_lblNom.gridy = 3;
			contentPanel.add(lblNom, gbc_lblNom);

			nomAnimalField = new JTextField();
			nomAnimalField.setColumns(10);
			GridBagConstraints gbc_nomAnimalField = new GridBagConstraints();
			gbc_nomAnimalField.insets = new Insets(0, 0, 5, 5);
			gbc_nomAnimalField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nomAnimalField.gridx = 1;
			gbc_nomAnimalField.gridy = 3;
			contentPanel.add(nomAnimalField, gbc_nomAnimalField);

			JLabel lblSexe = new JLabel("Sexe :");
			GridBagConstraints gbc_lblSexe = new GridBagConstraints();
			gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
			gbc_lblSexe.anchor = GridBagConstraints.EAST;
			gbc_lblSexe.gridx = 2;
			gbc_lblSexe.gridy = 3;
			contentPanel.add(lblSexe, gbc_lblSexe);

			JComboBox comboBoxSexe = new JComboBox();
			comboBoxSexe.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
			GridBagConstraints gbc_comboBoxSexe = new GridBagConstraints();
			gbc_comboBoxSexe.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxSexe.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxSexe.gridx = 3;
			gbc_comboBoxSexe.gridy = 3;
			contentPanel.add(comboBoxSexe, gbc_comboBoxSexe);

			JLabel lblCouleur = new JLabel("Couleur :");
			GridBagConstraints gbc_lblCouleur = new GridBagConstraints();
			gbc_lblCouleur.anchor = GridBagConstraints.EAST;
			gbc_lblCouleur.insets = new Insets(0, 0, 5, 5);
			gbc_lblCouleur.gridx = 0;
			gbc_lblCouleur.gridy = 4;
			contentPanel.add(lblCouleur, gbc_lblCouleur);

			couleurField = new JTextField();
			couleurField.setColumns(10);
			GridBagConstraints gbc_couleurField = new GridBagConstraints();
			gbc_couleurField.insets = new Insets(0, 0, 5, 5);
			gbc_couleurField.fill = GridBagConstraints.HORIZONTAL;
			gbc_couleurField.gridx = 1;
			gbc_couleurField.gridy = 4;
			contentPanel.add(couleurField, gbc_couleurField);

			JLabel lblEspces = new JLabel("Espèces :");
			GridBagConstraints gbc_lblEspces = new GridBagConstraints();
			gbc_lblEspces.anchor = GridBagConstraints.EAST;
			gbc_lblEspces.insets = new Insets(0, 0, 5, 5);
			gbc_lblEspces.gridx = 0;
			gbc_lblEspces.gridy = 5;
			contentPanel.add(lblEspces, gbc_lblEspces);

			JComboBox comboboxEspece = new JComboBox();

			// TODO

			List<String> races = new ArrayList<String>();
			races = AnimalClientManager.getInstance().getAllRace();
			for (int i = 0; i < races.size(); i++) {
				comboboxEspece.addItem(races.get(i));
			}

			GridBagConstraints gbc_comboboxEspece = new GridBagConstraints();
			gbc_comboboxEspece.insets = new Insets(0, 0, 5, 5);
			gbc_comboboxEspece.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboboxEspece.gridx = 1;
			gbc_comboboxEspece.gridy = 5;
			contentPanel.add(comboboxEspece, gbc_comboboxEspece);

			JLabel lblTatouage = new JLabel("Tatouage :");
			GridBagConstraints gbc_lblTatouage = new GridBagConstraints();
			gbc_lblTatouage.anchor = GridBagConstraints.EAST;
			gbc_lblTatouage.insets = new Insets(0, 0, 5, 5);
			gbc_lblTatouage.gridx = 0;
			gbc_lblTatouage.gridy = 6;
			contentPanel.add(lblTatouage, gbc_lblTatouage);

			tatouageField = new JTextField();
			tatouageField.setColumns(10);
			GridBagConstraints gbc_tatouageField = new GridBagConstraints();
			gbc_tatouageField.insets = new Insets(0, 0, 5, 5);
			gbc_tatouageField.fill = GridBagConstraints.HORIZONTAL;
			gbc_tatouageField.gridx = 1;
			gbc_tatouageField.gridy = 6;
			contentPanel.add(tatouageField, gbc_tatouageField);

			JLabel lblRace = new JLabel("Race :");
			GridBagConstraints gbc_lblRace = new GridBagConstraints();
			gbc_lblRace.anchor = GridBagConstraints.EAST;
			gbc_lblRace.insets = new Insets(0, 0, 5, 5);
			gbc_lblRace.gridx = 2;
			gbc_lblRace.gridy = 6;
			contentPanel.add(lblRace, gbc_lblRace);

			JComboBox comboBoxRace = new JComboBox();
			GridBagConstraints gbc_comboBoxRace = new GridBagConstraints();
			gbc_comboBoxRace.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxRace.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxRace.gridx = 3;
			gbc_comboBoxRace.gridy = 6;

			String race = comboboxEspece.getModel().getSelectedItem().toString();
			System.out.println(race);

			List<String> especes = new ArrayList<String>();
			especes = AnimalClientManager.getInstance().getEspeceByRace(race);
			for (int i = 0; i < especes.size(); i++) {
				comboBoxRace.addItem(especes.get(i));
			}

			contentPanel.add(comboBoxRace, gbc_comboBoxRace);

			comboboxEspece.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String race = comboboxEspece.getModel().getSelectedItem().toString();

					List<String> especes = new ArrayList<String>();
					try {
						especes = AnimalClientManager.getInstance().getEspeceByRace(race);
						System.out.println(especes);
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
					comboBoxRace.removeAllItems();
					for (int i = 0; i < especes.size(); i++) {
						comboBoxRace.addItem(especes.get(i));
					}

				}
			});

			JButton okButton = new JButton("Valider");
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.EAST;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 7;
			contentPanel.add(okButton, gbc_okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					animal.setNomAnimal(nomAnimalField.getText());
					animal.setCouleur(couleurField.getText());
					animal.setRace(comboboxEspece.getModel().getSelectedItem().toString());
					animal.setSexe(comboBoxSexe.getModel().getSelectedItem().toString());
					animal.setTatouage(tatouageField.getText());
					animal.setArchive(false);
					animal.setEspece(comboBoxRace.getModel().getSelectedItem().toString());
					try {
						acm.addAnimal(animal, client.getCodeClient());
						dispose();
						GestionClients gc = new GestionClients(client);
						gc.setVisible(true);

					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();

				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Annuler");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.WEST;
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 7;
			contentPanel.add(cancelButton, gbc_cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
