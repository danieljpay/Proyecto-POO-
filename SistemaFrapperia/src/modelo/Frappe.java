package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import unidades.Caja;
import unidades.UnidadMedida;

public class Frappe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// atributos
	private String nombre;
	private float precio;
	private ArrayList<Ingrediente> ingredientes;
	
	//constructor
	public Frappe(String nombre, float precio, ArrayList<Ingrediente> ingredientes)  {
		this.nombre = nombre;
		this.precio = precio;
		this.ingredientes = ingredientes;
	}
	
	public DefaultTableModel getTablaIngredientes() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Cantidad");
		
		for(int i=0; i<ingredientes.size(); i++) {
			Vector<String> fila = new Vector<String>();
			
			fila.add(ingredientes.get(i).getNombre());
			
			UnidadMedida unidad = ingredientes.get(i).getCantidad().getUnidad();
			if(unidad instanceof Caja) {
				Caja caja = (Caja)unidad;
				fila.add(caja.getPiezas() + " pz(s)");
			}else {
				fila.add(ingredientes.get(i).getCantidad().getCantidad() + " " + unidad.getSimbolo());
			}
			modelo.addRow(fila);
		}
		
		return modelo;
	}
	
	// constructor necesario para cuando se quiere crer un nuevo ingrediente. nuevo método.
	public Frappe() {
		
	}
	
	//Metodo para igualar a otro Frappe
	public void igualarFrappe (Frappe frappeAIgualar) {
		this.nombre = frappeAIgualar.getNombre();
		this.precio = frappeAIgualar.getPrecio();
		this.ingredientes = frappeAIgualar.getIngredientes();
	}

	// getters
	public String getNombre() {
		return nombre;
	}

	public float getPrecio() {
		return precio;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	
	
	// setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
	
	
}
