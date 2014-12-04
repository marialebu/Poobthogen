package Aplicacion;

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
	
	private void inicializaIntentados(){
		for (int i = 0; i < intentados.length; i++) {
			intentados[i] = false;
		}
	}
}
