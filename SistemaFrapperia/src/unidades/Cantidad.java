package unidades;

import java.io.Serializable;

public class Cantidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UnidadMedida unidad;
	private int cantidad;
	
	public Cantidad(UnidadMedida unidad, int cantidad) {
		super();
		this.unidad = unidad;
		this.cantidad = cantidad;
	}
	
	public String getSimboloUnidad(){
		return unidad.getSimbolo();
	}
	
	public UnidadMedida getUnidad() {
		return unidad;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setUnidad(UnidadMedida unidad) {
		this.unidad = unidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
