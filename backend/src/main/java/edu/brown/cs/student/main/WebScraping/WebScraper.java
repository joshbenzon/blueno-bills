package edu.brown.cs.student.main.WebScraping;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WebScraper {

    private ChromeDriver driver;



    private final ArrayList<String[]> rattyBreak =  new ArrayList<>();
    private final ArrayList<String[]> rattyLunch = new ArrayList<>();
    private final ArrayList<String[]> rattyDin = new ArrayList<>();
    private final ArrayList<String[]> vDubBreak =  new ArrayList<>();
    private final ArrayList<String[]> vDubLunch =  new ArrayList<>();
    private final ArrayList<String[]> vDubDin =  new ArrayList<>();
    private final ArrayList<String[]> andrewsLunch =  new ArrayList<>();
    private final ArrayList<String[]> andrewsDin =  new ArrayList<>();
    private final ArrayList<String[]> ivyRoomLunch =  new ArrayList<>();
    private final ArrayList<String[]> ivyRoomDin =  new ArrayList<>();
    private final ArrayList<String[]> blueRoomBreak =  new ArrayList<>();
    private final ArrayList<String[]> blueRoomLunch =  new ArrayList<>();
    private ArrayList<String[]> josDin =  new ArrayList<>();

    public WebScraper(){
//      WebDriverManager.chromedriver().setup();
//      ChromeOptions options = new ChromeOptions();
//      this.driver = new ChromeDriver(options);
//      this.scrapeJos();

//      System.out.println("RATTY BREAKFAST IS: ");
//      for (String[] menu : this.getRattyBreak()){
//        System.out.println(Arrays.toString(menu));
//      }

//
//      driver.quit();

    }

  public void scrapeAndrews (){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeTwo("https://dining.brown.edu/cafe/andrews-commons/", andrewsLunch,andrewsDin);
    driver.quit();

  }

  public void scrapeIvyRoom (){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeTwo("https://dining.brown.edu/cafe/ivy-room/", ivyRoomLunch,ivyRoomDin);
    driver.quit();

  }

  public void scrapeBlueRoom (){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeTwo("https://dining.brown.edu/cafe/blue-room/", blueRoomBreak,blueRoomLunch);
    driver.quit();

  }

  public void scrapeJos(){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    josDin = scrapeOneMenu("https://dining.brown.edu/cafe/josiahs/");
    driver.quit();
    driver.quit();
  }


  public void scrapeRatty() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeThree("https://dining.brown.edu/cafe/sharpe-refectory/", rattyBreak, rattyLunch, rattyDin);
    driver.quit();

  }

  public void scrapeVDub() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);

    scrapeThree("https://dining.brown.edu/cafe/verney-woolley/", vDubBreak,vDubLunch,vDubDin);
    driver.quit();

  }

  private void scrapeThree(String url, ArrayList<String[]> DHbreakfast, ArrayList<String[]> DHlunch, ArrayList<String[]> DHdinner) {

    driver.get(url);
    List<WebElement> menus = driver.findElements(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    WebElement breakfast = menus.get(0);
    WebElement lunch = menus.get(1);
    WebElement dinner = menus.get(2);

    List<WebElement> breakfastMenuItems = breakfast.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement>  lunchMenuItems = lunch.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement> dinnerMenuItems = dinner.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> breakfastList = new ArrayList<>();
    ArrayList<String[]> lunchList = new ArrayList<>();
    ArrayList<String[]> dinnerList = new ArrayList<>();

    DHbreakfast.addAll(getMenuItems(breakfastMenuItems, breakfastList));

    DHlunch.addAll(getMenuItems(lunchMenuItems, lunchList));

    DHdinner.addAll(getMenuItems(dinnerMenuItems, dinnerList));

  }

  private void scrapeTwo(String url, ArrayList<String[]> DHMenuOne, ArrayList<String[]> DHMenuTwo) {

    driver.get(url);
    List<WebElement> menus = driver.findElements(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    WebElement first = menus.get(0);
    WebElement second = menus.get(1);

    List<WebElement> firstItems = first.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    List<WebElement>  secondItems = second.findElements(By.className("site-panel__daypart-item"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> firstList = new ArrayList<>();
    ArrayList<String[]> secondList = new ArrayList<>();

    DHMenuOne.addAll(getMenuItems(firstItems, firstList));

    DHMenuTwo.addAll(getMenuItems(secondItems, secondList));

  }

  private ArrayList<String[]> scrapeOneMenu(String url){
    driver.get(url);
    WebElement menu = driver.findElement(By.className("site-panel__daypart-tabs"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    expandDropDowns();

    List<WebElement> menuItems = menu.findElements(By.className("site-panel__daypart-item-container"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    ArrayList<String[]> menuList = new ArrayList<>();

    return getMenuItems(menuItems, menuList);
  }

  private ArrayList<String[]> getMenuItems(List<WebElement> menuItems,
                                           ArrayList<String[]> menuList) {

    for (WebElement menuItem : menuItems) {
      String header =
          menuItem.findElement(By.className("site-panel__daypart-item-header")).getText();
      //running over again in case text did not register the first time
      while(Objects.equals(
          header, "")){
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

  public void expandDropDowns() {
    List<WebElement> dropDowns =
        driver.findElements(By.className("site-panel__daypart-accordion-btn"));

    for(WebElement button : dropDowns){
      if(!Boolean.parseBoolean(button.getAttribute("aria-expanded"))){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        button.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
      }
    }
  }



  public ArrayList<String[]> getRattyBreak() {
    return rattyBreak;
  }

  public ArrayList<String[]> getRattyLunch() {
    return rattyLunch;
  }

  public ArrayList<String[]> getRattyDin() {

    return rattyDin;
  }

  public ArrayList<String[]> getvDubBreak() {
    return vDubBreak;
  }

  public ArrayList<String[]> getvDubLunch() {
    return vDubLunch;
  }

  public ArrayList<String[]> getvDubDin() {
    return vDubDin;
  }

  public ArrayList<String[]> getAndrewsLunch() {
    return andrewsLunch;
  }

  public ArrayList<String[]> getAndrewsDin() {
    return andrewsDin;
  }

  public ArrayList<String[]> getIvyRoomLunch() {
    return ivyRoomLunch;
  }

  public ArrayList<String[]> getIvyRoomDin() {
    return ivyRoomDin;
  }

  public ArrayList<String[]> getBlueRoomBreak() {
    return blueRoomBreak;
  }

  public ArrayList<String[]> getBlueRoomLunch() {
    return blueRoomLunch;
  }

  public ArrayList<String[]> getJosDin() {
    return josDin;
  }
}


