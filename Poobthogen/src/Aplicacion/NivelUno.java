package Aplicacion; 
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class NivelUno extends Virus implements Serializable{ 
	
	public NivelUno(Jugador j, int x ,int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		nextLevel = "NivelDos";
		nivel = 1;
		destruido = false; 
		establecerVecinos();
		if(tablero.getElemento(x, y) != null){
			if(compareTo(nivel, tablero.getElemento(x, y)) <= 0  && !tablero.getElementoTemporal(x, y)&& evoluciona){
				tablero.agregarElemento(Integer.parseInt(j.toString()), x, y, nextLevel, true);
			}
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
	 * 
	 */
	public boolean sePuedeEvolucionar() {
		return true;
	}
	
	


}
