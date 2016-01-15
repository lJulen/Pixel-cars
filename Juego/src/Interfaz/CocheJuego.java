package Interfaz;

public class CocheJuego extends Coche {
	private JLabelCoche miGrafico;  // Etiqueta gráfica del coche
	
		/**  Crea un nuevo coche de juego
		 */
	public CocheJuego() {
		miGrafico = new JLabelCoche();
	}
	
		/** Devuelve el JLabel gráfico asociado al coche de juego
		 * @return	Etiqueta gráfica del coche
		 */
	public JLabelCoche getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posición X del coche para poder dibujarlo en el gráfico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posición Y del coche para poder dibujarlo en el gráfico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la dirección del coche para poder dibujarlo en el gráfico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint(); 
	}
}
