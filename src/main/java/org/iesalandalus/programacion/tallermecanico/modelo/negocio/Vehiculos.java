package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    private List<Vehiculo> coleccionVehiculos;
    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return coleccionVehiculos = new ArrayList<>(coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)) {
            coleccionVehiculos.add(vehiculo);
        } else {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return indice == -1 ? null : coleccionVehiculos.get(indice);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)) {
            coleccionVehiculos.remove(vehiculo);
        } else {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
    }
}
