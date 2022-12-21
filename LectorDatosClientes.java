import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author jirol
 *
 */
public class LectorDatosClientes {
	File fichero1;
	ArrayList <String[]> matriz1 = new ArrayList<String[]>();
	private static Conexion2 conexion = new Conexion2();
	private static String [] split;
	private static ArrayList <String[][]> matriz = new ArrayList<String[][]>();
	private static ArrayList <String> splitear = new ArrayList <String>();
	public void insertaDatosClientes(String nombreFichero, String nombreTabla, String separadorCampos){
		nombreFichero = "C:\\Users\\jirol\\eclipse-workspace\\PracticaFicheros2\\src\\Fichero.txt";
		separadorCampos = ";";
		nombreTabla = "empleados";
	try {
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			String cadena;
			while ((cadena = br.readLine()) != null) {
				split = cadena.split(separadorCampos);
				for(int i = 0; i < split.length; i++) {
					if(split.length == 1) {
						splitear.add(null);
					}
					System.out.println("Split method"+split[i]);
					splitear.add(split[i]);
				}
			}
		try{
			
			//Desde aqui
			conexion.Conectar();
			PreparedStatement statement;
			String consulta = "INSERT INTO EMPLEADOS(DNI,NOMBRE) VALUES(?,?)";
			statement = conexion.getConexion().prepareStatement(consulta);
			conexion.getConexion().setAutoCommit(false);
			String [][] matriz1 = new String[splitear.size()][2];
			int contador1 = -1;
			int indice = 0;
			for(int x = 0; x < matriz1.length; x++) {
				for(int y = 0; y < matriz1[x].length; y++) {
					//System.out.println(matriz1[x].length);
					contador1++;
					matriz1[x][y] = splitear.get(indice);
					System.out.println("Lo que introduces"+matriz1[x][y]+"Indice"+indice);
					indice = (indice + 1) % splitear.size();
					statement.setString(y+1, matriz1[x][y]);
					//System.out.println("Matriz de la posicion "+matriz1[x][y]+"Indice: "+(y+1));
				}
				statement.addBatch();
			}
			statement.executeBatch();
			conexion.getConexion().commit();
			conexion.desconectar();
			br.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			try {
				conexion.getConexion().rollback();
				
			} catch (Exception er) {
				System.out.println(er);
				// TODO: handle exception
			}
		}
			// Hasta aqui
	}
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	//Ejercicio realizado
	/**
	 * 		int a = 0; 
			int b =1;
			PreparedStatement statement;
			for(int i = 0; i < contador; i++) {
				String consulta = "INSERT INTO EMPLEADOS(DNI,NOMBRE) VALUES('"+splitear.get(a)+"','"+splitear.get(b)+"')";
				statement = conexion.getConexion().prepareStatement(consulta);
				statement.execute();
				System.out.println("valores a introducir : "+splitear.get(a)+ splitear.get(b) +"  A: "+a+"  B: "+b);
				a = a+2;
				b = b+2;
			}
			conexion.desconectar();
			br.close();
			System.out.println("cierre de fichero");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 * @param nombreFichero
	 */
	private void crearFichero(String nombreFichero) {
		fichero1 = new File("C:\\Users\\jirol\\eclipse-workspace", nombreFichero + ".txt");
		try {
			if (fichero1.createNewFile()) {
				System.out.println("Fichero deseado creado, introduzca datos");
			} else
				JOptionPane.showMessageDialog(null, "Accediendo a fichero que desea");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * int contador1 = -1;
			String [][] matriz1 = new String[splitear.size()][2];
			for(int x = 0; x < matriz1.length; x++) {
				for(int y = 0; y < matriz1[x].length; y++) {
					contador1++;
					matriz1[x][y] = splitear.get(contador1);
					System.out.println("Matriz de la posicion "+matriz1[x][y]);
				}
			}
	 */
	/**
	 * System.out.println("Cadena :"+cadena);
				for(int i = 0; i < split.length; i++) {	
					String consulta = "Insert into clientes(DNI,NOMBRE) values('"+split[0]+"','"+split[1]+"')";
					try {
						PreparedStatement statement;
						statement = conexion.getConexion().prepareStatement(consulta);
						statement.execute();
						System.out.println("Split "+split[i]);
					}catch(SQLException e) {
						System.out.println(e);
					}
				}
	 */
}
