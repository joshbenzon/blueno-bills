package WebScraping;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

/**
 * This is the WebScraper class. It provides the data for the menu functionality in our app by
 * scraping from dining.brown.edu.
 */
public class WebScraper {

  private ChromeDriver driver;
  private final ArrayList<String[]> rattyBreak = new ArrayList<>();
  private final ArrayList<String[]> rattyLunch = new ArrayList<>();
  private final ArrayList<String[]> rattyDin = new ArrayList<>();
  private final ArrayList<String[]> vDubBreak = new ArrayList<>();
  private final ArrayList<String[]> vDubLunch = new ArrayList<>();
  private final ArrayList<String[]> vDubDin = new ArrayList<>();
  private final ArrayList<String[]> andrewsLunch = new ArrayList<>();
  private final ArrayList<String[]> andrewsDin = new ArrayList<>();
  private final ArrayList<String[]> ivyRoomLunch = new ArrayList<>();
  private final ArrayList<String[]> ivyRoomDin = new ArrayList<>();
  private final ArrayList<String[]> blueRoomBreak = new ArrayList<>();
  private final ArrayList<String[]> blueRoomLunch = new ArrayList<>();
  private ArrayList<String[]> josDin = new ArrayList<>();
  private DayOfWeek day;


  /**
   * This is the WebScraper constructor. It initializes the day of the week, as some menus are
   * dependent upon it.
   */
  public WebScraper() {
    LocalDate today = LocalDate.now();
    day = today.getDayOfWeek();
  }

  /**
   * This public method scrapes the lunch and dinner items from Andrews dining hall by calling
   * on the scrapeTwo() method.
   */
  public void scrapeAndrews() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeTwo("https://dining.brown.edu/cafe/andrews-commons/", andrewsLunch, andrewsDin);
    driver.quit();

  }

  /**
   * This public method scrapes the lunch and dinner items from Ivy Room dining hall by calling
   * on the scrapeTwo() method.
   */
  public void scrapeIvyRoom() {


    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);
    String[] message = {"Meal currently not being served", ""};

    if(day!=DayOfWeek.FRIDAY && day!= DayOfWeek.SATURDAY){
      scrapeTwo("https://dining.brown.edu/cafe/ivy-room/", ivyRoomLunch, ivyRoomDin);}
    else if (day == DayOfWeek.FRIDAY){
      scrapeOneMenu("https://dining.brown.edu/cafe/ivy-room/", ivyRoomLunch);
      ivyRoomDin.add(message);
    } else{
      ivyRoomLunch.add(message);
      ivyRoomDin.add(message);
    }
    driver.quit();

  }

  /**
   * This public method scrapes the breakfast and lunch items from Blue Room dining hall by calling
   * on the scrapeTwo() method.
   */
  public void scrapeBlueRoom() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    if(day!=DayOfWeek.SATURDAY || day !=DayOfWeek.SUNDAY) {
      scrapeTwo("https://dining.brown.edu/cafe/blue-room/", blueRoomBreak, blueRoomLunch);
    } else{
      String[] message = {"Meal currently not being served", ""};
      blueRoomBreak.add(message);
      blueRoomLunch.add(message);
    }
    driver.quit();

  }

  /**
   * This public method scrapes the dinner items from Jo's dining hall by calling
   * on the scrapeOne() method.
   */
  public void scrapeJos() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeOneMenu("https://dining.brown.edu/cafe/josiahs/", josDin);
    driver.quit();
    driver.quit();
  }

  /**
   * This public method scrapes the breakfast, lunch, and dinner items from Sharpe Refectory dining
   * hall by calling on the scrapeThree() method.
   */
  public void scrapeRatty() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeThree("https://dining.brown.edu/cafe/sharpe-refectory/", rattyBreak, rattyLunch,
        rattyDin);
    driver.quit();

  }

  /**
   * This public method scrapes the breakfast, lunch, and dinner items from Verney Woolley dining
   * hall by calling on the scrapeThree() method.
   */
  public void scrapeVDub() {

    if(day!= DayOfWeek.SATURDAY || day!= DayOfWeek.SUNDAY) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      driver = new ChromeDriver(options);

      scrapeThree("https://dining.brown.edu/cafe/verney-woolley/", vDubBreak, vDubLunch, vDubDin);
      driver.quit();
    } else{
      String[] message = {"Meal currently not being served", ""};
      vDubBreak.add(message);
      vDubLunch.add(message);
      vDubDin.add(message);

    }

  }

  /**
   * This private method scrapes data from the url passed in and populates the 3 arraylists passed
   * in with that data by calling on getMenuItems().
   * @param url - the url corresponding to the dining hall menu being scraped
   * @param DHbreakfast - the array list corresponding to breakfast items from a dining hall
   * @param DHlunch - the array list corresponding to lunch items from a dining hall
   * @param DHdinner - - the array list corresponding to dinner items from a dining hall
   */
  private void scrapeThree(String url, ArrayList<String[]> DHbreakfast, ArrayList<String[]> DHlunch,
                           ArrayList<String[]> DHdinner) {

    driver.get(url);
    List<WebElement> menus = driver.findElements(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    WebElement breakfast = menus.get(0);
    WebElement lunch = menus.get(1);
    WebElement dinner = menus.get(2);

    List<WebElement> breakfastMenuItems =
        breakfast.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement> lunchMenuItems = lunch.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement> dinnerMenuItems =
        dinner.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> breakfastList = new ArrayList<>();
    ArrayList<String[]> lunchList = new ArrayList<>();
    ArrayList<String[]> dinnerList = new ArrayList<>();

    DHbreakfast.addAll(getMenuItems(breakfastMenuItems, breakfastList));

    DHlunch.addAll(getMenuItems(lunchMenuItems, lunchList));

    DHdinner.addAll(getMenuItems(dinnerMenuItems, dinnerList));

  }

  /**
   * This private method scrapes data from the url passed in and populates the 2 arraylists passed
   * in with that data by calling on getMenuItems().
   * @param url - the url corresponding to the dining hall menu being scraped
   * @param DHMenuOne - the array list corresponding to first set of items from a dining hall
   * @param DHMenuTwo - the array list corresponding to second set of items from a dining hall
   */
  private void scrapeTwo(String url, ArrayList<String[]> DHMenuOne, ArrayList<String[]> DHMenuTwo) {

    driver.get(url);
    List<WebElement> menus = driver.findElements(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    //andrews sometimes doesn't have serve both menus
    WebElement first = menus.get(0);
    WebElement second = menus.get(1);

    List<WebElement> firstItems = first.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement> secondItems = second.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> firstList = new ArrayList<>();
    ArrayList<String[]> secondList = new ArrayList<>();

    DHMenuOne.addAll(getMenuItems(firstItems, firstList));

    DHMenuTwo.addAll(getMenuItems(secondItems, secondList));

  }

  /**
   * This private method scrapes data from the url passed in and returns an array list with that
   * data by calling on getMenuItems().
   * @param url - the url corresponding to the dining hall menu being scraped
   */
  private void scrapeOneMenu(String url, ArrayList<String[]> DHMenu) {
    driver.get(url);
    WebElement menu = driver.findElement(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    List<WebElement> menuItems =
        menu.findElements(By.className("site-panel__daypart-item-container"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> menuList = new ArrayList<>();

    DHMenu.addAll(getMenuItems(menuItems, menuList));
  }

  /**
   *
   * @param menuItems - the list of web elements from the DOM to be scraped
   * @param menuList - the array list to be populated with scraped data
   * @return the populated array list of scraped data
   */
  private ArrayList<String[]> getMenuItems(List<WebElement> menuItems,
                                           ArrayList<String[]> menuList) {

    for (WebElement menuItem : menuItems) {
      String header =
          menuItem.findElement(By.className("site-panel__daypart-item-header")).getText();
      //running over again in case text did not register the first time
      while (Objects.equals(
          header, "")) {
        header = menuItem.findElement(By.className("site-panel__daypart-item-header")).getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
      }
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
      String description =
          menuItem.findElement(By.className("site-panel__daypart-item-content")).getText();
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

      String[] food = {header, description};
      menuList.add(food);
    }
    return menuList;
  }

  /**
   * This private method expands any dropdown buttons on the dining hall menu pages
   */
  private void expandDropDowns() {
    List<WebElement> dropDowns =
        driver.findElements(By.className("site-panel__daypart-accordion-btn"));

    for (WebElement button : dropDowns) {
      if (!Boolean.parseBoolean(button.getAttribute("aria-expanded"))) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        button.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
      }
    }
  }

  /**
   * A getter method for the ratty breakfast menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getRattyBreak() {
    return rattyBreak;
  }

  /**
   * A getter method for the ratty lunch menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getRattyLunch() {
    return rattyLunch;
  }

  /**
   * A getter method for the ratty dinner menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getRattyDin() {

    return rattyDin;
  }

  /**
   * A getter method for the VDub breakfast menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getvDubBreak() {
    return vDubBreak;
  }

  /**
   * A getter method for the VDub lunch menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getvDubLunch() {
    return vDubLunch;
  }

  /**
   * A getter method for the VDub dinner menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getvDubDin() {
    return vDubDin;
  }

  /**
   * A getter method for the Andrews lunch menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getAndrewsLunch() {
    return andrewsLunch;
  }

  /**
   * A getter method for the Andrews dinner menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getAndrewsDin() {
    return andrewsDin;
  }

  /**
   * A getter method for the Ivy Room lunch menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getIvyRoomLunch() {
    return ivyRoomLunch;
  }

  /**
   * A getter method for the Ivy Room dinner menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getIvyRoomDin() {
    return ivyRoomDin;
  }

  /**
   * A getter method for the Blue Room breakfast menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getBlueRoomBreak() {
    return blueRoomBreak;
  }

  /**
   * A getter method for the Blue Room lunch menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getBlueRoomLunch() {
    return blueRoomLunch;
  }

  /**
   * A getter method for the Jo's dinner menu items
   * @return array list of string arrays representing menu items as [header, description]
   */
  public ArrayList<String[]> getJosDin() {
    return josDin;
  }
}
