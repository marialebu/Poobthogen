package Aplicacion; 

import java.io.Serializable;


public abstract class Virus implements Serializable{
	protected Jugador jugador;
	protected int nivel;
	protected Tablero tablero;
	
	protected Virus[] vecinos;
	
	public Virus(Jugador j, int x ,int y, Tablero t) {
		jugador = j;
		tablero = t;
	}
	
	/**
	 * Compara el nivel de dos elementos 
	 * @param v Elemento con el que se quiere comparar
	 * @return 0 si son del mismo nivel, 1 si el primer elemento es mayor y -1 en caso contrario. 
	 */
	public int compareTo(int nivel, Virus v2) {
		int res = 0;
		if(nivel > v2.getNivel()){
			res = 1;
		}else if(nivel < v2.getNivel()){
			res = -1;
		}
		return res;
	}
	
	/**
	 * Consulta el nivel de un elemento. 
	 * @return Retorna el nivel de un elemento
	 */
	public int getNivel() {
		return nivel;
	}
	
	public abstract void evolucionar();
	public abstract String toString();
	
	

	
	
}
