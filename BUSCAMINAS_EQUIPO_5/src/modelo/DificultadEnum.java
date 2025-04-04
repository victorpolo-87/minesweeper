package modelo;

public enum DificultadEnum {
    FACIL(8, 8, 10),
    NORMAL(16, 16, 40),
    DIFICIL(24, 24, 99);

    private final int filas;
    private final int columnas;
    private final int minas;

    DificultadEnum(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getMinas() {
        return minas;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase()
               + " (" + filas + "x" + columnas + ", " + minas + " minas)";
    }
}
