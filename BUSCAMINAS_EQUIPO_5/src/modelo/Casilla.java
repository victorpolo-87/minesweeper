package modelo;

public class Casilla {

	private int y;
	private int x;
	private boolean visible;

	public Casilla(int y, int x, boolean visible) {
		this.y = y;
		this.x = x;
		this.visible = visible;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void recuperarPosicion(int y, int x) {
		
	}
	
}
