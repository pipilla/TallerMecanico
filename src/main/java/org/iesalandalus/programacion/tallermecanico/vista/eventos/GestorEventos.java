package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);
    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }
    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no puede ser nulo.");
        for (Evento evento : eventos) {

        }
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
