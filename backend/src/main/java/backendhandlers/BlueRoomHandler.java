package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the BlueRoomHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class BlueRoomHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the BlueRoomHandler constructor. It initializes the WebScraper class and calls the
   * scrapeBlueRoom() method.
   */
  public BlueRoomHandler() {
    scraper = new WebScraper();
    scraper.scrapeBlueRoom();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> blueMenus = new ArrayList<>();

      if (scraper.getBlueRoomBreak() != null){
        blueMenus.add(scraper.getBlueRoomBreak());
      }

      if (scraper.getBlueRoomLunch() != null){
        blueMenus.add(scraper.getBlueRoomLunch());
      }

      return GSON.toJson(blueMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}