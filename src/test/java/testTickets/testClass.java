package testTickets;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.lang.reflect.Array;


@Test
public class testClass {
    private WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("http://booking.uz.gov.ua/");
        driver.findElement(By.xpath("//ul[@id=\"langs\"]//li[1]//b")).click();
        driver.findElement(By.name("station_from")).sendKeys("Київ");
        driver.findElement(By.name("station_from")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_from")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("station_till")).sendKeys("Тернопіль");
        driver.findElement(By.name("station_till")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_till")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("date_dep")).click();
        driver.findElement(By.linkText("10")).click();
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.name("search")).click();
        trainSearch();


    }

    public static void trainSearch() {
        WebDriver driver = new FirefoxDriver();

        String trains[] = new String [6];
        trains[0]= "049 К";
        trains[1]= "081 К";
        trains[2]= "143 К";


        for(int i=0; i<trains.length; i++){
            System.out.println(trains[i]);
        }


        Boolean plackart049 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart049) {
            String places_sring = driver.findElement(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).getText();
            int placesAmount = Integer.parseInt(places_sring);
            if (placesAmount > 1) {
                facebook();
            } else { System.out.println("nice");
            }
        }


        Boolean plackart081 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart081) {
            facebook();
        } else {
        }

        Boolean plackart143 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart143) {
            facebook();
        } else {
        }

        Boolean kype049 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype049) {
            facebook();
        } else {
        }

        Boolean kype081 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype081) {
            facebook();
        } else {
        }

        Boolean kype143 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype143) {
            facebook();
        } else {
        }
    }



    public static void facebook() {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("https://www.facebook.com/");
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("u_0_j")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.id("email")).sendKeys("chizdrel@ya.ru");
        driver.findElement(By.id("pass")).sendKeys("Greenice123@");
        driver.findElement(By.id("u_0_m")).click();
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"linkWrap noCount\"]//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.xpath("//a[@class=\"_5afe sortableItem\"]//div[@class=\"linkWrap noCount\"]//span")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value=\"1\"]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }

        driver.findElement(By.xpath("//button[@value='1']")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1rt\"]//textarea//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }

        driver.findElement(By.xpath("//div[@class=\"_1rt\"]//textarea")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
        driver.findElement(By.id("u_jsonp_2_6")).click();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}



