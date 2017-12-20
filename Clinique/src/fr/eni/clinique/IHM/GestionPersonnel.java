package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Personnel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class GestionPersonnel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	PersonnelManager pm = PersonnelManager.getInstance();
	Personnel selectedPersonnel = new Personnel();
	private JTable table;

	public GestionPersonnel() throws BLLException {
		PersonnelDynamicList pdl = new PersonnelDynamicList();
		setBounds(100, 100, 461, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 134, 84, 121, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton button = new JButton("Ajouter");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NouvelUtilisateur nouvelUtilisateur = new NouvelUtilisateur(pdl);
					nouvelUtilisateur.setVisible(true);
				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.EAST;
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 0;
			gbc_button.gridy = 0;
			contentPanel.add(button, gbc_button);
		}
		{
			JButton button = new JButton("Supprimer");
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {

						pm.deletePersonnel(getCodePers());
						pdl.updateData();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 0;
			contentPanel.add(button, gbc_button);
		}
		{
			JButton button = new JButton("R\u00E9initialiser");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ReenitPassword reenitPassword;
					reenitPassword = new ReenitPassword(selectedPersonnel);
					reenitPassword.setVisible(true);

				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.WEST;
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 2;
			gbc_button.gridy = 0;
			contentPanel.add(button, gbc_button);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 4;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable(pdl);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public int getCodePers() throws BLLException {

		PersonnelDynamicList PdS = new PersonnelDynamicList();
		Object codePers = PdS.getValueAt(table.getSelectedRow(), 0);
		int intCodePers = codePers != null ? Integer.parseInt(codePers.toString()) : 0;
		return intCodePers;
	}
}
