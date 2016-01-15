package Interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BaseDatos {

	// ------------------------------------
	// VALIDO PARA CUALQUIER BASE DE DATOS
	// ------------------------------------
	
	private static Connection connection = null;
	private static Statement statement = null;
	static String izena;

	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error de conexión!! No se ha podido conectar con " + nombreBD );
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}

	// ------------------------------------
	// PARTICULAR DEL CATALOGO MULTIMEDIA
	// ------------------------------------
	
	/** Crea una tabla de catálogo multimedia en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaBDU() {
		if (statement==null) return;
		try {
			statement.executeUpdate("create table USUARIOS " +
				"(nombre string, contra string)");
			
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	public static void crearTablaBDT() {
		if (statement==null) return;
		try {
			statement.executeUpdate("create table TIEMPOS " +
				"(izena String, vehiculo String, minuto int, segundo int, milisegundo int)");
			
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	public static boolean anyadirUsuario( Statement st, String nombre, String contra ) throws SQLException {
		
		boolean comprobacion;
		comprobacion = comprobarUsuario(nombre);
		if (comprobacion == true)
		{
			JOptionPane.showMessageDialog(null, "Usuario existente, introduce otro nombre de usuario");
			return false;
		}
		
		try {
			String sentSQL = "insert into USUARIOS values(" +
					"'" + nombre + "', " +
					"'" + contra + "')";
			JOptionPane.showMessageDialog(null, "Usuario añadido");
			System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false;  // Se tiene que añadir 1 - error si no
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean anyadirTiempo( Statement st, String vehiculo, int min, int seg, int mili )
	{
		
		try {
			String sentSQL = "insert into TIEMPOS values(" +
					"'" + izena + "', " +
					"'" + vehiculo + "', " +
					"'" + min + "', " +               
					"'" + seg + "', " +
					"'" + mili + "')";
			
			JOptionPane.showMessageDialog(null, "Partida registrada" + " " + "Nombre:" + " " + izena + " " + "Vehiculo:" + " " + vehiculo + " " + "Tiempo:" + min + ":" + seg + ":" + mili);
			
			
			int val = st.executeUpdate( sentSQL );
			if (val!=1) return false;  // Se tiene que añadir 1 - error si no
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//COCHEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	
	public static void devolverTiemposCoche() throws SQLException
	{
		
		boolean a = false;
		
		int minutes1 = 5;
		int seconds1 = 0;
		int miliseconds1 = 0;
		
		int minutes2 = 0;
		int seconds2 = 0;
		int miliseconds2 = 0;
		
		int minutes3 = 0;
		int seconds3 = 0;
		int miliseconds3 = 0;
		
		Statement stmt = connection.createStatement();
		String query = "select izena, vehiculo, minuto, segundo, milisegundo from TIEMPOS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
			if(rs.getString(1).equals(izena) )
			{
				if(rs.getString(2).equals("Coche"))
				{
					if(rs.getInt(3) < minutes1 )
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = minutes1;
						seconds2 = seconds1;
						miliseconds2 = seconds1;
						
						minutes1 = rs.getInt(3);
						seconds1 = rs.getInt(4);
						miliseconds1 = rs.getInt(5);
						a = true;
						
					}
					
					if (rs.getInt(3) == minutes1)
					{
						if (rs.getInt(4) < seconds1)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = minutes1;
							seconds2 = seconds1;
							miliseconds2 = seconds1;
							
							minutes1 = rs.getInt(3);
							seconds1 = rs.getInt(4);
							miliseconds1 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds1)
						{
							if(rs.getInt(5) < miliseconds1)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = minutes1;
								seconds2 = seconds1;
								miliseconds2 = seconds1;
								
								minutes1 = rs.getInt(3);
								seconds1 = rs.getInt(4);
								miliseconds1 = rs.getInt(5);
								a = true;
								
							}
						}
						
					}	
					
					
					
					if (a == false && rs.getInt(3) < minutes2)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = rs.getInt(3);
						seconds2 = rs.getInt(4);
						miliseconds2 = rs.getInt(5);	
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes2)
					{
						if (rs.getInt(4) < seconds2)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = rs.getInt(3);
							seconds2 = rs.getInt(4);
							miliseconds2 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds2)
						{
							if (rs.getInt(5) < miliseconds2)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = rs.getInt(3);
								seconds2 = rs.getInt(4);
								miliseconds2 = rs.getInt(5);
								a = true;
							}
						}
					}
					
					
					if (a == false && rs.getInt(3) < minutes3)
					{
						minutes3 = rs.getInt(3);
						seconds3 = rs.getInt(4);
						miliseconds3 = rs.getInt(5);
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes3)
					{
						if (rs.getInt(4) < seconds3)
						{
							minutes3 = rs.getInt(3);
							seconds3 = rs.getInt(4);
							miliseconds3 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds3)
						{
							if (rs.getInt(5) < miliseconds3)
							{
								minutes3 = rs.getInt(3);
								seconds3 = rs.getInt(4);
								miliseconds3 = rs.getInt(5);
								a = true;
							}
						}
					}
				}
				
				
			}
			
			
			Tiempo1Coche(minutes1, seconds1, miliseconds1);
			Tiempo2Coche(minutes2, seconds2, miliseconds2);
			Tiempo3Coche(minutes3, seconds3, miliseconds3);
			a = false;
			
		}
	}
	
	
	static String PrimerTimeCoche;
	public static void Tiempo1Coche(int minutes1, int seconds1, int miliseconds1)
	{
		PrimerTimeCoche = minutes1 + ":" + seconds1 + ":" + miliseconds1;	
	}
	
	public static String getPrimerTimeCoche()
	{
		return PrimerTimeCoche;
	}
	
	static String SegundoTimeCoche;
	public static void Tiempo2Coche(int minutes2, int seconds2, int miliseconds2)
	{
		SegundoTimeCoche = minutes2 + ":" + seconds2 + ":" + miliseconds2;
	}
	
	public static String getSegundoTimeCoche()
	{
		return SegundoTimeCoche;
	}
	
	static String TercerTimeCoche;
	public static void Tiempo3Coche(int minutes3, int seconds3, int miliseconds3)
	{
		TercerTimeCoche = minutes3 + ":" + seconds3 + ":" + miliseconds3;
	}
	
	public static String getTercerTimeCoche()
	{
		return TercerTimeCoche;
	}
	
	
	
	
	
	//MOTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	
	
	
	public static void devolverTiemposMoto() throws SQLException
	{
		
		boolean a = false;
		
		int minutes1 = 5;
		int seconds1 = 0;
		int miliseconds1 = 0;
		
		int minutes2 = 0;
		int seconds2 = 0;
		int miliseconds2 = 0;
		
		int minutes3 = 0;
		int seconds3 = 0;
		int miliseconds3 = 0;
		
		Statement stmt = connection.createStatement();
		String query = "select izena, vehiculo, minuto, segundo, milisegundo from TIEMPOS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
			if(rs.getString(1).equals(izena) )
			{
				if(rs.getString(2).equals("Moto"))
				{
					if(rs.getInt(3) < minutes1 )
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = minutes1;
						seconds2 = seconds1;
						miliseconds2 = seconds1;
						
						minutes1 = rs.getInt(3);
						seconds1 = rs.getInt(4);
						miliseconds1 = rs.getInt(5);
						a = true;
						
					}
					
					if (rs.getInt(3) == minutes1)
					{
						if (rs.getInt(4) < seconds1)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = minutes1;
							seconds2 = seconds1;
							miliseconds2 = seconds1;
							
							minutes1 = rs.getInt(3);
							seconds1 = rs.getInt(4);
							miliseconds1 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds1)
						{
							if(rs.getInt(5) < miliseconds1)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = minutes1;
								seconds2 = seconds1;
								miliseconds2 = seconds1;
								
								minutes1 = rs.getInt(3);
								seconds1 = rs.getInt(4);
								miliseconds1 = rs.getInt(5);
								a = true;
								
							}
						}
						
					}	
					
					
					
					if (a == false && rs.getInt(3) < minutes2)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = rs.getInt(3);
						seconds2 = rs.getInt(4);
						miliseconds2 = rs.getInt(5);	
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes2)
					{
						if (rs.getInt(4) < seconds2)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = rs.getInt(3);
							seconds2 = rs.getInt(4);
							miliseconds2 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds2)
						{
							if (rs.getInt(5) < miliseconds2)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = rs.getInt(3);
								seconds2 = rs.getInt(4);
								miliseconds2 = rs.getInt(5);
								a = true;
							}
						}
					}
					
					
					if (a == false && rs.getInt(3) < minutes3)
					{
						minutes3 = rs.getInt(3);
						seconds3 = rs.getInt(4);
						miliseconds3 = rs.getInt(5);
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes3)
					{
						if (rs.getInt(4) < seconds3)
						{
							minutes3 = rs.getInt(3);
							seconds3 = rs.getInt(4);
							miliseconds3 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds3)
						{
							if (rs.getInt(5) < miliseconds3)
							{
								minutes3 = rs.getInt(3);
								seconds3 = rs.getInt(4);
								miliseconds3 = rs.getInt(5);
								a = true;
							}
						}
					}
				}
				
				
			}
			
			
			Tiempo1Moto(minutes1, seconds1, miliseconds1);
			Tiempo2Moto(minutes2, seconds2, miliseconds2);
			Tiempo3Moto(minutes3, seconds3, miliseconds3);
			a = false;
			
		}
	}
	
	
	static String PrimerTimeMoto;
	public static void Tiempo1Moto(int minutes1, int seconds1, int miliseconds1)
	{
		PrimerTimeMoto = minutes1 + ":" + seconds1 + ":" + miliseconds1;	
	}
	
	public static String getPrimerTimeMoto()
	{
		return PrimerTimeMoto;
	}
	
	static String SegundoTimeMoto;
	public static void Tiempo2Moto(int minutes2, int seconds2, int miliseconds2)
	{
		SegundoTimeMoto = minutes2 + ":" + seconds2 + ":" + miliseconds2;
	}
	
	public static String getSegundoTimeMoto()
	{
		return SegundoTimeMoto;
	}
	
	static String TercerTimeMoto;
	public static void Tiempo3Moto(int minutes3, int seconds3, int miliseconds3)
	{
		TercerTimeMoto = minutes3 + ":" + seconds3 + ":" + miliseconds3;
	}
	
	public static String getTercerTimeMoto()
	{
		return TercerTimeMoto;
	}
	
	
	
	
	//CAMIIOOOOOOOOOONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
	
	
	public static void devolverTiemposCamion() throws SQLException
	{
		
		boolean a = false;
		
		int minutes1 = 5;
		int seconds1 = 0;
		int miliseconds1 = 0;
		
		int minutes2 = 0;
		int seconds2 = 0;
		int miliseconds2 = 0;
		
		int minutes3 = 0;
		int seconds3 = 0;
		int miliseconds3 = 0;
		
		Statement stmt = connection.createStatement();
		String query = "select izena, vehiculo, minuto, segundo, milisegundo from TIEMPOS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
			if(rs.getString(1).equals(izena) )
			{
				if(rs.getString(2).equals("Camion"))
				{
					if(rs.getInt(3) < minutes1 )
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = minutes1;
						seconds2 = seconds1;
						miliseconds2 = seconds1;
						
						minutes1 = rs.getInt(3);
						seconds1 = rs.getInt(4);
						miliseconds1 = rs.getInt(5);
						a = true;
						
					}
					
					if (rs.getInt(3) == minutes1)
					{
						if (rs.getInt(4) < seconds1)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = minutes1;
							seconds2 = seconds1;
							miliseconds2 = seconds1;
							
							minutes1 = rs.getInt(3);
							seconds1 = rs.getInt(4);
							miliseconds1 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds1)
						{
							if(rs.getInt(5) < miliseconds1)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = minutes1;
								seconds2 = seconds1;
								miliseconds2 = seconds1;
								
								minutes1 = rs.getInt(3);
								seconds1 = rs.getInt(4);
								miliseconds1 = rs.getInt(5);
								a = true;
								
							}
						}
						
					}	
					
					
					
					if (a == false && rs.getInt(3) < minutes2)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = rs.getInt(3);
						seconds2 = rs.getInt(4);
						miliseconds2 = rs.getInt(5);	
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes2)
					{
						if (rs.getInt(4) < seconds2)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = rs.getInt(3);
							seconds2 = rs.getInt(4);
							miliseconds2 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds2)
						{
							if (rs.getInt(5) < miliseconds2)
							{
								minutes3 = minutes2;
								seconds3 = seconds2;
								miliseconds3 = seconds2;
								
								minutes2 = rs.getInt(3);
								seconds2 = rs.getInt(4);
								miliseconds2 = rs.getInt(5);
								a = true;
							}
						}
					}
					
					
					if (a == false && rs.getInt(3) < minutes3)
					{
						minutes3 = rs.getInt(3);
						seconds3 = rs.getInt(4);
						miliseconds3 = rs.getInt(5);
						a = true;
					}
					
					if (a == false && rs.getInt(3) == minutes3)
					{
						if (rs.getInt(4) < seconds3)
						{
							minutes3 = rs.getInt(3);
							seconds3 = rs.getInt(4);
							miliseconds3 = rs.getInt(5);
							a = true;
						}
						
						if (rs.getInt(4) == seconds3)
						{
							if (rs.getInt(5) < miliseconds3)
							{
								minutes3 = rs.getInt(3);
								seconds3 = rs.getInt(4);
								miliseconds3 = rs.getInt(5);
								a = true;
							}
						}
					}
				}
				
				
			}
			
			
			Tiempo1Camion(minutes1, seconds1, miliseconds1);
			Tiempo2Camion(minutes2, seconds2, miliseconds2);
			Tiempo3Camion(minutes3, seconds3, miliseconds3);
			a = false;
			
		}
	}
	
	
	static String PrimerTimeCamion;
	public static void Tiempo1Camion(int minutes1, int seconds1, int miliseconds1)
	{
		PrimerTimeCamion = minutes1 + ":" + seconds1 + ":" + miliseconds1;	
	}
	
	public static String getPrimerTimeCamion()
	{
		return PrimerTimeCamion;
	}
	
	static String SegundoTimeCamion;
	public static void Tiempo2Camion(int minutes2, int seconds2, int miliseconds2)
	{
		SegundoTimeCamion = minutes2 + ":" + seconds2 + ":" + miliseconds2;
	}
	
	public static String getSegundoTimeCamion()
	{
		return SegundoTimeCamion;
	}
	
	static String TercerTimeCamion;
	public static void Tiempo3Camion(int minutes3, int seconds3, int miliseconds3)
	{
		TercerTimeCamion = minutes3 + ":" + seconds3 + ":" + miliseconds3;
	}
	
	public static String getTercerTimeCamion()
	{
		return TercerTimeCamion;
	}
	
	
	
	
	//MIIIIS MEJOOOOOOOOOOREEEESSSS TIEMPOOOOOOOOOOSSSSSSSSS
	
	
	
	public static void MisMejoresTiempos() throws SQLException
	{
	boolean a = false;
		
		int minutes1 = 5;
		int seconds1 = 0;
		int miliseconds1 = 0;
		
		int minutes2 = 0;
		int seconds2 = 0;
		int miliseconds2 = 0;
		
		int minutes3 = 0;
		int seconds3 = 0;
		int miliseconds3 = 0;
		
		Statement stmt = connection.createStatement();
		String query = "select izena, vehiculo, minuto, segundo, milisegundo from TIEMPOS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
			if(rs.getString(1).equals(izena)) 
			{
				if(rs.getInt(3) < minutes1 )
				{
					minutes3 = minutes2;
					seconds3 = seconds2;
					miliseconds3 = seconds2;
					
					minutes2 = minutes1;
					seconds2 = seconds1;
					miliseconds2 = seconds1;
					
					minutes1 = rs.getInt(3);
					seconds1 = rs.getInt(4);
					miliseconds1 = rs.getInt(5);
					a = true;
					
				}
				
				if (rs.getInt(3) == minutes1)
				{
					if (rs.getInt(4) < seconds1)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = minutes1;
						seconds2 = seconds1;
						miliseconds2 = seconds1;
						
						minutes1 = rs.getInt(3);
						seconds1 = rs.getInt(4);
						miliseconds1 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds1)
					{
						if(rs.getInt(5) < miliseconds1)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = minutes1;
							seconds2 = seconds1;
							miliseconds2 = seconds1;
							
							minutes1 = rs.getInt(3);
							seconds1 = rs.getInt(4);
							miliseconds1 = rs.getInt(5);
							a = true;
							
						}
					}
					
				}	
				
				
				
				if (a == false && rs.getInt(3) < minutes2)
				{
					minutes3 = minutes2;
					seconds3 = seconds2;
					miliseconds3 = seconds2;
					
					minutes2 = rs.getInt(3);
					seconds2 = rs.getInt(4);
					miliseconds2 = rs.getInt(5);	
					a = true;
				}
				
				if (a == false && rs.getInt(3) == minutes2)
				{
					if (rs.getInt(4) < seconds2)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = rs.getInt(3);
						seconds2 = rs.getInt(4);
						miliseconds2 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds2)
					{
						if (rs.getInt(5) < miliseconds2)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = rs.getInt(3);
							seconds2 = rs.getInt(4);
							miliseconds2 = rs.getInt(5);
							a = true;
						}
					}
				}
				
				
				if (a == false && rs.getInt(3) < minutes3)
				{
					minutes3 = rs.getInt(3);
					seconds3 = rs.getInt(4);
					miliseconds3 = rs.getInt(5);
					a = true;
				}
				
				if (a == false && rs.getInt(3) == minutes3)
				{
					if (rs.getInt(4) < seconds3)
					{
						minutes3 = rs.getInt(3);
						seconds3 = rs.getInt(4);
						miliseconds3 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds3)
					{
						if (rs.getInt(5) < miliseconds3)
						{
							minutes3 = rs.getInt(3);
							seconds3 = rs.getInt(4);
							miliseconds3 = rs.getInt(5);
							a = true;
						}
					}
				}
			}
			
			Tiempo1Record(minutes1, seconds1, miliseconds1);
			Tiempo2Record(minutes2, seconds2, miliseconds2);
			Tiempo3Record(minutes3, seconds3, miliseconds3);
			a = false;
			
		}
		
	}
	
	
	
	static String PrimerTimeRecord;
	public static void Tiempo1Record(int minutes1, int seconds1, int miliseconds1)
	{
		PrimerTimeRecord = minutes1 + ":" + seconds1 + ":" + miliseconds1;	
	}
	
	public static String getPrimerTimeRecord()
	{
		return PrimerTimeRecord;
	}
	
	static String SegundoTimeRecord;
	public static void Tiempo2Record(int minutes2, int seconds2, int miliseconds2)
	{
		SegundoTimeRecord = minutes2 + ":" + seconds2 + ":" + miliseconds2;
	}
	
	public static String getSegundoTimeRecord()
	{
		return SegundoTimeRecord;
	}
	
	static String TercerTimeRecord;
	public static void Tiempo3Record(int minutes3, int seconds3, int miliseconds3)
	{
		TercerTimeRecord = minutes3 + ":" + seconds3 + ":" + miliseconds3;
	}
	
	public static String getTercerTimeRecord()
	{
		return TercerTimeRecord;
	}
	
	
	
	
	
	//REEEEEEEEECOOOOOOOORDDDDDDDDDDDDDDDSSSSSSSSS
	
	public static void MejoresTiempos() throws SQLException
	{
		
	boolean a = false;
		
		int minutes1 = 5;
		int seconds1 = 0;
		int miliseconds1 = 0;
		
		int minutes2 = 0;
		int seconds2 = 0;
		int miliseconds2 = 0;
		
		int minutes3 = 0;
		int seconds3 = 0;
		int miliseconds3 = 0;
		
		Statement stmt = connection.createStatement();
		String query = "select izena, vehiculo, minuto, segundo, milisegundo from TIEMPOS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
		{
				if(rs.getInt(3) < minutes1 )
				{
					minutes3 = minutes2;
					seconds3 = seconds2;
					miliseconds3 = seconds2;
					
					minutes2 = minutes1;
					seconds2 = seconds1;
					miliseconds2 = seconds1;
					
					minutes1 = rs.getInt(3);
					seconds1 = rs.getInt(4);
					miliseconds1 = rs.getInt(5);
					a = true;
					
				}
				
				if (rs.getInt(3) == minutes1)
				{
					if (rs.getInt(4) < seconds1)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = minutes1;
						seconds2 = seconds1;
						miliseconds2 = seconds1;
						
						minutes1 = rs.getInt(3);
						seconds1 = rs.getInt(4);
						miliseconds1 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds1)
					{
						if(rs.getInt(5) < miliseconds1)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = minutes1;
							seconds2 = seconds1;
							miliseconds2 = seconds1;
							
							minutes1 = rs.getInt(3);
							seconds1 = rs.getInt(4);
							miliseconds1 = rs.getInt(5);
							a = true;
							
						}
					}
					
				}	
				
				
				
				if (a == false && rs.getInt(3) < minutes2)
				{
					minutes3 = minutes2;
					seconds3 = seconds2;
					miliseconds3 = seconds2;
					
					minutes2 = rs.getInt(3);
					seconds2 = rs.getInt(4);
					miliseconds2 = rs.getInt(5);	
					a = true;
				}
				
				if (a == false && rs.getInt(3) == minutes2)
				{
					if (rs.getInt(4) < seconds2)
					{
						minutes3 = minutes2;
						seconds3 = seconds2;
						miliseconds3 = seconds2;
						
						minutes2 = rs.getInt(3);
						seconds2 = rs.getInt(4);
						miliseconds2 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds2)
					{
						if (rs.getInt(5) < miliseconds2)
						{
							minutes3 = minutes2;
							seconds3 = seconds2;
							miliseconds3 = seconds2;
							
							minutes2 = rs.getInt(3);
							seconds2 = rs.getInt(4);
							miliseconds2 = rs.getInt(5);
							a = true;
						}
					}
				}
				
				
				if (a == false && rs.getInt(3) < minutes3)
				{
					minutes3 = rs.getInt(3);
					seconds3 = rs.getInt(4);
					miliseconds3 = rs.getInt(5);
					a = true;
				}
				
				if (a == false && rs.getInt(3) == minutes3)
				{
					if (rs.getInt(4) < seconds3)
					{
						minutes3 = rs.getInt(3);
						seconds3 = rs.getInt(4);
						miliseconds3 = rs.getInt(5);
						a = true;
					}
					
					if (rs.getInt(4) == seconds3)
					{
						if (rs.getInt(5) < miliseconds3)
						{
							minutes3 = rs.getInt(3);
							seconds3 = rs.getInt(4);
							miliseconds3 = rs.getInt(5);
							a = true;
						}
					}
				}
			
			Tiempo1RecordTotal(minutes1, seconds1, miliseconds1);
			Tiempo2RecordTotal(minutes2, seconds2, miliseconds2);
			Tiempo3RecordTotal(minutes3, seconds3, miliseconds3);
			a = false;
		}
	}
	
	
	
	static String PrimerTimeRecordTotal;
	public static void Tiempo1RecordTotal(int minutes1, int seconds1, int miliseconds1)
	{
		PrimerTimeRecordTotal = minutes1 + ":" + seconds1 + ":" + miliseconds1;	
	}
	
	public static String getPrimerTimeRecordTotal()
	{
		return PrimerTimeRecordTotal;
	}
	
	static String SegundoTimeRecordTotal;
	public static void Tiempo2RecordTotal(int minutes2, int seconds2, int miliseconds2)
	{
		SegundoTimeRecordTotal = minutes2 + ":" + seconds2 + ":" + miliseconds2;
	}
	
	public static String getSegundoTimeRecordTotal()
	{
		return SegundoTimeRecordTotal;
	}
	
	static String TercerTimeRecordTotal;
	public static void Tiempo3RecordTotal(int minutes3, int seconds3, int miliseconds3)
	{
		TercerTimeRecord = minutes3 + ":" + seconds3 + ":" + miliseconds3;
	}
	
	public static String getTercerTimeRecordTotal()
	{
		return TercerTimeRecordTotal;
	}
	
	
	

	public static boolean comprobarUsuario (String nombre) throws SQLException
	{
		Statement stmt = connection.createStatement();
		String query = "select nombre from USUARIOS";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
			if (rs.getString(1).equals(nombre))
			{
				return true;
			}
		}
		rs.close();
		return false;
	}
	
	public static boolean log(Statement st , String nombre, String contra) throws SQLException
	{
		Statement stmt = connection.createStatement();
		String query = "select nombre,contra from USUARIOS";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
			if (rs.getString(1).equals(nombre) && rs.getString(2).equals(contra))
			{
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecta");
		rs.close();
		return false;
	}
	
	
	public static void setNombre(String nombre)
	{
		izena = nombre;
	}
	
	
	
}