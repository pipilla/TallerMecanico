package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import java.util.Objects;

public class Controlador implements IControlador {
    private final Vista vista;
    private final Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
    }

    public void comenzar() {
        vista.comenzar();
    }

    public void terminar() {
        vista.terminar();
    }

    @Override
    public void actualizar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        String resultado = "";
        try {
            switch (evento) {
                case INSERTAR_CLIENTE -> {
                    modelo.insertar(vista.leerCliente());
                    resultado = "Se ha insertado el cliente correctamente.";
                }
                case BUSCAR_CLIENTE -> {
                    modelo.buscar(vista.leerCliente());
                    resultado = "Se ha encontrado el cliente.";
                }
                case BORRAR_CLIENTE -> {
                }
                case LISTAR_CLIENTES -> {
                }
                case MODIFICAR_CLIENTE -> {
                }
                case INSERTAR_VEHICULO -> {
                }
                case BUSCAR_VEHICULO -> {
                }
                case BORRAR_VEHICULO -> {
                }
                case LISTAR_VEHICULOS -> {
                }
                case INSERTAR_REVISION -> {
                }
                case BUSCAR_REVISION -> {
                }
                case BORRAR_REVISION -> {
                }
                case LISTAR_REVISIONES -> {
                }
                case LISTAR_REVISIONES_CLIENTE -> {
                }
                case LISTAR_REVISIONES_VEHICULO -> {
                }
                case ANADIR_HORAS_REVISION -> {
                }
                case ANADIR_PRECIO_MATERIAL_REVISION -> {
                }
                case CERRAR_REVISION -> {
                }
                case SALIR -> {
                }
            }
            vista.notificarResultado(evento,resultado,true);
        } catch (Exception e) {
            resultado = e.getMessage();
            vista.notificarResultado(evento,resultado,false);
        }
    }
}