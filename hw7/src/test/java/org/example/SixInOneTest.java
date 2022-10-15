package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class SixInOneTest extends AbstractTest {
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    void saveBrowserLogs() {
        LogEntries browserLogs = getWebDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.size() > 0) {
            allLogRows.forEach(logEntry -> {
                logger.debug("BROWSERLOGS: "+logEntry.getMessage());
            });
        }
    }

    @Test
    @DisplayName("Тест-кейс №1: Авторизация на сайте")
    @Description("Тест-кейс №1: Авторизация на сайте")
    @Link("ссылка на tms")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Авторизация на сайте")
    @Story("Вход на сайт по имени пользователя и паролю")
    public void testCase1() throws IOException {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("MrMango")
                .setPassword("kejkenna24")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("Елизавета"));
        saveBrowserLogs();
        String fileName =  "test-case1-" + System.currentTimeMillis() + ".png";
        CommonUtils.makeScreenshot(getWebDriver(),fileName);

        logger.info("Тест-кейс №1 пройден");  
    }


    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (негативный тест)")
    // Аннотации allure
    @Description("Тест-кейс №1: Авторизация на сайте (негативный тест)")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Авторизация на сайте")
    @Story("Ошибка при неверном пользователе или пароле")
    public void testCase2() {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        assertTrue(
                new LoginPage(getWebDriver())
                        .setLogin("MrMango")
                        .setPassword("password")        // неверный пароль
                        .pressInBtt()
                        .isError()
        );
        saveBrowserLogs();
        logger.info("Тест-кейс №2 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №3: Проверка правильности отображения номера телефона на главной странице")
    // Аннотации allure
    @Description("Тест-кейс №3: Проверка правильности отображения номера телефона на главной странице")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Контактные данные")
    @Story("Поиск пользователем телефонного номера в шапке")
    public void testCase3() {
        getWebDriver().get("https://ribomaniya.ru");
        assertTrue(
                new MainPage(getWebDriver()).getPhoneNum().equals("8 (800) 350-32-12")
        );
        logger.info("Тест-кейс №3 пройден");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menudata.csv")
    @DisplayName("Тест-кейс №5: Проверка работы главных полей товарного каталога сайта")
    @Description("Тест-кейс №5: Проверка работы главных полей товарного каталога сайта")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Тестирование интерфейса сайта")
    @Feature("Навигация по сайту с помощью меню разделов")
    @Story("Переход пользователя м/у товарными категориями")
    public void testCase5(String s_xpath, String s_title, String s_header) throws InterruptedException {
        getWebDriver().get("https://ribomaniya.ru");
        MainPage e = new MainPage(getWebDriver());
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); 
        e.pressMenuItem(s_xpath);
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); 
        assertTrue(e.checkContentHeader(s_header));
        saveBrowserLogs();
        logger.info("Тест-кейс №5 пройден ("+s_title+")");
    }

    @Test
    @DisplayName("Тест-кейс №4: Выбор товара и добавление его в корзину, удаление его из корзины как позиции")
    @Description("Тест-кейс №4: Выбор товара и добавление его в корзину, удаление его из корзины как позиции")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Тестирвоание конктреной пользовательской истории")
    @Feature("Корзина")
    @Story("Выбор товара, добавление его в корзину, очистка корзины")
    public void testCase4() throws InterruptedException {
        new CommodPage(getWebDriver())
                .pressMainMenuItem()
                .pressCategoryItem()
                .pressCommodItem()
                .pressCommodVersionItem()
                .pressAddCommodToBacket()
                .pressBacketBtt();
        assertTrue(new BacketPage(getWebDriver()).getCommodText().equals("Predator сер. LJH122 (010)"));
        String s = new BacketPage(getWebDriver())
                .pressDelCommod()
                .getPriceText();
        assertTrue(s.equals("0 р."));

        saveBrowserLogs();
        logger.info("Тест-кейс №4 пройден"); 
    }

    @Test
    @DisplayName("Тест-кейс №6: Проверка работы формы ввода личных данных")
    @Description("Тест-кейс №6: Проверка работы формы ввода личных данных")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Тестирвоание конктреной пользовательской истории")
    @Feature("Окно ввода пользовательских данных в ЛК")
    @Story("Редактирование данных в ЛК")
    public void testCase6()  throws InterruptedException {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("MrMango")
                .setPassword("kejkenna24")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("Елизавета"));
        logger.debug(" - тесткейс № 6 : авторизация успешна");
        new MainPage(getWebDriver()).pressCabinetBtt();
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("Анна")
                .setSecondNameField("Викторовна")
                .setLastNameField("Волкова")
                .clearNewPasswordField()
                .pressApplyBtt();

        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?"); 
        assertTrue(new MainPage(getWebDriver()).checkUser("Анна"));
        logger.debug(" - тесткейс № 6 : данные пользователя сохранены");
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("Елизавета")
                .setSecondNameField("Петровна")
                .setLastNameField("Сухоручко")
                .clearNewPasswordField()
                .pressApplyBtt();
        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?"); 
        assertTrue(new MainPage(getWebDriver()).checkUser("Елизавета"));
        logger.debug(" - тесткейс № 6 : данные тестового пользователя восстановлены");
        saveBrowserLogs();
        logger.info("Тест-кейс №6 пройден");
    }

}
