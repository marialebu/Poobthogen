package Aplicacion; 

import java.io.Serializable;


public class NivelTres extends Virus implements Serializable{
	
	public static final int nivel = 3;  
	
	public NivelTres(Jugador j, int x, int y) throws PoobthogenExcepcion{
		super(j, x, y);
		jugador = j; 
	}

	/**
	 * Evoluciona al virus. 
	 */
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	/**Consulta la informacion asociada a un virus
	 * 
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "T"+jugador.toString();
	}

}
