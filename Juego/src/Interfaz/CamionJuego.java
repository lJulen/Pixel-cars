package Interfaz;

public class CamionJuego extends Camion {
	private JLabelCamion miGrafico;  // Etiqueta gráfica del coche
	
		/**  Crea un nuevo camión de juego
		 */
	public CamionJuego() {
		miGrafico = new JLabelCamion();
	}
	
		/** Devuelve el JLabel gráfico asociado al camión de juego
		 * @return	Etiqueta gráfica del camión
		 */
	public JLabelCamion getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posición X del camión para poder dibujarlo en el gráfico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posición Y del camión para poder dibujarlo en el gráfico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
	}

		/** Cambia la dirección del camión para poder dibujarlo en el gráfico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint();
	}

}
