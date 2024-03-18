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
    private GestorEventos gestorEventos = new GestorEventos(List<Evento> Evento.values());

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
            System.out.println();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
    }

    @Override
    public void terminar() {
        System.out.printf("¡HASTA PRONTO!%n");
    }

    @Override
    public void ejecutar(Evento opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE -> mostrarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> mostrarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> mostrarTrabajo();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrarRevision();
            case SALIR -> salir();
        }
    }


    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        gestorEventos.notificar(evento);
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
        Objects.requireNonNull(vehiculos, "Los clientes no pueden ser nulos.");
        System.out.printf("%s%n", vehiculos);
    }

    @Override
    public void mostrarTrabajos(Trabajos trabajos) {
        Objects.requireNonNull(trabajos, "Los clientes no pueden ser nulos.");
        System.out.printf("%s%n", trabajos);
    }

    @Override
    public void mostrarTrabajosCliente(Trabajos trabajosCliente) {
        Objects.requireNonNull(trabajosCliente, "Los clientes no pueden ser nulos.");
        System.out.printf("%s%n", trabajosCliente);
    }

    @Override
    public void mostrarTrabajosVehiculo(Trabajos trabajosVehiculo) {
        Objects.requireNonNull(trabajosVehiculo, "Los clientes no pueden ser nulos.");
        System.out.printf("%s%n", trabajosVehiculo);
    }

}