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
		
		
		for(Row r : training){
			int count=0;
			count++;
			if(count==2){
				break;
			}
			System.out.println("here");
			double diceValue=SimilarityMeasure.computeTrigrams(r.name,test.targetName);
			System.out.println("Dice value is computed");
			test.add(diceValue,r.name,r.country);
			
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
	
	public void predict() throws IOException{
		//values
		String testCountry="";
		String trainingName="Nate";
		String trainingCountry="Ireland";
		String votedCountry="";
		int k=1;
		String[] country={"Ireland","Netherlands","Poland"};
		
		
		File f = new File("train.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		//algo starts
		ArrayList<Row> testData= readFile("test.csv");
		ArrayList<Row> trainigData= readFile("train.csv");
		
		ArrayList<Input> result = new ArrayList<Input>();
		
		for(Row r : testData){
			int count=0;
			count++;
			if(count==2){
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
		
//		writeResult(result, "result.csv");
		
		System.out.println("--------done learning-------");
		
		// read from the csv sequentially
		
		// Create a list of Input
		
		// Keep adding in the list.
		
		// Data is trained once all the input data is traversed
		
		// train with 26 k 
		
		
		

	}
	
	public static void main(String[] args) throws IOException {
		Test t = new Test();
		t.predict();
		
	}

}
