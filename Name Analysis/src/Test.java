import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;





public class Test {

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


	public int runTest(Input test, ArrayList<Row> training, String[] country){
		int count=0;

		for(Row r : training){

			count++;
			if(count==1000){
				break;
			}
			System.out.println("here");
			double diceValue=SimilarityMeasure.computeTrigrams(r.name,test.targetName);
			System.out.println("Dice value is computed");
			test.add(diceValue,r.name,r.country);
			System.out.println("Name " + r.name);
			System.out.println("Test name " + test.targetName);
			//			try {
			//				Thread.sleep(1000);
			//			} catch (InterruptedException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
		}


		test.findNearestNeighbors();
		System.out.println("Found nearest neighbors");
		return test.findNeighbor(country);
	}

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

	public void predict(int k) throws IOException{
		//values
		String testCountry="";
		String trainingName="Nate";
		String trainingCountry="Ireland";
		String votedCountry="";
		
		String[] country={"Ireland","Netherlands","Poland"};


		File f = new File("train.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		//algo starts
		ArrayList<Row> testData= readFile("testfirst.csv");
		ArrayList<Row> trainigData= readFile("trainfirst.csv");

		for (Row r : testData) {
			System.out.println(r.name);
		}

		for (Row r : trainigData) {
			System.out.println(r.name);
		}

		System.out.println("Size of test data " + testData.size());
		System.out.println("Size of train data " + trainigData.size());

		//		try {
		//			Thread.sleep(10000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		ArrayList<Input> result = new ArrayList<Input>();

		int count = 0;

		for(Row r : testData) {
			count++;

			if(count==1000){
				break;
			}

			Input test = new Input(r.name,k);
			test.setRow(r);
			int index = runTest(test, trainigData, country);
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

		// read from the csv sequentially

		// Create a list of Input

		// Keep adding in the list.

		// Data is trained once all the input data is traversed

		// train with 26 k 




	}

	public static void main(String[] args) throws IOException {
		FileWriter file = new FileWriter("analysis.csv",true);
		//file.write("trainingsize,time,k,accuracy");
		//file.write("\n");

		long start = System.currentTimeMillis();
		int k=1;
		Test t = new Test();
		t.predict(k);
		long finish=(System.currentTimeMillis() - start)/1000 ;

		double accuracy=0;
		int expected=0;
		int total=0;;

		Scanner sc = new Scanner(new File("result.csv"));
		sc.nextLine();

		while(sc.hasNextLine()){
			String[] r = sc.nextLine().split(",");
			if(r[1].equals(r[2])){
				expected++;
			}
			total++;

		}
		accuracy=(expected/(double)total) * 100 ;
		file.write("1000");
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


