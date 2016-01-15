package Interfaz;

public class Camion
{
	protected double miVelocidad;  // Velocidad en pixels/segundo
	protected double miDireccionActual;  // Dirección en la que estoy mirando en grados (de 0 a 360)
	protected double posX;  // Posición en X (horizontal)
	protected double posY;  // Posición en Y (vertical)
	public static double masa;
	public static double fuerzaRozamientoAire;
	public static double fuerzaRozamientoSuelo;
	public static double coefRozSuelo;
	public static double coefRozAire;
	public static double FUERZA_BASE_ADELANTE;
	public static double FUERZA_BASE_ATRAS;
	
	public Camion() {
		miVelocidad = 0;
		miDireccionActual = 0;
		posX = 300;
		posY = 300;
		masa=2;
		fuerzaRozamientoSuelo=20;
		fuerzaRozamientoAire=0.6;
		FUERZA_BASE_ADELANTE=800;
		FUERZA_BASE_ATRAS=200;
		
	}
	// Constructores
	
		/** Devuelve fuerza adelante del camión 
		 ** @return FUERZA_BASE_ADELANTE
		 */
	public static double getFUERZA_BASE_ADELANTE() {
		return FUERZA_BASE_ADELANTE;
	}
	
		/** Cambia la fuerza adelante del camión
		 * @param fUERZA_BASE_ADELANTE
		 */
	public static void setFUERZA_BASE_ADELANTE(double fUERZA_BASE_ADELANTE) {
		FUERZA_BASE_ADELANTE = fUERZA_BASE_ADELANTE;
	}
	
		/** Devuelve fuerza trasera del camión 
		 ** @return FUERZA_BASE_ATRAS
		 */
	public static double getFUERZA_BASE_ATRAS() {
		return FUERZA_BASE_ATRAS;
	}
	
		/** Cambia la fuerza trasera del camión
		 * @param fUERZA_BASE_ATRAS
		 */
	public static void setFUERZA_BASE_ATRAS(double fUERZA_BASE_ATRAS) {
		FUERZA_BASE_ATRAS = fUERZA_BASE_ATRAS;
	}
	
		/** Devuelve la velocidad del camión 
		 ** @return	miVelocidad
		 */
	public double getMiVelocidad() {
		return miVelocidad;
	}
	
		/** Cambia la velocidad del camión
		 * @param miVelocidad
		 */
	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}
	
		/** Devuelve la dirección actual del camión 
		 ** @return	miDireccionActual
		 */
	public double getMiDireccionActual() {
		return miDireccionActual;
	}
	
		/** Cambia la dirección actual del camión
		 * @param miDireccionActual
		 */
	public void setMiDireccionActual(double miDireccionActual) {
		this.miDireccionActual = miDireccionActual;
	}
	
		/** Devuelve la masa del camión 
		 ** @return	masa
		 */
	public static  double getMasa() {
		return masa;
	}
	
		/** Cambia la masa del camión
		 * @param masa
		 */
	public void setMasa(double masa) {
		this.masa = masa;
	}

		/** Devuelve la fuerza de rozamiento del aire del camión 
		 ** @return	fuerzaRozamientoAire
		 */
	public static double getFuerzaRozamientoAire() {
		return fuerzaRozamientoAire;
	}
	
		/** Cambia la fuerza de rozamiento del aire del camión
		 * @param fuerzaRozamientoAire
		 */
	public void setFuerzaRozamientoAire(double fuerzaRozamientoAire) {
		this.fuerzaRozamientoAire = fuerzaRozamientoAire;
	}
	
		/** Devuelve la fuerza de rozamiento del suelo del camión 
		 ** @return	fuerzaRozamientoSuelo
		 */
	public static double getFuerzaRozamientoSuelo() {
		return fuerzaRozamientoSuelo;
	}
		
		/** Cambia la fuerza de rozamiento del suelo del camión
		 * @param fuerzaRozamientoSuelo
		 */
	public void setFuerzaRozamientoSuelo(double fuerzaRozamientoSuelo) {
		this.fuerzaRozamientoSuelo = fuerzaRozamientoSuelo;
	}
	
		/** Devuelve el coeficiente de rozamiento del suelo del camión 
		 ** @return	coefRozSuelo
		 */
	public double getCoefRozSuelo() {
		return coefRozSuelo;
	}
	
		/** Cambia el coeficiente de rozamiento del suelo del camión
		 * @param coefRozSuelo
		 */
	public void setCoefRozSuelo(double coefRozSuelo) {
		this.coefRozSuelo = coefRozSuelo;
	}
		
		/** Devuelve el coeficiente de rozamiento del aire del camión 
		 ** @return	coefRozAire
		 */
	public double getCoefRozAire() {
		return coefRozAire;
	}
	
		/** Cambia el coeficiente de rozamiento del aire del camión
		 * @param coefRozAire
		 */
	public void setCoefRozAire(double coefRozAire) {
		this.coefRozAire = coefRozAire;
	}

		/** Devuelve la velocidad actual del camión en píxeles por segundo
		 * @return	velocidad
		 */
	public double getVelocidad() {
		return miVelocidad;
	}

		/** Cambia la velocidad actual del camión
		 * @param miVelocidad
		 */
	public void setVelocidad( double miVelocidad ) {
		this.miVelocidad = miVelocidad;
	}
	
		/** Devuelve la dirección actual del camión 
		 * @return	miDireccionActual
		 */
	public double getDireccionActual() {
		return miDireccionActual;
	}
	
		/** Cambia la dirección del camión
		 * @param dir
		 */
	public void setDireccionActual( double dir ) {
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}
	
		/** Devuelve la posición X actual del camión 
		 * @return	posX
		 */
	public double getPosX() {
		return posX;
	}
	
		/** Devuelve la posición Y actual del camión
		 * @return	posY
		 */
	public double getPosY() {
		return posY;
	}
	
		/** Cambia la posición X e Y actual del camión
		 * @param posX
		 * @param posY
		 */
	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
		/** Cambia la posición X actual del camión
		 * @param posX
		 */
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
		/** Cambia la posición Y actual del camión
		 * @param posY
		 */
	public void setPosY( double posY ) {
		this.posY = posY; 
	}

		/** Cambia la velocidad actual del camión
		 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
		 * @param tiempo	Tiempo transcurrido en segundos
		 */
	public void acelera( double aceleracion, double tiempo ) {
		miVelocidad = MundoJuegoCamion.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
	}
	
		/** Cambia la dirección actual del camión
		 * @param giro	Angulo de giro a sumar o restar de la dirección actual, en grados (-180 a +180)
		 * 	Considerando positivo giro antihorario, negativo giro horario
		 */
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}
	
		/** Cambia la posición del camión dependiendo de su velocidad y dirección
		 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
		 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + MundoJuegoCamion.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		setPosY( posY + MundoJuegoCamion.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
	}
	
		 /** Devuelve la fuerza de aceleración del camión
		 * @return Fuerza de aceleración en Newtixels
		 */
	public double fuerzaAceleracionAdelante() {
		if (miVelocidad<=-150) return FUERZA_BASE_ADELANTE;
		else if (miVelocidad<=0)
		return FUERZA_BASE_ADELANTE*(-miVelocidad/150*0.5+0.5);
		else if (miVelocidad<=250)
		return FUERZA_BASE_ADELANTE*(miVelocidad/250*0.5+0.5);
		else if (miVelocidad<=250)
		return FUERZA_BASE_ADELANTE*(miVelocidad/250*0.5+0.5);
		else if (miVelocidad<=750)
		return FUERZA_BASE_ADELANTE;
		else return FUERZA_BASE_ADELANTE*(-(miVelocidad-1000)/250);
	}
	
	/**Devuelve la fuerza de aceleración del camión
	 * @return Fuerza de aceleración en Newtixels
	 */
	public double fuerzaAceleracionAtras() {
		if (miVelocidad<=-150) return FUERZA_BASE_ATRAS;
		else if (miVelocidad<=0)
		return FUERZA_BASE_ATRAS*(-miVelocidad/150*0.5+0.5);
		else if (miVelocidad<=250)
		return FUERZA_BASE_ATRAS*(miVelocidad/250*0.5+0.5);
		else if (miVelocidad<=250)
		return FUERZA_BASE_ATRAS*(miVelocidad/250*0.5+0.5);
		else if (miVelocidad<=750)
		return FUERZA_BASE_ATRAS;
		else return FUERZA_BASE_ADELANTE*(-(miVelocidad-1000)/250);
	}
}