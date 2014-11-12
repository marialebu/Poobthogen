package Aplicacion; 

import java.io.Serializable;


public abstract class Virus extends Elemento implements Serializable{
	
	private Tablero tablero;
	
	public abstract void evolucionar();
	public abstract String toString();
	
}
