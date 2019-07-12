package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class BrowserUtilities {
    public static void switchToWindow(String targetTitle){
//c
        String origin= Driver.getDriver().getWindowHandle(); //a
        //a  // b  // c // d
        Set<String> listWindows= Driver.getDriver().getWindowHandles();

        for(String handle:listWindows){

            Driver.getDriver().switchTo().window(handle);

            if(Driver.getDriver().getTitle().equals(targetTitle)){

                return;
            }

        }

        Driver.getDriver().switchTo().window(origin);

    }

    public static void hover(WebElement element){

        Actions action=new Actions(Driver.getDriver());

        action.moveToElement(element);

    }

    public static void selectByText(WebElement element, String text){

        Select select=new Select(element);

        select.selectByVisibleText(text);

    }


    public static void selectByIndex(WebElement element, int index){

        Select select=new Select(element);

        select.selectByIndex(index);

    }


    public static WebElement waitForVisibility(WebElement element, int timeUnit){

        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),timeUnit);

        return wait.until(ExpectedConditions.visibilityOf(element));


    }

    public static WebElement waitForClickable(WebElement element , int timeUnit){

        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),timeUnit);

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static String getScreenShot(String name) throws IOException {

        // Will return the date of execution time
        String date=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        //Takes the screenshot. It is coming from the selenium.
        TakesScreenshot screenshot=(TakesScreenshot) Driver.getDriver();

        File source=screenshot.getScreenshotAs(OutputType.FILE);

        // full path of the screen image
        String target=System.getProperty("user.dir")+"/ScreenShots"+name+date+".jpg";

        File destionation=new File(target);

        // save the screenshot to the given path
        FileUtils.copyFile(source,destionation);

        return target;


    }


}
