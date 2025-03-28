package modelo;

public class Casilla_numero extends Casilla {

	private int valor;

	public Casilla_numero(int y, int x, boolean visible, int valor) {
		super(y, x, visible);
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
