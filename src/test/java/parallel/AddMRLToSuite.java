package parallel;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import util.TestBase;


public class AddMRLToSuite extends TestBase {

	Logger log = Logger.getLogger(AddMRLToSuite.class);
	
	
	@Before("@Scenario2")
	public void setUp() throws InterruptedException {
		log.info("************************Initializing Browser and opening Regression Manager Login Page********************************");
		initialization();
		login();
	}

	
	@Given("^i was provided with suites manager window$")
	public void i_was_provided_with_suites_manager_window() throws Throwable {
		WebElement suitesManager = driver.findElement(By.xpath("//img[@src='images/48x48/shadow/cubes.png']"));
		Actions action = new Actions(driver);
		action.moveToElement(suitesManager).click().build().perform();
		Thread.sleep(10000); 
	}

	
	@Then("^i expand tree node$")
	public void i_expand_tree_node() throws Throwable {
		log.info("************************User expand VINODDUDDUKURI right tree node********************************");
		WebElement vinodduddukuri1 = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_35']/div/span"));
		Actions action = new Actions(driver);
		action.moveToElement(vinodduddukuri1).click().build().perform();
		Thread.sleep(5000);
	}

	
	@Then("^i expand tree node(\\d+)$")
	public void i_expand_tree_node(int arg1) throws Throwable {
		log.info("************************User expand VINODDUDDUKURI left tree node********************************");
		WebElement vinodduddukuri2 = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_13']/div/span"));
		Actions action = new Actions(driver);
		action.moveToElement(vinodduddukuri2).click().build().perform();
		Thread.sleep(2000);
	}

	
	@Then("^i will drag and drop mrl$")
	public void i_will_drag_and_drop_mrl() throws Throwable {
		log.info("************************User add pdf_embed mrl from VINODDUDDUKURI left tree node to testMRL suite in VINODDUDDUKURI right tree********************************");
		Actions action = new Actions(driver);
		WebElement pdf_embed = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_63']"));
		WebElement testMRL = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_40']"));
		WebElement sourceLocator = pdf_embed;
		WebElement targetLocator = testMRL;
		action.clickAndHold(sourceLocator).moveByOffset(-1, -1).moveToElement(targetLocator).release(targetLocator).build().perform();
		action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.dismiss();
		Thread.sleep(5000);
		WebElement window1 = driver.findElement(By.xpath("//div[@id='window1_closeButton']"));
		onClick(driver, window1, 20);
		WebElement logOff = driver.findElement(By.xpath("//img[@src='images/48x48/shadow/door.png']"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(logOff).click().build().perform();
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	@After("@Scenario2")
	 public void endScenario(Scenario scenario) throws Exception {
		 onFailure(scenario);
		 driver.quit();
	 }
	
	public void onClick(WebDriver driver,WebElement locator,long Timeouts) {
		new WebDriverWait(driver, Timeouts).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
}
