package clases;

import java.util.Scanner;

public class GestorArboles {

	public void run() {
	
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
