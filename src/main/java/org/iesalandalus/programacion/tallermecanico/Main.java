package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public class Main {
    public static void main(String[] args) {
        VistaTexto vistaTexto = new VistaTexto();
        ModeloCascada modeloCascada = new ModeloCascada();
        Controlador controlador = new Controlador(modeloCascada, vistaTexto);
        controlador.comenzar();
    }
}
