package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateLines {

	public static void main(String[] args) {
		removeDuplicateLines();
	}

	public static void removeDuplicateLines() {
		Set<String> lines = new HashSet<String>();
		try {
			// Open the file that is the first
			// command line parameter
			BufferedReader br = new BufferedReader(new FileReader("./file/input.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
				//System.out.println(line);
			}

			// Close the input stream
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

			System.out.println(lines);
	}

}
