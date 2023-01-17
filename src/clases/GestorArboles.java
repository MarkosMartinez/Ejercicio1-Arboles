package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.Scanner;

public class GestorArboles {
	
	private static final String HOST = "localhost";
	private static final String BBDD = "eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public void run() {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);
			
			Statement st = con.createStatement(); //Crear el ejecutor de sentencias
			/*String sentenciaInsert = "INSERT INTO animales (nombre) VALUES ('Test');";
			st.execute(sentenciaInsert);*/  //No es necesario ";" si solo es una linea.
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		final int INSERTAR_ARBOL = 1;
		final int ELIMINAR_ARBOL = 2;
		final int MODIFICAR_INFOR = 3;
		final int VISUALIZAR_ARBOLES = 4;
		final int SALIR = 0;

		Scanner scan = new Scanner(System.in);
		int opcion_menu;

		do {
			System.out.println(" ------MENU------");
			System.out.println(INSERTAR_ARBOL + ". Insertar árbol");
			System.out.println(ELIMINAR_ARBOL + ". Eliminar árbol");
			System.out.println(MODIFICAR_INFOR + ". Modificar informacion");
			System.out.println(VISUALIZAR_ARBOLES + ". Visualizar árboles");
			System.out.println(SALIR + ". Salir");
			System.out.println("Elije una de las opciones");
			opcion_menu = Integer.parseInt(scan.nextLine());

			switch (opcion_menu) {
			case INSERTAR_ARBOL:
				System.out.println("Opcion escogida: " + INSERTAR_ARBOL + "\n");
				
				System.out.println("Escribe el nombre comun del árbol: ");
				String nombreComun = scan.nextLine();
				System.out.println("Escribe el nombre cientifico del árbol: ");
				String nombreCientifico = scan.nextLine();
				System.out.println("Escribe el nombre del habitat del árbol: ");
				String habitat = scan.nextLine();
				System.out.println("Escribe la altura del árbol: ");
				int altura = Integer.parseInt(scan.nextLine());
				System.out.println("Escribe el origen del árbol: ");
				String origen = scan.nextLine();

				break;
			case ELIMINAR_ARBOL:
				System.out.println("Opcion escogida: " + ELIMINAR_ARBOL + "\n");

				break;
			case MODIFICAR_INFOR:
				System.out.println("Opcion escogida: " + MODIFICAR_INFOR + "\n");

				break;
			case VISUALIZAR_ARBOLES:
				System.out.println("Opcion escogida: " + VISUALIZAR_ARBOLES + "\n");

				break;
			case SALIR:
				System.out.println("ADIOS");
				break;
			default:
				System.out.println("Opcion incorrecta!");
			}

		} while (opcion_menu != SALIR);
		scan.close();
		
	}
	
}
