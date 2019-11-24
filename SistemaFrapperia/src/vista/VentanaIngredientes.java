package vista;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIngredientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Componentes
	private Controlador controlador;
	private JLabel lblTitulo;
	
	private JPanel panelTabla;
	private JTable tablaIngredientes;
	private JButton btnEditar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public VentanaIngredientes() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 439);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Ingredientes");
		lblTitulo.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblTitulo.setBounds(10, 11, 177, 26);
		contentPane.add(lblTitulo);
		
		panelTabla = new JPanel();
		panelTabla.setBounds(10, 48, 513, 349);
		contentPane.add(panelTabla);
		GridBagLayout gbl_panelTabla = new GridBagLayout();
		gbl_panelTabla.columnWidths = new int[]{418, 0, 0};
		gbl_panelTabla.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelTabla.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTabla.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelTabla.setLayout(gbl_panelTabla);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaEditarIngrediente();
			}
		});
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.fill = GridBagConstraints.BOTH;
		gbc_btnEditar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditar.gridx = 1;
		gbc_btnEditar.gridy = 0;
		panelTabla.add(btnEditar, gbc_btnEditar);
		
		tablaIngredientes = new JTable();
		tablaIngredientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_tablaIngredientes = new GridBagConstraints();
		gbc_tablaIngredientes.gridheight = 4;
		gbc_tablaIngredientes.insets = new Insets(0, 0, 0, 5);
		gbc_tablaIngredientes.fill = GridBagConstraints.BOTH;
		gbc_tablaIngredientes.gridx = 0;
		gbc_tablaIngredientes.gridy = 0;
		panelTabla.add(tablaIngredientes, gbc_tablaIngredientes);
		
		btnAgregar = new JButton("Agregar ");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaAgregarIngredientes(true);
			}
		});
		
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.fill = GridBagConstraints.BOTH;
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregar.gridx = 1;
		gbc_btnAgregar.gridy = 1;
		panelTabla.add(btnAgregar, gbc_btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tablaIngredientes.getSelectedRow();
				controlador.eliminarIngrediente(index);
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.BOTH;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 2;
		panelTabla.add(btnEliminar, gbc_btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.BOTH;
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 3;
		panelTabla.add(btnSalir, gbc_btnSalir);
	}

	
	// GETTERS
	public Controlador getControlador() {
		return controlador;
	}

	public JLabel getLblEmpleados() {
		return lblTitulo;
	}

	public JPanel getPanelTabla() {
		return panelTabla;
	}

	public JTable getTablaIngredientes() {
		return tablaIngredientes;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}
	
	
	// SETTERS
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLblEmpleados(JLabel lblEmpleados) {
		this.lblTitulo = lblEmpleados;
	}

	public void setPanelTabla(JPanel panelTabla) {
		this.panelTabla = panelTabla;
	}

	public void setTablaIngredientes(JTable tablaIngredientes) {
		this.tablaIngredientes = tablaIngredientes;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	
	
}
