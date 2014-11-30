package Presentacion; 

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.Timer;

import Aplicacion.*; 
public class Nnnnn {
	public static void main(String[] args) throws PoobthogenExcepcion, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	Tablero t = new Tablero(5, 5, false);
    	t.agregaJugador(new Jugador('1', t));
		t.agregaJugador(new Jugador('2', t));
		t.agregarElemento(1, 2, 2, "NivelTres", true);
		t.cambiarTurno();
		t.imprimir();
		t.agregarElemento(1, 2, 1, "NivelTres", true);
		t.cambiarTurno();
		t.imprimir();
		t.agregarElemento(1, 2, 3, "NivelTres", true);
		t.cambiarTurno();
		t.imprimir();
		t.agregarElemento(1, 2, 1, "NivelTres", true);
		t.cambiarTurno();
		t.imprimir();
		/*t.agregarElemento(1, 2, 1, "Destructor", true);
		t.imprimir();*/
	}
}
