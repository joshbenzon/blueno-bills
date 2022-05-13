package edu.brown.cs.student;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WebScraperTest {
  @Test
  public void testArrayListsNotNull() {
    WebScraper scraper = new WebScraper();
    scraper.scrapeIvyRoom();
    scraper.scrapeJos();
    scraper.scrapeRatty();
    scraper.scrapeBlueRoom();
    scraper.scrapeVDub();
    scraper.scrapeAndrews();

    assertNotEquals(scraper.getIvyRoomLunch(),null);
    assertNotEquals(scraper.getAndrewsLunch(),null);
    assertNotEquals(scraper.getAndrewsDin(),null);
    assertNotEquals(scraper.getBlueRoomBreak(),null);
    assertNotEquals(scraper.getBlueRoomLunch(),null);
    assertNotEquals(scraper.getRattyBreak(),null);
    assertNotEquals(scraper.getRattyLunch(),null);
    assertNotEquals(scraper.getRattyDin(),null);
    assertNotEquals(scraper.getvDubBreak(),null);
    assertNotEquals(scraper.getvDubLunch(),null);
    assertNotEquals(scraper.getvDubDin(),null);
    assertNotEquals(scraper.getJosDin(),null);
  }

  @Test
  public void testArrayListsNotEmpty() {
    WebScraper scraper = new WebScraper();
    scraper.scrapeIvyRoom();
    scraper.scrapeJos();
    scraper.scrapeRatty();
    scraper.scrapeBlueRoom();
    scraper.scrapeVDub();
    scraper.scrapeAndrews();

    assertNotEquals(scraper.getIvyRoomLunch().size(),0);
    assertNotEquals(scraper.getAndrewsLunch().size(),0);
    assertNotEquals(scraper.getAndrewsDin().size(),0);
    assertNotEquals(scraper.getBlueRoomBreak().size(),0);
    assertNotEquals(scraper.getBlueRoomLunch().size(),0);
    assertNotEquals(scraper.getRattyBreak().size(),0);
    assertNotEquals(scraper.getRattyLunch().size(),0);
    assertNotEquals(scraper.getRattyDin().size(),0);
    assertNotEquals(scraper.getvDubBreak().size(),0);
    assertNotEquals(scraper.getvDubLunch().size(),0);
    assertNotEquals(scraper.getvDubDin().size(),0);
    assertNotEquals(scraper.getJosDin().size(),0);
  }

  @Test
  public void apiRattyTest(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeRatty();
    Gson GSON = new Gson();
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/ratty");
    WebElement body = driver.findElement(By.tagName("body"));
    String menu = GSON.toJson(scraper.getRattyBreak())  +
        GSON.toJson(scraper.getRattyLunch())  + GSON.toJson(scraper.getRattyDin());
    assertEquals(body.getText(), menu);
    driver.close();
  }

  @Test
  public void apiVDubTest(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeVDub();
    Gson GSON = new Gson();
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/vdub");
    WebElement body = driver.findElement(By.tagName("body"));
    String menu = GSON.toJson(scraper.getvDubBreak())  +
        GSON.toJson(scraper.getvDubLunch())  + GSON.toJson(scraper.getvDubDin());
    assertEquals(body.getText(), menu);
    driver.close();
  }

  @Test
  public void apiAndrewsTest(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeAndrews();
    Gson GSON = new Gson();
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/andrews");
    WebElement body = driver.findElement(By.tagName("body"));
    String menu = GSON.toJson(scraper.getAndrewsLunch())  +
        GSON.toJson(scraper.getAndrewsDin());
    assertEquals(body.getText(), menu);
    driver.close();
  }

  @Test
  public void apiIvyRoom(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeIvyRoom();
    Gson GSON = new Gson();
    String menu;
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/ivyroom");
    WebElement body = driver.findElement(By.tagName("body"));
    if(scraper.getIvyRoomDin() == null){
      menu = GSON.toJson(scraper.getIvyRoomLunch());
    }else {
      menu = GSON.toJson(scraper.getIvyRoomLunch()) +
          GSON.toJson(scraper.getIvyRoomDin());
    }
    assertEquals(body.getText(), menu);
    driver.close();
  }

  @Test
  public void apiBlueRoom(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeBlueRoom();
    Gson GSON = new Gson();
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/blueroom");
    WebElement body = driver.findElement(By.tagName("body"));
    String menu = GSON.toJson(scraper.getBlueRoomBreak())  +
        GSON.toJson(scraper.getBlueRoomLunch());
    assertEquals(body.getText(), menu);
    driver.close();
  }

  @Test
  public void apiJos(){
    WebScraper scraper = new WebScraper();
    scraper.scrapeJos();
    Gson GSON = new Gson();
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("http://localhost:4567/jos");
    WebElement body = driver.findElement(By.tagName("body"));
    String menu = GSON.toJson(scraper.getJosDin());
    assertEquals(body.getText(), menu);
    driver.close();
  }
}
