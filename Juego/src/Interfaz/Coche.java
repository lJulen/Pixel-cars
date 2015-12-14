package Interfaz;

/** Clase para definir instancias l�gicas de coches con posici�n, direcci�n y velocidad.
 * @author Andoni Egu�luz
 * Facultad de Ingenier�a - Universidad de Deust (2014)
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



