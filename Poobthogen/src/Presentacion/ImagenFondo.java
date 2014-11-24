package Presentacion;

import java.awt.*;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.*;

public class ImagenFondo extends JPanel{
	
	private String fondo; 
	
	public ImagenFondo(String fondo){
		super();
		this.fondo = fondo;
	}
	
	public void paintComponent(Graphics g){
		Dimension tam = getSize();
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource(fondo)).getImage());
		g.drawImage(imagen.getImage(), 0, 0, tam.width, tam.height, null);
	}

}
