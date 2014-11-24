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
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()  && !tablero.getElementoTemporal(temp_dx, temp_dy)){
					Virus vecino = tablero.getElemento(temp_dx, temp_dy);
					if(vecino != null && vecino.sePuedeEvolucionar()){
						vecino.evolucionar(evoluciona, j);
					}
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
	
	/**
	 * 
	 */
	public boolean sePuedeEvolucionar() {
		return true;
	}
	
	


}
