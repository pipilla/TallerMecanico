package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.*;

public class VentanaPrincipal extends Controlador {

    private LeerCliente leerCliente;

    @FXML
    void buscarCliente() {

    }

    @FXML
    void insertarCliente() {
        leerCliente = (LeerCliente) Controladores.get("/vistas/LeerCliente.fxml", "Leer Cliente", getEscenario());
        leerCliente.limpiar();
        leerCliente.setVentanaPrincipal(this);
        leerCliente.getEscenario().show();
    }

    @FXML
    void listarCliente() {

    }

}
