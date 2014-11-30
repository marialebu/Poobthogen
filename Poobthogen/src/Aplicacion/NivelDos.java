package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelDos extends Virus implements Serializable{
	
	/**
	 * Constructor de la clase NivelDos
	 * @param j Jugador al que pertenece
	 * @param x Posicion en x
	 * @param y posicion en y 
	 * @param t Tablero en el que se crea
	 * @param evoluciona Si se expande o no el virus
	 */
	public NivelDos(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona); 
		nextLevel = "NivelTres";
		nivel = 2;
		destruido = false;
		if(tablero.getElemento(x, y)!=null && evoluciona && compareTo(nivel,tablero.getElemento(x, y))==0){
			evolucionar(evoluciona,j);
		}
		if(Virus.compareTo(nivel, tablero.getElemento(x, y))>=0 && evoluciona){
			agregarNivelUno();
		}
		
		
	}
	
	/**
	 * Agrega los virus de nivel uno que deben crearse al crear una de nivel dos. 
	 * @throws PoobthogenExcepcion
	 */
	public void agregarNivelUno() throws PoobthogenExcepcion{
		try{
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()){
					trateDeAgregarNivelUno(temp_dx, temp_dy);
				}
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO);
		}
	}
	
	/**
	 * Trata de agregar la ficha de nivel uno
	 * @param i Posicion x en el tablero. 
	 * @param j Posicion y en el tablero. 
	 * @throws PoobthogenExcepcion
	 */
	private void trateDeAgregarNivelUno(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(1, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelDos", true);
		}else if(compareTo(1, tablero.getElemento(i, j)) >0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelUno", true);
		}
	}
	
	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		String res = "D";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	
	/**
	 * Consulta si un virus se puede evolucionar
	 * @return Verdadero si no ha evolucionado y falso en caso contrario. 
	 */
	public boolean sePuedeEvolucionar() {
		return true;
	}
	
	/**
	 * Consulta de que tipo es el virus con su jugador.  
	 * @return Una cadena con el nombre de la clase y el jugador al que pertenece
	 */
	public String esDeTipo(){
		return "NivelDos "+(jugador != null ? jugador.toString() : 0);
	}
	
}
