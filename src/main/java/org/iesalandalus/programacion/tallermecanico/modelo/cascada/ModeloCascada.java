package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ModeloCascada implements org.iesalandalus.programacion.tallermecanico.modelo.Modelo {
    private IClientes clientes;
    private ITrabajos trabajos;
    private IVehiculos vehiculos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        trabajos = fuenteDatos.crearTrabajos();
        vehiculos = fuenteDatos.crearVehiculos();
    }
    @Override
    public void comenzar() {
        System.out.printf("El modelo ha comenzado.%n");
    }

    @Override
    public void terminar() {
        System.out.printf("El modelo ha terminado.%n");
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        if (trabajo instanceof Revision revision) {
            trabajos.insertar(new Revision(buscar(revision.getCliente()), buscar(revision.getVehiculo()), revision.getFechaInicio()));
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajos.insertar(new Mecanico(buscar(mecanico.getCliente()), buscar(mecanico.getVehiculo()), mecanico.getFechaInicio()));
        }
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        trabajo = trabajos.buscar(trabajo);
        if (trabajo instanceof Revision revision) {
            trabajo = new Revision(revision);
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajo = new Mecanico(mecanico);
        }
        return trabajo;
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }
    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(cliente)) {
            borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> nuevosClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            nuevosClientes.add(new Cliente(cliente));
        }
        return nuevosClientes;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> nuevosTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            if (trabajo instanceof Revision revision) {
                nuevosTrabajos.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                nuevosTrabajos.add(new Mecanico(mecanico));
            }
        }
        return nuevosTrabajos;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> nuevosTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            if (trabajo instanceof Revision revision) {
                nuevosTrabajos.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                nuevosTrabajos.add(new Mecanico(mecanico));
            }
        }
        return nuevosTrabajos;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> nuevosTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            if (trabajo instanceof Revision revision) {
                nuevosTrabajos.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                nuevosTrabajos.add(new Mecanico(mecanico));
            }
        }
        return nuevosTrabajos;
    }
}
