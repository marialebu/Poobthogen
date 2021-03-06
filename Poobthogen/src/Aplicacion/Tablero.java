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
	private boolean[][] TurnoEvolucionados;
	private HashMap<String, Integer> niveles; 
	
	
	
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
		TurnoEvolucionados = new boolean[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				TurnoTemporal[i][j] = false; 
				TurnoEvolucionados[i][j] = false;
			}
		}
		jugadores = new ArrayList<Jugador>(2); 
		if(neutral){
			GenerarFichasNeutrales();
		}
	}
	
	/**Crea un tablero, con x filas y y columnas
	 * @param filas Cantidad de filas del tablero
	 * @param neutrales Cantidad de fichas neutrales
	 */
	public Tablero(int filas, int columnas){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Virus[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
		TurnoTemporal = new boolean[filas][columnas];
		TurnoEvolucionados = new boolean[filas][columnas];
	}
	
	/**Agrega un elemento al tablero
	 * @param jugador Jugador al que pertenece el elemento, puede  ser nulo para las fichas neutrales. 
	 * @param i Posicion en i en el tablero
	 * @param j Posicion en j en el tablero. 
	 * @param elemento Elemento que se va a agregar
	 * @throws PoobthogenExcepcion
	 * @return Verdadero si el juego termina y falso en caso contrario.
	 */
	public boolean agregarElemento(int jugador, int i, int j, String elemento, boolean seExpande) throws PoobthogenExcepcion{
		try{
			Class ex = Class.forName("Aplicacion."+elemento);
			int nivelElemento = getVirusNivel(elemento);
			if(elementos[i][j] == null || 
					(elementos[i][j] != null && nivelElemento>elementos[i][j].getNivel(true)) ||
						(elementos[i][j] != null && nivelElemento==elementos[i][j].getNivel(true) &&
							(jugador == -1 ? null : jugadores.get(jugador-1)) == elementos[i][j].getJugador())){
				TurnoTemporal[i][j] = true;
				Virus v = (Virus)ex.getConstructor(Jugador.class, Integer.TYPE, Integer.TYPE, Tablero.class, Boolean.TYPE).newInstance(jugador == -1 ? null : jugadores.get(jugador-1), i, j, this, seExpande);
				elementos[i][j] = elementos[i][j] == null || 
						(elementos[i][j] != null && nivelElemento>=elementos[i][j].getNivel(true)) ? v : elementos[i][j];
			}else{
				TurnoTemporal[i][j] = false;
				throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
			}
		}catch (InstantiationException  | ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA+" "+e.getMessage());
		}catch (InvocationTargetException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ACCION_NO_PERMITIDA);
		}catch (IllegalAccessException | IllegalArgumentException| NoSuchMethodException| SecurityException e){
			Log.registreError(e);
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_INESPERADO);
		}
		return verificar();
	}
	
	/**Obtiene el nivel del virus que se quiere colocar
	 * @param elemento Elemento del cual se quiere obtener el nivel. 
	 */
	private int getVirusNivel(String elemento) throws PoobthogenExcepcion{
		int a = -1;
		try{
			Tablero t = new Tablero(1, 1);
			Class ex = Class.forName("Aplicacion."+elemento);
			Virus v = (Virus)ex.getConstructor(Jugador.class, Integer.TYPE, Integer.TYPE, Tablero.class, Boolean.TYPE).newInstance(null, 0, 0, t, false);
			a = v.getNivel(false);
		}catch (InstantiationException  | ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA+" "+e.getMessage());
		}catch (InvocationTargetException e){
			a = Integer.MAX_VALUE;
		}catch (IllegalAccessException | IllegalArgumentException| NoSuchMethodException| SecurityException e){
			Log.registreError(e);
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_INESPERADO);
		}
		return a;
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
		int cantidad = r.nextInt((int)((filas*columnas)*0.20));
		String[] tipos = {"NivelUno", "NivelDos", "Bloque"}; 
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
	public boolean verificar(){
		boolean estaLleno = true;
		for (int i = 0; i < filas && estaLleno; i++) {
			for (int j = 0; j < columnas && estaLleno; j++) {
				estaLleno = elementos[i][j] != null && elementos[i][j].getNivel(true)>0;
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
	public void cambiarTurno(){
		turno = !turno;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				TurnoTemporal[i][j] = false; 
				TurnoEvolucionados[i][j] = false;
			}
		}
	}
	
	/**Muestra el tablero por consola. 
	 */
	public String toString() {
		String rta = "";
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				rta+=(elementos[i][j] != null ? elementos[i][j].toString() : "-")+" ";
			}
			rta+="\n";
		}
		return rta;
	}
	
	/**Muestra el tablero por consola. 
	 */
	public String[][] obtener() {
		String[][] res = new String[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				res[i][j] = (elementos[i][j] != null ? elementos[i][j].toString() : "-");
			}
		}
		return res;
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
	
	/**Obtiene si ya hubo una evolucion en una posicion dada y en un turno dado
	 * @param i Fila. 
	 * @param j Columna
	 * @return El elemento que se necesita. 
	 */
	public boolean getEvolucionTemporal(int i, int j) throws PoobthogenExcepcion{ 
		boolean e = false; 
		if(i < elementos.length && j < elementos[0].length && i>=0 && j>=0) {
			e = TurnoEvolucionados[i][j];
		}else{
			throw new PoobthogenExcepcion("");
		}
		return e;
	}
	
	/**
	 * Consulta un jugador 
	 * @param indice Puede ser 1 o 2, dependiendo del jugador. 
	 * @return El jugador 1 o 2 segun lo solicitado.  
	 */
	public Jugador getJugador(int indice){
		return jugadores.get(indice);
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
	
	/**
	 * Establece que en esa casilla se evoluciono un virus en el turno actual. 
	 * @param x Posicion en x
	 * @param y Posicion en y 
	 */
	public void setEvolucionTemporal(int x, int y) {
		TurnoEvolucionados[x][y] = true;
	}	
	
	/**
	 * Consulta quien esta jugando actualmente
	 * @return Retorna verdadero si juega el uno o falso si juega el dos. 
	 */
	public boolean getTurno(){
		return turno;
	}
	
	/**
	 * Determina el ganador del juego con el conteo de fichas de cada uno. 
	 * @return 1 si gana el jugador uno, -1 si gana el jugador 2 y 0 si es un empate. 
	 */
	public int determinaGanador(){
		int jugador1 = 0;
		int jugador2= 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if(elementos[i][j] != null){
					if(elementos[i][j].getNivel(true) > 0){
						if(elementos[i][j].getJugador() != null && elementos[i][j].getJugador().toString().equals(jugadores.get(0).toString())){
							jugador1++;
						}else if (elementos[i][j].getJugador() != null &&elementos[i][j].getJugador().toString().equals(jugadores.get(1).toString())){
							jugador2++;
						}
					}
				}
			}
		}
		return Integer.compare(jugador1, jugador2);
	}
	
	/**
	 * Establece las proporciones de fichas que tiene cada jugador en el tablero.
	 * @return Un arreglo con la proporcion del jugador uno en la primera casilla y la proporcion del jugador dos en la segunda. 
	 */
	public int[] proporciones(){
		float jugador1 = 0;
		float jugador2= 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if(elementos[i][j] != null){
					if(elementos[i][j].getNivel(true) > 0){
						if(elementos[i][j].getJugador() != null && elementos[i][j].getJugador().toString().equals(jugadores.get(0).toString())){
							jugador1++;
						}else if (elementos[i][j].getJugador() != null && elementos[i][j].getJugador().toString().equals(jugadores.get(1).toString())){
							jugador2++;
						}
					}
				}
			}
		}
		int total = filas*columnas;
		int[] proporciones = {(int) ((jugador1/total)*100), (int) ((jugador2/total)*100)};
		return proporciones;
	}
}
