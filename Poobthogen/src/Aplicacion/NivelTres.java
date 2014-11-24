package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelTres extends Virus implements Serializable{
	

	public NivelTres(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		maxEvolucion = true;
		nextLevel = "Bloque";
		nivel = 3;
		destruido = false; 
		if(tablero.getElemento(x, y)!=null && evoluciona){
			evolucionar(evoluciona,j);
		}
		if((Virus.compareTo(nivel, tablero.getElemento(x, y))> 0)){
			agregarNivelDos();
		}else{
			evolucionarVecinos(evoluciona, j);
		}
	}
	
	protected void evolucionarVecinos(boolean evoluciona, Jugador j) throws PoobthogenExcepcion{
		for (int i = 0; i < dx.length; i++) {
			int temp_dx = x+dx[i];
			int temp_dy = y+dy[i];
			if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas() && !tablero.getElementoTemporal(temp_dx, temp_dy)){
				Virus vecino = tablero.getElemento(temp_dx, temp_dy);
				if(vecino != null && vecino.sePuedeEvolucionar() && compareTo(nivel,vecino)==0){
					vecino.evolucionar(evoluciona, j);
				}
			}
		}
	}

	public void agregarNivelDos() throws PoobthogenExcepcion{
		try{
			if(x-1 >= 0){
				trateDeAgregarNivelDos(x-1, y);
			}
			if (x+1 < tablero.filas()){
				trateDeAgregarNivelDos(x+1, y);
			}
			if (y-1>=0){
				trateDeAgregarNivelDos(x, y-1);
			}
			if (y+1<tablero.columnas()){
				trateDeAgregarNivelDos(x, y+1);
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion("Error de formato.");
		}
	}
	
	private void trateDeAgregarNivelDos(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(2, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelTres", true);
		}else if(compareTo(2, tablero.getElemento(i, j)) >0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelDos", true);
		}
	}


	/**Consulta la informacion asociada a un virus
	 * 
	 * @return Una cadena con el tipo de virus y su jugador. 
	 */
	public String toString() {
		String res = "T";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean sePuedeEvolucionar(){
		return maxEvolucion; 
	}
}
