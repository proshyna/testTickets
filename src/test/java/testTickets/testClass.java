package testTickets;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;
import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;
import static org.junit.Assert.assertEquals;


public class testClass {


    private static WebDriver driver = new FirefoxDriver();

    @Test
    public static void main(String[] args) {

        filters();
        getCurrentPeriodTrains();
        searchingForRequiredTrains();
        preparingDesiredTypesOfCarraige();
        comparingRequiredTrainsANDDesiredCarriageTypeTrains();
        checkingPlacesAmount();

    }


    private static void filters() {  // Searching for trains with defined filters

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


    private static List<String> searchingForRequiredTrains() { // List of possible trains
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


    private static List<String> preparingDesiredTypesOfCarraige() {
        List<String> trainsDesiredTypes = new ArrayList<String>();
        trainsDesiredTypes.add("Купе");
        trainsDesiredTypes.add("Плацкарт");
        for (int t = 0; t < trainsDesiredTypes.size(); t++) {
            //  System.out.println(trainsDesiredTypes.get(t1));
        }
        return trainsDesiredTypes;
    }


    private static List<String> searchForTrainsWithDesiredCarriageType() {
        List<String> trainsWithDesiredTypes = preparingDesiredTypesOfCarraige();

        List<String> trainsCurrentTypes = new ArrayList<String>();

        for (int i = 0; i < trainsWithDesiredTypes.size(); i++) {
            List<WebElement> checkingCarraigeTypes = driver.findElements(By.xpath("//div[@title='" + trainsWithDesiredTypes.get(i) + "']/../..//td[@class='num']/a"));

            trainsCurrentTypes.add(checkingCarraigeTypes.get(i).getText());
            // System.out.println(trainsCurrentTypes.get(i));
        }
        return trainsCurrentTypes;
    }


    private static List<String> comparingRequiredTrainsANDDesiredCarriageTypeTrains() {
        List<String> requiredTrainsVar = searchingForRequiredTrains();
        List<String> desiredCarriageTypeTrainsVar = searchForTrainsWithDesiredCarriageType();

        List<String> requiredTrainsWithDesiredCarriageType = new ArrayList<String>();

        for (int i = 0; i < requiredTrainsVar.size(); i++) {
            //  System.out.println(requiredTrains.get(i)+"Текст-----requiredTrains");

            for (int j = 0; j < desiredCarriageTypeTrainsVar.size(); j++) {
                //  System.out.println(desiredCarriageTypeTrains.get(j) + "Текст-----CarriageType");

                if (requiredTrainsVar.get(i).equals(desiredCarriageTypeTrainsVar.get(j))) {

                    requiredTrainsWithDesiredCarriageType.add(requiredTrainsVar.get(i));
                    // System.out.println(requiredTrainsWithDesiredCarriageType.get(i));
                }
            }
        }
        return requiredTrainsWithDesiredCarriageType;
    }


    private static void checkingPlacesAmount() {
        List<String> suitableTrains = comparingRequiredTrainsANDDesiredCarriageTypeTrains();

        for (int i = 0; i < suitableTrains.size(); i++) {

            int plackartInt = Integer.parseInt(driver.findElement(By.xpath("//td[@class='num']/a[contains(text(),'" + suitableTrains.get(i) + "')]/../..//div[@title='Плацкарт' ]/b")).getText());
            int kypeInt = Integer.parseInt(driver.findElement(By.xpath("//td[@class='num']/a[contains(text(),'" + suitableTrains.get(i) + "')]/../..//div[@title='Купе' ]/b")).getText());
            if (plackartInt >= 2 || kypeInt >= 2) {
                System.out.println("Plackart = " + plackartInt);
                System.out.println("Kype = " + kypeInt);
                screenshoting();
                sendNotificationOnFacebook();
            } else {
                System.out.println("Amount of places not enough");
            }
        }
        driver.quit();
    }


    private static void sendNotificationOnFacebook() {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("https://www.facebook.com/");
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("u_0_j")));
        } catch (Exception e) {
            System.out.println("Somthing wrong1 :(");
        }
        driver.findElement(By.id("email")).sendKeys("chizdrel@ya.ru");
        driver.findElement(By.id("pass")).sendKeys("Greenice123@");
        driver.findElement(By.id("u_0_m")).click();
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"linkWrap noCount\"]//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong2 :(");
        }
        driver.findElement(By.xpath("//a[@class=\"_5afe sortableItem\"]//div[@class=\"linkWrap noCount\"]//span")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value=\"1\"]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong3 :(");
        }

        driver.findElement(By.xpath("//button[@value='1']")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1rt\"]//textarea")));
        } catch (Exception e) {
            System.out.println("Somthing wrong4 :(");
        }

        driver.findElement(By.xpath("//div[@class=\"_1rt\"]//textarea")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
        driver.findElement(By.id("u_jsonp_2_6")).click();
        driver.quit();
    }


    private static void screenshoting() { //Capture entire page screenshot and then store it to destination drive
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Masha\\Dropbox\\screenshotUKRZALIZNUCYA.jpg"));
            System.out.print("Screenshot is captured and stored in your C:\\Users\\Masha\\Dropbox\\");
        } catch (Exception e) {
            System.out.print("Somthing wrong5 :(");
        }
    }

   /* @AfterTest
    public void afterTest() {
        driver.quit();*/

}



