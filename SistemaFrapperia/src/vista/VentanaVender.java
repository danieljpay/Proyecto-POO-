package vista;

//import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import controlador.Controlador;
import modelo.Venta;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaVender extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private Controlador controlador;
	
	private JPanel panelTabla;
	private JTable tablaCarrito;
	private JPanel panelTotal;
	private JLabel labelTotal;
	private JButton btnCancelar;
	private JButton btnTerminar;
	private JLabel lblMensajes;
	
	private Venta venta;
	private JLabel lblPagaCon;
	private JTextField tfPago;
	private JLabel lbl2;
	private JLabel lblCambio;
	
	/**
	 * Create the frame.
	 */
	public VentanaVender(Venta venta) {
		
		this.venta = venta;
		setLocationRelativeTo(null);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelTabla = new JPanel();
		panelTabla.setBounds(10, 11, 330, 394);
		contentPane.add(panelTabla);
		GridBagLayout gbl_panelTabla = new GridBagLayout();
		gbl_panelTabla.columnWidths = new int[]{0, 0};
		gbl_panelTabla.rowHeights = new int[]{0, 0};
		gbl_panelTabla.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTabla.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelTabla.setLayout(gbl_panelTabla);
		
		tablaCarrito = new JTable();
		tablaCarrito.setShowVerticalLines(false);
		tablaCarrito.setShowHorizontalLines(false);
		tablaCarrito.setShowGrid(false);
		tablaCarrito.setRowSelectionAllowed(false);
		tablaCarrito.setEnabled(false);
		tablaCarrito.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_tablaCarrito = new GridBagConstraints();
		gbc_tablaCarrito.fill = GridBagConstraints.BOTH;
		gbc_tablaCarrito.gridx = 0;
		gbc_tablaCarrito.gridy = 0;
		panelTabla.add(tablaCarrito, gbc_tablaCarrito);
		
		panelTotal = new JPanel();
		panelTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTotal.setBounds(350, 11, 228, 238);
		contentPane.add(panelTotal);
		GridBagLayout gbl_panelTotal = new GridBagLayout();
		gbl_panelTotal.columnWidths = new int[]{0, 0};
		gbl_panelTotal.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 35, 0};
		gbl_panelTotal.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTotal.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panelTotal.setLayout(gbl_panelTotal);
		
		JLabel lbl = new JLabel("  Total: ");
		lbl.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.WEST;
		gbc_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_lbl.gridx = 0;
		gbc_lbl.gridy = 0;
		panelTotal.add(lbl, gbc_lbl);
		
		labelTotal = new JLabel("  $ 0.00");
		labelTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GridBagConstraints gbc_labelTotal = new GridBagConstraints();
		gbc_labelTotal.insets = new Insets(0, 0, 5, 0);
		gbc_labelTotal.gridheight = 2;
		gbc_labelTotal.gridx = 0;
		gbc_labelTotal.gridy = 1;
		panelTotal.add(labelTotal, gbc_labelTotal);
		
		lblPagaCon = new JLabel("Paga con: ");
		lblPagaCon.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPagaCon = new GridBagConstraints();
		gbc_lblPagaCon.insets = new Insets(0, 0, 5, 0);
		gbc_lblPagaCon.anchor = GridBagConstraints.WEST;
		gbc_lblPagaCon.gridx = 0;
		gbc_lblPagaCon.gridy = 3;
		panelTotal.add(lblPagaCon, gbc_lblPagaCon);
		
		tfPago = new JTextField();
		tfPago.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				// bloquea entradas no deseadas
				if(Character.isDigit(letra) == false && letra != '.') {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				String pago = tfPago.getText();
				
				if(pago.isEmpty() == false) {
					
					if(pagoValido(pago)) {
						
						double cambio = venta.calcularCambio(Double.parseDouble(pago));
						
						if(cambio >= 0) {
							lblCambio.setFont(new Font("Segoe UI", Font.BOLD, 20));
							lblCambio.setText("$ " + String.valueOf(cambio));
						} else {
							lblCambio.setFont(new Font("Segoe UI", Font.BOLD, 12));
							lblCambio.setText("El monto no cubre la cantidad a pagar.");
						}
						
						
					} else {
						lblCambio.setFont(new Font("Segoe UI", Font.BOLD, 12));
						lblCambio.setText("El pago que ingresaste no es válido.");
					}
				} else {
					lblCambio.setText(null);
				}
			}
		});
		GridBagConstraints gbc_tfPago = new GridBagConstraints();
		gbc_tfPago.insets = new Insets(0, 0, 5, 0);
		gbc_tfPago.fill = GridBagConstraints.BOTH;
		gbc_tfPago.gridx = 0;
		gbc_tfPago.gridy = 4;
		panelTotal.add(tfPago, gbc_tfPago);
		tfPago.setColumns(10);
		
		lbl2 = new JLabel("Cambio:");
		lbl2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2.anchor = GridBagConstraints.WEST;
		gbc_lbl2.gridx = 0;
		gbc_lbl2.gridy = 5;
		panelTotal.add(lbl2, gbc_lbl2);
		
		lblCambio = new JLabel("$ 0.00");
		lblCambio.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GridBagConstraints gbc_lblCambio = new GridBagConstraints();
		gbc_lblCambio.gridheight = 2;
		gbc_lblCambio.insets = new Insets(0, 0, 5, 0);
		gbc_lblCambio.gridx = 0;
		gbc_lblCambio.gridy = 6;
		panelTotal.add(lblCambio, gbc_lblCambio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setToolTipText("cancelar venta");
		btnCancelar.setBounds(350, 371, 228, 34);
		contentPane.add(btnCancelar);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.terminarVenta(venta);
				dispose();
			}
		});
		btnTerminar.setToolTipText("terminar venta");
		btnTerminar.setBounds(350, 313, 228, 56);
		contentPane.add(btnTerminar);
		
		lblMensajes = new JLabel("\u00BFQuieres terminar la venta?");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setBounds(350, 256, 228, 56);
		contentPane.add(lblMensajes);
	}

	// identifica que no haya más de un punto decimal en el número.
		public boolean pagoValido(String pago) {
			char[] arregloPrecio = pago.toCharArray();
			
			int contador = 0; // cuenta los puntos decimales.
			for(int i=0; i<arregloPrecio.length; i++) {
				if(arregloPrecio[i] == '.') {
					contador++;
				}
			}
			
			if(contador > 1) {
				return false;
			}
			else {
				return true;
			}
			
		}
	
	// getters -----------
	public Controlador getControlador() {
		return controlador;
	}


	public JPanel getPanelTabla() {
		return panelTabla;
	}


	public JTable getTable() {
		return tablaCarrito;
	}


	public JPanel getPanelBotones() {
		return panelTotal;
	}


	public JLabel getLabelTotal() {
		return labelTotal;
	}


	public JButton getBtnCancelar() {
		return btnCancelar;
	}


	public JButton getBtnTerminar() {
		return btnTerminar;
	}
	
	public JTable getTablaCarrito() {
		return tablaCarrito;
	}

	public JPanel getPanelTotal() {
		return panelTotal;
	}

	public JLabel getLblMensajes() {
		return lblMensajes;
	}

	public Venta getVenta() {
		return venta;
	}



	// setters -----------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	public void setPanelTabla(JPanel panelTabla) {
		this.panelTabla = panelTabla;
	}


	public void setTable(JTable table) {
		this.tablaCarrito = table;
	}


	public void setPanelBotones(JPanel panelBotones) {
		this.panelTotal = panelBotones;
	}


	public void setLabelTotal(JLabel labelTotal) {
		this.labelTotal = labelTotal;
	}


	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}


	public void setBtnTerminar(JButton btnTerminar) {
		this.btnTerminar = btnTerminar;
	}

	public void setTablaCarrito(JTable tablaCarrito) {
		this.tablaCarrito = tablaCarrito;
	}

	public void setPanelTotal(JPanel panelTotal) {
		this.panelTotal = panelTotal;
	}

	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}
