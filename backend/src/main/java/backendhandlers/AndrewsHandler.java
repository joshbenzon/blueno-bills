package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class AndrewsHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public AndrewsHandler(){

    scraper = new WebScraper();
    scraper.scrapeAndrews();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {


    try {
      String menu = GSON.toJson(scraper.getAndrewsLunch())  +
          GSON.toJson(scraper.getAndrewsDin()) ;
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
