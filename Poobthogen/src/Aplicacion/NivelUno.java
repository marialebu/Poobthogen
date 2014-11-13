package Aplicacion; 

import java.io.Serializable;


public class NivelUno extends Virus implements Serializable{
	
	public static final int nivel = 1;  
	/**
	 * 
	 * @param j
	 * @param x
	 * @param y
	 * @throws PoobthogenExcepcion 
	 */
	public NivelUno(Jugador j, int x, int y) throws PoobthogenExcepcion{
		super(j, x, y);
	}
	
	/**
	 * Evoluciona al virus. 
	 */
	public void evolucionar(){
	}
	
	/**Consulta la informacion asociada a un virus
	 * 
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "U"+jugador.toString();
	}

}
