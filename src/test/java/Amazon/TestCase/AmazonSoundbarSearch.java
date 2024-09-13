package Amazon.TestCase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.WebDriver;


public class AmazonSoundbarSearch {

	public static void main(String[] args) {
		
        // Set up Firefox options
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = null;
        try {
            driver = new FirefoxDriver(options);
            driver.get("https://www.amazon.in");

            // Search for LG soundbar
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("lg soundbar");
            searchBox.submit();

            // Wait for search results to load (can be improved with WebDriverWait)
            Thread.sleep(5000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         // Wait for search results to load and products to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")));
            


            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
         // Wait for search results to load and products to be visible
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-price']")));
           
            // Get product titles and prices using XPath
            List<WebElement> productTitles = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
            List<WebElement> productPrices = driver.findElements(By.xpath("//span[@class='a-price']"));

            Map<String, Integer> productPricesMap = new HashMap<>();

            // Combine titles and prices
            for (int i = 0; i < productTitles.size(); i++) {
                String title = productTitles.get(i).getText();
                String priceText = i < productPrices.size() ? productPrices.get(i).getText() : "0";
                int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
                productPricesMap.put(title, price);
            }

            // Sort by price
            List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productPricesMap.entrySet());
            Collections.sort(sortedProducts, (a, b) -> Integer.compare(a.getValue(), b.getValue()));

            // Print sorted products
            for (Map.Entry<String, Integer> entry : sortedProducts) {
                System.out.println(entry.getValue() + " " + entry.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            if (driver != null) {
                driver.quit();
            }
        }
        System.out.println("Completed Task");
    }

	

}
