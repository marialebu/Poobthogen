package Aplicacion; 

import java.io.Serializable;


public abstract class Virus extends Elemento implements Serializable{
	
	protected Tablero tablero;
	protected Elemento[] vecinos;
	
	public Virus(Jugador j, int x ,int y) throws PoobthogenExcepcion{

	}
	
	public abstract void evolucionar();
	public abstract String toString();
	
	/**
	 * Agrega una union a un virus aledanio.
	 * @param pos Posicion en el  tablero.
	 * <br>0. Arriba
	 * <br>1. Abajo
	 * <br>2. Derecha
	 * <br>3. Izquierda<br>
	 * @param v Virus del cual va a ser vecino. 
	 */
	public void agregarVecino(int pos, Elemento v){
		vecinos[pos] = v;
	}
	
	public int getNivel(){
		return nivel;
	}
	
	public int compareTo (Virus v){
		int res = 0;
		if (v.getNivel() > this.nivel){
			res = 1; 
		}else if (v.getNivel() < this.nivel){
			res = -1;
		}
		return res;
		
	}
	
	
	
}
