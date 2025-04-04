package controlador;

import vista.VentanaJuego;
import vista.VentanaMenuPrincipal;

public class Main {

	private static VentanaJuego ventanaJuego;
	public static void main(String[] args) {
		
		VentanaMenuPrincipal vm = new VentanaMenuPrincipal();
		vm.setVisible(true);
		VentanaJuego v1 = new VentanaJuego();
		v1.pack();
		v1.setVisible(true);
		
	}
	
	public static void cerrarRanking() {
		
	}
	
}
