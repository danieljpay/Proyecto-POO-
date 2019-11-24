package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VentanaHistorialVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Controlador controlador;
	
	private JPanel panelTabla;
	private JTable tablaVentas;
	private JLabel lblHistorialDeVentas;
	private JButton btnSalir;
	private JLabel lblFiltrarPor;
	private JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	public VentanaHistorialVentas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 440);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelTabla = new JPanel();
		panelTabla.setBounds(10, 52, 473, 338);
		contentPane.add(panelTabla);
		GridBagLayout gbl_panelTabla = new GridBagLayout();
		gbl_panelTabla.columnWidths = new int[]{0, 0};
		gbl_panelTabla.rowHeights = new int[]{0, 0};
		gbl_panelTabla.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTabla.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelTabla.setLayout(gbl_panelTabla);
		
		tablaVentas = new JTable();
		tablaVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_tablaVentas = new GridBagConstraints();
		gbc_tablaVentas.fill = GridBagConstraints.BOTH;
		gbc_tablaVentas.gridx = 0;
		gbc_tablaVentas.gridy = 0;
		panelTabla.add(tablaVentas, gbc_tablaVentas);
		
		lblHistorialDeVentas = new JLabel("Historial de Ventas");
		lblHistorialDeVentas.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblHistorialDeVentas.setBounds(10, 11, 276, 30);
		contentPane.add(lblHistorialDeVentas);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(493, 360, 143, 30);
		contentPane.add(btnSalir);
		
		lblFiltrarPor = new JLabel("Mostrar:");
		lblFiltrarPor.setBounds(495, 52, 87, 14);
		contentPane.add(lblFiltrarPor);
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// AQUÍ SE LLAMA AL CONSTRUCTOR PARA CAMBIAR LA TABLA DE VENTAS.
				
			}
		});
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
		modelo.addElement("Todas");
		modelo.addElement("Último mes");
		modelo.addElement("Última semana");
		modelo.addElement("Hoy");
		comboBox.setModel(modelo);;
		comboBox.setBounds(493, 77, 143, 30);
		contentPane.add(comboBox);
	}

	// getters --------
	public Controlador getControlador() {
		return controlador;
	}
	
	public JPanel getPanelTabla() {
		return panelTabla;
	}

	public JTable getTablaVentas() {
		return tablaVentas;
	}

	public JLabel getLblHistorialDeVentas() {
		return lblHistorialDeVentas;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}
	
	// setters -----------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setPanelTabla(JPanel panelTabla) {
		this.panelTabla = panelTabla;
	}

	public void setTablaVentas(JTable tablaVentas) {
		this.tablaVentas = tablaVentas;
	}

	public void setLblHistorialDeVentas(JLabel lblHistorialDeVentas) {
		this.lblHistorialDeVentas = lblHistorialDeVentas;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	
}
