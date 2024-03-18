package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.ReceptorEventos;

public interface IControlador extends ReceptorEventos {
    void actualizar(Evento evento);
}
