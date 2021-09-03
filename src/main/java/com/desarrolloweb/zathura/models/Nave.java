package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    private Double totalTiempoViaje;

    @ManyToOne
    private Planeta planetaActual;

    // @OneToMany(mappedBy = "nave")
    // List<NaveXProducto> naveXProductos;

    @ManyToMany
    @JoinTable(name = "nave_x_producto", joinColumns = @JoinColumn(name = "nave_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;
    
    @OneToMany(mappedBy = "nave")
    private List<Tripulante> tripulantes;

    @ManyToOne
    private ModeloNave modeloNave;

    public Nave() {
    }

    public Nave(Long id, String nombre, Double cargaActual, Double cantidadCredito, Double totalTiempoViaje, Planeta planetaActual, List<Producto> productos, List<Tripulante> tripulantes, ModeloNave modeloNave) {
        this.id = id;
        this.nombre = nombre;
        this.cargaActual = cargaActual;
        this.cantidadCredito = cantidadCredito;
        this.totalTiempoViaje = totalTiempoViaje;
        this.planetaActual = planetaActual;
        this.productos = productos;
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

    public Double getTotalTiempoViaje() {
        return this.totalTiempoViaje;
    }

    public void setTotalTiempoViaje(Double totalTiempoViaje) {
        this.totalTiempoViaje = totalTiempoViaje;
    }

    public Planeta getPlanetaActual() {
        return this.planetaActual;
    }

    public void setPlanetaActual(Planeta planetaActual) {
        this.planetaActual = planetaActual;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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

    public Nave totalTiempoViaje(Double totalTiempoViaje) {
        setTotalTiempoViaje(totalTiempoViaje);
        return this;
    }

    public Nave planetaActual(Planeta planetaActual) {
        setPlanetaActual(planetaActual);
        return this;
    }

    public Nave productos(List<Producto> productos) {
        setProductos(productos);
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
        return Objects.equals(id, nave.id) && Objects.equals(nombre, nave.nombre) && Objects.equals(cargaActual, nave.cargaActual) && Objects.equals(cantidadCredito, nave.cantidadCredito) && Objects.equals(totalTiempoViaje, nave.totalTiempoViaje) && Objects.equals(planetaActual, nave.planetaActual) && Objects.equals(productos, nave.productos) && Objects.equals(tripulantes, nave.tripulantes) && Objects.equals(modeloNave, nave.modeloNave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cargaActual, cantidadCredito, totalTiempoViaje, planetaActual, productos, tripulantes, modeloNave);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cargaActual='" + getCargaActual() + "'" +
            ", cantidadCredito='" + getCantidadCredito() + "'" +
            ", totalTiempoViaje='" + getTotalTiempoViaje() + "'" +
            ", planetaActual='" + getPlanetaActual() + "'" +
            ", productos='" + getProductos() + "'" +
            ", tripulantes='" + getTripulantes() + "'" +
            ", modeloNave='" + getModeloNave() + "'" +
            "}";
    }

}
