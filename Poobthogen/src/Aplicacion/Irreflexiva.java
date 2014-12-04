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
		inicializaPosiciones();
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
		String tipoVirus[] = {"NivelUno","NivelDos","NivelTres","Destructor"};
		Random r = new Random();
		int[] posicion = posiciones[r.nextInt(posiciones.length-1)];
		for (int i = 0; i < tablero.filas(); i++) {
			for (int j = 0; j < tablero.columnas(); j++) {
					try{
						return tablero.agregarElemento(Integer.parseInt(identificador+""), posicion[0], posicion[1], tipoVirus[r.nextInt(tipoVirus.length)], true);
					}catch(PoobthogenExcepcion e){
						posicion = posiciones[r.nextInt(posiciones.length-1)];
					}
			}
		}
		return tablero.verificar();
	}

}
