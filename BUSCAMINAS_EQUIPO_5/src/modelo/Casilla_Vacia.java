package modelo;

public class Casilla_Vacia extends Casilla {

	private int cantidadVacias;

	public Casilla_Vacia(int y, int x, boolean visible, int cantidadVacias) {
		super(y, x, visible);
		this.cantidadVacias = cantidadVacias;
		
	}

	public int getCantidadVacias() {
		return cantidadVacias;
	}

	public void setCantidadVacias(int cantidadVacias) {
		this.cantidadVacias = cantidadVacias;
	}
	

}
