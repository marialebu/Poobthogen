package Presentacion; 

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Aplicacion.*; 
public class Nnnnn {
	public static void main(String[] args) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Tablero t = new Tablero(7, 7, true);
		t.imprimir();
		System.out.println();
		t.agregaJugador(new Jugador('1'));
		t.agregaJugador(new Jugador('2'));
		//t.agregarElemento(1, 5, 5, "NivelUno", true);
		//t.imprimir();
		//t.agregarElemento(2, 5, 5, "NivelDos", true);
		//t.imprimir();
		//System.out.println();
		//t.agregarElemento(1, 4, 5, "NivelDos", true);
		t.agregarElemento(1, 4, 5, "NivelTres", true);
		t.imprimir();
	}
}
