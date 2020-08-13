package parallel;

import org.apache.log4j.Logger;
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

public class RemoveMRLFromSuite extends TestBase {

	Logger log = Logger.getLogger(RemoveMRLFromSuite.class);
	
	
	@Before("@Scenario3")
	public void setUp() throws InterruptedException {
		log.info("************************Initializing Browser and opening Regression Manager Login Page********************************");
		initialization();
		login();
	}

	@Given("^i am provided with suites manager window$")
	public void i_am_provided_with_suites_manager_window() throws Throwable {
		WebElement suitesManager = driver.findElement(By.xpath("//img[@src='images/48x48/shadow/cubes.png']"));
		Actions action = new Actions(driver);
		action.moveToElement(suitesManager).click().build().perform();
		Thread.sleep(10000); 
	}
	
	@Then("^i expand run testMrl suite tree node$")
	public void i_expand_run_testMrl_suite_tree_node() throws Throwable {
		log.info("************************Expand testMrl suite under VINODDUDDUKURI right tree node********************************");
		WebElement vinodduddukuri1 = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_35']/div/span"));
		Actions action = new Actions(driver);
		action.moveToElement(vinodduddukuri1).click().build().perform();
		Thread.sleep(5000);
		WebElement expandtestMRL = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_40']/div/span"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(expandtestMRL).click().build().perform();
		Thread.sleep(5000);
	}

	
	@Then("^i select and remove mrl from suite$")
	public void i_select_and_remove_mrl_from_suite() throws Throwable {
		log.info("************************Remove pdf_embed mrl from testMrl suite available in VINODDUDDUKURI right tree ********************************");
		WebElement testMRL2 = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_42']"));
		WebElement removeBtn = driver.findElement(By.xpath("//button[@id='tbbtnRemoveSuitesTree1']"));
		WebElement window1 = driver.findElement(By.xpath("//div[@id='window1_closeButton']"));
		Actions action = new Actions(driver);
		action.moveToElement(testMRL2).click().build().perform();
		onClick(driver, removeBtn, 20);
		Thread.sleep(5000);
		onClick(driver,window1, 20);
		WebElement logOff = driver.findElement(By.xpath("//img[@src='images/48x48/shadow/door.png']"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(logOff).click().build().perform();
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	@After("@Scenario3")
	public void endScenario(Scenario scenario) throws Exception {
		onFailure(scenario);
		driver.quit();
	}
	
	public void onClick(WebDriver driver,WebElement locator,long Timeouts) {
		new WebDriverWait(driver, Timeouts).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
}
