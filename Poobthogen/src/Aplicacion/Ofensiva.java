package Aplicacion;

public class Ofensiva extends Maquina{

	/**
	 * Constructor de la clase ofensiva
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Ofensiva(char id, Tablero t) throws PoobthogenExcepcion {
		super(id, t);
	}
	
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		return false;
	}
	
}
