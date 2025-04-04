package controlador;

import vista.VentanaJuego;
import vista.VentanaMenuPrincipal;
import vista.VentanaRanking;

public class Main {
	
	public static void main(String[] args) {
		
		VentanaMenuPrincipal vm = new VentanaMenuPrincipal();
		VentanaRanking ventanaRanking = new VentanaRanking();
		ventanaRanking.setVisible(true);
		vm.setVisible(true);
		VentanaJuego v1 = new VentanaJuego();
		v1.pack();
		v1.setVisible(true);
		
	}
	
}
