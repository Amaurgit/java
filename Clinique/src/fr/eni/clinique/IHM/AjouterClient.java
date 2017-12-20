package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.DAL.DALException;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterClient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField clientNameField;
	private JTextField clientNickField;
	private JTextField clientAdressField;
	private JTextField clientCpField;
	private JTextField clientTownField;
	private JLabel lblNewLabel;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblCodepostal;
	private JLabel lblVille;
	private JTextField clientAdress2Field;
	private AnimalClientManager acm = new AnimalClientManager() ;




	public AjouterClient() {
		setTitle("Ajout d'un Nouveau Client");
		setBounds(100, 100, 378, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{77, 172, 67, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNewLabel = new JLabel("Nom");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			clientNameField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 2;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(clientNameField, gbc_textField);
			clientNameField.setColumns(10);
		}
		{
			lblPrnom = new JLabel("Pr\u00E9nom");
			GridBagConstraints gbc_lblPrnom = new GridBagConstraints();
			gbc_lblPrnom.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrnom.anchor = GridBagConstraints.EAST;
			gbc_lblPrnom.gridx = 0;
			gbc_lblPrnom.gridy = 1;
			contentPanel.add(lblPrnom, gbc_lblPrnom);
		}
		{
			clientNickField = new JTextField();
			clientNickField.setColumns(10);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 2;
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(clientNickField, gbc_textField_1);
		}
		{
			lblAdresse = new JLabel("Adresse");
			GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
			gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
			gbc_lblAdresse.anchor = GridBagConstraints.EAST;
			gbc_lblAdresse.gridx = 0;
			gbc_lblAdresse.gridy = 2;
			contentPanel.add(lblAdresse, gbc_lblAdresse);
		}
		{
			clientAdressField = new JTextField();
			clientAdressField.setColumns(10);
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.gridwidth = 2;
			gbc_textField_6.insets = new Insets(0, 0, 5, 0);
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 1;
			gbc_textField_6.gridy = 2;
			contentPanel.add(clientAdressField, gbc_textField_6);
		}
		
		{
			clientAdress2Field = new JTextField();
			clientAdress2Field.setColumns(10);
			GridBagConstraints gbc_clientAdress2Field = new GridBagConstraints();
			gbc_clientAdress2Field.gridwidth = 2;
			gbc_clientAdress2Field.insets = new Insets(0, 0, 5, 5);
			gbc_clientAdress2Field.fill = GridBagConstraints.HORIZONTAL;
			gbc_clientAdress2Field.gridx = 1;
			gbc_clientAdress2Field.gridy = 3;
			contentPanel.add(clientAdress2Field, gbc_clientAdress2Field);
		}
		{
			lblCodepostal = new JLabel("CodePostal");
			GridBagConstraints gbc_lblCodepostal = new GridBagConstraints();
			gbc_lblCodepostal.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodepostal.anchor = GridBagConstraints.EAST;
			gbc_lblCodepostal.gridx = 0;
			gbc_lblCodepostal.gridy = 4;
			contentPanel.add(lblCodepostal, gbc_lblCodepostal);
		}
		{
			clientCpField = new JTextField();
			clientCpField.setColumns(10);
			GridBagConstraints gbc_clientCpField = new GridBagConstraints();
			gbc_clientCpField.gridwidth = 2;
			gbc_clientCpField.insets = new Insets(0, 0, 5, 0);
			gbc_clientCpField.fill = GridBagConstraints.HORIZONTAL;
			gbc_clientCpField.gridx = 1;
			gbc_clientCpField.gridy = 4;
			contentPanel.add(clientCpField, gbc_clientCpField);
		}
		{
			lblVille = new JLabel("Ville");
			GridBagConstraints gbc_lblVille = new GridBagConstraints();
			gbc_lblVille.insets = new Insets(0, 0, 5, 5);
			gbc_lblVille.anchor = GridBagConstraints.EAST;
			gbc_lblVille.gridx = 0;
			gbc_lblVille.gridy = 5;
			contentPanel.add(lblVille, gbc_lblVille);
		}
		{
			clientTownField = new JTextField();
			clientTownField.setColumns(10);
			GridBagConstraints gbc_clientTownField = new GridBagConstraints();
			gbc_clientTownField.gridwidth = 2;
			gbc_clientTownField.insets = new Insets(0, 0, 5, 0);
			gbc_clientTownField.fill = GridBagConstraints.HORIZONTAL;
			gbc_clientTownField.gridx = 1;
			gbc_clientTownField.gridy = 5;
			contentPanel.add(clientTownField, gbc_clientTownField);
		}
		{
			JButton okButton = new JButton("Valider");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Client client = new Client();
					client.setNomClient(clientNameField.getText());
					client.setPrenomClient(clientNickField.getText());
					client.setAdresse1(clientAdressField.getText());
					client.setAdresse2(clientAdress2Field.getText());
					client.setCodePostal(clientCpField.getText());
					client.setVille(clientTownField.getText());
					try {
						acm.addClient(client);
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					try {
						GestionClients gestionClient = new GestionClients(client);
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			okButton.setIcon(new ImageIcon(AjouterClient.class.getResource("/fr/eni/clinique/IHM/ressources/icon.png")));
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.anchor = GridBagConstraints.EAST;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 1;
			gbc_okButton.gridy = 6;
			contentPanel.add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Annuler");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setIcon(new ImageIcon(AjouterClient.class.getResource("/fr/eni/clinique/IHM/ressources/go-back-button.png")));
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.EAST;
			gbc_cancelButton.gridx = 2;
			gbc_cancelButton.gridy = 6;
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
