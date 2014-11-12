package Aplicacion; 

import java.io.Serializable;


public class NivelUno extends Virus implements Serializable{

	public NivelUno(Jugador j){
		jugador = j; 
	}
	
	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		System.out.println("Soy un ni�o malo");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "U"+jugador.toString();
	}

}
