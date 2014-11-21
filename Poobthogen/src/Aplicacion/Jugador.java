package Aplicacion; 

import java.io.Serializable;


public class Jugador implements Serializable{
	private char identificador; 
	
	/**Constructor de la clase jugador
	 * @param id Identificador del jugador. 
	 * a. El identificador debe ser "1" o "2"<br>
	 * @throws PoobthogenExcepcion 
	 */
	public Jugador(char id) throws PoobthogenExcepcion{
		identificador = id;
	}
	
	/**Consulta la informacion asociada a un jugador
	 * 
	 * @return Una cadena con el identificador del jugador
	 */
	public String toString(){
		return identificador+""; 
	}
	

}
