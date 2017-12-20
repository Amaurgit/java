package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DossierMedical extends JDialog {
	private JTextField clientField;
	private JTextField codeAnimalField;
	private JTextField nomAnimalField;
	private JTextField couleurAnimalField;
	private JTextField especeAnimalField;
	private JTextField sexeAnimalField;
	private JTextField tatouageAnimalField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextArea textArea = new JTextArea();
	private AnimalClientManager animalclientmanager = new AnimalClientManager();

	public DossierMedical(Rdv selectedRdv) {
		setTitle("Dossier Medical");
		setBounds(100, 100, 642, 252);
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 99, 69, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
					Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);

			{
				JLabel lblAntecedentsConsulations = new JLabel("Antecedents / Consulations :");
				GridBagConstraints gbc_lblAntecedentsConsulations = new GridBagConstraints();
				gbc_lblAntecedentsConsulations.insets = new Insets(0, 0, 5, 5);
				gbc_lblAntecedentsConsulations.gridx = 7;
				gbc_lblAntecedentsConsulations.gridy = 0;
				panel.add(lblAntecedentsConsulations, gbc_lblAntecedentsConsulations);
			}
			{
				JLabel lblClient = new JLabel("Client :");
				GridBagConstraints gbc_lblClient = new GridBagConstraints();
				gbc_lblClient.anchor = GridBagConstraints.EAST;
				gbc_lblClient.insets = new Insets(0, 0, 5, 5);
				gbc_lblClient.gridx = 1;
				gbc_lblClient.gridy = 1;
				panel.add(lblClient, gbc_lblClient);
			}
			{
				clientField = new JTextField();
				clientField.setText(selectedRdv.getAnimal().getClient().getNomClient());
				clientField.setEnabled(false);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 1;
				panel.add(clientField, gbc_textField);
				clientField.setColumns(10);
			}
			{
				textArea.setText(selectedRdv.getAnimal().getAntecedents());
				GridBagConstraints gbc_textArea = new GridBagConstraints();
				gbc_textArea.gridheight = 6;
				gbc_textArea.insets = new Insets(0, 0, 5, 0);
				gbc_textArea.gridwidth = 8;
				gbc_textArea.fill = GridBagConstraints.BOTH;
				gbc_textArea.gridx = 4;
				gbc_textArea.gridy = 1;
				panel.add(textArea, gbc_textArea);
			}
			{
				JLabel lblAnimal = new JLabel("Animal :");
				GridBagConstraints gbc_lblAnimal = new GridBagConstraints();
				gbc_lblAnimal.anchor = GridBagConstraints.EAST;
				gbc_lblAnimal.insets = new Insets(0, 0, 5, 5);
				gbc_lblAnimal.gridx = 1;
				gbc_lblAnimal.gridy = 2;
				panel.add(lblAnimal, gbc_lblAnimal);
			}
			{
				codeAnimalField = new JTextField();
				codeAnimalField.setText(Integer.toString(selectedRdv.getAnimal().getCodeAnimal()));
				codeAnimalField.setEnabled(false);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.gridx = 2;
				gbc_textField_1.gridy = 2;
				panel.add(codeAnimalField, gbc_textField_1);
				codeAnimalField.setColumns(10);
			}
			{
				nomAnimalField = new JTextField();
				nomAnimalField.setText(selectedRdv.getAnimal().getNomAnimal());
				nomAnimalField.setEnabled(false);
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.insets = new Insets(0, 0, 5, 5);
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.gridx = 2;
				gbc_textField_2.gridy = 3;
				panel.add(nomAnimalField, gbc_textField_2);
				nomAnimalField.setColumns(10);
			}
			{
				sexeAnimalField = new JTextField();
				sexeAnimalField.setText(selectedRdv.getAnimal().getSexe());
				sexeAnimalField.setEnabled(false);
				GridBagConstraints gbc_sexeAnimalField = new GridBagConstraints();
				gbc_sexeAnimalField.fill = GridBagConstraints.HORIZONTAL;
				gbc_sexeAnimalField.insets = new Insets(0, 0, 5, 5);
				gbc_sexeAnimalField.gridx = 3;
				gbc_sexeAnimalField.gridy = 3;
				panel.add(sexeAnimalField, gbc_sexeAnimalField);
				sexeAnimalField.setColumns(10);
			}

			{
				couleurAnimalField = new JTextField();
				couleurAnimalField.setText(selectedRdv.getAnimal().getCouleur());
				couleurAnimalField.setEnabled(false);
				GridBagConstraints gbc_textField_3 = new GridBagConstraints();
				gbc_textField_3.insets = new Insets(0, 0, 5, 5);
				gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_3.gridx = 2;
				gbc_textField_3.gridy = 4;
				panel.add(couleurAnimalField, gbc_textField_3);
				couleurAnimalField.setColumns(10);
			}
			{
				especeAnimalField = new JTextField();
				especeAnimalField.setText(selectedRdv.getAnimal().getEspece());
				especeAnimalField.setEnabled(false);
				GridBagConstraints gbc_especeAnimalField = new GridBagConstraints();
				gbc_especeAnimalField.insets = new Insets(0, 0, 5, 5);
				gbc_especeAnimalField.fill = GridBagConstraints.HORIZONTAL;
				gbc_especeAnimalField.gridx = 2;
				gbc_especeAnimalField.gridy = 5;
				panel.add(especeAnimalField, gbc_especeAnimalField);
				especeAnimalField.setColumns(10);
			}
			{
				tatouageAnimalField = new JTextField(" ");
				if (tatouageAnimalField.getText().equals(""))
					tatouageAnimalField.setText("Non renseigné");

				else
					tatouageAnimalField.setText(selectedRdv.getAnimal().getTatouage());
				tatouageAnimalField.setEnabled(false);
				GridBagConstraints gbc_tatouageAnimalField = new GridBagConstraints();
				gbc_tatouageAnimalField.insets = new Insets(0, 0, 5, 5);
				gbc_tatouageAnimalField.fill = GridBagConstraints.HORIZONTAL;
				gbc_tatouageAnimalField.gridx = 2;
				gbc_tatouageAnimalField.gridy = 6;
				panel.add(tatouageAnimalField, gbc_tatouageAnimalField);
				tatouageAnimalField.setColumns(10);

			}
			{
				btnNewButton = new JButton("Valider");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Animal animal = selectedRdv.getAnimal();
						animal.setAntecedents(textArea.getText());
						try {
							animalclientmanager.updateAnimal(animal);
							dispose();

						} catch (BLLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton.gridx = 8;
				gbc_btnNewButton.gridy = 7;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				btnNewButton_1 = new JButton("Annuler");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton_1.gridx = 9;
				gbc_btnNewButton_1.gridy = 7;
				panel.add(btnNewButton_1, gbc_btnNewButton_1);
			}
		}

	}

}
