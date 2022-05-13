package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the AndrewsHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class AndrewsHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the AndrewsHandler constructor. It initializes the WebScraper class and calls the
   * scrapeAndrews() method.
   */
  public AndrewsHandler() {
    scraper = new WebScraper();
    scraper.scrapeAndrews();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> andrewsMenus = new ArrayList<>();

      if (scraper.getAndrewsLunch() != null){
        andrewsMenus.add(scraper.getAndrewsLunch());
      }

      if (scraper.getAndrewsDin() != null){
        andrewsMenus.add(scraper.getAndrewsDin());
      }

      return GSON.toJson(andrewsMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
