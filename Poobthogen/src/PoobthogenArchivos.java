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
		Tablero tablero = null;
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
						 columnas = linea.length()-2; // Sin contar las columnas con *
					 }
					 filas++;	 
					 elemento.add(linea);
				}
				filas-=2; // Sin contar las esquinas de *
				Elemento[][] elementos = new Elemento[filas][columnas];
				tablero = new Tablero(filas, columnas, elementos);
				for (int i = 0 ; i < elemento.size(); i++) {
					String t = elemento.get(i);
					for (int j = 0; j < t.length(); j++) {
						if(t.charAt(j) != '*' ){
							if(j < t.length()-1 && t.charAt(j+1) != '*' && tiposVirus.containsKey(t.charAt(j))){
								if(t.charAt(j+1) == '1' || t.charAt(j+1) == '2'){
									tablero.agregarVirus((int)t.charAt(j+1)-48, i, j, (Virus)Class.forName(tiposVirus.get(t.charAt(j))).newInstance()); // ESto no lo se hacer bien, toca reparar con el del laboratorio
								}else{
									// Acá deberia añadirse un virus neutral, pero no tenemos el "Jugador neutral" ARREGLO
								}
								//Se añade el virus de tipo t.charAt(j) y al jugador t.charAt(j+1)
							}else{
								//El formato esta raro, deberia venir una letra y un virus
							}
						}
					}
				}
				br.close();
			}
		}catch(FileNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ARCHIVO_NO_ENCONTRADO); 
		}catch (IOException e){
			System.out.println("Un error en la entrada se ha generado");
		}catch(ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA); 
		}catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// ARREGLO
			e.printStackTrace();
		}
		
		return tablero;
	}
	
	public void exportar(File f, Tablero t){
		
	}
}
