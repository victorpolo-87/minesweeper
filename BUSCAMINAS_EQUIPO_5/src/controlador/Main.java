package controlador;

import modelo.Dificultad;
import vista.VentanaJuego;
import vista.VentanaMenuPrincipal;

public class Main {
    private static int x; // Columnas
    private static int y; // Filas
    private static VentanaMenuPrincipal vm;

    public static void main(String[] args) {
        vm = new VentanaMenuPrincipal();
        vm.setVisible(true);
    }

    public static void getFilasColumnas(Dificultad dificultad) {
        if (dificultad != null) {
            x = dificultad.getTamanioColumnas();
            y = dificultad.getTamanioFilas();
        } else {
            throw new IllegalArgumentException("La dificultad no puede ser nula");
        }
    }

    public static void cambiarVentanaAJuego() {
        VentanaJuego v1 = new VentanaJuego(x, y);
        vm.setVisible(false); 
        v1.setVisible(true);
        v1.pack();
    }

}