package Aplicacion; 

import java.io.Serializable;


public class Destructor extends Virus implements Serializable{

	public Destructor(Jugador j, int x, int y, Tablero t, boolean evoluciona) {
		super(j, x, y, t, evoluciona);
	}

	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "G"+jugador.toString();
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

}
