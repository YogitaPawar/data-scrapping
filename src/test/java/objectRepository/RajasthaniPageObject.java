package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RajasthaniPageObject {
	
	                 WebDriver driver = null;
	
	public	RajasthaniPageObject(WebDriver driver){
		this.driver = driver;
	}
	
	By search_textbox=By.id("ctl00_txtsearch");
	By search_btn=By.name("ctl00$imgsearch");
	By link_click=By.cssSelector("a[class='rcpsrch_suggest']");
	
	public void textbox() {
		driver.findElement(search_textbox).sendKeys("Rajasthani");
	}
	
	public void search() {
		driver.findElement(search_btn).click();
	}
	
	public void linkclick() {
		driver.findElement(link_click).click();
	}
	

}
