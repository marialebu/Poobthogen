package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public abstract class Virus implements Serializable{
	protected Jugador jugador;
	protected int nivel;
	protected Tablero tablero;
	protected String nextLevel;
	protected int x;
	protected int y;
	
	protected Virus[] vecinos;
	
	public Virus(Jugador j, int x ,int y, Tablero t, boolean evoluciona) {
		jugador = j;
		tablero = t;
		this.x = x;
		this.y = y;
		vecinos = new Virus[4];
		if(x-1 >= 0 && tablero.getElemento(x-1, y) != null){
			vecinos[0] = tablero.getElemento(x-1, y); 
		}else if (x+1 < tablero.filas() && tablero.getElemento(x+1, y) != null){
			vecinos[1] = tablero.getElemento(x+1, y);
		}else if (y-1>=0 &&  tablero.getElemento(x, y-1) != null){
			vecinos[2] = tablero.getElemento(x+1, y);
		}else if (y+1>=tablero.columnas() &&  tablero.getElemento(x, y+1) != null){
			vecinos[3] = tablero.getElemento(x, y-1);
		}
	}
	
	/**
	 * Compara el nivel de dos elementos 
	 * @param v Elemento con el que se quiere comparar
	 * @return 0 si son del mismo nivel, 1 si el primer elemento es mayor y -1 en caso contrario. 
	 */
	public int compareTo(int nivel, Virus v2) {
		int res = 1;
		if(v2 != null){
			if(nivel < v2.getNivel()){
				res = -1;
			}else if(nivel == v2.getNivel()){
				res = -0;
			}
		}
		return res;
	}
	
	/**
	 * Consulta el nivel de un elemento. 
	 * @return Retorna el nivel de un elemento
	 */
	public int getNivel(){
		return nivel;
	}
	/**
	 * Evoluciona al virus a su siguiente nivel. 
	 * @throws PoobthogenExcepcion
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void evolucionar() throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		tablero.evolucionar(nextLevel, x, y, jugador);	
	}
	public abstract String toString();
	
	

	
	
}
