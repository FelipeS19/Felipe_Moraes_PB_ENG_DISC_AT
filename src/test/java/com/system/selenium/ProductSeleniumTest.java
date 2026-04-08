package com.system.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://localhost:" + port + "/login");

        wait.until(d -> d.findElement(By.name("username"))).sendKeys("admin");
        wait.until(d -> d.findElement(By.name("password"))).sendKeys("admin");
        wait.until(d -> d.findElement(By.tagName("button"))).click();

        driver.get("http://localhost:" + port + "/products");
    }

    @Test
    void shouldCreateProduct() {

        String nome = "ProdutoTeste" + System.currentTimeMillis();

        wait.until(d -> d.findElement(By.name("name"))).sendKeys(nome);
        wait.until(d -> d.findElement(By.name("price"))).sendKeys("10");
        wait.until(d -> d.findElement(By.name("quantity"))).sendKeys("2");

        wait.until(d -> d.findElement(By.tagName("button"))).click();

        wait.until(d -> d.getPageSource().contains(nome));

        assertTrue(driver.getPageSource().contains(nome));
    }

    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}