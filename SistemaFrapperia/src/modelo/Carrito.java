package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class Carrito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// atributos
	private Controlador controlador;
	// lista de frappes del carrito.
	private ArrayList<ArrayList<Frappe>> listaFrappes; // matriz de frappes
	
	// constructor
	public Carrito() {
		this.listaFrappes = new ArrayList<ArrayList<Frappe>>();
	}

	// métodos -----------
	// agrega un frappe al carrito
	public void agregarFrappe(Frappe frappe) {
		
		if(frappeRepetido(frappe)) {
			int columna = buscarColumnaDe(frappe);
			// añadimos el frappe a la columna correspondiente
			this.listaFrappes.get(columna).add(frappe);
		}
		else {
			ArrayList<Frappe> columna = new ArrayList<Frappe>();
			columna.add(frappe);
			this.listaFrappes.add(columna);
		}
	}
	// evalua si el frappe que queremos agregar al carrrito está "repetido" o no. 
	// si hay más de ese tipo de frappe.
	public boolean frappeRepetido(Frappe frappe) {
		
		int columna = buscarColumnaDe(frappe);
		if(columna != -1) {
			return true;
		}
		
		return false;
	}
	
	// devuelve el index de la columna que coincida con el nombre del frappe dado.
	// devuelve -1 si no se encuentra una coincidencia.
	public int buscarColumnaDe(Frappe frappe) {
		
		for(int i=0; i<this.listaFrappes.size(); i++) {
			ArrayList<Frappe> columna = this.listaFrappes.get(i);
			if(columna.get(0).getNombre().equals(frappe.getNombre())) {
				return i;
			}
		}
		return -1;
	}
	
	// elimina un frappe del carrito
	public void eliminarFrappe(Frappe frappe) {
		int columna = buscarColumnaDe(frappe);
		
		if(columna != -1) {
			this.listaFrappes.get(columna).remove(0);
			if(this.listaFrappes.get(columna).size() == 0) {
				this.listaFrappes.remove(columna);
			}
		}
	}
	// genera y devuelve la tabla del carrito.
	public DefaultTableModel getTablaCarrito() {
		DefaultTableModel tablaCarrito = new DefaultTableModel();
		
		// columnas
		//tablaCarrito.addColumn("numero");
		tablaCarrito.addColumn("nombre");
		tablaCarrito.addColumn("precio");
		tablaCarrito.addColumn("cantidad");
		
		// titulos
		Vector<String> titulos = new Vector<String>();
		//titulos.add("#");
		titulos.add("Nombre");
		titulos.add("Precio"); // individual 
		titulos.add("Cantidad");
		tablaCarrito.addRow(titulos);
		
		/*
		// añadimos un vector vacío para mostrar un espacio.
		Vector<String> espacio = new Vector<String>();
		//espacio.add("");
		espacio.add("");
		espacio.add("");
		espacio.add("");
		tablaCarrito.addRow(espacio);
		*/
		
		for(int i=0; i<this.listaFrappes.size(); i++) {
			ArrayList<Frappe> columnaFrappes = this.listaFrappes.get(i);
			
			Vector<String> fila = new Vector<String>();
			//fila.add("" + (i+1));
			fila.add(columnaFrappes.get(0).getNombre());
			fila.add("$ " + columnaFrappes.get(0).getPrecio());
			fila.add("x" + columnaFrappes.size());	
			
			tablaCarrito.addRow(fila);
		}
		
		return tablaCarrito;
	}

	
	public String toString() {
		String lista = "";
		
		for(int i=0; i<this.listaFrappes.size(); i++) {
			ArrayList<Frappe> columnaFrappes = this.listaFrappes.get(i);
			
			if(i == listaFrappes.size()-1) {
				lista = lista + columnaFrappes.get(0).getNombre() + " " + "x" + columnaFrappes.size();
			} else {
				lista = lista + columnaFrappes.get(0).getNombre() + " " + "x" + columnaFrappes.size() + ", ";
			}
			
		}
		
		return lista;
	}
	//public ArrayList<String> frappes
	
	
	
	// getters ----------
	public Controlador getControlador() {
		return controlador;
	}
	public ArrayList<ArrayList<Frappe>> getListaFrappes() {
		return listaFrappes;
	}
	
	// setters -------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setListaFrappes(ArrayList<ArrayList<Frappe>> listaFrappes) {
		this.listaFrappes = listaFrappes;
	}
	
	
	
}
