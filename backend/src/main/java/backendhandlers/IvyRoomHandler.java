package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class IvyRoomHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public IvyRoomHandler(){
    scraper = new WebScraper();
    scraper.scrapeIvyRoom();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> ivyMenus = new ArrayList<>();
      ivyMenus.add(scraper.getIvyRoomLunch());
      ivyMenus.add(scraper.getIvyRoomDin());

      return GSON.toJson(ivyMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}