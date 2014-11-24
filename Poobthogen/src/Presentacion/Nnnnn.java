package Presentacion; 

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Aplicacion.*; 
public class Nnnnn {
	public static void main(String[] args) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		/*Tablero t = new Tablero(10, 10, false);
		//Tablero t = PoobthogenArchivos.importar(new File("poobthogen.txt"));
		t.imprimir();
		/*File f = new File("poobthogen1.txt");
		PoobthogenArchivos.exportar(f, t);
		System.out.println();
		t.agregaJugador(new Jugador('1'));
		t.agregaJugador(new Jugador('2'));
		t.agregarElemento(1, 5, 5, "NivelUno", true);
		t.cambiarTurno();
		t.imprimir();
		System.out.println("siguiente turno.");
		t.agregarElemento(1, 5, 5, "NivelUno", true);
		t.cambiarTurno();
		t.imprimir();
		//System.out.println();
		t.agregarElemento(1, 4, 5, "NivelUno", true);
		t.cambiarTurno();
		t.imprimir();
		t.agregarElemento(2, 5, 5, "NivelTres", true);
		t.imprimir();
		t.cambiarTurno();
		//t.agregarElemento(1, 5, 5, "NivelTres", true);
		t.imprimir();
		t.cambiarTurno();
		t.agregarElemento(1, 4,4, "Destructor", true);
		t.imprimir();*/
		Tablero t = new Tablero(5, 5, false);
    	t.agregaJugador(new Jugador('1'));
		t.agregaJugador(new Jugador('2'));
		t.agregarElemento(1, 2, 2, "NivelUno", true);
		t.cambiarTurno();
		t.agregarElemento(1, 2, 1, "NivelUno", true);
		t.cambiarTurno();
		t.agregarElemento(1, 2, 3, "NivelUno", true);
		t.cambiarTurno();
		t.agregarElemento(1, 2, 2, "NivelUno", true);
		t.cambiarTurno();
		t.imprimir();
	}
}
