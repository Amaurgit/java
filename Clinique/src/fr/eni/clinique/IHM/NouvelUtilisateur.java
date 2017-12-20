package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Personnel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NouvelUtilisateur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField newUserTxF = new JTextField();
	private JTextField passUserTfx = new JTextField();
	private JComboBox comboRoleUser = new JComboBox();
	private PersonnelManager dao = PersonnelManager.getInstance();

	public NouvelUtilisateur(PersonnelDynamicList pdl) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Ajout d'un nouvel utilisateur");
		setBounds(100, 100, 443, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 38, 38, 38, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,
				1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel newUserLbl = new JLabel("Nom de l'utilisateur :");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridwidth = 7;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(newUserLbl, gbc_lblNewLabel);
		}
		{

			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 8;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 9;
			gbc_textField.gridy = 0;
			contentPanel.add(newUserTxF, gbc_textField);
			newUserTxF.setColumns(10);
		}
		{
			JLabel roleUserLbl = new JLabel("Rôle de l'utilisateur :");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.gridwidth = 6;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 3;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(roleUserLbl, gbc_lblNewLabel_1);
		}
		{

			comboRoleUser.setModel(new DefaultComboBoxModel(new String[] { "vet", "sec", "adm" }));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 8;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 9;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboRoleUser, gbc_comboBox);
		}
		{
			JLabel passwordUserLbl = new JLabel("Mot de passe utilisateur :");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.gridwidth = 7;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(passwordUserLbl, gbc_lblNewLabel_2);
		}
		{
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.gridwidth = 10;
			gbc_textField_2.insets = new Insets(0, 0, 0, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 9;
			gbc_textField_2.gridy = 2;
			contentPanel.add(passUserTfx, gbc_textField_2);
			passUserTfx.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Personnel personnel = new Personnel();

						personnel.setRole(comboRoleUser.getModel().getSelectedItem().toString());
						personnel.setNom(newUserTxF.getText());
						personnel.setRole(comboRoleUser.getModel().getSelectedItem().toString());
						// personnel.setRole(role);
						personnel.setMotPasse(passUserTfx.getText());

						try {
							if (passUserTfx.getText().trim().equals("") || newUserTxF.getText().trim().equals("")) {

								JOptionPane.showMessageDialog(contentPanel, "Merci de saisir tous les champs");

							} else
								dao.addPersonnel(personnel);
							pdl.updateData();
							dispose();

						} catch (BLLException e) {
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();

					}
				});
				cancelButton.setActionCommand("");
				buttonPane.add(cancelButton);
			}
		}
	}

}
