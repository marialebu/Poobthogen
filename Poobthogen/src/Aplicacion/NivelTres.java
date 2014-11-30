package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelTres extends Virus implements Serializable{
	

	public NivelTres(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		maxEvolucion = true;
		nextLevel = "Bloque";
		nivel = 3;
		destruido = false; 
		if(tablero.getElemento(x, y)!=null && evoluciona && compareTo(nivel,tablero.getElemento(x, y))==0){
			evolucionar(evoluciona,j);
		}
		if((Virus.compareTo(nivel, tablero.getElemento(x, y))> 0) && evoluciona){
			agregarNivelDos();
		}
	}

	public void agregarNivelDos() throws PoobthogenExcepcion{
		try{
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()){
					trateDeAgregarNivelDos(temp_dx, temp_dy);
				}
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO);
		}
	}
	
	private void trateDeAgregarNivelDos(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(2, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelTres", true);
		}else if(compareTo(2, tablero.getElemento(i, j)) >0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelDos", true);
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
	
	/**
	 * 
	 * @return
	 */
	public boolean sePuedeEvolucionar(){
		return maxEvolucion; 
	}
	
	public String esDeTipo(){
		return "NivelTres "+(jugador != null ? jugador.toString() : 0);
	}
}
