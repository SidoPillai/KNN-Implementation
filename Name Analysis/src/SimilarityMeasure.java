import java.util.ArrayList;
import java.util.Collections;

public class SimilarityMeasure {
	//list of trigrams for each string
	static ArrayList<String> Name1 = new ArrayList<String>();
	static ArrayList<String> Name2 = new ArrayList<String>();
	/**
	 * 
	 * 
	 * @param name1
	 * @param name2
	 * @return coefficient  
	 * 
	 *            Compute the trigrams of two strings 
	 * 
	 * 
	 **/
	
	public static double  computeTrigrams(String name1, String name2) {

		int length1 = name1.length() - 1;
		int length2 = name2.length() - 1;

		int count1 = 0;
		int count2 = 0;
		int common = 0;
		int totalCount = 0;

		double coefficient=0;

		for (int i = 0; (i + 2) <= length1; i++) {
			StringBuilder tempA = new StringBuilder();

			name1.replaceAll(" ", "");
			char first=name1.charAt(i);
			char second=name1.charAt(i+1);
			char third=name1.charAt(i+2);

			//substituting spaces
			if( first==' '){
				first='_';

			}
			if(second==' '){
				second='_';

			} 
			if(third==' '){
				third='_';

			}


			tempA.append(first + "" + second + ""
					+ third);
			
			if(Name1.add(tempA.toString())) count1++;

		}

		for (int i = 0; (i + 2) <= length2; i++) {

			StringBuilder tempA = new StringBuilder();
			name2.replaceAll(" ", "");
			char first=name2.charAt(i);
			char second=name2.charAt(i+1);
			char third=name2.charAt(i+2);

			//substituting spaces
			if( first==' '){
				first='_';

			}
			if(second==' '){
				second='_';

			} 
			if(third==' '){
				third='_';

			}


			tempA.append(first + "" + second + ""
					+ third);
		
			if(Name2.add(tempA.toString())) count2++;

		}
		totalCount = Name1.size() + Name2.size();

		//sort the list of trigrams
		Collections.sort(Name1);
		Collections.sort(Name2);

		//compute shared n-grams


		//calculate the dice's coefficient
		coefficient=calculateDice(totalCount,Name1,Name2);
		return coefficient;
	}
	
	/**
	 * @param totalCount
	 * @param Name1
	 * @param Name2
	 * @return value   
	 * 
	 *            Calculate the dice's coefficient of two strings 
	 **/
	
	static double calculateDice(int totalCount, 
			ArrayList<String> Name1, ArrayList<String> Name2) {

		int common = 0;
		double value;

		int index1 = 0, index2 = 0;

		while(index1 < Name1.size() && index2 < Name2.size()) {

				if(Name1.get(index1).equals(Name2.get(index2))) {
					common++;
					index1++;
					index2++;
				} else if (Name1.get(index1).compareTo(Name2.get(index2))<0) {
					index1++;
				} else {
					index2++;
				}
		}
		

		int size = Name1.size() + Name2.size();
		Name1.clear();
        Name2.clear();

		
		value = 0;

		value = (2 * common)/(double)totalCount;

		return value;
	}
	
}
