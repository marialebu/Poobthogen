package Aplicacion; 

import java.io.Serializable;


public abstract class Elemento implements Serializable{
	protected int nivel; 
	protected Jugador jugador;
	
	public abstract String toString();
	public abstract int compareTo(Virus v);

}
