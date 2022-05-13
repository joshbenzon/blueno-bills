package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This is the RattyHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class RattyHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  /**
   * This is the RattyHandler constructor. It initializes the WebScraper class and calls the
   * scrapeRatty() method.
   */
  public RattyHandler(){

    scraper = new WebScraper();
    scraper.scrapeRatty();

  }

  /**
   * The handle method creates a JSON object from the scraped data.
   * @param request
   * @param response
   * @return String representing scraped data
   */
  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {

    try {
      String menu = GSON.toJson(scraper.getRattyBreak())  +
          GSON.toJson(scraper.getRattyLunch())  + GSON.toJson(scraper.getRattyDin());
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
