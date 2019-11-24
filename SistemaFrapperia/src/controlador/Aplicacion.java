package controlador;

import vista.*;

import modelo.*;
import unidades.Unidades;

public class Aplicacion {

	private static Controlador controlador;
	// declaramos jframes.
	private static VentanaPrincipal ventanaPrincipal;
	private static VentanaAgregarFrappe ventanaAgregarFrappe;
	private static VentanaAgregarIngrediente ventanaAgregarIngredientes;
	private static VentanaEditarFrappe ventanaEditarFrappe;
	private static VentanaEditarIngrediente ventanaEditarIngrediente;
	private static VentanaIngredientes ventanaIngredientes;
	private static VentanaFrappes ventanaFrappes;
	private static VentanaHistorialVentas ventanaHistorialVentas;
	
	private static Inventario inventario;
	private static Frappes frappes;
	private static Ventas ventas;
	private static Cuentas cuentas;
	
	public static Unidades unidades;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		iniciarObjetos();
		setRelaciones();
		
		Cargador.cargarDatos();
		
		// cargamos la lista de ingredientes en en panel de venta.
		controlador.getVentanaPrincipal().getPanelVenta().getListaFrappes().setModel(controlador.getModeloListaFrappes());
		// mostramos tabla de  carrito.
		Venta venta = controlador.getVentanaPrincipal().getPanelVenta().getVenta();
		controlador.getVentanaPrincipal().getPanelVenta().getTablaCarrito().setModel(venta.getTablaCarrito());
		// mostrar total inicial
		double total = controlador.getVentanaPrincipal().getPanelVenta().getVenta().calcularTotal();
		controlador.getVentanaPrincipal().getPanelVenta().getLblTotal().setText("Total: $" + total);
		// asignamos controlador 
		controlador.getVentanaPrincipal().getPanelVenta().setControlador(controlador);
		controlador.getVentanaPrincipal().setVisible(true);
	
		
	}

	public static void iniciarObjetos() {
	
		controlador = new Controlador();
		
		ventanaPrincipal = new VentanaPrincipal();
		ventanaAgregarFrappe = new VentanaAgregarFrappe();
		ventanaAgregarIngredientes = new VentanaAgregarIngrediente();
		ventanaEditarFrappe = new VentanaEditarFrappe();
		ventanaEditarIngrediente = new VentanaEditarIngrediente();
		ventanaIngredientes = new VentanaIngredientes();
		ventanaFrappes = new VentanaFrappes();
		ventanaHistorialVentas = new VentanaHistorialVentas();	
		
		inventario = new Inventario();
		frappes = new Frappes();
		ventas = new Ventas();
		cuentas = new Cuentas();

		unidades = new Unidades();
	}
	
	// establece las relaciones entre el controlador y las clases.
	public static void setRelaciones() {
		
		controlador.setVentanaPrincipal(ventanaPrincipal);
		controlador.setVentanaAgregarFrappe(ventanaAgregarFrappe);
		controlador.setVentanaAgregarIngredientes(ventanaAgregarIngredientes);
		controlador.setVentanaEditarFrappe(ventanaEditarFrappe);
		controlador.setVentanaEditarIngrediente(ventanaEditarIngrediente);
		controlador.setVentanaEmpleados(ventanaIngredientes);
		controlador.setVentanaFrappes(ventanaFrappes);
		controlador.setVentanaHistorialVentas(ventanaHistorialVentas);
		
		controlador.setInventario(inventario);
		controlador.setFrappes(frappes);
		controlador.setVentas(ventas);
		controlador.setCuentas(cuentas);
		
		ventanaPrincipal.setControlador(controlador);
		ventanaAgregarFrappe.setControlador(controlador);
		ventanaAgregarIngredientes.setControlador(controlador);
		ventanaEditarFrappe.setControlador(controlador);
		ventanaEditarIngrediente.setControlador(controlador);
		ventanaIngredientes.setControlador(controlador);
		ventanaFrappes.setControlador(controlador);
		ventanaHistorialVentas.setControlador(controlador);

		inventario.setControlador(controlador);
		frappes.setControlador(controlador);
		ventas.setControlador(controlador);
		cuentas.setControlador(controlador);
		
		Cargador.setControlador(controlador);
		
		//controlador.setVentanaPrincipal(ventanaPrincipal);
		//ventanaPrincipal.setControlador(controlador);
		
	}
	
	
}
