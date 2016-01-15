package Interfaz;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.*;

public class VentanaJuegoCoche extends JFrame {
	private static final long serialVersionUID = 1L;  // Para serialización
	JPanel pPrincipal;         // Panel del juego (layout nulo)
	MundoJuegoCoche miMundo;        // Mundo del juego
	CocheJuego miCoche;        // Coche del juego
	MiRunnable miHilo = null;  // Hilo del bucle principal de juego	
	Nivel nivel;
	int vueltas;
	int vidas=5;
	boolean semaforo;
	boolean[] obj;
	int min;
	int seg;
	int mili;
	
	public VentanaJuegoCoche() {
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
	
			final VentanaJuegoCoche miVentana = new VentanaJuegoCoche();
			miVentana.miMundo = new MundoJuegoCoche( miVentana.pPrincipal );
			miVentana.miMundo.creaCoche( 260, 600);
			miVentana.miMundo.creaNivel();
			miVentana.miCoche = miVentana.miMundo.getCoche();
			// Crea el hilo de movimiento del coche y lo lanza
			miVentana.miHilo = miVentana.new MiRunnable();  // Sintaxis de new para clase interna
			Thread nuevoHilo = new Thread( miVentana.miHilo );
			nuevoHilo.start();
			miVentana.setVisible(true);
		
		} catch (Exception e) {
			System.exit(1);  // Error anormal
		}
		
	}

	class MiRunnable implements Runnable {
		boolean sigo = true;

	private Cronometro crono=new Cronometro();
		@Override
		public void run() {
			crono.iniciarCronometro();
			while (sigo) {
				double fuerzaRoz= miMundo.calcFuerzaRozamiento(miCoche.getMasa(), miCoche.getCoefRozSuelo(), miCoche.getCoefRozAire(), miCoche.getVelocidad());


				if (obj[0]==true){
					miMundo.calcAceleracionConFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche.getMasa());
					miMundo.aplicarFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche);
				}
				
				if (obj[0]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miCoche);					
				}
			
				if (obj[1]==true){
					miMundo.calcAceleracionConFuerza(-miCoche.fuerzaAceleracionAtras(), miCoche.getMasa());
					miMundo.aplicarFuerza(-miCoche.fuerzaAceleracionAtras(), miCoche);
				}
				
				if (obj[1]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miCoche);					
				}
				
				if (obj[2]==true){
					miCoche.gira( +10 );
				}
				
				if (obj[3]==true){
					miCoche.gira( -10 );
				}
				
				miCoche.mueve( 0.040 );
				
				if (miMundo.sale(miCoche) == true) {
					vidas=vidas-1;
					Musica.PUM.play();
					JOptionPane.showMessageDialog(null,"Te quedan " + vidas + " vidas.");
					miMundo.miCoche.setPosicion(260, 600);
					miMundo.miCoche.setMiVelocidad(0);
					miMundo.miCoche.gira(0);
					miMundo.miCoche.setDireccionActual(0);
					crono.setTiempo(0, 0, 0);
					vueltas=0;		
				}
				if (vidas==0){
					miMundo.miCoche.setPosicion(260, 600);
					miMundo.miCoche.setMiVelocidad(0);
					miMundo.miCoche.gira(0);
					crono.pararCronometro();
					sigo = false;
					VentanaUsuario us = new VentanaUsuario();
					us.setVisible(true);
					close();
				}
				if((miCoche.getPosX()>258&&miCoche.getPosX()<690&&miCoche.getPosY()>350&&miCoche.getPosY()<750&&semaforo==false)){
					vueltas++;
					semaforo=true;
				}
				if(miCoche.getPosX()>700&&miCoche.getPosX()<860&&miCoche.getPosY()>0&&miCoche.getPosY()<750){
					semaforo=false;
				}
				
				if(vueltas==3){
					crono.pararCronometro();
					min=crono.getMinutos();
					seg=crono.getSegundos();
					mili=crono.getMilesimas();
					JOptionPane.showMessageDialog(null,"Tu tiempo es " + min + " :"+ seg + " :" + mili + ".");
					BaseDatos.initBD("PixelCars.db");
					BaseDatos.crearTablaBDT();
					String vehiculo = "Coche";
					BaseDatos.anyadirTiempo(BaseDatos.getStatement(), vehiculo, min, seg, mili);
					BaseDatos.close();
					miMundo.miCoche.setPosicion(260, 600);
					miMundo.miCoche.setMiVelocidad(0);
					miMundo.miCoche.gira(0);
					sigo=false;
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

