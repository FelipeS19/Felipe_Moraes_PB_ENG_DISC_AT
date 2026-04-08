package com.system.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.get("http://localhost:8080/login");
        
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");

        driver.findElement(By.tagName("button")).click();

        driver.get("http://localhost:8080/products");
    }

    @Test
    void shouldCreateProduct() {

        String nome = "ProdutoTeste" + System.currentTimeMillis();

        driver.findElement(By.name("name")).sendKeys(nome);
        driver.findElement(By.name("price")).sendKeys("10");
        driver.findElement(By.name("quantity")).sendKeys("2");

        driver.findElement(By.tagName("button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(d -> d.getPageSource().contains(nome));

        assertTrue(driver.getPageSource().contains(nome));
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}