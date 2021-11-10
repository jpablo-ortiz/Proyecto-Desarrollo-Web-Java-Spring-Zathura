package com.desarrolloweb.zathura.models.POJOs;

import java.util.List;

import com.desarrolloweb.zathura.models.Nave;

public class NavePojo {

    public ModeloNavePojo modeloNave;
    public List<TripulantePojo> tripulantes;

    public NavePojo(ModeloNavePojo modeloNave, List<TripulantePojo> tripulantes) {
        this.modeloNave = modeloNave;
        this.tripulantes = tripulantes;
    }

    public NavePojo() {
        super();
    }

    public Nave navePojoToNave() {
        Nave nave = new Nave();
        nave.setModeloNave(modeloNave.modeloNavePojoToModeloNave());
        nave.setTripulantes(tripulantes.stream().map(tripulantePojo -> tripulantePojo.tripulantePojoToTripulante())
                .collect(java.util.stream.Collectors.toList()));
        return nave;
    }
    
    public ModeloNavePojo getModeloNave() {
        return this.modeloNave;
    }

    public void setModeloNave(ModeloNavePojo modeloNave) {
        this.modeloNave = modeloNave;
    }

    public List<TripulantePojo> getTripulantes() {
        return this.tripulantes;
    }

    public void setTripulantes(List<TripulantePojo> tripulantes) {
        this.tripulantes = tripulantes;
    }


}
