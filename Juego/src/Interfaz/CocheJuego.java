package Interfaz;

public class CocheJuego extends Coche {
	private JLabelCoche miGrafico;  // Etiqueta gr�fica del coche
	
		/**  Crea un nuevo coche de juego
		 */
	public CocheJuego() {
		miGrafico = new JLabelCoche();
	}
	
		/** Devuelve el JLabel gr�fico asociado al coche de juego
		 * @return	Etiqueta gr�fica del coche
		 */
	public JLabelCoche getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posici�n X del coche para poder dibujarlo en el gr�fico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posici�n Y del coche para poder dibujarlo en el gr�fico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la direcci�n del coche para poder dibujarlo en el gr�fico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint(); 
	}
}
