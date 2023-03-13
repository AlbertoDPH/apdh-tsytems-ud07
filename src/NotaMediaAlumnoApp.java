import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class NotaMediaAlumnoApp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String nomAlumno;
		double notaProgra;
		double notabbdd;
		double notaMedia;
		String opcion;

		// Iremos introduciendo todos los datos de los alumnos 'clave:nomAlumno'
		// 'valor:notaMedia'
		Hashtable<String, Double> datosAlumnos = new Hashtable<>();

		// Guardamos todas la notas de cada alumno
		ArrayList<Double> notasAlumno = new ArrayList<>();

		// Controla si queremos seguir introduciendo alumnos
		do {
			System.out.println("Nombre del alumno:");
			nomAlumno = scan.next();

			System.out.println("Nota programación:");
			notaProgra = scan.nextDouble();

			System.out.println("Nota BBDD:");
			notabbdd = scan.nextDouble();

			// Inserta en el Lista las notas del alumno
			notasAlumno.add(notaProgra);
			notasAlumno.add(notabbdd);

			// Llama al método que calcula la nota media del alumno
			notaMedia = notaMediaAlumno(notasAlumno);

			// Inserta en el Hashteble el alumno y su nota media
			datosAlumnos.put(nomAlumno, notaMedia);

			// Borra la lista de las notas del alumno
			notasAlumno.clear();

			System.out.println("Quieres introducir más alumnos (s/n)");
			opcion = scan.next();

		} while (opcion.equalsIgnoreCase("s"));

		// Llama al método que muestra los datos del alumno
		datosAlumnos(datosAlumnos);

		scan.close();

	}

	private static void datosAlumnos(Hashtable<String, Double> insertAlumnos) {

		System.out.println("\nDatos alumnos");

		Enumeration<Double> datosAlumno = insertAlumnos.elements();
		Enumeration<String> key = insertAlumnos.keys();

		while (datosAlumno.hasMoreElements()) {
			System.out.println("Nombre del alumno: " + key.nextElement());
			System.out.println("Nota media: " + datosAlumno.nextElement());
		}
	}

	private static double notaMediaAlumno(ArrayList<Double> notasAlumno) {

		double notaTotal = 0;

		for (int i = 0; i < notasAlumno.size(); i++) {
			notaTotal += notasAlumno.get(i);
		}
		return notaTotal / notasAlumno.size();

	}

}
