package Aplicacion; 

import java.io.Serializable;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j, int x, int y, Tablero t) {
		super(j, x, y, t); 
		 nivel = 2;
	}

	/**
	 * Evoluciona al virus. 
	 */
	public String toString() {
		return "D"+jugador.toString();
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}
}
