package twitetr;

import static spark.Spark.get;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public final class Controller {

	private String librisKey = "3EC754BFD25059FB97F31F5EC4B519E6";

	public static String render(Map<String, Object> model, String templatePath) {
		return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	public static void main(final String[] args) {
		XMLHandler handler = new XMLHandler();
	
		staticFileLocation("/public"); // CSS och bilder. Eventuellt JS-filer

//		get route för startsidan, d.v.s. "localhost:4567/"
		get("/", (request, response) -> {
			
			Map<String, Object> model = new HashMap<>();
			
			model.put("sentenceChecked", "false"); // initialiserar sentenceChecked till false så att correction-box på html-sidan inte visas förrän en mening skickats till Libris-API
			model.put("librisError", "false"); 

			return new ModelAndView(model, "templates/index.html");
		}, new VelocityTemplateEngine());

		get("/tweet", (request, response) -> {
			
			Map<String, Object> model = new HashMap<>();

			model.put("sentenceChecked", "false"); // initialiserar sentenceChecked till false så att correction-box på html-sidan inte visas förrän en mening skickats till Libris-API
			model.put("librisError", "false"); 
			
			String userTweet = request.queryParams("userTweet"); //hämtar och sparar användarens tweet-text

			if (userTweet != null && userTweet.length() <= 280) { //koden körs endast om användaren skickat med en sträng, samt att denne inte överstiger 280 karaktärer, då detta är max storlek tillåtet för tweets
				List<String> list = Arrays.asList(userTweet.split(" ")); //sätter in varje ord i användarens text i en array
				model.put("list", list); //skickar med det ursprungliga meddelandet till klienten
				
				ArrayList<Word> words = new ArrayList<Word>(); //en arraylista som lagrar användarens tweet i med förslag på rättstavning
				words = handler.parseXML(userTweet); //här skickas texten till libris-api 
				
				if(words.isEmpty()) { //om det vi skickade till libris inte ger något svar så har något gått fel
					model.put("librisError", "true");
				}	
				
				model.put("words", words); //arraylistan med rättstavning skickas till klitenten
				model.put("sentenceChecked", "true"); //endast när denna är true så visas correction-boxen
			}

			return new ModelAndView(model, "templates/index.html");
		}, new VelocityTemplateEngine());
		
		
		//route för api:ets dokumentation
		get("/api.html", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			return new ModelAndView(model, "templates/api.html");
		}, new VelocityTemplateEngine());


	}
}