package Aplicacion; 

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PoobthogenArchivos {
	
	public static BufferedReader br;
	private static HashMap<String, String> tiposVirus;
	
	/**Guarda el estado actual del tablero. 
	 * @param f Archivo donde se va a guardar. 
	 * @param t Tablero del juego
	 */
	public static void guardar(File f, Tablero t) throws PoobthogenExcepcion{
	    if(f == null)throw new PoobthogenExcepcion("Error al guardar: "+PoobthogenExcepcion.ARCHIVO_INVALIDO);
	    if(t == null)throw new PoobthogenExcepcion("Error al guardar: "+PoobthogenExcepcion.TABLERO_INVALIDO);
		if(!f.getName().endsWith(".dat"))throw new PoobthogenExcepcion("Error al guardar: "+PoobthogenExcepcion.FORMATO_INVALIDO+". Se esperaba un archivo .dat");
		try{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(t);
		out.close();
		}catch(IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA);
		}
	}
	
	/** Abre un archivo para leer un tablero guardado. 
	 * @param f Archivo donde se encuentra el tablero. 
	 * @return El tablero que fue guardado en dicho archivo.
	 * @throws PoobthogenExcepcion 
	 */
	public static Tablero abrir(File f)  throws PoobthogenExcepcion{
	    if(f == null)throw new PoobthogenExcepcion("Error al abrir: "+PoobthogenExcepcion.ARCHIVO_INVALIDO);
	    if(!f.getName().endsWith(".dat"))throw new PoobthogenExcepcion("Error al abrir: "+PoobthogenExcepcion.FORMATO_INVALIDO+". Se esperaba un archivo .dat");
	    Tablero tablero;
	    try{
	    	ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));	
	    	tablero = (Tablero)in.readObject();
	    }catch(ClassCastException e){
	    	throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA);
	    }catch(IOException e){
	    	throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA);
	    }catch(ClassNotFoundException e){
	    	throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA);
	    }
		return tablero;
	}
	
	/**Importa un tablero de un archivo dado. 
	 * @param f Archivo que contiene el tablero. 
	 * @return Tablero importado. 
	 * @throws PoobthogenExcepcion
	 */
	public static Tablero importar(File f) throws PoobthogenExcepcion{
		if(f == null)throw new PoobthogenExcepcion("Error al guardar: "+PoobthogenExcepcion.ARCHIVO_INVALIDO);
	    if(!f.getName().endsWith(".dat"))throw new PoobthogenExcepcion("Error al importar: "+PoobthogenExcepcion.FORMATO_INVALIDO+". Se esperaba un archivo .txt");
		Tablero tablero = null;
		try{
			tiposVirus =new HashMap<String, String>();
			br = new BufferedReader(new FileReader(f)); 
			String linea;
			while((linea = br.readLine()).startsWith("--"));
			if(linea.equals("VIRUS")){
				while(!(linea = br.readLine()).startsWith("--") && !(linea.equals("TABLERO"))){
					String[] virus = linea.split(" "); 	
					if(tiposVirus.containsKey(virus[0]))throw new PoobthogenExcepcion(PoobthogenExcepcion.ELEMENTO_DUPLICADO);
					tiposVirus.put(virus[0], virus[1]);
				}
				ArrayList<String> elemento = new ArrayList<String>();
				int[] filCol = cuenta(elemento);
				tablero = new Tablero(filCol[1], filCol[0]);
				tablero.agregaJugador(new Jugador("1"));
				tablero.agregaJugador(new Jugador("2"));
				for (int i = 0 ; i < elemento.size(); i++) {
					buscarYAgregar(elemento.get(i), tablero, i);
				}
				br.close();
			}
		}catch(FileNotFoundException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ARCHIVO_NO_ENCONTRADO); 
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}
		tablero.imprimir();
		return tablero;
	}
	
	/**Exporta un tablero a un archivo dado. 
	 * @param f Archivo al cual se exportara el tablero.  
	 * @param t Tablero que se exportara. 
	 * @throws PoobthogenExcepcion
	 */
	public static void exportar(File f, Tablero t) throws PoobthogenExcepcion{
		if(f == null)throw new PoobthogenExcepcion("Error al exportar: "+PoobthogenExcepcion.ARCHIVO_INVALIDO);
		if(t == null)throw new PoobthogenExcepcion("Error al exportar: "+PoobthogenExcepcion.TABLERO_INVALIDO);
	    if(!f.getName().endsWith(".txt"))throw new PoobthogenExcepcion("Error al exportar: "+PoobthogenExcepcion.FORMATO_ARCHIVO_INVALIDO+". Se esperaba un archivo .txt");
	    try{
		    PrintWriter out = new PrintWriter(new FileOutputStream(f));
		    out.println("VIRUS");
		    for(String r : tiposVirus.keySet()){
		    	out.println(r+" "+tiposVirus.get(r));
		    }
		    out.println("TABLERO");
		    Elemento[][] e = t.getElementos();
		    String res = ""; 
		    String linea = "*";
		    for (int i = 0; i < 1; i++) {
		    	for (int j = 0; j < (e[i].length)+1; j++) {
		    			res+="*";
		    	}
		    }
		    out.println(res+res);
		    for (int i = 0; i < e.length; i++) {
		    	for (int j = 0; j < (e[i].length); j++) {	
		    		linea+=generateLine(e[i][j]);
				}
		    	out.println(linea+"*");
		    	linea = "*";
			}
		    out.println(res+res);
		    out.close();
	    }catch(IOException e){
	    	throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA);
	    }
	}
	/**Crea la cadena que se agregara a la linea del archivo. 
	 * @param e Elemento a agregar, puede ser nulo. 
	 * @return Una cadena con la definicion del elemento. 
	 */
	private static String generateLine(Elemento e){
		String linea = "";
		if(e != null){
			linea+=e.toString();
		}else{
			linea+="  ";
		}
		return linea;
		
	}
	
	/**Cuenta la cantidad de filas y columnas del tablero. 
	 * @param elemento Arreglo con las lineas del tablero. 
	 * @return Un arreglo con las filas[1] y las columnas[0]- 
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
					 contar[0] = (linea.length()-2)/2; 
					 System.out.println();
				 }
				 if(!linea.isEmpty()){
					 contar[1]++;	 
					 elemento.add(linea);
				 }
			}
			contar[1]-=2;
			return contar;
		}catch (IOException e){
			throw new PoobthogenExcepcion(PoobthogenExcepcion.ERROR_ENTRADA); 
		}
	}
	
	/**Busca y agrega los tipos de elementos de una linea al tablero. 
	 * @param t linea que se leyo. 
	 * @param tablero Tablero al cual se agregan los elementos. 
	 * @param i linea asociada. 
	 * @throws PoobthogenExcepcion
	 */
	private static void buscarYAgregar(String t, Tablero tablero, int i) throws PoobthogenExcepcion{
		int j = 0; 
		while(j < t.length()) {
			if(t.charAt(j) != '*' && t.charAt(j) != ' ' ){
				j++;
				if(j < t.length()){
					if(t.charAt(j) != '*' && tiposVirus.containsKey(t.charAt(j-1)+"")){
						if(t.charAt(j) == '1' || t.charAt(j) == '2'){
							tablero.agregarElemento((int)t.charAt(j)- 49, i-1, (j-1)/2, tiposVirus.get(t.charAt(j-1)+""), false);
						}else if (t.charAt(j)== '_') tablero.agregarElemento(-1, i-1, (j-1)/2, tiposVirus.get(t.charAt(j-1)+""), false);
						else throw new PoobthogenExcepcion(PoobthogenExcepcion.JUGADOR_INVALIDO);
					}else if(t.charAt(j-1) != ' ')throw new PoobthogenExcepcion(PoobthogenExcepcion.CLASE_NO_ENCONTRADA);
				}else{
					//ERROR
				}
			}
			j++;
		}
	}
	
}