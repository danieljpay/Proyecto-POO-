package unidades;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

/*
 * Guarda las unidades de medida disponibles para los ingredientes.
 */
public class Unidades {

	private static ArrayList<UnidadMedida> listaUnidades;
	
	public Unidades() {
		
		listaUnidades = new ArrayList<UnidadMedida>();
		
		// añadimos las unidades.
		listaUnidades.add(new Gramo());
		//listaUnidades.add(new Kilogramo());
		//listaUnidades.add(new Litro());
		listaUnidades.add(new Mililitro());
		listaUnidades.add(new Caja());
		
	}
	
	public static DefaultComboBoxModel<String> getBoxModelUnidades(){
		 DefaultComboBoxModel<String> modelo = new  DefaultComboBoxModel<String>();
		 
		 for(int i=0; i<listaUnidades.size(); i++) {
			 modelo.addElement(listaUnidades.get(i).getSimbolo());
		 }
		 
		 return modelo;
	}
	
	// devuelve una unidad, dado su index.
	public static UnidadMedida getUnidadMedida(int index) {
		
		return listaUnidades.get(index);
		
	}
	
}
