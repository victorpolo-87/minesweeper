package modelo;

public class Casilla_bomba extends Casilla{

	private boolean bandera;

	public Casilla_bomba(int y, int x, boolean visible, boolean bandera) {
		super(y, x, visible);
		this.bandera = bandera;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	
	
}
