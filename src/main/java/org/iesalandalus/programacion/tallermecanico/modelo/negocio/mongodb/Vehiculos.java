package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Vehiculos implements IVehiculos {

    private static final String COLECCION = "vehiculos";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";


    private static Vehiculos instancia;

    private MongoCollection<Document> coleccionVehiculos;

    static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        coleccionVehiculos = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    Vehiculo getVehiculo(Document documento) {
        Vehiculo vehiculo = null;
        if (documento != null) {
            vehiculo = new Vehiculo(documento.getString(MARCA), documento.getString(MODELO), documento.getString(MATRICULA));
        }
        return vehiculo;
    }

    Document getDocumento(Vehiculo vehiculo) {
        Document documento = null;
        if (vehiculo != null) {
            String marca = vehiculo.marca();
            String modelo = vehiculo.modelo();
            String matricula = vehiculo.matricula();
            documento = new Document().append(MARCA, marca).append(MODELO, modelo).append(MATRICULA, matricula);
        }
        return documento;
    }

    private Bson getCriterioBusqueda(Vehiculo vehiculo) {
        return eq(MATRICULA, vehiculo.matricula());
    }

    @Override
    public List<Vehiculo> get() {
        return null;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return null;
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }
}
