package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Bloque extends Virus implements Serializable{
	
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		jugador = j;
		nivel = 4;
		if(tablero.getElemento(x, y) != null){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA); 
		} 
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

	public void evolucionar(boolean evoluciona) {}

	@Override
	public boolean sePuedeEvolucionar(){
		return false;
	}
}
