package Presentacion;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;

import javax.swing.border.*;

import java.util.*;

import Aplicacion.*; 

import java.io.*;

import javax.swing.filechooser.FileNameExtensionFilter;
public class PoobthogenGUI extends JFrame{
	
	private final int ONE_SECOND;
	private final String[] CANT_TURNOS = {"Ilimitado","10", "15", "20", "30"};
	private final String[] CANT_TIEMPO = {"Ilimitado","5 minutos", "10 minutos", "20 minutos"};
	private final int[] PEQUENO = {6, 6};
	private final int[] MEDIANO = {8, 8};
	private final int[] GRANDE = {10,10};
	private final String[] COLOR_VIRUS = {"verde", "roja", "amarilla", "azul", "neutral"};
	private final String[] MAQUINA = {"Timida", "Ofensiva", "Irreflexiva"};
	private final String[] TIPOS_VIRUS = {"NivelUno", "NivelDos", "NivelTres", "Destructor"};
	private final String[] JUGADORES = {"Jugador uno", "Jugador dos"};
	private boolean neutrales;
	private int iniciales;
	private int tiempoIncial;
	private int x;
	private int y;
	private boolean juegoPvP;
	
	private Tablero juego;
	private Jugador jugadorUno;
	private Jugador jugadorDos;
	private HashMap<String, Color> colores;
	private String colorJugUno;
	private String colorJugDos;
	private int turnosJuego;
	private int tiempoJuego;
	private String tipoMaquina;
	private String opcionVirus;
	private Jugador ganador;
	private boolean comienza;
	
	private JButton[] panelJugUno;
	private JButton[] panelJugDos;
	
	private JButton[][] fichasJuego;
	
	private JPanel principal;
	private JPanel contenedorBotones;
	private JButton juegoNuevo;
	private JButton cargarJuego;
	private JButton creditos;
	private Font f;
	
	private JButton unJugador;
	private JButton dosJugadores;
	private JButton volver; 
	
	private int k;
	private int k1;
	private JButton vacioPequeno;
	private JButton interrogantePequeno;
	private JButton vacioMediano;
	private JButton interroganteMediano;
	private JButton vacioGrande;
	private JButton interroganteGrande;
	private JPanel fichasPreJuego;
	private JPanel jugadorUnoFichas;
	private JPanel jugadorDosFichas;
	private JTabbedPane tableros;
	private JPanel areaPequeno;
	private JPanel areaMediano;
	private JPanel areaGrande;
	private JButton aceptar;
	private JButton cancelar;
	private JComboBox turnos;
	private JComboBox tiempo;
	private JComboBox jugadorInicial;
	
	private JMenuItem guardar;
	private JMenuItem exportar;
	private JMenuItem reiniciar;
	private JMenuItem salir;
	private JLabel muestraTurnos;
	private JLabel muestraTiempo;
	private JPanel tableroJuego;
	private Timer timer;
	private JPanel contenedor;
	private JButton pasaTurno;
	private JButton rendirse;
	private JLabel proporcionUno;
	private JLabel proporcionDos;
	private JPanel menuUno; 
	private JPanel menuDos; 
	private JButton revancha;
	private JButton volverAlMenuPrincipal;
	
	/**Ejecuta el juego. 
	 * @param args
	 */
	public static void main(String[] args) {
		PoobthogenGUI juego  = new PoobthogenGUI();
		juego.setVisible(true);
	}
	
	/**
	 * Constructor de la interfaz.
	 */
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		f = new Font("Century Gothic", Font.PLAIN, 20);
		preparePantalla();
		prepareElementosInicio();	
		setResizable(false);
		colores = new HashMap<String, Color>();
		inicializaColores();
		ONE_SECOND = 1000;
		turnosJuego = -1;
		tiempoJuego = -1;
		comienza = true;
	}
	
	/**
	 * Inicializa los colores por defecto. 
	 */
	private void inicializaColores(){
		colores.put("amarilla", new Color(221,172,69));
		colores.put("verde", new Color(100,203,176));
		colores.put("roja", new Color(225,65,129));
		colores.put("azul", new Color(91,104,206));	
	}
	
	/**
	 * Prepara los elementos del inicio del juego. 
	 */
	private void prepareElementosInicio(){
		try{
			preparePantallaInicio();
			prepareAccionesInicio();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara los elementos para la ventana de juego nuevo. 
	 */
	private void prepareElementosNuevo(){
		try{
			preparePantallaNuevoJuego();
			prepareAccionesNuevo();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara los elementos para la ventana de configuracion inicial del juego pvp
	 */
	private void prepareElementosPreJuego(){
		try{
			prepareVentanaConfiguracionInicial();
			prepareAccionesVentanaConfInicial();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara los elementos para la ventana de configuracion inicial del juego contra la maquina
	 */
	private void prepareElementosPreJuegoUno(){
		try{
			prepareVentanaConfiguracionInicialUno();
			prepareAccionesVentanaConfInicialUno();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara los elementos para la ventana de configuracion del tablero. 
	 */
	private void prepareElementosConfiguracionTablero(){
		try{
			prepareVentanaConfiguracionTablero();
			prepareAccionesVentanaConfTablero();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
		
	}
	
	/**
	 * Prepara la ventana del juego configurando la pantalla. 
	 */
	private void prepareElementosVentanaJuego(){
		try{
			prepareVentanaJuego();
			preparePantallaJuego();
			prepareAccionesJuego();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara los elementos de la pantalla de estadisticas
	 */
	private void prepareElementosGanadorJuego(){
		try{
			prepareVentanaGanadorJuego();
			prepareAccionesVentanaGanador();
		}catch(Exception e){
			JOptionPane.showMessageDialog(PoobthogenGUI.this, PoobthogenExcepcion.ERROR_INESPERADO, "ERROR", JOptionPane.ERROR_MESSAGE);
			Log.registreError(e);
			salgaError();
		}
	}
	
	/**
	 * Prepara el diseño de la pantalla de nuevo juego. 
	 */
	private void preparePantallaNuevoJuego(){
		remove(principal);
		principal = new ImagenFondo("/Presentacion/imagenes/PoobthogenInicio.jpg");
		add(principal);
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new BorderLayout());
		Dimension tam = this.getContentPane().getSize();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(Color.BLACK);
		unJugador = creaBoton(0, 0,"Un jugador",tam.height-50, tam.width-50, f);
		contenedorBotones.add(unJugador);
		dosJugadores = creaBoton(0, 0,"Dos jugadores",tam.height-50, tam.width-50, f);
		contenedorBotones.add(dosJugadores);
		volver = creaBoton(0, 0,"Volver",tam.height-50, tam.width-50, f);
		contenedorBotones.add(volver);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	/**
	 * Prepara el diseño de la pantalla inicial 
	 */
	private void preparePantallaInicio(){
		remove(principal);
		principal = new ImagenFondo("/Presentacion/imagenes/PoobthogenInicio.jpg");
		add(principal);
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new BorderLayout());
		Dimension tam = this.getContentPane().getSize();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(Color.BLACK);
		f = new Font("Century Gothic", Font.PLAIN, 20);
		juegoNuevo = creaBoton(0, 0,"Juego Nuevo",tam.height-50, tam.width-50, f);
		contenedorBotones.add(juegoNuevo);
		cargarJuego = creaBoton(0, 0,"Cargar Juego",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cargarJuego);
		creditos= creaBoton(0, 0,"Creditos",tam.height-50, tam.width-50, f);
		contenedorBotones.add(creditos);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	/**
	 * Prepara el diseño de la ventana de configuracion inicial para el juego contra maquina
	 */
	private void prepareVentanaConfiguracionInicialUno(){
		remove(principal);
		principal = new ImagenFondo("/Presentacion/imagenes/fondoPoobthogen.jpg");
		add(principal);
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new GridLayout(2,1));
		Dimension tam = this.getContentPane().getSize();
		preparePanelEscogerFichaUno();
		JPanel endOfWin = preparePanelTurnosTiempo();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(2, 1));
		contenedorBotones.setBackground(Color.BLACK);
		aceptar = creaBoton(0, 0,"Aceptar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(aceptar);
		cancelar = creaBoton(0, 0,"Cancelar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cancelar);
		endOfWin.add(contenedorBotones);
		principal.add(endOfWin);
	}
	
	/**
	 * Prepara el diseño de la ventana de configuracion inicial para el pvp
	 */
	private void prepareVentanaConfiguracionInicial(){
		remove(principal);
		principal = new ImagenFondo("/Presentacion/imagenes/fondoPoobthogen.jpg");
		add(principal);
		principal.removeAll();
		principal.updateUI();
		principal.setLayout(new GridLayout(2,1));
		Dimension tam = this.getContentPane().getSize();
		preparePanelEscogerFicha();
		JPanel endOfWin = preparePanelTurnosTiempo();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(2, 1));
		contenedorBotones.setBackground(Color.BLACK);
		aceptar = creaBoton(0, 0,"Aceptar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(aceptar);
		cancelar = creaBoton(0, 0,"Cancelar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cancelar);
		endOfWin.add(contenedorBotones);
		principal.add(endOfWin);
	}
	
	/**
	 * Prepara las acciones de la ventana de estadisticas
	 */
	private void prepareAccionesVentanaGanador(){
		revancha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego = null;
				configureTamañoPantalla();
				prepareElementosPreJuego();
			}
		});
		
		volverAlMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego = null;
				remove(principal);
				principal = new ImagenFondo("/Presentacion/imagenes/PoobthogenInicio.jpg");
		        add(principal);
		        configureTamañoPantalla();
				prepareElementosInicio();
			}
		});
	}
	
	/**
	 * Prepara los contenedores de la ventana de estadisticas
	 */
	private void prepareVentanaGanadorJuego(){
		timer.stop();
		refresque();
		JOptionPane.showMessageDialog(this,  ganador!= null ? "Ha ganado el jugador: "+ganador.toString() : "Empate");
		remove(principal);
		principal = new ImagenFondo("/Presentacion/imagenes/fondoPoobthogen.jpg");
		add(principal);
		if(ganador != null){
			principal.setBorder(new LineBorder(ganador.toString().equals("1") ? colores.get(colorJugUno) : colores.get(colorJugDos), 20));
		}
		principal.setLayout(new BorderLayout());
		principal.add(prepareTextosBorder("Estadisticas"), BorderLayout.NORTH);
		prepareContenedoresEstadisticas();
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(Color.BLACK);
		volverAlMenuPrincipal = creaBoton(0, 0,"Menu Principal",0, 0, f);
		contenedorBotones.add(volverAlMenuPrincipal);
		revancha = creaBoton(0, 0,"Revancha",0, 0, f);
		contenedorBotones.add(revancha);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
		
	}
	
	/**
	 * Prepara los contenedores de la ventana de estadisticas
	 */
	private void prepareContenedoresEstadisticas(){
		int[] proporciones = juego.proporciones();
		JPanel estadisticas = prepareContenedor(new JPanel(), "", false);
		estadisticas.setLayout(new GridLayout(2, 1));
		JPanel estadisticasUno = prepareContenedor(new JPanel(), "", false);
		estadisticasUno.setLayout(new BorderLayout());
		estadisticasUno.add(prepareTextosBorder("Jugador Uno"), BorderLayout.NORTH);
		estadisticasUno.add(prepareGrafica(proporciones[0], colorJugUno));
		estadisticasUno.setBorder(new LineBorder(new Color(0f,0f,0f,0f), 20));
		estadisticas.add(estadisticasUno);
		JPanel estadisticasDos = prepareContenedor(new JPanel(), "", false);
		estadisticasDos.setLayout(new BorderLayout());
		estadisticasDos.add(prepareTextosBorder("Jugador Dos"), BorderLayout.NORTH);
		estadisticasDos.add(prepareGrafica(proporciones[1], colorJugDos));
		estadisticasDos.setBorder(new LineBorder(new Color(0f,0f,0f,0f), 20));
		estadisticas.add(estadisticasDos);
		principal.add(estadisticas);
	}
	
	/**
	 * Crea la grafica de la proporcion de fichas del jugador. 
	 * @param prop Proporcion de fichas
	 * @param color Color escogido por el jugador. 
	 * @return Un panel con la grafica hecha en botones. 
	 */
	private JPanel prepareGrafica(int prop, String color){
		JPanel estadica = prepareContenedor(new JPanel(), "", false);
		estadica.setLayout(new GridLayout(1, 100));
		JButton cantidad;
		for (int i = 1; i < 100; i++) {
			cantidad = new JButton("");
			cantidad.setBorder(new LineBorder(new Color(0f,0f,0f,0f)));
			cantidad.setBackground(Color.black);
			if(i <= prop){
				cantidad.setBackground(colores.get(color));
			}
			estadica.add(cantidad);
		}
		return estadica;
	}
	/**
	 * Prepara los menus del juego para el jugador uno teniendo en cuenta el turno actual del juago.  
	 * @param j Posicion en y del boton
	 * @throws PoobthogenExcepcion
	 */
	private void preparePanelJugUno(int j) throws PoobthogenExcepcion{
		if(juego.getTurno()){
			opcionVirus = TIPOS_VIRUS[j]; 	
		}else{
			throw new PoobthogenExcepcion(PoobthogenExcepcion.SELECCION_INVALIDA);
		}
	}
	
	/**
	 * Prepara los menus del juego para el jugador uno teniendo en cuenta el turno actual del juago.  
	 * @param j Posicion en y del boton
	 * @throws PoobthogenExcepcion
	 */
	private void preparePanelJugDos(int j) throws PoobthogenExcepcion{
		if(!juego.getTurno()){
			opcionVirus = TIPOS_VIRUS[j]; 	
		}else{
			throw new PoobthogenExcepcion(PoobthogenExcepcion.SELECCION_INVALIDA);
		}
	}
	
	/**
	 * Configura el tamaño de la pantalla. 
	 */
	private void configureTamañoPantalla(){
		 setSize(x, y);
	     setLocation(x/2-50, y/2-100);
	}
	
	/**
	 * Prepara el JFrame con el fondo y un tamaño establecido. 
	 */
	private void preparePantalla(){
		Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
        x = (screen.width - getSize().width) / 2+50; 
        y = (screen.height - getSize().height) / 2+100; 
        setSize(x, y);
        setLocation(x/2-50, y/2-100);
		principal = new ImagenFondo("/Presentacion/imagenes/PoobthogenInicio.jpg");
        add(principal);
	}
	
	/**
	 * Prepara el JFrame para la ventana de juego, con un nuevo fondo y un nuevo tamano. 
	 */
	private void preparePantallaJuego(){
		Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screen.width - getSize().width/10; 
        int y = screen.height-getSize().height/10; 
        setSize(x, y);
        setLocation( getSize().width/40, 0);
	}
	
	/**
	 * Prepara la ventana donde se ejecuta el juego.
	 */
	private void prepareVentanaJuego(){
		prepareMenu();
		remove(principal);
		JOptionPane.showMessageDialog(this, "Bienvenido a Poobthogen\nEl juego esta por comenzar.");
		principal = new ImagenFondo("/Presentacion/imagenes/fondoPoobthogen.jpg");
		add(principal);
		principal.setLayout(new BorderLayout());
		prepareContenedoresJuego();
		opcionVirus = "NivelUno";
	}
	
	/**
	 * Prepara la barra de menus
	 */
	private void prepareMenu(){
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(f);
		guardar = new JMenuItem("Guardar");
		guardar.setFont(f);
		exportar = new JMenuItem("Exportar");
		exportar.setFont(f);
		reiniciar = new JMenuItem("Reiniciar");
		reiniciar.setFont(f);
		salir = new JMenuItem("Salir");
		salir.setFont(f);
		opciones.add(guardar);
		opciones.add(exportar);
		opciones.add(new JSeparator());
		opciones.add(reiniciar);
		opciones.add(salir);
		barra.add(opciones);
	}
	
	/**
	 * Prepara el diseno de la ventana de configuracion del tablero. 
	 */
	private void prepareVentanaConfiguracionTablero(){
		principal.removeAll();
		principal.updateUI();
		Dimension tam = this.getContentPane().getSize();
		principal.setLayout(new BorderLayout());
		principal.add(prepareTextosBorder("Configuracion de tablero"), BorderLayout.PAGE_START);
		prepareMenuTableros();
		principal.add(tableros, BorderLayout.CENTER);
		contenedorBotones = new JPanel();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		contenedorBotones.setBackground(Color.BLACK);
		aceptar = creaBoton(0, 0,"Aceptar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(aceptar);
		cancelar = creaBoton(0, 0,"Cancelar",tam.height-50, tam.width-50, f);
		contenedorBotones.add(cancelar);
		volver = creaBoton(0, 0,"Volver",tam.height-50, tam.width-50, f);
		contenedorBotones.add(volver);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	/**
	 * Prepara los contenedores de la ventana del juego. 
	 */
	private void prepareContenedoresJuego(){
		Dimension tam = this.getContentPane().getSize();
		contenedor = prepareContenedor(new JPanel(),"", false); 
		contenedor.setLayout(new GridLayout(1, 4));
		contenedor.add(prepareTextosBorder("Turnos: "));
		iniciales = turnosJuego;
		muestraTurnos = prepareTextosBorder(turnosJuego  <=0 ? "Ilimitado" : turnosJuego+"");
		contenedor.add(muestraTurnos);
		contenedor.add(prepareTextosBorder("Tiempo: "));
		tiempoIncial = tiempoJuego;
		muestraTiempo = prepareTextosBorder(tiempoJuego <= 0 ? "Ilimitado" : tiempoJuego+"");
		contenedor.add(muestraTiempo);
		contadorTiempo(contenedor);
		inicia();
		principal.add(contenedor, BorderLayout.PAGE_START);
		prepareMenuJugador();
		tableroJuego = prepareContenedor(new JPanel(), "", false);
		if(!comienza){
			juego.cambiarTurno();
		}
		refresque();
		JPanel contenedorBotones = prepareContenedor(new JPanel(),"", false);
		contenedorBotones.setLayout(new GridLayout(1, 2));
		pasaTurno = creaBoton(0, 0,"Pasar turno",tam.height-50, tam.width-50, f);
		contenedorBotones.add(pasaTurno);
		rendirse = creaBoton(0, 0,"Rendirse",tam.height-50, tam.width-50, f);
		contenedorBotones.add(rendirse);
		principal.add(contenedorBotones, BorderLayout.PAGE_END);
	}
	
	/**
	 * Prepara el diseño del menu del jugador. 
	 */
	private void prepareMenuJugador(){
		int[] proporciones = juego.proporciones();
		menuUno = prepareContenedor(new JPanel(),"", false);
		menuUno.setLayout(new GridLayout(5, 1));
		proporcionUno = prepareTextosBorder(proporciones[0]+"%");
		menuUno.add(proporcionUno);
		panelJugUno = menuJugador(menuUno, colorJugUno);
		principal.add(menuUno, BorderLayout.WEST);
		menuDos = prepareContenedor(new JPanel(),"", false);
		menuDos.setLayout(new GridLayout(5, 1));
		proporcionDos = prepareTextosBorder(proporciones[1]+"%");
		menuDos.add(proporcionDos);
		panelJugDos = menuJugador(menuDos, colorJugDos);
		principal.add(menuDos, BorderLayout.EAST);
	}
	
	/**
	 * Prepara las acciones para la ventana de configuracion inicial del juego pvp. 
	 */
	private void prepareAccionesVentanaConfInicial(){
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAceptar();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosNuevo();
			}
		});
		
		for (k = 0; k < panelJugUno.length; k++) {
			panelJugUno[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					colorJugUno = COLOR_VIRUS[j];		
				}
			});
		}
		
		for (k = 0; k < panelJugDos.length; k++) {
			panelJugDos[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					colorJugDos = COLOR_VIRUS[j];			
				}
			});
		}

		turnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				turnosJuego = eleccion == "Ilimitado" ? -1 : Integer.parseInt(eleccion);
			}
		});
		
		tiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				establecerTiempo(eleccion);
			}
		});
		
		jugadorInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				comienza = eleccion == "Jugador uno";
			}
		});
	}
	
	/**
	 * Prepara las acciones para la ventana de configuracion inicial del juego contra la maquina. 
	 */
	private void prepareAccionesVentanaConfInicialUno(){
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAceptarMaquina();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareElementosNuevo();
			}
		});
		
		for (k = 0; k < panelJugUno.length; k++) {
			panelJugUno[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					escogerColorMaquina(j);	
				}
			});
		}
		
		for (k = 0; k < MAQUINA.length; k++) {
			panelJugDos[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					tipoMaquina = MAQUINA[j];			
				}
			});
		}
		
		turnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				turnosJuego = eleccion == "Ilimitado" ? -1 : Integer.parseInt(eleccion);
			}
		});
		
		tiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				establecerTiempo(eleccion);
			}
		});
		
		jugadorInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String eleccion = (String)comboBox.getSelectedItem();
				comienza = eleccion == "Jugador uno";
			}
		});
	}
	
	/**
	 * Prepara las acciones de la ventana de configuracion del tablero. 
	 */
	private void prepareAccionesVentanaConfTablero(){
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkConfiguracionTablero();
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
		
		vacioPequeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = false;
					creaTablero(PEQUENO, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,(Object)e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		vacioMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = false;
					creaTablero(MEDIANO, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		vacioGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = false;
					creaTablero(GRANDE, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interrogantePequeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = true;
					creaTablero(PEQUENO, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interroganteMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = true;
					creaTablero(MEDIANO, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interroganteGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					neutrales = true;
					creaTablero(GRANDE, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Prepara las acciones de la ventana de juego
	 */
	private void prepareAccionesJuego(){
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					archivoNuevo();
				}catch (Exception ev){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,ev.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		exportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					archivoNuevoExportar();
				}catch (PoobthogenExcepcion ev){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,ev.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salga();
			}
		});
		
		for(k = 0; k < panelJugUno.length; k++) {
			panelJugUno[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					try{
						preparePanelJugUno(j);
					}catch(PoobthogenExcepcion e){
						JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		
		for(k = 0; k < panelJugDos.length; k++) {
			panelJugDos[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					try{
						preparePanelJugDos(j);
					}catch(PoobthogenExcepcion e){
						JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		
		pasaTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refrescaTurnos();
			}
		});
		
		rendirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ganador = juego.rendirse() == true ? jugadorUno : jugadorDos;
				timer.stop();
				prepareElementosGanadorJuego();
			}
		});
		
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reinicia();
				} catch (PoobthogenExcepcion ev) {
					JOptionPane.showMessageDialog(PoobthogenGUI.this, ev.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Inicia la cuenta del cronometro
	 */
	private void inicia(){
		timer.stop();
		timer.start();
	}
	
	/**
	 * Reinicia los estados del juego.
	 * @throws PoobthogenExcepcion
	 */
	private void reinicia() throws PoobthogenExcepcion{
		int filas = juego.filas();
		int columnas = juego.columnas();
		juego = new Tablero(filas, columnas, neutrales);
		tiempoJuego = tiempoIncial;
		inicia();
		turnosJuego = iniciales;
		refresque();
		refresqueProporciones();
		refrescaTurnos();
	}
	/**
	 * Prepara las acciones de los botones al jugar. 
	 */
	private void prepareAccionesBotones(){
		for (k = 0; k < juego.filas(); k++) {
			for (k1 = 0; k1 < juego.columnas(); k1++) {
				fichasJuego[k][k1].addActionListener(new ActionListener() {
					final int j = k1;
					final int i = k;
					public void actionPerformed(ActionEvent arg0) {
						try{
							boolean gana = jugar(i, j);
							if(!gana && turnosJuego != 0){
								refresque();
								turnosJuego--;
								refresqueTurnos(contenedor);
							}else{
								int ganadorJuego = juego.determinaGanador(); 
								ganador = ganadorJuego == 1 ? jugadorUno : ganadorJuego == -1 ?jugadorDos : null;
								prepareElementosGanadorJuego();
							}
						}catch (PoobthogenExcepcion e){
							refresque();
							JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
		}
	}
	
	/**
	 * Revisa si se puede avanzar a la ventana del juego. 
	 */
	private void checkAceptar(){
		if(colorJugDos == null || colorJugUno == null){
			JOptionPane.showMessageDialog(this, "Escoja un color de ficha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else if(colorJugDos == colorJugUno){
			JOptionPane.showMessageDialog(this,  "Los colores de los jugadores deben ser diferentes","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			juegoPvP = true;
			if(juego == null){
				prepareElementosConfiguracionTablero();
			}else{
				prepareElementosVentanaJuego();
			}	
		}
	}
	
	/**
	 * Establece el tiempo del juego segun la opcion del usuario. 
	 * @param eleccion Eleccion del usuario. 
	 */
	private void establecerTiempo(String eleccion){
		if(eleccion=="Ilimitado"){
			tiempoJuego = -1;
		}else{
			String[] temporal = eleccion.split(" ");
			tiempoJuego = Integer.parseInt(temporal[0])*60;
		}
	}
	
	/**
	 * Revisa si se puede avanzar a la ventana de configuracion del tablero en el juego contra la maquina. 
	 */
	private void checkAceptarMaquina(){
		if(colorJugDos == null || colorJugUno == null){
			JOptionPane.showMessageDialog(this, "Escoja un color de ficha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else if(tipoMaquina == null){
			JOptionPane.showMessageDialog(this,  "Debe escoger una dificultad","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			juegoPvP = false;
			prepareElementosConfiguracionTablero();
		}	
	}
	
	/**
	 * Escoge el color de ficha de la maquina. 
	 * @param j Posicion en x del boton
	 */
	private void escogerColorMaquina(int j){
		colorJugUno = COLOR_VIRUS[j];		
		Random r = new Random();
		colorJugDos = COLOR_VIRUS[r.nextInt(3)];
		while (colorJugDos == colorJugUno){
			colorJugDos = COLOR_VIRUS[r.nextInt(3)];
		}
	}
	
	/**
	 * Crea el tablero del juego con las dimensiones establecidas y las fichas neutrales si fue escogido por el usuario. 
	 * @param dimension Arreglo con las dimensiones establecidas
	 * @param neutrales Si lleva fichas neutrales o no
	 * @throws PoobthogenExcepcion
	 */
	private void creaTablero(int[] dimension, boolean neutrales) throws PoobthogenExcepcion{
		juego = new Tablero(dimension[0], dimension[1], neutrales);
		jugadorUno = new Jugador('1', juego);
		System.out.println(juegoPvP);
		if(juegoPvP){
			jugadorDos = new Jugador('2', juego);
		}else{
			jugadorDos = new Irreflexiva('2', juego);
		}
		juego.agregaJugador(jugadorUno);
		juego.agregaJugador(jugadorDos);
	}
	
	/**
	 * Revisa si puede avanzar a la ventana del juego.  
	 */
	private void checkConfiguracionTablero(){
		if(juego == null){
			JOptionPane.showMessageDialog(PoobthogenGUI.this,"Debe escoger una configuracion de tablero", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{
			prepareElementosVentanaJuego();
		}
	}
	
	/**
	 * Cambia los turnos del juego cada vez que un jugador realiza un movimiento cuando los turnos acaban, se pasa a la tabla de estadisticas.
	 */
	private void refrescaTurnos(){
		turnosJuego--;
		if(turnosJuego !=0){
			juego.cambiarTurno();
			refresqueTurnos(contenedor);
			refresque();
		}else{
			int ganadorJuego = juego.determinaGanador(); 
			ganador = ganadorJuego == 1 ? jugadorUno : ganadorJuego == -1 ?jugadorDos : null;
			prepareElementosGanadorJuego();
		}
	}
	
	/**
	 * Realiza una jugada si es posible
	 * @param i Posicion en i del tablero
	 * @param j Posicion en j del tablero
	 * @return Verdadero si el juego ha acabado, falso en caso contrario. 
	 * @throws PoobthogenExcepcion
	 */
	private boolean jugar(int i, int j) throws PoobthogenExcepcion{
		boolean turno = juego.getTurno();
		Jugador actual = turno == true ? jugadorUno : jugadorDos;
		boolean gana = false;
		try{
			gana = actual.juega(i, j, opcionVirus); 
 			if(!gana && turnosJuego != 0){
				juego.cambiarTurno();
				refresqueProporciones();
				if(!juego.getTurno() && !juegoPvP){
					jugar(0,0);
				}
			}else{
				int ganadorJuego = juego.determinaGanador();
				timer.stop();
				ganador = ganadorJuego == 1 ? jugadorUno : ganadorJuego == -1 ?jugadorDos : null;
				prepareElementosGanadorJuego();
			}
		}catch (PoobthogenExcepcion e){
			throw e;
		}
		return gana;
	}
	
	/**
	 * Crea el cronometro de tiempo. 
	 * @param contenedor Contenedor en el cual se incluira
	 */
	private void contadorTiempo(final JPanel contenedor){
		timer  = new Timer(ONE_SECOND, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tiempoJuego != 0){
					tiempoJuego--;
					refresqueTiempo(contenedor);
				}else{
					timer.stop();
					int ganadorJuego = juego.determinaGanador(); 
					ganador = ganadorJuego == 1 ? jugadorUno : ganadorJuego == -1 ?jugadorDos : null;
					prepareElementosGanadorJuego();
				}
			}
		});
	}
	
	/**
	 * Refresca el texto del contador. 
	 * @param contenedor Contenedor en el que se incluye. 
	 */
	private void refresqueTiempo(JPanel contenedor){
		contenedor.updateUI();
		muestraTiempo.setText(tiempoJuego <= -1 ? "Ilimitado" : tiempoJuego+"");
	}
	
	/**
	 * Refresca el texto de los turnos. 
	 * @param contenedor Contenedor en el que se incluye. 
	 */
	private void refresqueTurnos(JPanel contenedor){
		contenedor.updateUI();
		muestraTurnos.setText(turnosJuego <= -1 ? "Ilimitado" : turnosJuego+"");
	}
	
	/** 
	 * Refresca los textos de las proporciones de cada jugador
	 */
	private void refresqueProporciones(){
		menuDos.updateUI();
		menuUno.updateUI();
		int[] proporciones = juego.proporciones();
		proporcionUno.setText(proporciones[0]+"%");
		proporcionDos.setText(proporciones[1]+"%");
	}
	
	/**
	 * Refresca el color del borde segun el turno. 
	 */
	private void refresqueBorde(){
		boolean turno = juego.getTurno();
		Border temp = new CompoundBorder(new LineBorder(colores.get(turno == true ? colorJugUno : colorJugDos), 10), new LineBorder(new Color(0f,0f,0f,0f), 30));
		Border b = new CompoundBorder(new LineBorder(new Color(0f,0f,0f,0f), 50), temp);
		tableroJuego.setBorder(b);
	}
	
	/**
	 * Refresca el tablero con la ultima jugada. 
	 */
	private void refresque(){
		refresqueBorde();
		tableroJuego.removeAll();
		tableroJuego.updateUI();
		int filas = juego.filas();
		int columnas = juego.columnas();
		tableroJuego.setLayout(new GridLayout(filas, columnas));
		fichasJuego = new JButton[filas][columnas];
		Border bordeFicha = new LineBorder(new Color(0f,0f,0f,0f), 30);
		JButton t; 
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++) {
				t = new JButton(new ImageIcon(new ImageIcon(getClass().getResource(rutaVirus(juego.getElemento(i, j)))).getImage()));
				fichasJuego[i][j] = t;
				t.setOpaque(true);
				t.setBackground(Color.BLACK);
				t.setBorder(bordeFicha);
				tableroJuego.add(t);
			}
		}
		prepareAccionesBotones();
		principal.add(tableroJuego, BorderLayout.CENTER);
	}
	
	/**
	 * Crea la ruta para buscar la imagen asociada al virus. 
	 * @param v Virus al cual esta asociada la imagen. 
	 * @return Una cadena con la ruta de la carpeta del virus. 
	 */
	private String rutaVirus(Virus v){
		String ruta;
		if(v!=null && v.esDeTipo() != null){
			
			String[] tipo = v.esDeTipo().split(" ");
			ruta = "/Presentacion/"+(tipo[1].equals("1")?colorJugUno : tipo[1].equals("2") ? colorJugDos : "neutral")+"/"+tipo[0]+".png"; 
		}else{
			ruta = "/Presentacion/neutral/fichaNula.png"; 
		}
		return ruta;
	}
	
	/**
	 * Crea el menu de virus para el jugador. 
	 * @param j Panel en el que se incluyen
	 * @param opcion Color que eligio el jugador. 
	 * @return
	 */
	private JButton[] menuJugador(JPanel j, String opcion){
		JButton[] arreglo = new JButton[4];
		arreglo[0] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelUno1.png")).getImage()));
		arreglo[0].setBackground(Color.BLACK);
		arreglo[0].setOpaque(false);
		arreglo[1] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelDos1.png")).getImage()));
		arreglo[1].setBackground(Color.BLACK);
		arreglo[1].setOpaque(false);
		arreglo[2] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelTres1.png")).getImage()));
		arreglo[2].setBackground(Color.BLACK);
		arreglo[2].setOpaque(false);
		arreglo[3] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/Destructor.png")).getImage()));
		arreglo[3].setBackground(Color.BLACK);
		arreglo[3].setOpaque(false);
		for (JButton b : arreglo){
			j.add(b);
		}
		return arreglo;
	}
	
	private JPanel preparePanelTurnosTiempo(){
		JPanel endOfWin = prepareContenedor(new JPanel(),"", false);
		endOfWin.setLayout(new GridLayout(1, 2));
		JPanel turnosTiempo = prepareContenedor(new JPanel(),"", false);
		turnosTiempo.setLayout(new GridLayout(3, 1));
		JPanel turnos =  prepareContenedor(new JPanel(),"", false);
		turnos.setLayout(new BorderLayout());
		turnos.add(prepareTextosBorder("Turnos"), BorderLayout.PAGE_START);
		this.turnos = new JComboBox(CANT_TURNOS);
		turnos.add(this.turnos, BorderLayout.CENTER);
		turnosTiempo.add(turnos);
		JPanel tiempo =  prepareContenedor(new JPanel(),"", false);
		tiempo.setLayout(new BorderLayout());
		tiempo.add(prepareTextosBorder("Tiempo"), BorderLayout.PAGE_START);
		this.tiempo = new JComboBox(CANT_TIEMPO);
		tiempo.add(this.tiempo, BorderLayout.CENTER);
		turnosTiempo.add(tiempo);
		JPanel jugador =  prepareContenedor(new JPanel(),"", false);
		jugador.setLayout(new BorderLayout());
		jugador.add(prepareTextosBorder("Jugador inicial"), BorderLayout.PAGE_START);
		jugadorInicial = new JComboBox(JUGADORES);
		jugador.add(jugadorInicial, BorderLayout.CENTER);
		turnosTiempo.add(jugador);
		endOfWin.add(turnosTiempo);
		return endOfWin;
	}
	
	/**
	 *Prepara el panel de escoger el color de ficha. 
	 */
	private void preparePanelEscogerFicha(){
		fichasPreJuego = prepareContenedor(new JPanel(), "", true);
		fichasPreJuego.setLayout(new BorderLayout());
		fichasPreJuego.add(prepareTextosBorder("Jugadores"), BorderLayout.NORTH);
		JPanel contenedor = prepareContenedor(new JPanel(),"", false);
		contenedor.setLayout(new GridLayout(1, 2));
		prepareContenedoresFichaUno(contenedor);
		prepareContenedoresFichaDos(contenedor);
		fichasPreJuego.add(contenedor);
		principal.add(fichasPreJuego);
	}
	
	/**
	 *Prepara el panel de escoger el color de ficha para el pve.  
	 */
	private void preparePanelEscogerFichaUno(){
		fichasPreJuego = prepareContenedor(new JPanel(), "", true);
		fichasPreJuego.setLayout(new BorderLayout());
		fichasPreJuego.add(prepareTextosBorder("Jugadores"), BorderLayout.NORTH);
		JPanel contenedor = prepareContenedor(new JPanel(),"", false);
		contenedor.setLayout(new GridLayout(1, 1));
		prepareContenedoresFichaUno(contenedor);
		prepareContenedoresFichaDosMaquina(contenedor);
		fichasPreJuego.add(contenedor);
		principal.add(fichasPreJuego);
	}
	
	/**
	 * Prepara el diseño de la seccion de escoger el color de ficha del jugador uno. 
	 * @param contenedor
	 */
	private void prepareContenedoresFichaUno(JPanel contenedor){
		JPanel fichasUno = prepareContenedor(new JPanel(),"", false);
		fichasUno.setLayout(new BorderLayout());
		fichasUno.add(prepareTextosBorder("Jugador 1"), BorderLayout.NORTH);
		jugadorUnoFichas = prepareContenedor(new JPanel(), "", true);
		jugadorUnoFichas.setLayout(new GridLayout(1, 4));
		panelJugUno = prepareBotonesFichas(jugadorUnoFichas, panelJugUno);
		fichasUno.add(jugadorUnoFichas, BorderLayout.CENTER);
		contenedor.add(fichasUno);
	}
	
	/**
	 * Prepara el diseño de la seccion de escoger el color de ficha del jugador dos. 
	 * @param contenedor
	 */
	private void prepareContenedoresFichaDos(JPanel contenedor){
		JPanel fichasDos = prepareContenedor(new JPanel(),"", false);
		fichasDos.setLayout(new BorderLayout());
		fichasDos.add(prepareTextosBorder("Jugador 2"), BorderLayout.NORTH);
		jugadorDosFichas = prepareContenedor(new JPanel(),"", true);
		jugadorDosFichas.setLayout(new GridLayout(1, 4));
		panelJugDos = prepareBotonesFichas(jugadorDosFichas, panelJugDos);
		fichasDos.add(jugadorDosFichas, BorderLayout.CENTER);
		contenedor.add(fichasDos);
	}
	
	/**
	 * Prepara el diseño de la seccion de escoger el tipo de maquina
	 * @param contenedor
	 */
	private void prepareContenedoresFichaDosMaquina(JPanel contenedor){
		JPanel fichasDos = prepareContenedor(new JPanel(),"", false);
		fichasDos.setLayout(new BorderLayout());
		fichasDos.add(prepareTextosBorder("Jugador 2"), BorderLayout.NORTH);
		jugadorDosFichas = prepareContenedor(new JPanel(), "", true);
		jugadorDosFichas.setLayout(new GridLayout(1, 4));
		panelJugDos = prepareMaquinaFichas(jugadorDosFichas, panelJugDos);
		fichasDos.add(jugadorDosFichas, BorderLayout.CENTER);
		contenedor.add(fichasDos);
	}
	
	/**
	 * Prepara un Jlabel con configuraciones por defecto
	 * @param texto Que se quiere incluir 
	 * @return El Label con las configuraciones. 
	 */
	private JLabel prepareTextosBorder(String texto){
		JLabel text1 = new JLabel(texto);
		text1.setForeground(Color.WHITE);
		text1.setFont(f);
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		return text1;
	}
	
	/**
	 * Prepara un contenedor con configuraciones por defecto. 
	 * @param j Panel 
	 * @param mensaje Mensaje que tiene el borde. 
	 * @param borde Si se le quiere agregar borde o no. 
	 * @return Contenedor con configuraciones por defecto. 
	 */
	private JPanel prepareContenedor(JPanel j, String mensaje, boolean borde){
		if (borde){
			j.setBorder(new CompoundBorder( new EmptyBorder(1, 1, 1, 1), new TitledBorder(mensaje)));
		}
		j.setBackground(Color.BLACK);
		j.setOpaque(false);
		j.setLayout(new GridLayout(1, 4));
		j.setForeground(Color.WHITE);
		j.setFont(f);
		return j;
	}
	
	/**
	 * Prepara el diseño del menu de escoger tablero. 
	 */
	private void prepareMenuTableros(){
		tableros = new JTabbedPane();
		tableros.setFont(f);
		f = new Font("Century Gothic", Font.PLAIN, 50);
		tableros.addTab("Pequeno", prepareAreaPequeno("/Presentacion/imagenes/pequeno.png","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Mediano", prepareAreaMediano("/Presentacion/imagenes/mediano.png","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Grande", prepareAreaGrande("/Presentacion/imagenes/grande.png", "/Presentacion/imagenes/interrogante.png"));
		principal.add(tableros);
		f = new Font("Century Gothic", Font.PLAIN, 20);
	}
	
	/**
	 * Prepara el diseño del panel de tablero pequeno
	 * @param ruta1 Imagen tablero vacio
	 * @param ruta2 Imagen tablero random
	 * @return Un panel con la configuracion
	 */
	private JPanel prepareAreaPequeno(String ruta1, String ruta2){
		areaPequeno = new JPanel();
		prepareContenedor(areaPequeno, "", false);
		areaPequeno.setOpaque(false);
		vacioPequeno = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioPequeno.setBackground(Color.BLACK);
		vacioPequeno.setOpaque(false);
		areaPequeno.add(vacioPequeno); 
		interrogantePequeno = new RoundButton("?");
		interrogantePequeno.setFont(f);
		interrogantePequeno.setBackground(Color.BLACK);
		interrogantePequeno.setOpaque(false);
		areaPequeno.add(interrogantePequeno);
		return areaPequeno;
	}
	

	/**
	 * Prepara el diseño del panel de tablero mediano
	 * @param ruta1 Imagen tablero vacio
	 * @param ruta2 Imagen tablero random
	 * @return Un panel con la configuracion
	 */
	private JPanel prepareAreaMediano(String ruta1, String ruta2){
		areaMediano = new JPanel();
		prepareContenedor(areaMediano, "", false);
		areaMediano.setOpaque(false);
		vacioMediano = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioMediano.setBackground(Color.BLACK);
		vacioMediano.setOpaque(false);
		areaMediano.add(vacioMediano); 
		interroganteMediano = new RoundButton("?");
		interroganteMediano.setFont(f);
		interroganteMediano.setBackground(Color.BLACK);
		interroganteMediano.setOpaque(false);
		areaMediano.add(interroganteMediano);
		return areaMediano;
	}
	

	/**
	 * Prepara el diseño del panel de tablero grande
	 * @param ruta1 Imagen tablero vacio
	 * @param ruta2 Imagen tablero random
	 * @return Un panel con la configuracion
	 */
	private JPanel prepareAreaGrande(String ruta1, String ruta2){
		areaGrande = new JPanel();
		prepareContenedor(areaGrande, "", false);
		areaGrande.setOpaque(false);
		vacioGrande = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioGrande.setBackground(Color.BLACK);
		vacioGrande.setOpaque(false);
		areaGrande.add(vacioGrande); 
		interroganteGrande = new RoundButton("?");
		interroganteGrande.setFont(f);
		interroganteGrande.setBackground(Color.BLACK);
		interroganteGrande.setOpaque(false);
		areaGrande.add(interroganteGrande);
		return areaGrande;
	}
	
	/**
	 * Prepara los botones para escoger color de ficha
	 * @param j Panel que lo incluye
	 * @param arreglo Arreglo al que se le anaden los botones 
	 * @return El arreglo de botones configurados
	 */
	private JButton[] prepareBotonesFichas(JPanel j, JButton[] arreglo){
		arreglo = new JButton[4];
		arreglo[0] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/verde/NivelUno1.png")).getImage()));
		arreglo[0].setBackground(Color.BLACK);
		arreglo[0].setOpaque(false);
		arreglo[1] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/roja/NivelUno1.png")).getImage()));
		arreglo[1].setBackground(Color.BLACK);
		arreglo[1].setOpaque(false);
		arreglo[2] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/amarilla/NivelUno1.png")).getImage()));
		arreglo[2].setBackground(Color.BLACK);
		arreglo[2].setOpaque(false);
		arreglo[3] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/azul/NivelUno1.png")).getImage()));
		arreglo[3].setBackground(Color.BLACK);
		arreglo[3].setOpaque(false);
		for (JButton b : arreglo){
			j.add(b);
		}
		return arreglo;
	}
	
	/**
	 * Prepara los botones para escoger tipo de maquina
	 * @param j Panel que lo incluye
	 * @param arreglo Arreglo al que se le anaden los botones 
	 * @return El arreglo de botones configurados
	 */
	private JButton[] prepareMaquinaFichas(JPanel j, JButton[] arreglo){
		f = new Font("Century Gothic", Font.PLAIN, 12);
		arreglo = new JButton[3];
		arreglo[0] = new RoundButton("Timida");
		arreglo[0].setBackground(Color.BLACK);
		arreglo[0].setOpaque(false);
		arreglo[0].setFont(f);
		arreglo[0].setForeground(Color.WHITE);
		arreglo[1] = new RoundButton("Ofensiva");
		arreglo[1].setBackground(Color.BLACK);
		arreglo[1].setOpaque(false);
		arreglo[1].setFont(f);
		arreglo[1].setForeground(Color.WHITE);
		arreglo[2] = new RoundButton("Irreflexiva");
		arreglo[2].setBackground(Color.BLACK);
		arreglo[2].setOpaque(false);
		arreglo[2].setFont(f);
		arreglo[2].setForeground(Color.WHITE);
		for (JButton b : arreglo){
			j.add(b);
		}
		f = new Font("Century Gothic", Font.PLAIN, 20);
		return arreglo;
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
		
		dosJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareElementosPreJuego();
			}
		});
		
		unJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareElementosPreJuegoUno();
			}
		});
		
	}
	
	/**
	 * Muestra los creditos del juego
	 */
	private void muestreCreditos(){
		String mensaje = "Juego creado por:\nMaria Alejandra Blanco Uribe y Nicolas Gomez Solano\n"+new Date().getDate()+":"+(new Date().getMonth()+1)+":"+(new Date().getYear()+1900)+"\n"
				+"POOB 2014-2";
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
		if (juego != null){
			prepareElementosPreJuego();
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
	 *Crea un nuevo archivo para salvar mostrando una ventana para crearlo. 
	 *@throws E8oMasExcepcion
	 */
	private void archivoNuevoExportar() throws PoobthogenExcepcion{
		JFileChooser fileChooser = new JFileChooser(); 
		int returnV = fileChooser.showSaveDialog(this);
		File e = fileChooser.getSelectedFile();
		if (returnV == fileChooser.APPROVE_OPTION){
			PoobthogenArchivos.exportar(e, juego); 
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
			jugadorUno = juego.getJugador(0);
			jugadorDos = juego.getJugador(1);
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
			jugadorUno = juego.getJugador(0);
			jugadorDos = juego.getJugador(1);
		}
	}
	
	/**
	 * Termina con la aplicacion. 
	 */
	private void salga(){
    	Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(this,"En realidad desea cerrar la aplicacion","Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(this, "Aplicacion terminada satisfactoriamente");
			dispose(); 
    		System.exit(0);
    	}
    }
	
	/**
	 * Termina con la aplicacion. 
	 */
	private void salgaError(){
		JOptionPane.showMessageDialog(PoobthogenGUI.this, "Se ha generado un error inesperado, la aplicacion se cerrará");
		dispose(); 
    	System.exit(0);
    }
}
