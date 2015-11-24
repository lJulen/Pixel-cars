package Interfaz;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class clsGestorUsuarios {
	ArrayList<clsUsuario> listaUsuario;
	clsDatos objDatos;
	
	public clsGestorUsuarios(){
		listaUsuario=new ArrayList<clsUsuario>();
		objDatos=new clsDatos();
	}
	public void nuevoUsuario(String nombre, String contra) throws exceptionUsuarioExistente{
		comprobarUsuario(nombre);
		clsUsuario o= new clsUsuario();
		o.setNombre(nombre);
		o.setContra(contra);
		objDatos.ComenzarSave(1);
		objDatos.Save(o);
		objDatos.TerminarSave();
	}
	public void leerUsuarios(){
		ArrayList<Serializable>lista;
		lista=new ArrayList<Serializable>();
		if(objDatos.comprobarExistencia(1)==true){
			try {
				objDatos.ComenzarRead(1);
				lista=objDatos.Read();
				listaUsuario.clear();//Vaciar la lista
				for(Serializable s:lista){
					listaUsuario.add((clsUsuario) s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			objDatos.TerminarRead();
		}
	}
	public ArrayList<clsUsuario> getListaUsuario(){
		leerUsuarios();
		return listaUsuario;
	}
public void comprobarUsuario(String nombre)throws exceptionUsuarioExistente {
		
		if(objDatos.comprobarExistencia(1)==true){
			leerUsuarios();
			for(clsUsuario o:listaUsuario){		
				if(o.getNombre().equals(nombre)){
					throw new exceptionUsuarioExistente();
				}
			}
		}
	}


}
