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
    private boolean aceptado = false;

    public VentanaPrincipal ventanaPrincipal;
    public void setVentanaPrincipal(VentanaPrincipal ventana) {
        ventanaPrincipal = ventana;
    }
    public void limpiar() {
        tfDni.clear();
        tfDni.setStyle(null);
        tfNombre.clear();
        tfNombre.setStyle(null);
        tfTelefono.clear();
        tfTelefono.setStyle(null);
        aceptado = false;
    }
    public Cliente getCliente() {
        return new Cliente(tfNombre.getText(), tfDni.getText(), tfTelefono.getText());
    }
    public boolean isAceptado() {
        return aceptado;
    }
    private void comprobarDni(String nuevoDni) {
        if (nuevoDni.matches(Cliente.ER_DNI)) {
            tfDni.setStyle(ESTILO_CORRECTO);
        } else {
            tfDni.setStyle(ESTILO_INCORRECTO);
        }
    }
    private void comprobarNombre(String nuevoNombre) {
        if (nuevoNombre.matches(Cliente.ER_NOMBRE)) {
            tfNombre.setStyle(ESTILO_CORRECTO);
        } else {
            tfNombre.setStyle(ESTILO_INCORRECTO);
        }
    }
    private void comprobarTelefono(String nuevoTelefono) {
        if (nuevoTelefono.matches(Cliente.ER_TELEFONO)) {
            tfTelefono.setStyle(ESTILO_CORRECTO);
        } else {
            tfTelefono.setStyle(ESTILO_INCORRECTO);
        }
    }
    @FXML
    void aceptar() {
        try {
            System.out.println(getCliente());
            aceptado = true;
            getEscenario().close();
        } catch (Exception e) {
            Dialogos.mostrarDialogoAdvertencia("Error", e.getMessage(), getEscenario());
        }
    }

    @FXML
    void cancelar() {
        aceptado = false;
        if (Dialogos.mostrarDialogoConfirmacion("Insertar Cliente", "¿Estás seguro de que quieres cancelar la inserción?", this.getEscenario())) {
            getEscenario().close();
        }
    }

    @FXML
    void initialize() {
        tfDni.textProperty().addListener(((observable, oldValue, newValue) -> comprobarDni(newValue)));
        tfNombre.textProperty().addListener(((observable, oldValue, newValue) -> comprobarNombre(newValue)));
        tfTelefono.textProperty().addListener(((observable, oldValue, newValue) -> comprobarTelefono(newValue)));
    }

}
