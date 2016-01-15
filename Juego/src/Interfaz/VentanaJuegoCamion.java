package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class VentanaJuegoCamion extends JFrame {
	private static final long serialVersionUID = 1L;  // Para serialización
	JPanel pPrincipal;         // Panel del juego (layout nulo)
	MundoJuegoCamion miMundo;        // Mundo del juego
	CamionJuego miCamion;        // Coche del juego
	MiRunnableCamion miHilo = null;  // Hilo del bucle principal de juego	
	Nivel nivel;
	int vueltas;
	boolean semaforo;
	int vidas=5;
	boolean[] obj;
	int min;
	int seg;
	int mili;
	
	public VentanaJuegoCamion() {
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		// Añadido de componentes a contenedores
		add( pPrincipal, BorderLayout.CENTER );
		
		// Formato de ventana
		setSize( 1000, 750 );
		setResizable( false );
		obj=new boolean[4];
		
		// Añadido para que también se gestione por teclado con el KeyListener
		pPrincipal.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				
					case KeyEvent.VK_UP: {	
						obj[0]=true;	
						break;
					}
					case KeyEvent.VK_DOWN: {
						obj[1]=true;
						break;
					}
					case KeyEvent.VK_LEFT: {	
						obj[2]=true;
						break;
					}
					case KeyEvent.VK_RIGHT: {	
						obj[3]=true;
						break;
					}
				}
			}
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: {
					obj[0]=false;	
					break;
				}
				case KeyEvent.VK_DOWN: {
					obj[1]=false;
					break;
				}
				case KeyEvent.VK_LEFT: {
					obj[2]=false;
					break;
				}
				case KeyEvent.VK_RIGHT: {
					obj[3]=false;
					break;
				}
				}
			}
		});
		pPrincipal.setFocusable(true);
		pPrincipal.requestFocus();
		pPrincipal.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pPrincipal.requestFocus();
			}
		});
		// Cierre del hilo al cierre de la ventana
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (miHilo!=null) miHilo.acaba();
			}
		});
	}
	public void Juego(){
		try {
	
			final VentanaJuegoCamion miVentana = new VentanaJuegoCamion();
			miVentana.miMundo = new MundoJuegoCamion( miVentana.pPrincipal );
			miVentana.miMundo.creaCamion( 260, 600);
			miVentana.miMundo.creaNivel();
			miVentana.miCamion = miVentana.miMundo.getCamion();
			// Crea el hilo de movimiento del coche y lo lanza
			miVentana.miHilo = miVentana.new MiRunnableCamion();  // Sintaxis de new para clase interna
			Thread nuevoHilo = new Thread( miVentana.miHilo );
			nuevoHilo.start();
			miVentana.setVisible(true);
		
			
		} catch (Exception e) {
			System.exit(1);  // Error anormal
		}
		
	}
	
	class MiRunnableCamion implements Runnable {
		boolean sigo = true;
		private Cronometro crono=new Cronometro();
		@Override
		public void run() {
			crono.iniciarCronometro();
			while (sigo) {
				double fuerzaRoz= miMundo.calcFuerzaRozamiento(miCamion.getMasa(), miCamion.getCoefRozSuelo(), miCamion.getCoefRozAire(), miCamion.getVelocidad());
				
				if (obj[0]==true){
					miMundo.calcAceleracionConFuerza(miCamion.fuerzaAceleracionAdelante(), miCamion.getMasa());
					miMundo.aplicarFuerza(miCamion.fuerzaAceleracionAdelante(), miCamion);
				}
				
				if (obj[0]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miCamion);					
				}

				if (obj[1]==true){
					miMundo.calcAceleracionConFuerza(-miCamion.fuerzaAceleracionAtras(), miCamion.getMasa());
					miMundo.aplicarFuerza(-miCamion.fuerzaAceleracionAtras(), miCamion);
				}
				
				if (obj[1]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miCamion);		
				}
				
				if (obj[2]==true){
					miCamion.gira( +7 );
				}
				
				if (obj[3]==true){
					miCamion.gira( -7 );
				}
				
				miCamion.mueve( 0.040 );
				
				if (miMundo.sale(miCamion) == true)
				{		
					vidas=vidas-1;
					Musica.PUM.play();
					JOptionPane.showMessageDialog(null,"Te quedan " + vidas + " vidas.");
					miMundo.miCamion.setPosicion(260, 600);
					miMundo.miCamion.setMiVelocidad(0);
					miMundo.miCamion.gira(0);
					miMundo.miCamion.setDireccionActual(0);
					crono.setTiempo(0, 0, 0);
					vueltas=0;			
				}
				if (vidas==0)
				{
					miMundo.miCamion.setMiVelocidad(0);
					miMundo.miCamion.gira(0);
					miMundo.miCamion.setDireccionActual(0);	
					crono.pararCronometro();
					sigo = false;
					VentanaUsuario us = new VentanaUsuario();
					us.setVisible(true);
					close();
				}
				if(miCamion.getPosX()>258&&miCamion.getPosX()<690&&miCamion.getPosY()>350&&miCamion.getPosY()<750&&semaforo==false)
				{
					vueltas++;
					semaforo=true;
				}
				if(miCamion.getPosX()>700&&miCamion.getPosX()<860&&miCamion.getPosY()>0&&miCamion.getPosY()<750)
				{
					semaforo=false;
				}
				
				if(vueltas==3)
				{
					crono.pararCronometro();
					min=crono.getMinutos();
					seg=crono.getSegundos();
					mili=crono.getMilesimas();
					JOptionPane.showMessageDialog(null,"Tu tiempo es " + min + " :"+ seg + " :" + mili + ".");
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
					String vehiculo = "Camion";
					BaseDatos.anyadirTiempo(BaseDatos.getStatement(), vehiculo, min, seg, mili);
					BaseDatos.close();
					miMundo.miCamion.setMiVelocidad(0);
					miMundo.miCamion.gira(0);
					miMundo.miCamion.setDireccionActual(0);	
					sigo = false;
					VentanaUsuario us = new VentanaUsuario();
					us.setVisible(true);
					close();
				}
			
				
				// Dormir el hilo 40 milisegundos
				
			
				try {
					Thread.sleep( 40 );
				} catch (Exception e) {
				}
			}
		}
		/** Ordena al hilo detenerse en cuanto sea posible
		 */
		public void acaba() {
			sigo = false;
		}
	};
	public void close(){
		this.dispose();
	}
}

