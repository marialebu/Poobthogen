package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Tablero  implements Serializable{
	private boolean finalizado; 
	private boolean turno;
	private Elemento[][] elementos;
	private ArrayList<Jugador> jugadores; 
	private int filas;
	private int columnas;
	
	/**Crea un tablero, con x filas y y columnas y una cantidad de fichas neutrales. 
	 * 
	 * @param filas Cantidad de filas del tablero
	 * @param columnas Cantidad de columnas
	 * @param neutrales Cantidad de fichas neutrales
	 */
	public Tablero(int filas, int columnas, int neutrales){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Elemento[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 		
	}
	
	/**Crea un tablero, con x filas y y columnas
	 * 
	 * @param filas Cantidad de filas del tablero
	 * @param neutrales Cantidad de fichas neutrales
	 */
	public Tablero(int filas, int columnas){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Elemento[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
	}
	/**Agrega un elemento al tablero
	 * 
	 * @param jugador Jugador al que pertenece el elemento, puede  ser nulo para las fichas neutrales. 
	 * @param i Posicion en i en el tablero
	 * @param j Posicion en j en el tablero. 
	 * @param elemento Elemento que se va a agregar
	 * @throws PoobthogenExcepcion
	 */
	public void agregarElemento(int jugador, int i, int j, String elemento, boolean seExpande) throws PoobthogenExcepcion{
		try{
			Class ex = Class.forName("Aplicacion."+elemento);
			elementos[i][j] = (Elemento)ex.getConstructor(Jugador.class).newInstance(jugador == -1 ? null : jugadores.get(jugador));
		}catch (InstantiationException | ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA+" "+e.getMessage()); 
		}catch (Exception e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_INESPERADO); 
		}
	}
	/**Termina el juego. 
	 */
	public void finish(){
		System.exit(0);
	}
	/**Genera una cantidad de fichas neutrales en el tablero. 
	 * @param neutrales cantidad de fichas neutrales
	 */
	public void GenerarFichasNeutrales(int neutrales){
		
		Random r = new Random();
		int cantidad = neutrales;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				int pos = r.nextInt(1);
				if(pos == 0 && cantidad != 0){
					
					cantidad--;
				}
			}
		}
	}
	
	/**Verifica si el tablero se ha llenado
	 * @return Verdadero si esta lleno, y falso en caso contrario.
	 */
	public boolean verificar(){
		boolean estaLleno = true;
		for (int i = 0; i < filas && estaLleno; i++) {
			for (int j = 0; j < columnas && estaLleno; j++) {
				estaLleno = elementos[i][j] != null;
			}
		}
		finalizado = estaLleno;
		return finalizado;
	}
	/**Cambia de turno de cada jugador. 
	 */
	public void cambiarTurno(){
		turno = !turno;
	}
	/**Muestra el tablero por consola. 
	 */
	public void imprimir() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print((elementos[i][j] != null ? elementos[i][j].toString() : "-")+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**Agrega un jugador si es posible
	 * @param j Jugador que se quiere agregar. 
	 * @throws PoobthogenExcepcion
	 */
	public void agregaJugador(Jugador j) throws PoobthogenExcepcion{
		if(jugadores.size() > 2) throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO) ;
		jugadores.add(j);
	}
	/** Comprueba si ya no se pueden agregar los jugadores
	 * @param charAt Identificador, debe ser 1 o 2. 
	 * @return Verdadero si  se puede agregar, falso en caso contrario. 
	 */
	public boolean tieneJugador(char charAt) {
		return jugadores.size() >= (int) charAt -49;
	}
	/**Consulta los elementos de un tablero. 
	 * @return Un arreglo con los elementos del tablero. 
	 */
	public Elemento[][] getElementos(){
		return elementos;
		
	}
	/**Obtiene el elemento ubicado en una posicion específica. 
	 * @param i Fila. 
	 * @param j Columna
	 * @return El elemento que se necesita. 
	 * @throws PoobthogenExcepcion
	 */
	public Elemento getElemento(int i, int j) throws PoobthogenExcepcion{
		Elemento e = elementos[i][j]; 
		if(i >= elementos.length || j >= elementos[0].length || i<0 || j <0) throw new PoobthogenExcepcion(PoobthogenExcepcion.CASILLA_INVALIDA);
		return e;
	}
	
	public Elemento[] getVecinos(int x, int y) throws PoobthogenExcepcion{
		if(getElemento(x,y) == null)throw new PoobthogenExcepcion(PoobthogenExcepcion.CASILLA_INVALIDA);
		Elemento[] res = new Elemento[4];
		if(x-1 > 0 && getElemento(x-1, y) != null){
			res[0] =  getElemento(x-1, y);
		}else if (y+1<elementos[0].length && getElemento(x, y+1) != null){
			res[1] = getElemento(x, y+1);
		}else if (y-1>0 && getElemento(x, y-1) != null){
			res[2] =  getElemento(x-1, y);
		}else if (x+1<elementos.length && getElemento(x+1, y-1) != null){
			res[3] =  getElemento(x-1, y);
		}
		return null;
	}
	
}
