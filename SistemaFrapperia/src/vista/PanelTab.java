package vista;

import javax.swing.JPanel;

import controlador.Controlador;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTab extends JPanel {

	/**
	 * un componente que permite cerrar las pestañas de ventas.
	 */
	private static final long serialVersionUID = 1L;
	
	// componentes ---------------------------
	private Controlador controlador;
	
	private JLabel lblNuevaVenta;
	private JButton btnX;
	private int index;
	

	/**
	 * Create the panel.
	 */
	public PanelTab() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 31, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNuevaVenta = new JLabel("nueva venta");
		GridBagConstraints gbc_lblNuevaVenta = new GridBagConstraints();
		gbc_lblNuevaVenta.insets = new Insets(0, 0, 0, 0);
		gbc_lblNuevaVenta.gridx = 0;
		gbc_lblNuevaVenta.gridy = 0;
		add(lblNuevaVenta, gbc_lblNuevaVenta);
		
		btnX = new JButton("x");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//int index = controlador.getIndexOfTab(this);
				actualizarIndex();
				controlador.eliminarTab(index);
			}
		});
		btnX.setFocusPainted(false);
		btnX.setBorderPainted(false);
		btnX.setBorder(null);
		btnX.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_btnX = new GridBagConstraints();
		gbc_btnX.fill = GridBagConstraints.BOTH;
		gbc_btnX.gridx = 1;
		gbc_btnX.gridy = 0;
		add(btnX, gbc_btnX);

	}


	public void actualizarIndex() {
		this.index = controlador.actualizarIndexDelTab(this);
	}
	// GETTERS -----------------------------------
	public Controlador getControlador() {
		return controlador;
	}
	public JLabel getLblNuevaVenta() {
		return lblNuevaVenta;
	}
	public JButton getBtnX() {
		return btnX;
	}


	// SETTERS --------------------------------------------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setLblNuevaVenta(JLabel lblNuevaVenta) {
		this.lblNuevaVenta = lblNuevaVenta;
	}
	public void setBtnX(JButton btnX) {
		this.btnX = btnX;
	}
	
	
	

}
