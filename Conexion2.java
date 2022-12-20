import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */

/**
 * @author 
 *
 */
public class Conexion2 {
	private String nombreDB = "ejercicio3"; // Nombre de la base de datos
	private String usuario = "root"; // Usuario de la base de datos
	private String password = ""; // Contrase�a de la base de datos
	private String url = "jdbc:mysql://localhost:3306/" + nombreDB; // URL de la base de datos

	Connection conexion = null; // Variable de conexion a la base de datos

	public Conexion2() {
	}

	public void Conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// Cargar clase Driver
			try {
				//Damos valor a la variable conexion realizando la conexion a la base de datos
				conexion = DriverManager.getConnection(url, usuario, password);

				/**
				 * Si la conexion es correcta es distinto de null, por tanto hacemos print para
				 * comprobarlo
				 */
				if (conexion != null) {
					System.out.println("CONEXION ESTABLECIDA");
				}

			} catch (SQLException e) {
				System.out.println("Tabla o base de datos no encontrada");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver mal cargado");

		}
	}

	/**
	 * Metodo para retornar la conexion de la base de datos
	 *
	 * @return
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * Metodo para desconectar de la base de datos
	 */
	public void desconectar() {
		conexion = null;
	}
}

