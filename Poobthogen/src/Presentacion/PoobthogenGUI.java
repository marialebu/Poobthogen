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
	
	private int ONE_SECOND;
	
	private Tablero juego;
	private boolean tipoDeJuego;
	private Jugador jugadorUno;
	private Jugador jugadorDos;
	private HashMap<String, Color> colores;
	private JButton[] panelJugUno;
	private JButton[] panelJugDos;
	private final String[] cantTurnos = {"10", "15", "20", "30", "Ilimitado"};
	private final String[] cantTiempo = {"5 minutos", "10 minutos", "20 minutos", "Ilimitado"};
	private final int[] pequeno = {6, 6};
	private final int[] mediano = {8, 8};
	private final int[] grande = {10,10};
	private final String[] colorVirus = {"verde", "roja", "amarilla", "azul", "neutral"};
	private String colorJugUno;
	private String colorJugDos;
	private int turnosJuego = 10;
	private int tiempoJuego = 300;
	private String tipoMaquina;
	private final String[] maquina = {"Timida", "Ofensiva", "Irreflexiva"};
	private JButton[][] fichasJuego;
	private String opcionVirus;
	private String[] tiposVirus = {"NivelUno", "NivelDos", "NivelTres", "Destructor"};
	
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
	private JPanel turnosTiempo;
	private JPanel areaPequeno;
	private JPanel areaMediano;
	private JPanel areaGrande;
	private JButton aceptar;
	private JButton cancelar;
	private JComboBox turnos;
	private JComboBox tiempo;
	
	private JMenuItem guardar;
	private JMenuItem exportar;
	private JMenuItem reiniciar;
	private JMenuItem salir;
	private JLabel muestraTurnos;
	private JLabel muestraTiempo;
	private JPanel tablero;
	private JButton[] fichas;
	private JPanel tableroJuego;
	private Timer timer;
	private JPanel contenedor;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PoobthogenGUI juego  = new PoobthogenGUI();
		juego.setVisible(true);
	}
	
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		preparePantalla();
		f = new Font("Century Gothic", Font.PLAIN, 20);
		prepareElementosInicio();	
		setResizable(false);
		colores = new HashMap<String, Color>();
		inicializaColores();
		ONE_SECOND = 1000;
	}
	
	private void inicializaColores(){
		colores.put("amarilla", new Color(221,172,69));
		colores.put("verde", new Color(100,203,176));
		colores.put("roja", new Color(225,65,129));
		colores.put("azul", new Color(91,104,206));	
	}
	
	/**
	 * 
	 */
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
        int x = (screen.width - getSize().width) / 2+50; 
        int y = (screen.height - getSize().height) / 2+100; 
        setSize(x, y);
        setLocation(x/2-50, y/2-100);
        principal = new ImagenFondo("/Presentacion/imagenes/PoobthogenInicio.jpg");
        add(principal);
	}
	
	private void preparePantallaJuego(){
		Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screen.width - getSize().width/10; 
        int y = screen.height-getSize().height/10; 
        setSize(x, y);
        setLocation( getSize().width/40, 0);
	}
	
	private void prepareElementosPreJuego(){
		prepareVentanaConfiguracionInicial();
		prepareAccionesVentanaConfInicial();
	}
	
	private void prepareElementosPreJuegoUno(){
		prepareVentanaConfiguracionInicialUno();
		prepareAccionesVentanaConfInicialUno();
	}
	
	private void prepareElementosConfiguracionTablero(){
		prepareVentanaConfiguracionTablero();
		prepareAccionesVentanaConfTablero();
	}
	
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
					colorJugUno = colorVirus[j];		
				}
			});
		}
		
		for (k = 0; k < panelJugDos.length; k++) {
			panelJugDos[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					colorJugDos = colorVirus[j];			
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
	}
	
	private void checkAceptar(){
		if(colorJugDos == null || colorJugUno == null){
			JOptionPane.showMessageDialog(this, "Escoja un color de ficha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else if(colorJugDos == colorJugUno){
			JOptionPane.showMessageDialog(this,  "Los colores de los jugadores deben ser diferentes","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			if(juego == null){
				prepareElementosConfiguracionTablero();
			}else{
				prepareElementosVentanaJuego();
			}	
		}
	}
	
	private void preparePanelJugUno(int j) throws PoobthogenExcepcion{
		if(juego.getTurno()){
			opcionVirus = tiposVirus[j]; 	
		}else{
			throw new PoobthogenExcepcion(PoobthogenExcepcion.SELECCION_INVALIDA);
		}
	}
	
	private void preparePanelJugDos(int j) throws PoobthogenExcepcion{
		if(!juego.getTurno()){
			opcionVirus = tiposVirus[j]; 	
		}else{
			throw new PoobthogenExcepcion(PoobthogenExcepcion.SELECCION_INVALIDA);
		}
	}
	
	private void establecerTiempo(String eleccion){
		if(eleccion=="Ilimitado"){
			tiempoJuego = -1;
		}else{
			String[] temporal = eleccion.split(" ");
			tiempoJuego = Integer.parseInt(temporal[0])*60;
		}
	}
	
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
		
		for (k = 0; k < maquina.length; k++) {
			panelJugDos[k].addActionListener(new ActionListener() {
				final int j = k;
				public void actionPerformed(ActionEvent arg0) {
					tipoMaquina = maquina[j];			
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
	}
	
	private void checkAceptarMaquina(){
		if(colorJugDos == null || colorJugUno == null){
			JOptionPane.showMessageDialog(this, "Escoja un color de ficha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else if(tipoMaquina == null){
			JOptionPane.showMessageDialog(this,  "Debe escoger una dificultad","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			prepareElementosConfiguracionTablero();
		}	
	}
	
	private void escogerColorMaquina(int j){
		colorJugUno = colorVirus[j];		
		Random r = new Random();
		colorJugDos = colorVirus[r.nextInt(3)];
		while (colorJugDos == colorJugUno){
			colorJugDos = colorVirus[r.nextInt(3)];
		}
	}
	private void prepareVentanaConfiguracionTablero(){
		principal.removeAll();
		principal.updateUI();
		Dimension tam = this.getContentPane().getSize();
		principal.setLayout(new BorderLayout());
		principal.add(prepareTextosBorder("Configuraci�n de tablero"), BorderLayout.PAGE_START);
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
					creaTablero(pequeno, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,(Object)e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		vacioMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					creaTablero(mediano, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		vacioGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					creaTablero(grande, false);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interrogantePequeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					creaTablero(pequeno, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interroganteMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					creaTablero(mediano, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		interroganteGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					creaTablero(grande, true);
				}catch(PoobthogenExcepcion e){
					JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void creaTablero(int[] dimension, boolean neutrales) throws PoobthogenExcepcion{
		juego = new Tablero(dimension[0], dimension[1], neutrales);
		jugadorUno = new Jugador('1', juego);
		jugadorDos = new Jugador('2', juego);
		juego.agregaJugador(jugadorUno);
		juego.agregaJugador(jugadorDos);
	}
	
	private void checkConfiguracionTablero(){
		if(juego == null){
			JOptionPane.showMessageDialog(PoobthogenGUI.this,"Debe escoger una configuracion de tablero", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{
			prepareElementosVentanaJuego();
		}
	}
	
	private void prepareElementosVentanaJuego(){
		prepareVentanaJuego();
		preparePantallaJuego();
		prepareAccionesJuego();
	}
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
	}
	
	private void jugar(int i, int j) throws PoobthogenExcepcion{
		try{
			boolean turno = juego.getTurno();
			Jugador actual = turno == true ? jugadorUno : jugadorDos;
			if(actual.juega(i, j, opcionVirus)){
				juego.cambiarTurno();
				juego.imprimir();
			}
		}catch (PoobthogenExcepcion e){
			throw e;
		}
	}
	
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
	
	private void prepareContenedoresJuego(){
		contenedor = prepareContenedor(new JPanel(),"", false); 
		contenedor.setLayout(new GridLayout(1, 4));
		contenedor.add(prepareTextosBorder("Turnos: "));
		muestraTurnos = prepareTextosBorder(turnosJuego == -1 ? "Ilimitado" : turnosJuego+"");
		contenedor.add(muestraTurnos);
		contenedor.add(prepareTextosBorder("Tiempo: "));
		muestraTiempo = prepareTextosBorder(tiempoJuego >= 0 ? "Ilimitado" : tiempoJuego+"");
		contenedor.add(muestraTiempo);
		contadorTiempo(contenedor);
		timer.start();
		principal.add(contenedor, BorderLayout.PAGE_START);
		prepareMenuJugador();
		tableroJuego = prepareContenedor(new JPanel(), "", false);
		refresqueBorde();
		refresque();
	}
	
	private void contadorTiempo(final JPanel contenedor){
		timer  = new Timer(ONE_SECOND, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tiempoJuego > 0){
					tiempoJuego--;
					refresqueTiempo(contenedor);
				}else{
					timer.stop();
				}
			}
		});
	}
	
	private void refresqueTiempo(JPanel contenedor){
		contenedor.updateUI();
		muestraTiempo.setText(tiempoJuego <= 0 ? "Ilimitado" : tiempoJuego+"");
	}
	
	private void refresqueTurnos(JPanel contenedor){
		contenedor.updateUI();
		muestraTurnos.setText(turnosJuego <= -1 ? "Ilimitado" : turnosJuego+"");
	}
	
	private void prepareAccionesBotones(){
		for (k = 0; k < juego.filas(); k++) {
			for (k1 = 0; k1 < juego.columnas(); k1++) {
				fichasJuego[k][k1].addActionListener(new ActionListener() {
					final int j = k1;
					final int i = k;
					public void actionPerformed(ActionEvent arg0) {
						try{
							jugar(i, j);
							turnosJuego--;
							refresqueTurnos(contenedor);
						}catch (PoobthogenExcepcion e){
							JOptionPane.showMessageDialog(PoobthogenGUI.this,e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						refresque();
					}
				});
			}
		}
	}
	
	private void prepareMenuJugador(){
		JPanel menuUno = prepareContenedor(new JPanel(),"", false);
		menuUno.setLayout(new GridLayout(4, 1));
		panelJugUno = menuJugador(menuUno, colorJugUno);
		principal.add(menuUno, BorderLayout.WEST);
		JPanel menuDos = prepareContenedor(new JPanel(),"", false);
		menuDos.setLayout(new GridLayout(4, 1));
		panelJugDos = menuJugador(menuDos, colorJugDos);
		principal.add(menuDos, BorderLayout.EAST);
	}
	
	private void refresqueBorde(){
		boolean turno = juego.getTurno();
		Border temp = new CompoundBorder(new LineBorder(colores.get(turno == true ? colorJugUno : colorJugDos), 10), new LineBorder(new Color(0f,0f,0f,0f), 30));
		Border b = new CompoundBorder(new LineBorder(new Color(0f,0f,0f,0f), 50), temp);
		tableroJuego.setBorder(b);
	}
	
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
				t.setBackground(new Color(0f,0f,0f,0f));
				t.setBorder(bordeFicha);
				tableroJuego.add(t);
			}
		}
		prepareAccionesBotones();
		principal.add(tableroJuego, BorderLayout.CENTER);
	}
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
	
	private JButton[] menuJugador(JPanel j, String opcion){
		JButton[] arreglo = new JButton[4];
		arreglo[0] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelUno.png")).getImage()));
		arreglo[0].setBackground(Color.BLACK);
		arreglo[0].setOpaque(false);
		arreglo[1] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelDos.png")).getImage()));
		arreglo[1].setBackground(Color.BLACK);
		arreglo[1].setOpaque(false);
		arreglo[2] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/"+opcion+"/NivelTres.png")).getImage()));
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
	
	private JPanel preparePanelTurnosTiempo(){
		JPanel endOfWin = prepareContenedor(new JPanel(),"", false);
		endOfWin.setLayout(new GridLayout(1, 2));
		JPanel turnosTiempo = prepareContenedor(new JPanel(),"", false);
		turnosTiempo.setLayout(new GridLayout(2, 1));
		JPanel turnos =  prepareContenedor(new JPanel(),"", false);
		turnos.setLayout(new BorderLayout());
		turnos.add(prepareTextosBorder("Turnos"), BorderLayout.PAGE_START);
		this.turnos = new JComboBox(cantTurnos);
		turnos.add(this.turnos, BorderLayout.CENTER);
		turnosTiempo.add(turnos);
		JPanel tiempo =  prepareContenedor(new JPanel(),"", false);
		tiempo.setLayout(new BorderLayout());
		tiempo.add(prepareTextosBorder("Tiempo"), BorderLayout.PAGE_START);
		this.tiempo = new JComboBox(cantTiempo);
		tiempo.add(this.tiempo, BorderLayout.CENTER);
		turnosTiempo.add(tiempo);
		endOfWin.add(turnosTiempo);
		return endOfWin;
	}
	
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
	
	private JLabel prepareTextosBorder(String texto){
		JLabel text1 = new JLabel(texto);
		text1.setForeground(Color.WHITE);
		text1.setFont(f);
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		return text1;
	}
	
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
	
	private void prepareMenuTableros(){
		tableros = new JTabbedPane();
		tableros.setFont(f);
		tableros.addTab("Pequeno", prepareAreaPequeno("/Presentacion/imagenes/pathogenMap.jpg","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Mediano", prepareAreaMediano("/Presentacion/imagenes/pathogenMap.jpg","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Grande", prepareAreaGrande("/Presentacion/imagenes/pathogenMap.jpg", "/Presentacion/imagenes/interrogante.png"));
		principal.add(tableros);
	}
	
	private JPanel prepareAreaPequeno(String ruta1, String ruta2){
		areaPequeno = new JPanel();
		prepareContenedor(areaPequeno, "", false);
		areaPequeno.setOpaque(false);
		vacioPequeno = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioPequeno.setBackground(Color.BLACK);
		vacioPequeno.setOpaque(false);
		areaPequeno.add(vacioPequeno); 
		interrogantePequeno = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interrogantePequeno.setBackground(Color.BLACK);
		interrogantePequeno.setOpaque(false);
		areaPequeno.add(interrogantePequeno);
		return areaPequeno;
	}
	
	private JPanel prepareAreaMediano(String ruta1, String ruta2){
		areaMediano = new JPanel();
		prepareContenedor(areaMediano, "", false);
		areaMediano.setOpaque(false);
		vacioMediano = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioMediano.setBackground(Color.BLACK);
		vacioMediano.setOpaque(false);
		areaMediano.add(vacioMediano); 
		interroganteMediano = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interroganteMediano.setBackground(Color.BLACK);
		interroganteMediano.setOpaque(false);
		areaMediano.add(interroganteMediano);
		return areaMediano;
	}
	
	private JPanel prepareAreaGrande(String ruta1, String ruta2){
		areaGrande = new JPanel();
		prepareContenedor(areaGrande, "", false);
		areaGrande.setOpaque(false);
		vacioGrande = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacioGrande.setBackground(Color.BLACK);
		vacioGrande.setOpaque(false);
		areaGrande.add(vacioGrande); 
		interroganteGrande = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interroganteGrande.setBackground(Color.BLACK);
		interroganteGrande.setOpaque(false);
		areaGrande.add(interroganteGrande);
		return areaGrande;
	}
	
	private JButton[] prepareBotonesFichas(JPanel j, JButton[] arreglo){
		arreglo = new JButton[4];
		arreglo[0] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/verde/NivelUno.png")).getImage()));
		arreglo[0].setBackground(Color.BLACK);
		arreglo[0].setOpaque(false);
		arreglo[1] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/roja/NivelUno.png")).getImage()));
		arreglo[1].setBackground(Color.BLACK);
		arreglo[1].setOpaque(false);
		arreglo[2] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/amarilla/NivelUno.png")).getImage()));
		arreglo[2].setBackground(Color.BLACK);
		arreglo[2].setOpaque(false);
		arreglo[3] = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/azul/NivelUno.png")).getImage()));
		arreglo[3].setBackground(Color.BLACK);
		arreglo[3].setOpaque(false);
		for (JButton b : arreglo){
			j.add(b);
		}
		return arreglo;
	}
	
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
		String mensaje = "Bla bla bla y mas bla bla";
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
			dispose(); 
    		System.exit(0);
		}
    }
}
