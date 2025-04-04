package modelo;

public abstract class Casilla {
	protected int x;
	protected int y;
	public boolean revelada;
	public boolean marcada;

	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
		this.revelada = false;
		this.marcada = false;
	}
}