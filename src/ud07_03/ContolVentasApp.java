package ud07_03;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ContolVentasApp {
	
	public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //Creamos un objeto de tipo Hashtable para introducir los productos por clave: valor
        Hashtable<String, Double> stockProductos = new Hashtable<>();
        //Creamos un ArrayList para introducir los productos
        ArrayList<String> productos = new ArrayList<>();

        int opcion;

        //Elegimos opción del menú
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Añadir producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Mostar productos");
            System.out.println("4. Salir");
            System.out.print("Elegir opcion: ");
            opcion = scan.nextInt();

            switch (opcion) {

                //Añade articulos a la lista y añade el articulo 'clave' y el precio 'valor' al Hashtable
                case 1:
                    System.out.print("Articulo: ");
                    String nuevoProducto = scan.next();
                    System.out.print("Precio: ");
                    double precio = scan.nextDouble();

                    //Añade el artículo a la lista
                    productos.add(nuevoProducto);
                    //Añade la clave que es el artículo y su valor que es el precio al Hashtable
                    stockProductos.put(nuevoProducto, precio);
                    break;

                //Consulta los productos
                case 2:
                    System.out.print("Articulo: ");
                    String buscaProducto = scan.next();

                    //Muestra el precio obtenido de Hashtable a través de su clave
                    System.out.println("Precio: " + stockProductos.get(buscaProducto));
                    break;

                //Muestra todos los productos que hay en el Hashtable
                case 3:
                    //Recorre la lista de productos y obtiene el nombre del articulo y el precio del Hashtable
                    for (String producto : productos) {
                        System.out.println("Articulo: " + producto);
                        System.out.println("Precio: " + stockProductos.get(producto));
                        System.out.println();
                    }
                    break;
                //Salimos de la aplicacion
                case 4:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (!(opcion == 4));

        scan.close();
    }

}
