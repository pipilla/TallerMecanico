package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.LeerCliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());
    private static VistaGrafica instancia;

    private Controlador ventanaPrincipal;

    public static VistaGrafica getInstancia() {
        if (instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    public void setVentanaPrincipal(Controlador ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public void comenzar() {
        LanzadorVentanaPrincipal.comenzar();
    }

    @Override
    public void terminar() {

    }

    @Override
    public Cliente leerCliente() {
        LeerCliente leerCliente = (LeerCliente) Controladores.get("/vistas/LeerCliente.fxml", "Leer Cliente", null);
        return leerCliente.getCliente();
    }

    @Override
    public Cliente leerClienteDni() {
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        return null;
    }

    @Override
    public String leerNuevoTelefono() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculo() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
    }

    @Override
    public Trabajo leerRevision() {
        return null;
    }

    @Override
    public Trabajo leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public int leerHoras() throws OperationNotSupportedException {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() throws OperationNotSupportedException {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() throws OperationNotSupportedException {
        return null;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            Dialogos.mostrarDialogoInformacion(Evento.INSERTAR_CLIENTE.toString(), texto, null);
        } else {
            Dialogos.mostrarDialogoError(Evento.INSERTAR_CLIENTE.toString(), texto, null);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }

    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajos) {
        /* CAMBIAR MÁS TARDE */
        mostrarTrabajos(trabajos);
    }

    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajos) {
        /* CAMBIAR MÁS TARDE */
        mostrarTrabajos(trabajos);
    }
}
