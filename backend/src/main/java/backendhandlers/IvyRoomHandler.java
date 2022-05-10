package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class IvyRoomHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public IvyRoomHandler(){

    scraper = new WebScraper();
    scraper.scrapeIvyRoom();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {


    try {
      String menu = GSON.toJson(scraper.getIvyRoomLunch())  +
          GSON.toJson(scraper.getIvyRoomDin()) ;
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
