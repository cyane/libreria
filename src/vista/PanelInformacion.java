package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import modelo.Tema;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JButton;

public class PanelInformacion extends JPanel {
	private static final String FUENTE = "Comic Sans MS";
	private static final Font FONT_BOLD = new Font(FUENTE, Font.BOLD, 13);
	private static final Font FONT_ITALIC = new Font(FUENTE, Font.ITALIC, 12);
	private static final long serialVersionUID = -961732316263997753L;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtPaginas;
	private JTextField txtISBN;
	private JComboBox<Tema> comboTema;
	private JLabel lblMensaje;
	private JPanel panelEstado;
	private JPanel panelOtro;
	private JRadioButton rdbtnReedicion;
	private JRadioButton rdbtnNovedad;
	private JCheckBox chckbxRustica;
	private JCheckBox chckbxTapaDura;
	private JCheckBox chckbxCartone;

	public JTextField getTxtEditorial() {
		return txtEditorial;
	}

	public JTextField getTxtEjemplares() {
		return txtEjemplaresfF;
	}

	public JTextField getTxtCambios() {
		return txtCambios;
	}

	private JLabel lblEstado;
	private JLabel formato;
	private final ButtonGroup btnGroupRadio = new ButtonGroup();
	private final ButtonGroup btnGroupCheck = new ButtonGroup();
	private JTextField txtEditorial;
	private JLabel lblEditorial;
	private JTextField txtEjemplaresfF;
	private JLabel lblEjemplares;
	private JTextField txtCambios;
	private JButton btnOk;
	private JCheckBox chckbxGrapado;

	

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	public PanelInformacion() {
		setBackground(Color.LIGHT_GRAY);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25, 62, 141, 0, 85, 10, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setFont(FONT_BOLD);
		lblEstado.setForeground(Color.BLACK);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado.anchor = GridBagConstraints.SOUTH;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 4;
		gbc_lblEstado.gridy = 0;
		add(lblEstado, gbc_lblEstado);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(FONT_BOLD);
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.fill = GridBagConstraints.BOTH;
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 1;
		gbc_lblIsbn.gridy = 1;
		add(lblIsbn, gbc_lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setToolTipText("Introduzca el ISBN. Numerico");
		GridBagConstraints gbc_txtISBN = new GridBagConstraints();
		gbc_txtISBN.insets = new Insets(0, 0, 5, 5);
		gbc_txtISBN.fill = GridBagConstraints.BOTH;
		gbc_txtISBN.gridx = 2;
		gbc_txtISBN.gridy = 1;
		add(txtISBN, gbc_txtISBN);
		txtISBN.setColumns(10);
		
		panelEstado = new JPanel();
		panelEstado.setBackground(Color.LIGHT_GRAY);
		panelEstado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.gridheight = 2;
		gbc_panelEstado.insets = new Insets(0, 0, 5, 5);
		gbc_panelEstado.fill = GridBagConstraints.BOTH;
		gbc_panelEstado.gridx = 4;
		gbc_panelEstado.gridy = 1;
		add(panelEstado, gbc_panelEstado);
		panelEstado.setLayout(new GridLayout(2, 1, 0, 0));
		
		rdbtnNovedad = new JRadioButton("Novedad");
		rdbtnNovedad.setFont(FONT_ITALIC);
		rdbtnNovedad.setBackground(Color.LIGHT_GRAY);
		btnGroupRadio.add(rdbtnNovedad);
		rdbtnNovedad.setHorizontalAlignment(SwingConstants.CENTER);
		panelEstado.add(rdbtnNovedad);
		
		rdbtnReedicion = new JRadioButton("Reedicion");
		rdbtnReedicion.setFont(FONT_ITALIC);
		rdbtnReedicion.setBackground(Color.LIGHT_GRAY);
		btnGroupRadio.add(rdbtnReedicion);
		rdbtnReedicion.setHorizontalAlignment(SwingConstants.CENTER);
		panelEstado.add(rdbtnReedicion);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(FONT_BOLD);
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.fill = GridBagConstraints.BOTH;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 2;
		add(lblTitulo, gbc_lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setToolTipText("Introduzca el Titulo. Alfabetico");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.BOTH;
		gbc_txtTitulo.gridx = 2;
		gbc_txtTitulo.gridy = 2;
		add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setFont(FONT_BOLD);
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditorial.anchor = GridBagConstraints.EAST;
		gbc_lblEditorial.gridx = 1;
		gbc_lblEditorial.gridy = 3;
		add(lblEditorial, gbc_lblEditorial);
		
		txtEditorial = new JTextField();
		GridBagConstraints gbc_txtEditorial = new GridBagConstraints();
		gbc_txtEditorial.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditorial.fill = GridBagConstraints.BOTH;
		gbc_txtEditorial.gridx = 2;
		gbc_txtEditorial.gridy = 3;
		add(txtEditorial, gbc_txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(FONT_BOLD);
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.fill = GridBagConstraints.BOTH;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.gridx = 1;
		gbc_lblAutor.gridy = 4;
		add(lblAutor, gbc_lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setToolTipText("Introduzca el Autor. Alfabetico");
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.insets = new Insets(0, 0, 5, 5);
		gbc_txtAutor.fill = GridBagConstraints.BOTH;
		gbc_txtAutor.gridx = 2;
		gbc_txtAutor.gridy = 4;
		add(txtAutor, gbc_txtAutor);
		txtAutor.setColumns(10);
		
		formato = new JLabel("Formato");
		formato.setFont(FONT_BOLD);
		formato.setForeground(Color.BLACK);
		formato.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_Formato = new GridBagConstraints();
		gbc_Formato.fill = GridBagConstraints.HORIZONTAL;
		gbc_Formato.anchor = GridBagConstraints.SOUTH;
		gbc_Formato.insets = new Insets(0, 0, 5, 5);
		gbc_Formato.gridx = 4;
		gbc_Formato.gridy = 4;
		add(formato, gbc_Formato);
		
		lblEjemplares = new JLabel("Ejemplares:");
		lblEjemplares.setFont(FONT_BOLD);
		GridBagConstraints gbc_lblEjemplares = new GridBagConstraints();
		gbc_lblEjemplares.insets = new Insets(0, 0, 5, 5);
		gbc_lblEjemplares.anchor = GridBagConstraints.EAST;
		gbc_lblEjemplares.gridx = 1;
		gbc_lblEjemplares.gridy = 5;
		add(lblEjemplares, gbc_lblEjemplares);
		
		txtEjemplaresfF = new JTextField();
		GridBagConstraints gbc_txtEjemplaresfF = new GridBagConstraints();
		gbc_txtEjemplaresfF.insets = new Insets(0, 0, 5, 5);
		gbc_txtEjemplaresfF.fill = GridBagConstraints.BOTH;
		gbc_txtEjemplaresfF.gridx = 2;
		gbc_txtEjemplaresfF.gridy = 5;
		add(txtEjemplaresfF, gbc_txtEjemplaresfF);
		txtEjemplaresfF.setColumns(10);
		
		panelOtro = new JPanel();
		panelOtro.setBackground(Color.LIGHT_GRAY);
		panelOtro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelOtro = new GridBagConstraints();
		gbc_panelOtro.gridheight = 3;
		gbc_panelOtro.insets = new Insets(0, 0, 5, 5);
		gbc_panelOtro.fill = GridBagConstraints.BOTH;
		gbc_panelOtro.gridx = 4;
		gbc_panelOtro.gridy = 5;
		add(panelOtro, gbc_panelOtro);
		panelOtro.setLayout(new GridLayout(4, 0, 0, 1));
		
		chckbxRustica = new JCheckBox("Rustica");
		chckbxRustica.setFont(FONT_ITALIC);
		chckbxRustica.setBackground(Color.LIGHT_GRAY);
		btnGroupCheck.add(chckbxRustica);
		chckbxRustica.setHorizontalAlignment(SwingConstants.CENTER);
		panelOtro.add(chckbxRustica);
		
		chckbxTapaDura = new JCheckBox("Espiral");
		chckbxTapaDura.setFont(FONT_ITALIC);
		chckbxTapaDura.setBackground(Color.LIGHT_GRAY);
		btnGroupCheck.add(chckbxTapaDura);
		chckbxTapaDura.setHorizontalAlignment(SwingConstants.CENTER);
		panelOtro.add(chckbxTapaDura);
		
		chckbxCartone = new JCheckBox("Cartone");
		chckbxCartone.setFont(FONT_ITALIC);
		chckbxCartone.setBackground(Color.LIGHT_GRAY);
		btnGroupCheck.add(chckbxCartone);
		chckbxCartone.setHorizontalAlignment(SwingConstants.CENTER);
		panelOtro.add(chckbxCartone);
		
		chckbxGrapado = new JCheckBox("Grapado");
		chckbxGrapado.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxGrapado.setBackground(Color.LIGHT_GRAY);
		btnGroupCheck.add(chckbxGrapado);
		panelOtro.add(chckbxGrapado);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setFont(FONT_BOLD);
		lblTema.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTema = new GridBagConstraints();
		gbc_lblTema.anchor = GridBagConstraints.EAST;
		gbc_lblTema.fill = GridBagConstraints.VERTICAL;
		gbc_lblTema.insets = new Insets(0, 0, 5, 5);
		gbc_lblTema.gridx = 1;
		gbc_lblTema.gridy = 6;
		add(lblTema, gbc_lblTema);
		
		comboTema = new JComboBox();
		comboTema.setToolTipText("Seleccione el tema del libro");
		comboTema.setFont(FONT_BOLD);
		GridBagConstraints gbc_comboTema = new GridBagConstraints();
		gbc_comboTema.insets = new Insets(0, 0, 5, 5);
		gbc_comboTema.fill = GridBagConstraints.BOTH;
		gbc_comboTema.gridx = 2;
		gbc_comboTema.gridy = 6;
		add(comboTema, gbc_comboTema);
		comboTema.setModel(new DefaultComboBoxModel<Tema>(Tema.values()));
		
		JLabel lblPaginas = new JLabel("Paginas:");
		lblPaginas.setFont(FONT_BOLD);
		lblPaginas.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPaginas = new GridBagConstraints();
		gbc_lblPaginas.fill = GridBagConstraints.BOTH;
		gbc_lblPaginas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaginas.gridx = 1;
		gbc_lblPaginas.gridy = 7;
		add(lblPaginas, gbc_lblPaginas);
		
		txtPaginas = new JTextField();
		txtPaginas.setToolTipText("Introduzca el numero de paginas");
		GridBagConstraints gbc_txtPaginas = new GridBagConstraints();
		gbc_txtPaginas.insets = new Insets(0, 0, 5, 5);
		gbc_txtPaginas.fill = GridBagConstraints.BOTH;
		gbc_txtPaginas.gridx = 2;
		gbc_txtPaginas.gridy = 7;
		add(txtPaginas, gbc_txtPaginas);
		txtPaginas.setColumns(10);
		
		txtCambios = new JTextField();
		txtCambios.setToolTipText("INTRODUZCA CANTIDAD");
		txtCambios.setVisible(false);
		GridBagConstraints gbc_txtCambios = new GridBagConstraints();
		gbc_txtCambios.insets = new Insets(0, 0, 5, 5);
		gbc_txtCambios.fill = GridBagConstraints.BOTH;
		gbc_txtCambios.gridx = 2;
		gbc_txtCambios.gridy = 8;
		add(txtCambios, gbc_txtCambios);
		txtCambios.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setVisible(false);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 4;
		gbc_btnOk.gridy = 8;
		add(btnOk, gbc_btnOk);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBackground(Color.ORANGE);
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setFont(FONT_BOLD);
		lblMensaje.setForeground(Color.RED);
		GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
		gbc_lblMensaje.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMensaje.insets = new Insets(0, 0, 0, 5);
		gbc_lblMensaje.gridx = 2;
		gbc_lblMensaje.gridy = 9;
		add(lblMensaje, gbc_lblMensaje);

	}

	public JCheckBox getChckbxGrapado() {
		return chckbxGrapado;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public JTextField getTxtAutor() {
		return txtAutor;
	}

	public JTextField getTxtPaginas() {
		return txtPaginas;
	}

	public JTextField getTxtISBN() {
		return txtISBN;
	}

	public JComboBox<Tema> getComboTema() {
		return comboTema;
	}
	
	public JLabel getLblMensaje() {
		return lblMensaje;
	}
	public ButtonGroup getBtnGroupRadio() {
		return btnGroupRadio;
	}

	public ButtonGroup getBtnGroupCheck() {
		return btnGroupCheck;
	}
	
	public JRadioButton getRdbtnReedicion() {
		return rdbtnReedicion;
	}

	public JRadioButton getRdbtnNovedad() {
		return rdbtnNovedad;
	}

	public JCheckBox getChckbxRustica() {
		return chckbxRustica;
	}

	public JCheckBox getChckbxTapaDura() {
		return chckbxTapaDura;
	}

	public JCheckBox getChckbxCartone() {
		return chckbxCartone;
	}

}
