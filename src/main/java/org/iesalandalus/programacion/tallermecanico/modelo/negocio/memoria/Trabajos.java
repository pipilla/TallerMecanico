package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return coleccionTrabajos = new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> coleccionTrabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                coleccionTrabajosCliente.add(trabajo);
            }
        }
        return coleccionTrabajosCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> coleccionTrabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                coleccionTrabajosVehiculo.add(trabajo);
            }
        }
        return coleccionTrabajosVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        Objects.requireNonNull(fechaTrabajo, "La fecha no puede ser nula.");
        for (Trabajo trabajo : coleccionTrabajos) {
            if (!trabajo.estaCerrado()) {
                if (trabajo.getCliente().equals(cliente)) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
                }
                if (trabajo.getVehiculo().equals(vehiculo)) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en un trabajo.");
                }
            } else {
                if (trabajo.getCliente().equals(cliente) && !fechaTrabajo.isAfter(trabajo.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene un trabajo posterior.");
                }
                if (trabajo.getVehiculo().equals(vehiculo) && !fechaTrabajo.isAfter(trabajo.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene un trabajo posterior.");
                }
            }
        }
    }

    private Trabajo getTrabajo(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        if (buscar(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        return buscar(trabajo);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        buscar(Trabajo.get(trabajo.getVehiculo())).anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        if (buscar(trabajo) instanceof Mecanico trabajoMecanico) {
            trabajoMecanico.anadirPrecioMaterial(precioMaterial);
        } else {
            throw new OperationNotSupportedException("Solo se puede añadir un precio de material a un trabajo mecánico.");
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        buscar(Trabajo.get(trabajo.getVehiculo())).cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return indice == -1 ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar una trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
