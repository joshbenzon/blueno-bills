package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * This is the VDubHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class VDubHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  /**
   * This is the VDubHandler constructor. It initializes the WebScraper class and calls the
   * scrapeVDub() method.
   */
  public VDubHandler() {
    scraper = new WebScraper();
    scraper.scrapeVDub();
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      ArrayList<ArrayList<String[]>> vdubMenus = new ArrayList<>();

      if (scraper.getvDubBreak() != null){
        vdubMenus.add(scraper.getvDubBreak());
      }

      if (scraper.getvDubLunch() != null){
        vdubMenus.add(scraper.getvDubLunch());
      }

      if (scraper.getvDubDin() != null){
        vdubMenus.add(scraper.getvDubDin());
      }

      return GSON.toJson(vdubMenus);

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
