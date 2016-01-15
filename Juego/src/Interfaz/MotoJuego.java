package Interfaz;


public class MotoJuego extends Moto {
	private JLabelMoto miGrafico;  // Etiqueta gr�fica del coche
	
		/**  Crea una nueva moto de juego
		 */
	public MotoJuego() {
		miGrafico = new JLabelMoto();
	}
	
		/** Devuelve el JLabel gr�fico asociado a la moto de juego
		 * @return	Etiqueta gr�fica de la moto
		 */
	public JLabelMoto getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posici�n X de la moto para poder dibujarlo en el gr�fico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posici�n Y de la moto para poder dibujarlo en el gr�fico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
		// miGrafico.repaint();  // Al cambiar la location, Swing redibuja autom�ticamente
	}
	
		/** Cambia la direcci�n de la moto para poder dibujarlo en el gr�fico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint();  // Necesario porque Swing no redibuja al cambiar el giro (no pasa nada en el JLabel)
	}


}

