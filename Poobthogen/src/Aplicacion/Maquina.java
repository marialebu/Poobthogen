package Aplicacion;

public abstract class Maquina extends Jugador{
	
	/**
	 * Constructor de la clase maquina
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Maquina(char id, Tablero t) throws PoobthogenExcepcion {
		super(id, t);
	}

}
