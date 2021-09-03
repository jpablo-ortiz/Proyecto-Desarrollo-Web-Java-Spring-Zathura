package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Estrella implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Integer recurso;

    private Double x;

    private Double y;

    private Double z;

    private Boolean habitado;

    @OneToMany(mappedBy = "estrella")
    private List<Planeta> planetas = new ArrayList<>();

    @OneToMany(mappedBy = "estrellaA")
    private List<Ruta> rutasA = new ArrayList<>();

    @OneToMany(mappedBy = "estrellaB")
    private List<Ruta> rutasB = new ArrayList<>();

    public Estrella() {
    }

    public Estrella(String nombre, Integer recurso, Double x, Double y, Double z, Boolean habitado,
            List<Planeta> planetas, List<Ruta> rutasA, List<Ruta> rutasB) {
        this.nombre = nombre;
        this.recurso = recurso;
        this.x = x;
        this.y = y;
        this.z = z;
        this.habitado = habitado;
        this.planetas = planetas;
        this.rutasA = rutasA;
        this.rutasB = rutasB;
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

    public Integer getRecurso() {
        return this.recurso;
    }

    public void setRecurso(Integer recurso) {
        this.recurso = recurso;
    }

    public Double getX() {
        return this.x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return this.y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return this.z;
    }

    public void setZ(Double z) {
        this.z = z;
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

    public List<Planeta> getPlanetas() {
        return this.planetas;
    }

    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    public List<Ruta> getRutasA() {
        return this.rutasA;
    }

    public void setRutasA(List<Ruta> rutasA) {
        this.rutasA = rutasA;
    }

    public List<Ruta> getRutasB() {
        return this.rutasB;
    }

    public void setRutasB(List<Ruta> rutasB) {
        this.rutasB = rutasB;
    }

    public Estrella id(Long id) {
        setId(id);
        return this;
    }

    public Estrella nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Estrella recurso(Integer recurso) {
        setRecurso(recurso);
        return this;
    }

    public Estrella x(Double x) {
        setX(x);
        return this;
    }

    public Estrella y(Double y) {
        setY(y);
        return this;
    }

    public Estrella z(Double z) {
        setZ(z);
        return this;
    }

    public Estrella habitado(Boolean habitado) {
        setHabitado(habitado);
        return this;
    }

    public Estrella planetas(List<Planeta> planetas) {
        setPlanetas(planetas);
        return this;
    }

    public Estrella rutasA(List<Ruta> rutasA) {
        setRutasA(rutasA);
        return this;
    }

    public Estrella rutasB(List<Ruta> rutasB) {
        setRutasB(rutasB);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estrella)) {
            return false;
        }
        Estrella estrella = (Estrella) o;
        return Objects.equals(id, estrella.id) && Objects.equals(nombre, estrella.nombre)
                && Objects.equals(recurso, estrella.recurso) && Objects.equals(x, estrella.x)
                && Objects.equals(y, estrella.y) && Objects.equals(z, estrella.z)
                && Objects.equals(habitado, estrella.habitado) && Objects.equals(planetas, estrella.planetas)
                && Objects.equals(rutasA, estrella.rutasA) && Objects.equals(rutasB, estrella.rutasB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, recurso, x, y, z, habitado, planetas, rutasA, rutasB);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nombre='" + getNombre() + "'" + ", recurso='" + getRecurso() + "'"
                + ", x='" + getX() + "'" + ", y='" + getY() + "'" + ", z='" + getZ() + "'" + ", habitado='"
                + isHabitado() + "'" + ", planetas='" + getPlanetas() + "'" + ", rutasA='" + getRutasA() + "'"
                + ", rutasB='" + getRutasB() + "'" + "}";
    }

}
