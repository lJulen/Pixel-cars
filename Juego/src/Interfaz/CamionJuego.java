package Interfaz;

public class CamionJuego extends Camion {
	private JLabelCamion miGrafico;  // Etiqueta gr�fica del coche
	
		/**  Crea un nuevo cami�n de juego
		 */
	public CamionJuego() {
		miGrafico = new JLabelCamion();
	}
	
		/** Devuelve el JLabel gr�fico asociado al cami�n de juego
		 * @return	Etiqueta gr�fica del cami�n
		 */
	public JLabelCamion getGrafico() {
		return miGrafico;
	}
	
		/** Cambia la posici�n X del cami�n para poder dibujarlo en el gr�fico
		 * @param posX
		 */
	@Override
	public void setPosX(double posX) {
		super.setPosX(posX);
		miGrafico.setLocation( (int)posX, (int)posY );
	}
	
		/** Cambia la posici�n Y del cami�n para poder dibujarlo en el gr�fico
		 * @param posY
		 */	
	@Override
	public void setPosY(double posY) {
		super.setPosY(posY);
		miGrafico.setLocation( (int)posX, (int)posY );
	}

		/** Cambia la direcci�n del cami�n para poder dibujarlo en el gr�fico
		 * @param dir
		 */
	@Override
	public void setDireccionActual( double dir ) {
		super.setDireccionActual(dir);
		miGrafico.setGiro( miDireccionActual );
		miGrafico.repaint();
	}

}
