package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import vista.PanelInformacion;


public class Validador {


	
	
	private  boolean validarTexto(String cadena) {
		Pattern textoPatron = Pattern.compile("[A-Za-z\\s]{3,20}");
		Matcher testTexto = textoPatron.matcher(cadena);
		return testTexto.matches();
	}

	private  boolean validarNumeros(String cadena) {
		Pattern numerosPatron = Pattern.compile("[\\d]+");
		Matcher testNumeros = numerosPatron.matcher(cadena);
		return testNumeros.matches();
	}

	public  boolean validarISBN(String cadena) {
		Pattern isbnPatron = Pattern.compile("[\\d]{13}");
		Matcher testISBN = isbnPatron.matcher(cadena);
		return testISBN.matches();
	}

	private boolean validarTextoConNUmeros(String cadena) {
		Pattern textoPatron = Pattern.compile("[A-Za-z\\s\\d]{1,}");
		Matcher testTextoConNumeros = textoPatron.matcher(cadena);
		return testTextoConNumeros.matches();
	}

	boolean validar(PanelInformacion panelInformacion,JLabel label) {
		if (panelInformacion.getTxtISBN().getText().isEmpty() || panelInformacion.getTxtAutor().getText().isEmpty()
				|| panelInformacion.getTxtPaginas().getText().isEmpty()
				|| panelInformacion.getTxtTitulo().getText().isEmpty() 
				|| panelInformacion.getTxtEditorial().getText().isEmpty()
				|| panelInformacion.getTxtEjemplares().getText().isEmpty()
				|| panelInformacion.getBtnGroupRadio().getSelection()==null
				|| panelInformacion.getBtnGroupCheck().getSelection()==null){
			label.setText(Mensaje.CAMPOS_VACIOS.getMensaje());
			return false;
		}
		if (!validarISBN(panelInformacion.getTxtISBN().getText())) {
			label.setText(Mensaje.ISBN_MAL_ESCRITO.getMensaje());
			return false;
		}
		if (!validarTexto(panelInformacion.getTxtTitulo().getText())) {
			label.setText(Mensaje.TITULO_MAL_ESCRITO.getMensaje());
			return false;
		}
		if (!validarTexto(panelInformacion.getTxtEditorial().getText())) {
			label.setText(Mensaje.EDITORIAL_MAL_ESCRITO.getMensaje());
			return false;
		}
		if (!validarTextoConNUmeros(panelInformacion.getTxtAutor().getText())) {
			label.setText(Mensaje.AUTOR_MAL_ESCRITO.getMensaje());
			return false;
		}
		if (!validarNumeros(panelInformacion.getTxtPaginas().getText())) {
			label.setText(Mensaje.NPAG_MAL_ESCRITO.getMensaje());
			return false;
		}
		if (!validarNumeros(panelInformacion.getTxtEjemplares().getText())) {
			label.setText(Mensaje.EJEMPLARES_MAL_ESCRITO.getMensaje());
			return false;
		}
		
		return true;
	}
}
