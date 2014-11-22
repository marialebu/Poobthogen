package Aplicacion; 

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Tablero  implements Serializable{
	private boolean finalizado; 
	private boolean turno;
	private Virus[][] elementos;
	private ArrayList<Jugador> jugadores; 
	private int filas;
	private int columnas;
	private boolean[][] TurnoTemporal;
	private HashMap<String, Integer> niveles; 
	private int turnos;
	
	
	
	/**Crea un tablero, con x filas y y columnas y una cantidad de fichas neutrales. 
	 * @param filas Cantidad de filas del tablero
	 * @param columnas Cantidad de columnas
	 * @param neutral Agregar o no fichas neutrales. 
	 * @throws PoobthogenExcepcion 
	 */
	public Tablero(int filas, int columnas, boolean neutral) throws PoobthogenExcepcion{
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Virus[filas][columnas];
		TurnoTemporal = new boolean[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				TurnoTemporal[i][j] = false; 
			}
		}
		jugadores = new ArrayList<Jugador>(2); 
		niveles = new HashMap<String, Integer>(); 
		niveles.put("NivelUno", 1);
		niveles.put("NivelDos", 2);
		niveles.put("NivelTres", 3);
		niveles.put("Bloque", Integer.MAX_VALUE);
		niveles.put("Destructor", Integer.MAX_VALUE);
		turnos = -1;
		if(neutral){
			GenerarFichasNeutrales();
		}
	}
	
	
	/**Crea un tablero, con x filas y y columnas
	 * @param filas Cantidad de filas del tablero
	 * @param neutrales Cantidad de fichas neutrales
	 */
	public Tablero(int filas, int columnas, int turnos){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		this.turnos = turnos;
		elementos = new  Virus[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
		TurnoTemporal = new boolean[filas][columnas];
		niveles = new HashMap<String, Integer>();
		niveles.put("NivelUno", 1);
		niveles.put("NivelDos", 2);
		niveles.put("NivelTres", 3);
		niveles.put("Bloque", Integer.MAX_VALUE);
		niveles.put("Destructor", Integer.MAX_VALUE);
	}
	
	/**Agrega un elemento al tablero
	 * 
	 * @param jugador Jugador al que pertenece el elemento, puede  ser nulo para las fichas neutrales. 
	 * @param i Posicion en i en el tablero
	 * @param j Posicion en j en el tablero. 
	 * @param elemento Elemento que se va a agregar
	 * @throws PoobthogenExcepcion
	 */
	public boolean agregarElemento(int jugador, int i, int j, String elemento, boolean seExpande) throws PoobthogenExcepcion{
		try{
			Class ex = Class.forName("Aplicacion."+elemento);
			if(elementos[i][j] == null || (elementos[i][j] != null && niveles.get(elemento)>=elementos[i][j].getNivel())){
				TurnoTemporal[i][j] = true;
				Virus v = (Virus)ex.getConstructor(Jugador.class, Integer.TYPE, Integer.TYPE, Tablero.class, Boolean.TYPE).newInstance(jugador == -1 ? null : jugadores.get(jugador-1), i, j, this, seExpande);
				elementos[i][j] = elementos[i][j] == null || (elementos[i][j] != null && niveles.get(elemento)>=elementos[i][j].getNivel()) ? v : elementos[i][j];
			}else{
				TurnoTemporal[i][j] = false;
			}
		}catch (InstantiationException  | ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA+" "+e.getMessage()); 
		}catch (Exception e){
			e.printStackTrace();
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_INESPERADO+" "+e.getMessage());
		}
		return verificar();
	}
	/**Termina el juego. 
	 */
	public void finish(){
		System.exit(0);
	}
	/**Genera una cantidad de fichas neutrales en el tablero. 
	 * @param neutrales cantidad de fichas neutrales
	 * @throws PoobthogenExcepcion 
	 */
	public void GenerarFichasNeutrales() throws PoobthogenExcepcion{
		Random r = new Random();
		int cantidad = r.nextInt((int)((filas*columnas)*0.05));
		String[] tipos = {"Bloque", "NivelUno", "NivelDos"}; 
		for(int i = 0; i <= cantidad; i++){
			int posx = r.nextInt(filas-1);
			int posy = r.nextInt(columnas-1);
			while(elementos[posx][posy] != null){
				posx = r.nextInt(filas-1);
				posy = r.nextInt(columnas-1);
			}
			String tipo = tipos[r.nextInt(tipos.length)];
			agregarElemento(-1, posx, posy, tipo, false);
			
		}
	}
	/**Verifica si el tablero se ha llenado
	 * @return Verdadero si esta lleno, y falso en caso contrario.
	 */
	private boolean verificar(){
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
	 * Permite a un jugador rendirse, su oponente gana sin importar nada. 
	 * @return Verdadero si el jugador 2 gana y falso si el jugador 1 gana. 
	 */
	public boolean rendirse(){
		return !turno; 
	}
	/**
	 * Cambia de turno de cada jugador. 
	 * @throws PoobthogenExcepcion 
	 */
	public void cambiarTurno() throws PoobthogenExcepcion{
		turno = !turno;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				TurnoTemporal[i][j] = false; 
			}
		}
		turnos--;
		if(turnos==0)throw new PoobthogenExcepcion(PoobthogenExcepcion.JUEGO_TERMINADO);
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
	
	/**Muestra el tablero por consola. 
	 */
	public void imprimirTemporal() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print((TurnoTemporal[i][j] ? elementos[i][j].toString() : "-")+" ");
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
	
	/**Obtiene el elemento ubicado en una posicion espec�fica. 
	 * @param i Fila. 
	 * @param j Columna
	 * @return El elemento que se necesita. 
	 */
	public Virus getElemento(int i, int j){ 
		Virus e = null; 
		if(i < elementos.length && j < elementos[0].length && i>=0 && j>=0) {
			e = elementos[i][j];
		}
		return e;
	}
	
	/**Obtiene el elemento ubicado en una posicion espec�fica en un turno especifico. 
	 * @param i Fila. 
	 * @param j Columna
	 * @return El elemento que se necesita. 
	 */
	public boolean getElementoTemporal(int i, int j) throws PoobthogenExcepcion{ 
		boolean e = false; 
		if(i < elementos.length && j < elementos[0].length && i>=0 && j>=0) {
			e = TurnoTemporal[i][j];
		}else{
			throw new PoobthogenExcepcion("");
		}
		return e;
	}
	
	/**
	 * Consulta la cantidad de filas del tablero. 
	 * @return Cantidad de filas. 
	 */
	public int filas(){
		return filas;
	}
	/**
	 * Consulta la cantidad de columnas del tablero.
	 * @return Cantidad de columnas
	 */
	public int columnas(){
		return columnas;
	}	
	
}
