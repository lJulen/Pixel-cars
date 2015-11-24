package Interfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class clsDatos
{

	private final String fic_usuario = "src\\Data\\usuario.dat";
	


	ObjectOutputStream oos;
	ObjectInputStream ois; 
	AppendableObjectOutputStream aos;
	
	public clsDatos(){}
	
	private String setFichero (int clase)
	{
		//Según el fichero sobre el que se quiera trabajar, se selecciona la ruta.
		switch(clase)
		{
			
			case 1: 
			{
				return fic_usuario;
			}
		
			
		}
		return "";
	}
	
	public void ComenzarSave(int c)
	{
		
		
		String ruta = setFichero(c);
		File fic;
		
		//se comprueba si el fichero existe o es la primera vez.
		fic=new File(ruta);
		//Si el fichero existe se deberán añadir registros. En caso contrario se deberá de crear el fichero
		if(fic.exists())
		{
			try {
				aos = new AppendableObjectOutputStream(new FileOutputStream(fic,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			
			try
			{
				fic.createNewFile();
				oos = new ObjectOutputStream(new FileOutputStream(fic));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	public void TerminarSave()
	{
		try
		{
			if (oos!=null) oos.close();
			else if(aos!=null)aos.close();
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Se cierra al terminar.
	}
	public void Save(Serializable o)
	{
		
	
		try
		{
			if(oos!=null) 
				oos.writeObject(o);
			else
			{
				if(aos!=null)	
				{
					
					aos.writeObject(o);
				}
			}
			
		
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ComenzarRead(int clase) throws IOException
	{
		String ruta=setFichero(clase);
		File fic;
		
		fic=new File(ruta);
		if (fic.exists())
		{
			try
			{
				ois = new ObjectInputStream(new FileInputStream(fic));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new IOException();
		}
	}
	
	public void TerminarRead()
	{
		try
		{
			if(ois!=null)ois.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Serializable> Read()
	{
		
		ArrayList<Serializable>lista;
		Serializable o=null;
		
		
		lista=new ArrayList<Serializable>();
		try
		{
			
			while ((o = (Serializable)ois.readObject()) != null) 
			{
			       lista.add(o);
			        
			 }
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			if(o==null) 
			{
				//System.out.println(e.getMessage());
			}
		}catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return lista;
	}
	
	public void ResetFile (int clase)
	{
		String ruta =setFichero(clase);
		File f=new File(ruta);
		f.delete();
			
	}
	public int ContarGuardados(int clase)
	{
		ArrayList<Serializable> l=null;
		try {
			ComenzarRead(clase);
			l=Read();
			TerminarRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(l!=null)
			return l.size();
		else
			return 0;
	}
	public boolean comprobarExistencia(int c){
		String ruta = setFichero(c);
		File fic;
		fic=new File(ruta);
		if(fic.exists()){
			return true;
		}else return false;
	}
		
			
		
	
}
