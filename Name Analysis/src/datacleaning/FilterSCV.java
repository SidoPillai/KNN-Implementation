package datacleaning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FilterSCV {
	
	public static String testFile="test.csv";
	public static String trainFile="train.csv";
	public String header;

	private ArrayList<Row> readFile(String filename) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(filename));
		this.header = sc.nextLine();
		ArrayList<Row> rows = new ArrayList<Row>();
		
		while(sc.hasNextLine()){
			String[] r = sc.nextLine().split(",");
			rows.add(new Row(r));
		}
		
		sc.close();
		
		return rows;
	}
	
	
	private void writeFile(String filename, ArrayList<Row> data) throws IOException{
		
		File file = new File(filename);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		PrintWriter out = new PrintWriter(file);
		out.println(header);
		for(Row r : data){
			out.println(r.getCSV());
		}
		
		out.close();
		
	}
	
	private void splitIt(ArrayList<Row> rows, double training) throws IOException{
		
		int limit = (int) (rows.size()*(training));
		
		ArrayList<Row> train = new ArrayList<Row>();
		ArrayList<Row> test = new ArrayList<Row>();
		
		for(int i=0;i<limit;i++){
			train.add(rows.get(i));
		}
		
		for(int i=limit;i<rows.size();i++){
			test.add(rows.get(i));
		}
		
		writeFile(testFile, test);
		writeFile(trainFile, train);
	}
	
	public void algo(String filename, double training) throws IOException{
		ArrayList<Row> rows = readFile(filename);
		
		Collections.shuffle(rows);
		
		splitIt(rows, training);
	}
	
	
	public static void main(String[] args) throws IOException {
		String filename= "Name-Merge.csv";
		double training = 0.8;
		
		FilterSCV obj = new FilterSCV();
		
		obj.algo(filename, training);
		
	}

}
