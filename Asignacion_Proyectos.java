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
public class Asignacion_Proyectos {
	private int codEm;
	private int numPro;
	private String ffin;
	private String fini;
	private static String fecha1;
	private static String fecha2;
	private static Empleados e = new Empleados();
	private static int [] codigoEmpleados ;
	private static int numeropro;
	private static int codigoe;
	private static Conexion2 conexion = new Conexion2();
	private static ArrayList <Asignacion_Proyectos> aproyectos = new ArrayList<Asignacion_Proyectos>();
	public Asignacion_Proyectos(int codEm, int numPro, String ffin, String fini) {
		super();
		this.codEm = codEm;
		this.numPro = numPro;
		this.ffin = ffin;
		this.fini = fini;
	}
	public Asignacion_Proyectos() {
		
	}
	
	private static void rellenarAsignacion() {
		String consulta = "Select * from asignacion";
		aproyectos.clear();
		conexion.Conectar();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			while (result.next()) {
				aproyectos.add(
						new Asignacion_Proyectos(
								result.getInt("CODEM"),
								result.getInt("NUM_PRO"),
								result.getString("F_INICIO"),
								result.getString("F_FIN")));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			conexion.desconectar();
		}
		System.out.println("Resultados del array");
		for (int i = 0; i < aproyectos.size(); i++) {
			System.out.println(
					aproyectos.get(i).getNumPro() + " " + aproyectos.get(i).getCodEm() + " " + aproyectos.get(i).getFini()+aproyectos.get(i).getFfin());
		}
	}
	private static void fvali(Object fecha) {
		if(fecha.equals(null)||fecha.equals("")) {
			fecha1 = (java.time.LocalDate.now()).toString();
			System.out.println("Fecha de inicio"+fecha1);
		}
	}
	public static void  Asignacion_Proyectos() {
		rellenarAsignacion();
		numeropro = Integer.parseInt(JOptionPane.showInputDialog("Introduce el numero de proyecto"));
		codigoe = Integer.parseInt(JOptionPane.showInputDialog("Introduce el codigo de cliente encargado de la asignacion"));
		fecha1 = JOptionPane.showInputDialog("Introduce fecha de creacion");
		fvali(fecha1);
		fecha2 = JOptionPane.showInputDialog("Introduce fecha de fin de proyecto");
		aproyectos.add(new Asignacion_Proyectos());
		int indice = aproyectos.size()-1;
		aproyectos.get(indice).setCodEm(codigoe);
		aproyectos.get(indice).setNumPro(numeropro);
		aproyectos.get(indice).setFini(fecha1);
		aproyectos.get(indice).setFfin(fecha2);
		insercion();
		
	}
	private static void query(String consulta) {
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
	private static  void insercion() {
		if(fecha2.equals("")|| fecha2.equals(null)) {
			System.out.println("Entrando a fecha final vacio");
			String consulta = "INSERT INTO asignacion(CODEM,NUM_PRO,F_INICIO) VALUES('"+codigoe+"','"+numeropro+"','"+fecha1+"')";
			query(consulta);
		}
		else if(fecha2 != null || fecha2!= "") {
			System.out.println("Fecha final llena");
			String consulta = "INSERT INTO asignacion(CODEM,NUM_PRO,F_INICIO,F_FIN) VALUES('"+codigoe+"','"+numeropro+"','"+fecha1+"','"+fecha2+"')";
			query(consulta);
		}
	}
	
	public void getListAsigEmpleados() {
		String fecha = (java.time.LocalDate.now()).toString(); 
		int cod = Integer.parseInt(JOptionPane.showInputDialog("Introduce el numero de proyecto que deseas consultar"));
		System.out.println("Lista de empleados");
		System.out.println(fecha);
		String consulta = "SELECT * from asignacion where F_INICIO <= '"+fecha+"' and F_FIN > '"+fecha+"' and NUM_PRO = '"+cod+"';";
		conexion.Conectar();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			aproyectos.clear();
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			while (result.next()) {
				aproyectos.add(
						new Asignacion_Proyectos(
								result.getInt("CODEM"),
								result.getInt("NUM_PRO"),
								result.getString("F_INICIO"),
								result.getString("F_FIN")));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			conexion.desconectar();
		}
		int contador = 0;
		e.rellenarEmpleados();
		for(int i = 0; i < aproyectos.size(); i++) {
			//JOptionPane.showMessageDialog(null, "Numero de empleado del array de proyectos"+aproyectos.get(i).getCodEm());
			for(int b = 0; b < e.empleados.size(); b++) {
				//JOptionPane.showMessageDialog(null, "Numero de empleado del array empleados"+e.empleados.get(b).getCodEm());;
				if(aproyectos.get(i).getCodEm() == e.empleados.get(b).getCodEm()) {
					contador ++;
					JOptionPane.showMessageDialog(null, "Empleados "+e.empleados.get(b).getCodEm()+"\nProyectos"+aproyectos.get(i).getCodEm());
					String resultado = "Empleados asignados al numero de proyecto"+aproyectos.get(i).getNumPro()
							+"\n Nombre: "+e.empleados.get(b).getNombre()
							+"\n Dni: "+e.empleados.get(b).getDni();
					JOptionPane.showMessageDialog(null,resultado);
				}
			}
		}
		if(contador == 0) {
			JOptionPane.showMessageDialog(null, "No hay usuarios asigandos al numero de proyecto "+cod);
		}
	}
	public int getCodEm() {
		return codEm;
	}
	public void setCodEm(int codEm) {
		this.codEm = codEm;
	}
	public int getNumPro() {
		return numPro;
	}
	public void setNumPro(int numPro) {
		this.numPro = numPro;
	}
	public String getFfin() {
		return ffin;
	}
	public void setFfin(String ffin) {
		this.ffin = ffin;
	}
	public String getFini() {
		return fini;
	}
	public void setFini(String fini) {
		this.fini = fini;
	}
	//SELECT * from asignacion where F_INICIO < '2022-12-16' and F_FIN > '2022-12-16';
}
