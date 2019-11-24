package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Frappe;
import modelo.Venta;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelVenta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// componentes.
	private JLabel lblMensaje1;
	private JLabel lblMensaje2;
	private JList<String> listaFrappes;
	private JButton btnAgregar;
	private JTable tablaCarrito;
	private JButton btnVender;
	private JButton btnEliminar;
	private JLabel lblTotal;
	
	private Venta venta; // venta asociada al panel.  
	private Controlador controlador;
	private JButton btnVentaACuenta;
	private JLabel lblMensajes;
	
	/**
	 * Create the panel.
	 */
	public PanelVenta() {
		this.venta = new Venta();
		

		
		setBorder(new TitledBorder(null, "   vender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{151, 88, 18, 195, 112, 0};
		gridBagLayout.rowHeights = new int[]{0, 168, 0, 57, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblMensaje1 = new JLabel("Frapp\u00E9s disponibles.");
		GridBagConstraints gbc_lblMensaje1 = new GridBagConstraints();
		gbc_lblMensaje1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMensaje1.gridx = 0;
		gbc_lblMensaje1.gridy = 0;
		add(lblMensaje1, gbc_lblMensaje1);
		
		lblMensaje2 = new JLabel("Carrito");
		GridBagConstraints gbc_lblMensaje2 = new GridBagConstraints();
		gbc_lblMensaje2.insets = new Insets(0, 0, 5, 5);
		gbc_lblMensaje2.gridx = 3;
		gbc_lblMensaje2.gridy = 0;
		add(lblMensaje2, gbc_lblMensaje2);
		
		listaFrappes = new JList<String>();
		GridBagConstraints gbc_listaFrappes = new GridBagConstraints();
		gbc_listaFrappes.gridheight = 2;
		gbc_listaFrappes.insets = new Insets(0, 0, 5, 5);
		gbc_listaFrappes.fill = GridBagConstraints.BOTH;
		gbc_listaFrappes.gridx = 0;
		gbc_listaFrappes.gridy = 1;
		add(listaFrappes, gbc_listaFrappes);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(listaFrappes.getSelectedIndex() != -1) {
					// agregamos el frappe seleccionado al carrito de la venta.
					String nombreFrappe = listaFrappes.getSelectedValue();
					Frappe frappe = controlador.getFrappePorNombre(nombreFrappe); 
					
					venta.agregarFrappe(frappe);
					
					// actualizamos la tabla del carrito para mostrar al cliente.
					tablaCarrito.setModel(venta.getTablaCarrito());
					// actualizamos el total
					lblTotal.setText("Total: $" + venta.calcularTotal());
					
					lblMensajes.setText(null);
					
					btnVender.setEnabled(true);
					btnVentaACuenta.setEnabled(true);
					
				} else {
					lblMensajes.setText("Debe seleccionar un frappé.");
				}
			}
		});

		btnAgregar.setToolTipText("Agregar al carrito");
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.fill = GridBagConstraints.BOTH;
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregar.gridx = 1;
		gbc_btnAgregar.gridy = 1;
		add(btnAgregar, gbc_btnAgregar);
		
		tablaCarrito = new JTable();
		tablaCarrito.setFillsViewportHeight(true);
		tablaCarrito.setShowVerticalLines(false);
		tablaCarrito.setShowHorizontalLines(false);
		tablaCarrito.setShowGrid(false);
		tablaCarrito.setRowSelectionAllowed(false);
		tablaCarrito.setEnabled(false);
		GridBagConstraints gbc_tablaCarrito = new GridBagConstraints();
		gbc_tablaCarrito.gridheight = 2;
		gbc_tablaCarrito.gridwidth = 2;
		gbc_tablaCarrito.insets = new Insets(0, 0, 5, 5);
		gbc_tablaCarrito.fill = GridBagConstraints.BOTH;
		gbc_tablaCarrito.gridx = 2;
		gbc_tablaCarrito.gridy = 1;
		add(tablaCarrito, gbc_tablaCarrito);
		
		btnVender = new JButton("Vender");
		btnVender.setEnabled(false);
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaVender(venta);
			}
		});
		btnVender.setToolTipText("Terminar venta");
		GridBagConstraints gbc_btnVender = new GridBagConstraints();
		gbc_btnVender.fill = GridBagConstraints.BOTH;
		gbc_btnVender.insets = new Insets(0, 0, 5, 0);
		gbc_btnVender.gridx = 4;
		gbc_btnVender.gridy = 1;
		add(btnVender, gbc_btnVender);
		
		btnVentaACuenta = new JButton("Venta a Cuenta");
		btnVentaACuenta.setEnabled(false);
		btnVentaACuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaVenderACuenta(venta);
			}
		});
		GridBagConstraints gbc_btnVentaACuenta = new GridBagConstraints();
		gbc_btnVentaACuenta.fill = GridBagConstraints.BOTH;
		gbc_btnVentaACuenta.insets = new Insets(0, 0, 5, 0);
		gbc_btnVentaACuenta.gridx = 4;
		gbc_btnVentaACuenta.gridy = 2;
		add(btnVentaACuenta, gbc_btnVentaACuenta);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// si hay frappé seleccionado.
				if(listaFrappes.getSelectedIndex() != -1) {
					String nombreFrappe = listaFrappes.getSelectedValue();
					Frappe frappe = controlador.getFrappePorNombre(nombreFrappe);
					
					venta.eliminarFrappe(frappe);
					
					// actualizamos la info.
					tablaCarrito.setModel(venta.getTablaCarrito());
					lblTotal.setText("Total: $" + venta.calcularTotal());
					
					lblMensajes.setText(null);
					
					// revisamos si el carrito está vacío
					if(venta.getCarrito().getListaFrappes().size() == 0) {
						btnVender.setEnabled(false);
						btnVentaACuenta.setEnabled(false);
					}
					
				} else {
					lblMensajes.setText("Debe seleccionar un frappé.");
				}
				
			}
		});
		btnEliminar.setToolTipText("Eliminar del carrito");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.BOTH;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 2;
		add(btnEliminar, gbc_btnEliminar);
		
		lblMensajes = new JLabel("");
		GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
		gbc_lblMensajes.fill = GridBagConstraints.BOTH;
		gbc_lblMensajes.gridwidth = 2;
		gbc_lblMensajes.insets = new Insets(0, 0, 0, 5);
		gbc_lblMensajes.gridx = 0;
		gbc_lblMensajes.gridy = 3;
		add(lblMensajes, gbc_lblMensajes);
		
		lblTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 3;
		gbc_lblTotal.gridy = 3;
		add(lblTotal, gbc_lblTotal);

	}

	
	// GETTERS
	public JLabel getLblMensaje1() {
		return lblMensaje1;
	}

	public JLabel getLblMensaje2() {
		return lblMensaje2;
	}

	public JList<String> getListaFrappes() {
		return listaFrappes;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JTable getTablaCarrito() {
		return tablaCarrito;
	}

	public JButton getBtnVender() {
		return btnVender;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public Venta getVenta() {
		return venta;
	}

	public Controlador getControlador() {
		return controlador;
	}
	
	public JLabel getLblMensajes() {
		return lblMensajes;
	}
	
	// SETTERS
	public void setLblMensaje1(JLabel lblMensaje1) {
		this.lblMensaje1 = lblMensaje1;
	}

	public void setLblMensaje2(JLabel lblMensaje2) {
		this.lblMensaje2 = lblMensaje2;
	}

	public void setListaFrappes(JList<String> listaFrappes) {
		this.listaFrappes = listaFrappes;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public void setTablaCarrito(JTable tablaCarrito) {
		this.tablaCarrito = tablaCarrito;
	}

	public void setBtnVender(JButton btnVender) {
		this.btnVender = btnVender;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}
	
	
	

}
