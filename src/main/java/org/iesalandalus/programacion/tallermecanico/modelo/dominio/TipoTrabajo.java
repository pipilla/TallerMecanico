package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public enum TipoTrabajo {
    MECANICO("Mecanico"),
    REVISION("Revisión");

    final String nombre;

    TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    static TipoTrabajo get(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (trabajo instanceof Revision) {
            return REVISION;
        } else {
            return MECANICO;
        }
    }
}
