package com.desarrolloweb.zathura;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.zathura.controllers.EstrellaController;
import com.desarrolloweb.zathura.controllers.ModeloNaveController;
import com.desarrolloweb.zathura.controllers.NaveController;
import com.desarrolloweb.zathura.controllers.RutaController;
import com.desarrolloweb.zathura.controllers.TripulanteController;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.models.Tripulante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private ModeloNaveController modeloNaveController;

    @Autowired
    private NaveController naveController;

    @Autowired
    private TripulanteController tripulanteController;

    @Autowired
    private EstrellaController estrellaController;

    @Autowired
    private RutaController rutaController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("BIENVENIDOOOOO");
        ModeloNave modeloNave = new ModeloNave(Long.valueOf(1), "Cuchao", Double.valueOf(500), Double.valueOf(500));
        modeloNave = modeloNaveController.crearModeloNave(modeloNave);

        Nave nave = new Nave(Long.valueOf(1), "Nave de Kenneth", Double.valueOf(100), Double.valueOf(1500),
                Double.valueOf(500));
        // nave.setPlanetaActual(planetaActual);
        nave.setModeloNave(modeloNave);
        nave = naveController.crearNave(nave);

        Tripulante tripulante = new Tripulante(Long.valueOf(1), "Kenneth", "Kenneth", Boolean.valueOf(true),
                Boolean.valueOf(false), Boolean.valueOf(false));
        tripulante.setNave(nave);
        tripulante = tripulanteController.crearTripulante(tripulante);

        Tripulante tripulante2 = new Tripulante(Long.valueOf(2), "Juan", "Juan", Boolean.valueOf(false),
                Boolean.valueOf(true), Boolean.valueOf(false));
        tripulante2.setNave(nave);
        tripulante2 = tripulanteController.crearTripulante(tripulante2);

        Tripulante tripulante3 = new Tripulante(Long.valueOf(3), "Pedro", "Pedro", Boolean.valueOf(false),
                Boolean.valueOf(false), Boolean.valueOf(true));
        tripulante3.setNave(nave);
        tripulante3 = tripulanteController.crearTripulante(tripulante3);

        crearEstrellas();

    }

    public void crearEstrellas() {

        List<Estrella> estrellas = new ArrayList<Estrella>();
        // Crear Estrelllas
        Estrella estrella = new Estrella("Estrella 1", Double.valueOf(123), Double.valueOf(432), Double.valueOf(575),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 2", Double.valueOf(742), Double.valueOf(1), Double.valueOf(999),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 3", Double.valueOf(543), Double.valueOf(876), Double.valueOf(123),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 4", Double.valueOf(111), Double.valueOf(325), Double.valueOf(876),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 5", Double.valueOf(345), Double.valueOf(897), Double.valueOf(56),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 6", Double.valueOf(876), Double.valueOf(543), Double.valueOf(123),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 7", Double.valueOf(543), Double.valueOf(876), Double.valueOf(123),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 8", Double.valueOf(111), Double.valueOf(325), Double.valueOf(876),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 9", Double.valueOf(345), Double.valueOf(897), Double.valueOf(3),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 10", Double.valueOf(5), Double.valueOf(4562), Double.valueOf(123),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 11", Double.valueOf(23), Double.valueOf(867), Double.valueOf(123),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 12", Double.valueOf(6), Double.valueOf(7), Double.valueOf(658),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 13", Double.valueOf(345), Double.valueOf(897), Double.valueOf(56),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 14", Double.valueOf(86), Double.valueOf(6), Double.valueOf(123),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 15", Double.valueOf(543), Double.valueOf(431), Double.valueOf(123),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        estrella = new Estrella("Estrella 16", Double.valueOf(274), Double.valueOf(325), Double.valueOf(2),
                Boolean.valueOf(false));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        // Crear Rutas de todas las estrellas con todas las estrellas
        for (int i = 0; i < estrellas.size(); i++) {
            for (int j = i + 1; j < estrellas.size(); j++) {
                Ruta ruta = new Ruta(estrellas.get(i), estrellas.get(j));
                rutaController.crearRuta(ruta);
            }
        }

        //for (Estrella estrella1 : estrellas) {
        //    for (Estrella estrella2 : estrellas) {
        //        if (estrella1.getId() != estrella2.getId()) {
        //            System.out.println("Creando ruta de " + estrella1.getNombre() + " a " + estrella2.getNombre());
        //            Ruta ruta = new Ruta(estrella1, estrella2);
        //            rutaController.crearRuta(ruta);
        //        }
        //    }
        //}
    }

}
