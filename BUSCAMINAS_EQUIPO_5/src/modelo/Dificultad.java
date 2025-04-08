package modelo;

public class Dificultad {

    private DificultadEnum dificultadEnum;
    private int tamanioFilas;
    private int tamanioColumnas;
    private int cantidadMinas;

    public Dificultad(DificultadEnum dificultadEnum) {
        this.dificultadEnum = dificultadEnum;
        this.tamanioFilas = dificultadEnum.getFilas();
        this.tamanioColumnas = dificultadEnum.getColumnas();
        this.cantidadMinas = dificultadEnum.getMinas();
    }

    public void setDificultadEnum(DificultadEnum dificultadEnum) {
        this.dificultadEnum = dificultadEnum;
        this.tamanioFilas = dificultadEnum.getFilas();
        this.tamanioColumnas = dificultadEnum.getColumnas();
        this.cantidadMinas = dificultadEnum.getMinas();
    }

    public DificultadEnum getDificultadEnum() {
        return dificultadEnum;
    }
    
    public int getTamanioFilas() {
        return tamanioFilas;
    }

    public int getTamanioColumnas() {
        return tamanioColumnas;
    }

    public int getCantidadMinas() {
        return cantidadMinas;
    }

    @Override
    public String toString() {
        return dificultadEnum.toString();
    }
}

