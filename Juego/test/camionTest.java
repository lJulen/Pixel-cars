import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Interfaz.Camion;
import Interfaz.Moto;

public class camionTest {

	Camion c;
	@Before
	public void setUp() throws Exception {
		c = new Camion();
	}

	@Test
	public void testGira() {
		c.gira( 10 );
		assertEquals( 10.0, c.getDireccionActual(), 0.0 );
		c.gira( 360 );
		assertEquals( 10.0, c.getDireccionActual(), 0.0 );
	}

	@Test
	public void testFuerzaAceleracionAdelante() {
		double[] tablaVel =    { -600, -500, -150,  -75,   0,  125, 250, 500, 750, 875, 1000 };
		double[] tablaFuerza = {    1,    1,    1, 0.75, 0.5, 0.75,   1,   1,   1, 0.5,    0 };  
		for (int i=0;i<tablaVel.length;i++) {
			c.setVelocidad( tablaVel[i] );
			assertEquals( "Velocidad " + tablaVel[i], tablaFuerza[i]*Camion.FUERZA_BASE_ADELANTE, c.fuerzaAceleracionAdelante(), 0.0000001 );
		}
	}

	@Test
	public void testAcelera() {
		c.setVelocidad(0);
		c.acelera( 100, 1 );
		assertEquals( 100, c.getVelocidad(), 0.00000001 );
		c.setVelocidad(100);
		c.acelera( 50, 0.02 );
		assertEquals( 101, c.getVelocidad(), 0.00000001 );
		c.setVelocidad(100);
		c.acelera( -100, 1 );
		assertEquals( 0, c.getVelocidad(), 0.00000001 );
		c.setVelocidad(50);
		c.acelera( -200, 0.5 );
		assertEquals( -50, c.getVelocidad(), 0.00000001 );
	}
	
	@Test
	public void testPosicion(){
		c.setPosicion(5.0, 6.0);
		assertEquals(5.0, c.getPosX(),0.0);
		assertEquals(6.0, c.getPosY(),0.0);
	}
	
	@Test
	public void testDireccion(){
		c.setDireccionActual(370.0);
		assertEquals(10.0, c.getDireccionActual(),0.0 )	;

	}

}	
