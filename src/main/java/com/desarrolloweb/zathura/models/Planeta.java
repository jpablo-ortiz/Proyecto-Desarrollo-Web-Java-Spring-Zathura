package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Planeta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Boolean habitado;

    @ManyToOne
    Estrella estrella;

    @OneToMany(mappedBy = "planeta")
    List<PlanetaXProducto> planetaXProductos;

    @OneToMany(mappedBy = "planetaActual")
    private List<Nave> naves;

    public Planeta() {
    }

    public Planeta(String nombre, Boolean habitado, Estrella estrella, List<PlanetaXProducto> planetaXProductos, List<Nave> naves) {
        this.nombre = nombre;
        this.habitado = habitado;
        this.estrella = estrella;
        this.planetaXProductos = planetaXProductos;
        this.naves = naves;
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

    public List<PlanetaXProducto> getPlanetaXProductos() {
        return this.planetaXProductos;
    }

    public void setPlanetaXProductos(List<PlanetaXProducto> planetaXProductos) {
        this.planetaXProductos = planetaXProductos;
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

    public Planeta planetaXProductos(List<PlanetaXProducto> planetaXProductos) {
        setPlanetaXProductos(planetaXProductos);
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
        return Objects.equals(id, planeta.id) && Objects.equals(nombre, planeta.nombre) && Objects.equals(habitado, planeta.habitado) && Objects.equals(estrella, planeta.estrella) && Objects.equals(planetaXProductos, planeta.planetaXProductos) && Objects.equals(naves, planeta.naves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, habitado, estrella, planetaXProductos, naves);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", habitado='" + isHabitado() + "'" +
            ", estrella='" + getEstrella() + "'" +
            ", planetaXProductos='" + getPlanetaXProductos() + "'" +
            ", naves='" + getNaves() + "'" +
            "}";
    }


}
