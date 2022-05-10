package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class JosHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public JosHandler(){

    scraper = new WebScraper();
    scraper.scrapeJos();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {
    try {
      return GSON.toJson(scraper.getJosDin());
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
