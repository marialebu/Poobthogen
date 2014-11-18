package Aplicacion; 
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class NivelUno extends Virus implements Serializable{ 
	
	public NivelUno(Jugador j, int x ,int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		super(j, x, y, t, evoluciona);
		nextLevel = "NivelDos";
		nivel = 1;
		if(tablero.getElemento(x, y) != null){
			if(compareTo(nivel, tablero.getElemento(x, y)) <= 0 && evoluciona){
				evolucionar();
			}
		}
		for(int i= 0; i < vecinos.length; i++){
			if(vecinos[i]!= null){
				if(compareTo(nivel, vecinos[i]) != 0){
					vecinos[i] = null;
				}
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


}
