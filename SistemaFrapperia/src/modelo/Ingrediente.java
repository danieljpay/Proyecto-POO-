package modelo;

import java.io.Serializable;
import unidades.Cantidad;

public class Ingrediente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private double costoCompra;
	private int cantidadDisponible;	
	// prueba
	private Cantidad cantidad;
	
	public Ingrediente(String nombre, double costoCompra, int cantidadDisponible) {
		super();
		this.nombre = nombre;
		this.costoCompra = costoCompra;
		this.cantidadDisponible = cantidadDisponible;
	}
	
	// prueba
	public Ingrediente(String nombre, double costoCompra, Cantidad cantidad) {
		this.nombre = nombre;
		this.costoCompra = costoCompra;
		this.cantidad = cantidad;
	}
	
	public Ingrediente() {
		
	}
	
	
	// getters --------
	public String getNombre() {
		return nombre;
	}
	public double getCostoCompra() {
		return costoCompra;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	// setters ---------------
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCostoCompra(double costoCompra) {
		this.costoCompra = costoCompra;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	
	// prueba
	public Cantidad getCantidad() {
		return cantidad;
	}
	public void setCantidad(Cantidad cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
