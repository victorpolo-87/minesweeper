package modelo;

public class Dificultad {

	private DificultadEnum dificultadEnum;
	private int tamanioFilas;
	private int tamanioColumnas;

	public Dificultad(DificultadEnum dificultadEnum, int tamanioFilas, int tamanioColumnas) {
		this.dificultadEnum = dificultadEnum;
		this.tamanioFilas = tamanioFilas;
		this.tamanioColumnas = tamanioColumnas;
	}

}
