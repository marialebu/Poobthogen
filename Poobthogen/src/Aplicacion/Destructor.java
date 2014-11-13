package Aplicacion; 

import java.io.Serializable;


public class Destructor extends Virus implements Serializable{
	
	public static final int nivel = -1;  
	
	public Destructor(Jugador j, int x, int y) throws PoobthogenExcepcion {
		super(j, x, y);
	}

	/**
	 * Evoluciona al destructor
	 */
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "G"+jugador.toString();
	}

}
