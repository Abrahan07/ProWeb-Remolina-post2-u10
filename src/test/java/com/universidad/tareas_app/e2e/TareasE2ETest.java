package com.universidad.tareas_app.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TareasE2ETest {

    private static WebDriver driver;
    private static TareasPage tareasPage;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless");
        opts.addArguments("--no-sandbox");
        opts.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(opts);
        driver.get("http://localhost:" + port + "/tareas");
        tareasPage = new TareasPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Order(1)
    void paginaTareas_cargaCorrectamente_tituloContieneTareas() {
        assertThat(tareasPage.obtenerTituloPagina()).contains("Tareas");
    }

    @Test
    @Order(2)
    void btnNuevaTarea_alHacerClick_muestraFormulario() {
        tareasPage.clickNuevaTarea();
        assertThat(tareasPage.formularioVisible()).isTrue();
    }

    @Test
    @Order(3)
    void crearTarea_conTituloValido_apareceEnLista() {
        int tareasAntes = tareasPage.contarTareas();

        tareasPage.clickNuevaTarea();
        tareasPage.ingresarTitulo("Tarea Selenium Test");
        tareasPage.ingresarDescripcion("Descripcion de prueba");
        tareasPage.clickGuardar();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".tarea-item"), tareasAntes));

        assertThat(tareasPage.contarTareas()).isGreaterThan(tareasAntes);
    }
}