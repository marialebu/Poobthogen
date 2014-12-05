package Aplicacion;

public class Ofensiva extends Maquina{

	
	protected int[] dx = {1,-1,0,0};
	protected int[] dy = {0,0,1,-1};
	
	private static boolean[][] visited;
	/**
	 * Constructor de la clase ofensiva
	 * @param id Identificador de la maquina
	 * @param t Tablero en el que juega
	 * @throws PoobthogenExcepcion
	 */
	public Ofensiva(char id, Tablero t) throws PoobthogenExcepcion {
		super(id, t);
	}
	
	/**
	 * Realiza una jugada. 
	 * @param x Posicion en x del tablero
	 * @param y Posicion en y del tablero
	 * @param virus Tipo de virus que quiere colocar
	 * @return Si se ha ganado el juego o no
	 * @throws PoobthogenExcepcion
	 */
	public boolean juega(int x, int y, String virus) throws PoobthogenExcepcion{
		visited = new boolean[tablero.filas()][tablero.columnas()];
		int I_MAX = -1;
		int J_MAX = -1;
		int maximo = -1;
		int max_temp;
		for (int i = 0; i < tablero.filas(); i++) {
			for (int j = 0; j <  tablero.columnas(); j++) {
				if(!visited[i][j] && tablero.getElemento(i, j)!=null && this != tablero.getElemento(i, j).getJugador() && tablero.getElemento(i, j).getNivel(true)>0){
					max_temp = busquedaVirus(i,j,tablero.getElemento(i, j).getNivel(true),tablero.getElemento(i, j).getNivel(true));
					if(max_temp > maximo){
						maximo = max_temp;
						I_MAX = i;
						J_MAX = j;
					}
				}
			}
		}
		boolean termina = false;
		if(I_MAX != -1 && J_MAX != -1){
			if(tablero.getElemento(I_MAX, J_MAX).getNivel(true)<=2){
				termina = tablero.agregarElemento((int)identificador-48, I_MAX, J_MAX, tablero.getElemento(I_MAX, J_MAX).GetNextLevel(), true);
			}else{
				visited = new boolean[tablero.filas()][tablero.columnas()];
				boolean hiceAlgoConTres = buscarFichasPropias(I_MAX, J_MAX, tablero.getElemento(I_MAX, J_MAX).getNivel(true));
				if(!hiceAlgoConTres){
					termina = tablero.agregarElemento((int)identificador-48, I_MAX, J_MAX, "Destructor", true);
				}
				termina = tablero.verificar();
			}
		}else{
			termina = jugarMaquinaRandom(x, y, virus);
		}
		return termina;
	}
	
	
	/**
	 * Busca si puede realizar una jugada que haga mas danio con una ficha de la maquina, poniendo un bloque
	 * @param i Posicion en x
	 * @param j Posicion en y 
	 * @param l 
	 * @return
	 * @throws PoobthogenExcepcion
	 */
	private boolean buscarFichasPropias(int i, int j, int l) throws PoobthogenExcepcion {
		boolean found = false;
		for (int k = 0; k < dx.length && !found; k++) {
			int temp_dx = i+dx[k];
			int temp_dy = j+dy[k];
			if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas() && !visited[temp_dx][temp_dy]
					&& tablero.getElemento(temp_dx, temp_dy)!=null && tablero.getElemento(temp_dx, temp_dy).getNivel(true)==l){
				if(this == tablero.getElemento(temp_dx, temp_dy).getJugador()){
					found = true;
					tablero.agregarElemento((int)identificador-48, temp_dx, temp_dy, "NivelTres", true);
				}else{
					visited[temp_dx][temp_dy] = true;
					buscarFichasPropias(temp_dx, temp_dy, l);
				}
			}
		}
		return found;
	}

	private int busquedaVirus(int i, int j, int l, int maximo) {
		for (int k = 0; k < dx.length; k++) {
			int temp_dx = i+dx[k];
			int temp_dy = j+dy[k];
			if(temp_dx>=0 && temp_dx < tablero.filas() && temp_dy>=0 && temp_dy < tablero.columnas() && !visited[temp_dx][temp_dy]
					&& tablero.getElemento(temp_dx, temp_dy)!=null && this != tablero.getElemento(temp_dx, temp_dy).getJugador() && tablero.getElemento(temp_dx, temp_dy).getNivel(true)==l){
				maximo+=l;
				visited[temp_dx][temp_dy] = true;
				busquedaVirus(temp_dx, temp_dy, l, maximo);
			}
		}
		return maximo;
	}
	
}
