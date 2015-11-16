package Interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements ActionListener {
	PanelConImagen contentPane;
	
	public VentanaPrincipal(){
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		//Creamos el panel
		setSize(580,400);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(contentPane.createImage("/Imagen/Pixel-Cars.jpg").getImage());
		setContentPane(contentPane);
		contentPane.setLayout( null );
		
		JButton bLog=new JButton("Log in");
		bLog.setFont(new Font("Jokerman", Font.PLAIN, 20));
		bLog.setBounds(79, 152, 95, 55);
		bLog.setOpaque(false);
		bLog.setContentAreaFilled(false);
		bLog.setBorderPainted(false);
		bLog.addActionListener(this);
		
		JButton bSign=new JButton("Sign up");
		bSign.setFont(new Font("Jokerman", Font.PLAIN, 20));
		bSign.setBounds(422, 152, 113, 55);
		bSign.setOpaque(false);
		bSign.setContentAreaFilled(false);
		bSign.setBorderPainted(false);
		bSign.addActionListener(this);
		
		contentPane.add( bLog );
		contentPane.add( bSign );
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "Log in":VentanaLog objPersona=new VentanaLog();
		objPersona.setVisible(true);
		break;
		/*case "Sign up":VentanaSign objPelicula=new VentanaSign();
		objPelicula.setVisible(true);
		break;*/
		}
	}
}
