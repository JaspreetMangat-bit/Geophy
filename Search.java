package com.geophy.evra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search {
	private WebDriver driver;
	// opening page in chorme browser
	@BeforeMethod(alwaysRun = true)
	private void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "https://evra.geophy.com/search";
		driver.get(url);
		System.out.println("Login Page open");
		driver.manage().window().maximize();

	}

	@Test(priority = 1, groups = { "SearchAll", "smoketest" })
	public void SearchAll() {
		System.out.println("Staring Login Test");
// Enter user name
		WebElement Email = driver.findElement(By.id("email"));
		Email.sendKeys("qaskillschallenge@geophy.com");
// Enter password
		WebElement Pwd = driver.findElement(By.id("password"));
		Pwd.sendKeys("qaskillschallenge@geophy.com");
// click Login
		WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		login.click();	
		System.out.println("Staring search Test");
		
//wait for address input box to display
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address_input")));
//Enter Address
		WebElement AddressText = driver.findElement(By.xpath("//input[@id='address_input']"));
		AddressText.sendKeys("555 N College Ave, Tempe, AZ 85281, USA");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//Enter Net Operating income 2 000 000
		WebElement NOI = driver.findElement(By.xpath("//input[@id='noi']"));
		NOI.sendKeys("2000000");
//Select Number of units 200
		// Select NOU = new
		// Select(driver.findElement(By.xpath("//div[@id='introjsNumberOfUnits']")));
		WebElement NOU = driver.findElement(By.xpath("//input[@name='number_of_units']"));
		// div[@id='introjsNumberOfUnits']//input[@name='number_of_units']
		NOU.sendKeys("200");
		// NOU.selectByVisibleText("200");
//Enter Occupancy 80%
		WebElement Occupancy = driver.findElement(By.xpath("//input[@name='occupancy']"));
		Occupancy.sendKeys("80%");
//Enter Year of Construction 2000
		WebElement YOC = driver.findElement(By.xpath("//input[@name='year_built']"));
		YOC.sendKeys("2000");

//Hit Run Valuation
		WebElement RV = driver.findElement(By.xpath("//button[@id='introjsRunValuationButton']"));
		WebDriverWait Wait3 = new WebDriverWait(driver, 10);
		Wait3.until(ExpectedConditions.elementToBeClickable(RV));
		RV.click();

//wait for valuation page to be displayed
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait Wait = new WebDriverWait(driver, 10);
		WebElement WelcomeMsg = driver.findElement(By.xpath("//div[@id='propertyMap'][@class='sm:w-1/2']"));
		Wait.until(ExpectedConditions.visibilityOf(WelcomeMsg));

//Validate correct valuation page is displayed as per the entered search criteria 
//Validating the address
		WebElement Addval = driver.findElement(
				By.xpath("//section[@id='page-content']/section[2]//h4[.='555 N College Ave, Tempe, AZ 85281, USA']"));
		String actualAdd = Addval.getText();
		System.out.println(actualAdd);
		String expectedAdd = "555 N COLLEGE AVE, TEMPE, AZ 85281, USA";
		Assert.assertEquals(expectedAdd, actualAdd, "Address enterd not same");
//Validating Numbers of units	
		WebElement NOUval = driver.findElement(
				By.xpath("//section[@id='page-content']/section[2]//table[@class='table table-striped']//td[.='200']"));
		String actualUnits = NOUval.getText();
		System.out.println(actualUnits);
		String expectedunits = "200";
		Assert.assertEquals(expectedunits, actualUnits, "Units entered are not same");
//Validating Year of Construction
		WebElement YOCval = driver.findElement(By
				.xpath("//section[@id='page-content']/section[2]//table[@class='table table-striped']//td[.='2000']"));
		String actualYOC = YOCval.getText();
		System.out.println(actualYOC);
		String expectedYOC = "2000";
		Assert.assertEquals(expectedYOC, actualYOC, "YOC entered is not same");
//Validating NOI
		WebElement NOIval = driver.findElement(By.xpath(
				"//section[@id='page-content']/section[2]//table[@class='table table-striped']/tbody/tr[4]/td[2]"));
		String actualNOI = NOIval.getText();
		System.out.println(actualNOI);
		String expectedNOI = "$ 2,000,000";
		Assert.assertEquals(expectedNOI, actualNOI, "YOC entered is not same");
//Validating Occupancy
		WebElement Occval = driver.findElement(By.xpath(
				"//section[@id='page-content']/section[2]//table[@class='table table-striped']/tbody/tr[6]/td[2]"));
		String actualOcc = Occval.getText();
		System.out.println(actualOcc);
		String expectedOcc = "80%";
		Assert.assertEquals(expectedOcc, actualOcc, "Occupancy entered is not same");

	}
//Search only with required fields
	@Test(priority = 2, groups = { "SearchRequired", "smoketest" })
	public void SearchRequired() {
		System.out.println("Staring Login Test");
// Enter user name
		WebElement Email = driver.findElement(By.id("email"));
		Email.sendKeys("qaskillschallenge@geophy.com");
// Enter password
		WebElement Pwd = driver.findElement(By.id("password"));
		Pwd.sendKeys("qaskillschallenge@geophy.com");
// click Login
		WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		login.click();
	
		System.out.println("Staring search Test");
		
//wait for address input box to display
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address_input")));
//Enter Address
		WebElement AddressText = driver.findElement(By.xpath("//input[@id='address_input']"));
		AddressText.sendKeys("555 N College Ave, Tempe, AZ 85281, USA");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//Enter Net Operating income 2 000 000
		WebElement NOI = driver.findElement(By.xpath("//input[@id='noi']"));
		NOI.sendKeys("2000000");
//Select Number of units 200
		// Select NOU = new
		// Select(driver.findElement(By.xpath("//div[@id='introjsNumberOfUnits']")));
		WebElement NOU = driver.findElement(By.xpath("//input[@name='number_of_units']"));
		// div[@id='introjsNumberOfUnits']//input[@name='number_of_units']
		NOU.sendKeys("200");

//Enter Year of Construction 2000
		WebElement YOC = driver.findElement(By.xpath("//input[@name='year_built']"));
		YOC.sendKeys("2000");

//Hit Run Valuation
		WebElement RV = driver.findElement(By.xpath("//button[@id='introjsRunValuationButton']"));
		WebDriverWait Wait3 = new WebDriverWait(driver, 10);
		Wait3.until(ExpectedConditions.elementToBeClickable(RV));
		RV.click();

//wait for valuation page to be displayed
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait Wait = new WebDriverWait(driver, 10);
		WebElement WelcomeMsg = driver.findElement(By.xpath("//div[@id='propertyMap'][@class='sm:w-1/2']"));
		Wait.until(ExpectedConditions.visibilityOf(WelcomeMsg));

//Validate correct valuation page is displayed as per the entered search criteria 
//Validating the address
		WebElement Addval = driver.findElement(
				By.xpath("//section[@id='page-content']/section[2]//h4[.='555 N College Ave, Tempe, AZ 85281, USA']"));
		String actualAdd = Addval.getText();
		System.out.println(actualAdd);
		String expectedAdd = "555 N COLLEGE AVE, TEMPE, AZ 85281, USA";
		Assert.assertEquals(expectedAdd, actualAdd, "Address enterd not same");
//Validating Numbers of units	
		WebElement NOUval = driver.findElement(
				By.xpath("//section[@id='page-content']/section[2]//table[@class='table table-striped']//td[.='200']"));
		String actualUnits = NOUval.getText();
		System.out.println(actualUnits);
		String expectedunits = "200";
		Assert.assertEquals(expectedunits, actualUnits, "Units entered are not same");
//Validating Year of Construction
		WebElement YOCval = driver.findElement(By
				.xpath("//section[@id='page-content']/section[2]//table[@class='table table-striped']//td[.='2000']"));
		String actualYOC = YOCval.getText();
		System.out.println(actualYOC);
		String expectedYOC = "2000";
		Assert.assertEquals(expectedYOC, actualYOC, "YOC entered is not same");
//Validating NOI
		WebElement NOIval = driver.findElement(By.xpath(
				"//section[@id='page-content']/section[2]//table[@class='table table-striped']/tbody/tr[4]/td[2]"));
		String actualNOI = NOIval.getText();
		System.out.println(actualNOI);
		String expectedNOI = "$ 2,000,000";
		Assert.assertEquals(expectedNOI, actualNOI, "YOC entered is not same");


	}
	@Test(priority = 3, groups = { "Mandatoryfieldempty", "smoketest" })
	public void Mandatoryfieldempty() {
		System.out.println("Staring Login Test");
// Enter user name
		WebElement Email = driver.findElement(By.id("email"));
		Email.sendKeys("qaskillschallenge@geophy.com");
// Enter password
		WebElement Pwd = driver.findElement(By.id("password"));
		Pwd.sendKeys("qaskillschallenge@geophy.com");
// click Login
		WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		login.click();
	
		System.out.println("Staring search Test");
		
//Enter Net Operating income 2 000 000
		WebElement NOI = driver.findElement(By.xpath("//input[@id='noi']"));
		NOI.sendKeys("2000000");
//Select Number of units 200
		// Select NOU = new
		// Select(driver.findElement(By.xpath("//div[@id='introjsNumberOfUnits']")));
		WebElement NOU = driver.findElement(By.xpath("//input[@name='number_of_units']"));
		// div[@id='introjsNumberOfUnits']//input[@name='number_of_units']
		NOU.sendKeys("200");

//Enter Year of Construction 2000
		WebElement YOC = driver.findElement(By.xpath("//input[@name='year_built']"));
		YOC.sendKeys("2000");

//Validating if Hit Run Valuation is disabled
		WebElement RV = driver.findElement(By.xpath("//button[@id='introjsRunValuationButton']"));
		Assert.assertTrue(RV.isEnabled(),"RunValidation is enabled");
	}


//Closing the browser 
	@AfterMethod(alwaysRun = true)
	private void teardowm() {
		driver.quit();
	}
}
