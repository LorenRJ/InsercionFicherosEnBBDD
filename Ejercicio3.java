import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author jirol
 */
public class Ejercicio3 {

	/**
	 * @param args
	 */
	private static String linea;
	static Conexion2 conexion2 = new Conexion2();
	private static Scanner scan1 = new Scanner(System.in);
	private static Object fecha1;
	private static String nombre;
	private static String dni;
	private static String fecha2;
	private static int numpro;
	private static ArrayList <String> empleados = new ArrayList();
	private static ArrayList <String> proyectos = new ArrayList();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}
	private static void menu() {
		System.out.println("MENU DE EJERCICIO 3");
		System.out.println("1.- Crear empleados");
		System.out.println("2.- Crear proyectos");
		System.out.println("3.- Asignar Proyectos");
		Scanner scan = new Scanner(System.in);
		linea = scan.nextLine();
		switch(linea) {
		case"1":
			aniadirEmpleados();
			menu();
			break;
		case"2":
			crearProyectos();
			menu();
			break;
		case"3":
			asignarProyectos();
			menu();
			break;
		default:
			System.out.println("Introduce el numero del menu correcto");
			menu();
			break;
		}
	}
	public static void crearProyectos() {
		conexion2.Conectar();
		LocalDate fecha = java.time.LocalDate.now();
		System.out.println("\nIntroduce Datos de proyectos");
		System.out.println("Introduce Nombre");
		nombre = scan1.nextLine();
		System.out.println("Introduce DNI jefe de proyecto");
		dni = scan1.nextLine();
		System.out.println("Introduce fecha de inicio de proyecto");
		fecha1 = scan1.nextLine();
		fvali(fecha1);
		System.out.println(fecha1);
		System.out.println("Introduce fecha de fin de proyecto");
		fecha2 = scan1.nextLine();
		fechafinvali();
		
	}
	private static void fechafinvali() {
		if(fecha2.equals("")|| fecha2.equals(null)) {
			System.out.println("Entrando a fecha final vacio");
			String consulta1 = "INSERT INTO PROYECTOS(NOMBRE,DNI_NIF_JEFE,F_INICIO) VALUES('"+nombre+"','"+dni+"','"+fecha1+"')";
			PreparedStatement statement1;
			try {
				statement1 = conexion2.getConexion().prepareStatement(consulta1);
				statement1.execute();
				System.out.println("REALIZADA LA CONSULTA");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			resultado();
			conexion2.desconectar();
		}
		else if(fecha2 != null || fecha2!= "") {
			System.out.println("Relizar consulta llena");
			String consulta = "INSERT INTO PROYECTOS(NOMBRE,DNI_NIF_JEFE,F_INICIO,F_FIN) VALUES('"+nombre+"','"+dni+"','"+fecha1+"','"+fecha2+"')";
			PreparedStatement statement;
			try {
				statement = conexion2.getConexion().prepareStatement(consulta);
				statement.execute();
				System.out.println("REALIZADA LA CONSULTA");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			resultado();
			conexion2.desconectar();
		}
	}
	
	private static void resultado() {
		String consulta2 = "SELECT * from proyectos";
		PreparedStatement statement1 = null;
		ResultSet result = null;
		try {
			statement1 = conexion2.getConexion().prepareStatement(consulta2);
			result = statement1.executeQuery();
			while(result.next()) {
				numpro++;
			}
			System.out.println("\n\nDatos de los proyectos\nNumero de proyecto: "+numpro+"\nNombre: "+nombre+
					"\nDNI Jefe de proyecto: "+dni+"\nFecha de inicio: "+fecha1+"\nFecha de fin de proyecto: "+fecha2+"\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static void fvali(Object fecha) {
		if(fecha.equals(null)||fecha.equals("")) {
			fecha1 = java.time.LocalDate.now();
		}
	}
	private static void comprobarDni(String dni) {
		if(dni.length() != 9) {
			System.out.println("El dni debe tener longitud 9");
			aniadirEmpleados();
		}
	}
	public static boolean aniadirEmpleados() {
		System.out.println("Introduce nombre del empleado");
		String linea1;
		linea1 = scan1.nextLine();
		System.out.println("Introduce DNI/NIF");
		String linea2;
		linea2 = scan1.nextLine();
		comprobarDni(linea2);
		conexion2.Conectar();
		String consulta = "INSERT INTO EMPLEADOS(DNI,NOMBRE) VALUES('"+linea2+"','"+linea1+"')";
		PreparedStatement statement;
		try {
			statement = conexion2.getConexion().prepareStatement(consulta);
			statement.execute();
			System.out.println("\n\nEmpleado creado \n- DNI: "+linea1+"\n- NOMBRE: "+linea2+"\n");
			conexion2.desconectar();
		} catch (Exception e) {
			System.out.println("Hola");
		}
		return true;
	}
	private static void asignarProyectos() {
		rellenarArrayProyecto();
	}
	private static void rellenarEmpleados() {
		conexion2.Conectar();
		String consulta = "Select * FROM EMPLEADOS";
		empleados.clear();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conexion2.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			while(result.next()) {
				String resultado = "Codigo de empleado: "+result.getInt("CODEM")+"  dni: "+result.getString("DNI")+"  Nombre : "+result.getString("NOMBRE")+"\n";
				System.out.println(resultado);
				empleados.add(resultado);
			}
		}catch(Exception e) {
			
		}
		
	}
	private static void rellenarArrayProyecto() {
		conexion2.Conectar();
		// TODO Auto-generated method stub
		proyectos.clear();
		String consulta = "select * from proyectos";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conexion2.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			while(result.next()) {
				String resultado = "Numero de proyecto: "+result.getInt("NUM_PRO")+" Nombre: "+result.getString("NOMBRE")+
						" DNI: "+result.getString("DNI_NIF_JEFE")+"\n";
				System.out.println(resultado);
				proyectos.add(resultado);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		System.out.println("Que numero de proyecto quieres asignar");
		String num = scan1.nextLine();
		rellenarEmpleados();
		System.out.println("Que numero de empleado deseas asociar");
		String nume = scan1.nextLine();
		System.out.println("Que fecha de inicio de proyecto asocias");
		fecha1 = scan1.nextLine();
		fvali(fecha1);
		System.out.println("Que fecha de final de proyecto asocias");
		fecha2 = scan1.nextLine();
		f2vali();
		String consulta1 = "INSERT INTO asignacion(CODEM,NUM_PRO,F_INICIO,F_FIN) VALUES('"+nume+"','"+num+"','"+fecha1+"','"+fecha2+"')";
		PreparedStatement statement1 ;
		try {
			statement1 = conexion2.getConexion().prepareStatement(consulta1);
			statement1.execute();
			System.out.println("Asignacion de proyecto realizada con exito");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	private static void f2vali() {
		if(fecha2.equals(null) || fecha2.equals("")) {
			System.out.println("Debes Introducir fecha fin de forma obligatoria");
			String linea = scan1.nextLine();
			fecha2 = linea;
			if(fecha2.equals("") || fecha2.equals(null)) {
				f2vali();
			}
		}
	}
	
}
