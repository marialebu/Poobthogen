package Presentacion;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class RoundButton extends JButton {
	
	private int f;
	private int f1;
	private Shape shape;

	/**
	 * Constructor de la clase boton redondo
	 * @param label Texto que lleva el boton
	 */
  public RoundButton(String label) {
    super(label);
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width, 
      size.height);
    setPreferredSize(size);
    setContentAreaFilled(false);
    f = 30;
    f1 = 30;
    this.setForeground(Color.WHITE);
  }
  
  /**
   * Constructor de la clase boton redondo con imagen. 
   * @param image Imagen que lleva el boton
   */
  public RoundButton(Icon image) {
	    super(image);
	    Dimension size = getPreferredSize();
	    size.width = size.height = Math.max(size.width, 
	      size.height);
	    setPreferredSize(size);
	    setContentAreaFilled(false);
	    f = 30;
	    f1 = 30;
	  }

  /**
   * Establece los colores cuando el boton es presionado o no. 
   */
  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(new Color(23, 22, 36));
    } else {
      g.setColor(new Color(34, 34, 70));
    }
    g.fillRoundRect(15, 15, getSize().width - 30, getSize().height - 30, f, f1); 
    super.paintComponent(g);
  }
  
  /**
   * Establece el borde del boton
   */
  protected void paintBorder(Graphics g) {
    g.setColor(new Color(22, 25, 40, 1));
    g.drawRoundRect(15, 15, getSize().width - 1, getSize().height - 1, f, f1);
  }
  
  /**
   * Establece si el cursor esta dentro del boton o no. 
   */
  public boolean contains(int x, int y) {
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new RoundRectangle2D.Double(0, 30,getWidth(),getHeight(), f, f1); ;
    }
    return shape.contains(x, y);
  }
}
