package modelo;

import java.io.*;
import java.util.*;

public class Ranking {
    private static final int MAXIMOS_JUGADORES = 5;
    private final String archivo;
    private List<RankingEntry> entradas;

    public Ranking(String dificultad) {
        this.archivo = "src/ranking_" + dificultad.toLowerCase() + ".txt";
        this.entradas = new ArrayList<>();
        cargarEntradas();
    }

    public void agregarEntrada(String nombre, int puntuacion) {
        entradas.add(new RankingEntry(nombre, puntuacion));
        Collections.sort(entradas, new Comparator<RankingEntry>() {
            @Override
            public int compare(RankingEntry e1, RankingEntry e2) {
                return Integer.compare(e2.getPuntuacion(), e1.getPuntuacion());
            }
        });
        if (entradas.size() > MAXIMOS_JUGADORES) {
            entradas = entradas.subList(0, MAXIMOS_JUGADORES);
        }
        guardarEntradas();
    }

    public List<RankingEntry> getEntradas() {
        return new ArrayList<>(entradas);
    }

    private void cargarEntradas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    try {
                        String nombre = partes[0];
                        int puntuacion = Integer.parseInt(partes[1]);
                        entradas.add(new RankingEntry(nombre, puntuacion));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear puntuación en línea: " + linea);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Archivo no existe, se creará al guardar
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(entradas, new Comparator<RankingEntry>() {
            @Override
            public int compare(RankingEntry e1, RankingEntry e2) {
                return Integer.compare(e2.getPuntuacion(), e1.getPuntuacion());
            }
        });
        if (entradas.size() > MAXIMOS_JUGADORES) {
            entradas = entradas.subList(0, MAXIMOS_JUGADORES);
        }
    }

    private void guardarEntradas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (RankingEntry entry : entradas) {
                writer.write(entry.getNombre() + ":" + entry.getPuntuacion());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}