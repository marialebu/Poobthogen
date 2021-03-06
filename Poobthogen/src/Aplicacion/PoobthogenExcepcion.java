package Aplicacion; 


public class PoobthogenExcepcion extends Exception{
	
	public static final String CLASE_NO_ENCONTRADA = "El virus no existe";
	public static final String ARCHIVO_NO_ENCONTRADO= "El archivo no existe";
	public static final String ARCHIVO_INFO_INVALIDA= "El archivo contiene informaci�n inv�lida";
	public static final String ERROR_ENTRADA= "Se ha generado un error en la entrada.";
	public static final String FORMATO_INVALIDO = "El formato dentro del archivo no es correcto";
	public static final String JUGADOR_INVALIDO = "El juego solo tiene y debe tener dos jugadores";
	public static final String ELEMENTO_DUPLICADO = "El elemento ya existe";
	public static final String ARCHIVO_INVALIDO = "El archivo no puede ser nulo";
	public static final String TABLERO_INVALIDO = "El archivo no puede ser nulo";
	public static final String FORMATO_ARCHIVO_INVALIDO = "El formato del archivo no es valido";
	public static final String DEFINICION_DE_VIRUS_INVALIDA = "La linea no puede estar vacia";
	public static final String CASILLA_INVALIDA= "La casilla ingresada es invalida";
	public static final String EVOLUCION_CANCELADA= "Los virus solo pueden evolucionar a virus de igual o menor nivel";
	public static final String  ERROR_INESPERADO = "Se ha generado un error inesperado.";
	public static final String  ACCION_NO_PERMITIDA = "No se puede realizar esa accion";
	public static final String JUEGO_TERMINADO = "No se puede realizar mas movimientos";
	public static final String ESCOJA_VIRUS = "Primero debe escoger el virus que quiere agregar";
	public static final String SELECCION_INVALIDA = "Debe seleccionar un virus de su propio color";
	public static final String NO_TIPOS_VIRUS = "No hay definicion previa de los virus";
	public static final String NO_PROPORCION = "No se puede establecer una proporcion";
	
	public PoobthogenExcepcion(String mensaje){
		super(mensaje); 
	}

}
