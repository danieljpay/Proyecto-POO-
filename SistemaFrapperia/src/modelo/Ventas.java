/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author plupy
 */
public class Ventas implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Controlador controlador;
    private ArrayList<Venta> listaVentas;
    
    //Constructor
    public Ventas() {
       listaVentas = new ArrayList<Venta>();
    }
    
    
    //Metodos
   
    //Devuelve una venta dada su posicion en la lista de ventas
    public Venta getVenta(int numero){
        int numeroEnArray= numero-1;        //Se resta uno porque el Array comienza a contar en 0
        return listaVentas.get(numeroEnArray);    //Regresa la venta en la posicion deseada de la lista de ventas
    }
    
    //  agrega una venta
    public void agregarVenta(Venta venta) {
    	listaVentas.add(venta);
    }
    
    //Elimina la venta en la posicion indicada
    public Venta eliminarVenta(int numero){
        int numeroEnArray= numero-1;        //Se resta uno porque el Array comienza a contar en 0
        return listaVentas.remove(numeroEnArray);
    }
    
    //Edita la venta en la posiciÃ³n                  ####################   falta revision   ############
    public void editarVenta(int numero, Carrito carrito, Double precioTotal, Date fecha){
        int numeroEnArray = numero-1;    //Se resta uno porque el Array comienza a contar en 0
        listaVentas.get(numeroEnArray).setCarrito(carrito);
        listaVentas.get(numeroEnArray).setPrecioTotal(precioTotal);
        listaVentas.get(numeroEnArray).setFecha(fecha);
    }
    
 // nuevo método para generar Tablas
    //Crea la tabla con la información de las ventas en el arraylist dado
      public DefaultTableModel getTablaVentas(ArrayList<Venta> lista){
          DefaultTableModel tablaVentas = new DefaultTableModel() {
          	
          	/**
  			 * 
  			 */
  			private static final long serialVersionUID = 1L;
  			// desactivamos la edicion de celdas.
  			@Override
          	public boolean isCellEditable(int fila, int columna) {
          		return false;
          	}
          	
          };

          tablaVentas.addColumn("");
          tablaVentas.addColumn("");
          tablaVentas.addColumn("");
          
          Vector<String> titulos = new Vector<String>();
          titulos.add("Carrito");
          titulos.add("PrecioTotal");
          titulos.add("Fecha");
          tablaVentas.addRow(titulos);

          for(int i=0; i<listaVentas.size(); i++) {
              //////Venta venta = listaVentas.get(i);
              Vector<String> nuevaFila = new Vector<String>();

              nuevaFila.add(lista.get(i).getCarrito().toString());
              nuevaFila.add("$ " + String.valueOf(lista.get(i).calcularTotal()));
              nuevaFila.add(lista.get(i).getFecha().toString());
              
              tablaVentas.addRow(nuevaFila);
          }
          
          return tablaVentas;
      }
     
    
    //Metodos get

    public Controlador getControlador() {
        return controlador;
    }

    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }

    
    //Metodos set

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setListaVentas(ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas; 
    }

    
    //Metodo para filtrar por MES
    public ArrayList<Venta> filtrarPorMes(){
        ArrayList<Venta> ventasDelMes = new ArrayList<Venta>();//El Array que guardará las ventas dentro del rango del mes
        Calendar calendar = Calendar.getInstance();//Guarda la fecha actual
        calendar.add(Calendar.MONTH, -1);//Le resta un mes a la fecha actual
        Date fechaLimite = calendar.getTime();//Guarda la fecha limite (un mes antes)
        
        for (int i=0;i<this.listaVentas.size();i++){//Recorro el array de ventas (TODAS)
            Date fechaAComparar = this.listaVentas.get(i).getFecha();//Guarda la fecha de la venta (Venta del array)
            if (fechaAComparar.equals(fechaLimite) || fechaAComparar.after(fechaLimite)){//Si la fecha es igual a un mes antes o más reciente
                ventasDelMes.add(this.listaVentas.get(i));//La añade al array que voy a devolver
            }
        }
        return ventasDelMes; //Devuelvo un array con las ventas del mes anterior al actual
    }
    
  //Metodo para filtrar por SEMANA
    public ArrayList<Venta> filtrarPorSemana(){
        ArrayList<Venta> ventasDeLaSemana = new ArrayList<Venta>();//El Array que guardará las ventas dentro del rango de la semana
        Calendar calendar = Calendar.getInstance();//Guarda la fecha actual
        calendar.add(Calendar.DATE, -7);//Le resta una semana a la fecha actual
        Date fechaLimite = calendar.getTime();//Guarda la fecha limite (una semana antes)
        
        for (int i=0;i<this.listaVentas.size();i++){//Recorro el array de ventas (TODAS)
            Date fechaAComparar = this.listaVentas.get(i).getFecha();//Guarda la fecha de la venta (Venta del array)
            if (fechaAComparar.equals(fechaLimite) || fechaAComparar.after(fechaLimite)){//Si la fecha es igual a una semana antes o más reciente
                ventasDeLaSemana.add(this.listaVentas.get(i));//La añade al array que voy a devolver
            }
        }
        return ventasDeLaSemana; //Devuelvo un array con las ventas de la semana anterior al actual
    }
    
    //Metodo para filtrar por DIA
    public ArrayList<Venta> filtrarPorDia(){
        ArrayList<Venta> ventasDelDia = new ArrayList<Venta>();//El Array que guardará las ventas dentro del rango del dia
        Calendar calendar = Calendar.getInstance();//Guarda la fecha actual
        calendar.add(Calendar.DATE, -1);//Le resta un dia a la fecha actual
        Date fechaLimite = calendar.getTime();//Guarda la fecha limite (un dia antes)
        
        for (int i=0;i<this.listaVentas.size();i++){//Recorro el array de ventas (TODAS)
            Date fechaAComparar = this.listaVentas.get(i).getFecha();//Guarda la fecha de la venta (Venta del array)
            if (fechaAComparar.equals(fechaLimite) || fechaAComparar.after(fechaLimite)){//Si la fecha es igual a un dia antes o más reciente
                ventasDelDia.add(this.listaVentas.get(i));//La añade al array que voy a devolver
            }
        }
        return ventasDelDia; //Devuelvo un array con las ventas del dia anterior al actual
    }
   
  
    
}
