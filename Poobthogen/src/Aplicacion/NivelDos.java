package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super(j, x, y, t, evoluciona); 
		nextLevel = "NivelTres";
		nivel = 2;
		destruido = false; 
		if(tablero.getElemento(x, y) != null){
			if(evoluciona && compareTo(nivel, tablero.getElemento(x, y)) == 0 && !tablero.getElementoTemporal(x, y)){
				tablero.agregarElemento(Integer.parseInt(j.toString()), x, y, nextLevel, true);
			}
		}
		agregarNivelUno();
		for(int i= 0; i < vecinos.length; i++){
			if(vecinos[i]!= null){
				if(compareTo(nivel, vecinos[i]) != 0){
					vecinos[i] = null;
				}
			}	
		}
	}
	
	public void agregarNivelUno() throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		try{
			if(x-1 >= 0){
				trateDeAgregarNivelUno(x-1, y);
			}
			if (x+1 < tablero.filas()){
				trateDeAgregarNivelUno(x+1, y);
			}
			if (y-1>=0){
				trateDeAgregarNivelUno(x, y-1);
			}
			if (y+1<tablero.columnas()){
				trateDeAgregarNivelUno(x, y+1);
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion("Error de formato.");
		}
	}

	private void trateDeAgregarNivelUno(int i, int j) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion {
		if(compareTo(1, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelDos", true);
		}else if(compareTo(1, tablero.getElemento(i, j)) >0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelUno", true);
		}
		
	}

	public String toString() {
		String res = "D";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	
	public void evolucionar(boolean evoluciona, Jugador j) {}

	public boolean sePuedeEvolucionar() {
		return true;
	}
	
}
