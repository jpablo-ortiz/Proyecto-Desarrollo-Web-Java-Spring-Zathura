package com.desarrolloweb.zathura;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

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
import com.desarrolloweb.zathura.service.RutaService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("system-test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CasosDeUsoSystemTest {

    @Autowired
    private EstrellaRepository estrellaRepository;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private ModeloNaveRepository modeloNaveRepository;

    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private TripulanteRepository tripulanteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private NaveXProductoRepository naveXProductoRepository;

    @Autowired
    private PlanetaXProductoRepository planetaXProductoRepository;

    private ChromeDriver browser;
    private WebDriverWait wait;
    private String baseURL = "http://localhost:4200";

    @AfterEach
    public void end() {
        if (browser != null) {
            browser.quit();
        }
    }

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");

        options.merge(DesiredCapabilities.chrome());

        System.setProperty("webdriver.chrome.driver", new ClassPathResource("src/main/resources/chromedriver.exe").getPath());
        this.browser = new ChromeDriver(options);
        this.wait = new WebDriverWait(browser, 10);

        // if (!inicializado) {
        List<Estrella> estrellas = new ArrayList<Estrella>();
        Estrella estrella1 = new Estrella("estrella1", Double.valueOf(2), Double.valueOf(4), Double.valueOf(5), true);
        Estrella estrella2 = new Estrella("estrella2", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                true);
        Estrella estrella3 = new Estrella("estrella3", Double.valueOf(42), Double.valueOf(49), Double.valueOf(56),
                true);
        Estrella estrella4 = new Estrella("estrella4", Double.valueOf(67), Double.valueOf(42), Double.valueOf(59),
                true);
        Estrella estrella5 = new Estrella("estrella5", Double.valueOf(12), Double.valueOf(34), Double.valueOf(45),
                true);
        Estrella estrella6 = new Estrella("estrella6", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                true);
        Estrella estrella7 = new Estrella("estrella7", Double.valueOf(42), Double.valueOf(49), Double.valueOf(56),
                true);
        Estrella estrella8 = new Estrella("estrella8", Double.valueOf(67), Double.valueOf(42), Double.valueOf(59),
                true);
        Estrella estrella9 = new Estrella("estrella9", Double.valueOf(12), Double.valueOf(34), Double.valueOf(45),
                true);
        Estrella estrella10 = new Estrella("estrella10", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                true);

        estrellaRepository.save(estrella1); // id1
        estrellaRepository.save(estrella2); // id2
        estrellaRepository.save(estrella3); // id3
        estrellaRepository.save(estrella4); // id4
        estrellaRepository.save(estrella5); // id5
        estrellaRepository.save(estrella6); // id6
        estrellaRepository.save(estrella7); // id7
        estrellaRepository.save(estrella8); // id8
        estrellaRepository.save(estrella9); // id9
        estrellaRepository.save(estrella10); // id10

        estrellas.add(estrella1);
        estrellas.add(estrella2);
        estrellas.add(estrella3);
        estrellas.add(estrella4);
        estrellas.add(estrella5);
        estrellas.add(estrella6);
        estrellas.add(estrella7);
        estrellas.add(estrella8);
        estrellas.add(estrella9);
        estrellas.add(estrella10);
        Planeta planeta1 = new Planeta("planeta1", true, estrella1);
        planetaRepository.save(planeta1); // id11
        // Crear modelo nave
        ModeloNave modeloNave1 = new ModeloNave("modeloNave1", Double.valueOf(50000), Double.valueOf(100),
                Double.valueOf(100));
        modeloNaveRepository.save(modeloNave1); // id12
        // crear Nave
        Nave nave1 = new Nave("nave1", Double.valueOf(50000), Double.valueOf(50000), Double.valueOf(100), planeta1,
                modeloNave1);
        naveRepository.save(nave1); // id13
        // Crear Tripulantes
        Tripulante tripulante = new Tripulante("usuario1", "1234", true, false, false, nave1);
        tripulante = tripulanteRepository.save(tripulante); // id14

        Planeta planeta2 = new Planeta("planeta2", true, estrella2);
        planetaRepository.save(planeta2); // id15
        Planeta planeta3 = new Planeta("planeta3", true, estrella1);
        planetaRepository.save(planeta3); // id16

        Producto producto1 = new Producto("pera", Double.valueOf(247), Double.valueOf(100), Double.valueOf(100));
        productoRepository.save(producto1); // id17

        NaveXProducto naveXProducto1 = new NaveXProducto(Double.valueOf(100), Double.valueOf(100), Double.valueOf(100),
                nave1, producto1);
        naveXProductoRepository.save(naveXProducto1); // id18

        PlanetaXProducto planetaXProducto1 = new PlanetaXProducto(Double.valueOf(100), Double.valueOf(100),
                Double.valueOf(100), Double.valueOf(100), Integer.valueOf(100), planeta1, producto1);
        planetaXProductoRepository.save(planetaXProducto1);// id19

        // Crear Rutas de todas las estrellas con todas las estrellas
        for (int i = 0; i < estrellas.size(); i++) {
            for (int j = i + 1; j < estrellas.size(); j++) {
                Ruta ruta = new Ruta(estrellas.get(i), estrellas.get(j));
                rutaService.crearRuta(ruta);
            }
        }
        // inicializado = true;
        // }

        String username = "usuario1";

        this.browser.get(baseURL + "/login");
        this.browser.findElementById("username").sendKeys(username);
        this.browser.findElementById("password").sendKeys("1234");
        this.browser.findElementById("login").click();
        // this.browser.get(baseURL + "/");
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputNombre")));
        String user = element.getAttribute("value");
        try {
            if (user.equals(username)) {
                System.out.println("Login correcto");
            } else {
                fail("Login incorrecto");
            }
        } catch (Exception e) {
            fail("No se encontro el texto: " + username);
        }
    }

    @Test
    void viajarEntreEstrellas() {
        // Variables
        String idEstrellaOrigen = "1";
        String idEstrellaDestino = "2";
        String idPlaneta = "15";
        String nombreEstrellaOrigen = "estrella1";
        String nombreEstrellaDestino = "estrella2";
        String nombrePlaneta = "planeta2";
        WebElement element;

        // El menú
        this.browser.get(baseURL + "/menu");
        // Dar click navegación estrellas
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas"))).click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreEstrella")));
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, "Nombre " + nombreEstrellaOrigen));
            System.out.println("Menu estrellas correcto");

            // Dar click en el boton para viajar a otra estrella
            this.browser.findElementById("btn" + idEstrellaDestino).click();

            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn" + idPlaneta)));

            // Dar click en el boton para viajar a un planeta
            element.click();

            // Dar click en el navEstrellas para volver a la navegación de estrellas
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas"))).click();
            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreEstrella")));

            try {
                // Verificar que la estrella actual sea la estrella a la que se viajó
                wait.until(ExpectedConditions.textToBePresentInElement(element, nombreEstrellaDestino));
            } catch (Exception e) {
                fail("No se encontro el texto: " + nombreEstrellaDestino);
            }
        } catch (Exception e) {
            fail("No se encontro el texto: " + nombreEstrellaOrigen);
        }
    }

    @Test
    void viajarEntrePlanetas() {
        // Variables
        String idEstrella = "1";
        String idPlanetaOrigen = "11";
        String idPlanetaDestino = "16";
        String nombreEstrella = "estrella1";
        String nombrePlanetaOrigen = "planeta1";
        String nombrePlanetaDestino = "planeta3";

        WebElement element;

        // El menú
        this.browser.get(baseURL + "/menu");
        // Dar click navegación estrellas
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas"))).click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreEstrella")));
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, "Nombre " + nombreEstrella));
            System.out.println("Menu estrellas correcto");

            // Dar click en el boton para viajar a otra estrella
            this.browser.findElementById("viajarEstrellaActual").click();

            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombrePlaneta")));
            wait.until(ExpectedConditions.textToBePresentInElement(element, "Nombre " + nombrePlanetaOrigen));
            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn" + idPlanetaDestino)));

            // Dar click en el boton para viajar a un planeta
            element.click();

            // Dar click en el navEstrellas para volver a la navegación de estrellas
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas"))).click();
            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreEstrella")));
            try {
                wait.until(ExpectedConditions.textToBePresentInElement(element, nombreEstrella));
                this.browser.findElementById("viajarEstrellaActual").click();

                // Verificar que el planeta actual sea el planeta a la que se viajó
                element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombrePlaneta")));
                wait.until(ExpectedConditions.textToBePresentInElement(element, "Nombre " + nombrePlanetaDestino));

            } catch (Exception e) {
                fail("No se encontro el texto: " + nombrePlanetaDestino);
            }
        } catch (Exception e) {
            fail("No se encontro el texto: " + nombrePlanetaOrigen);
        }
    }

    @Test
    void realizarCompra() {
        // Variables
        String idProducto = "17";
        String cantidad = "2";
        String mensajeExitoso = "Compra Realizada Con Éxito";
        WebElement element;

        // El menú
        this.browser.get(baseURL + "/menu");

        // Dar click comercializar
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navComerciar"))).click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCompra" + idProducto)));

        // Dar click en el boton para comprar
        element.click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidadCompra")));
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(cantidad);

        this.browser.findElementById("btnComprar").click();

        try {

            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajeExitoso")));
            wait.until(ExpectedConditions.textToBePresentInElement(element, mensajeExitoso));

        } catch (Exception e) {
            fail("No se encontro el texto: " + mensajeExitoso);
        }
    }

    @Test
    void realizarVenta() {
        // Variables
        String idProducto = "17";
        String cantidad = "50";
        String mensajeExitoso = "Venta Realizada Con Éxito";
        WebElement element;

        // El menú
        this.browser.get(baseURL + "/menu");

        // Dar click comercializar
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navComerciar"))).click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnVenta" + idProducto)));

        // Dar click en el boton para comprar
        element.click();
        element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidadVenta")));
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(cantidad);

        this.browser.findElementById("btnVenta").click();

        try {

            element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajeExitoso")));
            wait.until(ExpectedConditions.textToBePresentInElement(element, mensajeExitoso));

        } catch (Exception e) {
            fail("No se encontro el texto: " + mensajeExitoso);
        }
    }

}
