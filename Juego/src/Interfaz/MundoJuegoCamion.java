package Interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

/** "Mundo" del juego del coche.
 * Incluye las físicas para l movimiento y los choques de objetos.
 * Representa un espacio 2D en el que se mueven el coche y los objetos de puntuación.
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class MundoJuegoCamion {
	private JPanel panel;  // panel visual del juego
	CamionJuego miCamion;    // Camión del juego
	Nivel nivel;
	
		/** Construye un mundo de juego
		 * @param panel	Panel visual del juego
		 */
	public MundoJuegoCamion( JPanel panel ) {
		this.panel = panel;
	}

		/** Crea un camión nuevo y lo añade al mundo y al panel visual
		 * @param posX	Posición X de pixel del nuevo camión
		 * @param posY	Posición Y de píxel del nuevo camión
		 */
	public void creaCamion( int posX, int posY) {
		// Crear y añadir el camión a la ventana
		
		miCamion = new CamionJuego();
		miCamion.setPosicion( posX, posY );
		panel.add( miCamion.getGrafico() );  // Añade al panel visual
		miCamion.getGrafico().repaint();  // Refresca el dibujado del camión
			
	}
	//Creamos el mundo
	public void creaNivel(){
		nivel=new Nivel();
		panel.add(nivel);
		panel.repaint();
	}
	
		/** Devuelve el camión del mundo
		 * @return	Camión en el mundo. Si no lo hay, devuelve null
		 */
	public CamionJuego getCamion() {
		return miCamion;
	}
	
		/** Devuelve si se ha salido
		 * @param camión, para mirar la posición
		 * @return	true, si se sale del circuito
		 * @return false, si no se sale del cicuito
		 */
	public boolean sale( CamionJuego camion) 
	{	
		if(miCamion.getPosX()>0&&miCamion.getPosX()<22&&miCamion.getPosY()>0&&miCamion.getPosY()<750||miCamion.getPosX()>0&&miCamion.getPosX()<839&&miCamion.getPosY()>-100&&miCamion.getPosY()<3||miCamion.getPosX()>870&&miCamion.getPosX()<1000&&miCamion.getPosY()>0&&miCamion.getPosY()<750||miCamion.getPosX()>0&&miCamion.getPosX()<1000&&miCamion.getPosY()>650&&miCamion.getPosY()<750||miCamion.getPosX()>620&&miCamion.getPosX()<1000&&miCamion.getPosY()>570&&miCamion.getPosY()<750||miCamion.getPosX()>124&&miCamion.getPosX()<770&&miCamion.getPosY()>120&&miCamion.getPosY()<460||miCamion.getPosX()>124&&miCamion.getPosX()<480&&miCamion.getPosY()>421&&miCamion.getPosY()<550){
		return true;
		}
		return false;
	}
	
		/** Calcula y devuelve la posición X de un movimiento
		 * @param vel    	Velocidad del movimiento (en píxels por segundo)
		 * @param dir    	Dirección del movimiento en grados (0º = eje OX positivo. Sentido antihorario)
		 * @param tiempo	Tiempo del movimiento (en segundos)
		 * @return
		 */
	public static double calcMovtoX( double vel, double dir, double tiempo ) {
		return vel * Math.cos(dir/180.0*Math.PI) * tiempo;
	}
	
		/** Calcula y devuelve la posición X de un movimiento
		 * @param vel    	Velocidad del movimiento (en píxels por segundo)
		 * @param dir    	Dirección del movimiento en grados (0º = eje OX positivo. Sentido antihorario)
		 * @param tiempo	Tiempo del movimiento (en segundos)
		 * @return
		 */
	public static double calcMovtoY( double vel, double dir, double tiempo ) {
		return vel * -Math.sin(dir/180.0*Math.PI) * tiempo;
	}
	
		/** Calcula el cambio de velocidad en función de la aceleración
		 * @param vel		Velocidad original
		 * @param acel		Aceleración aplicada (puede ser negativa) en pixels/sg2
		 * @param tiempo	Tiempo transcurrido en segundos
		 * @return	Nueva velocidad
		 */
	public static double calcVelocidadConAceleracion( double vel, double acel, double tiempo ) {
		return vel + (acel*tiempo);
	}
	
		/** Calcula el cambio de rozamiento
		 * @param masa	
		 * @param coefRozSuelo
		 * @param coefRozAire
		 * @param vel
		 * @return	rozamiento
		 */
	public static double calcFuerzaRozamiento( double masa, double coefRozSuelo,
			 double coefRozAire, double vel ) {
			 double fuerzaRozamientoAire = coefRozAire * (-vel); // En contra del movimiento
			 double fuerzaRozamientoSuelo = masa * coefRozSuelo * ((vel>0)?(-1):1); // Contra mvto
			 return fuerzaRozamientoAire + fuerzaRozamientoSuelo;
	 } 
	
		/** Calcula la aceleración
		 * @param fuerza	
		 * @param masa
		 * @return	aceleración
		 */
	public static double calcAceleracionConFuerza( double fuerza, double masa ) {
		 return fuerza/masa; 
	}
	
		/** Calcula la fuerza
		 * @param fuerza	
		 * @param camión
		 */
	public static void aplicarFuerza( double fuerza, Camion camion ) {
		 double fuerzaRozamiento = calcFuerzaRozamiento( Camion.getMasa() ,
		 Camion.getFuerzaRozamientoSuelo(), Camion.getFuerzaRozamientoAire(), camion.getVelocidad() );
		 double aceleracion = calcAceleracionConFuerza( fuerza+fuerzaRozamiento, Camion.getMasa() );
		 if (fuerza==0) {
		 // No hay fuerza, solo se aplica el rozamiento
		 double velAntigua = camion.getVelocidad();
		 camion.acelera( aceleracion, 0.04 );
		 if (velAntigua>=0 && camion.getVelocidad()<0
		 || velAntigua<=0 && camion.getVelocidad()>0) {
		 camion.setVelocidad(0); // Si se está frenando, se para (no anda al revés)
		 }
		 } else {
		 camion.acelera( aceleracion, 0.04 );
		 } 
	}
}