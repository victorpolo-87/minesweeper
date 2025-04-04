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

	
	public DificultadEnum getDificultadEnum() {
		return dificultadEnum;
	}


	public void setDificultadEnum(DificultadEnum dificultadEnum) {
		this.dificultadEnum = dificultadEnum;
	}

	public int getTamanioFilas() {
		return tamanioFilas;
	}

	
	public void setTamanioFilas(int tamanioFilas) {
		this.tamanioFilas = tamanioFilas;
	}

	public int getTamanioColumnas() {
		return tamanioColumnas;
	}

	
	public void setTamanioColumnas(int tamanioColumnas) {
		this.tamanioColumnas = tamanioColumnas;
	}

}
