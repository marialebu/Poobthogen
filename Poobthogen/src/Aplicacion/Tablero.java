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
	
	/**
	 * 
	 * @param filas
	 * @param columnas
	 * @param neutrales
	 */
	public Tablero(int filas, int columnas, int neutrales){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Elemento[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
		
	}
	
	/**
	 * 
	 * @param filas
	 * @param columnas
	 */
	public Tablero(int filas, int columnas){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Elemento[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
	}
	/**
	 * 
	 * @param jugador
	 * @param i
	 * @param j
	 * @param elemento
	 * @throws PoobthogenExcepcion
	 */
	public void agregarElemento(int jugador, int i, int j, String elemento, boolean seExpande) throws PoobthogenExcepcion{
		try{
			Class ex = Class.forName(elemento);
			
			elementos[i][j] = (Elemento)ex.getConstructor(Jugador.class).newInstance(jugador == -1 ? null : jugadores.get(jugador));
			
		}catch (InstantiationException | ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA); 
		}catch (IllegalAccessException | IllegalArgumentException e){
			//Algo	
		}catch (InvocationTargetException e){
			//Algo
		}catch (NoSuchMethodException e){
			//Algo
		}catch (SecurityException e){
			//Algo
		}	
	}
	/**
	 * 
	 */
	public void finish(){
		System.exit(0);
	}
	/**
	 * 
	 * @param neutrales
	 */
	public void GenerarFichasNeutrales(int neutrales){
		Random r = new Random();
		int cantidad = neutrales;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				int pos = r.nextInt(1);
				if(pos == 0 && cantidad != 0){
					// Se inserta un virus neutral
					cantidad--;
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
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
	/**
	 * 
	 */
	public void cambiarTurno(){
		turno = !turno;
		// Other fucking things.
	}

	public void imprimir() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print((elementos[i][j] != null ? elementos[i][j].toString() : "-")+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void agregaJugador(Jugador j) throws PoobthogenExcepcion{
		if(jugadores.size() > 2) throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO) ;
		jugadores.add(j);
	}

	public boolean tieneJugador(char charAt) {
		return jugadores.size() >= (int) charAt -49;
	}
	
	public Elemento[][] getElementos(){
		return elementos;
		
	}
}
