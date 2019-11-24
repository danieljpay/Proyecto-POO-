package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import controlador.Controlador;

public class Cuentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controlador controlador;

	// atributos
	private ArrayList<Cuenta> listaCuentas;
	
	// constructor
	public Cuentas() {
		listaCuentas = new ArrayList<Cuenta>();
	}

	// métodos
	public void agregarCuenta(Cuenta cuenta) {
		this.listaCuentas.add(cuenta);
	}
	public void eliminarCuenta(Cuenta cuenta) {
		int index = this.listaCuentas.indexOf(cuenta);
		this.listaCuentas.remove(index);
	}
	public Cuenta getUnaCuenta(int index) {
		return listaCuentas.get(index);
	}
	
	public void abonarACuenta(int index, double abono) {
		this.listaCuentas.get(index).disminuirSaldoPendiente(abono);
	}
	public void aumentarSaldo(int index, double monto) {
		this.listaCuentas.get(index).aumentarSaldoPendiente(monto);		
	}
	
	// devuelve la lista de cuentas como DefaultListModel
	public DefaultListModel<String> getModeloListaCuentas() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		for(int i=0; i<listaCuentas.size(); i++) {
			modelo.addElement(listaCuentas.get(i).getNombre());
		}
		
		return modelo;
	}
	
	// recibe un nombre y determina si ya existe una cuenta o no con el mismo nombre.
	public boolean nombreRepetido(String nombre) {
		
		nombre = quitarEspaciosDelanteros(nombre);
		nombre = quitarEspaciosTraseros(nombre);
		
		for(int i=0; i<this.listaCuentas.size(); i++) {
			String nombreAux = this.listaCuentas.get(i).getNombre();
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

	public ArrayList<Cuenta> getListaCuentas() {
		return listaCuentas;
	}
	// setters --------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
	
}
