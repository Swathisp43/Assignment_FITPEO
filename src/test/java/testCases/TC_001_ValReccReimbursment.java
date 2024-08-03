package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		int sliderWidth = slider.getSize().getWidth();
		WebElement sliderHandle = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]"));

		// Check if aria-valuenow attribute is present
		String ariaValueNow = sliderHandle.getAttribute("aria-valuenow");
		int currentValue = 200; // Default value if aria-valuenow is not available
		if (ariaValueNow != null) {
		    try {
		        currentValue = Integer.parseInt(ariaValueNow);
		    } catch (NumberFormatException e) {
		        System.out.println("Failed to parse aria-valuenow: " + ariaValueNow);
		    }
		}

		// Calculate the x-offset to set the slider to 820
		int maxSliderValue = 1000; // Assume the maximum value for the slider is 1000
		int currentOffset = (currentValue * sliderWidth) / maxSliderValue;
		int desiredOffset = (820 * sliderWidth) / maxSliderValue;
		int xOffset = desiredOffset - currentOffset;

		// Move the slider to 820
		Actions move = new Actions(driver);
		move.clickAndHold(sliderHandle).moveByOffset(xOffset, 0).release().perform();

		Thread.sleep(1000); 
		
		// 5) Update the Text Field:		  		  
		 WebElement slider_input = driver.findElement(By.xpath(
				"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));
		slider_input.click();
		slider_input.clear();
		slider_input.sendKeys("560");
		
		
		//6) Validate Slider Value:
		  String sliderValue = slider.getAttribute("value");
          if ("560".equals(sliderValue)) {
              System.out.println("Slider value is correctly set to 560.");
          } else {
              System.out.println("Slider value is not set correctly.");
          }
          
          //7)Select CPT Codes:

          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider_input);
          WebElement cpt99091 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")); // Replace with the actual ID of the checkbox
          WebElement cpt99453 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")); // Replace with the actual ID of the checkbox
          WebElement cpt99454 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")); // Replace with the actual ID of the checkbox
          if (!cpt99091.isSelected()) cpt99091.click();
          if (!cpt99453.isSelected()) cpt99453.click();
          if (!cpt99454.isSelected()) cpt99454.click();
          
       //8   Validate Total Recurring Reimbursement:
          WebElement totalReimbursementHeader = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'][contains(text(),'Total Recurring Reimbursement for all Patients Per')]")); // Replace with the actual ID of the header
          String expectedReimbursement = "Total Recurring Reimbursement for all Patients Per Month: $110700";
          String actualReimbursement = totalReimbursementHeader.getText();

          if (expectedReimbursement.equals(actualReimbursement)) {
              System.out.println("Total Recurring Reimbursement is correctly displayed as $110700.");
          } else {
              System.out.println("Total Recurring Reimbursement is not displayed correctly.");
          }
		}
       finally {
          
      }
      driver.quit();
          
	}
}
