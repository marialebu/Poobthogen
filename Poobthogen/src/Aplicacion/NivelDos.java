package Aplicacion; 

import java.io.Serializable;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j){
		nivel = 2;
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
