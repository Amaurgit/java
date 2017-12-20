package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.Gestion_Connexion;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class EcranDialConnexion extends JDialog {
	JPasswordField mdp;
	JTextField utilisateur;

	public EcranDialConnexion() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Connexion");
		setBounds(100, 100, 336, 152);
		setAlwaysOnTop(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 120, 0, 13, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 0, 0, 43, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblNewLabel = new JLabel("Code utilisateur :");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			utilisateur = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 2;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 1;
			getContentPane().add(utilisateur, gbc_textField);
			utilisateur.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Mot de passe :");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		mdp = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 2;
		getContentPane().add(mdp, gbc_passwordField);

		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (utilisateur.getText().equals("")) {
						utilisateur.setText("Merci de saisir un code utilisateur");
					}
					if (mdp.equals("")) {
						mdp.setText("Merci de saisir votre mot de passe");
					}

					Gestion_Connexion gc = new Gestion_Connexion();
					EcranSec ecranSec = EcranSec.getInstance();
					EcranAdm ecranAdm = EcranAdm.getInstance();
					EcranVet ecranVet = EcranVet.getInstance();

					try {
						if (gc.isAdmOk(Integer.valueOf((utilisateur.getText())), String.valueOf(mdp.getPassword()))) {
							ecranAdm.setVisible(true);
							ecranSec.setVisible(false);
							ecranVet.setVisible(false);
							dispose();

						}
						if (gc.isSecOk(Integer.valueOf((utilisateur.getText())), String.valueOf(mdp.getPassword()))) {
							ecranSec.setVisible(true);
							ecranAdm.setVisible(false);
							ecranVet.setVisible(false);
							dispose();

						}
						if (gc.isVetOk(Integer.valueOf((utilisateur.getText())), String.valueOf(mdp.getPassword()))) {
							ecranVet.setVisible(true);
							ecranAdm.setVisible(false);
							ecranSec.setVisible(false);
							dispose();

						}

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});

			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.EAST;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 3;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 4;
		gbc_cancelButton.gridy = 3;
		getContentPane().add(cancelButton, gbc_cancelButton);
		cancelButton.setActionCommand("Annuler");

	}

	private static EcranDialConnexion INSTANCE = new EcranDialConnexion();

	public static EcranDialConnexion getInstance() {
		return INSTANCE;
	}

	public void afficheConnexion(EcranDialConnexion ecran) {
		ecran.setVisible(true);
		mdp.setText("");
		utilisateur.setText("");

	}

}
