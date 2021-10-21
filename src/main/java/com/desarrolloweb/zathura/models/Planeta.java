package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Planeta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Boolean habitado;

    @ManyToOne
    @JsonManagedReference
    private Estrella estrella;

    // @OneToMany(mappedBy = "planeta")
    // List<PlanetaXProducto> planetaXProductos;

    @ManyToMany
    @JoinTable(name = "planetaxproducto", joinColumns = @JoinColumn(name = "planeta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    @JsonBackReference
    private List<Producto> productos = new ArrayList<>();

    // @OneToMany(mappedBy = "planetaActual")
    // private List<Nave> naves;

    @OneToMany(mappedBy = "planetaActual")
    @JsonBackReference
    private List<Nave> naves = new ArrayList<>();

    public Planeta() {
    }

    public Planeta(Long id, String nombre, Boolean habitado, Estrella estrella, List<Producto> productos, List<Nave> naves) {
        this.id = id;
        this.nombre = nombre;
        this.habitado = habitado;
        this.estrella = estrella;
        this.productos = productos;
        this.naves = naves;
    }
    public Planeta(Long id, String nombre, Boolean habitado) {
        this.id = id;
        this.nombre = nombre;
        this.habitado = habitado;  
    }
    public Planeta( String nombre, Boolean habitado,Estrella estrella) {
 
        this.nombre = nombre;
        this.habitado = habitado; 
        this.estrella = estrella; 
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean isHabitado() {
        return this.habitado;
    }

    public Boolean getHabitado() {
        return this.habitado;
    }

    public void setHabitado(Boolean habitado) {
        this.habitado = habitado;
    }

    public Estrella getEstrella() {
        return this.estrella;
    }

    public void setEstrella(Estrella estrella) {
        this.estrella = estrella;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Nave> getNaves() {
        return this.naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public Planeta id(Long id) {
        setId(id);
        return this;
    }

    public Planeta nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Planeta habitado(Boolean habitado) {
        setHabitado(habitado);
        return this;
    }

    public Planeta estrella(Estrella estrella) {
        setEstrella(estrella);
        return this;
    }

    public Planeta productos(List<Producto> productos) {
        setProductos(productos);
        return this;
    }

    public Planeta naves(List<Nave> naves) {
        setNaves(naves);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Planeta)) {
            return false;
        }
        Planeta planeta = (Planeta) o;
        return Objects.equals(id, planeta.id) && Objects.equals(nombre, planeta.nombre) && Objects.equals(habitado, planeta.habitado) && Objects.equals(estrella, planeta.estrella) && Objects.equals(productos, planeta.productos) && Objects.equals(naves, planeta.naves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, habitado, estrella, productos, naves);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", habitado='" + isHabitado() + "'" +
            ", estrella='" + getEstrella() + "'" +
            ", productos='" + getProductos() + "'" +
            ", naves='" + getNaves() + "'" +
            "}";
    }

}
