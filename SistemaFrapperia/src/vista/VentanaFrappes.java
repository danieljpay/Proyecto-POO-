package vista;

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
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class VentanaFrappes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Componentes
		private Controlador controlador;
		private JLabel lblTitulo;
		
		private JPanel panelTabla;
		private JTable tablaFrappes;
		private JButton btnEditar;
		private JButton btnAgregar;
		private JButton btnEliminar;
		private JButton btnSalir;

		/**
		 * Create the frame.
		 */
		public VentanaFrappes() {
			
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 540, 439);
			setLocationRelativeTo(null);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			lblTitulo = new JLabel("Frapp\u00E9s");
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
			btnEditar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					
					controlador.abrirVentanaEditarFrappe(tablaFrappes.getSelectedRow()-1);
					// checar si funciona ese index 
				}
			});
			
			
			GridBagConstraints gbc_btnEditar = new GridBagConstraints();
			gbc_btnEditar.fill = GridBagConstraints.BOTH;
			gbc_btnEditar.insets = new Insets(0, 0, 5, 0);
			gbc_btnEditar.gridx = 1;
			gbc_btnEditar.gridy = 0;
			panelTabla.add(btnEditar, gbc_btnEditar);
			
			tablaFrappes = new JTable();
			tablaFrappes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaFrappes.setBorder(new LineBorder(new Color(0, 0, 0)));
			GridBagConstraints gbc_tablaFrappes = new GridBagConstraints();
			gbc_tablaFrappes.gridheight = 4;
			gbc_tablaFrappes.insets = new Insets(0, 0, 0, 5);
			gbc_tablaFrappes.fill = GridBagConstraints.BOTH;
			gbc_tablaFrappes.gridx = 0;
			gbc_tablaFrappes.gridy = 0;
			panelTabla.add(tablaFrappes, gbc_tablaFrappes);
			
			btnAgregar = new JButton("Agregar ");
			btnAgregar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					controlador.abrirVentanaAgregarFrappe();
				}
			});
			GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
			gbc_btnAgregar.fill = GridBagConstraints.BOTH;
			gbc_btnAgregar.insets = new Insets(0, 0, 5, 0);
			gbc_btnAgregar.gridx = 1;
			gbc_btnAgregar.gridy = 1;
			panelTabla.add(btnAgregar, gbc_btnAgregar);
			
			btnEliminar = new JButton("Eliminar");
			GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
			gbc_btnEliminar.fill = GridBagConstraints.BOTH;
			gbc_btnEliminar.insets = new Insets(0, 0, 5, 0);
			gbc_btnEliminar.gridx = 1;
			gbc_btnEliminar.gridy = 2;
			panelTabla.add(btnEliminar, gbc_btnEliminar);
			
			btnSalir = new JButton("Salir");
			btnSalir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
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
			return tablaFrappes;
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
			this.tablaFrappes = tablaIngredientes;
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
