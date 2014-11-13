package Aplicacion; 

import java.io.Serializable;


public class NivelTres extends Virus implements Serializable{
	
	public NivelTres(Jugador j){
		nivel = 3; 
		jugador = j; 
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "T"+jugador.toString();
	}

}
