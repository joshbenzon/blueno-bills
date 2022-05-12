package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class JosHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public JosHandler() {
    scraper = new WebScraper();
    scraper.scrapeJos();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> josMenus = new ArrayList<>();
      josMenus.add(scraper.getJosDin());

      return GSON.toJson(josMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
