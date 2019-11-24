package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Ingrediente;
import unidades.Caja;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JDPedirCantidad extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	private Controlador controlador;
	
	private JLabel lblCantidad;
	private JTextField tfCantidad;
	
	private JLabel lblUnidad;
	private JComboBox<String> cbUnidad;
	
	private JLabel lblPiezas;
	private JTextField tfPiezas;
	
	private JLabel lblMensajes;
	
	private JButton okButton;
	private JButton cancelButton;

	private Ingrediente ingrediente; 
	/**
	 * Create the dialog.
	 */
	public JDPedirCantidad() {
		
		//ingrediente = new Ingredientes();
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setAlwaysOnTop(true);
		setResizable(false);
		//setPositionRelativeTo(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("cantidad");
		setBounds(100, 100, 344, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 132, 70, 86, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblCantidad = new JLabel("Cantidad:");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(5, 5, 5, 5);
			gbc_lblCantidad.anchor = GridBagConstraints.EAST;
			gbc_lblCantidad.gridx = 0;
			gbc_lblCantidad.gridy = 0;
			contentPanel.add(lblCantidad, gbc_lblCantidad);
		}
		{
			tfCantidad = new JTextField();
			tfCantidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char letra = e.getKeyChar();
					// bloquea entradas no deseadas
					if(Character.isDigit(letra) == false && letra != (char)8) {
						e.consume();
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
					revisarBotonOK();
				}
			});
			GridBagConstraints gbc_tfCantidad = new GridBagConstraints();
			gbc_tfCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_tfCantidad.fill = GridBagConstraints.BOTH;
			gbc_tfCantidad.gridx = 1;
			gbc_tfCantidad.gridy = 0;
			contentPanel.add(tfCantidad, gbc_tfCantidad);
			tfCantidad.setColumns(10);
		}
		{
			lblUnidad = new JLabel("Unidad:");
			GridBagConstraints gbc_lblUnidad = new GridBagConstraints();
			gbc_lblUnidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblUnidad.anchor = GridBagConstraints.EAST;
			gbc_lblUnidad.gridx = 2;
			gbc_lblUnidad.gridy = 0;
			contentPanel.add(lblUnidad, gbc_lblUnidad);
		}
		{
			cbUnidad = new JComboBox<String>();
			cbUnidad.setEnabled(false);
			GridBagConstraints gbc_cbUnidad = new GridBagConstraints();
			gbc_cbUnidad.insets = new Insets(0, 0, 5, 0);
			gbc_cbUnidad.fill = GridBagConstraints.BOTH;
			gbc_cbUnidad.gridx = 3;
			gbc_cbUnidad.gridy = 0;
			contentPanel.add(cbUnidad, gbc_cbUnidad);
		}
		{
			lblPiezas = new JLabel("Piezas:");
			lblPiezas.setForeground(Color.LIGHT_GRAY);
			GridBagConstraints gbc_lblPiezas = new GridBagConstraints();
			gbc_lblPiezas.anchor = GridBagConstraints.EAST;
			gbc_lblPiezas.insets = new Insets(0, 0, 5, 5);
			gbc_lblPiezas.gridx = 2;
			gbc_lblPiezas.gridy = 1;
			contentPanel.add(lblPiezas, gbc_lblPiezas);
		}
		{
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
				public void keyReleased(KeyEvent arg0) {
					revisarBotonOK();
				}
			});
			tfPiezas.setEnabled(false);
			GridBagConstraints gbc_tfPiezas = new GridBagConstraints();
			gbc_tfPiezas.insets = new Insets(0, 0, 5, 0);
			gbc_tfPiezas.fill = GridBagConstraints.BOTH;
			gbc_tfPiezas.gridx = 3;
			gbc_tfPiezas.gridy = 1;
			contentPanel.add(tfPiezas, gbc_tfPiezas);
			tfPiezas.setColumns(10);
		}
		{
			lblMensajes = new JLabel("Ingrese la cantidad. ");
			GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
			gbc_lblMensajes.gridwidth = 4;
			gbc_lblMensajes.insets = new Insets(0, 0, 0, 5);
			gbc_lblMensajes.gridx = 0;
			gbc_lblMensajes.gridy = 2;
			contentPanel.add(lblMensajes, gbc_lblMensajes);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						// metemos los datos al ingrediente
						if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
							Caja caja = (Caja) ingrediente.getCantidad().getUnidad();
							caja.setPiezas(Integer.parseInt(tfPiezas.getText()));
							ingrediente.getCantidad().setUnidad(caja);
						} else {
							ingrediente.getCantidad().setCantidad(Integer.valueOf(tfCantidad.getText()));
						}
						
						// regresamos la información al controlador.
						controlador.devolverCantidad(ingrediente);
						
						dispose();
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(okButton.isEnabled() == false) {
						lblMensajes.setText("Debe llenar todos los campos.");
						}	
					}
				});
				
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void revisarBotonOK() {
		
		if(ingrediente.getCantidad().getUnidad() instanceof Caja) {
			if(tfPiezas.getText().isEmpty() == false) {
				okButton.setEnabled(true);
			}
			else {
				okButton.setEnabled(false);
			}
		} else {
			if(tfCantidad.getText().isEmpty() == false) {
				okButton.setEnabled(true);
			} 
			else {
				okButton.setEnabled(false);
			}
		}
	}

	// getters -------------------------------------
	public Controlador getControlador() {
		return controlador;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JTextField getTfCantidad() {
		return tfCantidad;
	}

	public JLabel getLblUnidad() {
		return lblUnidad;
	}

	public JComboBox<String> getCbUnidad() {
		return cbUnidad;
	}

	public JLabel getLblPiezas() {
		return lblPiezas;
	}

	public JTextField getTfPiezas() {
		return tfPiezas;
	}

	public JLabel getLblMensajes() {
		return lblMensajes;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	// setters ------------------------
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public void setTfCantidad(JTextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public void setLblUnidad(JLabel lblUnidad) {
		this.lblUnidad = lblUnidad;
	}

	public void setCbUnidad(JComboBox<String> cbUnidad) {
		this.cbUnidad = cbUnidad;
	}

	public void setLblPiezas(JLabel lblPiezas) {
		this.lblPiezas = lblPiezas;
	}

	public void setTfPiezas(JTextField tfPiezas) {
		this.tfPiezas = tfPiezas;
	}

	public void setLblMensajes(JLabel lblMensajes) {
		this.lblMensajes = lblMensajes;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	
	

}
