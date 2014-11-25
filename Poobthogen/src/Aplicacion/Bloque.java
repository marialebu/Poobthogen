package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Bloque extends Virus implements Serializable{
	
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona){
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

	public void evolucionar(boolean evoluciona, Jugador j) throws PoobthogenExcepcion {
		throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
	}

	public boolean sePuedeDestruir() throws PoobthogenExcepcion{
		return true;
	}
	
	public void destruir(Jugador j) throws PoobthogenExcepcion{
		throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
	}
	
	public boolean sePuedeEvolucionar() {
		return false;
	}
	
	public String esDeTipo(){
		return "Bloque "+(jugador != null ? jugador.toString() : 0);
	}
	
}
