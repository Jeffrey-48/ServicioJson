package org.hello.resteasyjackson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Servicio para generar el nombre completo de una persona por medio de un metodo POST y archivos Json
 */
@Path("/resteasy-jackson/quarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonResource {

    private final Set<Persona> Persona = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private final Set<Persona> Personaretornada = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public JacksonResource() {
        //Dato para el Json
    }

    @GET
    public Set<Persona> list() {
        return Persona;
    }

    @POST
    public Persona add(Persona quark) {
        Persona.add(quark);
        Persona personaRetorno = new Persona(quark.nombre + " " + quark.apellido);
        Persona.add(personaRetorno);
        return personaRetorno;
    }

    @DELETE
    public Set<Persona> delete(Persona persona) {
        Persona.removeIf(existingQuark -> existingQuark.nombre.contentEquals(persona.nombre));
        return Persona;
    }

    /**
     * Creacion de la clase Persona
     */
    public static class Persona {
        public String nombre;
        public String apellido;
        public String cedula;
        public String nombreCompleto;

        public Persona() {
        }

        /**
         * Constructor de la clase Persona
         * @param nombre nombre de la persona
         * @param apellido apellido de la persona
         * @param cedula cedula de la persona
         */
        public Persona(String nombre, String apellido, String cedula) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.cedula=cedula;
        }

        public Persona( String textoNombre) {
            this.nombreCompleto = textoNombre;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }
    }
}
