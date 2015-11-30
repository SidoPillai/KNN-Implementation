package datacleaning;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class ExcelToCSV {
	public static void main(String[] args) throws IOException {

		//		String querySequence = "TTTT";
		//		String dnaSequence = "TTTTTT";
		//		Pattern seq = Pattern.compile(querySequence);
		//        Matcher matcher = seq.matcher(dnaSequence);
		//		
		//        int count = 0;
		//		while(matcher.find()) {
		////			combiner.add(sequenceID, ONE);
		//			count++;
		////			matcher.start(matcher.start()+1);
		////			matcher.group(matcher.start()+1);
		////			matcher.find(matcher.start());
		//			
		//        }
		//		
		//		System.out.println("Count " + count);

		try
		{
			//File to store data in form of CSV
		for(int k=1;k<=42;k++){
			//int k=39;
			File f = new File("Ireland"+k+".csv");
			

			OutputStream os = (OutputStream)new FileOutputStream(f);
			String encoding = "UTF8";
			OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			BufferedWriter bw = new BufferedWriter(osw);

			//Excel document to be imported
			//String path="/Users/Shashank/Documents/CapstoneProject/Dataset/";
			String filename = "Ireland"+k+".xls";
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(filename),ws);

			// Gets the sheets from workbook
			for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
			{
				Sheet s = w.getSheet(sheet);

				bw.write(s.getName());
				bw.newLine();

				Cell[] row = null;

				// Gets the cells from sheet
				for (int i = 0 ; i < s.getRows() ; i++)
				{
					row = s.getRow(i);

					if (row.length > 0)
					{
						bw.write(row[0].getContents());
						for (int j = 1; j < row.length; j++)
						{
							bw.write(',');
							bw.write(row[j].getContents());
						}
					}
					bw.newLine();
				}
			}
		
			bw.flush();
			bw.close();
		}
		}
		catch (UnsupportedEncodingException e)
		{
			System.err.println(e.toString());
		}
		catch (IOException e)
		{
			System.err.println(e.toString());
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
		}
	}
	
}
