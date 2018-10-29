package controlador;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import modelo.Estanteria;
import modelo.Libro;
import vista.UI;

public class ParaUI extends UI {

	private static final long serialVersionUID = 1L;
	private Estanteria estanteria;

	public ParaUI() {
		this.estanteria = new Estanteria();
		cargarPintarLista();
		getTxtCambios().setVisible(false);
		enableAllForm(false);
		aniadirListeners();
		enableComponents(false, getBtnAlta(), getBtnGuardar(), getBtnModificar(), getBtnBaja(),
				getBtnAumentarEjemplares(), getBtnVenderEjemplares());
	}

	private void cargarPintarLista() {
		getModeloListaLibros().removeAllElements();
		getList().removeAll();
		ArrayList<Libro> cargarLista = this.estanteria.cargarLista();
		int i=0;
		for (Libro libro : cargarLista) {
			i++;
			System.out.println(i);
			getModeloListaLibros().addElement(libro.getTitulo() + ";" + libro.getISBN());
		}
		getList().setModel(getModeloListaLibros());
		getBtnOk().setVisible(false);
		cargarLista.clear();
	}

	private void aniadirListeners() {
		listenerAlta();
		listenerBaja();
		listenerGuardar();
		listenerModificar();
		listenerNuevo();
		listenerLista();
		listenerTxtISBN();
		listenerVenderComprarEjemplares();
		listenerComprar();
		listenerVender();
	}

	private void listenerVender() {
		getBtnVenderEjemplares().addActionListener(e -> {
			enableAllForm(false);
			getTxtCambios().setText("1");
			getTxtCambios().setVisible(true);
			getBtnOk().setVisible(true);
			enableComponents(false, getBtnAumentarEjemplares());
		});
	}

	private void listenerComprar() {
		getBtnAumentarEjemplares().addActionListener(e -> {
			enableAllForm(false);
			getTxtCambios().setText("1");
			getTxtCambios().setVisible(true);
			getBtnOk().setVisible(true);
			enableComponents(false, getBtnAumentarEjemplares(), getBtnVenderEjemplares());
		});

	}

	private void listenerTxtISBN() {
		getTxtISBN().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (getISBN().length() == 13) {
					Libro libro = estanteria.buscarLibro(getISBN());
					if (libro != null) {
						mostrarDatosPantalla(libro);
						setMensaje(Mensaje.MOSTRAR_LIBRO.getMensaje());
					} else {
						enableAllForm(true);
						cleanJTextComponents(getTxtAutor(), getTxtPaginas(), getTxtTitulo());
						getTxtEjemplares().setText("");
						setMensaje(Mensaje.ISBN_NO_EXISTE_ADD_LIBRO.getMensaje());
					}
				} else {
					enableAllForm(false);
					editableJTextComponents(true, getTxtISBN());
					setMensaje(Mensaje.ISBN_MAL_ESCRITO.getMensaje());
				}
			}
		});
	}

	private void listenerLista() {
		getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String isbn = getList().getSelectedValue().substring(getList().getSelectedValue().indexOf(';')+1,getList().getSelectedValue().length());
				mostrarDatosPantalla(estanteria.buscarLibro(isbn));
				enableComponents(true, getBtnBaja(), getBtnModificar(), getBtnAumentarEjemplares(),
						getBtnVenderEjemplares());
				enableComponents(false, getBtnAlta());
				enableAllForm(false);
				setMensaje(Mensaje.MOSTRAR_LIBRO.getMensaje());
			}

		});
	}

	private void listenerNuevo() {
		getBtnNuevo().addActionListener(e -> {
			getTxtCambios().setVisible(false);
			getBtnOk().setVisible(false);
			cleanAll();
			enableComponents(true, getBtnAlta());
			editableJTextComponents(true, getTxtISBN());
			enableComponents(false, getBtnBaja(), getBtnModificar(), getBtnGuardar(), getBtnAumentarEjemplares(),
					getBtnVenderEjemplares());
			setMensaje(Mensaje.LIBRO_NUEVO.getMensaje());
		});
	}

	private void listenerModificar() {
		getBtnModificar().addActionListener(e -> {
			getTxtCambios().setVisible(false);
			getBtnOk().setVisible(false);
			editableJTextComponents(true, getTxtAutor(), getTxtPaginas(), getTxtTitulo(), getTxtEditorial(),
					getTxtEjemplares());
			enableComponents(false, getBtnBaja(), getBtnModificar());
			enableComponents(true, getBtnGuardar());
			setMensaje(Mensaje.MODIFICACION_PREPARADA.getMensaje());
		});
	}

	private void listenerGuardar() {
		getBtnGuardar().addActionListener(e -> {
			enableAllForm(false);
			enableComponents(false, getBtnGuardar());
			if (new Validador().validar(getPanelInformacion(), getLblMensaje())) {
				recogerDatos(estanteria.buscarLibro(getISBN()));
				estanteria.borrarLibro(getISBN());
				darAlta(new Libro());
				cargarPintarLista();
				cleanAll();
				setMensaje(Mensaje.MODIFICACION_GUARDADA.getMensaje());
			}
		});
	}
public String getISBN() {
	return getTxtISBN().getText();
}

	private void listenerBaja() {
		getBtnBaja().addActionListener(e -> {
			getTxtCambios().setVisible(false);
			getBtnOk().setVisible(false);
			if (Integer.valueOf(getTxtEjemplares().getText()) > 0) {
				int dialog = JOptionPane.showConfirmDialog(null,
						"Esta usted realmente seguro de que desea borrar ese libro para siempre");
				if (dialog == 0) {
					borradorDeLibros();
				}
			}else{
				borradorDeLibros();
			}
		});
	}
	private void borradorDeLibros() {
		String isbn = getISBN();
		estanteria.borrarLibro(getISBN());
		getModeloListaLibros().removeElementAt(getList().getSelectedIndex());
		cleanAll();
		setMensaje(Mensaje.BAJA_COMPLETA_ELIMINAR.getMensaje());
	}

	private void listenerVenderComprarEjemplares() {
		getBtnOk().addActionListener(e -> {
			String isbn = getISBN();
			if (new Validador().validar(getPanelInformacion(), getLblMensaje())) {
				if (getBtnVenderEjemplares().isEnabled()) {
					enableComponents(false, getBtnVenderEjemplares());
					if (estanteria.buscarLibro(isbn).getUnidades() > 0) {
						if (estanteria.buscarLibro(isbn).getUnidades() >= Integer.valueOf(getTxtCambios().getText())) {
							estanteria.decrementarEjemplares(getISBN(),
									Integer.valueOf(getTxtCambios().getText()));
							setMensaje(Mensaje.BAJA_COMPLETA_DISMINUIR.getMensaje());
						} else {
							setMensaje(Mensaje.LIBROS_INSUFICIENTES.getMensaje());
						}
					} else {
						setMensaje(Mensaje.SIN_UNIDADES.getMensaje());
					}
				} else {
					setMensaje(Mensaje.ADD_UNIDADES.getMensaje());
					estanteria.aumentarUnidades(getISBN(), Integer.valueOf(getTxtCambios().getText()));
				}
			}
			getTxtEjemplares().setText(String.valueOf(estanteria.buscarLibro(isbn).getUnidades()));
			getTxtCambios().setVisible(false);
			getBtnOk().setVisible(false);
		});
	}

	private void listenerAlta() {
		getBtnAlta().addActionListener(e -> {
			if (new Validador().validar(getPanelInformacion(), getLblMensaje())) {
				if (!estanteria.libroRepetido(getISBN())) {
					darAlta(new Libro());
					enableAllForm(false);
					enableComponents(false, getBtnAlta());
					cleanAll();
					setMensaje(Mensaje.ALTA_COMPLETA.getMensaje());
				} else {
					setMensaje(Mensaje.LIBRO_REPETIDO.getMensaje());
				}
			}
		});
	}

	private void cleanAll() {
		cleanJTextComponents(getTxtAutor(), getTxtEjemplares(), getTxtISBN(), getTxtPaginas(), getTxtTitulo(),getTxtEditorial());
		getComboTema().setSelectedIndex(0);
		getBtnGroupCheck().clearSelection();
		getBtnGroupRadio().clearSelection();
	}

	private void cleanJTextComponents(JTextComponent... comp) {
		for (int i = 0; i < comp.length; i++) {
			comp[i].setText("");
		}
	}

	private void enableAllForm(boolean enable) {
		enableComponents(enable, getComboTema(), getRdbtnNovedad(), getChckbxGrapado(), getRdbtnReedicion(),
				getChckbxCartone(), getChckbxRustica(), getChckbxTapaDura());
		editableJTextComponents(enable, getTxtAutor(), getTxtISBN(), getTxtPaginas(), getTxtTitulo(), getTxtEditorial(),
				getTxtEjemplares());
	}

	private void editableJTextComponents(boolean editable, JTextComponent... comp) {
		for (int i = 0; i < comp.length; i++) {
			comp[i].setEditable(editable);
		}
	}

	private void enableComponents(boolean enable, Component... comp) {
		for (int i = 0; i < comp.length; i++) {
			comp[i].setEnabled(enable);
		}
	}

	private void mostrarDatosPantalla(Libro libro) {
		sacarDatosPantalla(libro);
		ponerRadiobtn(libro.getEstado());
		ponercheck(libro);
	}

	private void sacarDatosPantalla(Libro libro) {
		getTxtTitulo().setText(libro.getTitulo());
		getTxtISBN().setText(libro.getISBN());
		getTxtAutor().setText(libro.getAutor());
		getTxtPaginas().setText(String.valueOf(libro.getNumPaginas()));
		getComboTema().setSelectedIndex(libro.getTema());
		getTxtEditorial().setText(libro.getEditorial());
		getTxtEjemplares().setText(String.valueOf(libro.getUnidades()));
	}

	private void setMensaje(String mensaje) {
		getLblMensaje().setText(mensaje);
	}

	private void ponercheck(Libro libro) {
		getChckbxCartone().setSelected(libro.getFormato(0));
		getChckbxRustica().setSelected(libro.getFormato(1));
		getChckbxTapaDura().setSelected(libro.getFormato(2));
	}

	private void ponerRadiobtn(boolean estado) {
		if (estado) {
			getRdbtnNovedad().setSelected(true);
		} else {
			getRdbtnReedicion().setSelected(true);
		}
	}

	private void darAlta(Libro libro) {
		recogerDatos(libro);
		getModeloListaLibros().addElement(getTxtTitulo().getText() + ";" + getISBN());
		getList().setModel(getModeloListaLibros());
		estanteria.insertarLibro(libro);
	}

	private void recogerDatos(Libro nuevoLibro) {
		nuevoLibro.setAutor(getTxtAutor().getText());
		nuevoLibro.setEstado(getRdbtnNovedad().isSelected());
		nuevoLibro.setFormato(0, getChckbxCartone().isSelected());
		nuevoLibro.setFormato(1, getChckbxRustica().isSelected());
		nuevoLibro.setFormato(2, getChckbxTapaDura().isSelected());
		nuevoLibro.setFormato(3, getChckbxGrapado().isSelected());
		nuevoLibro.setISBN(getISBN());
		nuevoLibro.setNumPaginas(Integer.valueOf(getTxtPaginas().getText()));
		nuevoLibro.setTitulo(getTxtTitulo().getText());
		nuevoLibro.setTema(getComboTema().getSelectedIndex());
		nuevoLibro.setEditorial(getTxtEditorial().getText());
		nuevoLibro.setUnidades(Integer.valueOf(getTxtEjemplares().getText()));
	}

}
