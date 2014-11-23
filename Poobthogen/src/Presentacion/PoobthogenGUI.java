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
	
	private Tablero juego;
	private boolean tipoDeJuego;
	private JButton[] panelJugUno;
	private JButton[] panelJugDos;
	private String coloresUno;
	private String coloresDos;
	private final String[] cantTurnos = {"10", "15", "20", "30", "Ilimitado"};
	private final String[] cantTiempo = {"5 minutos", "10 minutos", "20 minutos", "Ilimitado"};
	
	private JPanel principal;
	private JPanel contenedorBotones;
	private JButton juegoNuevo;
	private JButton cargarJuego;
	private JButton creditos;
	private Font f;
	
	private JButton unJugador;
	private JButton dosJugadores;
	private JButton volver; 
	
	private JPanel fichasPreJuego;
	private JPanel jugadorUnoFichas;
	private JPanel jugadorDosFichas;
	private JTabbedPane tableros;
	private JPanel turnosTiempo;
	private JPanel areaPequeno;
	private JPanel areaMediano;
	private JPanel areaGrande;
	private JButton aceptar;
	private JButton cancelar;
	private JComboBox turnos;
	private JComboBox tiempo;
	
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		preparePantalla();
		f = new Font("Century Gothic", Font.PLAIN, 20);
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
		prepareVentanaConfiguracionInicial();
		prepareAccionesVentanaConfInicial();
	}
	
	private void prepareElementosConfiguracionTablero(){
		prepareVentanaConfiguracionTablero();
		prepareAccionesVentanaConfTablero();
	}
	
	private void prepareAccionesVentanaConfInicial(){
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosConfiguracionTablero();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosNuevo();
			}
		});
		
		//Faltan acciones para guardar configuracion :D
	}
	
	private void prepareVentanaConfiguracionTablero(){
		principal.removeAll();
		principal.updateUI();
		Dimension tam = this.getContentPane().getSize();
		principal.setLayout(new BorderLayout());
		principal.add(prepareTextosBorder("Configuración de tablero"), BorderLayout.PAGE_START);
		prepareMenuTableros();
		principal.add(tableros, BorderLayout.CENTER);
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(new Color(0f, 0f, 0f, 0.1f));
		aceptar = creaBoton(0, 0,"Aceptar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(aceptar);
		cancelar = creaBoton(0, 0,"Cancelar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cancelar);
		volver = creaBoton(0, 0,"Volver",tam.height-50, tam.width-50, f);
		contenedorBotones.add(volver);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	private void prepareAccionesVentanaConfTablero(){
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosVentanaJuego();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosNuevo();
			}
		});
		
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosPreJuego();
			}
		});
		
		
		//Faltan acciones para guardar configuracion :D
	}
	
	private void prepareElementosVentanaJuego(){
		prepareVentanaJuego();
	}
	private void prepareVentanaJuego(){
		JOptionPane.showMessageDialog(this, "Bienvenido a Poobthogen\nEl juego esta por comenzar.");
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new GridLayout(2,1));
	}
	
	private void prepareVentanaConfiguracionInicial(){
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new GridLayout(2,1));
		Dimension tam = this.getContentPane().getSize();
		preparePanelEscogerFicha();
		JPanel endOfWin = preparePanelTurnosTiempo();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(2, 1));
		contenedorBotones.setBackground(new Color(0f, 0f, 0f, 0.1f));
		aceptar = creaBoton(0, 0,"Aceptar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(aceptar);
		cancelar = creaBoton(0, 0,"Cancelar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cancelar);
		endOfWin.add(contenedorBotones);
		principal.add(endOfWin);
	}
	
	private JPanel preparePanelTurnosTiempo(){
		JPanel endOfWin = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		endOfWin.setLayout(new GridLayout(1, 2));
		JPanel turnosTiempo = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		turnosTiempo.setLayout(new GridLayout(2, 1));
		JPanel turnos =  prepareContenedor(new JPanel(), Color.BLACK, "", false);
		turnos.setLayout(new BorderLayout());
		turnos.add(prepareTextosBorder("Turnos"), BorderLayout.PAGE_START);
		this.turnos = new JComboBox(cantTurnos);
		turnos.add(this.turnos, BorderLayout.CENTER);
		turnosTiempo.add(turnos);
		JPanel tiempo =  prepareContenedor(new JPanel(), Color.BLACK, "", false);
		tiempo.setLayout(new BorderLayout());
		tiempo.add(prepareTextosBorder("Tiempo"), BorderLayout.PAGE_START);
		this.tiempo = new JComboBox(cantTiempo);
		tiempo.add(this.tiempo, BorderLayout.CENTER);
		turnosTiempo.add(tiempo);
		endOfWin.add(turnosTiempo);
		return endOfWin;
	}
	
	private void preparePanelEscogerFicha(){
		fichasPreJuego = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		fichasPreJuego.setLayout(new BorderLayout());
		fichasPreJuego.add(prepareTextosBorder("Jugadores"), BorderLayout.NORTH);
		JPanel contenedor = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		contenedor.setLayout(new GridLayout(1, 2));
		prepareContenedoresFichaUno(contenedor);
		prepareContenedoresFichaDos(contenedor);
		fichasPreJuego.add(contenedor);
		principal.add(fichasPreJuego);
	}
	
	private void prepareContenedoresFichaUno(JPanel contenedor){
		JPanel fichasUno = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		fichasUno.setLayout(new BorderLayout());
		fichasUno.add(prepareTextosBorder("Jugador 1"), BorderLayout.NORTH);
		jugadorUnoFichas = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		jugadorUnoFichas.setLayout(new GridLayout(1, 4));
		prepareBotonesFichas(jugadorUnoFichas);
		fichasUno.add(jugadorUnoFichas, BorderLayout.CENTER);
		contenedor.add(fichasUno);
	}
	
	private void prepareContenedoresFichaDos(JPanel contenedor){
		JPanel fichasDos = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		fichasDos.setLayout(new BorderLayout());
		fichasDos.add(prepareTextosBorder("Jugador 2"), BorderLayout.NORTH);
		jugadorDosFichas = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		jugadorDosFichas.setLayout(new GridLayout(1, 4));
		prepareBotonesFichas(jugadorDosFichas);
		fichasDos.add(jugadorDosFichas, BorderLayout.CENTER);
		contenedor.add(fichasDos);
	}
	
	private JLabel prepareTextosBorder(String texto){
		JLabel text1 = new JLabel(texto);
		text1.setForeground(Color.WHITE);
		text1.setFont(f);
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		return text1;
	}
	
	private JPanel prepareContenedor(JPanel j, Color c, String mensaje, boolean borde){
		if (borde){
			j.setBorder(new CompoundBorder( new EmptyBorder(1, 1, 1, 1), new TitledBorder(mensaje)));
		}
		j.setBackground(c);
		j.setOpaque(false);
		j.setLayout(new GridLayout(1, 4));
		j.setForeground(Color.WHITE);
		j.setFont(f);
		return j;
	}
	
	private void prepareMenuTableros(){
		tableros = new JTabbedPane();
		tableros.setFont(f);
		tableros.add("Pequeño", prepareAreaPequeno("/Presentacion/imagenes/pathogenMap.jpg ","/Presentacion/imagenes/interrogante.jpg"));
		tableros.add("Mediano", prepareAreaMediano("/Presentacion/imagenes/pathogenMap.jpg ","/Presentacion/imagenes/interrogante.jpg"));
		tableros.add("Grande", prepareAreaGrande("/Presentacion/imagenes/pathogenMap.jpg", "/Presentacion/imagenes/interrogante.jpg"));
		principal.add(tableros);
	}
	
	private JPanel prepareAreaPequeno(String ruta1, String ruta2){
		areaPequeno = new JPanel();
		prepareContenedor(areaPequeno,  new Color(0f, 0f, 0f, 0.1f), "", false);
		areaPequeno.setOpaque(false);
		JButton vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaPequeno.add(vacio); 
		JButton j = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(Color.BLACK);
		j.setOpaque(false);
		areaPequeno.add(j);
		return areaPequeno;
	}
	
	private JPanel prepareAreaMediano(String ruta1, String ruta2){
		areaMediano = new JPanel();
		prepareContenedor(areaMediano, new Color(0f, 0f, 0f, 0.1f), "", false);
		areaMediano.setOpaque(false);
		JButton vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaMediano.add(vacio); 
		JButton j = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(Color.BLACK);
		j.setOpaque(false);
		areaMediano.add(j);
		return areaMediano;
	}
	
	private JPanel prepareAreaGrande(String ruta1, String ruta2){
		areaGrande = new JPanel();
		prepareContenedor(areaGrande, new Color(0f, 0f, 0f, 0.1f), "", false);
		areaGrande.setOpaque(false);
		JButton vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaGrande.add(vacio); 
		JButton j = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		j.setBackground(Color.BLACK);
		j.setOpaque(false);
		areaGrande.add(j);
		return areaGrande;
	}
	
	private void prepareBotonesFichas(JPanel j){
		panelJugUno = new JButton[4];
		panelJugUno[0] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/verde/NivelUno.png")).getImage()));
		panelJugUno[0].setBackground(Color.BLACK);
		panelJugUno[0].setOpaque(false);
		panelJugUno[1] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/roja/NivelUno.png")).getImage()));
		panelJugUno[1].setBackground(Color.BLACK);
		panelJugUno[1].setOpaque(false);
		panelJugUno[2] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/amarilla/NivelUno.png")).getImage()));
		panelJugUno[2].setBackground(Color.BLACK);
		panelJugUno[2].setOpaque(false);
		panelJugUno[3] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/azul/NivelUno.png")).getImage()));
		panelJugUno[3].setBackground(Color.BLACK);
		panelJugUno[3].setOpaque(false);
		for (JButton b : panelJugUno){
			j.add(b);
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
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(new Color(0f, 0f, 0f, 0.1f));
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
