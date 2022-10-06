package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class CommodPage extends MainPage {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(xpath = "//div[@class='col-12']//a[@href='/kruchki/']")
    private WebElement mainMenuItem;

    @FindBy(xpath = "//div[@class='row']//a[@href='/kruchki/dvoynyey/']")
    private WebElement cateroryItem;

    @FindBy(xpath = "//a[@href='/kruchki/dvoynye/predator-ser-ljh122/']")
    private WebElement commodItem;

    @FindBy(xpath = "//td//strong[text()='010']/../..")
    private WebElement commodVersionItem;

    @FindBy(xpath = "//input[@name='tocart[add]']")
    private WebElement addCommodToBacket;

    @FindBy(xpath = "//li//a[@href='/cabinet/backet/']//i")
    private WebElement backetBtt;

    public CommodPage(WebDriver driver){
        super(driver);
    }

    public CommodPage pressMainMenuItem(){
        this.mainMenuItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/kruchki/")); 
        return this;
    }

    public CommodPage pressCategoryItem(){
        this.cateroryItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/kruchki/dvoynye/")); 
        return this;
    }

    public CommodPage pressCommodItem(){
        this.commodItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/kruchki/dvoynye/predator-ser-ljh122/")); 
        return this;
    }

    public CommodPage pressCommodVersionItem() throws InterruptedException{
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        this.commodVersionItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("predator-ser-ljh122/predator-ser-ljh122-010/")); 
        return this;
    }

    public CommodPage pressAddCommodToBacket() throws InterruptedException{
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        this.addCommodToBacket.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("predator-ser-ljh122/predator-ser-ljh122-010/")); 
        return this;
    }

    public CommodPage pressBacketBtt(){
        getDriver().get("https://ribomaniya.ru/cabinet/basket/");
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/cabinet/basket/"));
        return this;
    }

}
