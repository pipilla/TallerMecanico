package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<ReceptorEventos, List<Evento>> receptores;
    public GestorEventos(List<Evento> eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        receptores = new HashMap<>();
    }
    void suscribir(ReceptorEventos receptor, List<Evento> eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no puede ser nulo.");
        receptores.put(receptor, eventos);
    }

    public void desuscribir(ReceptorEventos receptor, List<Evento> eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no puede ser nulo.");
        receptores.remove(receptor, eventos);
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");

    }
}
