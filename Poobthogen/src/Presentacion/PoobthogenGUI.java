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
	private final int[] pequeno = {6, 6};
	private final int[] mediano = {8, 8};
	private final int[] grande = {10,10};
	private final String[] colorVirus = {"verde", "roja", "amarilla", "azul"};
	private String colorJugUno;
	private String colorJugDos;
	private int turnosJuego = 10;
	private int tiempoJuego = 300;
	private String tipoMaquina;
	private final String[] maquina = {"Timida", "Ofensiva", "Irreflexiva"};
	
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
	private JButton vacio;
	private JButton interrogante;
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
	
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		preparePantalla();
		f = new Font("Century Gothic", Font.PLAIN, 20);
		prepareElementosInicio();	
		setResizable(false);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PoobthogenGUI juego  = new PoobthogenGUI();
		juego.setVisible(true);
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
			prepareElementosConfiguracionTablero();
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
				prepareElementosConfiguracionTablero();
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
		
		vacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = tableros.getSelectedIndex();
				System.out.println("jajaja");
				System.out.println(opcion);
			}
		});
		
		
		
		
		
		
		
		
		//Faltan acciones para guardar configuracion :D
	}
	
	private void prepareElementosVentanaJuego(){
		prepareVentanaJuego();
		preparePantallaJuego();
	}
	private void prepareVentanaJuego(){
		prepareMenu();
		remove(principal);
		JOptionPane.showMessageDialog(this, "Bienvenido a Poobthogen\nEl juego esta por comenzar.");
		principal = new ImagenFondo("/Presentacion/imagenes/fondoPoobthogen.jpg");
		add(principal);
		principal.setLayout(new BorderLayout());
		prepareContenedoresJuego();
	}
	
	private void prepareContenedoresJuego(){
		JPanel contenedor = prepareContenedor(new JPanel(), Color.BLACK, "", false); 
		contenedor.setLayout(new GridLayout(1, 4));
		contenedor.add(prepareTextosBorder("Turnos: "));
		muestraTurnos = prepareTextosBorder(turnosJuego == -1 ? "Ilimitado" : turnosJuego+"");
		contenedor.add(muestraTurnos);
		contenedor.add(prepareTextosBorder("Tiempo: "));
		muestraTiempo = prepareTextosBorder(tiempoJuego == -1 ? "Ilimitado" : tiempoJuego+"");
		contenedor.add(muestraTiempo);
		principal.add(contenedor, BorderLayout.PAGE_START);
		prepareMenuJugador();
	}
	
	private void prepareMenuJugador(){
		JPanel menuUno = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		menuUno.setLayout(new GridLayout(4, 1));
		panelJugUno = menuJugador(menuUno, colorJugUno);
		principal.add(menuUno, BorderLayout.WEST);
		JPanel menuDos = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		menuDos.setLayout(new GridLayout(4, 1));
		panelJugDos = menuJugador(menuDos, colorJugDos);
		principal.add(menuDos, BorderLayout.EAST);
	}
	
	private void refresque(){
		
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
	
	private void preparePanelEscogerFichaUno(){
		fichasPreJuego = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		fichasPreJuego.setLayout(new BorderLayout());
		fichasPreJuego.add(prepareTextosBorder("Jugadores"), BorderLayout.NORTH);
		JPanel contenedor = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		contenedor.setLayout(new GridLayout(1, 1));
		prepareContenedoresFichaUno(contenedor);
		prepareContenedoresFichaDosMaquina(contenedor);
		fichasPreJuego.add(contenedor);
		principal.add(fichasPreJuego);
	}
	
	private void prepareContenedoresFichaUno(JPanel contenedor){
		JPanel fichasUno = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		fichasUno.setLayout(new BorderLayout());
		fichasUno.add(prepareTextosBorder("Jugador 1"), BorderLayout.NORTH);
		jugadorUnoFichas = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		jugadorUnoFichas.setLayout(new GridLayout(1, 4));
		panelJugUno = prepareBotonesFichas(jugadorUnoFichas, panelJugUno);
		fichasUno.add(jugadorUnoFichas, BorderLayout.CENTER);
		contenedor.add(fichasUno);
	}
	
	private void prepareContenedoresFichaDos(JPanel contenedor){
		JPanel fichasDos = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		fichasDos.setLayout(new BorderLayout());
		fichasDos.add(prepareTextosBorder("Jugador 2"), BorderLayout.NORTH);
		jugadorDosFichas = prepareContenedor(new JPanel(), Color.BLACK, "", true);
		jugadorDosFichas.setLayout(new GridLayout(1, 4));
		panelJugDos = prepareBotonesFichas(jugadorDosFichas, panelJugDos);
		fichasDos.add(jugadorDosFichas, BorderLayout.CENTER);
		contenedor.add(fichasDos);
	}
	
	private void prepareContenedoresFichaDosMaquina(JPanel contenedor){
		JPanel fichasDos = prepareContenedor(new JPanel(), Color.BLACK, "", false);
		fichasDos.setLayout(new BorderLayout());
		fichasDos.add(prepareTextosBorder("Jugador 2"), BorderLayout.NORTH);
		jugadorDosFichas = prepareContenedor(new JPanel(), Color.BLACK, "", true);
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
		tableros.addTab("Pequeño", prepareAreaPequeno("/Presentacion/imagenes/pathogenMap.jpg ","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Mediano", prepareAreaMediano("/Presentacion/imagenes/pathogenMap.jpg ","/Presentacion/imagenes/interrogante.png"));
		tableros.addTab("Grande", prepareAreaGrande("/Presentacion/imagenes/pathogenMap.jpg", "/Presentacion/imagenes/interrogante.png"));
		principal.add(tableros);
	}
	
	private JPanel prepareAreaPequeno(String ruta1, String ruta2){
		areaPequeno = new JPanel();
		prepareContenedor(areaPequeno,  Color.BLACK, "", false);
		areaPequeno.setOpaque(false);
		vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaPequeno.add(vacio); 
		interrogante = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interrogante.setBackground(Color.BLACK);
		interrogante.setOpaque(false);
		areaPequeno.add(interrogante);
		return areaPequeno;
	}
	
	private JPanel prepareAreaMediano(String ruta1, String ruta2){
		areaMediano = new JPanel();
		prepareContenedor(areaMediano, Color.BLACK, "", false);
		areaMediano.setOpaque(false);
		vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaMediano.add(vacio); 
		interrogante = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interrogante.setBackground(Color.BLACK);
		interrogante.setOpaque(false);
		areaMediano.add(interrogante);
		return areaMediano;
	}
	
	private JPanel prepareAreaGrande(String ruta1, String ruta2){
		areaGrande = new JPanel();
		prepareContenedor(areaGrande, Color.BLACK, "", false);
		areaGrande.setOpaque(false);
		vacio = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta1)).getImage()));
		vacio.setBackground(Color.BLACK);
		vacio.setOpaque(false);
		areaGrande.add(vacio); 
		interrogante = new RoundButton(new ImageIcon(new ImageIcon(getClass().getResource(ruta2)).getImage()));
		interrogante.setBackground(Color.BLACK);
		interrogante.setOpaque(false);
		areaGrande.add(interrogante);
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
