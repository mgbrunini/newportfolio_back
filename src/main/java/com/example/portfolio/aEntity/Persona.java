package com.example.portfolio.aEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String nombreUsuario;
    private String contraseña;
    private String acercaDe;
    //atributo estudio
    @OneToMany(mappedBy = "persona")
    private List<Estudio> estudio = new ArrayList<>();
    //Atributo Trabajo
    @OneToMany(mappedBy = "persona")
    private List<Trabajo> trabajo = new ArrayList<>();
    //Atributo Proyecto
    @OneToMany(mappedBy = "persona")
    private List<Proyecto> proyecto = new ArrayList<>();
    //Atributo Conocimiento
    @OneToMany(mappedBy = "persona")
    private List<Conocimiento> conocimiento = new ArrayList<>();
    
    //Constructores

    public Persona() {
    }

    public Persona(String nombre, String apellido, String correo, String nombreUsuario, String contraseña, String acercaDe) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.acercaDe = acercaDe;
    }

    
    
    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAcercaDe() {
        return acercaDe;
    }

    public void setAcercaDe(String acercaDe) {
        this.acercaDe = acercaDe;
    }

    public List<Estudio> getEstudio() {
        return estudio;
    }

    public void setEstudio(List<Estudio> estudio) {
        this.estudio = estudio;
    }

    public List<Trabajo> getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(List<Trabajo> trabajo) {
        this.trabajo = trabajo;
    }

    public List<Proyecto> getProyecto() {
        return proyecto;
    }

    public void setProyecto(List<Proyecto> proyecto) {
        this.proyecto = proyecto;
    }

    public List<Conocimiento> getConocimiento() {
        return conocimiento;
    }

    public void setConocimiento(List<Conocimiento> conocimiento) {
        this.conocimiento = conocimiento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
}
