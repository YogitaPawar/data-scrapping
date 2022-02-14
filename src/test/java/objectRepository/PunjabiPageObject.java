package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PunjabiPageObject {

    WebDriver driver = null;

public	PunjabiPageObject(WebDriver driver){
this.driver = driver;
}

By search_textbox=By.cssSelector("#ctl00_txtsearch");
By search_btn=By.name("ctl00$imgsearch");
By link_click=By.cssSelector("a[class='rcpsrch_suggest']");

public void textbox() {
driver.findElement(search_textbox).sendKeys("Punjabi");
}

public void search() {
driver.findElement(search_btn).click();
}

public void linkclick() {
driver.findElement(link_click).click();
}


}
