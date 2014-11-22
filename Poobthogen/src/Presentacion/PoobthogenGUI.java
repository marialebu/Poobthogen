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
	
	JPanel principal;
	JPanel contenedorBotones;
	ArrayList<JButton> botonesInicio;
	
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
		Dimension tam = this.getSize();
		JPanel contenedorBotones = new JPanel();
		botonesInicio = new ArrayList<JButton>();
		contenedorBotones.setLayout(new GridLayout(1, 3));
		
	}
	
	private void prepareAcciones(){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter(){
    		public void windowClosing (WindowEvent e){
    			salga();	
    		}
    	});
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
