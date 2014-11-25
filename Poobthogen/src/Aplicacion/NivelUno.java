package Aplicacion; 
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class NivelUno extends Virus implements Serializable{ 
	
	public NivelUno(Jugador j, int x ,int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		nextLevel = "NivelDos";
		nivel = 1;
		destruido = false; 
		if(tablero.getElemento(x, y)!=null && evoluciona){
			evolucionar(evoluciona,j);
		}
		if(compareTo(nivel,tablero.getElemento(x, y))==0){
			evolucionarVecinos(evoluciona,j);
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
	
	public String esDeTipo(){
		return "NivelUno "+(jugador != null ? jugador.toString() : 0);
	}
	
	


}
