package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Bloque extends Virus implements Serializable{
	
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		jugador = j;
		nivel = Integer.MAX_VALUE; 
		destruido = true;
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

	public void evolucionar(boolean evoluciona, Jugador j) {}

	public boolean sePuedeEvolucionar(){
		return false;
	}
		
}
