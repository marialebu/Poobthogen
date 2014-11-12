package Presentacion; 

import java.io.File;
import Aplicacion.*; 



public class Nnnnn {

	public static void main(String[] args) throws PoobthogenExcepcion {
		Tablero t = PoobthogenArchivos.importar(new File("archivo.txt"));
		PoobthogenArchivos.exportar(new File("exporta.txt"), t);

	}

}
