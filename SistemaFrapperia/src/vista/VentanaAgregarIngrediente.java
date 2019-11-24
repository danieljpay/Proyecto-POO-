package vista;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class VentanaAgregarIngrediente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Componentes.
	private Controlador controlador;
	private JLabel lblTitulo;
	
	private JPanel panelDatos;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblCosto;
	private JTextField tfCosto;
	private JLabel lblCantidad;
	private JLabel lblMensajes;
	// prueba
	private JLabel lblUnidades;
	private JComboBox<String> cbUnidades;
	private JLabel lblPiezas;
	private JButton btnCancelar;
	private JTextField tfCantidad;
	private JTextField tfPiezas;
	//
	private JButton btnHecho;
	
	private boolean actualizarInfo;
	
	//private boolean hechoActivado = false;
	/**
	 * Create the frame.
	 */
	public VentanaAgregarIngrediente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 283);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Agregar Ingrediente");
		lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTitulo.setBounds(10, 11, 150, 26);
		contentPane.add(lblTitulo);
		
		panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(null, "datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panelDatos.setBounds(10, 48, 429, 144);
		contentPane.add(panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{69, 80, 69, 80, 51, 66, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 33, 0};
		gbl_panelDatos.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// verificamos que el ingrediente no exista.
				String nombre = tfNombre.getText();
				
				if(controlador.ingredienteExistente(nombre)) {
					lblMensajes.setText("Ya existe un ingrediente con ese nombre.");
				} else {
					lblMensajes.setText("Ingrese los datos.");
				}
				
				revisarBtnHecho();
			}
		});
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.gridwidth = 3;
		gbc_tfNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tfNombre.fill = GridBagConstraints.BOTH;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 0;
		panelDatos.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		lblCosto = new JLabel("Costo: ");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 1;
		panelDatos.add(lblCosto, gbc_lblCosto);
		
		tfCosto = new JTextField();
		tfCosto.addKeyListener(new KeyAdapter() {
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
				// verificar que el número sea valido.
				String costo = tfCosto.getText();
				
				if(costoValido(costo) == false) {
					lblMensajes.setText("Ingrese un costo válido.");
				}
				else {
					lblMensajes.setText("Ingrese los datos.");
				}
				
				revisarBtnHecho();
			}
		});
		GridBagConstraints gbc_tfCosto = new GridBagConstraints();
		gbc_tfCosto.gridwidth = 3;
		gbc_tfCosto.insets = new Insets(0, 0, 5, 5);
		gbc_tfCosto.fill = GridBagConstraints.BOTH;
		gbc_tfCosto.gridx = 1;
		gbc_tfCosto.gridy = 1;
		panelDatos.add(tfCosto, gbc_tfCosto);
		tfCosto.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.anchor = GridBagConstraints.EAST;
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.gridx = 0;
		gbc_lblCantidad.gridy = 2;
		panelDatos.add(lblCantidad, gbc_lblCantidad);
		
		tfCantidad = new JTextField();
		tfCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				revisarBtnHecho();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				// bloquea entradas no deseadas
				if(Character.isDigit(letra) == false && letra != (char)8) {
					e.consume();
				}
			}
			
		});
		GridBagConstraints gbc_tfCantidad = new GridBagConstraints();
		gbc_tfCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_tfCantidad.fill = GridBagConstraints.BOTH;
		gbc_tfCantidad.gridx = 1;
		gbc_tfCantidad.gridy = 2;
		panelDatos.add(tfCantidad, gbc_tfCantidad);
		tfCantidad.setColumns(10);
		
		lblUnidades = new JLabel("Unidades:");
		GridBagConstraints gbc_lblUnidades = new GridBagConstraints();
		gbc_lblUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidades.anchor = GridBagConstraints.EAST;
		gbc_lblUnidades.gridx = 2;
		gbc_lblUnidades.gridy = 2;
		panelDatos.add(lblUnidades, gbc_lblUnidades);
		
		cbUnidades = new JComboBox<String>();
		cbUnidades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				String item = (String)cbUnidades.getSelectedItem();
				
				if(item.equals("Caja(s)")) {
					tfPiezas.setEnabled(true);
					lblPiezas.setForeground(Color.BLACK);
				} else {
					tfPiezas.setEnabled(false);
					lblPiezas.setForeground(Color.LIGHT_GRAY);
				}
				
				revisarBtnHecho();
			}
		});
		GridBagConstraints gbc_cbUnidades = new GridBagConstraints();
		gbc_cbUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_cbUnidades.fill = GridBagConstraints.BOTH;
		gbc_cbUnidades.gridx = 3;
		gbc_cbUnidades.gridy = 2;
		panelDatos.add(cbUnidades, gbc_cbUnidades);
		
		lblPiezas = new JLabel("Piezas:");
		lblPiezas.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblPiezas = new GridBagConstraints();
		gbc_lblPiezas.anchor = GridBagConstraints.EAST;
		gbc_lblPiezas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPiezas.gridx = 4;
		gbc_lblPiezas.gridy = 2;
		panelDatos.add(lblPiezas, gbc_lblPiezas);
		
		tfPiezas = new JTextField();
		tfPiezas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				// bloquea entradas no deseadas
				if(Character.isDigit(letra) == false && letra != (char)8) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				revisarBtnHecho();
			}
		});
		tfPiezas.setEnabled(false);
		GridBagConstraints gbc_tfPiezas = new GridBagConstraints();
		gbc_tfPiezas.insets = new Insets(0, 0, 5, 5);
		gbc_tfPiezas.fill = GridBagConstraints.BOTH;
		gbc_tfPiezas.gridx = 5;
		gbc_tfPiezas.gridy = 2;
		panelDatos.add(tfPiezas, gbc_tfPiezas);
		tfPiezas.setColumns(10);
		
		lblMensajes = new JLabel("Ingrese los datos.");
		lblMensajes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
		gbc_lblMensajes.anchor = GridBagConstraints.WEST;
		gbc_lblMensajes.insets = new Insets(0, 0, 0, 5);
		gbc_lblMensajes.gridwidth = 5;
		gbc_lblMensajes.fill = GridBagConstraints.VERTICAL;
		gbc_lblMensajes.gridx = 1;
		gbc_lblMensajes.gridy = 3;
		panelDatos.add(lblMensajes, gbc_lblMensajes);
		
		btnHecho = new JButton("Hecho");
		btnHecho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(btnHecho.isEnabled() == false) {
					lblMensajes.setText("Debe llenar todos los campos correctamente.");
				}
			}
		});
		btnHecho.setEnabled(false);
		btnHecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfPiezas.isEnabled() == false) {
					controlador.agregarIngrediente(tfNombre.getText(), tfCosto.getText(), tfCantidad.getText(), cbUnidades.getSelectedIndex());
				} else {
					controlador.agregarIngrediente(tfNombre.getText(), tfCosto.getText(), tfCantidad.getText(), cbUnidades.getSelectedIndex(), tfPiezas.getText());
				}
				
				if(actualizarInfo) {
					controlador.actualizarListaIngredientes();
					controlador.actualizarTablaIngredientes();
				}
				
				
				dispose();
			}
		});
		btnHecho.setBounds(10, 200, 316, 43);
		contentPane.add(btnHecho);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(336, 200, 103, 43);
		contentPane.add(btnCancelar);
	}
	
	// Métodos
	public boolean costoValido(String costo) {
		char[] arregloCosto = costo.toCharArray();
		
		int contador = 0; // cuenta los puntos decimales.
		for(int i=0; i<arregloCosto.length; i++) {
			if(arregloCosto[i] == '.') {
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
	
	public void revisarBtnHecho() {
		if(camposCompletos()) {
			btnHecho.setEnabled(true);
		} else {
			btnHecho.setEnabled(false);
		}
	}
	public boolean camposCompletos() {
		
		if(tfNombre.getText().isEmpty() == false && tfCosto.getText().isEmpty() == false && tfCantidad.getText().isEmpty() == false) {
			if(controlador.ingredienteExistente(tfNombre.getText()) == false) {
				if(costoValido(tfCosto.getText())) {
					if(tfPiezas.isEnabled()) {
						if(tfPiezas.getText().isEmpty() == false) {
							return true;
						}
					} else {
						return true;
					}
				}				
			}
		}
		
		
		return false;
	}

	// GETTERS
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

	public JLabel getLblCosto() {
		return lblCosto;
	}

	public JTextField getTfCosto() {
		return tfCosto;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JButton getBtnHecho() {
		return btnHecho;
	}
	
	public Controlador getControlador() {
		return controlador;
	}
	
	public JLabel getLblMensajes() {
		return lblMensajes;
	}
	
	// SETTERS
	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public void setPanelDatos(JPanel panelDatos) {
		this.panelDatos = panelDatos;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public void setLblCosto(JLabel lblCosto) {
		this.lblCosto = lblCosto;
	}

	public void setTfCosto(JTextField tfCosto) {
		this.tfCosto = tfCosto;
	}

	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public void setBtnHecho(JButton btnHecho) {
		this.btnHecho = btnHecho;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}

	// actualizar
	public boolean isActualizarInfo() {
		return actualizarInfo;
	}

	public void setActualizarInfo(boolean actualizarInfo) {
		this.actualizarInfo = actualizarInfo;
	}

	
	// ----------------------------
	public JLabel getLblUnidades() {
		return lblUnidades;
	}

	public JComboBox<String> getCbUnidades() {
		return cbUnidades;
	}

	public JLabel getLblPiezas() {
		return lblPiezas;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getTfCantidad() {
		return tfCantidad;
	}

	public JTextField getTfPiezas() {
		return tfPiezas;
	}

	public void setLblUnidades(JLabel lblUnidades) {
		this.lblUnidades = lblUnidades;
	}

	public void setCbUnidades(JComboBox<String> cbUnidades) {
		this.cbUnidades = cbUnidades;
	}

	public void setLblPiezas(JLabel lblPiezas) {
		this.lblPiezas = lblPiezas;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setTfCantidad(JTextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public void setTfPiezas(JTextField tfPiezas) {
		this.tfPiezas = tfPiezas;
	}
	
	
}
