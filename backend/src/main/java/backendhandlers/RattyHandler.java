package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Arrays;

public class RattyHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public RattyHandler() {
    scraper = new WebScraper();
    scraper.scrapeRatty();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> rattyMenus = new ArrayList<>();
      rattyMenus.add(scraper.getRattyBreak());
      rattyMenus.add(scraper.getRattyLunch());
      rattyMenus.add(scraper.getRattyDin());

      return GSON.toJson(rattyMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}