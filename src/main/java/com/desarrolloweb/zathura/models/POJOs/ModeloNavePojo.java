package com.desarrolloweb.zathura.models.POJOs;

import com.desarrolloweb.zathura.models.ModeloNave;

public class ModeloNavePojo {
    public Long id;
    public String nombreModelo;
    public Double cargaMax;
    public Double velocidadMax;
    public Double tiempoLimite;

    public ModeloNavePojo(Long id, String nombreModelo, Double cargaMax, Double velocidadMax, Double tiempoLimite) {
        this.id = id;
        this.nombreModelo = nombreModelo;
        this.cargaMax = cargaMax;
        this.velocidadMax = velocidadMax;
        this.tiempoLimite = tiempoLimite;
    }

    public ModeloNavePojo() {
        super();
    }

    public ModeloNave modeloNavePojoToModeloNave() {
        ModeloNave modeloNave = new ModeloNave();
        modeloNave.setId(id);
        modeloNave.setNombreModelo(nombreModelo);
        modeloNave.setCargaMax(cargaMax);
        modeloNave.setVelocidadMax(velocidadMax);
        modeloNave.setTiempoLimite(tiempoLimite);
        return modeloNave;
    }
}
