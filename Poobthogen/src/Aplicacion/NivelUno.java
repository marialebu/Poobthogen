package Aplicacion; 
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class NivelUno extends Virus implements Serializable{ 
	
	/**
	 * Constructor de la clase NivelUno
	 * @param j Jugador al que pertenece
	 * @param x Posicion en x
	 * @param y posicion en y 
	 * @param t Tablero en el que se crea
	 * @param evoluciona Si se expande o no el virus
	 */
	public NivelUno(Jugador j, int x ,int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		nextLevel = "NivelDos";
		nivel = 1;
		destruido = false; 
		if(tablero.getElemento(x, y)!=null && evoluciona && compareTo(nivel,tablero.getElemento(x, y))==0){
			evolucionar(evoluciona,j);
		}
	}
	
	
	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		String res = "U";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	

	/**
	 * Consulta si un virus se puede evolucionar
	 * @return Verdadero si no ha evolucionado y falso en caso contrario. 
	 */
	public boolean sePuedeEvolucionar() {
		return true;
	}
	
	/**
	 * Consulta de que tipo es el virus con su jugador.  
	 * @return Una cadena con el nombre de la clase y el jugador al que pertenece
	 */
	public String esDeTipo(){
		return "NivelUno "+(jugador != null ? jugador.toString() : 0);
	}
	
	


}
