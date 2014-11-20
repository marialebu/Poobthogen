package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Destructor extends Virus implements Serializable{

	public Destructor(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion, NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super(j, x, y, t, evoluciona);
		nivel = Integer.MAX_VALUE; 
		Virus v2= tablero.getElemento(x, y); 
		destruido = false; 
		if(v2== null || !v2.sePuedeDestruir()) throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA); 
		nivel = Integer.MIN_VALUE; 
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
	public void evolucionar(boolean evoluciona, Jugador j){}

	public boolean sePuedeEvolucionar() {
		return false;
	}
}
