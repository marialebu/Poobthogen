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
	
	JPanel principal;
	JPanel contenedorBotones;
	JButton juegoNuevo;
	JButton cargarJuego;
	JButton creditos;
	Font f;
	
	public PoobthogenGUI(){
		setTitle("Poobthogen");
		prepareElementos();
		prepareAcciones();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PoobthogenGUI juego  = new PoobthogenGUI();
		juego.setVisible(true);
	}
	
	private void prepareElementos(){
		preparePantalla();
		preparePantallaInicio();
	}
	
	private void preparePantalla(){
		Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - getSize().width) / 2; 
        int y = (screen.height - getSize().height) / 2; 
        setSize(x, y);
        principal = new ImagenFondo();
        add(principal);
	}
	
	private void preparePantallaInicio(){
		Dimension tam = this.getContentPane().getSize();
		principal.setLayout(new BorderLayout());
		JPanel contenedorBotones = new JPanel();
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
	
	private JButton creaBoton(int x, int y, String mensaje, int w, int h, Font f){
		JButton b = new RoundButton(mensaje);
		b.setFocusPainted(false);
		b.setFont(f);
		return b;
		
	}
	
	private void prepareAcciones(){
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
	}
	
	private void muestreCreditos(){
		String mensaje = "Bla bla bla y más bla bla";
		JOptionPane.showMessageDialog(this,(Object)mensaje, "Creditos", JOptionPane.INFORMATION_MESSAGE);
	}
	private void cargueJuego(){
		Object [] opciones ={"Abrir","Importar","Cancelar", };
		int eleccion = JOptionPane.showOptionDialog(this,"En realidad desea cerrar la aplicacion","Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
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
	
	private void salga(){
    	Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(this,"En realidad desea cerrar la aplicacion","Mensaje de Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION){
			dispose(); 
    		System.exit(0);
		}
    }
	
	

}
