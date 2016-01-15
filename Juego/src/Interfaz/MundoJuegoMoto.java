package Interfaz;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

public class MundoJuegoMoto {
	private JPanel panel;  // panel visual del juego
	MotoJuego miMoto;    // Moto del juego
	Nivel nivel;
	
		/** Construye un mundo de juego
		 * @param panel	Panel visual del juego
		 */
	public MundoJuegoMoto( JPanel panel ) {
		this.panel = panel;
	}

		/** Crea una moto nueva y lo añade al mundo y al panel visual
		 * @param posX	Posición X de pixel de la nueva moto
		 * @param posY	Posición Y de píxel de la nueva moto
		 */
	public void creaMoto( int posX, int posY) {
		// Crear y añadir la moto a la ventana	
		miMoto = new MotoJuego();
		miMoto.setPosicion(posX, posY);
		panel.add( miMoto.getGrafico() );  // Añade al panel visual
		miMoto.getGrafico().repaint();  // Refresca el dibujado de la moto		
	}
	
	//Creamos el nivel
	public void creaNivel(){
		nivel=new Nivel();
		panel.add(nivel);
		panel.repaint();
	}
	
		/** Devuelve la moto del mundo
		 * @return	Moto en el mundo. Si no lo hay, devuelve null
		 */
	public MotoJuego getMoto() {
		return miMoto;
	}
	
		/** Devuelve si se ha salido
		 * @param moto, para mirar la posición
		 * @return	true, si se sale del circuito
		 * @return false, si no se sale del cicuito
		 */
	public boolean sale( MotoJuego moto) 
	{	
		if(miMoto.getPosX()>0&&miMoto.getPosX()<22&&miMoto.getPosY()>0&&miMoto.getPosY()<750||miMoto.getPosX()>0&&miMoto.getPosX()<839&&miMoto.getPosY()>-100&&miMoto.getPosY()<3||miMoto.getPosX()>870&&miMoto.getPosX()<1000&&miMoto.getPosY()>0&&miMoto.getPosY()<750||miMoto.getPosX()>0&&miMoto.getPosX()<1000&&miMoto.getPosY()>650&&miMoto.getPosY()<750||miMoto.getPosX()>620&&miMoto.getPosX()<1000&&miMoto.getPosY()>570&&miMoto.getPosY()<750||miMoto.getPosX()>124&&miMoto.getPosX()<770&&miMoto.getPosY()>120&&miMoto.getPosY()<460||miMoto.getPosX()>124&&miMoto.getPosX()<480&&miMoto.getPosY()>421&&miMoto.getPosY()<550){
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
		 * @param moto
		 */
	public static void aplicarFuerza( double fuerza, Moto moto ) {
		 double fuerzaRozamiento = calcFuerzaRozamiento( Moto.getMasa() ,
		 Moto.getFuerzaRozamientoSuelo(), Moto.getFuerzaRozamientoAire(), moto.getVelocidad() );
		 double aceleracion = calcAceleracionConFuerza( fuerza+fuerzaRozamiento, Moto.getMasa() );
		 if (fuerza==0) {
		 // No hay fuerza, solo se aplica el rozamiento
		 double velAntigua = moto.getVelocidad();
		 moto.acelera( aceleracion, 0.04 );
		 if (velAntigua>=0 && moto.getVelocidad()<0
		 || velAntigua<=0 && moto.getVelocidad()>0) {
		 moto.setVelocidad(0); // Si se está frenando, se para (no anda al revés)
		 }
		 } else {
		 moto.acelera( aceleracion, 0.04 );
		 } 
	}

}