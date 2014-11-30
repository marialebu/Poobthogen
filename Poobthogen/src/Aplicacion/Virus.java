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
	protected boolean destruido; 
	protected boolean maxEvolucion;
	
	protected int[] dx = {1,-1,0,0};
	protected int[] dy = {0,0,1,-1};
	
	public Virus(Jugador j, int x ,int y, Tablero t, boolean evoluciona) {
		jugador = j;
		tablero = t;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Compara el nivel de dos elementos 
	 * @param v Elemento con el que se quiere comparar
	 * @return 0 si son del mismo nivel, 1 si el primer elemento es mayor y -1 en caso contrario. 
	 */
	public static int compareTo(int nivel, Virus v2){
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
	 * Evolucionar vecinos
	 */
	protected void evolucionarVecinos(boolean evoluciona, Jugador j) throws PoobthogenExcepcion{
		for (int i = 0; i < dx.length; i++) {
			int temp_dx = x+dx[i];
			int temp_dy = y+dy[i];
			if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()  && !tablero.getElementoTemporal(temp_dx, temp_dy)){
				Virus vecino = tablero.getElemento(temp_dx, temp_dy);
				if(vecino != null && vecino.sePuedeEvolucionar()){
					vecino.evolucionar(evoluciona, j);
				}
			}
		}
	}
	
	protected void destruirVecinos(Jugador j) throws PoobthogenExcepcion{
		for (int i = 0; i < dx.length; i++) {
			int temp_dx = x+dx[i];
			int temp_dy = y+dy[i];
			if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()){
				Virus vecino = tablero.getElemento(temp_dx, temp_dy);
				if(vecino != null && vecino.sePuedeDestruir() && compareTo(nivel,vecino)==0){
					vecino.destruir(j);
				}
			}
		}
	}
	
	/**
	 * Consulta el nivel de un elemento. 
	 * @return Retorna el nivel de un elemento
	 */
	public int getNivel(){
		return nivel;
	}

	public abstract String toString();

	public abstract boolean sePuedeEvolucionar();
	
	public boolean sePuedeDestruir() throws PoobthogenExcepcion {
		return !destruido;
	}

	public void destruir(Jugador j) throws PoobthogenExcepcion{
		destruido = true;
		tablero.agregarElemento(Integer.parseInt(j.toString()), x, y, "Destructor", true);
	}
	 
	public void evolucionar(boolean evoluciona, Jugador j) throws  PoobthogenExcepcion{
		if(evoluciona){
			maxEvolucion = false;
			tablero.setEvolucionTemporal(x,y);
			tablero.agregarElemento(Integer.parseInt(j.toString()), x, y, nextLevel, true);
		}
	}

	public Jugador getJugador() {
		return jugador;
	}
	
	public abstract String esDeTipo();
	
	
}
