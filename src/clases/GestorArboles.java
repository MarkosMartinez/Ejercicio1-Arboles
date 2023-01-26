package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
				Arbol arbol = new Arbol();
				System.out.println("Escribe el nombre comun del árbol: ");
				arbol.setNombreComun(scan.nextLine());
				System.out.println("Escribe el nombre cientifico del árbol: ");
				arbol.setNombreCientifico(scan.nextLine());
				System.out.println("Escribe el nombre del habitat del árbol: ");
				arbol.setHabitat(scan.nextLine());
				System.out.println("Escribe la altura del árbol: ");
				arbol.setAltura(Integer.parseInt(scan.nextLine()));
				System.out.println("Escribe el origen del árbol: ");
				arbol.setOrigen(scan.nextLine());
				
					PreparedStatement insert = con.prepareStatement("INSERT INTO arboles (nombre_comun, nombre_cientifico, habitad, altura, origen) VALUES (?, ?, ?, ?, ?);");
					insert.setString(1, arbol.getNombreComun());
					insert.setString(2, arbol.getNombreCientifico());
					insert.setString(3, arbol.getHabitat());
					insert.setInt(4, arbol.getAltura());
					insert.setString(5, arbol.getOrigen());
					insert.execute();
					con.close();
					System.out.println("Árbol insertado!");

				break;
			case ELIMINAR_ARBOL:
				arbol = new Arbol();
				System.out.println("Escribe el ID del árbol que quieres eliminar: ");
				arbol.setId(Integer.parseInt(scan.nextLine()));
				
				if(existe(arbol.getId())) {
					PreparedStatement delete = con.prepareStatement("DELETE FROM arboles WHERE id = ?;");
					delete.setInt(1, arbol.getId());
					delete.execute();
					con.close();
					System.out.println("El árbol con el ID " + arbol.getId() + " ha sido eliminado!");
				}else {
					System.out.println("No puedo eliminar ese arbol porque no existe!");
				}

				break;
			case MODIFICAR_INFOR:
				arbol = new Arbol();
				System.out.println("Escribe el ID del árbol al que quieres modificar: ");
				arbol.setId(Integer.parseInt(scan.nextLine()));
				if(existe(arbol.getId())) {
					PreparedStatement sentenciaSelect = con.prepareStatement("SELECT * FROM arboles;");
					sentenciaSelect.execute();
					ResultSet resultado = sentenciaSelect.executeQuery();
					while(resultado.next()) {
						if(resultado.getInt(1) == arbol.getId()) {
							
							System.out.println("Escribe el nuevo nombre comun del árbol (" + resultado.getString(2) + "): ");
							arbol.setNombreComun(scan.nextLine());
							if(arbol.getNombreComun() == "")
								arbol.setNombreComun(resultado.getString(2));
							System.out.println("Escribe el nuevo nombre cientifico del árbol (" + resultado.getString(3) + "): ");
							arbol.setNombreCientifico(scan.nextLine());
							if(arbol.getNombreCientifico() == "")
								arbol.setNombreCientifico(resultado.getString(3));
							System.out.println("Escribe el nuevo habitat del árbol (" + resultado.getString(4) + "): ");
							arbol.setHabitat(scan.nextLine());
							if(arbol.getHabitat() == "")
								arbol.setHabitat(resultado.getString(4));
							System.out.println("Escribe la nueva altura del arbol (" + resultado.getInt(5) + "): ");
							if(scan.nextLine() == "") {
								arbol.setAltura(-1000);
							}else {
							arbol.setAltura(Integer.parseInt(scan.nextLine()));
							}
							if(arbol.getAltura() == -1000)
								arbol.setAltura(Integer.parseInt(resultado.getString(5)));
							System.out.println("Escribe el nuevo origen del árbol (" + resultado.getString(6) + "): ");
							arbol.setOrigen(scan.nextLine());
							if(arbol.getOrigen() == "")
								arbol.setOrigen(resultado.getString(6));
					}
					}
				
					PreparedStatement modify = con.prepareStatement("UPDATE arboles SET nombre_comun= ?, nombre_cientifico= ?, habitad= ?, altura= ?, origen= ? WHERE id = ?;");
					modify.setString(1, arbol.getNombreComun());
					modify.setString(2, arbol.getNombreCientifico());
					modify.setString(3, arbol.getHabitat());
					modify.setInt(4, arbol.getAltura());
					modify.setString(5, arbol.getOrigen());
					modify.setInt(6, arbol.getId() );
					modify.execute();
					con.close();
					System.out.println("El árbol con el ID " + arbol.getId() + " ha sido modificado!");
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
					ArrayList<Arbol> arboles = new ArrayList<Arbol>();
					while(resultado.next()) {
						arbol = new Arbol();
						arbol.setId(resultado.getInt("id"));
						arbol.setNombreComun(resultado.getString("nombre_comun"));
						arbol.setNombreCientifico(resultado.getString("nombre_cientifico"));
						arbol.setHabitat(resultado.getString("habitad"));
						arbol.setAltura(resultado.getInt("altura"));
						arbol.setOrigen(resultado.getString("origen"));
	
						arboles.add(arbol);
					}
					
					for (Arbol arbol2 : arboles) {
						System.out.println(arbol2);
					}
					
					con.close();

				break;
			case SALIR:
				System.out.println("Agur!");
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
	
		String sentenciaSelect = "SELECT * FROM arboles;";
		ResultSet resultado = st.executeQuery(sentenciaSelect);
		while(resultado.next()) {
			if(resultado.getInt(1) == id)
				return true;
		}
		st.close();
		con.close();
		
		return false;
	}
	
}
