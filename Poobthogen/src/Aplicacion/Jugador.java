package Aplicacion; 

import java.io.Serializable;


public class Jugador implements Serializable{
	private char identificador; 
	private Tablero tablero;
	/**Constructor de la clase jugador
	 * @param id Identificador del jugador. 
	 * a. El identificador debe ser "1" o "2"<br>
	 * @throws PoobthogenExcepcion 
	 */
	public Jugador(char id, Tablero t) throws PoobthogenExcepcion{
		identificador = id;
		this.tablero = t; 
	}
	
	/**Consulta la informacion asociada a un jugador
	 * 
	 * @return Una cadena con el identificador del jugador
	 */
	public String toString(){
		return identificador+""; 
	}
	
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		return tablero.agregarElemento(Integer.parseInt(identificador+""), x, y, virus, true);
	}
	
}
