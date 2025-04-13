package modelo;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities; // esta parte soluciona un error del timer en memoria
import vista.VentanaJuego;

public class Juego {
	private Tablero tablero;
	private EstadoEnum estadoJuego;
	private Dificultad dificultad;
	private int tiempo;
	private Timer timer;
	private VentanaJuego ventana;
	private int bombaExplotadaX = -1;
    private int bombaExplotadaY = -1; 

	public Juego() {
		this.estadoJuego = EstadoEnum.NO_INICIADO;
		this.tiempo = 0;
		this.timer = new Timer();
	}

	public void setVentana(VentanaJuego ventana) {
		this.ventana = ventana;
	}

	public void iniciarJuego(Dificultad dificultad) {
		this.dificultad = dificultad;
		this.tablero = new Tablero(dificultad.getTamanioFilas(), dificultad.getTamanioColumnas(),
				dificultad.getCantidadMinas());
		tablero.inicializarTablero();
		tablero.colocarBombas();
		tablero.calcularNumeros();
		estadoJuego = EstadoEnum.EN_CURSO;
		bombaExplotadaX = -1;
        bombaExplotadaY = -1;
		iniciarTemporizador();
	}

	private void iniciarTemporizador() {  // Si no se entiende esta parte escribanme y se los explico 
		tiempo = 0;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (estadoJuego == EstadoEnum.EN_CURSO) {
					tiempo++;
					actualizarVentanaJuego();
				}
			}
		}, 0, 1000);
	}

	public void procesarClic(int x, int y, boolean esClicDerecho) {
		if (estadoJuego != EstadoEnum.EN_CURSO) {
			return;
		}

		if (esClicDerecho) {
			tablero.marcarCasilla(x, y);
			actualizarVentanaJuego();
		} else {
			boolean resultado = tablero.revelarCasilla(x, y);
			if (!resultado) {
				estadoJuego = EstadoEnum.PERDIDO;
				bombaExplotadaX = x;
                bombaExplotadaY = y; 
				detenerTemporizador();
				actualizarVentanaJuego();
			} else {
				if (verificarVictoria()) {
					estadoJuego = EstadoEnum.GANADO;
					detenerTemporizador();
					actualizarVentanaJuego();
				} else {
					actualizarVentanaJuego();
				}
			}
		}
	}

	private void detenerTemporizador() {
		timer.cancel();
		timer = new Timer();
	}

	private boolean verificarVictoria() {
		int totalFilas = tablero.getFilas();
		int totalColumnas = tablero.getColumnas();
		int casillasSinBomba = (totalFilas * totalColumnas) - dificultad.getCantidadMinas();
		int casillasReveladas = 0;

		for (int i = 0; i < totalFilas; i++) {
			for (int j = 0; j < totalColumnas; j++) {
				Casilla casilla = tablero.getCasilla(i, j);
				if (casilla.revelada && !(casilla instanceof CasillaBomba)) {
					casillasReveladas++;
				}
			}
		}
		return casillasReveladas == casillasSinBomba;
	}

	private void actualizarVentanaJuego() { // permite actualizar la ventana evitando el error del timer :)
	    if (ventana != null) {
	        SwingUtilities.invokeLater(new Runnable() { // Si no se entiende esta parte escribanme y se los explico 
	            @Override
	            public void run() {
	                ventana.actualizarInterfaz();
	            }
	        });
	    }
	}

	public void reiniciarJuego() {
		detenerTemporizador();
		iniciarJuego(dificultad);
		actualizarVentanaJuego();
	}

	public int getNumBombasRestantes() {
		int marcadas = 0;
		for (int i = 0; i < tablero.getFilas(); i++) {
			for (int j = 0; j < tablero.getColumnas(); j++) {
				if (tablero.getCasilla(i, j).marcada) {
					marcadas++;
				}
			}
		}
		return dificultad.getCantidadMinas() - marcadas;
	}

	public int getTiempo() {
		return tiempo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public EstadoEnum getEstadoJuego() {
		return estadoJuego;
	}

	public int getBombaExplotadaX() {
		return bombaExplotadaX;
	}

	public void setBombaExplotadaX(int bombaExplotadaX) {
		this.bombaExplotadaX = bombaExplotadaX;
	}

	public int getBombaExplotadaY() {
		return bombaExplotadaY;
	}

	public void setBombaExplotadaY(int bombaExplotadaY) {
		this.bombaExplotadaY = bombaExplotadaY;
	}
	
	
}