package modelo;

import java.util.*;

public class GestorRanking {
    private Ranking rankingFacil;
    private Ranking rankingNormal;
    private Ranking rankingDificil;

    public GestorRanking() {
        rankingFacil = new Ranking("FACIL");
        rankingNormal = new Ranking("NORMAL");
        rankingDificil = new Ranking("DIFICIL");
    }

    public void actualizarRanking(String dificultad, String nombre, int puntuacion) {
        String dificultadEstandarizada = dificultad.trim().toUpperCase(); 
        System.out.println("Dificultad recibida en GestorRanking: " + dificultadEstandarizada);
        switch (dificultadEstandarizada) {
            case "FACIL":
                rankingFacil.agregarEntrada(nombre, puntuacion);
                break;
            case "NORMAL":
                rankingNormal.agregarEntrada(nombre, puntuacion);
                break;
            case "DIFICIL":
                rankingDificil.agregarEntrada(nombre, puntuacion);
                break;
            default:
                System.err.println("Error: Dificultad no reconocida: " + dificultadEstandarizada);
                throw new IllegalArgumentException("Dificultad no válida: " + dificultad + ". Se esperaba FACIL, NORMAL o DIFICIL.");
        }
    }

    public List<RankingEntry> obtenerRanking(String dificultad) {
        String dificultadEstandarizada = dificultad.trim().toUpperCase(); 
        System.out.println("Obteniendo ranking para dificultad: " + dificultadEstandarizada); 
        switch (dificultadEstandarizada) {
            case "FACIL":
                return rankingFacil.getEntradas();
            case "NORMAL":
                return rankingNormal.getEntradas();
            case "DIFICIL":
                return rankingDificil.getEntradas();
            default:
                System.err.println("Error: Dificultad no reconocida: " + dificultadEstandarizada);
                return new ArrayList<>();
        }
    }
}