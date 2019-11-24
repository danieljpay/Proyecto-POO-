package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNuevaCuenta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Controlador controlador;
	
	private JPanel panelDatos;
	private JTextField tfNombre;
	private JLabel lblNombre;
	private JButton btnHecho;
	private JLabel lblMensajes;

	/**
	 * Create the frame.
	 */
	public VentanaNuevaCuenta() {
		
		setResizable(false);
		setTitle("nueva cuenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 201);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelDatos = new JPanel();
		panelDatos.setForeground(Color.LIGHT_GRAY);
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panelDatos.setBounds(10, 11, 353, 78);
		contentPane.add(panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		lblNombre = new JLabel("  Nombre:");
		lblNombre.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = tfNombre.getText();
				
				if(controlador.nombreRepetido(nombre)) {
					lblMensajes.setText("El nombre ingresado ya pertecene a una cuenta.");
					
					btnHecho.setEnabled(false);
				}
				else {
					if(nombre.startsWith(" ")) {
						lblMensajes.setText("Los nombre no puede empezar con espacios.");
						btnHecho.setEnabled(false);
					}
					else {
						if(nombre.isEmpty()) {
							lblMensajes.setText("El saldo inicial de la cuenta es $0.00 por defecto.");
							btnHecho.setEnabled(false);
						}
						else {
							lblMensajes.setText("El saldo inicial de la cuenta es $0.00 por defecto.");
							btnHecho.setEnabled(true);
						}
					}
				}
			}
		});
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.fill = GridBagConstraints.BOTH;
		gbc_tfNombre.gridx = 0;
		gbc_tfNombre.gridy = 1;
		panelDatos.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		btnHecho = new JButton("Hecho");
		btnHecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.agregarCuenta(tfNombre.getText());
				dispose();
			}
		});
		btnHecho.setEnabled(false);
		btnHecho.setBounds(10, 125, 353, 31);
		contentPane.add(btnHecho);
		
		lblMensajes = new JLabel("El saldo inicial de la cuenta es $0.00 por defecto.");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setBounds(10, 100, 353, 14);
		contentPane.add(lblMensajes);
	}

	// getters ----------
	public Controlador getControlador() {
		return controlador;
	}

	public JPanel getPanelDatos() {
		return panelDatos;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JButton getBtnHecho() {
		return btnHecho;
	}

	public JLabel getLblMensajes() {
		return lblMensajes;
	}

	// setters ----------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setPanelDatos(JPanel panelDatos) {
		this.panelDatos = panelDatos;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public void setBtnHecho(JButton btnHecho) {
		this.btnHecho = btnHecho;
	}

	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}
	
	
	

}
