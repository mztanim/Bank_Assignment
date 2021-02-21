package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import Util.ConfigReader;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Amazon_Steps {


	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

    /** To Wait Until Element to be Visible */
    public void explicitWaitvisibilityOfElementLocated(By element, long timeInSeconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, timeInSeconds);
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

	
	@Given("the user launches Amazon")
	public void the_user_launches_amazon() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://www.amazon.com/");
	}


	@When("the user is on Amazon homepage")
	public void the_user_is_on_amazon_homepage() {
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Amazon"));

	}
	@Then("the user searches Nikon on Amazon search bar")
	public void the_user_searches_nikon_on_amazon_search_bar() {
		explicitWaitvisibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']"),30);
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Nikon");

		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

	}
	@Then("the user sorts High to Low from the sortby list")
	public void the_user_sorts_high_to_low_from_the_sortby_list() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[@class='a-dropdown-label']")).click();
		driver.findElement(By.xpath("//a[@id='s-result-sort-select_2']")).click();

	}
	@Then("the user selects the second item")
	public void the_user_selects_the_second_item() {
		// Write code here that turns the phrase above into concrete actions
		explicitWaitvisibilityOfElementLocated(By.xpath("//div[@data-cel-widget='search_result_1']//img"),30);
		driver.findElement(By.xpath("//div[@data-cel-widget=\"search_result_1\"]//h2//a")).click();

	}
	@Then("verify the text matching with Nikon D3X")
	public void verify_the_text_matching_with_nikon_d3x() {
		// Write code here that turns the phrase above into concrete actions
		explicitWaitvisibilityOfElementLocated(By.xpath("//span[@id='productTitle']"),30);
		String productTitle = driver.findElement(By.xpath("//span[@id='productTitle']")).getText().trim();
		System.out.println(productTitle);

		Assert.assertTrue(productTitle.contains("Nikon D3X"));;
	}
}
