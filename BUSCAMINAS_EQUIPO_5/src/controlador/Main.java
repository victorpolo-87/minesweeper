package controlador;

import modelo.Dificultad;
import modelo.Juego;
import vista.VentanaJuego;
import vista.VentanaMenuPrincipal;
import vista.VentanaRanking;

public class Main {
	private static VentanaMenuPrincipal ventanaMenuPrincipal;
	private static VentanaJuego ventanaJuego;
	private static Juego juego;
	private static String nombreJugador;
	private static Dificultad dificultad;

	public static void main(String[] args) {
		ventanaMenuPrincipal = new VentanaMenuPrincipal();
		ventanaMenuPrincipal.setVisible(true);
		
	}
	
	
	public static void getFilasColumnas(Dificultad d) {
		int filas = d.getTamanioFilas();
		int columnas = d.getTamanioColumnas();
		juego = new Juego();
		juego.setNombreJugador(nombreJugador);
		juego.iniciarJuego(dificultad);
		String dificultadJuego = dificultad.toString();
		ventanaJuego = new VentanaJuego(filas, columnas, juego, nombreJugador, dificultadJuego); //int x, int y, Juego juego, String nombreJugador, String dificultad
	}

	public static void cambiarVentanaAJuego() {
		ventanaMenuPrincipal.dispose();
		ventanaJuego.setVisible(true);
	}

	public static void setNombreJugador(String nombre) {
		nombreJugador = nombre;
	}

	public static void setDificultad(Dificultad d) {
		dificultad = d;
	}
	
	public static void cambiarVentanaRanking() {
		VentanaRanking ventanaRanking = new VentanaRanking(nombreJugador);
		ventanaRanking.setVisible(true);
	}
}