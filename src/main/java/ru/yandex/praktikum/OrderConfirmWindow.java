package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderConfirmWindow {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public OrderConfirmWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 3);

    }

    private By orderConfirmButton = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM'  and text()='Да']");
    public void clickOrderConfirmButton() {
        webDriver.findElement(orderConfirmButton).click();
    }

}
