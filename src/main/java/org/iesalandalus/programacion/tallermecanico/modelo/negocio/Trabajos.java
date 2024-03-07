package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos {
    private List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    public List<Trabajo> get() {
        return coleccionTrabajos = new ArrayList<>(coleccionTrabajos);
    }

    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> coleccionTrabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                coleccionTrabajosCliente.add(trabajo);
            }
        }
        return coleccionTrabajosCliente;
    }

    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> coleccionTrabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                coleccionTrabajosVehiculo.add(trabajo);
            }
        }
        return coleccionTrabajosVehiculo;
    }

    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarRevision(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
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

    private Trabajo getRevision(Revision trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre un trabajo nulo.");
        if (buscar(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        return buscar(trabajo);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        getRevision(revision).anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Trabajo.copiar(trabajo).anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajo.cerrar(fechaFin);
    }

    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar una revisión nula.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return indice == -1 ? null : coleccionTrabajos.get(indice);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una trabajo nulo.");
        if (!coleccionTrabajos.contains(revision)) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(revision);
    }
}
