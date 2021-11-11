package com.desarrolloweb.zathura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.desarrolloweb.zathura.controllers.EstrellaController;
import com.desarrolloweb.zathura.controllers.ModeloNaveController;
import com.desarrolloweb.zathura.controllers.NaveController;
import com.desarrolloweb.zathura.controllers.PlanetaController;
import com.desarrolloweb.zathura.controllers.ProductoController;
import com.desarrolloweb.zathura.controllers.RutaController;
import com.desarrolloweb.zathura.controllers.TripulanteController;
import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.NaveXProducto;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.PlanetaXProducto;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.repositories.ModeloNaveRepository;
import com.desarrolloweb.zathura.repositories.NaveRepository;
import com.desarrolloweb.zathura.repositories.NaveXProductoRepository;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;
import com.desarrolloweb.zathura.repositories.PlanetaXProductoRepository;
import com.desarrolloweb.zathura.repositories.ProductoRepository;
import com.desarrolloweb.zathura.repositories.TripulanteRepository;
import com.desarrolloweb.zathura.service.NaveService;
import com.desarrolloweb.zathura.service.TripulanteService;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private EstrellaController estrellaController;

    @Autowired
    private PlanetaController planetaController;

    @Autowired
    private RutaController rutaController;

    @Autowired
    private NaveController naveController;

    @Autowired
    private TripulanteController tripulanteController;

    @Autowired
    private ProductoController productoController;

    @Autowired
    private ModeloNaveController modeloNaveController;

    @Autowired
    private EstrellaRepository estrellaRepository;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PlanetaXProductoRepository planetaXProductoRepository;

    @Autowired
    private ModeloNaveRepository modeloNaveRepository;

    @Autowired
    private TripulanteRepository tripulanteRepository;

    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private NaveXProductoRepository naveXProductoRepository;

    @Autowired
    private NaveService naveService;

    @Autowired
    private TripulanteService tripulanteService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // IniciarDatosAleatoriosBatch();
        // generarEntrega2();
    }

    Random random = new Random();
    RandomStringGenerator randomGen = new RandomStringGenerator.Builder().withinRange('a', 'z')
            .usingRandom(random::nextInt).build();

    /**
     * Inicializa la base de datos con datos aleatorios
     */
    public void IniciarDatosAleatoriosBatch() {
        /**
         * Se realiza llamados a las funciones
         */
        creacionEstrellas();
        creacionProductos();
        creacionModelosNave();
    }

    /**
     * Crea estrellas aleatorias
     */
    public void creacionEstrellas() {
        List<Estrella> estrellas = new ArrayList<Estrella>();
        Estrella estrella = new Estrella();
        int totalEstrellas = 20;
        Double estrellasHabitadas = totalEstrellas * (0.5);
        // Se recorren todas las estrellas
        for (int i = 0; i < totalEstrellas; i++) {
            if (random.nextInt(2) == 1 && estrellasHabitadas > 0) {
                // Guardar Estrella habitable
                estrella = estrellaRepository.save(new Estrella(randomGen.generate(5, 10), random.nextDouble(),
                        random.nextDouble(), random.nextDouble(), true));
                estrellasHabitadas--;
                estrellas.add(estrella);
                // Condición de que en cada estrella esten 3 planetas
                int ran = random.nextInt(3);
                for (int j = 0; j <= ran; j++)
                    // Guardar planeta
                    planetaRepository.save(new Planeta(randomGen.generate(5, 10), true, estrella));
            } else {
                // Guardar Estrella no habitable
                estrella = estrellaRepository.save(new Estrella(
                        randomGen.generate(5, 10),
                        random.nextInt(2000) ,
                        random.nextDouble() * 1000000 + 1, 
                        random.nextDouble() * 1000000 + 1, 
                        random.nextDouble() * 1000000 + 1, 
                        false
                        ));
                estrellas.add(estrella);
            }

        }
        // Crear Rutas de todas las estrellas con todas las estrellas
        for (int i = 0; i < estrellas.size(); i++) {
            for (int j = i + 1; j < estrellas.size(); j++) {
                Ruta ruta = new Ruta(estrellas.get(i), estrellas.get(j));
                rutaController.crearRuta(ruta);
            }
        }
    }

    /**
     * Creación de productos 500 productos aleatorios
     */
    public void creacionProductos() {

        int maxProducto = 50;
        ArrayList<Producto> productos = new ArrayList<Producto>();
        // Se recorre todos los productos
        for (int i = 0; i < maxProducto; i++) {
            // Se guarda producto
            Producto producto = productoRepository.save(new Producto(randomGen.generate(5, 10),
                    random.nextDouble() * 100 + 1, random.nextDouble() * 100 + 1, random.nextDouble() * 100 + 1));
            productos.add(producto);
        }
        // Se recorren todos los planetas ya creados
        for (Planeta planetas : planetaRepository.findAll()) {
            int cantidadProductos = random.nextInt(20);

            List<Producto> temp_prod = (List<Producto>) productos.clone();
            Collections.shuffle(temp_prod);
            for (int i = 0; i < cantidadProductos; i++) {
                // Se guarda relacion muchos a mucho en la tabla intermedia PlanetaxProducto
                double factorOferta = random.nextDouble() * 1000000 + 1;
                double factorDemanda = random.nextDouble() * 1000000 + 1;
                int stock = random.nextInt(100001);
                double precioVenta = (factorDemanda) / (1 + stock);
                double precioCompra = (factorOferta) / (1 + stock);
                planetaXProductoRepository.save(new PlanetaXProducto(precioVenta, precioCompra, factorDemanda,
                        factorOferta, stock, planetas, temp_prod.get(i)));
            }
        }
    }

    /**
     * Crea modelos de naves aleatorios 20 modelos nave
     */
    public void creacionModelosNave() {
        ModeloNave modeloNave;
        
        ArrayList<Long> modelosnaves = new ArrayList<Long>();
        for (int i = 0; i < 20; i++) {
            // se guarda modelo nave
            modeloNave = modeloNaveRepository.save(
                new ModeloNave(
                        randomGen.generate(5, 10), 
                        random.nextDouble() * 2000 + 1,
                        random.nextDouble() * 400 + 1,
                        (double) random.nextInt(200) + 100
                    ));
            modelosnaves.add(modeloNave.getId());
        }

        // Creación naves

        Nave nave = new Nave();
        int ran = 0, ranPlaneta = 0;
        Double costoR = random.nextDouble();
        for (int i = 0; i < 10; i++) {
            String nombreNave = randomGen.generate(5, 10);
            ran = random.nextInt(20);
            ranPlaneta = random.nextInt(40000);
            boolean correcto = true;
            Planeta planeta = new Planeta();
            Producto prod = new Producto();
            while (correcto) {
                planeta = planetaRepository.findById((long) ranPlaneta).orElse(null);
                if (planeta == null) {
                    ranPlaneta = random.nextInt(40000);
                } else if (planeta != null) {
                    correcto = false;
                }
            }
            // Se guarda el modeloNave
            ModeloNave mod = modeloNaveRepository.findById(modelosnaves.get(ran)).get();
            planeta = planetaRepository.findById((long) ranPlaneta).orElse(null);
            nave = naveRepository.save(new Nave(
                            nombreNave, 
                            0.0, 
                            5000.0, 
                            0.0, 
                            planeta, 
                            mod
                        ));
            for (int j = 0; j < random.nextInt(10); j++) {
                int ranPro = random.nextInt(50000);
                correcto = true;
                while (correcto) {
                    prod = productoRepository.findById((long) ranPro).orElse(null);
                    if (prod == null) {
                        ranPro = random.nextInt(50000);
                    } else {
                        correcto = false;
                    }
                }
                //prod = productoRepository.findById((long) ranPro).orElse(null);
                // Se guarda la relacion muchos a mucho en la tabla intermedia NaveXproducto.
                //Double stock = (double) (random.nextInt(10) + 1);
                //double totalCredito = stock * prod.getCostoCredito();
                //double totalVolumen = stock * prod.getVolumen();
                //naveXProductoRepository.save(
                //        new NaveXProducto(
                //                stock,
                //                totalCredito,
                //                totalVolumen,
                //                nave, 
                //                prod
                //            ));
            }

            // Generación de Tripulantes en las naves ya existentes.

            for (int j = 0; j < 3; j++) {
                ran = random.nextInt(3);
                if (ran == 0)
                    tripulanteRepository.save(new Tripulante(randomGen.generate(5, 10), randomGen.generate(5, 10), true,
                            false, false, nave));
                if (ran == 1)
                    tripulanteRepository.save(new Tripulante(randomGen.generate(5, 10), randomGen.generate(5, 10),
                            false, true, false, nave));
                if (ran == 2)
                    tripulanteRepository.save(new Tripulante(randomGen.generate(5, 10), randomGen.generate(5, 10),
                            false, false, true, nave));
            }

        }

    }

    public void generarEntrega2() throws RecordNotFoundException {
        System.out.println("BIENVENIDOOOOO");

        List<Estrella> estrellas = new ArrayList<Estrella>();
        // Crear Estrelllas
        Estrella estrella1 = new Estrella("Estrella 1", Double.valueOf(123), Double.valueOf(432), Double.valueOf(575),
                Boolean.valueOf(true));
        estrella1 = estrellaController.crearEstrella(estrella1);
        estrellas.add(estrella1);

        Estrella estrella2 = new Estrella("Estrella 2", Double.valueOf(742), Double.valueOf(1), Double.valueOf(999),
                Boolean.valueOf(true));
        estrella2 = estrellaController.crearEstrella(estrella2);
        estrellas.add(estrella2);

        Estrella estrella = new Estrella("Estrella 3", Double.valueOf(543), Double.valueOf(876), Double.valueOf(123),
                Boolean.valueOf(true));
        estrella = estrellaController.crearEstrella(estrella);
        estrellas.add(estrella);

        Estrella estrella3 = new Estrella("Estrella 4", Double.valueOf(111), Double.valueOf(325), Double.valueOf(876),
                Boolean.valueOf(true));
        estrella3 = estrellaController.crearEstrella(estrella3);
        estrellas.add(estrella3);

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

        estrella = new Estrella("Estrella 8", Double.valueOf(11), Double.valueOf(5), Double.valueOf(6),
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

        List<Planeta> planetas = new ArrayList<Planeta>();
        // Crear Estrelllas
        Planeta planeta = new Planeta(Long.valueOf(1), "planeta 1", true);
        planeta.setEstrella(estrella3);
        planeta = planetaController.crearPlaneta(planeta);
        planetas.add(planeta);

        Planeta planeta2 = new Planeta(Long.valueOf(2), "Planeta 2", true);
        planeta2.setEstrella(estrella3);
        planeta2 = planetaController.crearPlaneta(planeta2);
        planetas.add(planeta2);

        Planeta planeta3 = new Planeta(Long.valueOf(3), "Planeta 3", true);
        planeta3.setEstrella(estrella3);
        planeta3 = planetaController.crearPlaneta(planeta3);
        planetas.add(planeta3);

        Planeta planeta4 = new Planeta(Long.valueOf(4), "Planeta 4", true);
        planeta4.setEstrella(estrella3);
        planeta4 = planetaController.crearPlaneta(planeta4);
        planetas.add(planeta4);

        Planeta planeta5 = new Planeta(Long.valueOf(3), "Planeta 5", true);
        planeta5.setEstrella(estrella2);
        planeta5 = planetaController.crearPlaneta(planeta5);
        planetas.add(planeta5);

        ModeloNave modeloNave = new ModeloNave(Long.valueOf(1), "Cuchao", Double.valueOf(500), Double.valueOf(500), (double) 50);
        modeloNave = modeloNaveController.crearModeloNave(modeloNave);

        Nave nave = new Nave(Long.valueOf(1), "Nave de Kenneth", Double.valueOf(100), Double.valueOf(1500), (double) 0);
        nave.setPlanetaActual(planeta);
        nave.setModeloNave(modeloNave);
        nave = naveService.crearNave(nave);

        Tripulante tripulante = new Tripulante(Long.valueOf(1), "Kenneth", "Kenneth", Boolean.valueOf(true),
                Boolean.valueOf(false), Boolean.valueOf(false));
        tripulante.setNave(nave);
        tripulante = tripulanteService.crearTripulante(tripulante);

        Tripulante tripulante2 = new Tripulante(Long.valueOf(2), "Juan", "Juan", Boolean.valueOf(false),
                Boolean.valueOf(true), Boolean.valueOf(false));
        tripulante2.setNave(nave);
        tripulante2 = tripulanteService.crearTripulante(tripulante2);

        Tripulante tripulante3 = new Tripulante(Long.valueOf(3), "Pedro", "Pedro", Boolean.valueOf(false),
                Boolean.valueOf(false), Boolean.valueOf(true));
        tripulante3.setNave(nave);
        tripulante3 = tripulanteService.crearTripulante(tripulante3);

        Producto producto1 = new Producto(Long.valueOf(1), "Producto 1", Double.valueOf(500), Double.valueOf(100),
                Double.valueOf(100));
        producto1 = productoController.crearProducto(producto1);

        Producto producto2 = new Producto(Long.valueOf(2), "Producto 2", Double.valueOf(300), Double.valueOf(200),
                Double.valueOf(800));
        producto2 = productoController.crearProducto(producto2);

        Producto producto3 = new Producto(Long.valueOf(3), "Producto 3", Double.valueOf(100), Double.valueOf(600),
                Double.valueOf(50));
        producto3 = productoController.crearProducto(producto3);

        Producto producto4 = new Producto(Long.valueOf(4), "Producto 4", Double.valueOf(900), Double.valueOf(50),
                Double.valueOf(10));
        producto4 = productoController.crearProducto(producto4);

        Producto producto5 = new Producto(Long.valueOf(5), "Producto 5", Double.valueOf(500), Double.valueOf(100),
                Double.valueOf(100));
        producto5 = productoController.crearProducto(producto5);

        NaveXProducto naveXProducto = new NaveXProducto(Long.valueOf(1), Double.valueOf(500), Double.valueOf(500),
                Double.valueOf(1000));
        naveXProducto.setNave(nave);
        naveXProducto.setProducto(producto1);

        // Crear NaveXProducto

        PlanetaXProducto planetaXProducto1 = new PlanetaXProducto(Long.valueOf(1), Double.valueOf(250),
                Double.valueOf(100), Double.valueOf(950), Double.valueOf(350), Integer.valueOf(745000));

        planetaXProducto1.setPlaneta(planeta);
        planetaXProducto1.setProducto(producto1);

        PlanetaXProducto planetaXProducto2 = new PlanetaXProducto(Long.valueOf(2), Double.valueOf(250),
                Double.valueOf(100), Double.valueOf(950), Double.valueOf(350), Integer.valueOf(745000));

        planetaXProducto2.setPlaneta(planeta2);
        planetaXProducto2.setProducto(producto3);

        PlanetaXProducto planetaXProducto3 = new PlanetaXProducto(Long.valueOf(3), Double.valueOf(250),
                Double.valueOf(100), Double.valueOf(950), Double.valueOf(350), Integer.valueOf(745000));
        planetaXProducto3.setPlaneta(planeta5);
        planetaXProducto3.setProducto(producto5);
    }
}
