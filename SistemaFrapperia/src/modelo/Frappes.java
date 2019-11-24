package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class Frappes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * aquí se guardan los frappes disponibles para la venta
	 * así como todos los métodos relativos su información.
	 */
	
	private Controlador controlador;
	private ArrayList<Frappe> listaFrappes; // frappes disponibles para la venta
	
	public Frappes() {
		listaFrappes = new ArrayList<Frappe>();
	}
	
	// edita la información del frappe, dado un index.
	public void editarFrappe(int index, String nombre, String precio, ArrayList<Ingrediente> ingredientes) {
		float precioFrappe = Float.parseFloat(precio);
		
		listaFrappes.get(index).setNombre(nombre);
		listaFrappes.get(index).setPrecio(precioFrappe);
		listaFrappes.get(index).setIngredientes(ingredientes);
	}
	
	// elimina un frappe dado su index
	public void eliminarFrappe(int index) {
		listaFrappes.remove(index);
	}
	// agrega un frappe a la lista de frappes disponibles para la venta
	public void agregarFrappe(String nombre, String precio, ArrayList<Ingrediente> ingredientes) {
		Float precioFrappe = Float.parseFloat(precio);
		
		Frappe nuevoFrappe = new Frappe(nombre, precioFrappe, ingredientes);
		listaFrappes.add(nuevoFrappe);
	}
	// devuelve el frappe en el index indicado.
	public Frappe getFrappe(int index) {
		return listaFrappes.get(index);
	}
	// devuelve el frappe de la lista de frappes disponibles, dado su nombre.
	public Frappe getFrappePorNombre(String nombre) {
		
		// provicional, nunca se devolverá este valor inicial, al menos que coincida con el nombre buscado.
		Frappe frappe = this.listaFrappes.get(0);
		
		for(int i=0; i<this.listaFrappes.size(); i++) {
			if((this.listaFrappes.get(i).getNombre()).equals(nombre)) {
				frappe = this.listaFrappes.get(i);
			}
		}
		
		return frappe;
	}
	// genera y devuelve la tabla con la información de los frappes.
	public DefaultTableModel getTablaFrappes() {
		
		DefaultTableModel tablaFrappes = new DefaultTableModel() {
			/**
			 *  
			 */
			private static final long serialVersionUID = 1L;
			// desactivamos edición de celdas.
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		
		
		
		tablaFrappes.addColumn("numero");
		tablaFrappes.addColumn("nombre");
		tablaFrappes.addColumn("precio");
		tablaFrappes.addColumn("ingredientes");
		
		Vector<String> titulos = new Vector<String>();
		titulos.add("Número");
		titulos.add("Nombre");
		titulos.add("Precio");
		titulos.add("Ingredientes");
		tablaFrappes.addRow(titulos);
		
		for(int i=0; i<listaFrappes.size(); i++) {
			Frappe frappe = listaFrappes.get(i);
			Vector<String> nuevaFila = new Vector<String>();
			
			nuevaFila.add("" + (i+1));
			nuevaFila.add(frappe.getNombre());
			nuevaFila.add("$" + frappe.getPrecio());
			
			// generar lista de ingredientes.
			String ingredientes = "";
			ArrayList<Ingrediente> listaIngredientes = frappe.getIngredientes();
			for(int k=0; k<listaIngredientes.size(); k++) {
				Ingrediente ingrediente = listaIngredientes.get(k);
				if(k == listaIngredientes.size()-1) {
					ingredientes = ingredientes + ingrediente.getNombre();  
				} else {
					ingredientes = ingredientes + ingrediente.getNombre() + ",";
				}
			}
			nuevaFila.add(ingredientes);
			
			tablaFrappes.addRow(nuevaFila);
		}
		
		
		return tablaFrappes;
	}
	
	// devuelve la lista de frappes
	public DefaultListModel<String> getModeloListaFrappes() {
		DefaultListModel<String> lista = new DefaultListModel<String>();
		
		for(int i=0; i<this.listaFrappes.size(); i++) {
			Frappe frappe = this.listaFrappes.get(i);
			lista.addElement(frappe.getNombre());
		}
		
		return lista;
	}
	
	// getters ---------
	public Controlador getControlador() {
		return controlador;
	}
	public ArrayList<Frappe> getListaFrappes() {
		return listaFrappes;
	}
	// setters -----------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setListaFrappes(ArrayList<Frappe> listaFrappes) {
		this.listaFrappes = listaFrappes;
	}
	
	
}
