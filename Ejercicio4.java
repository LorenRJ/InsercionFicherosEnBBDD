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
public class Ejercicio4 {
	/**
	 * @param args
	 */
	private static String [] sep = {";"};
	private static String[] obj = { "Crear Empleados", "Crear Proyectos", "Asignar Proyectos","Mostrar Lista De Empleados en Proyectos","Insertar datos mediante fichero csv", "Salir" };
	private static Empleados emplea = new Empleados();
	private static Proyectos proyectos = new Proyectos();
	private static Asignacion_Proyectos aproyectos = new Asignacion_Proyectos();
	private static LectorDatosClientes lector = new LectorDatosClientes();
	
	public static void main(String[] args) {
		menu();
	}
	private static void menu() {
		Object seleccion = JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Elija consulta a realizar" // Mensaje de la ventanas
				, "Practica Ficheros" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, obj // el objeto
				, 0 // posicion del que va aparecer seleccionado
		);
		try {
			switch (seleccion.toString()) {
			case "Crear Empleados":
				System.out.println("Crear usuarios");
				emplea.crearEmpleados();
				menu();
				break;
			case "Crear Proyectos":
				System.out.println("Eliminar usuarios");
				proyectos.crearProyecto();
				menu();
				break;
			case "Asignar Proyectos":
				System.out.println("Buscar usuarios");
				aproyectos.Asignacion_Proyectos();
				menu();
				break;
			case "Mostrar Lista De Empleados en Proyectos":
				aproyectos.getListAsigEmpleados();
				menu();
				break;
			case "Insertar datos mediante fichero csv":
				//Object sec = JOptionPane.showInputDialog(null
						//, "Separador de datos" 
						//, "Inssertar Datos" 
						//, JOptionPane.QUESTION_MESSAGE 
						//, null 
						//, sep
						//, 0 
				//);
				lector.insertaDatosClientes(null,null,null);
				break;
			case "Salir":
				System.out.println("Salir");
				break;
			default:
				System.out.println("adios");
				menu();
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}