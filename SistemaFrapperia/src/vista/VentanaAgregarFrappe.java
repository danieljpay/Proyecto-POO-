package vista;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Frappe;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaAgregarFrappe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// controlador declarado.
	private Controlador controlador;
	
	// componentes declarados.
	private JLabel lblTitulo;
	
	private JPanel panelDatos;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblPrecio;
	private JTextField tfPrecio;
	
	private JPanel panelIngredientes;
	private JButton btnAgregar;
	private JButton btnEliminar;
	
	private JList<String> listaIngredientes;
	private DefaultListModel<String> modeloListaIngredientes;
	//private DefaultListModel<String> modeloListaAgregados;
	private JLabel lblElijeUnIngrediente;
	private JLabel lblIngredientesDisponibles;
	private JButton btnCrearNuevoIngrediente;
	
	private JButton btnHecho;
	private JLabel lblMensajes;
	
	private JTable tablaAgregados;
	private DefaultTableModel modeloTablaAgregados;
	
	private Frappe frappe; // el frappe que se va a agregar.
	private JScrollPane scrollAgregados;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public VentanaAgregarFrappe() {
		
		frappe = new Frappe();
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 495);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Agregar Frapp\u00E9");
		lblTitulo.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblTitulo.setBounds(10, 11, 158, 32);
		contentPane.add(lblTitulo);
		
		panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "   Datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelDatos.setBounds(10, 48, 398, 118);
		contentPane.add(panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 326, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 31, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
	    lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField();
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tfNombre.fill = GridBagConstraints.BOTH;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 0;
		panelDatos.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.anchor = GridBagConstraints.EAST;
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 0;
		gbc_lblPrecio.gridy = 1;
		panelDatos.add(lblPrecio, gbc_lblPrecio);
		
		tfPrecio = new JTextField();
		tfPrecio.addKeyListener(new KeyAdapter() {
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
				
				String precio = tfPrecio.getText();
				
				if(precioValido(precio) == false) {
						lblMensajes.setText("Ingrese un precio válido.");
					}
					else {
						lblMensajes.setText("");
					}
				} 
			
		});
		GridBagConstraints gbc_tfPrecio = new GridBagConstraints();
		gbc_tfPrecio.insets = new Insets(0, 0, 5, 0);
		gbc_tfPrecio.fill = GridBagConstraints.BOTH;
		gbc_tfPrecio.gridx = 1;
		gbc_tfPrecio.gridy = 1;
		panelDatos.add(tfPrecio, gbc_tfPrecio);
		tfPrecio.setColumns(10);
		
		lblMensajes = new JLabel("");
		GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
		gbc_lblMensajes.gridwidth = 2;
		gbc_lblMensajes.gridx = 0;
		gbc_lblMensajes.gridy = 2;
		panelDatos.add(lblMensajes, gbc_lblMensajes);
		
		panelIngredientes = new JPanel();
		panelIngredientes.setBorder(new TitledBorder(null, "   Ingredientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelIngredientes.setBounds(10, 177, 398, 220);
		contentPane.add(panelIngredientes);
		GridBagLayout gbl_panelIngredientes = new GridBagLayout();
		gbl_panelIngredientes.columnWidths = new int[]{106, 82, 230, 0};
		gbl_panelIngredientes.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelIngredientes.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelIngredientes.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panelIngredientes.setLayout(gbl_panelIngredientes);
		
		lblIngredientesDisponibles = new JLabel("Ingredientes disponibles.");
		GridBagConstraints gbc_lblIngredientesDisponibles = new GridBagConstraints();
		gbc_lblIngredientesDisponibles.gridwidth = 2;
		gbc_lblIngredientesDisponibles.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngredientesDisponibles.gridx = 0;
		gbc_lblIngredientesDisponibles.gridy = 0;
		panelIngredientes.add(lblIngredientesDisponibles, gbc_lblIngredientesDisponibles);
		
		lblElijeUnIngrediente = new JLabel("Ingredientes agregados.");
		GridBagConstraints gbc_lblElijeUnIngrediente = new GridBagConstraints();
		gbc_lblElijeUnIngrediente.insets = new Insets(0, 0, 5, 0);
		gbc_lblElijeUnIngrediente.gridx = 2;
		gbc_lblElijeUnIngrediente.gridy = 0;
		panelIngredientes.add(lblElijeUnIngrediente, gbc_lblElijeUnIngrediente);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					controlador.abrirDialogoPedirCantidad(listaIngredientes.getSelectedValue());
					
					lblMensajes.setText(null);
				} catch(ArrayIndexOutOfBoundsException e){
				  	lblMensajes.setText("Debe seleccionar un ingrediente.");
				}	
			}
		});
		
		scrollAgregados = new JScrollPane();
		GridBagConstraints gbc_scrollAgregados = new GridBagConstraints();
		gbc_scrollAgregados.gridheight = 2;
		gbc_scrollAgregados.insets = new Insets(0, 0, 5, 0);
		gbc_scrollAgregados.fill = GridBagConstraints.BOTH;
		gbc_scrollAgregados.gridx = 2;
		gbc_scrollAgregados.gridy = 1;
		panelIngredientes.add(scrollAgregados, gbc_scrollAgregados);
		
		tablaAgregados = new JTable();
		tablaAgregados.setFocusable(false);
		scrollAgregados.setViewportView(tablaAgregados);
		tablaAgregados.setShowVerticalLines(false);
		tablaAgregados.setShowHorizontalLines(false);
		tablaAgregados.setShowGrid(false);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelIngredientes.add(scrollPane, gbc_scrollPane);
		
		listaIngredientes = new JList<String>();
		scrollPane.setViewportView(listaIngredientes);
		//modeloListaIngredientes = new DefaultListModel<String>();
		//listaIngredientes.setModel(modeloListaIngredientes);
		listaIngredientes.setBackground(Color.LIGHT_GRAY);
		
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAgregar.gridx = 0;
		gbc_btnAgregar.gridy = 3;
		panelIngredientes.add(btnAgregar, gbc_btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				int fila = tablaAgregados.getSelectedRow();
				
				if(fila != -1) {
					String nombreIngrediente = (String) tablaAgregados.getModel().getValueAt(fila, 0);
					
					modeloListaIngredientes.addElement(nombreIngrediente);
					listaIngredientes.setModel(modeloListaIngredientes);
					
					modeloTablaAgregados.removeRow(fila);
					tablaAgregados.setModel(modeloTablaAgregados);
					
					lblMensajes.setText(null);
				} else {
					lblMensajes.setText("Debe seleccionar un ingrediente agregado.");
				}
				
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 3;
		panelIngredientes.add(btnEliminar, gbc_btnEliminar);
		
		btnCrearNuevoIngrediente = new JButton("Crear nuevo ingrediente");
		btnCrearNuevoIngrediente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.abrirVentanaAgregarIngredientes(true);
			}
		});
		GridBagConstraints gbc_btnCrearNuevoIngrediente = new GridBagConstraints();
		gbc_btnCrearNuevoIngrediente.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrearNuevoIngrediente.gridx = 2;
		gbc_btnCrearNuevoIngrediente.gridy = 3;
		panelIngredientes.add(btnCrearNuevoIngrediente, gbc_btnCrearNuevoIngrediente);
		
		btnHecho = new JButton("Hecho");
		btnHecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			    // generamos la lista.
				JList<String> listaIngreAgregados = new JList<String>();
				DefaultListModel<String> modelo = new DefaultListModel<String>();
				
				for(int i=0; i<tablaAgregados.getModel().getRowCount(); i++) {
					String nombreIngrediente = (String)tablaAgregados.getValueAt(i, 0);
					modelo.addElement(nombreIngrediente);
				}
				
				listaIngreAgregados.setModel(modelo);
				
				if(camposCompletos()) {
					controlador.agregarFrappe(tfNombre.getText(), tfPrecio.getText(), listaIngreAgregados);
					dispose();
				}
				else {
					lblMensajes.setText("Debes llenar todos campos.");
				}
				
			}
		});
		btnHecho.setBounds(10, 408, 299, 47);
		contentPane.add(btnHecho);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(314, 408, 94, 47);
		contentPane.add(btnCancelar);
	}


	// Métodos
	// identifica que no haya más de un punto decimal en el número.
	public boolean precioValido(String precio) {
		char[] arregloPrecio = precio.toCharArray();
		
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
	
	public boolean camposCompletos() {
		
		if(tfNombre.getText().isEmpty()
				|| tfPrecio.getText().isEmpty()) {
			return false;
		}
		else {
			if(precioValido(tfPrecio.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	//Bloquear edicion del nombre
	public void bloqueoNombre() {
		tfNombre.setEditable(false);
	}
	

	// GETTERS
	public Controlador getControlador() {
		return controlador;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JPanel getPanelDatos() {
		return panelDatos;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public JPanel getPanelIngredientes() {
		return panelIngredientes;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JList<String> getListaIngredientes() {
		return listaIngredientes;
	}

	public JLabel getLblElijeUnIngrediente() {
		return lblElijeUnIngrediente;
	}

	public JLabel getLblIngredientesDisponibles() {
		return lblIngredientesDisponibles;
	}

	public JButton getBtnCrearNuevoIngrediente() {
		return btnCrearNuevoIngrediente;
	}

	public JButton getBtnHecho() {
		return btnHecho;
	}
	
	public DefaultListModel<String> getModeloListaIngredientes() {
		return modeloListaIngredientes;
	}

	public JLabel getLblMensajes() {
		return lblMensajes;
	}


	
	// SETTERS

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public void setPanelDatos(JPanel panelDatos) {
		this.panelDatos = panelDatos;
	}
	
	
	
	
	// ----------------------------------------------
	public JTable getTablaAgregados() {
		return tablaAgregados;
	}


	public void setListaIngredientes(JList<String> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}


	public void setTablaAgregados(JTable tablaAgregados) {
		this.tablaAgregados = tablaAgregados;
	}


	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public void setPanelIngredientes(JPanel panelIngredientes) {
		this.panelIngredientes = panelIngredientes;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void setListaIngredientes(DefaultListModel<String> modelo) {
		this.listaIngredientes.setModel(modelo);
	}

	public void setLblElijeUnIngrediente(JLabel lblElijeUnIngrediente) {
		this.lblElijeUnIngrediente = lblElijeUnIngrediente;
	}

	public void setLblIngredientesDisponibles(JLabel lblIngredientesDisponibles) {
		this.lblIngredientesDisponibles = lblIngredientesDisponibles;
	}

	public void setBtnCrearNuevoIngrediente(JButton btnCrearNuevoIngrediente) {
		this.btnCrearNuevoIngrediente = btnCrearNuevoIngrediente;
	}

	public void setBtnHecho(JButton btnHecho) {
		this.btnHecho = btnHecho;
	}

	public void setModeloListaIngredientes(DefaultListModel<String> modeloListaIngredientes) {
		this.modeloListaIngredientes = modeloListaIngredientes;
	}

	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}


	public DefaultTableModel getModeloTablaAgregados() {
		return modeloTablaAgregados;
	}


	public void setModeloTablaAgregados(DefaultTableModel modeloTablaAgregados) {
		this.modeloTablaAgregados = modeloTablaAgregados;
	}


	public Frappe getFrappe() {
		return frappe;
	}


	public void setFrappe(Frappe frappe) {
		this.frappe = frappe;
	}
}
