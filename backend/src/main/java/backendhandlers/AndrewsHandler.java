package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class AndrewsHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public AndrewsHandler() {
    scraper = new WebScraper();
    scraper.scrapeAndrews();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> andrewsMenus = new ArrayList<>();
      andrewsMenus.add(scraper.getAndrewsLunch());
      andrewsMenus.add(scraper.getAndrewsDin());

      return GSON.toJson(andrewsMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
