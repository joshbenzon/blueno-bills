package backendhandlers;

import WebScraping.WebScraper;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Arrays;

public class RattyHandler implements Route {
  private static final Gson GSON = new Gson();
  WebScraper scraper;

  public RattyHandler() {
    scraper = new WebScraper();
    scraper.scrapeRatty();

    System.out.println(Arrays.toString(scraper.getRattyBreak().get(0)) + " BREAKFAST");
    System.out.println(Arrays.toString(scraper.getRattyLunch().get(0)) + " LUNCH");
    System.out.println(Arrays.toString(scraper.getRattyDin().get(0)) + " DINNER");
  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
//      System.out.println(scraper.getRattyBreak() + " BREAKFAST");
//      System.out.println(scraper.getRattyLunch() + " LUNCH");
//      System.out.println(scraper.getRattyDin() + " DINNER");

      return GSON.toJson(scraper.getRattyBreak()) +
          GSON.toJson(scraper.getRattyLunch()) + GSON.toJson(scraper.getRattyDin());

    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
