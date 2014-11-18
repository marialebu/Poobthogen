package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelTres extends Virus implements Serializable{

	public NivelTres(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		nextLevel = "Bloque";
		nivel = 3;
		if(tablero.getElemento(x, y) != null){
			if(evoluciona && compareTo(nivel, tablero.getElemento(x, y)) == 0){
				evolucionar();
			}else if (evoluciona && compareTo(nivel, tablero.getElemento(x, y)) < 0 ){
				tablero.getElemento(x, y).evolucionar();
			}
		}
		agregarNivelDos();
		for(int i= 0; i < vecinos.length; i++){
			if(vecinos[i]!= null){
				if(compareTo(nivel, vecinos[i]) != 0){
					vecinos[i] = null;
				}
			}	
		}
		
	}
	
	public void agregarNivelDos() throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		try{
			if(x-1 >= 0  && compareTo(2, tablero.getElemento(x-1, y)) >=0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x-1, y, "NivelDos", true);
			}
			if (x+1 < tablero.filas() && compareTo(2, tablero.getElemento(x+1, y)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x+1, y, "NivelDos", true);
			}
			if (y-1>=0 && compareTo(2, tablero.getElemento(x, y-1)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x, y-1, "NivelDos", true);
			}
			if (y+1<tablero.columnas() && compareTo(2, tablero.getElemento(x, y+1)) >= 0){
				tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), x, y+1, "NivelDos", true);
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion("Error de formato.");
		}
	}


	/**Consulta la informacion asociada a un virus
	 * 
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		String res = "T";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}



}
