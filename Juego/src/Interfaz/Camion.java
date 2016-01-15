package Interfaz;

public class Camion
{
	protected double miVelocidad;  // Velocidad en pixels/segundo
	protected double miDireccionActual;  // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	protected double posX;  // Posici�n en X (horizontal)
	protected double posY;  // Posici�n en Y (vertical)
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
	
		/** Devuelve fuerza adelante del cami�n 
		 ** @return FUERZA_BASE_ADELANTE
		 */
	public static double getFUERZA_BASE_ADELANTE() {
		return FUERZA_BASE_ADELANTE;
	}
	
		/** Cambia la fuerza adelante del cami�n
		 * @param fUERZA_BASE_ADELANTE
		 */
	public static void setFUERZA_BASE_ADELANTE(double fUERZA_BASE_ADELANTE) {
		FUERZA_BASE_ADELANTE = fUERZA_BASE_ADELANTE;
	}
	
		/** Devuelve fuerza trasera del cami�n 
		 ** @return FUERZA_BASE_ATRAS
		 */
	public static double getFUERZA_BASE_ATRAS() {
		return FUERZA_BASE_ATRAS;
	}
	
		/** Cambia la fuerza trasera del cami�n
		 * @param fUERZA_BASE_ATRAS
		 */
	public static void setFUERZA_BASE_ATRAS(double fUERZA_BASE_ATRAS) {
		FUERZA_BASE_ATRAS = fUERZA_BASE_ATRAS;
	}
	
		/** Devuelve la velocidad del cami�n 
		 ** @return	miVelocidad
		 */
	public double getMiVelocidad() {
		return miVelocidad;
	}
	
		/** Cambia la velocidad del cami�n
		 * @param miVelocidad
		 */
	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}
	
		/** Devuelve la direcci�n actual del cami�n 
		 ** @return	miDireccionActual
		 */
	public double getMiDireccionActual() {
		return miDireccionActual;
	}
	
		/** Cambia la direcci�n actual del cami�n
		 * @param miDireccionActual
		 */
	public void setMiDireccionActual(double miDireccionActual) {
		this.miDireccionActual = miDireccionActual;
	}
	
		/** Devuelve la masa del cami�n 
		 ** @return	masa
		 */
	public static  double getMasa() {
		return masa;
	}
	
		/** Cambia la masa del cami�n
		 * @param masa
		 */
	public void setMasa(double masa) {
		this.masa = masa;
	}

		/** Devuelve la fuerza de rozamiento del aire del cami�n 
		 ** @return	fuerzaRozamientoAire
		 */
	public static double getFuerzaRozamientoAire() {
		return fuerzaRozamientoAire;
	}
	
		/** Cambia la fuerza de rozamiento del aire del cami�n
		 * @param fuerzaRozamientoAire
		 */
	public void setFuerzaRozamientoAire(double fuerzaRozamientoAire) {
		this.fuerzaRozamientoAire = fuerzaRozamientoAire;
	}
	
		/** Devuelve la fuerza de rozamiento del suelo del cami�n 
		 ** @return	fuerzaRozamientoSuelo
		 */
	public static double getFuerzaRozamientoSuelo() {
		return fuerzaRozamientoSuelo;
	}
		
		/** Cambia la fuerza de rozamiento del suelo del cami�n
		 * @param fuerzaRozamientoSuelo
		 */
	public void setFuerzaRozamientoSuelo(double fuerzaRozamientoSuelo) {
		this.fuerzaRozamientoSuelo = fuerzaRozamientoSuelo;
	}
	
		/** Devuelve el coeficiente de rozamiento del suelo del cami�n 
		 ** @return	coefRozSuelo
		 */
	public double getCoefRozSuelo() {
		return coefRozSuelo;
	}
	
		/** Cambia el coeficiente de rozamiento del suelo del cami�n
		 * @param coefRozSuelo
		 */
	public void setCoefRozSuelo(double coefRozSuelo) {
		this.coefRozSuelo = coefRozSuelo;
	}
		
		/** Devuelve el coeficiente de rozamiento del aire del cami�n 
		 ** @return	coefRozAire
		 */
	public double getCoefRozAire() {
		return coefRozAire;
	}
	
		/** Cambia el coeficiente de rozamiento del aire del cami�n
		 * @param coefRozAire
		 */
	public void setCoefRozAire(double coefRozAire) {
		this.coefRozAire = coefRozAire;
	}

		/** Devuelve la velocidad actual del cami�n en p�xeles por segundo
		 * @return	velocidad
		 */
	public double getVelocidad() {
		return miVelocidad;
	}

		/** Cambia la velocidad actual del cami�n
		 * @param miVelocidad
		 */
	public void setVelocidad( double miVelocidad ) {
		this.miVelocidad = miVelocidad;
	}
	
		/** Devuelve la direcci�n actual del cami�n 
		 * @return	miDireccionActual
		 */
	public double getDireccionActual() {
		return miDireccionActual;
	}
	
		/** Cambia la direcci�n del cami�n
		 * @param dir
		 */
	public void setDireccionActual( double dir ) {
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}
	
		/** Devuelve la posici�n X actual del cami�n 
		 * @return	posX
		 */
	public double getPosX() {
		return posX;
	}
	
		/** Devuelve la posici�n Y actual del cami�n
		 * @return	posY
		 */
	public double getPosY() {
		return posY;
	}
	
		/** Cambia la posici�n X e Y actual del cami�n
		 * @param posX
		 * @param posY
		 */
	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
		/** Cambia la posici�n X actual del cami�n
		 * @param posX
		 */
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
		/** Cambia la posici�n Y actual del cami�n
		 * @param posY
		 */
	public void setPosY( double posY ) {
		this.posY = posY; 
	}

		/** Cambia la velocidad actual del cami�n
		 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
		 * @param tiempo	Tiempo transcurrido en segundos
		 */
	public void acelera( double aceleracion, double tiempo ) {
		miVelocidad = MundoJuegoCamion.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
	}
	
		/** Cambia la direcci�n actual del cami�n
		 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
		 * 	Considerando positivo giro antihorario, negativo giro horario
		 */
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}
	
		/** Cambia la posici�n del cami�n dependiendo de su velocidad y direcci�n
		 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
		 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + MundoJuegoCamion.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		setPosY( posY + MundoJuegoCamion.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
	}
	
		 /** Devuelve la fuerza de aceleraci�n del cami�n
		 * @return Fuerza de aceleraci�n en Newtixels
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
	
	/**Devuelve la fuerza de aceleraci�n del cami�n
	 * @return Fuerza de aceleraci�n en Newtixels
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