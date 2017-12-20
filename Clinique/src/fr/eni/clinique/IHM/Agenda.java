package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.TOOLS.DateLabelFormatter;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Agenda extends JFrame {

	private JPanel contentPane;
	private Personnel veterinaire = new Personnel();
	private Rdv selectedRdv;
	private Calendar cal = GregorianCalendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a z");
	private JTable table;

	public Agenda() throws BLLException, DALException {
		setBounds(100, 100, 691, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 93, 54, 107, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 38 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		UtilDateModel model = new UtilDateModel();

		model.setValue(cal.getTime());

		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JLabel lblNewLabel = new JLabel("Vétérinaire");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JComboBox<Personnel> cbVeterinaire = new JComboBox<>();

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(cbVeterinaire, gbc_comboBox);
		List<Personnel> veto = PersonnelManager.getInstance().getPersonnel();
		for (int i = 0; i < veto.size(); i++)
			if (veto.get(i).getRole().equals("vet")) {
				cbVeterinaire.addItem(veto.get(i));
			}

		RdvDynamicList rdl = new RdvDynamicList(veto.get(0), new Timestamp(cal.getTime().getTime()));

		JLabel lblNewLabel_1 = new JLabel("Date ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		datePicker.getJFormattedTextField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					veterinaire = (Personnel) cbVeterinaire.getSelectedItem();

					rdl.updateDataByVetAndDate(veterinaire, (Date) datePicker.getModel().getValue());
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.gridx = 3;
		gbc_datePicker.gridy = 0;
		panel.add(datePicker, gbc_datePicker);
		cbVeterinaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veterinaire = (Personnel) cbVeterinaire.getSelectedItem();

				try {
					rdl.updateDataByVetAndDate(veterinaire, (Date) datePicker.getModel().getValue());
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					veterinaire = (Personnel) cbVeterinaire.getSelectedItem();

					rdl.updateDataByVetAndDate(veterinaire, (Date) datePicker.getModel().getValue());
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable(rdl);
		scrollPane.setViewportView(table);
		
				JButton btnNewButton = new JButton("Dossier Medical");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DossierMedical dossier = new DossierMedical(selectedRdv);
						System.out.println(selectedRdv);
						dossier.setVisible(true);
					}
				});
				btnNewButton.setIcon(new ImageIcon(Agenda.class.getResource("/fr/eni/clinique/IHM/ressources/folder.png")));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
				gbc_btnNewButton.gridx = 3;
				gbc_btnNewButton.gridy = 8;
				panel.add(btnNewButton, gbc_btnNewButton);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = table.getSelectedRow();
					selectedRdv = rdl.getValueAt(index);
				}
			}
		});
	}

}
