package unidades;

public class Caja extends UnidadMedida{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int piezas; // piezas por caja.
	
	public Caja() {
		super("Caja", "Caja(s)");
	}

	public int getPiezas() {
		return piezas;
	}

	public void setPiezas(int piezas) {
		this.piezas = piezas;
	}
	
	
}
