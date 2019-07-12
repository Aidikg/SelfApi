package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TestingPages {
    WebDriver driver;
    public TestingPages(){
        this.driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="user_login")
    public WebElement username;

    @FindBy(id="user_pass")
    public WebElement password;

    @FindBy(xpath = "//div[.='Posts']")
    public WebElement postButton;

    @FindBy(xpath = "row-title")
    public List<WebElement> allTitles;

    @FindBy(xpath="//a[@class='page-title-action']")
    public  WebElement newPostButton;

    @FindBy(xpath = "//textarea[@id='post-title-0']")
    public WebElement editTitle;

    @FindBy(xpath = "//button[@class='components-button editor-post-publish-panel__toggle is-button is-primary']")
    public WebElement publishButton1;

    @FindBy (xpath = "/html//div[@id='editor']/div[@class='components-drop-zone__provider']/div[@class='components-navigate-regions']/div/div[3]/div/div[@role='region']//div[@class='editor-post-publish-panel__header-publish-button']/button[@type='button']")
    public WebElement PublishButton2;
}
