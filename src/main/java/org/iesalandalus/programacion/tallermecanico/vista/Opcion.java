package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3,"Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar cliente"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar vehículos"),
    INSERTAR_REVISION(10, "Insertar revisión"),
    BUSCAR_REVISION(11, "Buscar revisión"),
    BORRAR_REVISION(12, "Borrar revision"),
    LISTAR_REVISIONES(13, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisiones del cliente"),
    LISTAR_REVISIONES_VEHICULO(15, "Listar revisiones del vehiculo"),
    ANADIR_HORAS_REVISION(16, "Añadir horas revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio de material de revision."),
    CERRAR_REVISION(18, "Cerrar revision"),
    SALIR(19, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    static Map<Integer, Opcion> opciones;

    static {
        opciones = new HashMap<>();
        for (Opcion opcion : Opcion.values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (opciones.get(numeroOpcion) == null) {
            throw new IllegalArgumentException("Opción incorrecta.");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s. %s%n", this.numeroOpcion, this.mensaje);
    }
}
