package Presentacion;

import java.awt.*;
import javax.swing.*;

public class ImagenFondo extends JPanel{
	
	public void paintComponent(Graphics g){
		Dimension tam = getSize();
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/Presentacion/imagenes/imagenFondo.jpg")).getImage());
		g.drawImage(imagen.getImage(), 0, 0, tam.width, tam.height, null);
	}

}
