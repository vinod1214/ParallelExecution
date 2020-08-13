package parallel;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import util.TestBase;

public class LoginPage extends TestBase {
	
	Logger log = Logger.getLogger(LoginPage.class);
	
		
	@Before("@Scenario1")
	public void setUp() throws InterruptedException {
		log.info("************************Initializing Browser and login to Regression Manager App********************************");
		initialization();
		login();
	}
	
	
	@Given("^I am given with RegMan Home Page$")
	public void i_am_given_with_RegMan_Home_Page() throws Throwable {
		Thread.sleep(5000);
		log.info("************************Execute method********************************");
	}

	
	@Then("^I validate the HomePage title$")
	public void i_validate_the_HomePage_title() throws Throwable {
	    String title = driver.getTitle();
	    log.info(title);
	    Assert.assertEquals("Documaker Regressions System",title);
	}

	
	@Then("^check for oracle logo$")
	public void check_for_oracle_logo() throws Throwable {
	   boolean flag = driver.findElement(By.xpath("//img[@src='images/oralogo-small.gif']")).isDisplayed();
	   Assert.assertTrue(flag);
	   WebElement logOff = driver.findElement(By.xpath("//img[@src='images/48x48/shadow/door.png']"));
	   Actions action = new Actions(driver);
		action.moveToElement(logOff).click().build().perform();
		Thread.sleep(5000);
	   driver.quit();
	}
	
	
	@After("@Scenario1")
	public void endScenario(Scenario scenario) throws Exception {
		onFailure(scenario);
		driver.quit();
	}
}
