package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws IOException {

		System.out.println("Enter a name to find the closest match");
		Scanner sc = new Scanner(System.in);

		String name = sc.nextLine();
		String names[] = name.split(" ");

		int nameLength = names.length;

		if (nameLength == 2) {
			String firstName = names[0];
			String secondName = names[1];

			// check on firstName
			getOptimalMatch(firstName);

			// check on last name
		} else if (nameLength == 3) {
			// put condition for van der, de
		}


	}

	private static void getOptimalMatch(String firstName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("train.txt"));
		String elem = null;
		String con = null;
		String gen = null;
		//List<Match> matches = new ArrayList<Match>();
		List<String> matchedName = new ArrayList<String>();
		List<String> matchedCountry = new ArrayList<String>();
		List<String> matchedGender = new ArrayList<String>();

		while(br.readLine()!=null) {
			elem = br.readLine();
			String [] splits = getSplits(firstName);
			if (splits.length > 0) {
				// handle the delimiter
				con = br.readLine();
				// handle the delimiter
				gen = br.readLine();

				matchedName.add(firstName);
				matchedCountry.add(con);
				matchedGender.add(gen);
			}
		}

		if (matchedCountry.size() > 0) {
			System.out.println("Closest Match for " + firstName);
			System.out.println("Country " + matchedName.get(0));
			System.out.println("Gender " + matchedGender.get(0));
		} else {
			System.out.println("No closest match found!!!");
		}
	}

	private static String[] getSplits(String name) {

		String [] splits = null;
		List<String> splitList = new ArrayList<String>();
		int len = name.length();

		for (int i = 0; i < len-3; i++) {
			splitList.add(name.substring(i,i+3));
		}

		splits = new String[splitList.size()];

		splitList.toArray(splits);

		return splits;
	}

	private class Match {
		String name;
		String country;
		String gender;

		Match(String name, String country, String gender) {
			this.name = name;
			this.country = country;
			this.gender = gender;
		}

		String getName() {
			return name;
		}

		String getCountry() {
			return country;
		}

		String getGender() {
			return gender;
		}
	}
}
