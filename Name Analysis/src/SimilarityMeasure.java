import java.util.ArrayList;
import java.util.Collections;

public class SimilarityMeasure {
//this class computation
	static ArrayList<String> Name1 = new ArrayList<String>();
	static ArrayList<String> Name2 = new ArrayList<String>();

	public static double  computeTrigrams(String name1, String name2) {

		int length1 = name2.length() - 1;
		int length2 = name2.length() - 1;

		int count1 = 0;
		int count2 = 0;
		int common = 0;
		int totalCount = 0;
		
		double coefficient=0;

		for (int i = 0; (i + 2) <= length1; i++) {
			// String temp1="";
			StringBuilder tempA = new StringBuilder();

			// temp1=name1.charAt(i)+""+name1.charAt(i+1)+""+name2.charAt(i+2);
			name2.replaceAll(" ", "");
			char first=name2.charAt(i);
			char second=name2.charAt(i+1);
			char third=name2.charAt(i+2);
			
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
			//System.out.println(tempA);
			Name1.add(tempA.toString());

			count1++;
		}

		for (int i = 0; (i + 2) <= length2; i++) {
			// String temp1="";
						StringBuilder tempA = new StringBuilder();

						// temp1=name1.charAt(i)+""+name1.charAt(i+1)+""+name2.charAt(i+2);
						name2.replaceAll(" ", "");
						char first=name2.charAt(i);
						char second=name2.charAt(i+1);
						char third=name2.charAt(i+2);
						
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
						//System.out.println(tempA);
						Name2.add(tempA.toString());

						count2++;
		}
		totalCount = count1 + count2;
		System.out.println("Name1:"+count1+"Name2:"+count2);
		Collections.sort(Name1);
		Collections.sort(Name2);
		
		//compute shared n-grams
		
		
		
		System.out.println("Common trigrams:"+common+"Total trigrams:"+totalCount);
		coefficient=calculateDice(totalCount, common,Name1,Name2);
		System.out.println("Dice value is:"+coefficient);
		return coefficient;
	}

	//calculate dice coefficient 
	static double calculateDice(int totalCount, int common,ArrayList<String> Name1, ArrayList<String> Name2) {
		for(int i=0;i<Name1.size();i++){
			for(int j=0;j<Name2.size();j++){
				//System.out.println("Name 1" + Name1.get(i));
				//System.out.println("Name 2" + Name2.get(j));
				if(Name2.get(j).equals(Name1.get(i))){
					//System.out.println("Name 1" + Name1.get(i));
					//System.out.println("Name 2" + Name2.get(j));
					common++;
					Name2.remove(j);
					break;
					
					
				}	
			}
			
		}
		
		
		double value=0;
		
		value=2* common/(double)totalCount;
		return value;
	}
	/*
	 * public static void main(String[] args) { String test1="Josephaine";
	 * String test2="Nathan"; SimilarityMeasure.computeDice(test1,test2); }
	 */

}
