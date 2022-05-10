package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

public class BlueRoomHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  public BlueRoomHandler(){

    scraper = new WebScraper();
    scraper.scrapeBlueRoom();

  }

  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {


    try {
      String menu = GSON.toJson(scraper.getBlueRoomBreak())  +
          GSON.toJson(scraper.getBlueRoomLunch()) ;
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
