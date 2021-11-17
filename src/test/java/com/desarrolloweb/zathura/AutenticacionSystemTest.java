package com.desarrolloweb.zathura;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("system-test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AutenticacionSystemTest {

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
    private WebDriverWait waitCorto;
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

        System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
        this.browser = new ChromeDriver(options);
        this.wait = new WebDriverWait(browser, 10);
        this.waitCorto = new WebDriverWait(browser, 1);

        // if (!inicializado) {
        List<Estrella> estrellas = new ArrayList<Estrella>();
        Estrella estrella1 = new Estrella("estrella1", Double.valueOf(2), Double.valueOf(4), Double.valueOf(5), true);

        estrellaRepository.save(estrella1); // id1

        estrellas.add(estrella1);

        Planeta planeta1 = new Planeta("planeta1", true, estrella1);
        planetaRepository.save(planeta1); // id2
        // Crear modelo nave
        ModeloNave modeloNave1 = new ModeloNave("modeloNave1", Double.valueOf(100), Double.valueOf(100),
                Double.valueOf(100));
        modeloNaveRepository.save(modeloNave1); // id3
        // crear Nave
        Nave nave1 = new Nave("nave1", Double.valueOf(100), Double.valueOf(100), Double.valueOf(100), planeta1,
                modeloNave1);
        naveRepository.save(nave1); // id4
        // Crear Tripulantes
        Tripulante tripulante = new Tripulante("usuario1", "1234", true, false, false, nave1);
        tripulante = tripulanteRepository.save(tripulante); // id5

        Tripulante tripulante2 = new Tripulante("usuario2", "1234", false, true, false, nave1);
        tripulante2 = tripulanteRepository.save(tripulante2); // id6

        Tripulante tripulante3 = new Tripulante("usuario3", "1234", false, false, true, nave1);
        tripulante3 = tripulanteRepository.save(tripulante3); // id7

        // inicializado = true;
        // }
    }

    @Test
    void loginCapitan() {
        String username = "usuario1";
        String password = "1234";

        this.browser.get(baseURL + "/login");
        this.browser.findElementById("username").sendKeys(username);
        this.browser.findElementById("password").sendKeys(password);
        this.browser.findElementById("login").click();
        // this.browser.get(baseURL + "/");
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputNombre")));
        String user = element.getAttribute("value");
        try {
            if (user.equals(username)) {
                this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas")));
                this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navComerciar")));
            } else {
                fail("Login incorrecto");
            }
        } catch (Exception e) {
            fail("Error: El capitán no tiene habilitada todas las funciones");
        }
    }

    @Test
    void loginNavegante() {
        String username = "usuario2";
        String password = "1234";

        this.browser.get(baseURL + "/login");
        this.browser.findElementById("username").sendKeys(username);
        this.browser.findElementById("password").sendKeys(password);
        this.browser.findElementById("login").click();
        // this.browser.get(baseURL + "/");
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputNombre")));
        String user = element.getAttribute("value");
        try {
            if (user.equals(username)) {
                try {
                    this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas")));
                    try {
                        this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navComerciar")));
                        fail("Error: Comercialización habilitada para el navegante");
                    } catch (Exception e) {
                        // Este camino es el correcto
                    }
                } catch (Exception e) {
                    fail("Error: Navegación no habilitada para el navegante");
                }
            } else {
                fail("Login incorrecto");
            }
        } catch (Exception e) {
            fail("No se encontro el texto: " + username);
        }
    }

    @Test
    void loginComerciante() {
        String username = "usuario3";
        String password = "1234";

        this.browser.get(baseURL + "/login");
        this.browser.findElementById("username").sendKeys(username);
        this.browser.findElementById("password").sendKeys(password);
        this.browser.findElementById("login").click();
        // this.browser.get(baseURL + "/");
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputNombre")));
        String user = element.getAttribute("value");
        try {
            if (user.equals(username)) {
                try {
                    this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navComerciar")));
                    try {
                        this.waitCorto.until(ExpectedConditions.visibilityOfElementLocated(By.id("navEstrellas")));
                        fail("Error: Navegación habilitada para el comerciante");
                    } catch (Exception e) {
                        // Este camino es el correcto
                    }
                } catch (Exception e) {
                    fail("Error: Comercialización no habilitada para el comerciante");
                }
            } else {
                fail("Login incorrecto");
            }
        } catch (Exception e) {
            fail("No se encontro el texto: " + username);
        }
    }
}
