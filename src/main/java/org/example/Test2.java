/**
 * Класс Test2
 * Негативный сценарий проверки
 */

package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       
        driver.get("https://ribomaniya.ru/?logout=yes");

        
        driver.get("https://ribomaniya.ru/cabinet/auth/?login=yes&backurl=%2F");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("MrMango");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("password"); // невалидный пароль
        driver.findElement(By.xpath("//button[@name='Login']")).click();
        String s = driver.findElement(By.xpath("//font[@class='errortext']")).getText();
        assert (s.equals("Неверный логин или пароль."));

        
        System.out.println("Тест №2 пройден");    
        
        driver.quit();

    }
}
