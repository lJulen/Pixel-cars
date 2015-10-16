package ud.prog3.pr02;

/** Clase para definir instancias l�gicas de coches con posici�n, direcci�n y velocidad.
 * @author Andoni Egu�luz
 * Facultad de Ingenier�a - Universidad de Deust (2014)
 */
public class Coche {
	protected double miVelocidad;  // Velocidad en pixels/segundo
	protected double miDireccionActual;  // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	protected double posX;  // Posici�n en X (horizontal)
	protected double posY;  // Posici�n en Y (vertical)
	protected String piloto;  // Nombre de piloto
	static double masa;
	static double fuerzaRozamientoAire;
	static double fuerzaRozamientoSuelo;
	static double coefRozSuelo;
	static double coefRozAire;
	static double FUERZA_BASE_ADELANTE;
	static double FUERZA_BASE_ATRAS;
	// Constructores
	
	public static double getFUERZA_BASE_ADELANTE() {
		return FUERZA_BASE_ADELANTE;
	}

	public static void setFUERZA_BASE_ADELANTE(double fUERZA_BASE_ADELANTE) {
		FUERZA_BASE_ADELANTE = fUERZA_BASE_ADELANTE;
	}

	public static double getFUERZA_BASE_ATRAS() {
		return FUERZA_BASE_ATRAS;
	}

	public static void setFUERZA_BASE_ATRAS(double fUERZA_BASE_ATRAS) {
		FUERZA_BASE_ATRAS = fUERZA_BASE_ATRAS;
	}

	public Coche() {
		miVelocidad = 0;
		miDireccionActual = 0;
		posX = 300;
		posY = 300;
		masa=1;
		fuerzaRozamientoSuelo=15.5;
		fuerzaRozamientoAire=0.35;
		FUERZA_BASE_ADELANTE=2000;
		FUERZA_BASE_ATRAS=1000;
		
	}
	
	public double getMiVelocidad() {
		return miVelocidad;
	}

	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}

	public double getMiDireccionActual() {
		return miDireccionActual;
	}

	public void setMiDireccionActual(double miDireccionActual) {
		this.miDireccionActual = miDireccionActual;
	}

	public static  double getMasa() {
		return masa;
	}

	public void setMasa(double masa) {
		this.masa = masa;
	}

	public static double getFuerzaRozamientoAire() {
		return fuerzaRozamientoAire;
	}

	public void setFuerzaRozamientoAire(double fuerzaRozamientoAire) {
		this.fuerzaRozamientoAire = fuerzaRozamientoAire;
	}

	public static double getFuerzaRozamientoSuelo() {
		return fuerzaRozamientoSuelo;
	}

	public void setFuerzaRozamientoSuelo(double fuerzaRozamientoSuelo) {
		this.fuerzaRozamientoSuelo = fuerzaRozamientoSuelo;
	}

	public double getCoefRozSuelo() {
		return coefRozSuelo;
	}

	public void setCoefRozSuelo(double coefRozSuelo) {
		this.coefRozSuelo = coefRozSuelo;
	}

	public double getCoefRozAire() {
		return coefRozAire;
	}

	public void setCoefRozAire(double coefRozAire) {
		this.coefRozAire = coefRozAire;
	}

	/** Devuelve la velocidad actual del coche en p�xeles por segundo
	 * @return	velocidad
	 */
	public double getVelocidad() {
		return miVelocidad;
	}

	/** Cambia la velocidad actual del coche
	 * @param miVelocidad
	 */
	public void setVelocidad( double miVelocidad ) {
		this.miVelocidad = miVelocidad;
	}

	public double getDireccionActual() {
		return miDireccionActual;
	}

	public void setDireccionActual( double dir ) {
		// if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
	public void setPosY( double posY ) {
		this.posY = posY; 
	}
	
	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}


	/** Cambia la velocidad actual del coche
	 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
	 * @param tiempo	Tiempo transcurrido en segundos
	 */
	public void acelera( double aceleracion, double tiempo ) {
		miVelocidad = MundoJuego.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
	}
	
	/** Cambia la direcci�n actual del coche
	 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}
	
	/** Cambia la posici�n del coche dependiendo de su velocidad y direcci�n
	 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
	 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + MundoJuego.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		setPosY( posY + MundoJuego.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
	}
	
	@Override
	public String toString() {
		return piloto + " (" + posX + "," + posY + ") - " +
			   "Velocidad: " + miVelocidad + " ## Direcci�n: " + miDireccionActual; 
	}
		 /** Devuelve la fuerza de aceleraci�n del coche, de acuerdo al motor definido en la pr�ctica 2
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
