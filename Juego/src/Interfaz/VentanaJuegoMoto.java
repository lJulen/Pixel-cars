package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class VentanaJuegoMoto extends JFrame {
	private static final long serialVersionUID = 1L;  // Para serialización
	JPanel pPrincipal;         // Panel del juego (layout nulo)
	MundoJuegoMoto miMundo;        // Mundo del juego
	MotoJuego miMoto;        // Coche del juego
	MiRunnableMoto miHilo = null;  // Hilo del bucle principal de juego	
	Nivel nivel;
	int vueltas;
	Cronometro crono;
	boolean semaforo;
	int vidas=5;
	boolean[] obj;
	int min;
	int seg;
	int mili;
	
	public VentanaJuegoMoto() {
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
	
			final VentanaJuegoMoto miVentana = new VentanaJuegoMoto();
			miVentana.miMundo = new MundoJuegoMoto( miVentana.pPrincipal );
			miVentana.miMundo.creaMoto( 260, 600);
			miVentana.miMundo.creaNivel();
			miVentana.miMoto = miVentana.miMundo.getMoto();
			// Crea el hilo de movimiento del coche y lo lanza
			miVentana.miHilo = miVentana.new MiRunnableMoto();  // Sintaxis de new para clase interna
			Thread nuevoHilo = new Thread( miVentana.miHilo );
			nuevoHilo.start();
			miVentana.setVisible(true);
	
			
		} catch (Exception e) {
			System.exit(1);  // Error anormal
		}
		
	}
	
	
	/** Clase interna para implementación de bucle principal del juego como un hilo
	 * @author Andoni Eguíluz
	 * Facultad de Ingeniería - Universidad de Deusto (2014)
	 */

	class MiRunnableMoto implements Runnable {
		boolean sigo = true;
		private Cronometro crono=new Cronometro();
		@Override
		public void run() {
			crono.iniciarCronometro();
			while (sigo) {
				double fuerzaRoz= miMundo.calcFuerzaRozamiento(miMoto.getMasa(), miMoto.getCoefRozSuelo(), miMoto.getCoefRozAire(), miMoto.getVelocidad());
				
				if (obj[0]==true){
					miMundo.calcAceleracionConFuerza(miMoto.fuerzaAceleracionAdelante(), miMoto.getMasa());
					miMundo.aplicarFuerza(miMoto.fuerzaAceleracionAdelante(), miMoto);
				}
				
				if (obj[0]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miMoto);
					
				}

				if (obj[1]==true){					
					miMundo.calcAceleracionConFuerza(-miMoto.fuerzaAceleracionAtras(), miMoto.getMasa());
					miMundo.aplicarFuerza(-miMoto.fuerzaAceleracionAtras(), miMoto);
				}
				
				if (obj[1]==false){
					miMundo.aplicarFuerza(fuerzaRoz, miMoto);			
				}
				
				if (obj[2]==true){
					miMoto.gira( +15 );
				}
				
				if (obj[3]==true){
					miMoto.gira( -15 );
				}
				
				miMoto.mueve( 0.040 );
				
				if (miMundo.sale(miMoto) == true) {// Espejo vertical si choca en Y
					vidas=vidas-1;
					Musica.PUM.play();
					JOptionPane.showMessageDialog(null,"Te quedan " + vidas + " vidas.");
					
					miMundo.miMoto.setPosicion(260, 600);
					miMundo.miMoto.setMiVelocidad(0);
					miMundo.miMoto.gira(0);
					miMundo.miMoto.setDireccionActual(0);
					crono.setTiempo(0, 0, 0);
					vueltas=0;				
				}
				
				if (vidas==0){
					miMundo.miMoto.setMiVelocidad(0);
					miMundo.miMoto.gira(0);
					miMundo.miMoto.setDireccionActual(0);
					crono.pararCronometro();
					sigo = false;
					VentanaUsuario us = new VentanaUsuario();
					us.setVisible(true);
					close();
				}
				if(miMoto.getPosX()>258&&miMoto.getPosX()<690&&miMoto.getPosY()>350&&miMoto.getPosY()<750&&semaforo==false){
					vueltas++;
					semaforo=true;
					
				}
				if(miMoto.getPosX()>700&&miMoto.getPosX()<860&&miMoto.getPosY()>0&&miMoto.getPosY()<750){
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
					String vehiculo = "Moto";
					BaseDatos.anyadirTiempo(BaseDatos.getStatement(), vehiculo, min, seg, mili);
					BaseDatos.close();
					miMundo.miMoto.setMiVelocidad(0);
					miMundo.miMoto.gira(0);
					miMundo.miMoto.setDireccionActual(0);
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

