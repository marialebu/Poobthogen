package Aplicacion; 

import java.io.Serializable;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j){
		nivel = 2;
		jugador = j; 
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "D"+jugador.toString();
	}
}
