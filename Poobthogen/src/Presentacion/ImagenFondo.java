package Presentacion;

import java.awt.*;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.*;

public class ImagenFondo extends JPanel{
	
	private String fondo; 
	
	/**
	 * Constructor de la clase Imagen de fondo
	 * @param fondo Imagen que se va a usar de fondo. 
	 */
	public ImagenFondo(String fondo){
		super();
		this.fondo = fondo;
	}
	
	/**
	 * Establece el fondo en el panel. 
	 */
	public void paintComponent(Graphics g){
		Dimension tam = getSize();
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource(fondo)).getImage());
		g.drawImage(imagen.getImage(), 0, 0, tam.width, tam.height, null);
	}

}
