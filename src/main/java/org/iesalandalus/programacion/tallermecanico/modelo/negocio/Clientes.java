package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return coleccionClientes = new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        boolean modificado = false;
        if (coleccionClientes.contains(cliente)) {
            if (nombre != null && !nombre.isBlank()){
                buscar(cliente).setNombre(nombre);
                modificado = true;
            }
            if (telefono != null && !telefono.isBlank()){
                buscar(cliente).setTelefono(telefono);
                modificado = true;
            }
        } else {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        return modificado;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return indice == -1 ? null : coleccionClientes.get(indice);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            coleccionClientes.remove(cliente);
        } else {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
    }

}
