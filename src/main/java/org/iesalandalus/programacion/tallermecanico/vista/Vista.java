package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

public interface Vista {
    GestorEventos getGestorEventos();

    void comenzar();

    void terminar();

    void ejecutar(Evento opcion);

    void notificarResultado(Evento evento, String texto, boolean exito);

    void mostrarCliente(Cliente cliente);

    void mostrarVehiculo(Vehiculo vehiculo);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarClientes(Cliente clientes);

    void mostrarVehiculos(Vehiculos vehiculos);

    void mostrarTrabajos(Trabajos trabajos);

    void mostrarTrabajosCliente(Trabajos trabajosCliente);

    void mostrarTrabajosVehiculo(Trabajos trabajosVehiculo);
}
