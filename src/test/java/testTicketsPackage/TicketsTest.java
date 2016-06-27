package testTicketsPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketsTest {
//  public static  WebDriver driver = new FirefoxDriver();

    //private static ChromeDriverService service;
    static WebDriver driver;


    @Test
    public void testTicket(){
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTest\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        filters();
        getCurrentPeriodTrains();
        searchingForRequiredTrains();
        preparingDesiredTypesOfCarraige();
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
        driver.findElement(By.xpath("//td[@data-month='6']//a[text()='22']")).click();
        //driver.findElement(By.linkText("7")).click();

        try {
            myDynamicElement.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//td[@data-month='6']//a[text()='1']")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }

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


    private static Map<String, List<String>> searchForTrainsWithDesiredCarriageType() {
        List<String> trainsWithDesiredTypes = preparingDesiredTypesOfCarraige();
        List<String> requiredTrainsVar = searchingForRequiredTrains();

        Map<String, List<String>> trainsCurrentTypes = new HashMap<String, List<String>>();

        for (String desiredType : trainsWithDesiredTypes) { // all trains with desired carriage Types
            List<WebElement> checkingCarraigeTypes = driver.findElements(By.xpath("//div[@title='"
                    + desiredType + "']/../..//td[@class='num']/a"));

            for (WebElement trainWithAcceptableType : checkingCarraigeTypes) {//comparing required trains and "all trains with desired carriage Types"
                if (!requiredTrainsVar.contains(trainWithAcceptableType.getText())) {
                    continue;
                }

                if (trainsCurrentTypes.containsKey(trainWithAcceptableType.getText())) {
                    List<String> values = trainsCurrentTypes.get(trainWithAcceptableType.getText());
                    values.add(desiredType);
                } else {
                    List<String> desiredTypes = new ArrayList<String>();
                    desiredTypes.add(desiredType);
                    trainsCurrentTypes.put(trainWithAcceptableType.getText(), desiredTypes);
                }
            }
        }
        return trainsCurrentTypes;
    }

    private static void checkingPlacesAmount() {

        Map<String, List<String>> desiredCarriageTypeTrainsVar = searchForTrainsWithDesiredCarriageType();

        loop:
        for (Map.Entry<String, List<String>> entry : desiredCarriageTypeTrainsVar.entrySet()) {

            for (String carriageType : entry.getValue()) {

                int placesInt = Integer.parseInt(driver.findElement(By.xpath("//td[@class='num']/a[contains(text(),'"
                        + entry.getKey() + "')]/../..//div[@title='" + carriageType + "' ]/b")).getText());
                if (placesInt >= 2) {
                    System.out.println(entry.getKey() + " ----> " + carriageType + " = " + placesInt);

                    TicketsTest obj = new TicketsTest();
                   // obj.screenshoting();
                    sendNotificationOnFacebook();
                    break loop;
                }
            }
        }

        driver.quit();
    }


    private static void sendNotificationOnFacebook() {
        // WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTest\\chromedriver_win32\\chromedriver.exe");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

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
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Сообщения')]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong2 :(");
        }

        driver.findElement(By.xpath("//span[contains(text(),'Сообщения')]")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value=\"1\"]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong3 :(");
        }

        driver.findElement(By.xpath("//button[@value='1']")).click();

        driver.findElement(By.xpath("//input[@class='inputtext'][@placeholder='Поиск']")).sendKeys("Masha");
        driver.findElement(By.xpath("//input[@class='inputtext'][@placeholder='Поиск']")).sendKeys(Keys.ENTER);

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1rt\"]//textarea")));
        } catch (Exception e) {
            System.out.println("Somthing wrong4 :(");
        }

        driver.findElement(By.xpath("//div[@class=\"_1rt\"]//textarea")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Ответить']")));
        } catch (Exception e) {
            System.out.println("Somthing wrong9 :(");
        }
        driver.findElement(By.xpath("//input[@value='Ответить']")).click();
        driver.quit();
    }


    /*private static void screenshoting() { //Capture entire page screenshot and then store it to destination drive
        /*try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Masha\\Dropbox\\screenshotUKRZALIZNUCYA.jpg"));
            System.out.print("Screenshot is captured and stored in your C:\\Users\\Masha\\Dropbox\\");
        } catch (Exception e) {
            System.out.print("Somthing wrong5 :(");
        }
        try{
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "bmp", new File(args[0]));}
        catch (Exception e) {
            System.out.print("Somthing wrong5 :(");
        }*/


  /*  public void screenshoting(String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(fileName));
    }*/


    /*@AfterTest
    public void afterTest() {
        driver.quit();


    }*/
}

