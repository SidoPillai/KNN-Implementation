package trigram;
import java.util.HashMap;

public class Country {

	Integer count = 1;
	HashMap<String, Integer> country;
	
	Country() {
		country = new HashMap<String, Integer>();
	}

	void add(String key) {
		if (country.containsKey(key)) {
			country.put(key, country.get(key) + 1);
		} else {
			country.put(key, count);
		}
	}
}
