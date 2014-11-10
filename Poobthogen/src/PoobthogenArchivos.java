import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PoobthogenArchivos {
	
	BufferedReader br;
	
	/**
	 * 
	 * @param f
	 * @param t
	 */
	public void guardar(File f, Tablero t){
	}
	public Tablero abrir(File f){
		return null;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 * @throws PoobthogenExcepcion
	 */
	public Tablero importar(File f) throws PoobthogenExcepcion{
		HashMap<String, String> tiposVirus = new HashMap<String, String>();
		Tablero tablero = null;
		try{
			br = new BufferedReader(new FileReader(f)); 
			String linea;
			while((linea = br.readLine()).startsWith("--"));
			if(linea.equals("VIRUS")){
				while(!(linea = br.readLine()).startsWith("--") && !(linea.equals("TABLERO"))){
					String[] virus = linea.split(" "); 	
					tiposVirus.put(virus[0], virus[1]);
				}
				ArrayList<String> elemento = new ArrayList<String>();
				/*int columnas = -1;
				int filas = 0;
				while(!(linea = br.readLine()).startsWith("--") && br.ready()){
					 if(columnas == -1){
						 columnas = linea.length()-2; // Sin contar las columnas con *
					 }
					 filas++;	 
					 elemento.add(linea);
				}
				filas-=2; // Sin contar las esquinas de **/
				int[] filCol = cuenta(elemento);
				tablero = new Tablero(filCol[0], filCol[1]);
				for (int i = 0 ; i < elemento.size(); i++) {
					String t = elemento.get(i);
					for (int j = 0; j < t.length(); j++) {
						if(t.charAt(j) != '*' ){
							if(j < t.length()-1 && t.charAt(j+1) != '*' && tiposVirus.containsKey(t.charAt(j)+"")){
								if(t.charAt(j+1) == '1' || t.charAt(j+1) == '2'){
									tablero.agregarElemento((int)t.charAt(j+1)- 48, i, j, tiposVirus.get(t.charAt(j)+""));
								}else if (t.charAt(j+1)== '_'){
									tablero.agregarElemento(-1, i, j, tiposVirus.get(t.charAt(j)+""));
								}else{
									//throw()
								}
							}else if(!tiposVirus.containsKey(t.charAt(j))){
								
							}
						}
					}
				}
				br.close();
			}
		}catch(FileNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ARCHIVO_NO_ENCONTRADO); 
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}/*catch(ClassNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA); 
		}catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// ARREGLO
			e.printStackTrace();
		}*/
		return tablero;
	}
	
	/**
	 * 
	 * @param f
	 * @param t
	 */
	
	public void exportar(File f, Tablero t){
		
	}
	
	/**
	 * 
	 * @param elemento
	 * @param br
	 * @return
	 * @throws PoobthogenExcepcion
	 */
	
	private int[] cuenta (ArrayList<String> elemento) throws PoobthogenExcepcion {
		try{
			int contar[] = new int[2]; 
			contar[0] = -1;
			contar[1] = 0;
			String linea;
			while(!(linea = br.readLine()).startsWith("--") && br.ready()){
				 if(contar[0] == -1){
					 contar[0] = linea.length()-2; // Sin contar las columnas con *
				 }
				 contar[1]++;	 
				 elemento.add(linea);
			}
			contar[1]-=2; // Sin contar las esquinas de *
			return contar;
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}
	}
	
}
