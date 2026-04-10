package com.system.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToProducts(String url){
        driver.get(url);
    }

    public void createProduct(String name, String price, String quantity){

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("cadbtn")));

        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("price")).clear();
        driver.findElement(By.name("quantity")).clear();

        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("price")).sendKeys(price);
        driver.findElement(By.name("quantity")).sendKeys(quantity);
        driver.findElement(By.id("cadbtn")).click();
    }

    public boolean containsProduct(String name){
        return driver.getPageSource().contains(name);
    }
}