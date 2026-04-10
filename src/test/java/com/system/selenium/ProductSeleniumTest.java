package com.system.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.system.selenium.pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup();
        System.out.println("port: " + port);
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:" + port + "/login");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("admin");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("admin");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbtn"))).click();
            
            System.out.println("URL APÓS LOGIN: " + driver.getCurrentUrl());
            
            wait.until(ExpectedConditions.urlContains("/products"));
            if (driver.getCurrentUrl().contains("error")) {
                throw new RuntimeException("Login falhou!");
}

        } catch (Exception e) {
            System.out.println("ERRO NO LOGIN:");
            System.out.println(driver.getPageSource());
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCreateProduct() {

        ProductPage product = new ProductPage(driver);

        driver.get("http://localhost:" + port + "/products");

        String nome = "Produto" + System.currentTimeMillis();

        product.createProduct(nome, "10", "2");

        assertTrue(product.containsProduct(nome));
    }

    @Test
    void shouldShowErrorWhenEmptyFields() {

        ProductPage product = new ProductPage(driver);

        driver.get("http://localhost:" + port + "/products");

        product.createProduct("", "0", "0");

        System.out.println(driver.getCurrentUrl());

        assertTrue(
            driver.getPageSource().contains("obrigatório") ||
            driver.getPageSource().contains("Erro")
            
        );
    }

    @Test
    void shouldHandleslowresponses() {
        driver.get("http://localhost:" + port + "/products");

        assertDoesNotThrow(()-> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cadbtn")));
        });
    }
    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}