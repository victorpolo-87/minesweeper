package modelo;

public class CasillaNumero extends Casilla {

	private int numero;

	public CasillaNumero(int x, int y, int numero) {
        super(x, y);
        this.numero = numero;
    }

	public int getNumero() {
		return numero;
	}

	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	

}
