package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.TestBase;


public class HomePageObjects extends TestBase {
	
	@FindBy(how=How.XPATH,xpath="//img[@src='images/48x48/shadow/cubes.png']")
	WebElement suitesManager;
	
	@FindBy(how=How.XPATH,xpath="//img[@src='images/48x48/shadow/door.png']")
	WebElement logOff;
	
	@FindBy(how = How.XPATH, xpath="//div[@id='window1_closeButton']")
	WebElement window1;
	
	@FindBy(how = How.XPATH, xpath="//img[@src='images/oralogo-small.gif']")
	WebElement oracleLogo;
	
	public HomePageObjects(){
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateHomePageLogo() {
		return driver.findElement(By.xpath("//img[@src='images/oralogo-small.gif']")).isDisplayed();
	}
	
	public void SuitesManager() throws InterruptedException {
		Thread.sleep(10000);
		Actions action = new Actions(driver);
		action.moveToElement(suitesManager).click().build().perform();
		Thread.sleep(10000); 
	}
	
	public void window_close() {
		onClick(driver, window1, 20);
	}
	
	public void LogOff() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(logOff).click().build().perform();
		Thread.sleep(5000);
	}
	
	public void onClick(WebDriver driver,WebElement locator,long Timeouts) {
		new WebDriverWait(driver, Timeouts).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}

}
