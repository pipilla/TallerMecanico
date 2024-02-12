package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

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

    public void insertar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new IllegalArgumentException("El cliente ya está en la lista.");
        }
    }

    public Cliente buscar(Cliente cliente) {

    }

}
