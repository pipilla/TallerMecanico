package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Vista {
    public static final String LISTADO_DEFECTO = "%s%n%n";
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            System.out.println();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
    }

    public void terminar() {
        System.out.printf("¡HASTA PRONTO!%n");
    }

    private void ejecutar(Evento evento) {
        switch (evento) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrarRevision();
            case SALIR -> salir();
        }
    }

    private void insertarCliente() {
        Consola.mostrarCabecera(Evento.INSERTAR_CLIENTE.toString());
        try {
            controlador.insertar(Consola.leerCliente());
            System.out.printf("Cliente creado correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera(Evento.INSERTAR_VEHICULO.toString());
        try {
            controlador.insertar(Consola.leerVehiculo());
            System.out.printf("Vehículo creado correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera(Evento.INSERTAR_REVISION.toString());
        try {
            controlador.insertar(Consola.leerRevision());
            System.out.printf("Revisión creada correctamente.%n%n");
        } catch (OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera(Evento.BUSCAR_CLIENTE.toString());
        Cliente cliente = Consola.leerClienteDni();
        if (controlador.buscar(cliente) != null) {
            System.out.printf("Cliente encontrado: %s%n%n", controlador.buscar(cliente));
        } else {
            System.out.printf("El cliente no existe.%n%n");
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera(Evento.BUSCAR_VEHICULO.toString());
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        if (controlador.buscar(vehiculo) != null) {
            System.out.printf("Vehículo encontrado: %s%n%n", controlador.buscar(vehiculo));
        } else {
            System.out.printf("El vehículo no existe.%n%n");
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera(Evento.BUSCAR_REVISION.toString());
        Revision revision = Consola.leerRevision();
        controlador.buscar(revision);
        if (controlador.buscar(revision) != null) {
            System.out.printf("Revision encontrada: %s%n%n", controlador.buscar(revision));
        } else {
            System.out.printf("La revision no existe.%n%n");
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera(Evento.MODIFICAR_CLIENTE.toString());
        boolean modificado;
        try {
            modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
            if (modificado) {
                System.out.printf("El cliente ha sido modificado.%n%n");
            }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirHoras() {
        Consola.mostrarCabecera(Evento.ANADIR_HORAS_REVISION.toString());
        try {
            controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
            System.out.printf("Horas añadidas correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera(Evento.ANADIR_PRECIO_MATERIAL_REVISION.toString());
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
            System.out.printf("Precio añadido correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }

    }

    private void cerrarRevision() {
        Consola.mostrarCabecera(Evento.CERRAR_REVISION.toString());
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
            System.out.printf("La revisión se ha cerrado correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera(Evento.BORRAR_CLIENTE.toString());
        try {
            controlador.borrar(Consola.leerClienteDni());
            System.out.printf("El cliente ha sido borrado correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera(Evento.BORRAR_VEHICULO.toString());
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
            System.out.printf("El vehículo ha sido borrado correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera(Evento.BORRAR_REVISION.toString());
        try {
            controlador.borrar(Consola.leerRevision());
            System.out.printf("La revisión ha sido borrada correctamente.%n%n");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera(Evento.LISTAR_CLIENTES.toString());
        System.out.printf(LISTADO_DEFECTO, controlador.getClientes().toString());
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera(Evento.LISTAR_VEHICULOS.toString());
        System.out.printf(LISTADO_DEFECTO, controlador.getVehiculos().toString());
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera(Evento.LISTAR_REVISIONES.toString());
        System.out.printf(LISTADO_DEFECTO, controlador.getRevisiones().toString());
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera(Evento.LISTAR_REVISIONES_CLIENTE.toString());
        System.out.printf(LISTADO_DEFECTO, controlador.getRevisiones(Consola.leerClienteDni()).toString());
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera(Evento.LISTAR_REVISIONES_VEHICULO.toString());
        System.out.printf(LISTADO_DEFECTO, controlador.getRevisiones(Consola.leerVehiculoMatricula()).toString());
    }

    private void salir() {
        terminar();
    }
}
