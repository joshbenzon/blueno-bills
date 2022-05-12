package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class BlueRoomHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public BlueRoomHandler() {
    scraper = new WebScraper();
    scraper.scrapeBlueRoom();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> blueMenus = new ArrayList<>();
      blueMenus.add(scraper.getBlueRoomBreak());
      blueMenus.add(scraper.getBlueRoomLunch());

      return GSON.toJson(blueMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}