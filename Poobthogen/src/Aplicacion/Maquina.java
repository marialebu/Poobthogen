package Aplicacion;

import java.util.Random;

public abstract class Maquina extends Jugador{
	protected int[][] posiciones;
	protected boolean[]intentados;
	/**
	 * Constructor de la clase maquina
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Maquina(char id, Tablero t) throws PoobthogenExcepcion {
		super(id, t);
		posiciones = new int[tablero.filas()*tablero.columnas()][2];
		intentados = new boolean[tablero.filas()*tablero.columnas()];
		inicializaPosiciones();
		inicializaIntentados();
	}
	
	/**
	 * Inicializa el vector con todas las posibles coordenadas del tablero. 
	 */
	private void inicializaPosiciones(){
		int k = 0;
		for (int i = 0; i < tablero.filas(); i++) {
			for (int j = 0; j < tablero.columnas(); j++) {
				int[] tem = {i, j};
				posiciones[k] = tem;
				k++;
			}
		}
	}
	
	/**
	 * inicializa la matriz de intentados. 
	 */
	private void inicializaIntentados(){
		for (int i = 0; i < intentados.length; i++) {
			intentados[i] = false;
		}
	}
	
	/**
	 * Realiza una jugada random de un virus 1, 2, 3 o destructor.
	 * @param x Posicion en x
	 * @param y Posicion en y
	 * @param virus Elemento que se quiere agregar
	 * @return Si el juego termina o no
	 * @throws PoobthogenExcepcion
	 */
	protected boolean jugarMaquinaRandom(int x, int y, String virus) throws PoobthogenExcepcion{
		boolean termina = false;
		String tipoVirus[] = {"NivelUno","NivelDos","NivelTres","Destructor"};
		Random r = new Random();
		int pos = r.nextInt(posiciones.length-1);
		while(intentados[pos]){
			pos = r.nextInt(posiciones.length-1);
		}
		intentados[pos] = true;
		int[] posicion = posicion = posiciones[pos];
		try{
			termina = tablero.agregarElemento(Integer.parseInt(identificador+""), posicion[0], posicion[1], tipoVirus[r.nextInt(tipoVirus.length)], true);
		}catch(PoobthogenExcepcion e){
			termina = true;
			if(!tablero.verificar()){
				termina = juega(x,y,virus);
			}
		}
		return termina;
	}
}
