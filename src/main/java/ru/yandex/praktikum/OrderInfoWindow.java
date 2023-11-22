package ru.yandex.praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderInfoWindow {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public OrderInfoWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 3);

    }




    private By orderInfoLocator = By.xpath("//*[@class='Order_Text__2broi']");

    public void waitOrderInfoHeaderLocator() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Заказ оформлен']")));
    }


    public String getTextOrderInfo() {
        WebElement orderInfo = webDriver.findElement(orderInfoLocator);
        return  orderInfo.getText();
    }

}
