package backendhandlers;

import com.google.gson.Gson;
import edu.brown.cs.student.main.WebScraping.WebScraper;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This is the JosHandler class. It converts the data from the WebScraper class to a JSON object
 * to be accessed by the frontend.
 */
public class JosHandler implements Route {

  WebScraper scraper;
  private static final Gson GSON = new Gson();

  /**
   * This is the JosHandler constructor. It initializes the WebScraper class and calls the
   * scrapeJos() method.
   */
  public JosHandler(){

    scraper = new WebScraper();
    scraper.scrapeJos();

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
      return GSON.toJson(scraper.getJosDin());
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
