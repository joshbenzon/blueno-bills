package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class VDubHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public VDubHandler() {
    scraper = new WebScraper();
    scraper.scrapeVDub();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> vdubMenus = new ArrayList<>();
      vdubMenus.add(scraper.getvDubBreak());
      vdubMenus.add(scraper.getvDubLunch());
      vdubMenus.add(scraper.getvDubDin());

      return GSON.toJson(vdubMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
