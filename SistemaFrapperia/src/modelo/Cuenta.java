package modelo;

import java.io.Serializable;

public class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// atributos
	private String nombre;
	private double saldoPendiente; 
	
	// constructor
	public Cuenta(String nombre) {
		this.nombre = nombre;
		this.saldoPendiente = 0; // el saldo inicial es cero.
	}

	
	// métodos
	public void disminuirSaldoPendiente(double abono) {
		
		if(abono > this.saldoPendiente) {
			setSaldoPendiente(0);
		} else {
			this.saldoPendiente = this.saldoPendiente - abono;
		}
		
	}
	
	public void aumentarSaldoPendiente(double monto) {
		this.saldoPendiente = this.saldoPendiente + monto;
	}
	
	
	// getters -----------
	public String getNombre() {
		return nombre;
	}

	public double getSaldoPendiente() {
		return saldoPendiente;
	}
	// setters --------------
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSaldoPendiente(double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	
	
	
}
