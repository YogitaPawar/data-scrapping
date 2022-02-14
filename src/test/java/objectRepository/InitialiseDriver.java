package objectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitialiseDriver {
	public WebDriver driver;

	public WebDriver InitialiseDriver() {
		System.setProperty("webdriver.chrome.driver","/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.setAcceptInsecureCerts(true);
		//options.addArguments("--headless");

		driver = new ChromeDriver();//options);
		driver.get("https://www.tarladalal.com/");
return driver;
	}

	public void initialise() {


	}

}
