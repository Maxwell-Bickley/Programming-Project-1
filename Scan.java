/**
* This class reads any given test file and sends it to Printer, the class that outputs all  the equested information.
* @Author Max Bickley
* @Programming Project One
* CS322 - Compiler Construction
* Spring 2019
*/

//Note - This is the main file. Attempting to run Printer will result in an error. However, this file will reference Printer.
//As always, the file starts by importing the necessary functions.
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Scan {
        public static HashMap<Integer, String> lineholder = new HashMap<Integer, String>();
        public static Integer Part = 0;
        public static Integer Command;
        public static void main (String[]args) {//The main method.
                String line = null;
                //Searches for whatever file you entered along whatever file path you requested. Note that the file path and the file type are necessary inputs.
                System.out.println("Enter the complete name of the file that you want scanned including the file type.");
                Scanner file  = new Scanner(System.in);
                String FilePath = file.nextLine();
                File FileName = new File(FilePath);
                //Runs the program in accordance to the user's input command.
                System.out.println("Enter 1 to include a list of IP Addresses, 2 to include a list of usernames, and any other integer for the default output");
                Scanner request = new Scanner (System.in);
                String Order = request.nextLine();
                Command = Integer.parseInt(Order);
                //Reads the specified program but halts the application if the program cannot be found or read.
                try {
                        //Reads text files in the default encoding.
                        FileReader fileread = new FileReader(FileName);

                        //Wraps the FileReader in Bufferedreader.
                        BufferedReader bufferedread = new BufferedReader(fileread);

                        //Stores every part or line of a file that is read into an array so it can be transfered to Printer.
                        while((line = bufferedread.readLine()) != null) {
                                lineholder.put(Part,line);
                                Part = Part + 1;
                        }
                        Part = Part - 1; //Sets the part variable to the amount of values within the array.
                        bufferedread.close(); //Closes the file.
                }
                //Terminates the program and informs the user if the fule could not be located.
                catch(FileNotFoundException ex) {
                        System.out.println("Could not locate file " + FileName + "");
                }
                //Terminates the program and informs the user if the file could not be read.
                catch (IOException ex) {
                        System.out.println("Could not read file " + FileName + "");
                }
                //Instantiates and references Printer. This effectively runs the Printer program.
                Printer Printer = new Printer();
                Printer.main(args);
        }
        //Sends the necessary variables to Printer.
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
