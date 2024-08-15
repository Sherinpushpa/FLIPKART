package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Comparator;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    WebDriver driver;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(String url) {
        driver.get(url);
    }
    // enter the text in search bar
    public void enterText(By locator, String text) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Thread.sleep(1000);
        element.sendKeys(text);
    }
    // select the popularity tab
    public void selectPopularity(By locator) {
        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements) {
            if (element.getText().equals("Popularity")) {
                element.getText();
                element.click();
            }
        }
    }
    // print the rating count
    public void rating(By locator) {
        List<WebElement> ratings = driver.findElements(locator);
        int count = 0;

        for (WebElement rating : ratings) {

            if (Float.parseFloat(rating.getText()) <= 4.0) {
                count++;
            }
        }
        System.out.println(count);
    }
    // print the discout percentage along with the title of the product
    public void discount(By locator) {
        List <WebElement> products =  driver.findElements(locator);
        for (WebElement product : products) {
            String discountText = product.findElement(By.xpath(".//div[@class='UkUFwK']")).getText();
            if (Integer.parseInt(discountText.split("%")[0]) >= 17) {
                String title = product.findElement(By.className("KzDlHZ")).getText();
                System.out.println("The Title of the product is: " + title);
                System.out.println("The Discount percentage of the product is: " + discountText);
            }
        }
    }

    public void clickRating(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        // List <WebElement> ratings = driver.findElements(locator);
        // System.out.println(ratings.size());
        // for (WebElement rating : ratings) {
        //     System.out.println(rating.getText());
        //     if (rating.getText().equals(text.trim())) {
        //         rating.click();
        //         System.out.println("rating clicked");
        //     } else {
        //         System.out.println("rating not clicked");
        //     }
        // }
    }
    // print the title and image url according to the review count
    public void titleAndImageUrl(By locator) {
        List <WebElement> products = driver.findElements(locator);

        List<Product> productList = new ArrayList<>();

        for (WebElement product : products) {
            String title = product.findElement(By.xpath(".//a[contains(@class,'wjcEIp')]")).getAttribute("title");
            String reviewCountText = product.findElement(By.xpath(".//span[@class='Wphh3N']")).getText();
            String imageUrl = product.findElement(By.xpath(".//img[contains(@class,'DByuf4')]")).getAttribute("src");

            int reviewCount = Integer.parseInt(reviewCountText.replaceAll("[^0-9]", ""));

            productList.add(new Product(title, reviewCount, imageUrl));
        }

        List<Product> topProducts = productList.stream()
            .sorted(Comparator.comparingInt(Product::getReviewCount).reversed())
            .limit(5)
            .collect(Collectors.toList());

        for (Product product : topProducts) {
            System.out.println("Title: " + product.getTitle());
            System.out.println("Image URL: " + product.getImageUrl());
            System.out.println("Reviews: " + product.getReviewCount());
            System.out.println("--------");
        }
    }
}

class Product {
    private String title;
    private int reviewCount;
    private String imageUrl;

    public Product(String title, int reviewCount, String imageUrl) {
        this.title = title;
        this.reviewCount = reviewCount;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

