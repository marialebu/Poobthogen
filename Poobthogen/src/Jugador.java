import java.io.Serializable;


public class Jugador implements Serializable{
	private String identificador; 
	
	/**
	 * 
	 * @param id
	 */
	public Jugador(String id){
		identificador = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toString(){
		return identificador; 
	}
	

}
