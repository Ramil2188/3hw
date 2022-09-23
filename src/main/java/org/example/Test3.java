

package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        
        driver.get("https://ribomaniya.ru/?logout=yes"); 

        
        driver.findElement(By.xpath("//div[@class='col-12']//a[@href='/kruchki/']")).click();
        driver.findElement(By.xpath("//div[@class='row']//a[@href='/kruchki/dvoynye/']")).click();
        driver.findElement(By.xpath("//a[@href='/kruchki/dvoynye/predator-ser-ljh122/']")).click();
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td//strong[text()='010']/../..")).click();
        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='tocart[add]']")).click();
        driver.get("https://ribomaniya.ru/cabinet/basket/");
        js.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(1000);
        String s = driver.findElement(By.xpath("//a[@href='/kruchki/dvoynye/predator-ser-ljh122-010/']")).getText();
        assert (s.equals("Predator сер. LJH122 (010)"));
        driver.findElement(By.xpath("//span[@class='basket-item-actions-remove']")).click();
        String s1 = driver.findElement(By.xpath("//div[@data-entity='basket-total-price']")).getText();
            
        assert (s1.equals("0 р."));
            

        
        System.out.println("Тест №3 пройден");      

        
        driver.quit();
    }
}
