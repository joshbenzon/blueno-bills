package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class VDubHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public VDubHandler(){

    scraper = new WebScraper();
    scraper.scrapeVDub();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {


    try {
      String menu = GSON.toJson(scraper.getvDubBreak())  +
          GSON.toJson(scraper.getvDubLunch())  + GSON.toJson(scraper.getvDubDin());
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
