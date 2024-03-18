package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

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