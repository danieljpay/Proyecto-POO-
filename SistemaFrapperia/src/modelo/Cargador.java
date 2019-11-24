package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controlador.Controlador;

/*
 * Esta clase se encarga de cargar y guardar los datos del programa
 * al abrir y cerrarlo, respectivamente.
 */
public class Cargador {
	
	private static Controlador controlador;
	
	public static void cargarDatos() {
		try {
			
			FileInputStream fis = new FileInputStream("datos.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			controlador.setInventario((Inventario)ois.readObject());
			controlador.getInventario().setControlador(controlador);
			
			controlador.setFrappes((Frappes)ois.readObject());
			controlador.getFrappes().setControlador(controlador);
			
			controlador.setVentas((Ventas)ois.readObject());
			controlador.getVentas().setControlador(controlador);
			
			controlador.setCuentas((Cuentas)ois.readObject());
			controlador.getCuentas().setControlador(controlador);
			
			ois.close();
			fis.close();
			
			System.out.println("Se ha cargado la información");
		}catch(FileNotFoundException e1){
			System.out.println("No se pudo cargar la información");
		}catch(IOException | ClassNotFoundException e2) {
			System.out.println("No se pudo cargar la información");
		}
		
	}
	
	public static void guardarDatos() {
		
		try {
			
			FileOutputStream fos = new FileOutputStream("datos.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(controlador.getInventario());
			oos.writeObject(controlador.getFrappes());
			oos.writeObject(controlador.getVentas());
			oos.writeObject(controlador.getCuentas());
			
			oos.close();
			fos.close();
			
			System.out.println("Se guardó la información.");
			
		}catch(IOException e) {
			System.out.println("Imposible guardar la información.");
		}
	}

	
	
	public static Controlador getControlador() {
		return controlador;
	}

	public static void setControlador(Controlador controlador) {
		Cargador.controlador = controlador;
	}
	
	
}
