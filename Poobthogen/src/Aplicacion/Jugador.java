package Aplicacion; 

import java.io.Serializable;


public class Jugador implements Serializable{
	private String identificador; 
	
	/**Constructor de la clase jugador
	 * @param id Identificador del jugador. 
	 */
	public Jugador(String id){
		identificador = id;
	}
	
	/**Consulta la informacion asociada a un jugador
	 * 
	 * @return Una cadena con el identificador del jugador
	 */
	public String toString(){
		return identificador; 
	}
	

}
