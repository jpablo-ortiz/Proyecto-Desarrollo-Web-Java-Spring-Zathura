/*package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import javax.persistence.Id;

@Entity
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Double costoCredito;

    private Double volumen;

    private Double peso;

    @OneToMany(mappedBy = "producto")
    List<PlanetaXProducto> productoXPlanetas;

    @OneToMany(mappedBy = "producto")
    List<NaveXProducto> productoXNaves;

    public Producto() {
    }

    public Producto(String nombre, Double costoCredito, Double volumen, Double peso, List<PlanetaXProducto> productoXPlanetas, List<NaveXProducto> productoXNaves) {
        this.nombre = nombre;
        this.costoCredito = costoCredito;
        this.volumen = volumen;
        this.peso = peso;
        this.productoXPlanetas = productoXPlanetas;
        this.productoXNaves = productoXNaves;
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

    public Double getCostoCredito() {
        return this.costoCredito;
    }

    public void setCostoCredito(Double costoCredito) {
        this.costoCredito = costoCredito;
    }

    public Double getVolumen() {
        return this.volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public List<PlanetaXProducto> getProductoXPlanetas() {
        return this.productoXPlanetas;
    }

    public void setProductoXPlanetas(List<PlanetaXProducto> productoXPlanetas) {
        this.productoXPlanetas = productoXPlanetas;
    }

    public List<NaveXProducto> getProductoXNaves() {
        return this.productoXNaves;
    }

    public void setProductoXNaves(List<NaveXProducto> productoXNaves) {
        this.productoXNaves = productoXNaves;
    }

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto costoCredito(Double costoCredito) {
        setCostoCredito(costoCredito);
        return this;
    }

    public Producto volumen(Double volumen) {
        setVolumen(volumen);
        return this;
    }

    public Producto peso(Double peso) {
        setPeso(peso);
        return this;
    }

    public Producto productoXPlanetas(List<PlanetaXProducto> productoXPlanetas) {
        setProductoXPlanetas(productoXPlanetas);
        return this;
    }

    public Producto productoXNaves(List<NaveXProducto> productoXNaves) {
        setProductoXNaves(productoXNaves);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(costoCredito, producto.costoCredito) && Objects.equals(volumen, producto.volumen) && Objects.equals(peso, producto.peso) && Objects.equals(productoXPlanetas, producto.productoXPlanetas) && Objects.equals(productoXNaves, producto.productoXNaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, costoCredito, volumen, peso, productoXPlanetas, productoXNaves);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", costoCredito='" + getCostoCredito() + "'" +
            ", volumen='" + getVolumen() + "'" +
            ", peso='" + getPeso() + "'" +
            ", productoXPlanetas='" + getProductoXPlanetas() + "'" +
            ", productoXNaves='" + getProductoXNaves() + "'" +
            "}";
    }


}*/
