package testTickets;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;
import static org.junit.Assert.assertEquals;


@Test
public class testClass {

    public static WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) {

        filters();
        getCurrentPeriodTrains();
        requiredTrains();
        desiredTypesOfCarraige();
        comparingRequiredTrainsANDDesiredCarriageTypeTrains();

    }

    public static void filters() {  // Searching for trains with defined filters

        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("http://booking.uz.gov.ua/");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"langs\"]//li[1]//b")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.xpath("//ul[@id=\"langs\"]//li[1]//b")).click();
        driver.findElement(By.name("station_from")).sendKeys("Київ");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Київ']")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.name("station_from")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_from")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("station_till")).sendKeys("Тернопіль");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Тернопіль']")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.name("station_till")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_till")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("date_dep")).click();
        driver.findElement(By.linkText("14")).click();
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.name("search")).click();
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
    }


    private static List<String> getCurrentPeriodTrains() { // Getting WebElements and writing them to ArrayList
        List<String> currentTrains = new ArrayList<String>();
        List<WebElement> myElements = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a"));

        for (int i = 0; i < myElements.size(); i++) {
            currentTrains.add(myElements.get(i).getText());
            // System.out.println(myElements.get(i).getText());
        }
        return currentTrains;
    }


    public static List<String> requiredTrains() { // List of possible trains
        List<String> arrayOfWebElements = getCurrentPeriodTrains();
        //ArrayList creation
        List<String> requeredTrains = new ArrayList<String>();
        requeredTrains.add("049 К"); //Putting an Item In arraylist at Index = 0.
        requeredTrains.add("081 К"); //Putting an Item In arraylist at Index = 1.
        requeredTrains.add("143 К");

        List<String> listOfPossibleTrains = new ArrayList<String>();
        for (int i = 0; i < requeredTrains.size(); i++) {

            for (int j = 0; j < arrayOfWebElements.size(); j++) {

                if (requeredTrains.get(i).equals(arrayOfWebElements.get(j))) {
                    //System.out.println(requeredTrains.get(i));
                    listOfPossibleTrains.add(requeredTrains.get(i));
                }
            }
        }
        return listOfPossibleTrains;

    }



    public static List<String> desiredTypesOfCarraige() {
        List<String> trainsDesiredTypes = new ArrayList<String>();
        trainsDesiredTypes.add("Купе");
        trainsDesiredTypes.add("Плацкарт");
        for (int t = 0; t < trainsDesiredTypes.size(); t++) {
            //  System.out.println(trainsDesiredTypes.get(t1));
        }
        return trainsDesiredTypes;
    }


    public static List<String> trainsWithDesiredCarriageType() {
        List<String> trainsDesiredTypes = desiredTypesOfCarraige();

        List<String> trainsCurrentTypes = new ArrayList<String>();

        for (int i = 0; i < trainsDesiredTypes.size(); i++) {
            List<WebElement> checkingCarraigeTypes = driver.findElements(By.xpath("//div[@title='" + trainsDesiredTypes.get(i) + "']/../..//td[@class='num']/a"));

            trainsCurrentTypes.add(checkingCarraigeTypes.get(i).getText());
            // System.out.println(trainsCurrentTypes.get(i));
        }
        return trainsCurrentTypes;
    }

    public static List<String> comparingRequiredTrainsANDDesiredCarriageTypeTrains() {
        List<String> requiredTrainsVar = requiredTrains();
        List<String> desiredCarriageTypeTrainsVar = trainsWithDesiredCarriageType();

        List<String> requiredTrainsWithDesiredCarriageType = new ArrayList<String>();

        for (int i = 0; i < requiredTrainsVar.size(); i++) {
            //  System.out.println(requiredTrains.get(i)+"Текст-----requiredTrains");

            for (int j = 0; j < desiredCarriageTypeTrainsVar.size(); j++) {
                //  System.out.println(desiredCarriageTypeTrains.get(j) + "Текст-----CarriageType");

                if (requiredTrainsVar.get(i).equals(desiredCarriageTypeTrainsVar.get(j))) {

                    requiredTrainsWithDesiredCarriageType.add(requiredTrainsVar.get(i));
                    System.out.println(requiredTrainsWithDesiredCarriageType.get(i));
                }
            }
        }
        return requiredTrainsWithDesiredCarriageType;
    }
}


/*
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



*/