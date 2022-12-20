import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author jirol
 *
 */
public class Empleados {
	private int codEm;
	private String dni;
	private String nombre;
	private static Conexion2 conexion = new Conexion2();
	public ArrayList <Empleados> empleados = new ArrayList <Empleados>();
	@Override
	public String toString() {
		return "Empleados [codEm=" + codEm + ", dni=" + dni + ", nombre=" + nombre + ", empleados=" + empleados + "]";
	}
	public Empleados() {
		
	}
	public Empleados(int codEm, String dni, String nombre) {
		this.codEm = codEm;
		this.dni = dni;
		this.nombre = nombre;
	}
	public boolean crearEmpleados() {
		rellenarEmpleados();
		JOptionPane.showMessageDialog(null, "Introduce la informacion del empleado");
		String nombre1 = JOptionPane.showInputDialog("Introduce el nombre del empleado");
		String dni1 = JOptionPane.showInputDialog("Introduce el dni del empleado");
		empleados.add(new Empleados());
		int indice = empleados.size()-1;
		int codigo = empleados.size();
		empleados.get(indice).setCodEm(codigo);
		empleados.get(indice).setDni(dni1);
		empleados.get(indice).setNombre(nombre1);
		for(int i = 0; i < empleados.size();i++) {
			System.out.println(empleados.get(i).getCodEm()+" "+empleados.get(i).getDni()+" "+empleados.get(i).getNombre());
		}
		conexion.Conectar();
		String consulta = "INSERT INTO EMPLEADOS(DNI,NOMBRE) VALUES('"+dni1+"','"+nombre1+"')";
		PreparedStatement statement;
		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			statement.execute();
			System.out.println("Esta realizada la query");
			conexion.desconectar();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	public void rellenarEmpleados() {
		empleados.clear();
		conexion.Conectar();
		String consulta = "Select * from empleados";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
		statement = conexion.getConexion().prepareStatement(consulta);
		result = statement.executeQuery();
		while(result.next()) {
		empleados.add(new Empleados
		(result.getInt("CODEM"),
		result.getString("DNI"),
		result.getString("NOMBRE")));
		}
		}catch(Exception e) {
		System.out.println("Error query");
		}finally {
		conexion.desconectar();
		}
		System.out.println("Resultados del array");
		for(int i = 0; i < empleados.size(); i++) {
			System.out.println(empleados.get(i).getCodEm()+" "+empleados.get(i).getDni()+" "+empleados.get(i).getNombre());
		}
	}
	/**
	 * Getters y setters
	 * 
	 * @return
	 */
	public int getCodEm() {
		return codEm;
	}
	public void setCodEm(int codEm) {
		this.codEm = codEm;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
