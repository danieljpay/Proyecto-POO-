/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

//import java.util.ArrayList;
//import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author plupy
 */
public class Venta implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Controlador controlador;
    private Carrito carrito;
    private Double precioTotal;
    //private Date fecha; //usar java date()  
    private Date fecha; // en la que se realizó la venta.
    
    
    public Venta() {
    	this.carrito = new Carrito();
    	fecha = new Date();
    }
    
    //Métodos 
    
    
    //Agregar y eliminar frappes del carrito.
    public void agregarFrappe(Frappe frappe){
        carrito.agregarFrappe(frappe);
    }
    public void eliminarFrappe(Frappe frappe) {
    	carrito.eliminarFrappe(frappe);
    }    
    
    // devuelve la tabla del carrito.
    public DefaultTableModel getTablaCarrito (){
        return carrito.getTablaCarrito();
    }
    
    
    // devuelve el precio total de la venta.
    public double calcularTotal() {
    	
    	double total = 0;
    	
    	ArrayList<ArrayList<Frappe>> arrayFrappes = carrito.getListaFrappes();
    	
    	for(int i=0; i<arrayFrappes.size(); i++) {
    		ArrayList<Frappe> listaFrappe = arrayFrappes.get(i);
    		for(int k=0; k<listaFrappe.size(); k++) {
    			total = total + listaFrappe.get(k).getPrecio();
    		}
    	}
    	
    	this.precioTotal = total;
    	return total;
    }

    // devuelve el cambio
    public double calcularCambio(double pago) {
    	return pago - calcularTotal();
    }
    
    //MÃ©todos get

    
    public Controlador getControlador() {
        return controlador;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

	public Date getFecha() {
		return fecha;
	}

    
    //MÃ©todos set

    
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
}
