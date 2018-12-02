package vista;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

	
	private static final long serialVersionUID = 5164739545410400426L;
	private JPanel contentPane;
	private PanelInformacion panelInformacion=new PanelInformacion();
	public JButton getBtnOk() {
		return panelInformacion.getBtnOk();
	}
	private DefaultListModel<String> modeloListaLibros = new DefaultListModel<>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JList<String> list = new JList(modeloListaLibros);
	private JMenuItem btnNuevo;
	private JMenuItem btnAlta;
	private JMenuItem btnVenderEjemplares;
	private JMenuItem btnBaja;
	private JMenuItem btnModificar;
	private JMenuItem btnGuardar;
	private JMenuItem mntmVersion;
	private JMenuItem mntmSalir;
	private JScrollPane scrollPane;
	



	public JMenuItem getMntmDeleteTema() {
		return mntmDeletetema;
	}
	
	
	public JMenuItem getMntmUpdateTema() {
		return mntmUpdatetema;
	}

	public JMenuItem getBtnVenderEjemplares() {
		return btnVenderEjemplares;
	}

	public JTextField getTxtEditorial() {
		return panelInformacion.getTxtEditorial();
	}

	public JTextField getTxtEjemplares() {
		return panelInformacion.getTxtEjemplares();
	}

	public JTextField getTxtCambios() {
		return panelInformacion.getTxtCambios();
	}

	public JMenuItem getBtnAumentarEjemplares() {
		return btnAumentarEjemplares;
	}
	private JMenuItem btnAumentarEjemplares;
	private JMenuItem mntmAniadirTema;
	private JMenuItem mntmDeletetema;
	private JMenuItem mntmUpdatetema;
	private JMenu mnTema;
	
	public DefaultListModel<String> getModeloListaLibros() {
		return modeloListaLibros;
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAlta = new JMenu("Alta");
		menuBar.add(mnAlta);
		
		btnNuevo = new JMenuItem("Nuevo");
		mnAlta.add(btnNuevo);
		
		btnAlta = new JMenuItem("Dar de alta");
		mnAlta.add(btnAlta);
		
		btnAumentarEjemplares = new JMenuItem("Aumentar Ejemplares");
		mnAlta.add(btnAumentarEjemplares);
		
		JMenu mnBaja = new JMenu("Baja");
		menuBar.add(mnBaja);
		
		btnVenderEjemplares = new JMenuItem("Vender ejemplares");
		mnBaja.add(btnVenderEjemplares);
		
		btnBaja = new JMenuItem("Dar de baja");
		mnBaja.add(btnBaja);
		
		JMenu mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);
		
		btnModificar = new JMenuItem("Modificar Datos");
		mnModificar.add(btnModificar);
		
		btnGuardar = new JMenuItem("Guardar");
		mnModificar.add(btnGuardar);
		
		mnTema = new JMenu("Tema");
		menuBar.add(mnTema);
		
		mntmAniadirTema = new JMenuItem("A\u00F1adirTema");
		mnTema.add(mntmAniadirTema);
		
		mntmUpdatetema = new JMenuItem("UpdateTema");
		mnTema.add(mntmUpdatetema);
		
		mntmDeletetema = new JMenuItem("DeleteTema");
		mnTema.add(mntmDeletetema);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmVersion = new JMenuItem("Version");
		mntmVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Libreria que almacena en una base de datos \n"
						+ "      Creada por: Abel Hermoso \n"
						+ "             Version 4",
						"Version Info", 1);
			}
		});
		mnHelp.add(mntmVersion);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialog = JOptionPane.showConfirmDialog(null,
						"Esta usted realmente seguro de que desea salir de la aplicacion");
				if (dialog == 0) {
					System.exit(0);
				}
				
			}
		});
		mnHelp.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 0, 147, 0};
		gbl_contentPane.rowHeights = new int[] {0, 30, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLibreria = new JLabel("titulo");
		lblLibreria.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		GridBagConstraints gbc_lblLibreria = new GridBagConstraints();
		gbc_lblLibreria.gridwidth = 3;
		gbc_lblLibreria.insets = new Insets(0, 0, 5, 0);
		gbc_lblLibreria.gridx = 0;
		gbc_lblLibreria.gridy = 0;
		contentPane.add(lblLibreria, gbc_lblLibreria);
		
		JLabel lblLibros = new JLabel("Libros:");
		GridBagConstraints gbc_lblLibros = new GridBagConstraints();
		gbc_lblLibros.insets = new Insets(0, 0, 5, 0);
		gbc_lblLibros.gridx = 2;
		gbc_lblLibros.gridy = 1;
		contentPane.add(lblLibros, gbc_lblLibros);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		
		list = new JList<>();
		scrollPane.setViewportView(list);
		

		JPanel paneEmergente = new JPanel();
		GridBagConstraints gbc_paneEmergente = new GridBagConstraints();
		gbc_paneEmergente.gridheight = 2;
		gbc_paneEmergente.insets = new Insets(0, 0, 0, 5);
		gbc_paneEmergente.fill = GridBagConstraints.BOTH;
		gbc_paneEmergente.gridx = 1;
		gbc_paneEmergente.gridy = 1;
		contentPane.add(paneEmergente, gbc_paneEmergente);
		GridBagLayout gbl_paneEmergente = new GridBagLayout();
		gbl_paneEmergente.columnWidths = new int[]{401, 0};
		gbl_paneEmergente.rowHeights = new int[]{364, 0};
		gbl_paneEmergente.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_paneEmergente.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		paneEmergente.setLayout(gbl_paneEmergente);
		GridBagConstraints gbc_panelInformacion = new GridBagConstraints();
		gbc_panelInformacion.fill = GridBagConstraints.BOTH;
		gbc_panelInformacion.gridx = 0;
		gbc_panelInformacion.gridy = 0;
		panelInformacion.setBackground(Color.GRAY);
		paneEmergente.add(panelInformacion, gbc_panelInformacion);
	} 
	
	

	public JMenuItem getMntmAniadirTema() {
		return mntmAniadirTema;
	}

	public JMenuItem getBtnNuevo() {
		return btnNuevo;
	}

	public JMenuItem getBtnAlta() {
		return btnAlta;
	}

	public JMenuItem getBtnBaja() {
		return btnBaja;
	}

	public JMenuItem getBtnModificar() {
		return btnModificar;
	}

	public JMenuItem getBtnGuardar() {
		return btnGuardar;
	}

	public PanelInformacion getPanelInformacion() {
		return panelInformacion;
	}

	public JList<String> getList() {
		return list;
	}

	public JCheckBox getChckbxGrapado() {
		return panelInformacion.getChckbxGrapado();
	}

	public JRadioButton getRdbtnReedicion() {
		return panelInformacion.getRdbtnReedicion();
	}

	public JRadioButton getRdbtnNovedad() {
		return panelInformacion.getRdbtnNovedad();
	}

	public JCheckBox getChckbxRustica() {
		return panelInformacion.getChckbxRustica();
	}

	public JCheckBox getChckbxTapaDura() {
		return panelInformacion.getChckbxTapaDura();
	}

	public JCheckBox getChckbxCartone() {
		return panelInformacion.getChckbxCartone();
	}

	public JTextField getTxtTitulo() {
		return panelInformacion.getTxtTitulo();
	}
	public JTextField getTxtAutor() {
		return panelInformacion.getTxtAutor();
	}
	public JTextField getTxtPaginas() {
		return panelInformacion.getTxtPaginas();
	}
	public ButtonGroup getBtnGroupRadio() {
		return panelInformacion.getBtnGroupRadio();
	}

	public ButtonGroup getBtnGroupCheck() {
		return panelInformacion.getBtnGroupCheck();
	}

	public JTextField getTxtISBN() {
		return panelInformacion.getTxtISBN();
	}

	public JComboBox<String> getComboTema() {
		return panelInformacion.getComboTema();
	}
	public JLabel getLblMensaje() {
		return panelInformacion.getLblMensaje();
	}
}
