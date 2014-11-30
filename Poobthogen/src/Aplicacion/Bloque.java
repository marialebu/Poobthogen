package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Bloque extends Virus implements Serializable{
	
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		jugador = j;
		nivel = Integer.MAX_VALUE; 
		destruido = true;
		agregarNivelTres();
	}
	
	public void agregarNivelTres() throws PoobthogenExcepcion{
		try{
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()){
					trateDeAgregarNivelTres(temp_dx, temp_dy);
				}
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO);
		}
	}
	
	private void trateDeAgregarNivelTres(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(3, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "Bloque", true);
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
