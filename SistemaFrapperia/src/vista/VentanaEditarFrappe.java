package vista;

/*
 * Extiende de VentanaAgregarFrappe.
 * 
 * Cambia el titulo de la ventana y se crean m�todo propios.
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
		super.getLblTitulo().setText("Editar Frapp�");
		
		cargarInformacion();
	}
	
	// M�todos --------------------------------
	
	// carga la informci�n existente del frapp�.
	public void cargarInformacion() {
		
	}

}
