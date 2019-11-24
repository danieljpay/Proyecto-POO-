package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import unidades.Caja;
import unidades.Cantidad;

public class Inventario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Aquí se llevará el control de los ingredientes.
	 * y de los frappes disponibles para la venta.
	 */
	private Controlador controlador;
	private ArrayList<Ingrediente> listaIngredientes;
	
	public Inventario() {
		listaIngredientes = new ArrayList<Ingrediente>();
	}
	
	// método para agregar un ingredientes
	public void agregarIngrediente(String nombre, String costo, String cantidad) {
		
		double costoCompra = Double.parseDouble(costo);
		int cantidadDisponible = Integer.parseInt(cantidad);
		
		Ingrediente ingrediente = new Ingrediente(nombre, costoCompra, cantidadDisponible);
		listaIngredientes.add(ingrediente);
	}
	public void eliminarIngrediente(int index) {
		listaIngredientes.remove(index);
	}
	
	// nuevo método para agregar -------------------------------------------------------------
	public void agregarIngrediente(String nombre, String costo, Cantidad cantidad) {
		double costoCompra = Double.parseDouble(costo);
		
		Ingrediente ingrediente = new Ingrediente(nombre, costoCompra, cantidad);
		listaIngredientes.add(ingrediente);
	}
	// ---------------------------------------------------------------------------------------
	
	// cambia la información de ingrediente en el index indicado.
	public void editarIngrediente(int index, String costo, String cantidad) {
		
		//this.listaIngredientes.get(index).setNombre(nombre);
		this.listaIngredientes.get(index).setCostoCompra(Double.parseDouble(costo));
		//this.listaIngredientes.get(index).setCantidadDisponible(Integer.parseInt(cantidad));
		this.listaIngredientes.get(index).getCantidad().setCantidad(Integer.parseInt(cantidad));
	}
	public void editarIngrediente(int index, String costo, String cantidad, String piezas) {
		this.listaIngredientes.get(index).setCostoCompra(Double.parseDouble(costo));
		this.listaIngredientes.get(index).getCantidad().setCantidad(Integer.parseInt(cantidad));
		
		if(this.listaIngredientes.get(index).getCantidad().getUnidad() instanceof Caja) {
			Caja caja = (Caja) this.listaIngredientes.get(index).getCantidad().getUnidad();
			caja.setPiezas(Integer.parseInt(piezas));
			this.listaIngredientes.get(index).getCantidad().setUnidad(caja);
		}
		
	}
	
	// devuelve el ingrediente en el index indicado.
	public Ingrediente getIngrediente(int index) {
		return listaIngredientes.get(index);
	}
	public int getIngrediente(String nombre) {
		
		//Ingrediente ingrediente= new Ingrediente();
		
		for(int i= 0; i<listaIngredientes.size(); i++) {
			if(listaIngredientes.get(i).getNombre().equals(nombre)) {
				return i;
			}
		}
		
		return -1;
		//return ingrediente;
	}
	
	// disminuye la cantidad disponible de un ingrediente dado el index.
	public void disminuirCantidadIngrediente(int index) {
		
		Ingrediente ingrediente = listaIngredientes.get(index);
		
		int cantidadDisponible = ingrediente.getCantidadDisponible();
		ingrediente.setCantidadDisponible(cantidadDisponible - 1);
		
		listaIngredientes.set(index, ingrediente);
	}
	
	// método que devuelve la tabla de ingredientes
	public DefaultTableModel getTablaIngredientes() {
		
		DefaultTableModel tablaIngredientes = new DefaultTableModel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			// desactivamos edición de columnas.
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		
		tablaIngredientes.addColumn("index");
		tablaIngredientes.addColumn("nombre");
		tablaIngredientes.addColumn("costo");
		tablaIngredientes.addColumn("cantidad");
		
		Vector<String> titulos = new Vector<String>();
		titulos.add("#");
		titulos.add("Nombre");
		titulos.add("Costo");
		titulos.add("Cantidad");
		tablaIngredientes.addRow(titulos);
		
		for(int i=0; i<listaIngredientes.size(); i++) {
			Ingrediente ingrediente = listaIngredientes.get(i);
			
			Vector<String> nuevaFila = new Vector<String>();
			
			nuevaFila.add(String.valueOf(i+1)); // index
			nuevaFila.add(ingrediente.getNombre()); // nombre
			nuevaFila.add("$" + ingrediente.getCostoCompra()); // costo
			//nuevaFila.add(String.valueOf(ingrediente.getCantidadDisponible())); // cantidad
			
			if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
				Caja caja = (Caja) ingrediente.getCantidad().getUnidad();
				nuevaFila.add("" + ingrediente.getCantidad().getCantidad() + " " + ingrediente.getCantidad().getSimboloUnidad() + " - " + caja.getPiezas() + "pzs/caja");
			} else {
				nuevaFila.add("" + ingrediente.getCantidad().getCantidad() + " " + ingrediente.getCantidad().getSimboloUnidad());
			}
			
			
			tablaIngredientes.addRow(nuevaFila);
		}
		
		return tablaIngredientes;
	}
	
	// devuelve la lista de ingredientes
	public DefaultListModel<String> getListaIngredientes() {
		DefaultListModel<String> lista = new DefaultListModel<String>();
		
		for(int i=0; i<this.listaIngredientes.size(); i++) {
			lista.addElement(this.listaIngredientes.get(i).getNombre());
		}
		
		return lista;
	}
	
	
	
	// recibe un nombre y determina si ya existe un ingrediente o no con el mismo nombre.
	public boolean nombreRepetido(String nombre) {
		
		nombre = quitarEspaciosDelanteros(nombre);
		nombre = quitarEspaciosTraseros(nombre);
		
		for(int i=0; i<this.listaIngredientes.size(); i++) {
			String nombreAux = this.listaIngredientes.get(i).getNombre();
			nombreAux = quitarEspaciosDelanteros(nombreAux);
			nombreAux = quitarEspaciosTraseros(nombreAux);
			
			if(nombre.equals(nombreAux)) {
				return true;
			}
		}
		
		return false;
	}
	public String quitarEspaciosDelanteros(String cadena) {
		
		
		while(cadena.startsWith(" ")) {
			// quitamos el espacio.
			cadena = cadena.substring(1);
		}
		
		return cadena;
	}
	public String quitarEspaciosTraseros(String cadena) {
		
		while(cadena.endsWith(" ")) {
			// quitamos el espacio
			cadena = cadena.substring(0, cadena.length()-1);
		}
		
		return cadena;
	}
	
	
	// getters -----------
	public Controlador getControlador() {
		return controlador;
	}
	public ArrayList<Ingrediente> getIngredientes() {
		return listaIngredientes;
	}
	// setters ------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.listaIngredientes = ingredientes;
	}
	
	
	
}
