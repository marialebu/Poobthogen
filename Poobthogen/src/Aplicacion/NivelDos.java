package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super(j, x, y, t, evoluciona); 
		nextLevel = "NivelTres";
		nivel = 2;
		if(tablero.getElemento(x, y) != null){
			if(evoluciona && compareTo(nivel, tablero.getElemento(x, y)) == 0){
				evolucionar();
			}else if (evoluciona && compareTo(nivel, tablero.getElemento(x, y)) < 0 ){
				tablero.getElemento(x, y).evolucionar();
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
			if(x-1 >= 0  && compareTo(1, tablero.getElemento(x-1, y)) >=0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x-1, y, "NivelUno", true);
			}
			if (x+1 < tablero.filas() && compareTo(1, tablero.getElemento(x+1, y)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x+1, y, "NivelUno", true);
			}
			if (y-1>=0 && compareTo(1, tablero.getElemento(x, y-1)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x, y-1, "NivelUno", true);
			}
			if (y+1<tablero.columnas() && compareTo(1, tablero.getElemento(x, y+1)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x, y+1, "NivelUno", true);
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion("Error de formato.");
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
	
}
