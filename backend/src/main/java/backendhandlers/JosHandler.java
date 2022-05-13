package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the JosHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class JosHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the JosHandler constructor. It initializes the WebScraper class and calls the
   * scrapeJos() method.
   */
  public JosHandler() {
    scraper = new WebScraper();
    scraper.scrapeJos();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> josMenus = new ArrayList<>();

      if (scraper.getJosDin() != null){
        josMenus.add(scraper.getJosDin());
      }

      return GSON.toJson(josMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
