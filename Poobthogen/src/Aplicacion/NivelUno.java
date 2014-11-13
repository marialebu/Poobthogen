package Aplicacion; 

import java.io.Serializable;


public class NivelUno extends Virus implements Serializable{

	/**
	 * 
	 * @param j
	 * @param x
	 * @param y
	 * @throws PoobthogenExcepcion 
	 */
	public NivelUno(Jugador j, int x, int y) throws PoobthogenExcepcion{
		nivel = 1; 
		jugador = j;
		Elemento e = tablero.getElemento(x, y);
		if( e != null){
			if(e.compareTo(this) <= 0){
				evolucionar();
			}else throw new PoobthogenExcepcion(PoobthogenExcepcion.EVOLUCION_CANCELADA);
		}
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
