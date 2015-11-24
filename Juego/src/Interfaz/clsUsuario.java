package Interfaz;

import java.io.Serializable;

public class clsUsuario implements Serializable {
 protected String nombre;
 protected String contra;

 public clsUsuario() {
	super();
}


public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getContra() {
	return contra;
}
public void setContra(String contra) {
	this.contra = contra;
}
public String toString() {
	StringBuffer salida=new StringBuffer();
	salida.append("Usuario= ");
	salida.append(this.nombre);
	salida.append("Contraseña= ");
	salida.append(this.contra);
	return salida.toString();
}
}
