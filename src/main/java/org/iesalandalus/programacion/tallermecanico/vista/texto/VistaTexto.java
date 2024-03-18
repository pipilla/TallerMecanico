package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class VistaTexto implements org.iesalandalus.programacion.tallermecanico.vista.Vista {
    public static final String DNI_EJEMPLO = "11111111H";
    public static final String MATRICULA_DEFECTO = "1111JKK";
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            Consola.mostrarCabecera(evento.name());
            ejecutar(evento);
        } while (evento != Evento.SALIR);
        terminar();
    }

    @Override
    public void terminar() {
        System.out.printf("¡HASTA PRONTO!%n");
    }

    private void ejecutar(Evento opcion) {
        getGestorEventos().notificar(opcion);
    }

    public static Cliente leerCliente() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Consola.leerCadena("Dime el nombre del cliente: "), Consola.leerCadena("Dime el dni del cliente: "), Consola.leerCadena("Dime el teléfono del cliente: "));
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
                cliente = new Cliente(Cliente.get(Consola.leerCadena("Dime el DNI del cliente: ")));
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
            nombre = Consola.leerCadena("Dime el nuevo nombre del cliente: ");
            if (!nombre.isBlank()) {
                try {
                    new Cliente(nombre, DNI_EJEMPLO, "600600600");
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
            telefono = Consola.leerCadena("Dime el nuevo telefono del cliente: ");
            if (!telefono.isBlank()) {
                try {
                    new Cliente("Juan", DNI_EJEMPLO, telefono);
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
                vehiculo = new Vehiculo(Consola.leerCadena("Dime la marca del vehículo: "), Consola.leerCadena("Dime el modelo del vehículo: "), Consola.leerCadena("Dime la matrícula del vehículo: "));
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
                vehiculo = Vehiculo.get(Consola.leerCadena("Dime la matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException| NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public static Trabajo leerRevision() {
        Revision revision = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                revision = new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return revision;
    }

    public static Trabajo leerMecanico() {
        Trabajo mecanico = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                mecanico = new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return mecanico;
    }

    public static Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculo());
    }

    public static int leerHoras() {
        int horas;
        boolean horasCorrectas = false;
        do {
            horas = Consola.leerEntero("Dime las horas que quieres añadir: ");
            try {
                Revision revision = new Revision(Cliente.get(DNI_EJEMPLO), Vehiculo.get(MATRICULA_DEFECTO), LocalDate.now());
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
            precioMaterial = Consola.leerReal("Dime el precio que quieres añadir: ");
            try {
                Mecanico mecanico = new Mecanico(Cliente.get(DNI_EJEMPLO), Vehiculo.get(MATRICULA_DEFECTO), LocalDate.now());
                mecanico.anadirPrecioMaterial(precioMaterial);
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
            fechaCierre = Consola.leerFecha("Dime la fecha de cierre: ");
            try {
                Revision revision = new Revision(Cliente.get(DNI_EJEMPLO), Vehiculo.get(MATRICULA_DEFECTO), LocalDate.of(1900, 1, 1));
                revision.cerrar(fechaCierre);
                fechaCierreCorrecta = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!fechaCierreCorrecta);
        return fechaCierre;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if (exito) {
            System.out.printf("%s: %s%n", evento, texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        System.out.printf("%s%n", cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        System.out.printf("%s%n", vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El vehiculo no puede ser nulo.");
        System.out.printf("%s%n", trabajo);
    }

    @Override
    public void mostrarClientes(Cliente clientes) {
        Objects.requireNonNull(clientes, "Los clientes no pueden ser nulos.");
        System.out.printf("%s%n", clientes);
    }

    @Override
    public void mostrarVehiculos(Vehiculos vehiculos) {
        Objects.requireNonNull(vehiculos, "Los vehículos no pueden ser nulos.");
        System.out.printf("%s%n", vehiculos);
    }

    @Override
    public void mostrarTrabajos(Trabajos trabajos) {
        Objects.requireNonNull(trabajos, "Los trabajos no pueden ser nulos.");
        System.out.printf("%s%n", trabajos);
    }

    @Override
    public void mostrarTrabajosCliente(Trabajos trabajosCliente) {
        Objects.requireNonNull(trabajosCliente, "Los trabajos para el cliente no pueden ser nulos.");
        System.out.printf("%s%n", trabajosCliente);
    }

    @Override
    public void mostrarTrabajosVehiculo(Trabajos trabajosVehiculo) {
        Objects.requireNonNull(trabajosVehiculo, "Los trabajos para el vehiculo no pueden ser nulos.");
        System.out.printf("%s%n", trabajosVehiculo);
    }

}