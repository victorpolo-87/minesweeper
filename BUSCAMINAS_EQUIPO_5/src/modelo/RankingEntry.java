package modelo;

public class RankingEntry {
    private String nombre;
    private int puntuacion;

    public RankingEntry(String nombre, int puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}