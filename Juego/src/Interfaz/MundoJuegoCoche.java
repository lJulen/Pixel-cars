package Interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

public class MundoJuegoCoche {
	private JPanel panel;  // panel visual del juego
	CocheJuego miCoche;    // Coche del juego
	Nivel nivel;
	
	
		/** Construye un mundo de juego
		 * @param panel	Panel visual del juego
		 */
	public MundoJuegoCoche( JPanel panel ) {
		this.panel = panel;
	}

		/** Crea un coche nuevo y lo añade al mundo y al panel visual
		 * @param posX	Posición X de pixel del nuevo coche
		 * @param posY	Posición Y de píxel del nuevo coche
		 */
	public void creaCoche( int posX, int posY) {
		// Crear y añadir el coche a la ventana
		miCoche = new CocheJuego();
		miCoche.setPosicion(posX, posY);
		panel.add( miCoche.getGrafico() );  // Añade al panel visual
		miCoche.getGrafico().repaint();  // Refresca el dibujado del coche		
	}
	
	//Creamos el nivel
	public void creaNivel(){
		nivel=new Nivel();
		panel.add(nivel);
		panel.repaint();
	}
	
		/** Devuelve el coche del mundo
		 * @return	Coche en el mundo. Si no lo hay, devuelve null
		 */
	public CocheJuego getCoche() {
		return miCoche;
	}
	
		/** Calcula si hay choque en vertical con los límites del mundo
		 * @param coche	Coche cuyo choque se comprueba con su posición actual
		 * @return	true si hay choque vertical, false si no lo hay
		 */
	public boolean sale( CocheJuego coche) 
	{	
		if(miCoche.getPosX()>0&&miCoche.getPosX()<22&&miCoche.getPosY()>0&&miCoche.getPosY()<750||miCoche.getPosX()>0&&miCoche.getPosX()<839&&miCoche.getPosY()>-100&&miCoche.getPosY()<3||miCoche.getPosX()>870&&miCoche.getPosX()<1000&&miCoche.getPosY()>0&&miCoche.getPosY()<750||miCoche.getPosX()>0&&miCoche.getPosX()<1000&&miCoche.getPosY()>650&&miCoche.getPosY()<750||miCoche.getPosX()>620&&miCoche.getPosX()<1000&&miCoche.getPosY()>570&&miCoche.getPosY()<750||miCoche.getPosX()>124&&miCoche.getPosX()<770&&miCoche.getPosY()>120&&miCoche.getPosY()<460||miCoche.getPosX()>124&&miCoche.getPosX()<480&&miCoche.getPosY()>421&&miCoche.getPosY()<550){
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
		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
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
	public static void aplicarFuerza( double fuerza, Coche coche ) {
		 double fuerzaRozamiento = calcFuerzaRozamiento( Coche.getMasa() ,
		 Coche.getFuerzaRozamientoSuelo(), Coche.getFuerzaRozamientoAire(), coche.getVelocidad() );
		 double aceleracion = calcAceleracionConFuerza( fuerza+fuerzaRozamiento, Coche.getMasa() );
		 if (fuerza==0) {
		 // No hay fuerza, solo se aplica el rozamiento
		 double velAntigua = coche.getVelocidad();
		 coche.acelera( aceleracion, 0.04 );
		 if (velAntigua>=0 && coche.getVelocidad()<0
		 || velAntigua<=0 && coche.getVelocidad()>0) {
		 coche.setVelocidad(0); // Si se está frenando, se para (no anda al revés)
		 }
		 } else {
		 coche.acelera( aceleracion, 0.04 );
		 } 
	}
}