package edu.brown.cs.student.main.WebScraping;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebScraper {

    private final ChromeDriver driver;

    public WebScraper(){
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      this.driver = new ChromeDriver(options);
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
      this.scrapeRatty();

    }

    public void scrapeRatty(){
//      Map<String, String> menuItemMap
//          = new HashMap<>();

      driver.get("https://dining.brown.edu/cafe/sharpe-refectory/");
      List<WebElement> menus = driver.findElements(By.className("site-panel__daypart-tabs"));
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

      WebElement breakfast = menus.get(0);
      WebElement lunch = menus.get(1);
      WebElement dinner = menus.get(2);

      List<WebElement> breakfastMenuItems = breakfast.findElements(By.className("site-panel__daypart-item"));
      List<WebElement> lunchMenuItems = lunch.findElements(By.className("site-panel__daypart-item"));
      List<WebElement> dinnerMenuItems = dinner.findElements(By.className("site-panel__daypart-item"));

      ArrayList<String> breakfastList = new ArrayList<>();
      ArrayList<String> lunchList = new ArrayList<>();
      ArrayList<String> dinnerList = new ArrayList<>();

      getMenuItems(breakfastMenuItems, breakfastList);

      getMenuItems(lunchMenuItems, lunchList);

      getMenuItems(dinnerMenuItems, dinnerList);

      // whole container: class="l-container site-panel__daypart-container site-panel__daypart-container--initialized"
      // time of day: class="s-header site-panel__daypart-header"
      //meal div: class="panel__title site-panel__daypart-panel-title "
      //time div: class="site-panel__daypart-time"
      // food: class="c-tab__content-inner site-panel__daypart-tab-content-inner"
      // station title: class="site-panel__daypart-station-title" // might have to get inner text of button
      // header : class="site-panel__daypart-item-header"
      // content : class="site-panel__daypart-item-description"

      System.out.println(breakfastList);
      System.out.println(lunchList);
      System.out.println(dinnerList);
      driver.quit();
    }

  private void getMenuItems(List<WebElement> menuItems,
                            ArrayList<String> menuList) {
    for (WebElement menuItem : menuItems) {
      String header =
          menuItem.findElement(By.className("site-panel__daypart-item-header")).getText();
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
      String description =
          menuItem.findElement(By.className("site-panel__daypart-item-content")).getText();

      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

      String[] food = {header, description};
      menuList.add(Arrays.deepToString(food));
    }
  }


}


