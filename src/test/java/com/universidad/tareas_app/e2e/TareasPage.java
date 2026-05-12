package com.universidad.tareas_app.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class TareasPage {

    private final WebDriver driver;

    private static final By BTN_NUEVA = By.id("btn-nueva");
    private static final By LIST_ITEMS = By.cssSelector(".tarea-item");
    private static final By INPUT_TITULO = By.id("input-titulo");
    private static final By INPUT_DESCRIPCION = By.id("input-descripcion");
    private static final By BTN_GUARDAR = By.id("btn-guardar");
    private static final By FORM_NUEVA = By.id("form-nueva");

    public TareasPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obtenerTituloPagina() {
        return driver.getTitle();
    }

    public int contarTareas() {
        List elementos = driver.findElements(LIST_ITEMS);
        return elementos.size();
    }

    public void clickNuevaTarea() {
        driver.findElement(BTN_NUEVA).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(FORM_NUEVA));
    }

    public void ingresarTitulo(String titulo) {
        driver.findElement(INPUT_TITULO).clear();
        driver.findElement(INPUT_TITULO).sendKeys(titulo);
    }

    public void ingresarDescripcion(String descripcion) {
        driver.findElement(INPUT_DESCRIPCION).clear();
        driver.findElement(INPUT_DESCRIPCION).sendKeys(descripcion);
    }

    public void clickGuardar() {
        driver.findElement(BTN_GUARDAR).click();
    }

    public boolean formularioVisible() {
        return driver.findElement(FORM_NUEVA).isDisplayed();
    }
}