package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Bloque extends Virus implements Serializable{
	
	/**
	 * Constructor de la clase virus
	 * @param j Jugador al que pertenece
	 * @param x Posicion en x
	 * @param y posicion en y 
	 * @param t Tablero en el que se crea
	 * @param evoluciona Si se expande o no el virus
	 */
	public Bloque(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		jugador = j;
		nivel = Integer.MAX_VALUE; 
		destruido = true;
		agregarNivelTres();
	}
	
	/**
	 * Agrega los virus de tres para evolucionar los bloques. 
	 * @throws PoobthogenExcepcion
	 */
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
	
	/**
	 * Trata de agregar la ficha de nivel tres
	 * @param i Posicion x en el tablero. 
	 * @param j Posicion y en el tablero. 
	 * @throws PoobthogenExcepcion
	 */
	private void trateDeAgregarNivelTres(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(3, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "Bloque", true);
		}
	}
	
	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString(){
		String respuesta = "B";
		if(jugador == null){
			respuesta+="_";
		}else{
			respuesta+=jugador.toString();
		}
		return respuesta;
	}
	
	/**
	 * Evoluciona al virus, pero el bloque no se puede evolucionar. 
	 * @param evoluciona Saber si el virus se expande o no. 
	 * @param j Jugador que evoluciona al virus. 
	 * @throws PoobthogenExcepcion
	 */
	public void evolucionar(boolean evoluciona, Jugador j) throws PoobthogenExcepcion {
		throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
	}
	
	/**
	 * Consulta si un virus puede ser destruido
	 * @return Verdadero si se puede destruir y falso en caso contrario. 
	 * @throws PoobthogenExcepcion
	 */
	public boolean sePuedeDestruir(){
		return false;
	}
	
	/**
	 * Destruye el virus actual. 
	 * @param j Jugador que envia a destruir
	 * @throws PoobthogenExcepcion
	 */
	public void destruir(Jugador j) throws PoobthogenExcepcion{
		throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
	}
	
	/**
	 * Consulta si un virus se puede evolucionar
	 * @return Verdadero si no ha evolucionado y falso en caso contrario. 
	 */
	public boolean sePuedeEvolucionar() {
		return false;
	}
	
	/**
	 * Consulta de que tipo es el virus con su jugador.  
	 * @return Una cadena con el nombre de la clase y el jugador al que pertenece
	 */

	public String esDeTipo(){
		return "Bloque "+(jugador != null ? jugador.toString() : 0);
	}
	
}
