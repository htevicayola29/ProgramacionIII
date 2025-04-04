package PRACTICA_4;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Estudiante> estudiantes = new ArrayList<>();

        System.out.print("¿Cuántos estudiantes desea ingresar?: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- Estudiante #" + (i + 1) + " ---");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Primer apellido: ");
            String primerApellido = scanner.nextLine();

            System.out.print("Segundo apellido: ");
            String segundoApellido = scanner.nextLine();

            System.out.print("Año de nacimiento (yyyy): ");
            int anio = Integer.parseInt(scanner.nextLine());
            System.out.print("Mes de nacimiento (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.print("Día de nacimiento: ");
            int dia = Integer.parseInt(scanner.nextLine());
            Date fechaNacimiento = new Date(anio - 1900, mes, dia);

            System.out.print("Número de CI: ");
            int numeroCI = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Tiene complemento? (s/n): ");
            String tieneComplemento = scanner.nextLine();
            String complemento = "";
            if (tieneComplemento.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el complemento: ");
                complemento = scanner.nextLine();
            }

            System.out.print("Código SIS: ");
            String codigoSIS = scanner.nextLine();

            Estudiante estudiante = new Estudiante(nombre, primerApellido, segundoApellido, fechaNacimiento, numeroCI, complemento, codigoSIS);
            estudiantes.add(estudiante);
        }

        // Ordenar por edad
        estudiantes.sort(Comparator.comparingInt(Estudiante::calcularEdad));

        // Mostrar estudiantes
        System.out.println("\n--- Lista de estudiantes ordenada por edad ---");
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
    }
}
