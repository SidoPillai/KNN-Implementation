import java.util.ArrayList;
import java.util.Collections;

public class SimilarityMeasure {
	//this class computation
	static ArrayList<String> Name1 = new ArrayList<String>();
	static ArrayList<String> Name2 = new ArrayList<String>();

	public static double  computeTrigrams(String name1, String name2) {

		int length1 = name1.length() - 1;
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
			name1.replaceAll(" ", "");
			char first=name1.charAt(i);
			char second=name1.charAt(i+1);
			char third=name1.charAt(i+2);

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
			if(Name1.add(tempA.toString())) count1++;

			//			count1++;
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
			if(Name2.add(tempA.toString())) count2++;

			//			count2++;
		}
		totalCount = Name1.size() + Name2.size();
//		System.out.println("Name1:"+count1+"Name2:"+count2);
		Collections.sort(Name1);
		Collections.sort(Name2);

		//compute shared n-grams



//		System.out.println("Total trigrams:"+totalCount);
		coefficient=calculateDice(totalCount,Name1,Name2);
		System.out.println("Dice value is:"+coefficient);
		return coefficient;
	}

	//calculate dice coefficient 
	static double calculateDice(int totalCount, 
			ArrayList<String> Name1, ArrayList<String> Name2) {

//		int size1 = Name1.size();
//		int size2 = Name2.size();
		int common = 0;
		double value;

//		int limit;

//		if(size1>size2) limit = size1;
//		else limit = size2;
		int index1 = 0, index2 = 0;

		while(index1 < Name1.size() && index2 < Name2.size()) {

//			if() {
				if(Name1.get(index1).equals(Name2.get(index2))) {
					common++;
					index1++;
					index2++;
				} else if (Name1.get(index1).compareTo(Name2.get(index2))<0) {
					index1++;
				} else {
					index2++;
				}
//			}
		}
		
		System.out.println("Out of the loop");
		System.out.println("Total Count " + totalCount);
		System.out.println("Name 1 size " + Name1.size());
		System.out.println("Name 2 size " + Name2.size());
		int size = Name1.size() + Name2.size();
		System.out.println("From Values" + size);
		System.out.println("Common:"+common);
		
		value = 0;

		value = (2 * common)/(double)totalCount;

		//		common = 0;
		//		int sliderName1 = 0;
		//		int sliderName2 = 0;
		//
		//		int currentPosName1;
		//		int currentPosName2 = 0;
		//		int i = 0, j = 0;
		//		
		//		for(; i < Name1.size(); i++) {
		//			
		//			currentPosName1 = i;
		//			if (sliderName2 == currentPosName2) {
		//				j = sliderName2;
		//			}
		//			for(; j < Name2.size(); j++) {
		//				currentPosName2 = j;
		//				//System.out.println("Name 1" + Name1.get(i));
		//				//System.out.println("Name 2" + Name2.get(j));
		//				if(Name2.get(j).equals(Name1.get(i))){
		//					//System.out.println("Name 1" + Name1.get(i));
		//					//System.out.println("Name 2" + Name2.get(j));
		//					common++;
		//					//Name2.remove(j);
		//					//					i++;
		//					//					j++;
		////					break;
		//					i++;
		////					j++;
		//				} else if(Name1.get(i).compareTo(Name2.get(j))<0) {
		////										i++;
		//										sliderName2 = currentPosName2;
		//					break;
		//				} else {
		////										j++;
		//					continue;
		//				}
		//			}
		//
		//		}


//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		double value=0;
//
//		value=(2* common)/(double)totalCount;
		return value;
	}
	/*
	  public static void main(String[] args) { String test1="Josephaine";
	  String test2="Nathan"; SimilarityMeasure.computeTrigrams(test1,test2); }
	 */

}
