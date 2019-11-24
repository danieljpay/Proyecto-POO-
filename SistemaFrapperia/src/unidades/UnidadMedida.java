package unidades;

import java.io.Serializable;

public abstract class UnidadMedida implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String simbolo;
	// factor de equivalencia con la unidad "base". 
	// Por ejemplo, kg tiene una equivalencia de 1000 respecto al gramo.
	//private double equivalencia;
	
	
	public UnidadMedida(String nombre, String simbolo) {
		super();
		this.nombre = nombre;
		this.simbolo = simbolo;
	}
	
	// getters -------------------------
	public String getNombre() {
		return nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	// setters --------------------------
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}
