package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArboles {
	
	private static final String HOST = "localhost";
	private static final String BBDD = "eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public void run() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
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
			Connection con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);

			switch (opcion_menu) {
			case INSERTAR_ARBOL:				
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
				
					PreparedStatement insert = con.prepareStatement("INSERT INTO arboles (nombre_comun, nombre_cientifico, habitad, altura, origen) VALUES (?, ?, ?, ?, ?);");
					insert.setString(1, nombreComun);
					insert.setString(2, nombreCientifico);
					insert.setString(3, habitat);
					insert.setInt(4, altura);
					insert.setString(5, origen);
					insert.execute();
					con.close();
					System.out.println("Árbol insertado!");

				break;
			case ELIMINAR_ARBOL:
				System.out.println("Escribe el ID del árbol que quieres eliminar: ");
				int idArbol = Integer.parseInt(scan.nextLine());
				if(existe(idArbol)) {
					PreparedStatement delete = con.prepareStatement("DELETE FROM arboles WHERE id = ?;");
					delete.setInt(1, idArbol);
					delete.execute();
					con.close();
					System.out.println("El árbol con el ID " + idArbol + " ha sido eliminado!");
				}else {
					System.out.println("No puedo eliminar ese arbol porque no existe!");
				}

				break;
			case MODIFICAR_INFOR:
				System.out.println("Escribe el ID del árbol al que quieres modificar: ");
				int idModifi = Integer.parseInt(scan.nextLine());
				if(existe(idModifi)) {
					PreparedStatement sentenciaSelect = con.prepareStatement("SELECT * FROM arboles;");
					sentenciaSelect.execute();
					ResultSet resultado = sentenciaSelect.executeQuery();
					String nombreComunModifi = "";
					String nombreCientificoModifi = "";
					String habitatModifi = "";
					String alturaModifi = "";
					String origenModifi = "";
					while(resultado.next()) {
						if(resultado.getInt(1) == idModifi) {
							
							System.out.println("Escribe el nuevo nombre comun del árbol (" + resultado.getString(2) + "): ");
							nombreComunModifi = scan.nextLine();
							if(nombreComunModifi == "")
								nombreComunModifi = resultado.getString(2);
							System.out.println("Escribe el nuevo nombre cientifico del árbol (" + resultado.getString(3) + "): ");
							nombreCientificoModifi = scan.nextLine();
							if(nombreCientificoModifi == "")
								nombreCientificoModifi = resultado.getString(3);
							System.out.println("Escribe el nuevo habitat del árbol (" + resultado.getString(4) + "): ");
							habitatModifi = scan.nextLine();
							if(habitatModifi == "")
								habitatModifi = resultado.getString(4);
							System.out.println("Escribe la nueva altura del arbol (" + resultado.getInt(5) + "): ");
							alturaModifi = scan.nextLine();
							if(alturaModifi == "")
								alturaModifi = resultado.getString(5);
							System.out.println("Escribe el nuevo origen del árbol (" + resultado.getString(6) + "): ");
							origenModifi = scan.nextLine();
							if(origenModifi == "")
								origenModifi = resultado.getString(6);
					}
					}
				
					PreparedStatement modify = con.prepareStatement("UPDATE arboles SET nombre_comun= ?, nombre_cientifico= ?, habitad= ?, altura= ?, origen= ? WHERE id = ?;");
					modify.setString(1, nombreComunModifi);
					modify.setString(2, nombreCientificoModifi);
					modify.setString(3, habitatModifi);
					modify.setInt(4, Integer.parseInt(alturaModifi));
					modify.setString(5, origenModifi);
					modify.setInt(6, idModifi );
					modify.execute();
					con.close();
					System.out.println("El árbol con el ID " + idModifi + " ha sido modificado!");
				}else {
					System.out.println("No puedo modificar ese arbol porque no existe.");
				}

				break;
			case VISUALIZAR_ARBOLES:
				System.out.println("Lista de árboles: \n");
					PreparedStatement listar = con.prepareStatement("SELECT * FROM arboles;");
					listar.execute();
					ResultSet resultado = listar.executeQuery();
					System.out.println("ID - Nom Comun - Nom Cientifi - Habitad - Altura - Origen\n");
					while(resultado.next()) {
						System.out.println(resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3) + " - " + resultado.getString(4) + " - " + resultado.getInt(5) + " - " + resultado.getString(6));
					}
					con.close();

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
	
	public boolean existe(int id) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);
		Statement st = con.createStatement();
		boolean existe = false;
	
		String sentenciaSelect = "SELECT * FROM arboles;";
		ResultSet resultado = st.executeQuery(sentenciaSelect);
		while(resultado.next()) {
			if(resultado.getInt(1) == id)
				existe = true;
		}
		con.close();
		st.close();
		
		return existe;
	}
	
}
