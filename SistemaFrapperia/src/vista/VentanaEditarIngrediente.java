package vista;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaEditarIngrediente extends VentanaAgregarIngrediente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int index; // del ingrediente editandose.
	/**
	 * Create the frame.
	 */
	
	public VentanaEditarIngrediente() {
		
		
		super();
		getTfPiezas().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkBtnHecho();
			}
		});
		//getTfCantidad().removeActionListener(getTfCantidad().get);
		getTfCantidad().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//revisarBtnHecho();
				checkBtnHecho();
			}
		});
		getTfCosto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//revisarBtnHecho();
				checkBtnHecho();
			}
		});
		getTfNombre().setEnabled(false);
		getLblMensajes().setText("La unidad de medida y el nombre no se pueden modificar.");
		getCbUnidades().setEnabled(false);
		
		getBtnHecho().removeActionListener(getBtnHecho().getActionListeners()[0]);
		getBtnHecho().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getTfPiezas().isEnabled()) {
					getControlador().editarIngrediente(index, getTfCosto().getText(), getTfCantidad().getText(), getTfPiezas().getText());
				} else {
					getControlador().editarIngrediente(index, getTfCosto().getText(), getTfCantidad().getText());
				}
				
				dispose();
			}
		});
		super.getLblTitulo().setText("Editar Ingrediente");
		
	}
	
	public void checkBtnHecho() {
		
		if(getTfCosto().getText().isEmpty() == false && getTfCantidad().getText().isEmpty() == false) {
			if(costoValido(getTfCosto().getText())) {
				if(getTfPiezas().isEnabled()) {
					if(getTfPiezas().getText().isEmpty() == false) {
						getBtnHecho().setEnabled(true);
					} else {
						getBtnHecho().setEnabled(false);
					}
				}else {
					getBtnHecho().setEnabled(true);
				}
			} else {
				getBtnHecho().setEnabled(false);
			}
		}
		else {
			getBtnHecho().setEnabled(false);
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
