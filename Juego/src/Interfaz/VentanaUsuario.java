package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuario extends JFrame implements ActionListener {
	PanelConImagen contentPane;
	
	
	public VentanaUsuario(){
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		//Creamos el panel
		setSize(580,400);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(contentPane.createImage("/Imagen/Pixel-Cars.jpg").getImage());
		setContentPane(contentPane);
		contentPane.setLayout( null );
		//Creamos los botones
		
		JButton bJugar=new JButton("Jugar");
		bJugar.setFont(new Font("Jokerman", Font.PLAIN, 20));
		bJugar.setBounds(79, 152, 95, 55);
		bJugar.setOpaque(false);
		bJugar.setContentAreaFilled(false);
		bJugar.setBorderPainted(false);
		bJugar.addActionListener(this);
		
		JButton bInformacion=new JButton("Información");
		bInformacion.setFont(new Font("Jokerman", Font.PLAIN, 20));
		bInformacion.setBounds(229, 152, 150, 55);
		bInformacion.setOpaque(false);
		bInformacion.setContentAreaFilled(false);
		bInformacion.setBorderPainted(false);
		bInformacion.addActionListener(this);
		
		JButton bSalir=new JButton("Salir");
		bSalir.setFont(new Font("Jokerman", Font.PLAIN, 20));
		bSalir.setBounds(422, 152, 89, 55);
		bSalir.setOpaque(false);
		bSalir.setContentAreaFilled(false);
		bSalir.setBorderPainted(false);
		bSalir.addActionListener(this);
		
		contentPane.add( bJugar );
		contentPane.add( bInformacion );
		contentPane.add( bSalir );
		Musica.THEME.play();		
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "Jugar":VentanaVehiculos objVehiculos=new VentanaVehiculos();
		objVehiculos.setVisible(true);
		break;
		/*case "Información":VentanaVehiculo objVehiculo=new VentanaVehiculo();
		objPersona.setVisible(true);
		break;*/
		case "Salir":VentanaPrincipal objSalir=new VentanaPrincipal();
		objSalir.setVisible(true);
		break;
		}
	}
}
