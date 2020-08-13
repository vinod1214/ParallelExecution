package pageObjects;

import org.openqa.selenium.Alert;
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



public class SuitesManagerPageObjects extends TestBase {
	
	@FindBy(how=How.XPATH,xpath="//div[@id='dijit__TreeNode_35']/div/span")
	WebElement vinodduddukuri1;
	
	@FindBy(how=How.XPATH,xpath="//div[@id='dijit__TreeNode_13']/div/span")
	WebElement vinodduddukuri2;
	
	@FindBy(how=How.XPATH,xpath="//div[@id='dijit__TreeNode_63']")
	WebElement pdf_embed;
	
	@FindBy(how=How.XPATH,xpath="//div[@id='dijit__TreeNode_40']")
	WebElement testMRL;
	
	@FindBy(how = How.XPATH, xpath="//div[@id='dijit__TreeNode_40']/div/span")
	WebElement expandtestMRL;
	
	@FindBy(how = How.XPATH, xpath="//div[@id='dijit__TreeNode_42']")
	WebElement testMRL2;
	
	@FindBy(how=How.XPATH, xpath="//button[@id='tbbtnRemoveSuitesTree1']")
	WebElement removeBtn;
	
	@FindBy(how = How.XPATH, xpath="//div[@id='window1_closeButton']")
	WebElement window1;
	
	//Initializing the Page Objects:
		public SuitesManagerPageObjects(){
			PageFactory.initElements(driver, this);
		}
		
	//Actions
	
	public void expand_right_vinodduddukuri() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(vinodduddukuri1).click().build().perform();
		Thread.sleep(5000);
		
	}
	
	public void expand_left_vinodduddukuri() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(vinodduddukuri2).click().build().perform();
		Thread.sleep(2000);
	}
	
	public void drag_drop_pdf_embed_mrl() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement sourceLocator = pdf_embed;
		WebElement targetLocator = testMRL;
		action.clickAndHold(sourceLocator).moveByOffset(-1, -1).moveToElement(targetLocator).release(targetLocator).build().perform();
		action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.dismiss();
		Thread.sleep(5000);
		onClick(driver, window1, 20);
	}
	
	public void expand_testMRL_suite() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(expandtestMRL).click().build().perform();
		Thread.sleep(5000);
	}
	
	public void remove_mrl() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(testMRL2).click().build().perform();
		onClick(driver, removeBtn, 20);
		Thread.sleep(5000);
		onClick(driver,window1, 20);
	}
	
	public void onClick(WebDriver driver,WebElement locator,long Timeouts) {
		new WebDriverWait(driver, Timeouts).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}

}
