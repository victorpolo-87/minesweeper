package controlador;

import modelo.Dificultad;
import modelo.Juego;
import vista.VentanaJuego;
import vista.VentanaMenuPrincipal;

public class Main {
	private static Juego juego;
	private static VentanaMenuPrincipal ventanaMenu;
	private static VentanaJuego ventanaJuego;

	public static void main(String[] args) {
		juego = new Juego();
		ventanaMenu = new VentanaMenuPrincipal();
		ventanaMenu.setVisible(true);
	}

	public static void getFilasColumnas(Dificultad dificultad) {
		juego.iniciarJuego(dificultad); // Inicializa el tablero
	}

	public static void cambiarVentanaAJuego() {
		ventanaMenu.dispose();
		ventanaJuego = new VentanaJuego(juego.getTablero().getFilas(), juego.getTablero().getColumnas(), juego);
		ventanaJuego.setVisible(true);
	}
}