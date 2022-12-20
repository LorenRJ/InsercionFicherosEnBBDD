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
public class Proyectos {
	private int numPro;
	private String nombre;
	private String dniJefe;
	private String fini;
	private String ffin;	
	private String dni;
	private String name;
	private static String fecha1;
	private static String fecha2;
	private static ArrayList <Proyectos> proyectos = new ArrayList<Proyectos>();
	private static Conexion2 conexion = new Conexion2();
	
	public Proyectos(int numPro, String nombre, String dniJefe, String fini, String ffin) {
		super();
		this.numPro = numPro;
		this.nombre = nombre;
		this.dniJefe = dniJefe;
		this.fini = fini;
		this.ffin = ffin;
	}
	private void query(String consulta) {
		conexion.Conectar();
		PreparedStatement statement1;
		try {
			statement1 = conexion.getConexion().prepareStatement(consulta);
			statement1.execute();
			System.out.println("REALIZADA LA CONSULTA");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		//resultado();
		conexion.desconectar();
	}
	private void insercion() {
		if(fecha2.equals("")|| fecha2.equals(null)) {
			System.out.println("Entrando a fecha final vacio");
			String consulta = "INSERT into proyectos(NOMBRE,DNI_NIF_JEFE,F_INICIO)VALUES('"+name+"','"+dni+"','"+fecha1+"')";
			query(consulta);
		}
		else if(fecha2 != null || fecha2!= "") {
			System.out.println("Fecha final llena");
			String consulta = "INSERT INTO PROYECTOS(NOMBRE,DNI_NIF_JEFE,F_INICIO,F_FIN) VALUES('"+name+"','"+dni+"','"+fecha1+"','"+fecha2+"')";
			query(consulta);
		}
	}
	public void crearProyecto() {
		rellenarProyectos();
		name = JOptionPane.showInputDialog("Introduce el nombre");
		dni = JOptionPane.showInputDialog("Introduce dni de jefe de proyecto");
		fecha1 = JOptionPane.showInputDialog("Introduce la fecha de inicio del proyecto");
		fvali(fecha1);
		fecha2 = JOptionPane.showInputDialog("Introduce fehca fin del proyecto");
		proyectos.add(new Proyectos());
		int codigo = proyectos.size();
		int indice = proyectos.size()-1;
		proyectos.get(indice).setNumPro(codigo);
		proyectos.get(indice).setDniJefe(dni);
		proyectos.get(indice).setNombre(name);
		proyectos.get(indice).setFini(fecha1);
		proyectos.get(indice).setFfin(fecha2);
		System.out.println("Fecha de inicio"+proyectos.get(indice).getFfin());
		String resultado = "Proyecto Creado "
		+"\nNumero de proyecto: "+proyectos.get(indice).getNumPro()
		+"\nNombre: "+proyectos.get(indice).getNombre()
		+"\nDNI jefe de proyecto"+proyectos.get(indice).getDniJefe()
		+"\nFecha de inicio del proyecto: "+proyectos.get(indice).getFini()
		+"\nFnecha de fin de proyecto: "+proyectos.get(indice).getFfin();
		insercion();
		JOptionPane.showMessageDialog(null, resultado);
	}
	private static void fvali(Object fecha) {
		if(fecha.equals(null)||fecha.equals("")) {
			fecha1 = (java.time.LocalDate.now()).toString();
			System.out.println("Fecha de inicio"+fecha1);
		}
	}
	private static void rellenarProyectos() {
		proyectos.clear();
		conexion.Conectar();
		String consulta = "Select * from Proyectos";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			while (result.next()) {
				proyectos.add(
						new Proyectos(
								result.getInt("NUM_PRO"),
								result.getString("NOMBRE"),
								result.getString("DNI_NIF_JEFE"),
								result.getString("F_INICIO"),
								result.getString("F_FIN")));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			conexion.desconectar();
		}
		System.out.println("Resultados del array");
		for (int i = 0; i < proyectos.size(); i++) {
			System.out.println(
					proyectos.get(i).getNumPro() + " " + proyectos.get(i).getDniJefe() + " " + proyectos.get(i).getNombre()+proyectos.get(i).getFfin());
		}
	}
	public Proyectos() {
		super();
	}
	public int getNumPro() {
		return numPro;
	}
	public void setNumPro(int numPro) {
		this.numPro = numPro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDniJefe() {
		return dniJefe;
	}
	public void setDniJefe(String dniJefe) {
		this.dniJefe = dniJefe;
	}
	public String getFini() {
		return fini;
	}
	public void setFini(String fini) {
		this.fini = fini;
	}
	public String getFfin() {
		return ffin;
	}
	public void setFfin(String ffin) {
		this.ffin = ffin;
	}
	
}
