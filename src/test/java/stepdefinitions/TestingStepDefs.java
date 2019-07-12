package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.TestingPages;
import utilities.Configuration;
import utilities.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestingStepDefs {
    WebDriver driver= Driver.getDriver();
    TestingPages testingPages=new TestingPages();

    private String expectedTitle = "";
    static RequestSpecification request;
    static Response response;

    @Given("^user is authorized to create post$")
    public void user_is_authorized_to_create_post() throws Throwable {
        RestAssured.baseURI = "https://www.aidina.dev.cc/wp-json";
        RestAssured.basePath = "/wp/v2/posts/";
        request = given().relaxedHTTPSValidation().auth().preemptive()
                .basic("admin", "admin").contentType(ContentType.JSON);
    }

    @When("^user creates post with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_creates_post_with_and(String title, String content, String status) throws Throwable {
        Map<String, String> reqMap = new HashMap<>();
        Thread.sleep(3000);
        expectedTitle=title;
        reqMap.put("title", title);
        reqMap.put("content", content);
        reqMap.put("status", status);
        response=request.when().body(reqMap).log().all().post();
//        response.then().log().all().statusCode(201);
    }

    @Then("^user verifies the post with ui$")
    public void user_verifies_the_post_with_ui() throws Throwable {
        driver.get(Configuration.getProperty("wpurl"));
       // expectedTitle=driver.getTitle();
        testingPages.username.sendKeys(Configuration.getProperty("username"));
        testingPages.password.sendKeys(Configuration.getProperty("password")+ Keys.ENTER);
        testingPages.postButton.click();


        for(WebElement s : testingPages.allTitles){
            s.getText();
            Assert.assertTrue(s.getText().contains(expectedTitle));
        }
    }

    @Given("^user is authorized to create post through ui$")
    public void user_is_authorized_to_create_post_through_ui() throws Throwable {
        driver.get(Configuration.getProperty("wpurl"));
        // expectedTitle=driver.getTitle();
        testingPages.username.sendKeys(Configuration.getProperty("username"));
        testingPages.password.sendKeys(Configuration.getProperty("password")+ Keys.ENTER);
        testingPages.postButton.click();

        testingPages.newPostButton.click();
        testingPages.editTitle.sendKeys("cool post with UI");
        testingPages.publishButton1.click();
        testingPages.PublishButton2.click();
    }

    @Then("^user verifies the post with api$")
    public void user_verifies_the_post_with_api() throws Throwable {
        given().relaxedHTTPSValidation().
                get("").
                then().log().all()
                .statusCode(200);
    }

    @Then("^user verifies the post with DB$")
    public void user_verifies_the_post_with_DB() throws Throwable {

    }

    @When("^user creates post with \"([^\"]*)\"$")
    public void user_creates_post_with(String arg1) throws Throwable {

    }

}
