package Interfaz;

/** Clase para definir instancias lógicas de coches con posición, dirección y velocidad.
 * @author Andoni Eguíluz
 * Facultad de Ingeniería - Universidad de Deust (2014)
 */
public class Coche extends Vehiculo{
	
	// Constructores

	public Coche() {
		super();
		miVelocidad = 0;
		miDireccionActual = 0;
		posX = 300;
		posY = 300;
		masa=1;
		fuerzaRozamientoSuelo=15.5;
		fuerzaRozamientoAire=0.35;
		FUERZA_BASE_ADELANTE=1000;
		FUERZA_BASE_ATRAS=500;	
	
	}

	



}



