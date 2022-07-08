package com.visionit.automation.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	WebDriver driver;
	String base_url = "https://www.amazon.in/";
	int implicit_Wait_timeout_in_sec =20;
	
	
	
	@Given("User opened browser")
    public void user_opened_browser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicit_Wait_timeout_in_sec,TimeUnit.SECONDS);
		
		
        
    }
    
    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
       driver.get("https://www.amazon.in/");
       String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
       String actual = driver.getTitle();
       Assert.assertEquals("page Title validation",expected,actual);
    	
    	
    }
    
    @When("User Search for product {string}")
    public void user_search_for_product(String Laptop) {
       
    	WebDriverWait driverWait = new WebDriverWait(driver,20);
    	WebElement elementSearchBox = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
    	
    	elementSearchBox.sendKeys(Laptop);
    	driver.findElement(By.xpath("//input[@value='Go']")).click();
    	
    }
    
    @Then("Search Result page is displayed")
    public void search_result_page_is_displayed() {
    	WebDriverWait driverWait1 = new WebDriverWait(driver,20);
    	driverWait1.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));
    	
    	Assert.assertEquals("page Title validation","Amazon.in : Laptop",driver.getTitle());
    	
       
    }
    @Given("User search for product\"Laptop\"")
    public void user_search_for_product_laptop() {
    	   
    	       
    }
    
    @When("User clicked on any Product")
    public void user_clicked_on_any_product() {
    	 //listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']//h2"));
        //But at this step asks click on any links displayed in the search box
        //listOfProducts.get(0).click();
       
       
    }
    @Then("Product description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
        //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String prodDescp = it.next();//gives the child window id

        driver.switchTo().window(prodDescp); // switch to product Descp

        //Now driver can access new driver window, but can not access the orignal tab
        //Check product title is displayed
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id=\"add-to-cart-button\"]"));
        Assert.assertEquals("Product Title",true,addToCartButton.isDisplayed());

        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        
    }

     
    }

	
	
	


