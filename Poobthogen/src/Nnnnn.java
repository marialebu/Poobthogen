import java.io.File;


public class Nnnnn {

	public static void main(String[] args) throws PoobthogenExcepcion {
		Tablero t = PoobthogenArchivos.importar(new File("archivo.txt"));
		PoobthogenArchivos.exportar(new File("exporta.txt"), t);

	}

}
