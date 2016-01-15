package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Nivel extends JPanel {
	BufferedImage levelImg;
	public Nivel(){
		this.setSize(1000,750);
	}
	public void paint(Graphics grafico) {
		Dimension height = getSize();	 
		//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
		try {
			levelImg = ImageIO.read(getClass().getResource("/Imagen/Mundo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
				 
		//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
		 
		grafico.drawImage(levelImg, 0, 0, height.width, height.height, null);
		 
		setOpaque(false);
		super.paintComponent(grafico);
		}
}
