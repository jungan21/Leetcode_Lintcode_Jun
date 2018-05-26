package Regex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVParser {

	public static void main(String[] args) {

		CSVParser parser = new CSVParser();
		parser.run();

	}

	public void run() {

		//String csvFile = "/Users/jungan/Downloads/output_machines_tags.csv";
		String csvFile = "/Users/jungan/Downloads/input_YP_Paygo_with_Only_bi4c_tag.csv";
		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ",";

		try {
			FileWriter writer = new FileWriter(
					"/Users/jungan/Downloads/output_machines_tags_processed.csv");
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] machine = line.split(cvsSplitBy);
			//	System.out.print(machine.length + "   ");
//				if ((line.contains("widget") || line.contains("repo")
//						|| line.contains("provision")
//						|| line.contains("backup") || line.contains("ehaasp")
//						|| line.contains("yp") || line.contains("prod") || machine.length == 1)
//						&& !line.contains("dev")
//						&& !line.contains("qa")
//						&& !line.contains("ys") && !line.contains("test"))
				
				for (int i = 0; i < machine.length; i++) {

					System.out.print(machine[i]);
					if(i != machine.length-1){
				     System.out.print(cvsSplitBy);
					}
					
				}
				System.out.println();
//				if ((line.contains("widget") || line.contains("repo")
//						|| line.contains("provision")
//						|| line.contains("backup") || line.contains("ehaasp")
//						|| line.contains("yp") || line.contains("prod") )
//						&& !line.contains("dev")
//						&& !line.contains("qa")
//						&& !line.contains("ys") && !line.contains("test") && machine.length == 1 && line.contains("paygobm") )  {
//				
//					for (int i = 0; i < machine.length; i++) {
//
//						System.out.print(machine[i]);
//					 // System.out.print(cvsSplitBy);
//					}
//
//					System.out.println();	
//				}
				

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

}