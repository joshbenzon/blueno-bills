package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This is the IvyRoomHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class IvyRoomHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();
  String menu;

  /**
   * This is the IvyRoomHandler constructor. It initializes the WebScraper class and calls the
   * scrapeIvyRoom() method.
   */
  public IvyRoomHandler(){

    scraper = new WebScraper();
    scraper.scrapeIvyRoom();

  }

  /**
   * The handle method creates a JSON object from the scraped data.
   * @param request
   * @param response
   * @return String representing scraped data
   */
  @Override
  public Object handle(Request request, Response response) throws IllegalArgumentException {

    try {
      if(scraper.getIvyRoomDin() == null){
        menu = GSON.toJson(scraper.getIvyRoomLunch());
      }else if(scraper.getIvyRoomLunch() == null) {
        menu = GSON.toJson(scraper.getIvyRoomDin());
      } else{
        menu = GSON.toJson(scraper.getIvyRoomLunch()) +
            GSON.toJson(scraper.getIvyRoomDin());
      }
      return menu;
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }

  }
}
