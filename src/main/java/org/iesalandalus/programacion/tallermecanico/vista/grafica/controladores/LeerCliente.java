package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.*;

public class LeerCliente extends Controlador {
    private static final String ESTILO_CORRECTO = "-fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 3; -fx-border-color: #97db73; ";
    private static final String ESTILO_INCORRECTO = "-fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 3; -fx-border-color: #ad353b; ";

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    public VentanaPrincipal ventanaPrincipal;


}
