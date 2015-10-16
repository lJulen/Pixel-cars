package ud.prog3.pr02;

import java.util.Date;


/** Clase para definir instancias l�gicas de coches con posici�n, direcci�n y velocidad.
 * @author Andoni Egu�luz
 * Facultad de Ingenier�a - Universidad de Deusto (2014)
 */
public class Estrella{
	
	protected double posX;  // Posici�n en X (horizontal)
	protected double posY;  // Posici�n en Y (vertical)
	protected double miDireccionActual;  // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	private JLabelEstrella miGrafico;  // Etiqueta gr�fica de la estrella
	private Date creacion;
	
	// Constructores
	public Estrella() {
			miDireccionActual = 0;
			miGrafico = new JLabelEstrella();
	}
	
	/** Devuelve el JLabel gr�fico asociado al coche de juego
	 * @return	Etiqueta gr�fica del coche
	 */
	public JLabelEstrella getGrafico() {
		return miGrafico;
	}
	
	
	
	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}
	
	public void setDireccionActual( double dir ) {
		// if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}



	public double getPosX() {
		return posX;
	}



	public void setPosX(double posX) {
		this.posX = posX;
	}



	public double getPosY() {
		return posY;
	}



	public void setPosY(double posY) {
		this.posY = posY;
	}  
	
	public void setPos(double posX, double posY) {
		
		miGrafico.setLocation( (int)posX, (int)posY );
		miGrafico.repaint();  
	}
	
	
	
	
	
	
}