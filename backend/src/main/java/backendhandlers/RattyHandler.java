package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class RattyHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public RattyHandler(){

    scraper = new WebScraper();
    scraper.scrapeRatty();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {


    try {
      String menu = GSON.toJson(scraper.getRattyBreak())  +
          GSON.toJson(scraper.getRattyLunch())  + GSON.toJson(scraper.getRattyDin());
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
