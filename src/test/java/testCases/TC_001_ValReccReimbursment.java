package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC_001_ValReccReimbursment {

	public static void main(String args[]) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		try {
		// 1. Navigate to the FitPeo Homepage:
		driver.get("https://www.fitpeo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Navigate to the Revenue Calculator Page:
		driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();

		// 3. Scroll Down to the Slider section:
		WebElement calculator = driver.findElement(By.xpath("//h4[normalize-space()='Medicare Eligible Patients']"));
		WebElement slider = driver.findElement(
				By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-duk49p']"));
		// Scroll down the slider till calculator:
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", calculator);

		//4) Adjust the Slider: Get the slider dimensions and position
		WebElement slider_input = driver.findElement(By.xpath("//input[@type='number']"));
		WebElement slider1 = driver.findElement(By.className("MuiInputBase-input"));
		slider1.clear();
		WebElement sliderHandle =
		driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]"));		  
		Actions action = new Actions(driver);
		action.dragAndDropBy(sliderHandle,-94,0).perform();		  
		//  action.dragAndDropBy(sliderHandle,124,0).perform();
		slider_input.sendKeys("820");
		Thread.sleep(5000); 
		
		// 5) Update the Text Field:		  		  
		slider_input.sendKeys(Keys.BACK_SPACE);
		slider_input.sendKeys(Keys.BACK_SPACE);
		slider_input.sendKeys(Keys.BACK_SPACE);
		slider_input.sendKeys("560");
		
		Thread.sleep(1000);
		//6) Validate Slider Value:
		  String sliderValue = slider_input.getAttribute("value");
          if ("560".equals(sliderValue)) {
              System.out.println("Slider value is correctly set to 560.");
          } else {
              System.out.println("Slider value is not set correctly.");
          }
          
          //7)Select CPT Codes:
 		action.dragAndDropBy(sliderHandle,-94,0).perform();	
 		slider_input.sendKeys("820");
 		
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider_input);
          WebElement cpt99091 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")); 
          WebElement cpt99453 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")); 
          WebElement cpt99454 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")); 
          WebElement cpt99474 = driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
          if (!cpt99091.isSelected()) cpt99091.click();
          if (!cpt99453.isSelected()) cpt99453.click();
          if (!cpt99454.isSelected()) cpt99454.click();
          if(!cpt99474.isSelected()) cpt99474.click();
          
          Thread.sleep(2000);
       //8 )  Validate Total Recurring Reimbursement:
          WebElement totalReimbursementHeader = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'][normalize-space()='$110700']"));
          String expectedReimbursement = "$110700";
          String actualReimbursement = totalReimbursementHeader.getText();

          if (expectedReimbursement.equals(actualReimbursement)) {
              System.out.println("Total Recurring Reimbursement is correctly displayed as $110700.");
          } else {
              System.out.println("Total Recurring Reimbursement is not displayed correctly.");
          }
          
          driver.quit();
		}
       finally {        
        }
     
          
	}
}
