package modelo;

public class Tablero {
    private Casilla[][] casillas;
    private int filas;
    private int columnas;
    private int numeroBombas;

    public Tablero(int filas, int columnas, int numeroBombas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numeroBombas = numeroBombas;
        this.casillas = new Casilla[filas][columnas];
    }

    public void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new CasillaVacia(i, j);
            }
        }
    }

    public void colocarBombas() {
        int bombasColocadas = 0;
        java.util.Random random = new java.util.Random();
        while (bombasColocadas < numeroBombas) {
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);
            if (!(casillas[x][y] instanceof CasillaBomba)) {
                casillas[x][y] = new CasillaBomba(x, y);
                bombasColocadas++;
            }
        }
    }

    public void calcularNumeros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!(casillas[i][j] instanceof CasillaBomba)) {
                    int bombasAdyacentes = contarBombasAdyacentes(i, j);
                    if (bombasAdyacentes > 0) {
                        casillas[i][j] = new CasillaNumero(i, j, bombasAdyacentes);
                    }
                }
            }
        }
    }

    private int contarBombasAdyacentes(int x, int y) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaX = x + i;
                int nuevaY = y + j;
                if (nuevaX >= 0 && nuevaX < filas && nuevaY >=0 && nuevaY < columnas) {
                    if (casillas[nuevaX][nuevaY] instanceof CasillaBomba) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public boolean revelarCasilla(int x, int y) {
        if (x < 0 || x >= filas || y < 0 || y >= columnas) {
            return true;
        }
        Casilla casilla = casillas[x][y];
        if (casilla.revelada) {
            return true;
        }
        casilla.revelada = true;
        if (casilla instanceof CasillaBomba) {
            return false;
        } else if (casilla instanceof CasillaVacia) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    revelarCasilla(x + i, y + j);
                }
            }
            return true;
        } else if (casilla instanceof CasillaNumero) {
            return true;
        }
        return true;
    }

    public void marcarCasilla(int x, int y) {
        if (x >= 0 && x < filas && y >= 0 && y < columnas) {
            Casilla casilla = casillas[x][y];
            if (!casilla.revelada) {
                casilla.marcada = !casilla.marcada;
            }
        }
    }

    public Casilla getCasilla(int x, int y) {
        if (x >= 0 && x < filas && y >= 0 && y < columnas) {
            return casillas[x][y];
        }
        return null;
    }
}