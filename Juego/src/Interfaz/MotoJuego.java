package Interfaz;


public class MotoJuego extends Moto {
	private JLabelMoto miGrafico;  // Etiqueta gráfica del coche
	
		/**  Crea una nueva moto de juego
		 */
	public MotoJuego() {
		miGrafico = new JLabelMoto();
	}
	
		/** Devuelve el JLabel gráfico asociado a la moto de juego
		 * @return	Etiqueta gráfica de la moto
		 */
	public JLabelMoto getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posición X de la moto para poder dibujarlo en el gráfico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posición Y de la moto para poder dibujarlo en el gráfico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
		// miGrafico.repaint();  // Al cambiar la location, Swing redibuja automáticamente
	}
	
		/** Cambia la dirección de la moto para poder dibujarlo en el gráfico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint();  // Necesario porque Swing no redibuja al cambiar el giro (no pasa nada en el JLabel)
	}


}

