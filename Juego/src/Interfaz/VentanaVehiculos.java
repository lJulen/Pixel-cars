package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.SwingConstants;

public class VentanaVehiculos extends JFrame implements ActionListener {
PanelConImagen contentPane;
JLabel lblNewLabel;
JLabel lblNewLabel_1;
JLabel lblNewLabel_2;
JLabel lblNewLabel_3;
JLabel lblNewLabel_4;
JLabel lblCaracteristicas;
JLabel lblVelocidad;
JLabel lblAceleracin;
String path;
String path1;
String path2;
String path3;
URL url;
URL url1;
URL url2;
URL url3;
ImageIcon coche;
ImageIcon icon;
ImageIcon icon1;
ImageIcon icon2;
ImageIcon icon3;
JButton btnNewButton ;
int a;


	public VentanaVehiculos(){
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		//Creamos el panel
		setSize(580,400);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(contentPane.createImage("/Imagen/Seleccion.jpg").getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bCoche = new JButton("Coche");
		bCoche.setBackground(Color.WHITE);
		bCoche.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/Imagen/Coche - copia.png")));
		bCoche.setBounds(22, 39, 100, 100);
		contentPane.add(bCoche);
		bCoche.addActionListener(this);
		
		JButton bMoto = new JButton("Moto");
		bMoto.setBackground(Color.WHITE);
		bMoto.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/Imagen/Moto - copia.png")));
		bMoto.setBounds(122, 39, 100, 100);
		contentPane.add(bMoto);
		bMoto.addActionListener(this);
		
		JButton bCamion = new JButton("Camion");
		bCamion.setBackground(Color.WHITE);
		bCamion.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/Imagen/Camion - copia.png")));
		bCamion.setBounds(22, 139, 100, 100);
		contentPane.add(bCamion);
		bCamion.addActionListener(this);
		
		JButton bAleatorio = new JButton("Aleatorio");
		bAleatorio.setBackground(Color.WHITE);
		bAleatorio.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/Imagen/Aleatorio.png")));
		bAleatorio.setBounds(122, 139, 100, 100);
		contentPane.add(bAleatorio);
		bAleatorio.addActionListener(this);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(232, 39, 279, 200);
		contentPane.add(lblNewLabel);
		
		lblCaracteristicas = new JLabel("Caracteristicas");
		lblCaracteristicas.setFont(new Font("Jokerman", Font.BOLD, 11));
		lblCaracteristicas.setForeground(Color.WHITE);
		lblCaracteristicas.setBounds(326, 250, 109, 14);
		lblCaracteristicas.setVisible(false);
		contentPane.add(lblCaracteristicas);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(297, 275, 214, 23);
		contentPane.add(lblNewLabel_1);
		
		lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setFont(new Font("Jokerman", Font.BOLD, 11));
		lblVelocidad.setForeground(Color.WHITE);
		lblVelocidad.setBounds(213, 278, 92, 20);
		lblVelocidad.setVisible(false);
		contentPane.add(lblVelocidad);
		
		lblAceleracin = new JLabel("Aceleraci\u00F3n");
		lblAceleracin.setForeground(Color.WHITE);
		lblAceleracin.setFont(new Font("Jokerman", Font.BOLD, 11));
		lblAceleracin.setBounds(213, 315, 92, 14);
		lblAceleracin.setVisible(false);
		contentPane.add(lblAceleracin);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(297, 306, 214, 23);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Control");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Jokerman", Font.BOLD, 11));
		lblNewLabel_3.setBounds(213, 346, 92, 14);
		lblNewLabel_3.setVisible(false);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(297, 337, 214, 23);
		contentPane.add(lblNewLabel_4);
		
		btnNewButton = new JButton("Launch");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(22, 295, 164, 56);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		Musica.THEME.loop();
		
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "Coche":path = "/Imagen/Coche.png";  
		path1="/Imagen/Velocidad coche.png";
		path2="/Imagen/Acel coche.png";
		path3="/Imagen/Control coche.png";
		url = this.getClass().getResource(path);  
		url1=this.getClass().getResource(path1);  
		url2=this.getClass().getResource(path2);
		url3=this.getClass().getResource(path3);
		icon = new ImageIcon(url);  
		icon1 = new ImageIcon(url1);  
		icon2 = new ImageIcon(url2);  
		icon3 = new ImageIcon(url3);  
		lblNewLabel.setIcon(icon); 
		lblNewLabel_1.setIcon(icon1);
		lblNewLabel_2.setIcon(icon2);
		lblNewLabel_4.setIcon(icon3);
		lblNewLabel_3.setVisible(true);
		lblCaracteristicas.setVisible(true);
		lblVelocidad.setVisible(true);
		lblAceleracin.setVisible(true);
		btnNewButton.setEnabled(true);
		a=1;
		blinking();
		break;
		
		case "Moto":path = "/Imagen/Moto.png"; 
		path1="/Imagen/Velocidad moto.png";
		path2="/Imagen/Acel moto.png";
		path3="/Imagen/Control moto.png";
		url = this.getClass().getResource(path); 
		url1=this.getClass().getResource(path1);  
		url2=this.getClass().getResource(path2);  
		url3=this.getClass().getResource(path3);  
		icon = new ImageIcon(url);
		icon1 = new ImageIcon(url1); 
		icon2 = new ImageIcon(url2); 
		icon3 = new ImageIcon(url3); 
		lblNewLabel.setIcon(icon); 
		lblNewLabel_1.setIcon(icon1);
		lblNewLabel_2.setIcon(icon2);
		lblNewLabel_4.setIcon(icon3);
		lblNewLabel_3.setVisible(true);
		lblCaracteristicas.setVisible(true);
		lblVelocidad.setVisible(true);
		lblAceleracin.setVisible(true);
		btnNewButton.setEnabled(true);
		a=2;
		blinking();
		break;
		
		case "Camion":path = "/Imagen/Camion.png"; 
		path1="/Imagen/Velocidad camion.png";
		path2="/Imagen/Acel camion.png";
		path3="/Imagen/Control camion.png";
		url = this.getClass().getResource(path);  
		url1=this.getClass().getResource(path1);  
		url2=this.getClass().getResource(path2);  
		url3=this.getClass().getResource(path3);  
		icon = new ImageIcon(url);
		icon1 = new ImageIcon(url1);
		icon2 = new ImageIcon(url2);	
		icon3 = new ImageIcon(url3);	
		lblNewLabel.setIcon(icon); 
		lblNewLabel_1.setIcon(icon1);
		lblNewLabel_2.setIcon(icon2);
		lblNewLabel_4.setIcon(icon3);
		lblNewLabel_3.setVisible(true);
		lblCaracteristicas.setVisible(true);
		lblVelocidad.setVisible(true);
		lblAceleracin.setVisible(true);
		btnNewButton.setEnabled(true);
		a=3;
		blinking();
		break;
		
		case "Aleatorio":
			int numeroAleatorio = (int) (Math.random()*3+1);
            switch(numeroAleatorio){
	            case 1:path = "/Imagen/Coche.png";  
	    		path1="/Imagen/Velocidad coche.png";
	    		path2="/Imagen/Acel coche.png";
	    		path3="/Imagen/Control coche.png";
	    		url = this.getClass().getResource(path);  
	    		url1=this.getClass().getResource(path1);  
	    		url2=this.getClass().getResource(path2);
	    		url3=this.getClass().getResource(path3);
	    		icon = new ImageIcon(url);  
	    		icon1 = new ImageIcon(url1);  
	    		icon2 = new ImageIcon(url2);  
	    		icon3 = new ImageIcon(url3);  
	    		lblNewLabel.setIcon(icon); 
	    		lblNewLabel_1.setIcon(icon1);
	    		lblNewLabel_2.setIcon(icon2);
	    		lblNewLabel_4.setIcon(icon3);
	    		lblNewLabel_3.setVisible(true);
	    		lblCaracteristicas.setVisible(true);
	    		lblVelocidad.setVisible(true);
	    		lblAceleracin.setVisible(true);
	    		btnNewButton.setEnabled(true);
	    		a=1;
	    		break;
	    		
	            case 2:path = "/Imagen/Moto.png"; 
	    		path1="/Imagen/Velocidad moto.png";
	    		path2="/Imagen/Acel moto.png";
	    		path3="/Imagen/Control moto.png";
	    		url = this.getClass().getResource(path); 
	    		url1=this.getClass().getResource(path1);  
	    		url2=this.getClass().getResource(path2);  
	    		url3=this.getClass().getResource(path3);  
	    		icon = new ImageIcon(url);
	    		icon1 = new ImageIcon(url1); 
	    		icon2 = new ImageIcon(url2); 
	    		icon3 = new ImageIcon(url3); 
	    		lblNewLabel.setIcon(icon); 
	    		lblNewLabel_1.setIcon(icon1);
	    		lblNewLabel_2.setIcon(icon2);
	    		lblNewLabel_4.setIcon(icon3);
	    		lblNewLabel_3.setVisible(true);
	    		lblCaracteristicas.setVisible(true);
	    		lblVelocidad.setVisible(true);
	    		lblAceleracin.setVisible(true);
	    		btnNewButton.setEnabled(true);
	    		a=2;
	    		break;
	    		
	            case 3:path = "/Imagen/Camion.png"; 
	    		path1="/Imagen/Velocidad camion.png";
	    		path2="/Imagen/Acel camion.png";
	    		path3="/Imagen/Control camion.png";
	    		url = this.getClass().getResource(path);  
	    		url1=this.getClass().getResource(path1);  
	    		url2=this.getClass().getResource(path2);  
	    		url3=this.getClass().getResource(path3);  
	    		icon = new ImageIcon(url);
	    		icon1 = new ImageIcon(url1);
	    		icon2 = new ImageIcon(url2);	
	    		icon3 = new ImageIcon(url3);	
	    		lblNewLabel.setIcon(icon); 
	    		lblNewLabel_1.setIcon(icon1);
	    		lblNewLabel_2.setIcon(icon2);
	    		lblNewLabel_4.setIcon(icon3);
	    		lblNewLabel_3.setVisible(true);
	    		lblCaracteristicas.setVisible(true);
	    		lblVelocidad.setVisible(true);
	    		lblAceleracin.setVisible(true);
	    		btnNewButton.setEnabled(true);
	    		a=3;
	    		break;
            }
			break;
			
		case "Launch":
			switch(a){
				case 1:VentanaJuegoCoche objjuegocoche=new VentanaJuegoCoche();
				objjuegocoche.Juego();
				break;
				case 2: VentanaJuegoMoto objjuegomoto=new VentanaJuegoMoto();
				objjuegomoto.Juego();
				break;
				case 3: VentanaJuegoCamion objjuegocamion=new VentanaJuegoCamion();
				objjuegocamion.Juego();
				break;
			}	
			
			Musica.THEME.stop();
			this.dispose();
		break;
		}
	}
	
	 private void blinking() {
		 btnNewButton.setOpaque(true);
		 Timer blinkTimer;
		 
	        blinkTimer = new Timer(500, new ActionListener() {
	            boolean on=false;
	            public void actionPerformed(ActionEvent e) {
	                // blink the button background on and off
	            	btnNewButton.setBackground( on ? Color.RED : null);
	                on = !on;
	               
	            }
	        });
	        blinkTimer.restart();
		
	    }
	 
}
