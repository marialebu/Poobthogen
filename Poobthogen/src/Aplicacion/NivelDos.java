package Aplicacion; 

import java.io.Serializable;


public class NivelDos extends Virus implements Serializable{
	
	public static final int nivel = 2;  
	
	public NivelDos(Jugador j, int x, int y) throws PoobthogenExcepcion{
		super(j, x, y);
		jugador = j; 
	}

	/**
	 * Evoluciona al virus. 
	 */
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Evoluciona al virus. 
	 */
	public String toString() {
		return "D"+jugador.toString();
	}
}
