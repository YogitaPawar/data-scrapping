package objectRepository;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecipePageObjects {
	WebDriver driver = null;
	public	RecipePageObjects(WebDriver driver){
		this.driver = driver;
	}
	By main_menu = new By.ByXPath("//div[@class='menucontainer_div']");
	By all_links_in_recipe = new By.ByXPath("//article[@class='rcc_recipecard']");
	By recipeSteps = new By.ById("recipe_small_steps");
	By image_link_in_recipe = new By.ById("ctl00_cntrightpanel_imgRecipe");
	By nutrion_value = new By.ById("rcpnutrients");
	By ingredients = new By.ByXPath("//div[@id='rcpinglist']");
	By search_textbox= new By.ByXPath("//div[@id='search']/input[@id='ctl00_txtsearch']");
	By search_btn = new By.ByXPath("//div[@id='search']/input[@type='submit']");
	By link_click=By.cssSelector("a[class='rcpsrch_suggest']");
	//By link_click = new By.ByPartialLinkText("Kids Recipes");
	
	public List<WebElement> getAllLinksInRecipesPage() {
		List<WebElement> links=	driver.findElements(all_links_in_recipe);
		return links;

	}
	public void textbox(String searchText) {
		driver.findElement(search_textbox).sendKeys(searchText);
	}
	public void search() {
		driver.findElement(search_btn).click();
	}
	public void linkclick() {
		driver.findElement(link_click).click();
	}
	public WebElement hoverMainMenu() {
		return driver.findElement(main_menu);
	}
	
	public void selectLinkInRecipeListPage(int link) {
		driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+link+"]//div[@class='rcc_rcpcore']/span[1]/a")).click();
		
	} 
	public String getIngredients() {
		return driver.findElement(ingredients).getText();
	}
	
	public String getRecipeSteps() {
		return driver.findElement(recipeSteps).getText();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getImageLink() {
		return driver.findElement(image_link_in_recipe).getAttribute("src");
	}
	
	public String getNutritionValues() {
		return driver.findElement(nutrion_value).getText();
	}
	public String getRecipeLink() {
		return driver.getCurrentUrl();
	}
}
