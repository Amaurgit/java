package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.DAL.DALException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

public class RechercheClient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField rechercheClientParNomField;
	private AnimalClientManager acm = AnimalClientManager.getInstance();
	private Client selectedClient;
	private JTable table;

	public RechercheClient() throws BLLException, DALException {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String nom = null;
		ClientDynamicList cdl = new ClientDynamicList(nom);

		setBounds(100, 100, 650, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			rechercheClientParNomField = new JTextField("Nom du client");

			{
				JLabel label = new JLabel("");
				label.setIcon(
						new ImageIcon(RechercheClient.class.getResource("/fr/eni/clinique/IHM/ressources/find.png")));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				contentPanel.add(label, gbc_label);
			}
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 9;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(rechercheClientParNomField, gbc_textField);
			rechercheClientParNomField.setColumns(10);

			rechercheClientParNomField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String motCle = rechercheClientParNomField.getText();
						try {
							acm.getClientByName(motCle);
						} catch (BLLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							cdl.updateData(motCle);
						} catch (BLLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			});

			JButton btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String motCle = rechercheClientParNomField.getText();
					try {

						acm.getClientByName(motCle);
						try {
							cdl.updateData(motCle);
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			btnRechercher.setIcon(
					new ImageIcon(RechercheClient.class.getResource("/fr/eni/clinique/IHM/ressources/find.png")));
			GridBagConstraints gbc_btnRechercher = new GridBagConstraints();
			gbc_btnRechercher.insets = new Insets(0, 0, 5, 0);
			gbc_btnRechercher.gridwidth = 2;
			gbc_btnRechercher.gridx = 10;
			gbc_btnRechercher.gridy = 0;
			contentPanel.add(btnRechercher, gbc_btnRechercher);
		}
		{
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 12;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				contentPanel.add(scrollPane, gbc_scrollPane);
				{
					table = new JTable(cdl);

					table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (e.getValueIsAdjusting()) {
								int index = table.getSelectedRow();
								selectedClient = cdl.getValueAt(index);
								dispose();

								try {
									GestionClients gc = new GestionClients(selectedClient);
									gc.setVisible(true);
								} catch (BLLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (DALException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					});
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
