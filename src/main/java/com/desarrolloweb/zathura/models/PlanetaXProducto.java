package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

@Entity
public class PlanetaXProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Double PV;

    private Double PC;

    private Double FD;

    private Double FO;

    private Integer S;

    @ManyToOne
    private Planeta planeta;

    @ManyToOne
    private Producto producto;

    public PlanetaXProducto() {
    }

    public PlanetaXProducto(Double PV, Double PC, Double FD, Double FO, Integer S, Planeta planeta, Producto producto) {
        this.PV = PV;
        this.PC = PC;
        this.FD = FD;
        this.FO = FO;
        this.S = S;
        this.planeta = planeta;
        this.producto = producto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPV() {
        return this.PV;
    }

    public void setPV(Double PV) {
        this.PV = PV;
    }

    public Double getPC() {
        return this.PC;
    }

    public void setPC(Double PC) {
        this.PC = PC;
    }

    public Double getFD() {
        return this.FD;
    }

    public void setFD(Double FD) {
        this.FD = FD;
    }

    public Double getFO() {
        return this.FO;
    }

    public void setFO(Double FO) {
        this.FO = FO;
    }

    public Integer getS() {
        return this.S;
    }

    public void setS(Integer S) {
        this.S = S;
    }

    public Planeta getPlaneta() {
        return this.planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public PlanetaXProducto id(Long id) {
        setId(id);
        return this;
    }

    public PlanetaXProducto PV(Double PV) {
        setPV(PV);
        return this;
    }

    public PlanetaXProducto PC(Double PC) {
        setPC(PC);
        return this;
    }

    public PlanetaXProducto FD(Double FD) {
        setFD(FD);
        return this;
    }

    public PlanetaXProducto FO(Double FO) {
        setFO(FO);
        return this;
    }

    public PlanetaXProducto S(Integer S) {
        setS(S);
        return this;
    }

    public PlanetaXProducto planeta(Planeta planeta) {
        setPlaneta(planeta);
        return this;
    }

    public PlanetaXProducto producto(Producto producto) {
        setProducto(producto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlanetaXProducto)) {
            return false;
        }
        PlanetaXProducto planetaXProducto = (PlanetaXProducto) o;
        return Objects.equals(id, planetaXProducto.id) && Objects.equals(PV, planetaXProducto.PV) && Objects.equals(PC, planetaXProducto.PC) && Objects.equals(FD, planetaXProducto.FD) && Objects.equals(FO, planetaXProducto.FO) && Objects.equals(S, planetaXProducto.S) && Objects.equals(planeta, planetaXProducto.planeta) && Objects.equals(producto, planetaXProducto.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, PV, PC, FD, FO, S, planeta, producto);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", PV='" + getPV() + "'" +
            ", PC='" + getPC() + "'" +
            ", FD='" + getFD() + "'" +
            ", FO='" + getFO() + "'" +
            ", S='" + getS() + "'" +
            ", planeta='" + getPlaneta() + "'" +
            ", producto='" + getProducto() + "'" +
            "}";
    }


}
