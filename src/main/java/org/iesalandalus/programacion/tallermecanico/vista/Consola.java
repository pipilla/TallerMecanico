package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "";

    private Consola(){}

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.printf(String.format("%s%n", String.format("%%0%dd", mensaje.length())));
    }

    public static void mostrarMenu() {
        System.out.println("MENÚ:");
        for (Opcion opcion : Opcion.values()) {
            System.out.printf("%s", opcion);
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        System.out.print(mensaje);
        LocalDate fecha = null;
        boolean fechaCorrecta = false;
        do {
            try {
                fecha = LocalDate.parse(Entrada.cadena(), Revision.FORMATO_FECHA);
                fechaCorrecta = true;
            } catch (DateTimeParseException e) {
                fechaCorrecta = false;
            }
        } while (!fechaCorrecta);
        return fecha;
    }

    public static Opcion elegirOpcion(){
        Opcion opcion;
        do {
            opcion = Opcion.get(leerEntero("Elige una opción: "));
        } while (opcion == null);
        return opcion;
    }

    public static Cliente leerCliente() {
        Cliente cliente;
        try {
            cliente = new Cliente(leerCadena("Dime el "), leerCadena("Dime el dni del cliente: "), )
        }
    }
}
