package WebAutomation.WebAutomation;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class QATest {
	
	String pathOfChromeDriver = "C:\\Python37\\Scripts\\chromedriver.exe";
	String URL = "https://marksandspicy.com";
	public WebDriver driver;

	@BeforeEach
	public void beforeTest() {
		//Launching The Browser and navigating to the website
		System.setProperty("webdriver.chrome.driver", pathOfChromeDriver);
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterEach
	public void aftertest() {
		//Closing the browser
		driver.close();
	}
	
	@Test
	public void test1() {
		//Checking Authentication failed message
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email")).sendKeys("test@test.com");
		driver.findElement(By.id("passwd")).sendKeys("ThisIs@T3st");
		driver.findElement(By.id("SubmitLogin")).click();
		WebElement eleErrorMsg = driver.findElement(By.xpath("//*[text()='Authentication failed.']"));
		Assert.assertTrue(eleErrorMsg.isDisplayed());
	}
	
	@Test
	public void test2() {
		//Tooltip message verification
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email")).sendKeys("test@test.com");
		driver.findElement(By.id("email")).sendKeys(Keys.TAB);
		WebElement eleTooltip = driver.findElement(By.xpath("//div[@class='form-group form-ok']"));
		Assert.assertTrue(eleTooltip.isDisplayed());
	}
	
	@Test
	public void test3() {
		//Tooltip error message verification
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys(Keys.TAB);
		List<WebElement> eleToolTip = driver.findElements(By.xpath("//div[@class='form-group form-error']"));
		Assert.assertTrue(eleToolTip.size()==2);
	}
	
	@Test
	public void test4() {
		//Registration
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email_create")).sendKeys("soumya@test.com");
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("email")).sendKeys("soumya@test.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='CivMr']/../label")).click();	
		driver.findElement(By.id("nom")).sendKeys("Sen");
		driver.findElement(By.id("prenom")).sendKeys("Sam");
		driver.findElement(By.id("dateJour")).sendKeys("16");
		driver.findElement(By.id("dateMois")).sendKeys("06");
		driver.findElement(By.id("dateAnnee")).sendKeys("1993");
		driver.findElement(By.id("adresse")).sendKeys("1234 avenue");
		driver.findElement(By.id("codePostal")).sendKeys("32003");
		driver.findElement(By.id("codePostal")).sendKeys(Keys.TAB);
		String populateText = driver.findElement(By.id("ville")).getText();
		if (populateText.length()>0) {
			System.out.println("City and state are automatically populated");
		}else {
			System.out.println("City and state are not automatically populated");
		}
		driver.findElement(By.id("telephonePortable")).sendKeys("0612345678");
		driver.findElement(By.id("telephoneFixe")).sendKeys("0123456789");
		driver.findElement(By.id("BtnCreationSubmit")).click();
	}

}
