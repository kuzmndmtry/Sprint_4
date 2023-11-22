package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusPage {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public StatusPage (WebDriver webDriver){
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver,3);
    }

    private By imgNotFoundLocator = By.xpath("//img[@alt='Not found']");
    public void findImgNotFound() {
        WebElement imgNotFound = webDriver.findElement(imgNotFoundLocator);
    }
}
