package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dia/mes/año";

    private Consola(){}

    public static void mostrarCabecera(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.println(mensaje);
        System.out.printf(String.format("%s%n", ("-").repeat(mensaje.length()))); //CORREGIR ESTO
    }

    public static void mostrarMenu() {
        mostrarCabecera("MENÚ");
        for (Opcion opcion : Opcion.values()) {
            System.out.printf("%s%n", opcion);
        }
        System.out.println();
    }

    private static int leerEntero(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaCorrecta = false;
        do {
            try {
                fecha = LocalDate.parse(leerCadena(mensaje), Revision.FORMATO_FECHA);
                fechaCorrecta = true;
            } catch (DateTimeParseException ignored){
                System.out.printf("La fecha introducida tiene un formato inválido (%s).%n", CADENA_FORMATO_FECHA);
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
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(leerCadena("Dime el nombre del cliente: "), leerCadena("Dime el dni del cliente: "), leerCadena("Dime el teléfono del cliente: "));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public static Cliente leerClienteDni() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Cliente.get(leerCadena("Dime el DNI del cliente: ")));
                clienteCorrecto = true;
            } catch (IllegalArgumentException| NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public static String leerNuevoNombre() {
        String nombre;
        boolean nombreCorrecto = false;
        do {
            nombre = leerCadena("Dime el nuevo nombre del cliente: ");
            if (!nombre.isBlank()) {
                try {
                    new Cliente(nombre, "11111111H", "600600600");
                    nombreCorrecto = true;
                } catch (IllegalArgumentException| NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);
        return nombre;
    }

    public static String leerNuevoTelefono() {
        String telefono;
        boolean telefonoCorrecto = false;
        do {
            telefono = leerCadena("Dime el nuevo telefono del cliente: ");
            if (!telefono.isBlank()) {
                try {
                    new Cliente("Juan", "11111111H", telefono);
                    telefonoCorrecto = true;
                } catch (IllegalArgumentException| NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                telefonoCorrecto = true;
            }

        } while (!telefonoCorrecto);
        return telefono;
    }

    public static Vehiculo leerVehiculo() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;

        do {
            try {
                vehiculo = new Vehiculo(leerCadena("Dime la marca del vehículo: "), leerCadena("Dime el modelo del vehículo: "), leerCadena("Dime la matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public static Vehiculo leerVehiculoMatricula() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;
        do {
            try {
                vehiculo = Vehiculo.get(leerCadena("Dime la matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException| NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public static Revision leerRevision() {
        Revision revision = null;
        boolean revisionCorrecta = false;

        do {
            try {
                revision = new Revision(leerClienteDni(), leerVehiculoMatricula(), leerFecha("Dime la fecha de inicio de la revisión: "));
                revisionCorrecta = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!revisionCorrecta);
        return revision;
    }

    public static int leerHoras() {
        int horas;
        boolean horasCorrectas = false;
        do {
            horas = leerEntero("Dime las horas que quieres añadir: ");
            try {
                Revision revision = new Revision(Cliente.get("11111111H"), Vehiculo.get("1111JKK"), LocalDate.now());
                revision.anadirHoras(horas);
                horasCorrectas = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!horasCorrectas);
        return horas;
    }

    public static float leerPrecioMaterial() {
        float precioMaterial;
        boolean precioCorrecto = false;
        do {
            precioMaterial = leerReal("Dime el precio que quieres añadir: ");
            try {
                Revision revision = new Revision(Cliente.get("11111111H"), Vehiculo.get("1111JKK"), LocalDate.now());
                revision.anadirPrecioMaterial(precioMaterial);
                precioCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!precioCorrecto);
        return precioMaterial;
    }

    public static LocalDate leerFechaCierre() {
        LocalDate fechaCierre;
        boolean fechaCierreCorrecta = false;
        do {
            fechaCierre = leerFecha("Dime la fecha de cierre: ");
            try {
                Revision revision = new Revision(Cliente.get("11111111H"), Vehiculo.get("1111JKK"), LocalDate.of(1900, 1, 1));
                revision.cerrar(fechaCierre);
                fechaCierreCorrecta = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!fechaCierreCorrecta);
        return fechaCierre;
    }
}
