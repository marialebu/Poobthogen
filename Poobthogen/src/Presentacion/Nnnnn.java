package Presentacion; 

import java.io.File;
import Aplicacion.*; 
public class Nnnnn {

	public static void main(String[] args) throws PoobthogenExcepcion {
		//Tablero t = PoobthogenArchivos.importar(new File("poobthogen.txt"));
		//PoobthogenArchivos.exportar(new File("exporta.txt"), t);
		NivelDos v = new NivelDos((new Jugador("1")), 0, 0);
		NivelTres v1 = new NivelTres((new Jugador("1")), 1, 1);
		System.out.println(v.compareTo(v1));
		

	}

}
