package vista;

/*
 * Extiende de VentanaAgregarFrappe.
 * 
 * Cambia el titulo de la ventana y se crean método propios.
 */

public class VentanaEditarFrappe extends VentanaAgregarFrappe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public VentanaEditarFrappe() {
		super();
		super.getLblTitulo().setText("Editar Frappé");
		
		cargarInformacion();
	}
	
	// Métodos --------------------------------
	
	// carga la informción existente del frappé.
	public void cargarInformacion() {
		
	}

}
