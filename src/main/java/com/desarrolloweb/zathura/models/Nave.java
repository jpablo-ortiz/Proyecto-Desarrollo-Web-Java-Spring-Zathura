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

    private Double cargaMax;

    private Double velocidadMax;

    private Double cantidadCredito;

    @ManyToOne
    Planeta planetaActual;

    @OneToMany(mappedBy = "nave")
    List<NaveXProducto> naveXProductos;
    
    @OneToMany(mappedBy = "nave")
    List<Tripulante> tripulantes;

    public Nave() {
    }

    public Nave( String nombre, Double cargaActual, Double cargaMax, Double velocidadMax, Double cantidadCredito, Planeta planetaActual, List<NaveXProducto> naveXProductos, List<Tripulante> tripulantes) {
        this.nombre = nombre;
        this.cargaActual = cargaActual;
        this.cargaMax = cargaMax;
        this.velocidadMax = velocidadMax;
        this.cantidadCredito = cantidadCredito;
        this.planetaActual = planetaActual;
        this.naveXProductos = naveXProductos;
        this.tripulantes = tripulantes;
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

    public Double getCargaMax() {
        return this.cargaMax;
    }

    public void setCargaMax(Double cargaMax) {
        this.cargaMax = cargaMax;
    }

    public Double getVelocidadMax() {
        return this.velocidadMax;
    }

    public void setVelocidadMax(Double velocidadMax) {
        this.velocidadMax = velocidadMax;
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

    public Nave cargaMax(Double cargaMax) {
        setCargaMax(cargaMax);
        return this;
    }

    public Nave velocidadMax(Double velocidadMax) {
        setVelocidadMax(velocidadMax);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Nave)) {
            return false;
        }
        Nave nave = (Nave) o;
        return Objects.equals(id, nave.id) && Objects.equals(nombre, nave.nombre) && Objects.equals(cargaActual, nave.cargaActual) && Objects.equals(cargaMax, nave.cargaMax) && Objects.equals(velocidadMax, nave.velocidadMax) && Objects.equals(cantidadCredito, nave.cantidadCredito) && Objects.equals(planetaActual, nave.planetaActual) && Objects.equals(naveXProductos, nave.naveXProductos) && Objects.equals(tripulantes, nave.tripulantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cargaActual, cargaMax, velocidadMax, cantidadCredito, planetaActual, naveXProductos, tripulantes);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cargaActual='" + getCargaActual() + "'" +
            ", cargaMax='" + getCargaMax() + "'" +
            ", velocidadMax='" + getVelocidadMax() + "'" +
            ", cantidadCredito='" + getCantidadCredito() + "'" +
            ", planetaActual='" + getPlanetaActual() + "'" +
            ", naveXProductos='" + getNaveXProductos() + "'" +
            ", tripulantes='" + getTripulantes() + "'" +
            "}";
    }

}
