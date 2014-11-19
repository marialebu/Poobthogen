package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Destructor extends Virus implements Serializable{

	public Destructor(Jugador j, int x, int y, Tablero t, boolean evoluciona) {
		super(j, x, y, t, evoluciona);
	}

	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		String res = "G";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	public void evolucionar(boolean evoluciona){}

	public boolean sePuedeEvolucionar() {
		return false;
	}
}
