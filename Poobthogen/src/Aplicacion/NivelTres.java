package Aplicacion; 

import java.io.Serializable;


public class NivelTres extends Virus implements Serializable{

	public NivelTres(Jugador j, int x, int y, Tablero t){
		super(j, x, y, t);
		nivel = 3;
	}

	/**Consulta la informacion asociada a un virus
	 * 
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "T"+jugador.toString();
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}



}
