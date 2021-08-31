package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Nave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Double cargaActual;

    private Double cantidadCredito;

    @ManyToOne
    Planeta planetaActual;

    @OneToMany(mappedBy = "nave")
    List<NaveXProducto> naveXProductos;
    
    @OneToMany(mappedBy = "nave")
    List<Tripulante> tripulantes;

    @ManyToOne
    ModeloNave modeloNave;

    public Nave() {
    }

    public Nave(String nombre, Double cargaActual, Double cantidadCredito, Planeta planetaActual, List<NaveXProducto> naveXProductos, List<Tripulante> tripulantes, ModeloNave modeloNave) {
        this.nombre = nombre;
        this.cargaActual = cargaActual;
        this.cantidadCredito = cantidadCredito;
        this.planetaActual = planetaActual;
        this.naveXProductos = naveXProductos;
        this.tripulantes = tripulantes;
        this.modeloNave = modeloNave;
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

    public Double getCargaActual() {
        return this.cargaActual;
    }

    public void setCargaActual(Double cargaActual) {
        this.cargaActual = cargaActual;
    }

    public Double getCantidadCredito() {
        return this.cantidadCredito;
    }

    public void setCantidadCredito(Double cantidadCredito) {
        this.cantidadCredito = cantidadCredito;
    }

    public Planeta getPlanetaActual() {
        return this.planetaActual;
    }

    public void setPlanetaActual(Planeta planetaActual) {
        this.planetaActual = planetaActual;
    }

    public List<NaveXProducto> getNaveXProductos() {
        return this.naveXProductos;
    }

    public void setNaveXProductos(List<NaveXProducto> naveXProductos) {
        this.naveXProductos = naveXProductos;
    }

    public List<Tripulante> getTripulantes() {
        return this.tripulantes;
    }

    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public ModeloNave getModeloNave() {
        return this.modeloNave;
    }

    public void setModeloNave(ModeloNave modeloNave) {
        this.modeloNave = modeloNave;
    }

    public Nave id(Long id) {
        setId(id);
        return this;
    }

    public Nave nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Nave cargaActual(Double cargaActual) {
        setCargaActual(cargaActual);
        return this;
    }

    public Nave cantidadCredito(Double cantidadCredito) {
        setCantidadCredito(cantidadCredito);
        return this;
    }

    public Nave planetaActual(Planeta planetaActual) {
        setPlanetaActual(planetaActual);
        return this;
    }

    public Nave naveXProductos(List<NaveXProducto> naveXProductos) {
        setNaveXProductos(naveXProductos);
        return this;
    }

    public Nave tripulantes(List<Tripulante> tripulantes) {
        setTripulantes(tripulantes);
        return this;
    }

    public Nave modeloNave(ModeloNave modeloNave) {
        setModeloNave(modeloNave);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Nave)) {
            return false;
        }
        Nave nave = (Nave) o;
        return Objects.equals(id, nave.id) && Objects.equals(nombre, nave.nombre) && Objects.equals(cargaActual, nave.cargaActual) && Objects.equals(cantidadCredito, nave.cantidadCredito) && Objects.equals(planetaActual, nave.planetaActual) && Objects.equals(naveXProductos, nave.naveXProductos) && Objects.equals(tripulantes, nave.tripulantes) && Objects.equals(modeloNave, nave.modeloNave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cargaActual, cantidadCredito, planetaActual, naveXProductos, tripulantes, modeloNave);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cargaActual='" + getCargaActual() + "'" +
            ", cantidadCredito='" + getCantidadCredito() + "'" +
            ", planetaActual='" + getPlanetaActual() + "'" +
            ", naveXProductos='" + getNaveXProductos() + "'" +
            ", tripulantes='" + getTripulantes() + "'" +
            ", modeloNave='" + getModeloNave() + "'" +
            "}";
    }

}
