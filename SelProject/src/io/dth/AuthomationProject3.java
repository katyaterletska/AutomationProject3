package io.dth;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthomationProject3 {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1. Go to http://lexus.com
		driver.get("http://lexus.com");
		
		//2. Verify the title of the page contains “Experience Amazing”. 
		String actualTitle = driver.getTitle();
		String expectedTitle = "Experience Amazing";
		
		if (actualTitle.contains(expectedTitle)) {
			System.out.println("Task2: PASS");
		}else {
			System.out.println("Task2: FAIL");
		}
		
		//3. Click on Do not Sell My Personal information at the bottom of the page.
		String pW = driver.getWindowHandle();
		smartWait(4);
		driver.findElement(By.xpath("//a[@href='https://privacy.toyota.com/']")).click();
		
		//4. Verify the page title contains “Privacy Hub”.
		Set<String> windowH = driver.getWindowHandles();
		String expectedW = "Privacy Hub";
		for(String handles:windowH) {
			driver.switchTo().window(handles);
			if(driver.getTitle().contains(expectedW)) {
				break;
			}
		}
		smartWait(1);
		//System.out.println(driver.getTitle());
		
		
		if (driver.getTitle().contains(expectedW)) {
			System.out.println("Task4: PASS");
		}else {
			System.out.println("Task4: FAIL");
		}
		
		//5. Click on Your Privacy Rights.
		WebElement yourPrivacyRights = driver.findElement(By.xpath("(//a[@href='privacy-hub/privacyright.html'])[2]"));
		yourPrivacyRights.click();
		
		//.6 Verify that the page url is “https://privacy.toyota.com/privacy-hub/privacyright.html”.
		if(driver.getCurrentUrl().equals("https://privacy.toyota.com/privacy-hub/privacyright.html")) {
			System.out.println("Task6: PASS");
		}else {
			System.out.println("Task6: FAIL");
		}
		
		//7. Go back to the main window and click on Build your Lexus.
		driver.switchTo().window(pW);
		smartWait(1);
		driver.navigate().refresh();
		driver.findElement(By.xpath("//span[contains(text(),'BUILD YOUR')]")).click();
        smartWait(1);
        
        //8. Enter “22182” for zipcode and click on Enter on pop-up window.
        WebElement zip = driver.findElement(By.id("zip-overlay"));
        zip.click();
        zip.sendKeys("22182");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        smartWait(1);
        
        //9. Click on model GS.
        driver.findElement(By.xpath("//img[@src='/byl2014/pub-share/images/series/gs.png']")).click();
        
        
       //10. Choose 2020 GS 350 F Sport AWD. Before clicking, get the price 
       //    of the vehicle and save it into an int variable.
       String price =  driver.findElement(By.xpath("(//span[@class='title-price-med float-right'])[4]")).getText();
       price=price.substring(1,price.length()-1);
       price = price.replace(",","");
       int price1 = Integer.parseInt(price);
       //System.out.println(price1);
       smartWait(3);
       
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='omniture'])[5]"))).click();
       smartWait(5);
       
       
       //****************************************************
       //Site updated and now rest of the code doesn't work. No more GS 350 F Sport AWD available.
       //****************************************************
     
       
       //11. On the next page, click on the price menu on top and retrieve 
       //    and store the base price, dp&h fee and msrp into separate int 
       //    variables. Verify that the base price is the same as the price 
       //    shown at Step 10.  Verify that msrp equals to base price + dp&h fee;
       driver.findElement(By.xpath("//span[@id='total-price'][contains(text(),'55,530')]")).click();
       smartWait(3);

       String basePrice=driver.findElement(By.xpath("//span[contains(text(),'54,505')]")).getText();
       basePrice=basePrice.substring(1);
       basePrice=basePrice.replace(",","");
       int basePrice1 = Integer.parseInt(basePrice);
       //System.out.println(basePrice1);
       smartWait(2);
       
       String dpAndh=driver.findElement(By.xpath("//span[contains(text(),'1,025')]")).getText();
       dpAndh=dpAndh.substring(1);
       dpAndh=dpAndh.replace(",","");
       int dpAndh1 = Integer.parseInt(dpAndh);
       //System.out.println(dpAndh1);
       smartWait(2);
       
       String msrp = driver.findElement(By.xpath("//span[contains(text(),'55,530')]")).getText();
       msrp = msrp.replace(",","");
       int msrp1 = Integer.parseInt(msrp);
       //System.out.println(msrp1);
       smartWait(2);
       int total = basePrice1+dpAndh1;
       //System.out.println(total);
       
       
       
       if((basePrice1==price1)&&(msrp1==total)){
           System.out.println("Task11: PASS");
       }else {
           System.out.println("Task11: FAIL");
       }
       smartWait(2);
       
       
       //12. Close the menu and click on Ultrasonic Blue Mica color.
       driver.findElement(By.id("byl-price-breakdown")).click();
       smartWait(3);
       
     
       

     

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		public static void smartWait(int time){
            try{
                Thread.sleep(1000*time);
            }catch (Exception e){
            }
        }
    }
