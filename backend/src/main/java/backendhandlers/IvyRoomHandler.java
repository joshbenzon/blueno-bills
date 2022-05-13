package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the IvyRoomHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class IvyRoomHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the IvyRoomHandler constructor. It initializes the WebScraper class and calls the
   * scrapeIvyRoom() method.
   */
  public IvyRoomHandler(){
    scraper = new WebScraper();
    scraper.scrapeIvyRoom();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> ivyMenus = new ArrayList<>();

      if (scraper.getIvyRoomLunch() != null) {
        ivyMenus.add(scraper.getIvyRoomLunch());
      }

      if (scraper.getIvyRoomDin() != null) {
        ivyMenus.add(scraper.getIvyRoomDin());
      }

      return GSON.toJson(ivyMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}