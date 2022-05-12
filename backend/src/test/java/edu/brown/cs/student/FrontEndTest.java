package edu.brown.cs.student;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author shivanimendiratta
 */
public class FrontEndTest {

  private ChromeDriver driver;

  @Before
  public void startBackend() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    this.driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    driver.get("ws://localhost:4567/");
  }

  //tests that a table appears after the load button is pressed
  @Test
  public void loadButton() {
    WebElement loadButton = driver.findElement(By.id("loader"));
    loadButton.click();
    WebElement table = driver.findElement(By.id("table"));
    assertEquals(table.getTagName(), "table");

  }

  //tests that the choose table dropdown has 3 options
  @Test
  public void dropDownMenu() {

    WebElement loadButton = driver.findElement(By.id("loader"));
    loadButton.click();
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    Select select = new Select(driver.findElement(By.id("tables")));
    List<WebElement> options = select.getOptions();

    assertEquals(3, options.size());
    assertEquals("horoscopes", options.get(0).getText());
    assertEquals("tas", options.get(1).getText());
    assertEquals("ta_horoscope", options.get(2).getText());

  }

  //tests that the table's dimensions are correct according to each table the user chooses in the dropdown
  @Test
  public void tableUI() {

    WebElement loadButton = driver.findElement(By.id("loader"));
    loadButton.click();

    WebElement div = driver.findElement(By.id("dropdown"));
    WebElement dropdown = div.findElement(By.tagName("select"));
    List<WebElement> dropdownList = dropdown.findElements(By.tagName("option"));

    for (WebElement webElement : dropdownList) {
      dropdown.click();
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

      webElement.click();
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

      testTableDim(webElement);
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

    }
  }

  /**
   * This method returns the dimensions of the table
   *
   * @param table - the table
   */
  public void testTableDim(WebElement table) {

    int[] size = getDimensions(table.getText());

    // testing rows
    assertEquals(size[0],
        driver.findElements(By.xpath("/html/body/div[3]/table/tbody/tr")).size());

    // testing columns
    assertEquals(size[1],
        driver.findElements(By.xpath("/html/body/div[3]/table/tbody/tr[1]/th")).size());
  }

  /**
   * This method returns the dimensions of the given table
   *
   * @param table - name of table
   * @return array dimensions [row size, column size]
   */
  public int[] getDimensions(String table) {
    switch (table) {
      case "horoscopes":
        return new int[] {13, 2};
      case "tas":
        return new int[] {8, 3};
      case "ta_horoscope":
        return new int[] {8, 2};
      default:
        return new int[] {0, 0};
    }
  }

  @Test
  public void inputs() {

    WebElement loadButton = driver.findElement(By.id("loader"));
    loadButton.click();

    List<WebElement> inputs = driver.findElements(By.tagName("INPUT"));
    String text = "hi";
    inputs.get(0).click();
    inputs.get(0).sendKeys(text);
    assertEquals(text, inputs.get(0).getAttribute("value"));
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
  }

  /**
   * Tests that table content can be changed accurately
   */
  @Test
  public void changeTable () {
    WebElement content_div = driver.findElement(By.id("content"));
    List<WebElement> contents = content_div.findElements(By.tagName("TD"));
    for (WebElement i : contents) {
      i.click();
      i.clear();
      i.sendKeys("1");
    }
    for (WebElement i : contents) {
      assertEquals("1", i.getText());
    }
  }

  @After
  public void quit () {
    driver.quit();
  }
}