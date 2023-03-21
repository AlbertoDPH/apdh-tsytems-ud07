package ud07_02;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class VentasSuperApp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// Lo usaremos para introducir todos los datos de la compra
		Hashtable<Integer, ArrayList<Double>> insertar = new Hashtable<>();

		final double IVA = 0.21;
		final double IVAREDU = 0.04;

		double tipoIVA;
		double precioBruto;
		double cantidadPagada;
		double cambio;
		double ivaSelec = 0;
		String otroArticulo;
		String nomArticulo;
		int contArticulo = 1;

		// ------------------------ INSERTAMOS DATOS -------------------------------

		// Compra articulo
		do {

			double totalArticulos = 0;
			double totalPrecioBruto = 0;
			double precioIVA = 0;

			System.out.println("Compra num: " + contArticulo);

			do {
				// Insertamos noombre de artículo y precio
				System.out.print("Nombre Articulo: ");
				nomArticulo = scan.next();
				System.out.print("Precio: ");
				precioBruto = scan.nextDouble();

				// Calculamos el total del preciBruto de los artículos
				totalPrecioBruto += precioBruto;
				// Contador artículos comprados
				totalArticulos++;

				System.out.println("Comprar otro articulo (s/n)");
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
					// Controla que la la opción del IVA es la correcta
					System.err.println("Tipo de IVA incorrecto");
			} while (tipoIVA < 1 || tipoIVA > 2);

			// Muestra precio total con IVA incluido
			System.out.println("El precio total con IVA " + ivaSelec + ": " + precioIVA);

			do {
				// Cantidad a pagar
				System.out.print("Cantidad pagada: ");
				cantidadPagada = scan.nextInt();

				// Controla que la cantidad pagada es correcta
				if (cantidadPagada < precioIVA)
					System.err.println("La cantidad pagada no es valida");
			} while (cantidadPagada < precioIVA);

			// Cantidad a devolver
			cambio = cantidadPagada - precioIVA;

			// ------------------------- FIN INSERCIÓN DE DATOS ----------------------------

			// Creamos una lista para guardar los datos
			ArrayList<Double> datosArticulos = new ArrayList<>();

			// Añadismos los datos del la comrpa en el ArrayList
			datosArticulos = datosCompra(ivaSelec, totalPrecioBruto, precioIVA, totalArticulos, cantidadPagada, cambio);

			// Insertamos en el Hashtable el número de compra como Key y el ArrayList con
			// los datos de la compra
			insertar.put(contArticulo, datosArticulos);

			// Otra venta
			System.out.println("\nOtra venta (s/n)");
			otroArticulo = scan.next();

			if (otroArticulo.equalsIgnoreCase("s"))
				contArticulo++;

		} while (otroArticulo.equalsIgnoreCase("s"));

		// Extraemos datos de la compra
		extraerDatosCompra(insertar);

		scan.close();
	}

	/**
	 * Insertamos tosod los datos de la compra, numero de compra y listado de los
	 * datos de la compra en el Hashtable
	 * 
	 * @param contArticulo
	 * @param datosArticulos
	 * @return
	 */
	public static Hashtable<Integer, ArrayList<Double>> insertarEnHash(int contArticulo,
			ArrayList<Double> datosArticulos) {

		Hashtable<Integer, ArrayList<Double>> compras = new Hashtable<Integer, ArrayList<Double>>();

		compras.put(contArticulo, datosArticulos);

		return compras;

	}

	/**
	 * Rellenamos la lista con todos loss datos de la compra menos el númerod e
	 * compra
	 * 
	 * @param ivaSelec
	 * @param totalPrecioBruto
	 * @param precioIVA
	 * @param totalArticulos
	 * @param cantidadPagada
	 * @param cambio
	 * @return devuelve la una lista con todos los datos de la compra
	 */
	public static ArrayList<Double> datosCompra(double ivaSelec, Double totalPrecioBruto, Double precioIVA,
			Double totalArticulos, Double cantidadPagada, Double cambio) {

		ArrayList<Double> listCompra = new ArrayList<>();

		listCompra.add(ivaSelec);
		listCompra.add(totalPrecioBruto);
		listCompra.add(precioIVA);
		listCompra.add(totalArticulos);
		listCompra.add(cantidadPagada);
		listCompra.add(cambio);

		return listCompra;

	}

	/**
	 * Extraemos los datos del Hashtacle para que se muestren por consola
	 * 
	 * @param l_compra pasamos por parámetro el Hashtable que contiene el número de
	 *                 la compra y el listado de los datos de la compra
	 */
	public static void extraerDatosCompra(Hashtable<Integer, ArrayList<Double>> l_compra) {

		Enumeration<Integer> enumerationKeys = l_compra.keys();
		while (enumerationKeys.hasMoreElements()) {

			int numCompra = enumerationKeys.nextElement();
			System.out.println("\nDatos de la compra: " + numCompra);

			ArrayList<Double> datos = l_compra.get(numCompra);
			System.out.println("IVA Seleccionado: " + datos.get(0));
			System.out.println("Total precio bruto: " + datos.get(1));
			System.out.println("Total IVA incluido: " + datos.get(2));
			System.out.println("Total articulos: " + datos.get(3));
			System.out.println("Cantidad pagada: " + datos.get(4));
			System.out.println("Cambio cliente: " + datos.get(5));
			System.out.println();
		}
	}

}
