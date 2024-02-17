package org.iesalandalus.programacion.tallermecanico.vista;

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
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        salir();
    }

    private void ejecutar(Opcion opcion) {

    }

    private void insertarCliente() {
        Consola.mostrarCabecera(Opcion.INSERTAR_CLIENTE.toString());
        try {
            controlador.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera(Opcion.INSERTAR_VEHICULO.toString());
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera(Opcion.INSERTAR_REVISION.toString());
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera(Opcion.BUSCAR_CLIENTE.toString());
        try {
            Cliente cliente = Consola.leerClienteDni();
            controlador.buscar(cliente);
            System.out.printf("EXISTE: %s", cliente);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera(Opcion.BUSCAR_VEHICULO.toString());
        try {
            Vehiculo vehiculo = Consola.leerVehiculoMatricula();
            controlador.buscar(vehiculo);
            System.out.printf("EXISTE: %s", vehiculo);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera(Opcion.BUSCAR_REVISION.toString());
        try {
            Revision revision = Consola.leerRevision();
            controlador.buscar(revision);
            System.out.printf("EXISTE: %s", revision);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera(Opcion.MODIFICAR_CLIENTE.toString());
        boolean modificado = false;
        try {
            modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
            if (modificado) {
                System.out.printf("El cliente ha sido modificado.%n");
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
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera(Opcion.ANADIR_PRECIO_MATERIAL_REVISION.toString());
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera(Opcion.CERRAR_REVISION.toString());
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera(Opcion.BORRAR_CLIENTE.toString());
        try {
            controlador.borrar(Consola.leerCliente());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera(Opcion.BORRAR_VEHICULO.toString());
        try {
            controlador.borrar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera(Opcion.BORRAR_REVISION.toString());
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera(Opcion.LISTAR_CLIENTES.toString());
        System.out.printf("%s", controlador.getClientes().toString());
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera(Opcion.LISTAR_VEHICULOS.toString());
        System.out.printf("%s", controlador.getVehiculos().toString());
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES.toString());
        System.out.printf("%s", controlador.getRevisiones().toString());
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES_CLIENTE.toString());
        System.out.printf("%s", controlador.getRevisiones(Consola.leerCliente()).toString());
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera(Opcion.LISTAR_REVISIONES_VEHICULO.toString());
        System.out.printf("%s", controlador.getRevisiones(Consola.leerVehiculo()).toString());
    }

    private void salir() {
        Consola.mostrarCabecera(Opcion.SALIR.toString());
        System.out.printf("¡HASTA PRONTO!%n");
    }
}
