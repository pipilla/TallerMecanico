package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeloTest {

    @Mock
    private static IClientes clientes;
    @Mock
    private static IVehiculos vehiculos;
    @Mock
    private static ITrabajos trabajos;
    @InjectMocks
    private ModeloCascada modeloCascada = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.MEMORIA);

    private static Cliente cliente;
    private static Vehiculo vehiculo;
    private static Revision revision;
    private static Mecanico mecanico;

    private AutoCloseable procesadorAnotaciones;
    private MockedConstruction<Cliente> controladorCreacionMockCliente;
    private MockedConstruction<Clientes> controladorCreacionMockClientes;
    private MockedConstruction<IVehiculos> controladorCreacionMockVehiculos;
    private MockedConstruction<Revision> controladorCreacionMockRevision;
    private MockedConstruction<Mecanico> controladorCreacionMockMecanico;
    private MockedConstruction<Trabajos> controladorCreacionMockTrabajos;


    @BeforeAll
    public static void setup() {
        cliente = mock();
        when(cliente.getNombre()).thenReturn("Bob Esponja");
        when(cliente.getDni()).thenReturn("11223344B");
        when(cliente.getTelefono()).thenReturn("950112233");
        vehiculo = mock();
        when(vehiculo.marca()).thenReturn("Seat");
        when(vehiculo.modelo()).thenReturn("León");
        when(vehiculo.matricula()).thenReturn("1234BCD");
        revision = mock();
        when(revision.getCliente()).thenReturn(cliente);
        when(revision.getVehiculo()).thenReturn(vehiculo);
        when(revision.getFechaInicio()).thenReturn(LocalDate.now().minusDays(1));
        mecanico = mock();
        when(mecanico.getCliente()).thenReturn(cliente);
        when(mecanico.getVehiculo()).thenReturn(vehiculo);
        when(mecanico.getFechaInicio()).thenReturn(LocalDate.now().minusDays(1));
    }

    @BeforeEach
    void init() {
        controladorCreacionMockCliente = mockConstruction(Cliente.class);
        controladorCreacionMockClientes = mockConstruction(Clientes.class);
        controladorCreacionMockVehiculos = mockConstruction(Vehiculos.class);
        controladorCreacionMockRevision = mockConstruction(Revision.class);
        controladorCreacionMockMecanico = mockConstruction(Mecanico.class);
        controladorCreacionMockTrabajos = mockConstruction(Trabajos.class);
        procesadorAnotaciones = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        procesadorAnotaciones.close();
        controladorCreacionMockCliente.close();
        controladorCreacionMockClientes.close();
        controladorCreacionMockVehiculos.close();
        controladorCreacionMockRevision.close();
        controladorCreacionMockMecanico.close();
        controladorCreacionMockTrabajos.close();
    }

    @Test
    void comenzarNoHaceNada() {
        assertDoesNotThrow(() -> modeloCascada.comenzar());
    }

    @Test
    void terminarNoHaceNada() {
        assertDoesNotThrow(() -> modeloCascada.terminar());
    }

    @Test
    void insertarClienteLlamaClientesInsertar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
        assertDoesNotThrow(() -> verify(clientes).insertar(any(Cliente.class)));
        assertDoesNotThrow(() -> verify(clientes, times(0)).insertar(cliente));
    }

    @Test
    void insertarVehiculoLlamaVehiculosInsertar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(vehiculo));
        assertDoesNotThrow(() -> verify(vehiculos).insertar(vehiculo));
    }

    @Test
    void insertarTrabajoRevisionLlamaClientesBuscarVehiculosBuscarTrabajosInsertar() {
        InOrder orden = inOrder(clientes, vehiculos, trabajos);
        when(clientes.buscar(cliente)).thenReturn(cliente);
        when(vehiculos.buscar(vehiculo)).thenReturn(vehiculo);
        assertDoesNotThrow(() -> modeloCascada.insertar(revision));
        orden.verify(clientes).buscar(cliente);
        orden.verify(vehiculos).buscar(vehiculo);
        assertDoesNotThrow(() -> orden.verify(trabajos).insertar(any(Trabajo.class)));
        assertDoesNotThrow(() -> verify(trabajos, times(0)).insertar(revision));
    }

    @Test
    void insertarTrabajoMecanicoLlamaClientesBuscarVehiculosBuscarTrabajosInsertar() {
        InOrder orden = inOrder(clientes, vehiculos, trabajos);
        when(clientes.buscar(cliente)).thenReturn(cliente);
        when(vehiculos.buscar(vehiculo)).thenReturn(vehiculo);
        assertDoesNotThrow(() -> modeloCascada.insertar(mecanico));
        orden.verify(clientes).buscar(cliente);
        orden.verify(vehiculos).buscar(vehiculo);
        assertDoesNotThrow(() -> orden.verify(trabajos).insertar(any(Trabajo.class)));
        assertDoesNotThrow(() -> verify(trabajos, times(0)).insertar(mecanico));
    }

    @Test
    void buscarClienteLlamaClientesBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
        when(clientes.buscar(cliente)).thenReturn(cliente);
        Cliente clienteEncontrado = modeloCascada.buscar(cliente);
        verify(clientes).buscar(cliente);
        assertNotSame(cliente, clienteEncontrado);
    }

    @Test
    void buscarVehiculoLlamaVehiculosBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(vehiculo));
        when(vehiculos.buscar(vehiculo)).thenReturn(vehiculo);
        modeloCascada.buscar(vehiculo);
        verify(vehiculos).buscar(vehiculo);
    }

    @Test
    void buscarTrabajoLlamaTrabajosBuscar() {
        assertDoesNotThrow(() -> modeloCascada.insertar(revision));
        when(trabajos.buscar(revision)).thenReturn(revision);
        Trabajo trabajoEncontrada = modeloCascada.buscar(revision);
        verify(trabajos).buscar(revision);
        assertNotSame(revision, trabajoEncontrada);
    }

    @Test
    void modificarClienteLlamaClientesModificar() {
        assertDoesNotThrow(() -> modeloCascada.modificar(cliente, "Patricio Estrella", "950123456"));
        assertDoesNotThrow(() -> verify(clientes).modificar(cliente, "Patricio Estrella", "950123456"));
    }

    @Test
    void anadirHorasLlamaTrabajosAnadirHoras() {
        assertDoesNotThrow(() -> modeloCascada.anadirHoras(revision, 10));
        assertDoesNotThrow(() -> verify(trabajos).anadirHoras(revision, 10));
    }

    @Test
    void anadirPrecioMateriaLlamaTrabajosAnadirPrecioMaterial() {
        assertDoesNotThrow(() -> modeloCascada.anadirPrecioMaterial(revision, 100f));
        assertDoesNotThrow(() -> verify(trabajos).anadirPrecioMaterial(revision, 100f));
    }

    @Test
    void cerrarLlamaTrabajosCerrar() {
        assertDoesNotThrow(() -> modeloCascada.cerrar(revision, LocalDate.now()));
        assertDoesNotThrow(() -> verify(trabajos).cerrar(revision, LocalDate.now()));
    }

    @Test
    void borrarClienteLlamaTrabajosGetClienteTrabajosBorrarClientesBorrar() {
        simularClientesConTrabajos();
        InOrder orden = inOrder(clientes, trabajos);
        assertDoesNotThrow(() -> modeloCascada.borrar(cliente));
        orden.verify(trabajos).get(cliente);
        for (Trabajo trabajo : trabajos.get(cliente)) {
            assertDoesNotThrow(() -> orden.verify(trabajos).borrar(trabajo));
        }
        assertDoesNotThrow(() -> orden.verify(clientes).borrar(cliente));
    }

    private void simularClientesConTrabajos() {
        when(trabajos.get(cliente)).thenReturn(new ArrayList<>(List.of(mock(), mock())));
    }

    @Test
    void borrarVehiculoLlamaTrabajosGetVehiculoTrabajosBorrarVehiculosBorrar() {
        simularVehiculosConTrabajos();
        InOrder orden = inOrder(vehiculos, trabajos);
        assertDoesNotThrow(() -> modeloCascada.borrar(vehiculo));
        orden.verify(trabajos).get(vehiculo);
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            assertDoesNotThrow(() -> orden.verify(trabajos).borrar(trabajo));
        }
        assertDoesNotThrow(() -> orden.verify(vehiculos).borrar(vehiculo));
    }

    private void simularVehiculosConTrabajos() {
        when(trabajos.get(vehiculo)).thenReturn(new ArrayList<>(List.of(mock(), mock())));
    }

    @Test
    void borrarTrabajoLlamaTrabajosBorrar() {
        assertDoesNotThrow(() -> modeloCascada.borrar(revision));
        assertDoesNotThrow(() -> verify(trabajos).borrar(revision));
    }

    @Test
    void getClientesLlamaClientesGet() {
        when(clientes.get()).thenReturn(new ArrayList<>(List.of(cliente)));
        List<Cliente> clientesExistentes = modeloCascada.getClientes();
        verify(clientes).get();
        assertNotSame(cliente, clientesExistentes.get(0));
    }

    @Test
    void getVehiculosLlamaVehiculosGet() {
        when(vehiculos.get()).thenReturn(new ArrayList<>(List.of(vehiculo)));
        List<Vehiculo> vehiculosExistentes = modeloCascada.getVehiculos();
        verify(vehiculos).get();
        assertSame(vehiculo, vehiculosExistentes.get(0));
    }

    @Test
    void getTrabajosLlamaTrabajosGet() {
        when(trabajos.get()).thenReturn(new ArrayList<>(List.of(revision)));
        List<Trabajo> trabajosExistentes = modeloCascada.getTrabajos();
        verify(trabajos).get();
        assertNotSame(revision, trabajosExistentes.get(0));
    }

    @Test
    void getTrabajosClienteLlamaTrabajosGetCliente() {
        when(trabajos.get(cliente)).thenReturn(new ArrayList<>(List.of(revision)));
        List<Trabajo> trabajosCliente = modeloCascada.getTrabajos(cliente);
        verify(trabajos).get(cliente);
        assertNotSame(revision,trabajosCliente.get(0));
    }

    @Test
    void getTrabajosVehiculoLlamaTrabajosGetVehiculo() {
        when(trabajos.get(vehiculo)).thenReturn(new ArrayList<>(List.of(revision)));
        List<Trabajo> trabajosVehiculo = modeloCascada.getTrabajos(vehiculo);
        verify(trabajos).get(vehiculo);
        assertNotSame(revision,trabajosVehiculo.get(0));
    }

}