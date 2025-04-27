package modelo;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
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
    private int bombasActivadas = 0;
    private String nombreJugador; 

    public Juego() {
        this.estadoJuego = EstadoEnum.NO_INICIADO;
        this.tiempo = 0;
        this.timer = new Timer();
    }

    public void setVentana(VentanaJuego ventana) {
        this.ventana = ventana;
    }

    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre;
    }

    public String getNombreJugador() {
        return nombreJugador;
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
        bombasActivadas = 0;
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
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
                bombasActivadas++; 
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
        Casilla[][] casillas = tablero.getCasillas();
        boolean todasBombasMarcadas = true;
        boolean todasNoBombasReveladas = true;

        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                Casilla casilla = casillas[fila][columna];
                if (casilla instanceof CasillaBomba) {
                    if (!casilla.marcada) {
                        todasBombasMarcadas = false;
                    }
                } else {
                    if (!casilla.revelada) {
                        todasNoBombasReveladas = false;
                    }
                }
            }
        }

        boolean victoria = todasBombasMarcadas && todasNoBombasReveladas;
        System.out.println("Resultado de verificarVictoria(): " + victoria + " (Todas bombas marcadas: " + todasBombasMarcadas + ", Todas no-bombas reveladas: " + todasNoBombasReveladas + ")");
        return victoria;
    }

    private void actualizarVentanaJuego() {
        if (ventana != null) {
            SwingUtilities.invokeLater(new Runnable() {
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

    public boolean getCasillaClicada(int fila, int columna) {
        return estadoJuego == EstadoEnum.PERDIDO && fila == bombaExplotadaX && columna == bombaExplotadaY;
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

    public int getCasillasDescubiertas() {
        int count = 0;
        Casilla[][] casillas = tablero.getCasillas();
        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                Casilla casilla = casillas[fila][columna];
                if (!(casilla instanceof CasillaBomba) && casilla.revelada) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getBombasActivadas() {
        return bombasActivadas;
    }

    public int getCoeficienteDificultad() {
        int D;
        switch (dificultad.getDificultadEnum()) {
            case FACIL:
                D = 1;
                break;
            case NORMAL:
                D = 2;
                break;
            case DIFICIL:
                D = 3;
                break;
            default:
                D = 1;
        }
        return D;
    }

    public int calcularPuntuacion() {
        int C = getCasillasDescubiertas();
        int M = getBombasActivadas();
        int T = getTiempo() == 0 ? 1 : getTiempo();
        int D = getCoeficienteDificultad();
        int basePuntuacion = (C - M) * 10; 
        int penalizacionTiempo = T / 2; 
        int puntuacion = (basePuntuacion - penalizacionTiempo) * D;
        puntuacion = Math.max(0, puntuacion); 
        System.out.println("Calculando puntuación: C=" + C + ", M=" + M + ", T=" + T + ", D=" + D + ", Base=" + basePuntuacion + ", Penalización=" + penalizacionTiempo + ", Puntuación final=" + puntuacion);
        return puntuacion;
    }

    public void actualizarRanking(String nombre, String dificultad) {
        this.nombreJugador = nombre; 
        if (estadoJuego == EstadoEnum.GANADO) {
            int puntuacion = calcularPuntuacion();
            GestorRanking rankingManager = new GestorRanking();
            String dificultadEstandarizada = dificultad.trim().toUpperCase();
            rankingManager.actualizarRanking(dificultadEstandarizada, nombreJugador, puntuacion);
        } 
    }
}