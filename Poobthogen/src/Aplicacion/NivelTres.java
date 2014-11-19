package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class NivelTres extends Virus implements Serializable{
	private boolean maxEvolucion;

	public NivelTres(Jugador j, int x, int y, Tablero t, boolean evoluciona) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
		super(j, x, y, t, evoluciona);
		maxEvolucion = true;
		nextLevel = "Bloque";
		nivel = 3;
		for(int i= 0; i < vecinos.length; i++){
			if(vecinos[i]!= null){
				if(compareTo(nivel, vecinos[i]) != 0){
					vecinos[i] = null;
				}
			}	
		}
		//agregarNivelDos();
		if(tablero.getElemento(x, y) != null && evoluciona){
			System.out.println("aaaaaaaaaa");
			evolucionar(evoluciona);
			for(Virus v : vecinos){
				if(v != null && v.sePuedeEvolucionar()){
					v.evolucionar(evoluciona);
				}
			}
		}else if(!jugador.toString().equals(this.jugador.toString())){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA); 
			
		}
	}
	
	public void evolucionar(boolean evoluciona) throws NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
		System.out.println(evoluciona+" "+compareTo(nivel, tablero.getElemento(x, y))+" "+tablero.getElementoTemporal(x, y));
		if(evoluciona && compareTo(nivel, tablero.getElemento(x, y)) == 0){
			System.out.println("khdlkewjhflkwehf");
			maxEvolucion = false;
			tablero.agregarElemento(Integer.parseInt(jugador.toString()), x, y, nextLevel, true);
		}
	}
	
	public void agregarNivelDos() throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
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
	
	private void trateDeAgregarNivelDos(int i, int j) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, PoobthogenExcepcion{
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
