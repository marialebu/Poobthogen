package Aplicacion; 

import java.io.Serializable;


public class Bloque extends Virus implements Serializable{
	
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona){
		super(j, x, y, t, evoluciona);
		jugador = j;
		nivel = 4;
	}
	
	public String toString(){
		String respuesta = "B";
		if(jugador == null){
			respuesta+="_";
		}else{
			respuesta+=jugador.toString();
		}
		return respuesta;
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}
}
