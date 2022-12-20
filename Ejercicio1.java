import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

/**
 * 
 */

/**
 * @author Alumno
 *
 */
public class Ejercicio1 {
	/**
	 * @param args
	 */
	static ArrayList<String> registros = new ArrayList<String>();
	static Conexion conexion = new Conexion();
	private static int i = 0;
	private static String letra ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rellenarArray();
		buscarRegistros();
	}
	private static void rellenarArray() {
		conexion.Conectar();
		String consulta = "SELECT * FROM clientes";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			registros.clear();
			while(result.next()) {
				String registro = "Codigo: "+result.getInt("CODCLI")+" DNI: "+result.getString("DNI")+" Nombre: "+result.getString("NOMBRE")+"\n";
				registros.add(registro);
			}
			System.out.println("\n \nRegistro de referencia = "+registros.get(i));
		}catch(SQLException e){
			
		}
		conexion.desconectar();
	}
	private static void buscarRegistros() {
		System.out.println("Introduce letras para moverte por el fichero \n K = 'Siguiente fila'\n D = 'Fila anterior' \n . = 'Fin del programa'");
		Scanner entrada=new Scanner(System.in);
		letra = entrada.nextLine();
		menu();
	}
	private static void menu() {
		try{
			switch(letra.toLowerCase()) {
			case "k":
				i++;
				if(verdad()) {
					System.out.println("\nRegistro de referencia = "+registros.get(i));
					buscarRegistros();
					break;
				}else {
					System.out.println("Ya no hay mas registros por debajo de esta fila");
					buscarRegistros();
					break;
				}
			case "d":
				i--;
				if(verdad()) {
					System.out.println("\n[ Registro de referencia = "+registros.get(i)+"]");
					buscarRegistros();
					break;
				}else {
					System.out.println("Ya no hay mas registros por debajo de esta fila");
					buscarRegistros();
					break;
				}
				
			case ".":
				System.out.println("Fin del programa");
				System.exit(0);
				break;
			default:
				System.out.println("Valroes introducidos son incorrectos, revisa lo que introduce");
				buscarRegistros();
				break;
			}
		}catch(Exception e) {
			System.out.println("Valores introducidos incorrectos");
			buscarRegistros();
		}
		
	}
	private static boolean verdad() {
		if(i >= registros.size()) {
			return false;
		}
		else if(i < 0) {
			return false;
		}
		else {
			return true;
		}
	}
}