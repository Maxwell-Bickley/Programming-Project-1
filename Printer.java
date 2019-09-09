/**
* This collects any given text from Scanner, compares it using regular expressions, filters out repeating patterns, and tallies every tim a unique pattern successfully matches the pattern type that
it is being compared to. Then, it stores them into a HashMap, can optionally extract and print the usernames and IP Addresses from the HashMap, and print the lines of code parsed along with the number of
unqiue IP Addresses and the number of unique unsernames.
*@author Max Bickley
*@version 1.0
* Programming Project One
* CS322 - Compiler Construction
* Spring 2019
*/

//As always, the file begans by importing the necessary functions.
import java.io.*;
import javax.swing.*;
import java.util.HashMap;

public class Scan {
	//As many variables are declared outside of the main method as prudent so that they can later be sent to Printerv3.
	public static HashMap<Integer, String> lineholder = new HashMap<Integer, String>();
	public static Integer Part = 0;
	public static Integer Command;
    public static void main (String[]args) { //The main method.
	    String line = null;
		//Searches for whatever file name is typed within the same folder as this program.
        String fileName = JOptionPane.showInputDialog("Input the name of the file you want to search for. Please include the file type as well"); // The name of the file to open.
		File file = new File(fileName);
		//Runs the program in accordance to the user's input or command.
		String Order =JOptionPane.showInputDialog("Input 1 if you want the system to output the IP adresses along with the standard output, 2 if you want the system to output the user names along with the standard output, or any other integer for the standard output");
		Command=Integer.parseInt(Order);
        //References the files that are contained in the target code but halts the program if the process cannot be completed.
        try {
            //Reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            //Wraps FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

			//Stores every part or line of the file that is read into an array so it can be transfered to Printerv3.
            while((line = bufferedReader.readLine()) != null) {
				lineholder.put(Part, line);
			    Part = Part + 1;
            }   
			Part = Part - 1; //Sets the Part variable to the amount of values within the array.
            bufferedReader.close();//Closes the file.       
        }
		//Terminates the program and informs the user if the file could not be located.
        catch(FileNotFoundException ex) {
            System.out.println("Could not locate file '" + file + "'");                
        }
		//Terminates the program and informs the user if the file could not be read.
        catch(IOException ex) {
            System.out.println("Could not read file '" + file + "'");                  
        }
		//Instantiates and references Printerv3. Effectively runs the Printerv3 program.
		Printer Printer = new Printer();
		Printer.main(args);
      }
	//Sends the necessary variables to Printerv3.
	public int sender()
	{	
		return Command;
	}
	public int sender2()
	{	
		return Part;
	}
	public HashMap<Integer,String> sender3()
	{
		return lineholder;
	}
}
