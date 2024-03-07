package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Modelo {
    private Clientes clientes;
    private Trabajos trabajos;
    private IVehiculos IVehiculos;

    public Modelo() {
        comenzar();
    }
    public void comenzar() {
        clientes = new Clientes();
        trabajos = new Trabajos();
        IVehiculos = new Vehiculos();
    }

    public void terminar() {
        System.out.printf("El modelo ha terminado. %n");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        IVehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        trabajos.insertar(new Revision(buscar(revision.getCliente()), buscar(revision.getVehiculo()), revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return IVehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return new Revision(trabajos.buscar(revision));
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(revision, horas);
    }
    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Revision revision : trabajos.get(cliente)) {
            borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Revision revision : trabajos.get(vehiculo)) {
            borrar(revision);
        }
        IVehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        trabajos.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> nuevosClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            nuevosClientes.add(new Cliente(cliente));
        }
        return nuevosClientes;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(IVehiculos.get());
    }

    public List<Revision> getRevisiones() {
        List<Revision> nuevasRevisiones = new ArrayList<>();
        for (Revision revision : trabajos.get()) {
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> nuevasRevisiones = new ArrayList<>();
        for (Revision revision : trabajos.get(cliente)) {
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> nuevasRevisiones = new ArrayList<>();
        for (Revision revision : trabajos.get(vehiculo)) {
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }
}
