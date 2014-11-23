package Presentacion;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.*;

import java.util.*;

import Aplicacion.*; 

import java.io.*;

import javax.swing.filechooser.FileNameExtensionFilter;
public class PoobthogenGUI extends JFrame{
	
	Tablero juego;
	boolean tipoDeJuego;
	JButton[] panelJugUno;
	JButton[] panelJugDos;
	String coloresUno;
	String coloresDos;
	
	JPanel principal;
	JPanel contenedorBotones;
	JButton juegoNuevo;
	JButton cargarJuego;
	JButton creditos;
	Font f;
	
	JButton unJugador;
	JButton dosJugadores;
	JButton volver; 
	
	JPanel jugadorUnoFichas;
	JPanel jugadorDosFichas;
	JTabbedPane tableros;
	JPanel turnosTiempo;
	
	JPanel areaPequeno;
	JPanel areaMediano;
	JPanel areaGrande;
	
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		preparePantalla();
		prepareElementosInicio();		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PoobthogenGUI juego  = new PoobthogenGUI();
		juego.setVisible(true);
	}
	
	private void prepareElementosInicio(){
		preparePantallaInicio();
		prepareAccionesInicio();
	}
	
	private void prepareElementosNuevo(){
		preparePantallaNuevoJuego();
		prepareAccionesNuevo();
	}
	
	private void preparePantalla(){
		Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - getSize().width) / 2; 
        int y = (screen.height - getSize().height) / 2; 
        setSize(x, y);
        principal = new ImagenFondo();
        add(principal);
	}
	
	private void prepareElementosPreJuego(){
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new GridLayout(3,1));
		jugadorUnoFichas = new JPanel();
		jugadorDosFichas = new JPanel();
		Border panel = new EmptyBorder(1, 1, 1, 1);
		prepareContenedor(jugadorUnoFichas, Color.BLACK, "Fichas jugador uno");
		prepareContenedor(jugadorDosFichas, Color.BLACK, "Fichas jugador dos");
		prepareBotonesFichasUno();
		prepareBotonesFichasDos();
		principal.add(jugadorUnoFichas);
		principal.add(jugadorDosFichas);
		prepareMenuTableros();
	}
	
	private void prepareContenedor(JPanel j, Color c, String mensaje){
		j.setBorder(new CompoundBorder( new EmptyBorder(1, 1, 1, 1), new TitledBorder(mensaje)));
		j.setBackground(c);
		j.setOpaque(false);
		j.setLayout(new GridLayout(1, 4));
		j.setForeground(Color.WHITE);
	}
	
	private void prepareMenuTableros(){
		tableros = new JTabbedPane(); 
		tableros.setBackground(new Color(5, 7, 46));
		tableros.add("Pequeño", prepareAreaPequeno("/Presentacion/verde/NivelUno.png", "/Presentacion/imagenes/interrogante.jpg"));
		tableros.add("Mediano", prepareAreaMediano("/Presentacion/verde/NivelUno.png", "/Presentacion/imagenes/interrogante.jpg"));
		tableros.add("Grande", prepareAreaGrande("/Presentacion/verde/NivelUno.png", "/Presentacion/imagenes/interrogante.jpg"));
		principal.add(tableros);
	}
	
	private JPanel prepareAreaPequeno(String ruta1, String ruta2){
		areaPequeno = new JPanel();
		prepareContenedor(areaPequeno, Color.BLACK, "Tableros");
		areaPequeno.setOpaque(true);
		JButton vacio = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(new Color(5, 7, 46));
		areaPequeno.add(vacio); 
		JButton j = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(new Color(5, 7, 46));
		areaPequeno.add(j);
		return areaPequeno;
	}
	
	private JPanel prepareAreaMediano(String ruta1, String ruta2){
		areaMediano = new JPanel();
		prepareContenedor(areaMediano, Color.BLACK, "");
		areaMediano.setOpaque(true);
		JButton vacio = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(new Color(5, 7, 46));
		areaMediano.add(vacio); 
		JButton j = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(new Color(5, 7, 46));
		areaMediano.add(j);
		return areaMediano;
	}
	
	private JPanel prepareAreaGrande(String ruta1, String ruta2){
		areaGrande = new JPanel();
		prepareContenedor(areaGrande, Color.BLACK, "");
		areaGrande.setOpaque(true);
		JButton vacio = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(new Color(5, 7, 46));
		areaGrande.add(vacio); 
		JButton j = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(new Color(5, 7, 46));
		areaGrande.add(j);
		return areaGrande;
	}
	
	
	
	private void prepareBotonesFichasUno(){
		panelJugUno = new JButton[4];
		panelJugUno[0] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/verde/NivelUno.png")).getImage()));
		panelJugUno[0].setBackground(Color.BLACK);
		panelJugUno[0].setOpaque(false);
		panelJugUno[1] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/roja/NivelUno.png")).getImage()));
		panelJugUno[1].setBackground(Color.BLACK);
		panelJugUno[1].setOpaque(false);
		panelJugUno[2] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/amarilla/NivelUno.png")).getImage()));
		panelJugUno[2].setBackground(Color.BLACK);
		panelJugUno[2].setOpaque(false);
		panelJugUno[3] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/azul/NivelUno.png")).getImage()));
		panelJugUno[3].setBackground(Color.BLACK);
		panelJugUno[3].setOpaque(false);
		for (JButton b : panelJugUno){
			jugadorUnoFichas.add(b);
		}
	}
	
	private void prepareBotonesFichasDos(){
		panelJugDos = new JButton[4];
		panelJugDos[0] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/verde/NivelUno.png")).getImage()));
		panelJugDos[0].setBackground(Color.BLACK);
		panelJugDos[0].setOpaque(false);
		panelJugDos[1] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/roja/NivelUno.png")).getImage()));
		panelJugDos[1].setBackground(Color.BLACK);
		panelJugDos[1].setOpaque(false);
		panelJugDos[2] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/amarilla/NivelUno.png")).getImage()));
		panelJugDos[2].setBackground(Color.BLACK);
		panelJugDos[2].setOpaque(false);
		panelJugDos[3] = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/azul/NivelUno.png")).getImage()));
		panelJugDos[3].setBackground(Color.BLACK);
		panelJugDos[3].setOpaque(false);
		for (JButton b : panelJugDos){
			jugadorDosFichas.add(b);
		}
	}
	
	private void preparePantallaInicio(){
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new BorderLayout());
		Dimension tam = this.getContentPane().getSize();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(new Color(0f, 0f, 0f, 0.1f));
		f = new Font("Century Gothic", Font.PLAIN, 20);
		juegoNuevo = creaBoton(0, 0,"Juego Nuevo",tam.height-50, tam.width-50, f);
		contenedorBotones.add(juegoNuevo);
		cargarJuego = creaBoton(0, 0,"Cargar Juego",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cargarJuego);
		creditos= creaBoton(0, 0,"Creditos",tam.height-50, tam.width-50, f);
		contenedorBotones.add(creditos);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	
	
	private void preparePantallaNuevoJuego(){
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new BorderLayout());
		Dimension tam = this.getContentPane().getSize();
		principal.removeAll();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(new Color(0f, 0f, 0f, 0.1f));
		f = new Font("Century Gothic", Font.PLAIN, 20);
		unJugador = creaBoton(0, 0,"Un jugador",tam.height-50, tam.width-50, f);
		contenedorBotones.add(unJugador);
		dosJugadores = creaBoton(0, 0,"Dos jugadores",tam.height-50, tam.width-50, f);
		contenedorBotones.add(dosJugadores);
		volver = creaBoton(0, 0,"Volver",tam.height-50, tam.width-50, f);
		contenedorBotones.add(volver);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	/**
	 * Crea un boton con bordes redondeados con unas especificaciones 
	 * @param x posicion en x
	 * @param y posicion en y 
	 * @param mensaje String del mensaje que debe llevar
	 * @param w Ancho
	 * @param h Alto
	 * @param f Fuente del mensaje
	 * @return Retorna el Jbutton con las especificaciones. 
	 */
	private JButton creaBoton(int x, int y, String mensaje, int w, int h, Font f){
		JButton b = new RoundButton(mensaje);
		b.setFocusPainted(false);
		b.setFont(f);
		return b;
		
	}
	/**
	 * Prepara las acciones del panel de inicio. 
	 */
	private void prepareAccionesInicio(){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter(){
    		public void windowClosing (WindowEvent e){
    			salga();	
    		}
    	});
    	
    	cargarJuego.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cargueJuego();
            }
        });
    	
    	creditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestreCreditos();
			}
		});
    	
    	juegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareElementosNuevo();
			}
		});
	}
	
	/**
	 * Prepara las acciones del panel de juego nuevo. 
	 */
	private void prepareAccionesNuevo(){
		
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareElementosInicio();
			}
		});
		
		unJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareElementosPreJuego();
			}
		});
		
	}
	
	/**
	 * Muestra los creditos del juego
	 */
	private void muestreCreditos(){
		String mensaje = "Bla bla bla y más bla bla";
		JOptionPane.showMessageDialog(this,(Object)mensaje, "Creditos", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Abre o importa un juego segun la opcion del usuario. 
	 */
	private void cargueJuego(){
		Object [] opciones ={"Abrir","Importar","Cancelar", };
		int eleccion = JOptionPane.showOptionDialog(this,"Desea abrir o importar un juego","Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION){
			try{
				escogerArchivo();
			}catch (PoobthogenExcepcion ev){
				JOptionPane.showMessageDialog(this,(Object)ev.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}else if(eleccion == JOptionPane.NO_OPTION){
			try{
				escogerArchivoImportar();
			}catch (PoobthogenExcepcion ev){
				JOptionPane.showMessageDialog(this,(Object)ev.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 *Crea un nuevo archivo para salvar mostrando una ventana para crearlo. 
	 *@throws E8oMasExcepcion
	 */
	private void archivoNuevo() throws PoobthogenExcepcion{
		JFileChooser fileChooser = new JFileChooser(); 
		int returnV = fileChooser.showSaveDialog(this);
		File e = fileChooser.getSelectedFile();
		if (returnV == fileChooser.APPROVE_OPTION){
			PoobthogenArchivos.guardar(e, juego); 
		}
	}
	
	/**
	 *Genera una ventana para escoger el archivo que se va a abrir. 
	 *@throws E8oMasExcepcion
	 */
	private void escogerArchivo() throws PoobthogenExcepcion{
		JFileChooser fileChooser = new JFileChooser();
		int returnV = fileChooser.showOpenDialog(this);
		File e = fileChooser.getSelectedFile();
		if (returnV == fileChooser.APPROVE_OPTION){
			juego = PoobthogenArchivos.abrir(e); 
		}
	}
	
	/**
	 *Genera una ventana para escoger el archivo que se va a abrir. 
	 *@throws E8oMasExcepcion
	 */
	private void escogerArchivoImportar() throws PoobthogenExcepcion{
		JFileChooser fileChooser = new JFileChooser();
		int returnV = fileChooser.showOpenDialog(this);
		File e = fileChooser.getSelectedFile();
		if (returnV == fileChooser.APPROVE_OPTION){
			juego = PoobthogenArchivos.importar(e); 
		}
	}
	
	/**
	 * Termina con la aplicacion. 
	 */
	private void salga(){
    	Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(this,"En realidad desea cerrar la aplicacion","Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION){
			dispose(); 
    		System.exit(0);
		}
    }
}
