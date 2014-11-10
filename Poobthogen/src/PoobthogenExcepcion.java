
public class PoobthogenExcepcion extends Exception{
	
	public static final String CLASE_NO_ENCONTRADA = "El virus no existe";
	public static String ARCHIVO_NO_ENCONTRADO= "El archivo no existe";
	
	public PoobthogenExcepcion(String mensaje){
		super(mensaje); 
	}

}
