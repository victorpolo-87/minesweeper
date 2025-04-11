package modelo;

import java.util.Timer;
import java.util.TimerTask;

public class Juego {
	private Tablero tablero;
	private EstadoEnum estadoJuego;
	private Dificultad dificultad;
	private int tiempo;
	private Timer timer;

	public Juego() {
		this.estadoJuego = EstadoEnum.NO_INICIADO;
		this.tiempo = 0;
		this.timer = new Timer();
	}

	public void iniciarJuego(Dificultad dificultad) {
		this.dificultad = dificultad;
		this.tablero = new Tablero(dificultad.getTamanioFilas(), dificultad.getTamanioColumnas(),
				dificultad.getCantidadMinas());
		tablero.inicializarTablero();
		tablero.colocarBombas();
		tablero.calcularNumeros();
		estadoJuego = EstadoEnum.EN_CURSO;
		iniciarTemporizador();
	}

	private void iniciarTemporizador() {
		tiempo = 0;
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

	private void actualizarVentanaJuego() {
		System.out.println("Tiempo: " + tiempo + " segundos | Estado: " + estadoJuego);
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
}