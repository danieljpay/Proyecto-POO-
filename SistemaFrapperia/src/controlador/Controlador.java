package controlador;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import vista.*;
import modelo.*;
import unidades.Cantidad;
import unidades.Unidades;
import unidades.Caja;


public class Controlador implements Serializable{

	/**
	 *  ventana vender y ventana venta a cuenta no son instancias únicas
	 *  se crean desde un panel de venta cuando el cliente quiere terminar su compra.
	 */
	private static final long serialVersionUID = 1L;
	
	
	private VentanaPrincipal ventanaPrincipal;
	private VentanaAgregarFrappe ventanaAgregarFrappe;
	private VentanaAgregarIngrediente ventanaAgregarIngredientes;
	private VentanaEditarFrappe ventanaEditarFrappe;
	private VentanaEditarIngrediente ventanaEditarIngrediente;
	private VentanaIngredientes ventanaIngredientes;
	private VentanaFrappes ventanaFrappes;
	private VentanaHistorialVentas ventanaHistorialVentas;

	private Inventario inventario;
	private Frappes frappes;
	private Ventas ventas;
	private Cuentas cuentas;
	
	// métodos de relación
	// revisa si un ingredientes está asignado a algún frappé.
	public boolean ingredientePuedeBorrarse(Ingrediente ingrediente) {
		
		for(int i=0; i<frappes.getListaFrappes().size(); i++) {
			ArrayList<Ingrediente> ingredientesFrappe = frappes.getListaFrappes().get(i).getIngredientes();
			for(int k=0; k<ingredientesFrappe.size(); k++) {
				if(ingrediente.equals(ingredientesFrappe.get(k))) {
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	// Frappes ----------------
	// devuelve el frappe correspondiente, dado su nombre.
	public Frappe getFrappePorNombre(String nombre) {
		return frappes.getFrappePorNombre(nombre);
	}	
	// devuelve la lista de frappes disponibles.
	public DefaultListModel<String> getModeloListaFrappes(){
		return frappes.getModeloListaFrappes();
	}
	
	// agrega un frappe
	public void agregarFrappe(String nombre, String precio, JList<String> listaIngredientes) {
		
		// generamos el ArrayList de ingredientes
		ArrayList<Ingrediente> ingredientesFrappe = new ArrayList<Ingrediente>();
		
		// buscamos los ingredientes usando sus nombres y los agregamos al array.
		for(int i=0; i<listaIngredientes.getModel().getSize(); i++) {
			String nombreIngrediente = listaIngredientes.getModel().getElementAt(i);
			for(int k=0; k<inventario.getIngredientes().size(); k++) {
				String nombreIngredienteAux = inventario.getIngrediente(k).getNombre();
				if(nombreIngrediente.equals(nombreIngredienteAux)) {
					ingredientesFrappe.add(inventario.getIngrediente(k));
				}
			}
		}
		
		//Recorre el Array y verifica si el frappe ya existe
		boolean frappeExistente=false;
		int indice;
		for (indice=0;indice<frappes.getListaFrappes().size();indice++) {
			if (frappes.getListaFrappes().get(indice).getNombre().equals(nombre)) {
				frappeExistente=true;
				break;
			}
		}
		
		//Si el frappe ya existe, lo edita 
		//Sino, lo crea
		if (frappeExistente==false) {
			frappes.agregarFrappe(nombre, precio, ingredientesFrappe);
			System.out.println("Se agregó el frappé.");
		}else if (frappeExistente==true) {
			frappes.editarFrappe(indice, nombre, precio, ingredientesFrappe);
			for (int i=0;i<ventanaPrincipal.getPanelVenta().getVenta().getCarrito().getListaFrappes().size();i++) { //Todo este for actualiza a los frappes del carrito
				for (int j=0;j<ventanaPrincipal.getPanelVenta().getVenta().getCarrito().getListaFrappes().get(i).size();j++) {
					if (ventanaPrincipal.getPanelVenta().getVenta().getCarrito().getListaFrappes().get(i).get(j).getNombre().equals(nombre)) {
						ventanaPrincipal.getPanelVenta().getVenta().getCarrito().getListaFrappes().get(i).get(j).igualarFrappe(frappes.getFrappePorNombre(nombre));
					}
				}
			}
			ventanaPrincipal.getPanelVenta().actualizarCarrito();
			System.out.println("Se editó el frappé.");
		}
		Cargador.guardarDatos();
		actualizarVentanasFrappes();
	}
	
	//Actualiza los frappes de la tabla principal y de la ventana de agregar frappe
	public void actualizarVentanasFrappes() {
		ventanaPrincipal.actualizarTablaFrappesDisponibles();
		ventanaFrappes.getTablaIngredientes().setModel(frappes.getTablaFrappes());
		Cargador.guardarDatos();
	}
	
	//Eliminar un frappe
	public void eliminarFrappe(int indice) {
		frappes.eliminarFrappe(indice);
		actualizarVentanasFrappes();
	}
	
	//---- METODOS PARA EL CARRITO ----
	public void limpiarCarrito() {
		ventanaPrincipal.getPanelVenta().getVenta().getCarrito().getListaFrappes().clear();
		ventanaPrincipal.getPanelVenta().actualizarCarrito();
	}

	
	// Ingredientes ----------- 
	// agrega un ingrediente
	public boolean ingredienteExistente(String nombre) {
		return inventario.nombreRepetido(nombre);
	}
	
	// nuevo método para agregar ingrediente -----------
	public void agregarIngrediente(String nombre, String costo, String cantidad, int indexUnidad) {
		// creamos la Cantidad
		Cantidad cantidadIngrediente = new Cantidad(Unidades.getUnidadMedida(indexUnidad), Integer.parseInt(cantidad)); 
		
		inventario.agregarIngrediente(nombre, costo, cantidadIngrediente);
		
		Cargador.guardarDatos();
	}
	// método para ingredientes que se midan por cajas.
	public void agregarIngrediente(String nombre, String costo, String cantidad, int indexUnidad, String piezas) {
		int piezasCaja = Integer.parseInt(piezas);
		// creamos la Cantidad
		if(Unidades.getUnidadMedida(indexUnidad) instanceof Caja) {
			Caja caja = (Caja)Unidades.getUnidadMedida(indexUnidad);
			caja.setPiezas(piezasCaja);
			//--------- revisar
			Cantidad cantidadIngrediente = new Cantidad(caja, Integer.parseInt(cantidad)); 
			
			inventario.agregarIngrediente(nombre, costo, cantidadIngrediente);
			Cargador.guardarDatos();
		}
		
	}
	// --------------------------------------------------
	
	// elimina un ingredientes
	public void eliminarIngrediente(int index) {
		
		try {
			if(ingredientePuedeBorrarse(inventario.getIngrediente(index-1))) {
			
					inventario.eliminarIngrediente(index-1);
					//ventanaIngredientes.getTablaIngredientes().setModel(inventario.getTablaIngredientes());
					actualizarTablaIngredientes();
					
					Cargador.guardarDatos();
				
			}
			else {
				JOptionPane.showMessageDialog(null, "No puede eliminar este ingrediente porque está asignado a un frappé.");
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			// no es necesario tratarlo.
		}
	
	}
	
	public void editarIngrediente(int index, String costo, String cantidad) {
		
		inventario.editarIngrediente(index, costo, cantidad);
		
		Cargador.guardarDatos();
		
		actualizarTablaIngredientes();
		
		JOptionPane.showMessageDialog(null, "Ingrediente editado correctamente.");
		
	}
	public void editarIngrediente(int index, String costo, String cantidad, String piezas) {
		inventario.editarIngrediente(index, costo,  cantidad, piezas);
		Cargador.guardarDatos();
		
		actualizarTablaIngredientes();
		
		JOptionPane.showMessageDialog(null, "Ingrediente editado correctamente.");

	}
	
	public DefaultTableModel getTablaIngredientes() {
		return inventario.getTablaIngredientes();
	}
	
	
	// regresa la lisa de ingredientes disponibles.
	public DefaultListModel<String> getListaIngredientes() {
		return inventario.getListaIngredientes();
	}
	// actualizar la lista de ingredientes en la ventana agregar frappe. despues de agregar un ingrediente.
	public void actualizarListaIngredientes() {
		
		try {
			ventanaAgregarFrappe.getModeloListaIngredientes().addElement(inventario.getIngrediente(inventario.getIngredientes().size()-1).getNombre());
			ventanaAgregarFrappe.setListaIngredientes(ventanaAgregarFrappe.getModeloListaIngredientes());	
		} catch(NullPointerException e) {
			// no es necesario tratarlo
			// ocurre cuando se intenta actualizar la ventanaAgregarFrappe 
			// pero la ventana agregar ingrediente se llamó desde la ventana de ingredientes.
		}
		
	}
	// actualiza la tabla de ingredientes en la ventana de 
	public void actualizarTablaIngredientes() {
		ventanaIngredientes.getTablaIngredientes().setModel(inventario.getTablaIngredientes());
		
		// ajustamos tamaños de columnas.
		ventanaIngredientes.getTablaIngredientes().getColumnModel().getColumn(0).setPreferredWidth(5);
		ventanaIngredientes.getTablaIngredientes().getColumnModel().getColumn(1).setPreferredWidth(200);
		ventanaIngredientes.getTablaIngredientes().getColumnModel().getColumn(2).setPreferredWidth(5);
		ventanaIngredientes.getTablaIngredientes().getColumnModel().getColumn(3).setPreferredWidth(100);
		//
	}

	
	// Ventas -----------------
	// termina una venta
	public void terminarVenta(Venta venta) {
		System.out.println(venta.getPrecioTotal());;
		Date fecha = Calendar.getInstance().getTime();
		venta.setFecha(fecha);
		ventas.agregarVenta(venta);
		
		System.out.println("venta exitosa.");
		Cargador.guardarDatos();
	}
	
	
	// Cuentas ----------------
	// elimina una cuenta
	public void eliminarCuenta(Cuenta cuenta) {
		cuentas.eliminarCuenta(cuenta);
		Cargador.guardarDatos();
	}
	// agrega una cuenta
	public void agregarCuenta(String nombre) {
		Cuenta cuenta = new Cuenta(nombre);
		
		cuentas.agregarCuenta(cuenta);
		System.out.println("se ha creado la cuenta.");
		JOptionPane.showMessageDialog(null, "Cuenta agregada correctamente. Actualiza la lista.");
		
		Cargador.guardarDatos();
		System.out.println("Información guardada.");
	}
	public void abonarACuenta(int index, String abono) {
		
		double doubleAbono = Double.parseDouble(abono);
		cuentas.abonarACuenta(index, doubleAbono);
		
		Cargador.guardarDatos();
		JOptionPane.showMessageDialog(null, "Abono realizado correctamente.");
	}
	public void aumentarSaldoDeCuenta(int index, double monto) {
		cuentas.aumentarSaldo(index, monto);
		
		Cargador.guardarDatos();
		JOptionPane.showMessageDialog(null, "Monto agregado correctamente.");
	}
	public Cuenta getUnaCuenta(int index) {
		return cuentas.getUnaCuenta(index);
	}
	// recibe un nombre y determina si ya existe una cuenta o no con el mismo nombre.
	public boolean nombreRepetido(String nombre) {
		return cuentas.nombreRepetido(nombre);
	}
	// devuelve la lista de cuentas existentes
	public DefaultListModel<String> getModeloListaCuentas(){
		return cuentas.getModeloListaCuentas();
	}
	
	
	// abre una nueva pestaña de venta
	public void nuevaVenta() {
		JTabbedPane tabbedPane = ventanaPrincipal.getTabbedPane();
		if(tabbedPane.getTabCount() < 5) {
			PanelVenta nuevaVenta = new PanelVenta();
			nuevaVenta.getListaFrappes().setModel(getModeloListaFrappes());
			nuevaVenta.setControlador(this);
			
			PanelTab panelTab = new PanelTab();
			panelTab.setControlador(this);
			
			tabbedPane.addTab(null, nuevaVenta);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() -1 , panelTab);
			ventanaPrincipal.setTabbedPane(tabbedPane);
		}
	}
	public int  actualizarIndexDelTab(PanelTab tab) {
		return ventanaPrincipal.getTabbedPane().indexOfTabComponent(tab);
	}
	public void eliminarTab(int index) {
		ventanaPrincipal.getTabbedPane().remove(index);
	}
	
	
	
	// métodos para abrir ventanas. ----------------------------------
	public void abrirVentanaCuentas() {
		VentanaCuentas ventanaCuentas = new VentanaCuentas();
		ventanaCuentas.setControlador(this);
		
		ventanaCuentas.getListaCuentas().setModel(getModeloListaCuentas());
		
		ventanaCuentas.setVisible(true);
	}
	public void abrirVentanaVender(Venta venta) {
		
		VentanaVender ventanaVender = new VentanaVender(venta);
		ventanaVender.setControlador(this);
		
		// cargar la información.
		ventanaVender.getTablaCarrito().setModel(venta.getTablaCarrito());
		ventanaVender.getLabelTotal().setText( "$ " + venta.calcularTotal());
		
		ventanaVender.setVisible(true);
		
	}
	public void abrirVentanaVenderACuenta(Venta venta) {
		VentanaVentaACuenta ventanaVentaACuenta = new VentanaVentaACuenta(venta);
		ventanaVentaACuenta.setControlador(this);
		
		// cargar la información.
		ventanaVentaACuenta.getListaCuentas().setModel(cuentas.getModeloListaCuentas());
		ventanaVentaACuenta.getLblMonto().setText("$ " + venta.calcularTotal());
		
		ventanaVentaACuenta.setVisible(true);
	}
	public void abrirVentanaNuevaCuenta() {
		VentanaNuevaCuenta ventanaNuevaCuenta = new VentanaNuevaCuenta();
		ventanaNuevaCuenta.setControlador(this);
		
		ventanaNuevaCuenta.setVisible(true);
		
	}
	public void abrirVentanaAgregarFrappe() {
		
		ventanaAgregarFrappe.getTfNombre().setText(null);
		ventanaAgregarFrappe.getTfPrecio().setText(null);
		ventanaAgregarFrappe.setModeloListaIngredientes(inventario.getListaIngredientes());
		ventanaAgregarFrappe.setListaIngredientes(inventario.getListaIngredientes());
		
		DefaultTableModel modeloTablaAgregados = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			// desactivamos edición de celdas.
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
			
			
		};
		
		modeloTablaAgregados.addColumn("Nombre");
		modeloTablaAgregados.addColumn("Cantidad");
		
		ventanaAgregarFrappe.setModeloTablaAgregados(modeloTablaAgregados);
		ventanaAgregarFrappe.getTablaAgregados().setModel(ventanaAgregarFrappe.getModeloTablaAgregados());
		ventanaAgregarFrappe.getTablaAgregados().getColumnModel().getColumn(1).setPreferredWidth(30);
		ventanaAgregarFrappe.getTablaAgregados().getTableHeader().setReorderingAllowed(false);
		ventanaAgregarFrappe.getTablaAgregados().getTableHeader().setResizingAllowed(false);
		
		ventanaAgregarFrappe.setVisible(true);
	}
	public void abrirVentanaAgregarIngredientes(boolean actualizarInfo) {
		
		ventanaAgregarIngredientes.getTfNombre().setText(null);
		ventanaAgregarIngredientes.getTfCosto().setText(null);
		ventanaAgregarIngredientes.getLblMensajes().setText("Ingrese los datos.");
		ventanaAgregarIngredientes.getTfCantidad().setText(null);
		ventanaAgregarIngredientes.getTfPiezas().setText(null);
		ventanaAgregarIngredientes.getTfPiezas().setEnabled(false);
		ventanaAgregarIngredientes.getBtnHecho().setEnabled(false);
		ventanaAgregarIngredientes.getLblPiezas().setForeground(Color.LIGHT_GRAY);
		
		ventanaAgregarIngredientes.getCbUnidades().setModel(Unidades.getBoxModelUnidades());
		
		
		ventanaAgregarIngredientes.setActualizarInfo(actualizarInfo);
		ventanaAgregarIngredientes.setVisible(true);
	}
	public void abrirVentanaEditarFrappe(int index) {
		// cargamos la información del frappé.
		Frappe frappe = frappes.getFrappe(index);
		
		ventanaEditarFrappe.getTfNombre().setText(frappe.getNombre());
		ventanaEditarFrappe.getTfPrecio().setText("" + frappe.getPrecio());
		ventanaEditarFrappe.getTablaAgregados().setModel(frappe.getTablaIngredientes());
		
		
		
		ventanaEditarFrappe.setVisible(true);
	}
	public void abrirVentanaEditarIngrediente() {
		// cargamos la información del ingrediente.
		
		try {
			Ingrediente ingrediente = inventario.getIngrediente(ventanaIngredientes.getTablaIngredientes().getSelectedRow()-1);
			
			ventanaEditarIngrediente.getTfNombre().setText(ingrediente.getNombre());
			ventanaEditarIngrediente.getTfCosto().setText("" + ingrediente.getCostoCompra());
			ventanaEditarIngrediente.getTfCantidad().setText("" + ingrediente.getCantidad().getCantidad());
			
			DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
			modelo.addElement(ingrediente.getCantidad().getSimboloUnidad());
			ventanaEditarIngrediente.getCbUnidades().setModel(modelo);
			
			
			if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
				Caja caja  = (Caja) ingrediente.getCantidad().getUnidad();
				ventanaEditarIngrediente.getTfPiezas().setEnabled(true);
				ventanaEditarIngrediente.getTfPiezas().setText("" + caja.getPiezas());
			}else {
				ventanaEditarIngrediente.getTfPiezas().setEnabled(false);
				ventanaEditarIngrediente.getTfPiezas().setText(null);
			}
			
			ventanaEditarIngrediente.getBtnHecho().setEnabled(true);
			ventanaEditarIngrediente.setIndex(ventanaIngredientes.getTablaIngredientes().getSelectedRow()-1);
			//ventanaEditarIngrediente.revisarBtnHecho();
			
			System.out.println("abriendo ventana editar ingredientes.");
			ventanaEditarIngrediente.setVisible(true);
			
		}catch(ArrayIndexOutOfBoundsException e) {
			// no es necesario tratarlo
		}
		
	}
	public void abrirVentanaIngredientes() {
		actualizarTablaIngredientes();
		ventanaIngredientes.setVisible(true);
	}
	
	public void abrirVentanaFrappes() {
		ventanaFrappes.getTablaIngredientes().setModel(frappes.getTablaFrappes());
		ventanaFrappes.setVisible(true);
	}
	
	public void abrirVentanaHistorialVentas() {
		actualizarHistorialVentas();
		ventanaHistorialVentas.setVisible(true);
	}
	
	public void actualizarHistorialVentas() {
		ventanaHistorialVentas.getTablaVentas().setModel(ventas.getTablaVentas(ventas.getListaVentas()));
	}
	
	public void abrirDialogoPedirCantidad(String nombreIngrediente) {
		
		int index = inventario.getIngrediente(nombreIngrediente);
		
		if(index != -1) {
			Ingrediente ingrediente = inventario.getIngrediente(index);
					
					JDPedirCantidad jdPedirCantidad = new JDPedirCantidad();
					jdPedirCantidad.setControlador(this);
					
					// llenamos la información del combo box
					DefaultComboBoxModel<String> modeloCb = new DefaultComboBoxModel<String>();
					modeloCb.addElement(ingrediente.getCantidad().getSimboloUnidad());
					jdPedirCantidad.getCbUnidad().setModel(modeloCb);
					
					if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
						
						jdPedirCantidad.getTfCantidad().setEnabled(false);
						jdPedirCantidad.getLblCantidad().setForeground(Color.LIGHT_GRAY);
						
						jdPedirCantidad.getTfPiezas().setEnabled(true);
						jdPedirCantidad.getLblPiezas().setForeground(Color.BLACK);
					}
					
					jdPedirCantidad.setIngrediente(ingrediente);
					jdPedirCantidad.setVisible(true);
		}
	}
	// pasa la información de la ventana pedir cantidad a la ventana agregar ingrediente.
	public void devolverCantidad(Ingrediente ingrediente) {
		/*
		 * 
		 * agregar método.
		 * 
		 * devolver ingrediente
		 * asignar la cantidad al ingrediente.
		 * 
		 * actualizarTabla();
		 * 
		 */
		// quitamos el ingrediente de la lista de ingredientes disponibles. 
		ventanaAgregarFrappe.getModeloListaIngredientes().remove(ventanaAgregarFrappe.getListaIngredientes().getSelectedIndex());
		//ventanaAgregarFrappe.setModeloListaIngredientes(ventanaAgregarFrappe.getModeloListaIngredientes());
		ventanaAgregarFrappe.getListaIngredientes().setModel(ventanaAgregarFrappe.getModeloListaIngredientes());
		
		// creamos una linea para la tabla de ingredientes agregados en la ventana agregar ingrediente.
		Vector<String> fila = new Vector<String>();
		
		fila.add(ingrediente.getNombre());
		if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
			Caja caja = (Caja) ingrediente.getCantidad().getUnidad();
			fila.add("" + caja.getPiezas() + " pzs");
		} else {
			fila.add("" + ingrediente.getCantidad().getCantidad() + " " + ingrediente.getCantidad().getSimboloUnidad());
		}
		
		DefaultTableModel modeloAgregados = ventanaAgregarFrappe.getModeloTablaAgregados();
		modeloAgregados.addRow(fila);
		
		// actualizamos el modelo y la tabla con el nuevo modelo.
		ventanaAgregarFrappe.setModeloTablaAgregados(modeloAgregados);
		ventanaAgregarFrappe.getTablaAgregados().setModel(modeloAgregados);
		
		// eliminados el ingrediente de la lista.
		//ventanaAgregarFrappe.getListaIngredientes().setModel(ventanaAgregarFrappe.getModeloListaIngredientes());
	}
	
	// getters--------
	public VentanaPrincipal getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	public VentanaAgregarFrappe getVentanaAgregarFrappe() {
		return ventanaAgregarFrappe;
	}
	public VentanaAgregarIngrediente getVentanaAgregarIngredientes() {
		return ventanaAgregarIngredientes;
	}
	public VentanaEditarFrappe getVentanaEditarFrappe() {
		return ventanaEditarFrappe;
	}
	public VentanaEditarIngrediente getVentanaEditarIngrediente() {
		return ventanaEditarIngrediente;
	}
	public VentanaIngredientes getVentanaEmpleados() {
		return ventanaIngredientes;
	}
	public VentanaFrappes getVentanaFrappes() {
		return ventanaFrappes;
	}
	public VentanaHistorialVentas getVentanaHistorialVentas() {
		return ventanaHistorialVentas;
	}
	public VentanaIngredientes getVentanaIngredientes() {
		return ventanaIngredientes;
	}
	public Inventario getInventario() {
		return inventario;
	}
	public Frappes getFrappes() {
		return frappes;
	}
	public Ventas getVentas() {
		return ventas;
	}
	public Cuentas getCuentas() {
		return cuentas;
	}
	// setters ----------------
	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	public void setVentanaAgregarFrappe(VentanaAgregarFrappe ventanaAgregarFrappe) {
		this.ventanaAgregarFrappe = ventanaAgregarFrappe;
	}
	public void setVentanaAgregarIngredientes(VentanaAgregarIngrediente ventanaAgregarIngredientes) {
		this.ventanaAgregarIngredientes = ventanaAgregarIngredientes;
	}
	public void setVentanaEditarFrappe(VentanaEditarFrappe ventanaEditarFrappe) {
		this.ventanaEditarFrappe = ventanaEditarFrappe;
	}
	public void setVentanaEditarIngrediente(VentanaEditarIngrediente ventanaEditarIngrediente) {
		this.ventanaEditarIngrediente = ventanaEditarIngrediente;
	}
	public void setVentanaEmpleados(VentanaIngredientes ventanaIngredientes) {
		this.ventanaIngredientes = ventanaIngredientes;
	}
	public void setVentanaFrappes(VentanaFrappes ventanaFrappes) {
		this.ventanaFrappes = ventanaFrappes;
	}
	public void setVentanaHistorialVentas(VentanaHistorialVentas ventanaHistorialVentas) {
		this.ventanaHistorialVentas = ventanaHistorialVentas;
	}
	public void setVentanaIngredientes(VentanaIngredientes ventanaIngredientes) {
		this.ventanaIngredientes = ventanaIngredientes;
	}
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	public void setFrappes(Frappes frappes) {
		this.frappes = frappes;
	}
	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}
	public void setCuentas(Cuentas cuentas) {
		this.cuentas = cuentas;
	}
	
}
