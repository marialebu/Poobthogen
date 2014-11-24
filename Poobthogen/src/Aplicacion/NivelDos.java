package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws PoobthogenExcepcion{
		super(j, x, y, t, evoluciona); 
		nextLevel = "NivelTres";
		nivel = 2;
		destruido = false;
		if(tablero.getElemento(x, y)!=null && evoluciona){
			evolucionar(evoluciona,j);
		}
		if(Virus.compareTo(nivel, tablero.getElemento(x, y))>=0){
			agregarNivelUno();
		}else{
			//Evolucionar
			for (int i = 0; i < dx.length; i++) {
				int temp_dx = x+dx[i];
				int temp_dy = y+dy[i];
				if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas() && !tablero.getElementoTemporal(temp_dx, temp_dy)){
					Virus vecino = tablero.getElemento(temp_dx, temp_dy);
					if(vecino != null && vecino.sePuedeEvolucionar()){
						vecino.evolucionar(evoluciona, j);
					}
				}
			}
		}
		
		
	}
	
	public void agregarNivelUno() throws PoobthogenExcepcion{
		try{
			if(x-1 >= 0){
				trateDeAgregarNivelUno(x-1, y);
			}
			if (x+1 < tablero.filas()){
				trateDeAgregarNivelUno(x+1, y);
			}
			if (y-1>=0){
				trateDeAgregarNivelUno(x, y-1);
			}
			if (y+1<tablero.columnas()){
				trateDeAgregarNivelUno(x, y+1);
			}
		}catch(NumberFormatException e){
			throw new PoobthogenExcepcion("Error de formato.");
		}
	}

	private void trateDeAgregarNivelUno(int i, int j) throws PoobthogenExcepcion{
		if(compareTo(1, tablero.getElemento(i, j)) ==0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelDos", true);
		}else if(compareTo(1, tablero.getElemento(i, j)) >0 && !tablero.getElementoTemporal(i, j)){
			tablero.agregarElemento(jugador == null ? -1 : Integer.parseInt(jugador.toString()), i, j, "NivelUno", true);
		}
		
	}

	public String toString() {
		String res = "D";
		if(jugador != null){
			res+=jugador.toString(); 
		}else{
			res+="_";
		}
		return res;
	}
	
	public boolean sePuedeEvolucionar() {
		return true;
	}
	
}
