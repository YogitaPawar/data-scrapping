package test;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

//import objectRepository.RajasthaniPageObject;
import objectRepository.RecipePageObjects;

import utils.ExcelUtils;

public class HealthySaladsTest {
	public static String category;
	WebDriver driver=null;

	@Test

	public void recipeScrappingHealthysalads() throws Exception {


		System.setProperty("webdriver.chrome.driver", "driver/chromedriver1.exe");
		System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver();//options);

		Map<String, Object> prefs = new HashMap<String, Object>();

		prefs.put("profile.managed_default_content_settings.images", 2);

		ChromeOptions op = new ChromeOptions();
		op.setExperimentalOption("prefs", prefs);
		WebDriver driver= new ChromeDriver(op);

		driver.get("https://www.tarladalal.com/");
		// InitialiseDriver driver = new InitialiseDriver();
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
		RecipePageObjects recipePageObj = new RecipePageObjects(driver);
		//	RajasthaniPageObject rpobj = new RajasthaniPageObject(driver);

		recipePageObj.textbox("Healthy salad");
		recipePageObj.search();
		recipePageObj.linkclick();

		ExcelUtils xlUtils= new ExcelUtils("files/Sample.xlsx");


		/*xlUtils.setCellData("Sheet3", 0, 0, "Title");
		xlUtils.setCellData("Sheet3", 0, 1, "Category");
		xlUtils.setCellData("Sheet3", 0, 2, "Ingredients");
		xlUtils.setCellData("Sheet3", 0, 3, "Method/Recipe Steps");
		xlUtils.setCellData("Sheet3", 0, 4, "Nutrient Values ");
		xlUtils.setCellData("Sheet3", 0, 5, "Recipe image link");
		xlUtils.setCellData("Sheet3", 0, 6, "Link to the recipe");*/


		List<String> allLinksInRecipe =new ArrayList<String>();
		List<WebElement> links = recipePageObj.getAllLinksInRecipesPage();
		// js.executeScript("window.scrollBy(0,3000)");//"arguments[0].scrollIntoView();", links);
		for(int i=1;i<=links.size();i++)//links.size()
		{
			//System.out.println(links.get(i));
			//links.get(i).click();

			recipePageObj.selectLinkInRecipeListPage(i);
			String ingredients = recipePageObj.getIngredients();


			String title = recipePageObj.getTitle();
			//System.out.println(title);

			String recipeSteps = recipePageObj.getRecipeSteps();
			//System.out.println(recipeSteps);


			String imageLink =recipePageObj.getImageLink() ;
			//System.out.println(imageLink);

			String nutriotionVal =recipePageObj.getNutritionValues();
			//System.out.println(nutriotionVal);

			String recipeLink= recipePageObj.getRecipeLink();
			//System.out.println(recipeLink);

			xlUtils.setCellData("Sheet5", i, 0, title);
			xlUtils.setCellData("Sheet5", i, 1, category);
			xlUtils.setCellData("Sheet5", i, 2, ingredients);
			xlUtils.setCellData("Sheet5", i, 3, recipeSteps);
			xlUtils.setCellData("Sheet5", i, 4, nutriotionVal);
			xlUtils.setCellData("Sheet5", i, 5, imageLink);
			xlUtils.setCellData("Sheet5", i, 6, recipeLink);

			driver.navigate().back();

		}
		driver.close();   

	}
}