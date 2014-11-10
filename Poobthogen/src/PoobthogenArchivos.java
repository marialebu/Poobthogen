import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PoobthogenArchivos {
	public void guardar(File f, Tablero t){
	}
	public Tablero abrir(File f){
		return null;
	}
	public Tablero importar(File f) throws PoobthogenExcepcion{
		HashMap<String, String> tiposVirus = new HashMap<String, String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(f)); 
			String linea;
			while((linea = br.readLine()).startsWith("--"));
			if(linea.equals("VIRUS")){
				while(!(linea = br.readLine()).startsWith("--") && !(linea.equals("TABLERO"))){
					String[] virus = linea.split(" "); 	
					tiposVirus.put(virus[0], virus[1]);
				}
				int columnas = -1;
				int filas = 0;
				ArrayList<String> elemento = new ArrayList<String>();
				while(!(linea = br.readLine()).startsWith("--") && br.ready()){
					 if(columnas == -1){
						 columnas = linea.length();
					 }
					 filas++;	 
					 elemento.add(linea);
				}
				
			}
		}catch(FileNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ARCHIVO_NO_ENCONTRADO); 
		}catch (IOException e){
			System.out.println("Un error en la entrada se ha generado");
		}
		
		return null;
	}
	
	public void exportar(File f, Tablero t){
		
	}
}
