package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Destructor extends Virus implements Serializable{

	public Destructor(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		//nivel = Integer.MAX_VALUE; 
		Virus v2= tablero.getElemento(x, y);
		if(v2== null) throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
		//if(!v2.sePuedeDestruir()) throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
		nivel = v2.getNivel();
		destruido = !evoluciona; 
		if(v2.sePuedeDestruir()){
			destruir(j);
			destruido = true;
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas()){
					Virus vecino = tablero.getElemento(temp_dx, temp_dy);
					//System.out.println(vecino==null?"nulo":vecino.toString()+" "+vecino.getNivel()+" "+nivel);
					if(vecino != null && vecino.sePuedeDestruir() && compareTo(nivel,vecino)==0){
						vecino.destruir(j);
					}
				}
			}
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
	public void evolucionar(boolean evoluciona, Jugador j){}

	public boolean sePuedeEvolucionar() {
		return false;
	}
}
