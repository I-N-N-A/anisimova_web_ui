package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Test4 {


    private static final WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments( "--disable-notifications" );
        options.addArguments( "--disable-popup-blocking" );

        driver = new ChromeDriver( options );
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( 15, TimeUnit.SECONDS );
    }

    public static void main(String[] args) throws InterruptedException {

        driver.get( "https://geekbrains.ru/" );

        driver.findElement( By.xpath( "//a[contains(.,'Курсы')]" ) ).click();
        sleep( 4000 );

        driver.findElement( By.cssSelector( "body > div:nth-child(44) > div > button.c9e3._311d > svg" ) ).click();

        driver.findElement( By.xpath( "//a[contains(.,'Курсы • ')]" ) ).click();

        WebElement notifyDateHasChangedCheckbox = driver.findElement( By.cssSelector( "#filter-0" ) );
        System.out.println( "Before click: " + notifyDateHasChangedCheckbox.isSelected() );
        notifyDateHasChangedCheckbox.click();
        System.out.println( "After click: " + notifyDateHasChangedCheckbox.isSelected() );

        driver.findElement( By.cssSelector( "#courses-tab > div:nth-child(14) > a > div.gb-course-card__description > div.gb-course-card__title-wrapper > span" ) ).click();


        driver.findElement( By.cssSelector( "#registration-form-button" ) ).click();

        /*
        После нажатия появляется подсказка "Заполните это поле" (email)
        Тест показал, что если поле "по email" не заполено пользователь не может записаться на курс
        */

        sleep( 4000 );

        driver.quit();
    }
}
