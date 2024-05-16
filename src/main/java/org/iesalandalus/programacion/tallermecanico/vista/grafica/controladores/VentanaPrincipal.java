package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.*;

public class VentanaPrincipal extends Controlador {
    public Cliente cliente;

    public LeerCliente leerCliente;

    @FXML
    void buscarCliente() {

    }

    @FXML
    public void insertarCliente() {
        leerCliente = (LeerCliente) Controladores.get("/vistas/LeerCliente.fxml", "Leer Cliente", getEscenario());
        leerCliente.limpiar();
        leerCliente.getEscenario().showAndWait();
        if (!leerCliente.isAceptado()) {

        } else {
            cliente = leerCliente.getCliente();
        }
    }

    @FXML
    void listarCliente() {

    }
    public Cliente getCliente() {
        return cliente;
    }

}
