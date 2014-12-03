package Aplicacion;

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
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		String tipoVirus[] = {"NivelUno","NivelDos","NivelTres","Destructor"};
		Random r = new Random();
		for (int i = 0; i < tablero.filas(); i++) {
			for (int j = 0; j < tablero.columnas(); j++) {
				if(tablero.getElemento(i, j)==null){
					return tablero.agregarElemento(Integer.parseInt(identificador+""), i, j, tipoVirus[r.nextInt(tipoVirus.length)], true);
				}
			}
		}
		return tablero.verificar();
	}

}
