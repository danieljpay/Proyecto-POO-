package vista;
import java.awt.event.ActionListener;

//import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;

import modelo.Cuenta;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;


public class VentanaCuentas extends VentanaVentaACuenta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tftAbono;
	private JPanel panelAbonar;
	private JLabel lblCantidad;
	private JLabel lblMensajes;
	private JButton btnEliminar;
	
	//private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	
	public VentanaCuentas() {
		
		super();
		getBtnCancelar().setText("Cerrar");
		
		setBounds(100, 100, 595, 374);
		setLocationRelativeTo(null);
		getLblNuevaDeuda().setForeground(Color.LIGHT_GRAY);
		getLblNDEUDA().setForeground(Color.LIGHT_GRAY);
		getLblMonto().setForeground(Color.LIGHT_GRAY);
		getLblMONTO().setForeground(Color.LIGHT_GRAY);
		
		getListaCuentas().removeListSelectionListener(getListaCuentas().getListSelectionListeners()[0]);
		getListaCuentas().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// pedimos la cuenta al controlador
				
				try {
					Cuenta cuenta = getControlador().getUnaCuenta(getListaCuentas().getSelectedIndex());
					
					// mostramos la información.
					getLblNombre().setText(cuenta.getNombre());
					getLblDeuda().setText("$ " + cuenta.getSaldoPendiente());
					
					getLblMonto().setText("-");
					getLblNuevaDeuda().setText("-");
					
					tftAbono.setText(null);
					
					// activamos función de abonar.
					lblCantidad.setForeground(Color.BLACK);
					lblMensajes.setForeground(Color.BLACK);
		
					tftAbono.setEnabled(true);
					getBtnTerminar().setEnabled(false);
					btnEliminar.setEnabled(true);
				
				} catch(ArrayIndexOutOfBoundsException e1) {
					// no es necesario tratarlo, no causa problemas al usuario.
					resetCampos();
				}
				
			}
		});
		
		getBtnTerminar().setEnabled(false);
		setTitle("cuentas");
		getBtnAgregarCuenta().setSize(156, 23);

		getBtnCancelar().setBounds(426, 301, 156, 33);
		getBtnTerminar().setBounds(426, 203, 156, 94);
		getPanelDatos().setBounds(187, 11, 229, 323);
		getBtnAgregarCuenta().setLocation(426, 11);
		
		getBtnTerminar().setText("Abonar");
		getBtnTerminar().removeActionListener(getBtnTerminar().getActionListeners()[0]);
		getBtnTerminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// realizamos el abono
				getControlador().abonarACuenta(getListaCuentas().getSelectedIndex(), tftAbono.getText());
				
				// actualizamos la información.
				Cuenta cuenta = getControlador().getUnaCuenta(getListaCuentas().getSelectedIndex());
				getLblDeuda().setText("$ " + cuenta.getSaldoPendiente());
				getLblMonto().setText("-");
				getLblNuevaDeuda().setText("$ " + cuenta.getSaldoPendiente());
				
				tftAbono.setText("");
				
				getBtnTerminar().setEnabled(false);
				
			}
		});
		getLblMONTO().setText("  Abono:");
		
		panelAbonar = new JPanel();
		panelAbonar.setForeground(Color.LIGHT_GRAY);
		panelAbonar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " abonar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelAbonar.setBounds(426, 74, 156, 118);
		getContentPane().add(panelAbonar);
		GridBagLayout gbl_panelAbonar = new GridBagLayout();
		gbl_panelAbonar.columnWidths = new int[]{0, 0};
		gbl_panelAbonar.rowHeights = new int[]{0, 31, 0, 0};
		gbl_panelAbonar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAbonar.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelAbonar.setLayout(gbl_panelAbonar);
		
		lblCantidad = new JLabel("Ingrese una cantidad:");
		lblCantidad.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 0);
		gbc_lblCantidad.gridx = 0;
		gbc_lblCantidad.gridy = 0;
		panelAbonar.add(lblCantidad, gbc_lblCantidad);
		
		tftAbono = new JTextField();
		tftAbono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				// activar campos.
				getLblNuevaDeuda().setForeground(Color.BLACK);
				getLblNDEUDA().setForeground(Color.BLACK);
				getLblMonto().setForeground(Color.BLACK);
				getLblMONTO().setForeground(Color.BLACK);
				
				// pedimos la cuenta la controlador
				Cuenta cuenta = getControlador().getUnaCuenta(getListaCuentas().getSelectedIndex());
				
				try {	
					
					double abono = Double.parseDouble(tftAbono.getText());
					
					
					getLblMonto().setText("$ " + abono);
					
					if(abono > cuenta.getSaldoPendiente()) {
						getLblNuevaDeuda().setText("$ 0.00");
					} else {
						getLblNuevaDeuda().setText("$ " + (cuenta.getSaldoPendiente() - abono));
					}
					
					getBtnTerminar().setEnabled(true);
					
					
				} catch(NumberFormatException e) {
					
					lblMensajes.setText("Cantidad invalida.");
					getLblMonto().setText("$ 0.00");
					getLblNuevaDeuda().setText("$ " + cuenta.getSaldoPendiente());
					
					getBtnTerminar().setEnabled(false);
				}
			}
		});
		tftAbono.setEnabled(false);
		GridBagConstraints gbc_tftAbono = new GridBagConstraints();
		gbc_tftAbono.insets = new Insets(0, 0, 5, 0);
		gbc_tftAbono.fill = GridBagConstraints.BOTH;
		gbc_tftAbono.gridx = 0;
		gbc_tftAbono.gridy = 1;
		panelAbonar.add(tftAbono, gbc_tftAbono);
		tftAbono.setColumns(10);
		
		lblMensajes = new JLabel("Aqu\u00ED puede abonar.");
		lblMensajes.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
		gbc_lblMensajes.gridx = 0;
		gbc_lblMensajes.gridy = 2;
		panelAbonar.add(lblMensajes, gbc_lblMensajes);
		
		btnEliminar = new JButton("Eliminar cuenta");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// eliminamos la cuenta
				Cuenta cuenta = getControlador().getUnaCuenta(getListaCuentas().getSelectedIndex());
				getControlador().eliminarCuenta(cuenta);
				
				// actualizamos la información
				getListaCuentas().setModel(getControlador().getModeloListaCuentas());
				
				resetCampos();
				
			}
		});
		btnEliminar.setBounds(426, 44, 156, 23);
		getContentPane().add(btnEliminar);
		
	}
	
	public void resetCampos() {
		
		getLblNuevaDeuda().setForeground(Color.LIGHT_GRAY);
		getLblNDEUDA().setForeground(Color.LIGHT_GRAY);
		getLblMonto().setForeground(Color.LIGHT_GRAY);
		getLblMONTO().setForeground(Color.LIGHT_GRAY);
		lblCantidad.setForeground(Color.LIGHT_GRAY);
		lblMensajes.setForeground(Color.LIGHT_GRAY);
		
		
		tftAbono.setEnabled(false);
		tftAbono.setText(null);
		
		getLblNombre().setText("-");
		getLblDeuda().setText("-");
		getLblMonto().setText("-");
		getLblNuevaDeuda().setText("-");
		
		
		btnEliminar.setEnabled(false);
		getBtnTerminar().setEnabled(false);
	}
}
