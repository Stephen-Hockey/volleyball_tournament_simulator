package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The FileHandler class serves the unique purpose of reading from the several text files
 * that the program uses.
 * 
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class FileHandler {
	
	/**
	 * Gets line i (0 indexed) from the file located at the given path.
	 * @param path the location of the text file to be read
	 * @param i the index of the desired line
	 * @return line i from the file given by path
	 */
	public String getLine(String path, int i) {
    	try {
    		InputStream in = getClass().getResourceAsStream(path); 
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            int count = 0;
            while(line != null) {
            	line = reader.readLine();
                if(count == i) {
            	   return line;
                }
                count++;
            } 
            in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    	return null;
    }
}
