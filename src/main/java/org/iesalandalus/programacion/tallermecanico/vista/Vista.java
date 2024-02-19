package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion = null;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            System.out.println();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.printf("¡HASTA PRONTO!%n");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
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
        Consola.mostrarCabecera(Opcion.INSERTAR_CLIENTE.toString());
        try {
            controlador.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Cliente creado correctamente.%n%n");
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera(Opcion.INSERTAR_VEHICULO.toString());
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Vehículo creado correctamente.%n%n");
    }

    private void insertarRevision() {
        Consola.mostrarCabecera(Opcion.INSERTAR_REVISION.toString());
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Revisión creada correctamente.%n%n");
    }

    private void buscarCliente() {
        Consola.mostrarCabecera(Opcion.BUSCAR_CLIENTE.toString());
        Cliente cliente = Consola.leerClienteDni();
        controlador.buscar(cliente);
        System.out.printf("EXISTE: %s%n%n", controlador.buscar(cliente));
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera(Opcion.BUSCAR_VEHICULO.toString());
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        controlador.buscar(vehiculo);
        System.out.printf("EXISTE: %s%n%n", controlador.buscar(vehiculo));
    }

    private void buscarRevision() {
        Consola.mostrarCabecera(Opcion.BUSCAR_REVISION.toString());
        Revision revision = Consola.leerRevision();
        controlador.buscar(revision);
        System.out.printf("EXISTE: %s%n%n", controlador.buscar(revision));
    }

    private void modificarCliente() {
        Consola.mostrarCabecera(Opcion.MODIFICAR_CLIENTE.toString());
        boolean modificado = false;
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
        Consola.mostrarCabecera(Opcion.ANADIR_HORAS_REVISION.toString());
        try {
            controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Horas añadidas correctamente.%n%n");
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera(Opcion.ANADIR_PRECIO_MATERIAL_REVISION.toString());
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Precio añadido correctamente.%n%n");
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera(Opcion.CERRAR_REVISION.toString());
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("La revisión se ha cerrado correctamente.%n%n");
    }

    private void borrarCliente() {
        Consola.mostrarCabecera(Opcion.BORRAR_CLIENTE.toString());
        try {
            controlador.borrar(Consola.leerClienteDni());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("El cliente ha sido borrado correctamente.%n%n");
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera(Opcion.BORRAR_VEHICULO.toString());
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("El vehículo ha sido borrado correctamente.%n%n");
    }

    private void borrarRevision() {
        Consola.mostrarCabecera(Opcion.BORRAR_REVISION.toString());
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("La revisión ha sido borrada correctamente.%n%n");
    }

    private void listarClientes() {
        Consola.mostrarCabecera(Opcion.LISTAR_CLIENTES.toString());
        System.out.printf("%s%n", controlador.getClientes().toString());
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera(Opcion.LISTAR_VEHICULOS.toString());
        System.out.printf("%s%n%n", controlador.getVehiculos().toString());
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES.toString());
        System.out.printf("%s%n%n", controlador.getRevisiones().toString());
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES_CLIENTE.toString());
        System.out.printf("%s%n%n", controlador.getRevisiones(Consola.leerCliente()).toString());
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES_VEHICULO.toString());
        System.out.printf("%s%n%n", controlador.getRevisiones(Consola.leerVehiculo()).toString());
    }

    private void salir() {
        terminar();
    }
}
