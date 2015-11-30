
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Merge {

	public static void main(String[] args) throws IOException {

		final String NEW_LINE_SEPARATOR = "\n";
		final String FILE_HEADER = "fullname,gender,country";
		ArrayList<Person> person = new ArrayList<Person>();
		for(int i=1;i<=42;i++){
			File csvFile = new File("Ireland"+i+".csv");
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = null;

			int count = 0;

			try {
				while ((line = br.readLine()) != null) {
					count++;
					// use comma as separator
					String[] cols = line.split(",");

					if(cols[6].isEmpty()||cols[6]==null){
						person.add(new Person(cols[1], cols[2], "Ireland"));
					}
					else{
						person.add(new Person(cols[1], cols[2], cols[6]));
					}
					if (cols[17].isEmpty() || cols[17] == null || cols[18].isEmpty() || cols[18] == null) continue;
					person.add(new Person(cols[17], "M", cols[6]));
					person.add(new Person(cols[18], "F", cols[6]));
				}
			}  catch (Exception e) {
				System.out.println("Wohoo " + count);
			}
			FileWriter fileWriter = null;

			try {
				fileWriter = new FileWriter("Ireland-Merge.csv");
				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				//Write a new student object list to the CSV file
				for (Person p : person) {
					fileWriter.append(p.fullName);
					fileWriter.append(",");
					fileWriter.append(p.gender);
					fileWriter.append(",");
					fileWriter.append(p.country);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
				System.out.println("CSV file was created successfully !!!");
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
					e.printStackTrace();
				}

			}

		}

	}
}