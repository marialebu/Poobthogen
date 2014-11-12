package Aplicacion; 

import java.io.Serializable;


public class Bloque extends Elemento implements Serializable{
	
	public Bloque(Jugador j){
		jugador = j; 
	}
	
	public String toString(){
		String respuesta = "B";
		if(jugador == null){
			respuesta+="_";
		}else{
			respuesta+=jugador.toString();
		}
		return respuesta;
	}

}