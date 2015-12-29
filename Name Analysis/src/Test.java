import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;





public class Test {
	/**
	 * 
	 * 
	 * @param filename
	 * @return rows    
	 * 		   read each file and return a row
	 * 
	 **/

	private ArrayList<Row> readFile(String filename) throws FileNotFoundException{

		Scanner sc = new Scanner(new File(filename));
		sc.nextLine();
		ArrayList<Row> rows = new ArrayList<Row>();

		while(sc.hasNextLine()){
			String[] r = sc.nextLine().split(",");
			rows.add(new Row(r));
		}

		sc.close();

		return rows;
	}
	/**
	 * 
	 * 
	 * @param test
	 * @param training
	 * @param country
	 * @return index of class label in country[]   
	 * 		   run a test sample with the training dataset
	 * 
	 **/

	public int runTest(Input test, ArrayList<Row> training, String[] country){
		int count=0;

		for(Row r : training){
			count++;
			
//			if(count==200){
//				break;
//			}
			System.out.println("here");
			double diceValue=SimilarityMeasure.computeTrigrams(r.name,test.targetName);
			System.out.println("Dice value is computed");
			test.add(diceValue,r.name,r.country);
			System.out.println("Name " + r.name);
			System.out.println("Test name " + test.targetName);
		}


		test.findNearestNeighbors();
		System.out.println("Found nearest neighbors");
		return test.findNeighbor(country);
	}
	/**
	 * 
	 * 
	 * @param result
	 * @param filename 
	 * 		   write the results into a new file
	 * 
	 **/

	private void writeResult(ArrayList<Input> result, String filename) throws IOException{

		File file = new File(filename);

		if(!file.exists()){
			file.createNewFile();
		}

		PrintWriter out = new PrintWriter(file);
		out.println("input,result,expected");
		//result
		for(Input test : result){
			String inputName = test.targetName;
			String inputCountryForVerification = test.row.country;
			String resultingCountry = test.result;
			out.println(inputName+","+resultingCountry+","+inputCountryForVerification);
		}

		out.close();

	}
	/**
	 * @param k   
	 * 		   predict the country or origin depending on the value of k
	 * 
	 **/

	public void predict(int k) throws IOException{
		//values
		//list of countries considered
		String[] country={"Ireland","Netherlands","Poland"};


		File f = new File("train.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		// Test and training datasets input given here
		ArrayList<Row> testData= readFile("testfirst.csv");
		ArrayList<Row> trainingData= readFile("trainfirst.csv");

		for (Row r : testData) {
			System.out.println(r.name);
		}

		for (Row r : trainingData) {
			System.out.println(r.name);
		}



		ArrayList<Input> result = new ArrayList<Input>();

		int count = 0;

		for(Row r : testData) {
			count++;

//			if(count==200){
//				break;
//			}

			Input test = new Input(r.name,k);
			test.setRow(r);
			int index = runTest(test, trainingData, country);
			System.out.println("Found country");
			test.setResult(country[index]);
			result.add(test);

			bw.write(test.getResult());
			bw.write(",");
			String [] values = test.getRow();
			bw.write(values[0]);
			bw.write(",");
			bw.write(values[1]);
			bw.write(",");
			bw.write(values[2]);
			bw.write("$");
		}

		writeResult(result, "result.csv");

		System.out.println("--------done learning-------");


	}
	/**
	 * main method
	 * 
	 **/

	public static void main(String[] args) throws IOException {
		FileWriter file = new FileWriter("analysisFirst.csv",true);
		//file.write("trainingsize,time,k,accuracy");
		//run the first time for the first row;commented for all the other runs


		long start = System.currentTimeMillis();
		
		//change the value of k manually
		int k=5;
		Test t = new Test();
		t.predict(k);
		long finish=(System.currentTimeMillis() - start)/1000 ;

		double accuracy=0;
		int expected=0;
		int total=0;;

		//file to write all the results for each run
		Scanner sc = new Scanner(new File("result.csv"));
		sc.nextLine();

		while(sc.hasNextLine()){
			String[] r = sc.nextLine().split(",");
			if(r[1].equals(r[2])){
				expected++;
			}
			total++;

		}
		//compute the accuracy by comparing the predicted and expected country
		//and write to the file 
		accuracy=(expected/(double)total) * 100 ;
		file.write("200");
		file.write(",");
		file.write(String.valueOf(finish));
		file.write(",");
		file.write(String.valueOf(k));
		file.write(",");
		file.write(String.valueOf(accuracy));
		file.write("\n");
		file.close();

	}


//	file.write();



}


