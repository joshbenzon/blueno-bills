package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the RattyHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class RattyHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the RattyHandler constructor. It initializes the WebScraper class and calls the
   * scrapeRatty() method.
   */
  public RattyHandler() {
    scraper = new WebScraper();
    scraper.scrapeRatty();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> rattyMenus = new ArrayList<>();

      if (scraper.getRattyBreak() != null){
        rattyMenus.add(scraper.getRattyBreak());
      }

      if (scraper.getRattyLunch() != null){
        rattyMenus.add(scraper.getRattyLunch());
      }

      if (scraper.getRattyDin() != null){
        rattyMenus.add(scraper.getRattyDin());
      }

      return GSON.toJson(rattyMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
