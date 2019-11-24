package vista;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
//import java.awt.MenuBar;

import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// componentes -------------------------------
	// controlador
	private Controlador controlador;
	// menús
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnInventario;
	private JMenuItem mntmIngredientesExistentes;
	private JMenuItem mntmFrappsExistentes;
	private JMenuItem mntmNuevoIngrediente;
	private JMenuItem mntmNuevoFrapp;
	private JMenu mnVentas;
	private JMenuItem mntmHistorialDeVentas;
	private JMenuItem mntmCuentasPendientes;
	
	private JPanel panelVentas;
	private JTabbedPane tabbedPane;
	private PanelVenta panelVenta;
	//private PanelTab panelTab;
	
	private JPanel panelOpciones;
	private JButton btnNuevaVenta;
	private JButton btnVerVentas;
	private JButton btnActualizar;
	private JMenu mnFrappes;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		setTitle("punto de venta frapper\u00EDa");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 470);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // REVISAR.
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);
		
		mntmHistorialDeVentas = new JMenuItem("Historial de ventas");
		mntmHistorialDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.abrirVentanaHistorialVentas();
			}
		});
		mnVentas.add(mntmHistorialDeVentas);
		
		mntmCuentasPendientes = new JMenuItem("Cuentas pendientes");
		mntmCuentasPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaCuentas();
			}
		});
		mnVentas.add(mntmCuentasPendientes);
		
		mnFrappes = new JMenu("Frapp\u00E9s");
		menuBar.add(mnFrappes);
		
		mntmFrappsExistentes = new JMenuItem("Frapp\u00E9s existentes");
		mnFrappes.add(mntmFrappsExistentes);
		
		mntmNuevoFrapp = new JMenuItem("Nuevo frapp\u00E9");
		mnFrappes.add(mntmNuevoFrapp);
		mntmNuevoFrapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.abrirVentanaAgregarFrappe();
			}
		});
		mntmFrappsExistentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.abrirVentanaFrappes();
			}
		});
		
		mnInventario = new JMenu("Inventario");
		menuBar.add(mnInventario);
		
		mntmIngredientesExistentes = new JMenuItem("Ingredientes existentes");
		mntmIngredientesExistentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.abrirVentanaIngredientes();
			}
		});
		mnInventario.add(mntmIngredientesExistentes);
		
		mntmNuevoIngrediente = new JMenuItem("Nuevo ingrediente");
		mnInventario.add(mntmNuevoIngrediente);
		mntmNuevoIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.abrirVentanaAgregarIngredientes(false);
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelVentas = new JPanel();
		panelVentas.setBounds(10, 11, 644, 398);
		contentPane.add(panelVentas);
		GridBagLayout gbl_panelVentas = new GridBagLayout();
		gbl_panelVentas.columnWidths = new int[]{0, 0};
		gbl_panelVentas.rowHeights = new int[]{0, 0};
		gbl_panelVentas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelVentas.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelVentas.setLayout(gbl_panelVentas);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panelVentas.add(tabbedPane, gbc_tabbedPane);
		
		panelVenta = new PanelVenta();
		GridBagLayout gridBagLayout = (GridBagLayout) panelVenta.getLayout();
		gridBagLayout.columnWidths = new int[]{137, 0, 0, 278, 99};
		tabbedPane.addTab("nueva venta", null, panelVenta, null);
		
		//panelTab = new PanelTab();
		//tabbedPane.setTabComponentAt(0, panelTab); //el primer tab no se pude eliminar.
		
		panelOpciones = new JPanel();
		panelOpciones.setBounds(664, 34, 110, 177);
		contentPane.add(panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{0, 0};
		gbl_panelOpciones.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelOpciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.nuevaVenta();
			}
		});
		GridBagConstraints gbc_btnNuevaVenta = new GridBagConstraints();
		gbc_btnNuevaVenta.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevaVenta.fill = GridBagConstraints.BOTH;
		gbc_btnNuevaVenta.gridx = 0;
		gbc_btnNuevaVenta.gridy = 0;
		panelOpciones.add(btnNuevaVenta, gbc_btnNuevaVenta);
		
		btnVerVentas = new JButton("Ver Ventas");
		btnVerVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.abrirVentanaHistorialVentas();
			}
		});
		GridBagConstraints gbc_btnVerVentas = new GridBagConstraints();
		gbc_btnVerVentas.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerVentas.fill = GridBagConstraints.BOTH;
		gbc_btnVerVentas.gridx = 0;
		gbc_btnVerVentas.gridy = 1;
		panelOpciones.add(btnVerVentas, gbc_btnVerVentas);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelVenta.getListaFrappes().setModel(controlador.getModeloListaFrappes());
				
			}
		});
		GridBagConstraints gbc_btnActualizar = new GridBagConstraints();
		gbc_btnActualizar.fill = GridBagConstraints.BOTH;
		gbc_btnActualizar.gridx = 0;
		gbc_btnActualizar.gridy = 3;
		panelOpciones.add(btnActualizar, gbc_btnActualizar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnSalir.setBounds(664, 358, 110, 51);
		contentPane.add(btnSalir);
		
		
		
		// pedimos la información al controlador.
		//this.panelVenta.getListaFrappes().setModel(controlador.getListaIngredientes());
	}
	
	
	// getters ---------------------------
	public Controlador getControlador() {
		return controlador;
	}
	
	/*
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	*/
	public JMenu getMnArchivo() {
		return mnArchivo;
	}
	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}
	public JMenu getMnInventario() {
		return mnInventario;
	}
	public JMenuItem getMntmIngredientesExistentes() {
		return mntmIngredientesExistentes;
	}
	public JMenuItem getMntmFrappsExistentes() {
		return mntmFrappsExistentes;
	}
	public JMenuItem getMntmNuevoIngrediente() {
		return mntmNuevoIngrediente;
	}
	public JMenuItem getMntmNuevoFrapp() {
		return mntmNuevoFrapp;
	}
	public JMenu getMnVer() {
		return mnVentas;
	}
	public JMenuItem getMntmHistorialDeVentas() {
		return mntmHistorialDeVentas;
	}
	public JMenuItem getMntmVentasPendientes() {
		return mntmCuentasPendientes;
	}
	public JPanel getPanelVentas() {
		return panelVentas;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public PanelVenta getPanelVenta() {
		return panelVenta;
	}
	public JPanel getPanelOpciones() {
		return panelOpciones;
	}
	public JButton getBtnNuevaVenta() {
		return btnNuevaVenta;
	}
	public JButton getBtnVerVentas() {
		return btnVerVentas;
	}
	public JButton getBtnActualizar() {
		return btnActualizar;
	}
	
	// setters -------------------------------------------------------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	public void setMnArchivo(JMenu mnArchivo) {
		this.mnArchivo = mnArchivo;
	}
	public void setMntmSalir(JMenuItem mntmSalir) {
		this.mntmSalir = mntmSalir;
	}
	public void setMnInventario(JMenu mnInventario) {
		this.mnInventario = mnInventario;
	}
	public void setMntmIngredientesExistentes(JMenuItem mntmIngredientesExistentes) {
		this.mntmIngredientesExistentes = mntmIngredientesExistentes;
	}
	public void setMntmFrappsExistentes(JMenuItem mntmFrappsExistentes) {
		this.mntmFrappsExistentes = mntmFrappsExistentes;
	}
	public void setMntmNuevoIngrediente(JMenuItem mntmNuevoIngrediente) {
		this.mntmNuevoIngrediente = mntmNuevoIngrediente;
	}
	public void setMntmNuevoFrapp(JMenuItem mntmNuevoFrapp) {
		this.mntmNuevoFrapp = mntmNuevoFrapp;
	}
	public void setMnVer(JMenu mnVer) {
		this.mnVentas = mnVer;
	}
	public void setMntmHistorialDeVentas(JMenuItem mntmHistorialDeVentas) {
		this.mntmHistorialDeVentas = mntmHistorialDeVentas;
	}
	public void setMntmVentasPendientes(JMenuItem mntmVentasPendientes) {
		this.mntmCuentasPendientes = mntmVentasPendientes;
	}
	public void setPanelVentas(JPanel panelVentas) {
		this.panelVentas = panelVentas;
	}
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	public void setPanelVenta(PanelVenta panelVenta) {
		this.panelVenta = panelVenta;
	}
	public void setPanelOpciones(JPanel panelOpciones) {
		this.panelOpciones = panelOpciones;
	}
	public void setBtnNuevaVenta(JButton btnNuevaVenta) {
		this.btnNuevaVenta = btnNuevaVenta;
	}
	public void setBtnVerVentas(JButton btnVerVentas) {
		this.btnVerVentas = btnVerVentas;
	}
	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}
}
