package trigram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Trigram {

	public static void main(String[] args) {

		System.out.println("Enter the name : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		sc.close();


		ArrayList<String> allNames = new ArrayList<String>();
		allNames.add("Georges");
		allNames.add("Johannes");
		allNames.add("Gerardus");
		allNames.add("Vincentius");
		allNames.add("Anna");
		allNames.add("Anna");
		allNames.add("Anna");
		allNames.add("Leonard");
		allNames.add("Clara");
		allNames.add("Tona");
		allNames.add("Jan");
		allNames.add("Male");
		allNames.add("Adrianus");
		allNames.add("John");
		allNames.add("Cornelia");


		ArrayList<String> countries = new ArrayList<String>();
		countries.add("Netherlands");
		countries.add("Netherlands");
		countries.add("Netherlands");
		countries.add("Poland");
		countries.add("Netherlands");
		countries.add("Netherlands");
		countries.add("Ireland");
		countries.add("Netherlands");
		countries.add("Netherlands");
		countries.add("Ireland");
		countries.add("Netherlands");
		countries.add("Ireland");
		countries.add("Netherlands");

		ArrayList<String> trigrams = new ArrayList<String>();

		for(int i = 0; i < name.length()-2; i++) {
			trigrams.add(name.substring(i,i+3));
		}

		for (String s: trigrams){
			System.out.println(s);
		}

		int index = 0;
		HashMap<String, Integer> score = new HashMap<String, Integer>(trigrams.size());
		HashMap<String, Country> countryMap = new HashMap<String, Country>();
		Integer startValue = 1;

		for (String tri : trigrams) {
			for (String str : allNames) {
				if(str.contains(tri)) {
					if (!score.containsKey(tri)) {
						score.put(tri, startValue);
						Country y = new Country();
						y.add(countries.get(index));
						countryMap.put(tri, y);
					} else {
						score.put(tri, score.get(tri) + 1);
					}
				}
			}
			index++;
		}

		printValues(score, countryMap);
	}

	private static void printValues(HashMap<String, Integer> score, HashMap<String, Country> countryMap) {

		Set<String> keyScore = score.keySet();
		Set<String> keyCountry = countryMap.keySet();

		TreeMap<String, Integer> f = new TreeMap<String, Integer>();


		for (String str : keyScore) {
			System.out.println("-------------------");
			Country y = new Country();
			y = countryMap.get(str);
			Set<String> countryKeys = y.country.keySet();
			System.out.println("Trigram " + str + " Score " + score.get(str) + " Country " + y.country + " Count " + y.count);
			System.out.println("-------------------");
			for (String s : countryKeys) {
				f.put(s, y.country.get(s));
			}
			
			System.out.println("Nearest Neighbour predicted ");
			System.out.println(f.values());
			System.out.println(f.keySet());
		}
	}
}
