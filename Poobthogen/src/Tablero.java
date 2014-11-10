import java.util.*;

public class Tablero {
	private boolean finalizado; 
	private boolean turno;
	private Elemento[][] elementos;
	private ArrayList<Jugador> jugadores; 
	private int filas;
	private int columnas;
	
	public Tablero(int filas, int columnas, int neutrales){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		elementos = new  Elemento[filas][columnas]; 
		jugadores = new ArrayList<Jugador>(2); 
	}
	
	public Tablero(int filas, int columnas, Elemento[][] elementos){
		finalizado = false; 
		turno = true; 
		this.filas = filas;
		this.columnas = columnas;
		this.elementos = elementos;
		jugadores = new ArrayList<Jugador>(2); 
	}
	
	
	
	public void finish(){
		System.exit(0);
	}
	
	public void GenerarFichasNeutrales(int neutrales){
		Random r = new Random();
		int cantidad = neutrales;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				int pos = r.nextInt(1);
				if(pos == 0 && cantidad != 0){
					// Se inserta un virus neutral
					cantidad--;
				}
			}
		}
	}
	
	public boolean verificar(){
		boolean estaLleno = true;
		for (int i = 0; i < filas && estaLleno; i++) {
			for (int j = 0; j < columnas && estaLleno; j++) {
				estaLleno = elementos[i][j] != null;
			}
		}
		finalizado = estaLleno;
		return finalizado;
	}
	
	public void cambiarTurno(){
		turno = !turno;
		// Other fucking things.
	}
}
