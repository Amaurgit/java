package fr.eni.clinique.IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.BLL.AnimalClientManager;
import fr.eni.clinique.BLL.BLLException;
import fr.eni.clinique.BLL.PersonnelManager;
import fr.eni.clinique.BLL.RdvManager;
import fr.eni.clinique.BO.Animal;
import fr.eni.clinique.BO.Client;
import fr.eni.clinique.BO.Personnel;
import fr.eni.clinique.BO.Rdv;
import fr.eni.clinique.DAL.DALException;
import fr.eni.clinique.TOOLS.DateLabelFormatter;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

public class EcranRdv extends JFrame {

	private JPanel contentPane;
	private Client client = new Client();
	private RdvManager rdvM = RdvManager.getInstance();
	private Rdv selectedRdv;
	private Animal animal = new Animal();
	private Personnel veterinaire = new Personnel();
	Calendar calendar = GregorianCalendar.getInstance();
	private JTable table;

	public EcranRdv() throws BLLException, DALException {
		setBounds(100, 100, 557, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JComboBox<Personnel> cbVeterinaire = new JComboBox<Personnel>();
		List<Personnel> veto = PersonnelManager.getInstance().getPersonnel();
		for (int i = 0; i < veto.size(); i++)
			if (veto.get(i).getRole().equals("vet")) {
				cbVeterinaire.addItem(veto.get(i));
			}
		if (veto.size() > 0) {
			veterinaire = veto.get(0);
		}

		// Combobox animal
		JComboBox<Animal> cbAnimal = new JComboBox<Animal>();
		cbAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				animal = (Animal) cbAnimal.getModel().getSelectedItem();

			}
		});
		List<Animal> animaux = AnimalClientManager.getInstance().getAnimalsbyId(client.getCodeClient());
		for (int i = 0; i < animaux.size(); i++)
			cbAnimal.addItem((Animal) animaux.get(i));

		GridBagConstraints gbc_cbAnimal = new GridBagConstraints();
		gbc_cbAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_cbAnimal.gridx = 0;
		gbc_cbAnimal.gridy = 5;
		if (animaux.size() > 0) {
			animal = animaux.get(0);
		}

		RdvDynamicList rdl = new RdvDynamicList(veto.get(0));

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 212, 0, 44, 19, 41, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 164, 63, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_cbVeterinaire = new GridBagConstraints();
		gbc_cbVeterinaire.insets = new Insets(0, 0, 5, 5);
		gbc_cbVeterinaire.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbVeterinaire.gridx = 0;
		gbc_cbVeterinaire.gridy = 1;
		cbVeterinaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				veterinaire = (Personnel) cbVeterinaire.getModel().getSelectedItem();
				try {
					rdl.updateData(veterinaire);

				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JLabel labelVeterinaire = new JLabel("Vétérinaire :");
		GridBagConstraints gbc_labelVeterinaire = new GridBagConstraints();
		gbc_labelVeterinaire.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelVeterinaire.insets = new Insets(0, 0, 5, 5);
		gbc_labelVeterinaire.gridx = 0;
		gbc_labelVeterinaire.gridy = 0;
		contentPane.add(labelVeterinaire, gbc_labelVeterinaire);

		JLabel lblDateRdv = new JLabel("Date RDV :");
		GridBagConstraints gbc_lblDateRdv = new GridBagConstraints();
		gbc_lblDateRdv.anchor = GridBagConstraints.WEST;
		gbc_lblDateRdv.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateRdv.gridx = 2;
		gbc_lblDateRdv.gridy = 0;
		contentPane.add(lblDateRdv, gbc_lblDateRdv);
		contentPane.add(cbVeterinaire, gbc_cbVeterinaire);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		SpringLayout springLayout_1 = (SpringLayout) datePicker.getLayout();
		springLayout_1.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST,
				datePicker);
		datePicker.setShowYearButtons(true);

		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.gridwidth = 4;
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.gridx = 2;
		gbc_datePicker.gridy = 1;
		contentPane.add(datePicker, gbc_datePicker);

		JComboBox heures = new JComboBox();
		heures.setModel(new DefaultComboBoxModel(
				new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18" }));
		GridBagConstraints gbc_heures = new GridBagConstraints();
		gbc_heures.insets = new Insets(0, 0, 5, 5);
		gbc_heures.fill = GridBagConstraints.HORIZONTAL;
		gbc_heures.gridx = 2;
		gbc_heures.gridy = 2;
		contentPane.add(heures, gbc_heures);

		JLabel lblH = new JLabel("h");
		GridBagConstraints gbc_lblH = new GridBagConstraints();
		gbc_lblH.insets = new Insets(0, 0, 5, 5);
		gbc_lblH.anchor = GridBagConstraints.EAST;
		gbc_lblH.gridx = 3;
		gbc_lblH.gridy = 2;
		contentPane.add(lblH, gbc_lblH);

		JComboBox minutes = new JComboBox();
		minutes.setModel(new DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));
		GridBagConstraints gbc_minutes = new GridBagConstraints();
		gbc_minutes.fill = GridBagConstraints.HORIZONTAL;
		gbc_minutes.insets = new Insets(0, 0, 5, 5);
		gbc_minutes.gridx = 4;
		gbc_minutes.gridy = 2;
		contentPane.add(minutes, gbc_minutes);

		JButton btnUrgence = new JButton("Urgence");
		btnUrgence.setBackground(new Color(255, 69, 0));
		GridBagConstraints gbc_btnUrgence = new GridBagConstraints();
		gbc_btnUrgence.insets = new Insets(0, 0, 5, 0);
		gbc_btnUrgence.gridx = 5;
		gbc_btnUrgence.gridy = 2;
		contentPane.add(btnUrgence, gbc_btnUrgence);

		// ANIMAL
		JLabel labelAnimal = new JLabel("Animal :");
		GridBagConstraints gbc_labelAnimal = new GridBagConstraints();
		gbc_labelAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_labelAnimal.gridx = 0;
		gbc_labelAnimal.gridy = 4;
		contentPane.add(labelAnimal, gbc_labelAnimal);

		contentPane.add(cbAnimal, gbc_cbAnimal);

		// CLIENT
		// GBC
		GridBagConstraints gbc_cbClient = new GridBagConstraints();
		gbc_cbClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbClient.insets = new Insets(0, 0, 5, 5);
		gbc_cbClient.gridx = 0;
		gbc_cbClient.gridy = 3;

		JLabel labelClient = new JLabel("Client :");
		GridBagConstraints gbc_labelClient = new GridBagConstraints();
		gbc_labelClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelClient.anchor = GridBagConstraints.NORTH;
		gbc_labelClient.insets = new Insets(0, 0, 5, 5);
		gbc_labelClient.gridx = 0;
		gbc_labelClient.gridy = 2;
		contentPane.add(labelClient, gbc_labelClient);

		// Combobox client
		JComboBox<Client> cbClient = new JComboBox<>();
		cbClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = (Client) cbClient.getSelectedItem();
				try {
					majAnimaux(client.getCodeClient(), cbAnimal);
				} catch (BLLException e1) {
					e1.printStackTrace();
				}

			}
		});

		// Populate Combobox client
		List<Client> clients = AnimalClientManager.getInstance().getClient();
		for (int i = 0; i < clients.size(); i++) {
			cbClient.addItem(clients.get(i));
		}

		contentPane.add(cbClient, gbc_cbClient);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 6;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		contentPane.add(separator, gbc_separator);

		JButton button_2 = new JButton("Annuler");
		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable(rdl);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = table.getSelectedRow();
					selectedRdv = rdl.getValueAt(index);
				}
			}
		});

		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.WEST;
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 9;
		contentPane.add(button_2, gbc_button_2);

		JButton supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rdvM.removeRdv(selectedRdv.getPersonnel(), selectedRdv.getAnimal(), selectedRdv.getDateRdv());
					rdl.updateData(veterinaire);
				} catch (BLLException e1) {
					e1.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 9;
		contentPane.add(supprimer, gbc_button);

		JButton boutonValider = new JButton("Valider");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// GESTION DATE DATEPICKER
				String heure = (String) heures.getSelectedItem();
				String minute = (String) minutes.getSelectedItem();

				Date date = (Date) datePicker.getModel().getValue();

				calendar.setTime(date);
				calendar.set(Calendar.MILLISECOND, 0);
				calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(heure));
				calendar.set(Calendar.MINUTE, Integer.valueOf(minute));
				System.out.println(calendar.toString());

				date = calendar.getTime();

				Timestamp dateOk = new java.sql.Timestamp((date.getTime()));

				Rdv rdvToInsert = new Rdv();
				rdvToInsert.setAnimal(animal);
				rdvToInsert.setPersonnel(veterinaire);
				rdvToInsert.setDateRdv(dateOk);

				try {
					rdvM.insert(rdvToInsert);
					rdl.updateData(veterinaire);

				} catch (BLLException e) {
					JOptionPane.showMessageDialog(contentPane, "Impossible d'ajouter un RDV sur un RDV existant");
					e.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 9;
		contentPane.add(boutonValider, gbc_button_1);
	}

	public List<Animal> majAnimaux(int codeClient, JComboBox combobox) throws BLLException {
		codeClient = client.getCodeClient();
		combobox.removeAllItems();
		List<Animal> animaux = new ArrayList<Animal>();
		try {
			animaux = AnimalClientManager.getInstance().getAnimalsbyId(codeClient);
			for (int i = 0; i < animaux.size(); i++) {
				combobox.addItem((Animal) animaux.get(i));
			}
			if (animaux.size() > 0) {
				animal = animaux.get(0);
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		return animaux;
	}

}
