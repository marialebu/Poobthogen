
public class PoobthogenExcepcion extends Exception{
	
	public static final String CLASE_NO_ENCONTRADA = "El virus no existe";
	public static final String ARCHIVO_NO_ENCONTRADO= "El archivo no existe";
	public static final String ERROR_ENTRADA= "Se ha generado un error en la entrada.";
	public static final String FORMATO_INVALIDO = "El formato dentro del archivo no es correcto";
	public static final String JUGADOR_INVALIDO = "El juego solo tiene y debe tener dos jugadores";
	public static final String ELEMENTO_DUPLICADO = "El elemento ya existe";
	public static final String ARCHIVO_INVALIDO = "El archivo no puede ser nulo";
	public static final String TABLERO_INVALIDO = "El archivo no puede ser nulo";
	public static final String FORMATO_ARCHIVO_INVALIDO = "El formato del archivo no es valido";
	
	public PoobthogenExcepcion(String mensaje){
		super(mensaje); 
	}

}
