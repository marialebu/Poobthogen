package Aplicacion;

import java.util.Random;

public class Timida extends Maquina{
	
	/**
	 * Constructor de la clase timida
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Timida(char id, Tablero t) throws PoobthogenExcepcion {
		super(id, t);
	}
	/**
	 * Realiza una jugada. 
	 * @param x Posicion en x del tablero
	 * @param y Posicion en y del tablero
	 * @param virus Tipo de virus que quiere colocar
	 * @return Si se ha ganado el juego o no
	 * @throws PoobthogenExcepcion
	 */
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		boolean termina = false;
		String tipoVirus[] = {"NivelUno","NivelDos","NivelTres","Destructor"};
		Random r = new Random();
		int pos = r.nextInt(posiciones.length-1);
		while(intentados[pos]){
			pos = r.nextInt(posiciones.length-1);
		}
		intentados[pos] = true;
		int[] posicion = posicion = posiciones[pos];
		if(tablero.getElemento(posicion[0], posicion[1])== null){
			termina = tablero.agregarElemento(Integer.parseInt(identificador+""), posicion[0], posicion[1], "NivelUno", true);
		}else{
			juega(x, y, virus);
		}
		return termina;
	}
}
