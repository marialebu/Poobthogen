import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PoobthogenArchivos {
	
	public static BufferedReader br;
	
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
	public static Tablero importar(File f) throws PoobthogenExcepcion{
		HashMap<String, String> tiposVirus = new HashMap<String, String>();
		Tablero tablero = null;
		try{
			br = new BufferedReader(new FileReader(f)); 
			String linea;
			while((linea = br.readLine()).startsWith("--"));
			if(linea.equals("VIRUS")){
				while(!(linea = br.readLine()).startsWith("--") && !(linea.equals("TABLERO"))){
					String[] virus = linea.split(" "); 	
					if(tiposVirus.containsKey(virus[0])){
						throw new PoobthogenExcepcion(PoobthogenExcepcion.ELEMENTO_DUPLICADO);
					}
					tiposVirus.put(virus[0], virus[1]);
				}
				ArrayList<String> elemento = new ArrayList<String>();
				int[] filCol = cuenta(elemento);
				tablero = new Tablero(filCol[1], filCol[0]);
				for (int i = 0 ; i < elemento.size(); i++) {
					buscarYAgregar(elemento.get(i), tiposVirus, tablero, i);
				}
				br.close();
			}
		}catch(FileNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ARCHIVO_NO_ENCONTRADO); 
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}
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
	
	private static int[] cuenta (ArrayList<String> elemento) throws PoobthogenExcepcion {
		try{
			int contar[] = new int[2]; 
			contar[0] = -1;
			contar[1] = 0;
			String linea;
			while(!(linea = br.readLine()).startsWith("--") && br.ready()){
				 if(contar[0] == -1){
					 contar[0] = (linea.length()-2)/2; // Sin contar las columnas con *
					 System.out.println();
				 }
				 if(!linea.isEmpty()){
					 contar[1]++;	 
					 elemento.add(linea);
				 }
			}
			contar[1]-=2; // Sin contar las esquinas de *
			return contar;
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}
	}
	
	private static void buscarYAgregar(String t, HashMap<String, String> tiposVirus, Tablero tablero, int i) throws PoobthogenExcepcion{
		int j = 0; 
		while(j < t.length()) {
			if(t.charAt(j) != '*' && t.charAt(j) != ' ' ){
				j++;
				if(j < t.length()){
					if(t.charAt(j) != '*' && tiposVirus.containsKey(t.charAt(j-1)+"")){
						if(t.charAt(j) == '1' || t.charAt(j) == '2'){
							tablero.agregarElemento((int)t.charAt(j)- 49, i-1, (j-1)/2, tiposVirus.get(t.charAt(j-1)+""), false);
						}else if (t.charAt(j)== '_'){
							tablero.agregarElemento(-1, i-1, (j-1)/2, tiposVirus.get(t.charAt(j-1)+""), false);
						}else{
							throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO);
						}
					}else if(t.charAt(j-1) != ' '){
						throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA+" "+t.charAt(j-1)+" "+j);
					}
				}else{
					//ERROR
				}
			}
			j++;
		}
	}
	
}
