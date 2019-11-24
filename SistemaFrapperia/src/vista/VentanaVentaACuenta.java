package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;

import controlador.Controlador;
import modelo.Cuenta;
import modelo.Venta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaVentaACuenta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private Controlador controlador;
	
	private JPanel panelLista;
	private JList<String> listaCuentas;
	private JLabel lblCuentasExistentes;
	
	private JPanel panelDatos;
	private JLabel lblTituloDatos;
	private JLabel lblNOMBREE;
	private JLabel lblNombre;
	private JLabel lblDEUDAE;
	private JLabel lblDeuda;
	private JLabel lblMONTO;
	private JLabel lblMonto;
	private JLabel lblNDEUDA;
	private JLabel lblNuevaDeuda;
	
	private JButton btnCancelar;
	private JButton btnTerminar;
	private JButton btnAgregarCuenta;
	
	private Venta venta;
	
	/**
	 * Create the frame.
	 */
	public VentanaVentaACuenta(Venta venta) {
		
		this.venta = venta;
		iniciarComponentes();
	}
	
	// constructor usado para extender de VentanaVentaACuenta
	public VentanaVentaACuenta() {
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		setResizable(false);
		setTitle("agregar a cuenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 374);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLista = new JPanel();
		panelLista.setBounds(10, 44, 167, 290);
		contentPane.add(panelLista);
		GridBagLayout gbl_panelLista = new GridBagLayout();
		gbl_panelLista.columnWidths = new int[]{0, 0};
		gbl_panelLista.rowHeights = new int[]{0, 0, 0};
		gbl_panelLista.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelLista.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelLista.setLayout(gbl_panelLista);
		
		listaCuentas = new JList<String>();
		listaCuentas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// obtenemos la cuenta.
				Cuenta cuenta = controlador.getUnaCuenta(listaCuentas.getSelectedIndex());
				
				// mostramos la información.
				lblNombre.setText(cuenta.getNombre());
				lblDeuda.setText("$ " + cuenta.getSaldoPendiente());
				lblNuevaDeuda.setText("$ " + (cuenta.getSaldoPendiente() + venta.calcularTotal()));
				
			}
		});
		listaCuentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_listaCuentas = new GridBagConstraints();
		gbc_listaCuentas.insets = new Insets(0, 0, 5, 0);
		gbc_listaCuentas.fill = GridBagConstraints.BOTH;
		gbc_listaCuentas.gridx = 0;
		gbc_listaCuentas.gridy = 0;
		panelLista.add(listaCuentas, gbc_listaCuentas);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaCuentas.setModel(controlador.getModeloListaCuentas());
			}
		});
		btnActualizar.setToolTipText("actualizar lista");
		GridBagConstraints gbc_btnActualizar = new GridBagConstraints();
		gbc_btnActualizar.fill = GridBagConstraints.BOTH;
		gbc_btnActualizar.gridx = 0;
		gbc_btnActualizar.gridy = 1;
		panelLista.add(btnActualizar, gbc_btnActualizar);
		
		lblCuentasExistentes = new JLabel("Cuentas existentes");
		lblCuentasExistentes.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblCuentasExistentes.setBounds(10, 11, 186, 22);
		contentPane.add(lblCuentasExistentes);
		
		panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDatos.setBounds(187, 11, 229, 323);
		contentPane.add(panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 47, 0, 55, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		lblTituloDatos = new JLabel("  Informaci\u00F3n");
		lblTituloDatos.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTituloDatos = new GridBagConstraints();
		gbc_lblTituloDatos.insets = new Insets(0, 0, 5, 0);
		gbc_lblTituloDatos.gridx = 0;
		gbc_lblTituloDatos.gridy = 0;
		panelDatos.add(lblTituloDatos, gbc_lblTituloDatos);
		
		lblNOMBREE = new JLabel("  Nombre:");
		lblNOMBREE.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNOMBREE = new GridBagConstraints();
		gbc_lblNOMBREE.anchor = GridBagConstraints.WEST;
		gbc_lblNOMBREE.insets = new Insets(0, 0, 5, 0);
		gbc_lblNOMBREE.gridx = 0;
		gbc_lblNOMBREE.gridy = 1;
		panelDatos.add(lblNOMBREE, gbc_lblNOMBREE);
		
		lblNombre = new JLabel("-");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 17));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		lblDEUDAE = new JLabel("  Deuda actual:");
		lblDEUDAE.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDEUDAE = new GridBagConstraints();
		gbc_lblDEUDAE.anchor = GridBagConstraints.WEST;
		gbc_lblDEUDAE.insets = new Insets(0, 0, 5, 0);
		gbc_lblDEUDAE.gridx = 0;
		gbc_lblDEUDAE.gridy = 3;
		panelDatos.add(lblDEUDAE, gbc_lblDEUDAE);
		
		lblDeuda = new JLabel("$ 0.00");
		lblDeuda.setFont(new Font("Segoe UI", Font.BOLD, 17));
		GridBagConstraints gbc_lblDeuda = new GridBagConstraints();
		gbc_lblDeuda.insets = new Insets(0, 0, 5, 0);
		gbc_lblDeuda.gridx = 0;
		gbc_lblDeuda.gridy = 4;
		panelDatos.add(lblDeuda, gbc_lblDeuda);
		
		lblMONTO = new JLabel("  Monto a cargar:");
		lblMONTO.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMONTO = new GridBagConstraints();
		gbc_lblMONTO.insets = new Insets(0, 0, 5, 0);
		gbc_lblMONTO.anchor = GridBagConstraints.WEST;
		gbc_lblMONTO.gridx = 0;
		gbc_lblMONTO.gridy = 5;
		panelDatos.add(lblMONTO, gbc_lblMONTO);
		
		lblMonto = new JLabel("$ 0.00");
		lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 17));
		GridBagConstraints gbc_lblMonto = new GridBagConstraints();
		gbc_lblMonto.insets = new Insets(0, 0, 5, 0);
		gbc_lblMonto.gridx = 0;
		gbc_lblMonto.gridy = 6;
		panelDatos.add(lblMonto, gbc_lblMonto);
		
		lblNDEUDA = new JLabel("  Nueva deuda:");
		lblNDEUDA.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNDEUDA = new GridBagConstraints();
		gbc_lblNDEUDA.insets = new Insets(0, 0, 5, 0);
		gbc_lblNDEUDA.anchor = GridBagConstraints.WEST;
		gbc_lblNDEUDA.gridx = 0;
		gbc_lblNDEUDA.gridy = 7;
		panelDatos.add(lblNDEUDA, gbc_lblNDEUDA);
		
		lblNuevaDeuda = new JLabel("$ 0.00");
		lblNuevaDeuda.setFont(new Font("Segoe UI", Font.BOLD, 17));
		GridBagConstraints gbc_lblNuevaDeuda = new GridBagConstraints();
		gbc_lblNuevaDeuda.gridx = 0;
		gbc_lblNuevaDeuda.gridy = 8;
		panelDatos.add(lblNuevaDeuda, gbc_lblNuevaDeuda);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(426, 301, 112, 33);
		contentPane.add(btnCancelar);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listaCuentas.getSelectedIndex() != -1) {
					controlador.aumentarSaldoDeCuenta(listaCuentas.getSelectedIndex(), venta.calcularTotal());
					dispose();
				}
				
			}
		});
		btnTerminar.setBounds(426, 203, 112, 94);
		contentPane.add(btnTerminar);
		
		btnAgregarCuenta = new JButton("Nueva Cuenta");
		btnAgregarCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.abrirVentanaNuevaCuenta();
			}
		});
		btnAgregarCuenta.setBounds(426, 149, 112, 50);
		contentPane.add(btnAgregarCuenta);
	}

	// getters ------------
	public Controlador getControlador() {
		return controlador;
	}

	public JPanel getPanelLista() {
		return panelLista;
	}

	public JList<String> getListaCuentas() {
		return listaCuentas;
	}

	public JLabel getLblCuentasExistentes() {
		return lblCuentasExistentes;
	}

	public JPanel getPanelDatos() {
		return panelDatos;
	}

	public JLabel getLblTituloDatos() {
		return lblTituloDatos;
	}

	public JLabel getLblNOMBREE() {
		return lblNOMBREE;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblDEUDAE() {
		return lblDEUDAE;
	}

	public JLabel getLblDeuda() {
		return lblDeuda;
	}

	public JLabel getLblMONTO() {
		return lblMONTO;
	}

	public JLabel getLblMonto() {
		return lblMonto;
	}

	public JLabel getLblNDEUDA() {
		return lblNDEUDA;
	}

	public JLabel getLblNuevaDeuda() {
		return lblNuevaDeuda;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnTerminar() {
		return btnTerminar;
	}

	public JButton getBtnAgregarCuenta() {
		return btnAgregarCuenta;
	}
	
	public Venta getVenta() {
		return venta;
	}

	// setters ---------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setPanelLista(JPanel panelLista) {
		this.panelLista = panelLista;
	}

	public void setListaCuentas(JList<String> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public void setLblCuentasExistentes(JLabel lblCuentasExistentes) {
		this.lblCuentasExistentes = lblCuentasExistentes;
	}

	public void setPanelDatos(JPanel panelDatos) {
		this.panelDatos = panelDatos;
	}

	public void setLblTituloDatos(JLabel lblTituloDatos) {
		this.lblTituloDatos = lblTituloDatos;
	}

	public void setLblNOMBREE(JLabel lblNOMBREE) {
		this.lblNOMBREE = lblNOMBREE;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public void setLblDEUDAE(JLabel lblDEUDAE) {
		this.lblDEUDAE = lblDEUDAE;
	}

	public void setLblDeuda(JLabel lblDeuda) {
		this.lblDeuda = lblDeuda;
	}

	public void setLblMONTO(JLabel lblMONTO) {
		this.lblMONTO = lblMONTO;
	}

	public void setLblMonto(JLabel lblMonto) {
		this.lblMonto = lblMonto;
	}

	public void setLblNDEUDA(JLabel lblNDEUDA) {
		this.lblNDEUDA = lblNDEUDA;
	}

	public void setLblNuevaDeuda(JLabel lblNuevaDeuda) {
		this.lblNuevaDeuda = lblNuevaDeuda;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setBtnTerminar(JButton btnTerminar) {
		this.btnTerminar = btnTerminar;
	}

	public void setBtnAgregarCuenta(JButton btnAgregarCuenta) {
		this.btnAgregarCuenta = btnAgregarCuenta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}
