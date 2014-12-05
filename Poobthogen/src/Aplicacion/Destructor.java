package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Destructor extends Virus implements Serializable{

	public Destructor(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		Virus v2= tablero.getElemento(x, y);
		if(v2== null) throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
		if(v2.getJugador() == null) throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
		nivel = v2.getNivel(true);
		destruido = true;
		if(evoluciona){
			destruirVecinos(j);
		}
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
	
	/**
	 * Evoluciona al virus. 
	 * @param evoluciona Saber si el virus se expande o no. 
	 * @param j Jugador que evoluciona al virus. 
	 * @throws PoobthogenExcepcion
	 */
	public void evolucionar(boolean evoluciona, Jugador j){
		
	}
	
	/**
	 * Consulta si un virus se puede evolucionar
	 * @return Verdadero si no ha evolucionado y falso en caso contrario. 
	 */
	public boolean sePuedeEvolucionar() {
		return false;
	}
	
	/**
	 * Consulta de que tipo es el virus. 
	 * @return
	 */
	public String esDeTipo(){
		return null;
	}
	
	/**
	 * Obtiene el nivel de un virus
	 * @return el nivel correspondiente
	 */
	public int getNivel(boolean b){
		int nivelReal = Integer.MAX_VALUE;
		if(b){
			nivelReal = nivel;
		}
		return nivelReal;
	}
}
