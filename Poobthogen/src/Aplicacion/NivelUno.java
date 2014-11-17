package Aplicacion; 
import java.io.Serializable;

public class NivelUno extends Virus implements Serializable{ 
	
	public NivelUno(Jugador j, int x ,int y, Tablero t){
		super(j, x, y, t);
		nivel = 1;
		if(tablero.getElemento(x, y) != null){
			if(compareTo(nivel, tablero.getElemento(x, y)) > 0){
				evolucionar();
			}
		}
	}
	/**Consulta la informacion asociada a un virus
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		return "U"+jugador.toString();
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}


}
