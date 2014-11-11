import java.io.Serializable;


public class NivelDos extends Virus implements Serializable{
	
	public NivelDos(Jugador j){
		jugador = j; 
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "D"+jugador.toString();
	}
}
