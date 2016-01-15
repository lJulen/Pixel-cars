package Interfaz;

	public class Moto {
		protected double miVelocidad;  // Velocidad en pixels/segundo
		protected double miDireccionActual;  // Dirección en la que estoy mirando en grados (de 0 a 360)
		protected double posX;  // Posición en X (horizontal)
		protected double posY;  // Posición en Y (vertical)
		protected String piloto;  // Nombre de piloto
		public static double masa;
		public static double fuerzaRozamientoAire;
		public static double fuerzaRozamientoSuelo;
		public static double coefRozSuelo;
		public static double coefRozAire;
		public static double FUERZA_BASE_ADELANTE;
		public static double FUERZA_BASE_ATRAS;
		
		public Moto() {
			miVelocidad = 0;
			miDireccionActual = 0;
			posX = 260;
			posY = 600;
			masa=0.75;
			fuerzaRozamientoSuelo=17.5;
			fuerzaRozamientoAire=0.2;
			FUERZA_BASE_ADELANTE=1500;
			FUERZA_BASE_ATRAS=500;
			
		}
		// Constructores
		
			/** Devuelve fuerza adelante de la moto 
			 ** @return FUERZA_BASE_ADELANTE
			 */
		public static double getFUERZA_BASE_ADELANTE() {
			return FUERZA_BASE_ADELANTE;
		}
		
			/** Cambia la fuerza adelante de la moto
			 * @param fUERZA_BASE_ADELANTE
			 */
		public static void setFUERZA_BASE_ADELANTE(double fUERZA_BASE_ADELANTE) {
			FUERZA_BASE_ADELANTE = fUERZA_BASE_ADELANTE;
		}
		
			/** Devuelve fuerza trasera de la moto 
			 ** @return FUERZA_BASE_ATRAS
			 */
		public static double getFUERZA_BASE_ATRAS() {
			return FUERZA_BASE_ATRAS;
		}
		
			/** Cambia la fuerza trasera de la moto
			 * @param fUERZA_BASE_ATRAS
			 */
		public static void setFUERZA_BASE_ATRAS(double fUERZA_BASE_ATRAS) {
			FUERZA_BASE_ATRAS = fUERZA_BASE_ATRAS;
		}
		
			/** Devuelve la velocidad de la moto
			 ** @return	miVelocidad
			 */
		public double getMiVelocidad() {
			return miVelocidad;
		}
		
			/** Cambia la velocidad de la moto
			 * @param miVelocidad
			 */
		public void setMiVelocidad(double miVelocidad) {
			this.miVelocidad = miVelocidad;
		}
		
			/** Devuelve la dirección actual de la moto 
			 ** @return	miDireccionActual
			 */
		public double getMiDireccionActual() {
			return miDireccionActual;
		}
		
			/** Cambia la dirección actual de la moto
			 ** @param miDireccionActual
			 */
		public void setMiDireccionActual(double miDireccionActual) {
			this.miDireccionActual = miDireccionActual;
		}
		
			/** Devuelve la masa de la moto
			 ** @return	masa
			 */
		public static  double getMasa() {
			return masa;
		}
		
			/** Cambia la masa del moto
			 * @param masa
			 */
		public void setMasa(double masa) {
			this.masa = masa;
		}
		
			/** Devuelve la fuerza de rozamiento del aire de la moto 
			 ** @return	fuerzaRozamientoAire
			 */
		public static double getFuerzaRozamientoAire() {
			return fuerzaRozamientoAire;
		}
		
			/** Cambia la fuerza de rozamiento del aire de la moto
			 * @param fuerzaRozamientoAire
			 */
		public void setFuerzaRozamientoAire(double fuerzaRozamientoAire) {
			this.fuerzaRozamientoAire = fuerzaRozamientoAire;
		}
		
			/** Devuelve la fuerza de rozamiento del suelo de la moto
			 ** @return	fuerzaRozamientoSuelo
			 */
		public static double getFuerzaRozamientoSuelo() {
			return fuerzaRozamientoSuelo;
		}
		
			/** Cambia la fuerza de rozamiento del suelo de la moto
			 * @param fuerzaRozamientoSuelo
			 */
		public void setFuerzaRozamientoSuelo(double fuerzaRozamientoSuelo) {
			this.fuerzaRozamientoSuelo = fuerzaRozamientoSuelo;
		}
		
			/** Devuelve el coeficiente de rozamiento del suelo de la moto
			 ** @return	coefRozSuelo
			 */
		public double getCoefRozSuelo() {
			return coefRozSuelo;
		}
		
			/** Cambia el coeficiente de rozamiento del suelo de la moto
			 * @param coefRozSuelo
			 */
		public void setCoefRozSuelo(double coefRozSuelo) {
			this.coefRozSuelo = coefRozSuelo;
		}
		
			/** Devuelve el coeficiente de rozamiento del aire del coche 
			 ** @return	coefRozAire
			 */
		public double getCoefRozAire() {
			return coefRozAire;
		}
		
			/** Cambia el coeficiente de rozamiento del aire de la moto
			 * @param coefRozAire
			 */
		public void setCoefRozAire(double coefRozAire) {
			this.coefRozAire = coefRozAire;
		}

			/** Devuelve la velocidad actual de la moto en píxeles por segundo
			 * @return	velocidad
			 */
		public double getVelocidad() {
			return miVelocidad;
		}

			/** Cambia la velocidad actual de la moto
			 * @param miVelocidad
			 */
		public void setVelocidad( double miVelocidad ) {
			this.miVelocidad = miVelocidad;
		}
		
			/** Devuelve la dirección actual de la moto
			 ** @return	miDireccionActual
			 */
		public double getDireccionActual() {
			return miDireccionActual;
		}
		
			/** Cambia la dirección de la moto
			 * @param dir
			 */
		public void setDireccionActual( double dir ) {
			if (dir > 360) dir = dir - 360;
			miDireccionActual = dir;
		}
		
			/** Devuelve la posición X actual de la moto
			 * @return	posX
			 */
		public double getPosX() {
			return posX;
		}
		
			/** Devuelve la posición Y actual de la moto
			 * @return	posY
			 */
		public double getPosY() {
			return posY;
		}
		
			/** Cambia la posición X e Y actual dela moto
			 * @param posX
			 * @param posY
			 */
		public void setPosicion( double posX, double posY ) {
			setPosX( posX );
			setPosY( posY );
		}
		
			/** Cambia la posición X actual de la moto
			 * @param posX
			 */
		public void setPosX( double posX ) {
			this.posX = posX; 
		}
		
			/** Cambia la posición Y actual de la moto
			 * @param posY
			 */
		public void setPosY( double posY ) {
			this.posY = posY; 
		}

			/** Cambia la velocidad actual de la moto
			 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
			 * @param tiempo	Tiempo transcurrido en segundos
			 */
		public void acelera( double aceleracion, double tiempo ) {
			miVelocidad = MundoJuegoMoto.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
		}
		
			/** Cambia la dirección actual de la moto
			 * @param giro	Angulo de giro a sumar o restar de la dirección actual, en grados (-180 a +180)
			 * 				Considerando positivo giro antihorario, negativo giro horario
			 */
		public void gira( double giro ) {
			setDireccionActual( miDireccionActual + giro );
		}
		
			/** Cambia la posición de la moto dependiendo de su velocidad y dirección
			 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
			 */
		public void mueve( double tiempoDeMovimiento ) {
			setPosX( posX + MundoJuegoMoto.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
			setPosY( posY + MundoJuegoMoto.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		}

			 /** Devuelve la fuerza de aceleración del coche, de acuerdo al motor definido en la práctica 2
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
		
			/**Devuelve la fuerza de aceleración de la moto
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
