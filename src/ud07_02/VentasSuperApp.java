package ud07_02;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class VentasSuperApp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		final double IVA = 0.21;
		final double IVAREDU = 0.04;

		double tipoIVA;
		double precioBruto;
		double cantidadPagada;
		double cambio;
		double ivaSelec = 0;
		String otroArticulo;
		String nomArticulo;

		ArrayList<Double> listCompra = new ArrayList<>();

		// ------------------------ INSERTAMOS DATOS -------------------------------

		// Compra articulo
		do {
			double totalArticulos = 0;
			double totalPrecioBruto = 0;
			double precioIVA = 0;

			do {
				System.out.println("Nombre Artículo:");
				nomArticulo = scan.next();
				System.out.println("Precio:");
				precioBruto = scan.nextDouble();

				// Calculamos el total del preciBruto de los artículos
				totalPrecioBruto += precioBruto;
				// Contador artículos comprados
				totalArticulos++;

				System.out.println("Comprar otro artículo (s)");
				otroArticulo = scan.next();
			} while (otroArticulo.equalsIgnoreCase("s"));

			// Recogemos el tipoIVA seleccionado
			do {
				System.out.println("Tipo de IVA (1= 21%  2= 4%)");
				tipoIVA = scan.nextInt();
				if (tipoIVA == 1) {
					// precio con IVA 21 del total del precioBruto
					precioIVA += (totalPrecioBruto * IVA) + totalPrecioBruto;
					ivaSelec = 21;
				} else if (tipoIVA == 2) {
					// precio con IVA 4 del total del precioBruto
					precioIVA += (totalPrecioBruto * IVAREDU) + totalPrecioBruto;
					ivaSelec = 4;
				} else
					System.err.println("Tipo de IVA incorrecto");
			} while (tipoIVA < 1 || tipoIVA > 2);

			// Muestra precio total con IVA incluido
			System.out.println("El precio total con IVA " + ivaSelec + ": " + precioIVA);
			// Cantidad a pagar
			System.out.println("Cantidad pagada: ");
			cantidadPagada = scan.nextInt();
			// Cantidad a devolver
			cambio = cantidadPagada - precioIVA;

			// ------------------------- FIN INSERCIÓN DE DATOS ----------------------------

			// Inserción de los datos de la compra en la lista
			listCompra.add(ivaSelec);
			listCompra.add(totalPrecioBruto);
			listCompra.add(precioIVA);
			listCompra.add(totalArticulos);
			listCompra.add(cantidadPagada);
			listCompra.add(cambio);

			// Extraemos datos de la compra
			extraerDatosCompra(listCompra);

			// Limpiamos la lista de la compra
			listCompra.clear();

			// Otra venta
			System.out.println("\nOtra venta (s)");
			otroArticulo = scan.next();
		} while (otroArticulo.equalsIgnoreCase("s"));
	}

	/**
	 * Extraemos los datos de la lista
	 * 
	 * @param l_compra pasamos la lista por parámetro
	 */
	private static void extraerDatosCompra(ArrayList<Double> l_compra) {
		System.out.println("IVA Seleccionado: " + l_compra.get(0));
		System.out.println("Total precio bruto: " + l_compra.get(1));
		System.out.println("Total IVA incluido: " + l_compra.get(2));
		System.out.println("Total artículos: " + l_compra.get(3));
		System.out.println("Cantidad pagada: " + l_compra.get(4));
		System.out.println("Cambio cliente: " + l_compra.get(5));
	}

}
