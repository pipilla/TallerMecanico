package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "(?:[A-Z][a-záéíóúñ]+[ ]?)*";
    private static final String ER_DNI = "\\d{8}[A-Z]";
    private static final String ER_TELEFONO = "\\d{9}";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente){
        setNombre(cliente.getNombre());
        setDni(cliente.getDni());
        setTelefono(cliente.getTelefono());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El nombre no puede ser nulo.");
        if (!dni.matches(ER_DNI) || !comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("El dni no es válido.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) {
        char letraDni = dni.charAt(dni.length() - 1);
        dni = dni.substring(0, dni.length() - 2);
        int numeroDni = Integer.parseInt(dni);
        int resto = numeroDni % 23;
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letrasDni.charAt(resto) == letraDni;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El nombre no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no es válido.");
        }
        this.telefono = telefono;
    }

    public static Cliente get(String dni) {
        if (this.dni == dni) {

        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }

    @Override
    public String toString() {
        return String.format("Cliente[nombre=%s, dni=%s, teléfono=%s]", this.nombre, this.dni, this.telefono);
    }
}
