package com.system.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
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

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("http://localhost:" + port + "/login");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("admin");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("admin");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbtn"))).click();
            Thread.sleep(3000);
            System.out.println("URL APÓS LOGIN: " + driver.getCurrentUrl());
            wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/products"),
            ExpectedConditions.urlContains("error")
            ));
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

        String nome = "ProdutoTeste" + System.currentTimeMillis();

        try {
            driver.get("http://localhost:" + port + "/products");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(nome);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("price"))).sendKeys("10");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity"))).sendKeys("2");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("cadbtn"))).click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.tagName("body"), nome
            ));

            assertTrue(driver.getPageSource().contains(nome));

        } catch (Exception e) {
            System.out.println(" ERRO AO CRIAR PRODUTO:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}