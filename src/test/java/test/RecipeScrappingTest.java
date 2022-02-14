package test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import objectRepository.InitialiseDriver;
import objectRepository.RecipePageObjects;

import utils.ExcelUtils;


public class RecipeScrappingTest {
	public static String category;
	WebDriver driver=null;
	@Test
	public void recipeScrapping() throws Exception {
		System.setProperty("webdriver.chrome.driver","/drivers/chromedriver.exe");
		System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
		//ChromeOptions options = new ChromeOptions();
		

		//options.setAcceptInsecureCerts(true);
		//options.addArguments("--headless");

		driver = new ChromeDriver();//options);
		driver.get("https://www.tarladalal.com/recipes-for-high-blood-pressure-644");
		//	InitialiseDriver driver = new InitialiseDriver();
		RecipePageObjects recipePageObj = new RecipePageObjects(driver);
		


		Actions actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
		/*WebElement mainMenu = recipePageObj.hoverMainMenu();
		//actions.moveToElement(mainMenu);


		WebElement subMenu = sublinksObj.getHealthInMenu();
		actions.moveToElement(subMenu);

		WebElement secondSubMenu = sublinksObj.getBloodPressureInHealth();

		actions.moveToElement(secondSubMenu);

		actions.click().build().perform();*/
		
		ExcelUtils excel =  new ExcelUtils(".\\files\\Sample.xlsx");
		List<String> allLinksInRecipe =new ArrayList<String>();
		List<WebElement> links = recipePageObj.getAllLinksInRecipesPage();
		
		//	js.executeScript("window.scrollBy(0,3000)");//"arguments[0].scrollIntoView();", links);
		excel.WriteHeader();
		for(int i=1;i<20;i++)//links.size()
		{
			recipePageObj.selectLinkInRecipeListPage(i);

			String title = recipePageObj.getTitle();			
			//System.out.println(title);
			excel.setCellData("Sheet1", i, 0, title);

			String ingredients = recipePageObj.getIngredients();			
			//System.out.println(ingredients);
			excel.setCellData("Sheet1", i, 2, ingredients);

			String recipeSteps = recipePageObj.getRecipeSteps();
			excel.setCellData("Sheet1", i, 3, ingredients);

			String nutritionVal =recipePageObj.getNutritionValues();
			excel.setCellData("Sheet1", i, 4, nutritionVal);

			String imageLink =recipePageObj.getImageLink() ;
			excel.setCellData("Sheet1", i, 5, imageLink);

			String recipeLink= recipePageObj.getRecipeLink();
			excel.setCellData("Sheet1", i, 6, recipeLink);

			driver.navigate().back();

		}
	}

}
