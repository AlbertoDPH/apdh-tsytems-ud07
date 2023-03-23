package ud07_04;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Combinación de las dos clases VentasSuperApp y ControlVentasApp
 * 
 * @author Alberto
 *
 */
public class VentasControlStock {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// Almacenara los datos de las compras a traves de clave: numero de compra
		// valor: listado de los daros de la compra
		Hashtable<Integer, ArrayList<Double>> insertar = new Hashtable<>();
		// Almacena los datos del stock productos a traves de clave: nombre del proucto
		// valor: su precio

		Hashtable<String, Double> stockProductos = new Hashtable<>();
		// Almacena los nosmbres del los productos que estan en el stock
		ArrayList<String> productos = new ArrayList<>();

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

		int opcion;

		// Bucle del menu principal
		do {
			System.out.println("\nMenu:");
			System.out.println("1. Añadir producto");
			System.out.println("2. Buscar producto");
			System.out.println("3. Mostrar productos");
			System.out.println("4. Realizar venta");
			System.out.println("5. Mostrar ventas");
			System.out.println("6. Salir");
			System.out.print("Elegir opcion: ");
			opcion = scan.nextInt();

			switch (opcion) {
			// Seleccionamos una opcion
			case 1:
				// Inserta articulos
				System.out.print("Articulo: ");
				String nuevoProducto = scan.next();
				System.out.print("Precio: ");
				double precio = scan.nextDouble();

				// añade el producto a la lista de productos
				productos.add(nuevoProducto);

				// añade al Hashtable el nombre del producto nuevo y el precio
				stockProductos.put(nuevoProducto, precio);
				break;

			case 2:
				// Busca articulos
				System.out.print("Articulo: ");
				String buscaProducto = scan.next();

				// A traves del nombre del producto en el Hash encontramos el precio
				System.out.println("Precio: " + stockProductos.get(buscaProducto));
				break;

			case 3:
				// Muestra los articulos iterando en cada producto para que al acceder al Hash
				// nos muetre su valor: precio
				for (String producto : productos) {
					System.out.println("Articulo: " + producto);
					System.out.println("Precio: " + stockProductos.get(producto));
					System.out.println();
				}
				break;

			case 4:
				// Realiza ventas
				do {
					double totalArticulos = 0;
					double totalPrecioBruto = 0;
					double precioIVA = 0;

					System.out.println("Compra num: " + contArticulo);

					// Añade articulos a la compra
					do {
						System.out.print("Nombre Articulo: ");
						nomArticulo = scan.next();

						// guarda el precio del articulo seleccioando
						precioBruto = stockProductos.get(nomArticulo);

						// Actualiza el precio bruto
						totalPrecioBruto += precioBruto;

						// Actualiza el numero de articulos comprados
						totalArticulos++;

						System.out.println("Comprar otro articulo (s/n)");
						otroArticulo = scan.next();

						System.out.println("Articulo no encontrado");
						otroArticulo = "s";

					} while (otroArticulo.equalsIgnoreCase("s"));

					// Seleccionar tipo de IVA
					do {
						System.out.println("Tipo de IVA (1= 21%  2= 4%)");
						tipoIVA = scan.nextInt();
						if (tipoIVA == 1) {
							precioIVA += (totalPrecioBruto * IVA) + totalPrecioBruto;
							ivaSelec = 21;

						} else if (tipoIVA == 2) {
							precioIVA += (totalPrecioBruto * IVAREDU) + totalPrecioBruto;
							ivaSelec = 4;

						} else
							System.err.println("Tipo de IVA incorrecto");
					} while (tipoIVA < 1 || tipoIVA > 2);

					System.out.println("El precio total con IVA " + ivaSelec + ": " + precioIVA);

					// Controla que la cantidad a pagar sea correcta
					do {
						System.out.print("Cantidad pagada: ");
						cantidadPagada = scan.nextInt();

						if (cantidadPagada < precioIVA)
							System.err.println("La cantidad pagada no es valida");
					} while (cantidadPagada < precioIVA);

					cambio = cantidadPagada - precioIVA;

					// Inserta los datos de una venta en el Hash
					ArrayList<Double> datosArticulos = new ArrayList<>();

					// Llamada al metodo datosCompra y recibe por parametro todo los datos del la
					// compra para luego
					// guardarlos en la lista datosArticulos
					datosArticulos = datosCompra(ivaSelec, totalPrecioBruto, precioIVA, totalArticulos, cantidadPagada,
							cambio);

					// Inserta los datos de la venta en el Hash usando la clave Integer como
					// contArticulo y el ArrayList datosArticulos
					insertar.put(contArticulo, datosArticulos);

					System.out.println("\nOtra venta (s/n)");
					otroArticulo = scan.next();

					if (otroArticulo.equalsIgnoreCase("s"))
						contArticulo++;

				} while (otroArticulo.equalsIgnoreCase("s"));
				break;

			case 5:
				// Muestra las ventas realizadas, llamando al metodo y le pasa por parámetro el
				// Hash para que muestre los datos
				// de cada venta
				extraerDatosCompra(insertar);
				break;

			case 6:
				// Salir del programa
				System.out.println("Fin del programa");
				break;

			default:
				// Opcion no valida
				System.out.println("Opcion no valida");
			}
		} while (opcion != 6);

		scan.close();
	}

	/**
	 * Metodo que almacena los datos de la compra, recibiendo por parámetro todos lo
	 * datos
	 * 
	 * @param ivaSelec
	 * @param totalPrecioBruto
	 * @param precioIVA
	 * @param totalArticulos
	 * @param cantidadPagada
	 * @param cambio
	 * @return
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
	 * Metodo que extrae los datos de las compras realizadas y los muestra por
	 * consola
	 * 
	 * @param l_compra Se le pasa un Hashtable con el número de la compra y el
	 *                 listado de la compra
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
