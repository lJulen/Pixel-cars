package Interfaz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelMoto extends JLabel {
	private static final long serialVersionUID = 1L;  // Para serializaci�n
	public static final int TAMANYO_COCHE = 65;  // p�xels (igual ancho que algo)
	public static final int RADIO_ESFERA_COCHE = 20;  // Radio en p�xels del bounding circle del coche (para choques)
	private static final boolean DIBUJAR_ESFERA_COCHE = true;  // Dibujado (para depuraci�n) del bounding circle de choque del coche
	private double miGiro = Math.PI/2;
	
		/** Construye y devuelve el JLabel del coche con su gr�fico y tama�o
		 */
	public JLabelMoto() {
		
		try {
	
			setIcon( new ImageIcon( JLabelMoto.class.getResource( "/Imagen/MotoJuego.png" ).toURI().toURL() ) );
		} catch (Exception e) {
			System.err.println( "Error en carga de recurso: Moto.png no encontrado" );
			e.printStackTrace();
		}
		setBounds( 0, 0, TAMANYO_COCHE, TAMANYO_COCHE );
	}
	
	// giro
	

		/** Cambia el giro del JLabel
		 * @param gradosGiro	Grados a los que tiene que "apuntar" el coche,
		 * 						considerados con el 0 en el eje OX positivo,
		 * 						positivo en sentido antihorario, negativo horario.
		 */
	public void setGiro( double gradosGiro ) {
		// De grados a radianes...
		miGiro = gradosGiro/180*Math.PI;
		// El giro en la pantalla es en sentido horario (inverso):
		miGiro = -miGiro;  // Cambio el sentido del giro
		// Y el gr�fico del coche apunta hacia arriba (en lugar de derecha OX)
		miGiro = miGiro + Math.PI/2; // Sumo 90� para que corresponda al origen OX
	}

	// Redefinici�n del paintComponent para que se escale y se rote el gr�fico
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);   // En este caso no nos sirve el pintado normal de un JLabel
		Image Imagen = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Escalado m�s fino con estos 3 par�metros:
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Prepara rotaci�n (siguientes operaciones se rotar�n)
        g2.rotate( miGiro, TAMANYO_COCHE/2, TAMANYO_COCHE/2 ); // getIcon().getIconWidth()/2, getIcon().getIconHeight()/2 );
        // Dibujado de la imagen
        g2.drawImage( Imagen, 0, 0, TAMANYO_COCHE, TAMANYO_COCHE, null );
        if (DIBUJAR_ESFERA_COCHE) g2.drawOval( TAMANYO_COCHE/2-RADIO_ESFERA_COCHE, TAMANYO_COCHE/2-RADIO_ESFERA_COCHE,
        		RADIO_ESFERA_COCHE*2, RADIO_ESFERA_COCHE*2 );
	}
}
