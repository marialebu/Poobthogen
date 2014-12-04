package Aplicacion;

import java.util.ArrayList;
import java.util.Random;

public class Irreflexiva extends Maquina{
	
	/**
	 * Constructor de la clase maquina
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Irreflexiva(char id, Tablero t) throws PoobthogenExcepcion {
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
	//No creo que la irreflexiva tenga que ver si es null en una posicion. 
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		boolean termina = false;
		String tipoVirus[] = {"NivelUno","NivelDos","NivelTres","Destructor"};
		Random r = new Random();
		int pos = r.nextInt(posiciones.length-1);
		int[] posicion = posiciones[pos];
		while(posicion == null){
			pos = r.nextInt(posiciones.length-1);
			posicion = posiciones[pos];
		}
		posiciones[pos] = null;
		try{
			termina = tablero.agregarElemento(Integer.parseInt(identificador+""), posicion[0], posicion[1], tipoVirus[r.nextInt(tipoVirus.length)], true);
		}catch(PoobthogenExcepcion e){
						juega(x,y,virus);
		}
		return termina;
	}

}
