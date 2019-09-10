/**
* This collects any given text from Scan, compares it using regular expressions, filters out repeating patterns, and tallies every time a unique pattern successfully matches the pattern$
it is being compared to. Then, it stores them into a HashMap, can optionally extract and print the usernames and IP Addresses from the HashMap, and print the lines of code parsed along wit$
unqiue IP Addresses and the number of unique unsernames.
*@author Max Bickley
*@version 1.0
* Programming Project One
* CS322 - Compiler Construction
* Spring 2019
*/

//As always, the file begins by importing the necessary functions.
import java.io.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.regex.*;

public class Printer
{
        //Several variables were declared outside the main method for added flexibility in sharing the output with other files.
        public static Integer innerloop1 = 0;
        public static Integer innerloop2 = 0;
        public static Integer increment1;
        public static Integer increment2;
        public static String convert1;
        public static String convert2;
        public static String placeholderA;
        public static String placeholderB;
        public static HashMap<Integer, String> lines1 = new HashMap<Integer, String>();
        public static HashMap<Integer, String> lines2 = new HashMap<Integer, String>();
        public static HashMap<String, Integer> amount1 = new HashMap<String, Integer>();
        public static HashMap<String, Integer> amount2 = new HashMap<String, Integer>();
        public static void main(String[]args)
        {
                Scan scan = new Scan(); //Declares Scanner so that some of its variables may be sent to this class.
                //Collects variables from Scanner.
                Integer Command = scan.sender();
                Integer Part = scan.sender2();
                HashMap<Integer, String> lineholder = scan.sender3();
                //The outermost loop. This loop runs through each line of code and compares it to two patterns.
                for (int outerloop = 0; outerloop <= Part; outerloop++)
                {
                        //finds any pattern that matches 4 number groups with 1 to 3 digits in each group.
                        Pattern patternA = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
                        Matcher matchA = patternA.matcher(lineholder.get(outerloop));
                        //The middle loop. Places every pattern match on a given line into a placeholder array for later.
                        while (matchA.find())
                        {
                                //Transfers the pattern to two different HashMaps. Both are important for later use.
                                convert1 = matchA.group();
                                lines1.put(innerloop1, convert1);
                                amount1.put(convert1, 0);
                                innerloop1++;
                        }
                        //finds any pattern that matches an empty space, then "user", then another empty space, and then a word. A ; may also proceed the pattern.
                        Pattern patternB = Pattern.compile("\\;?\\suser\\s\\S+");
                        Matcher matchB = patternB.matcher(lineholder.get(outerloop));
                        //Repeats the middle loop to find every username on a line.
                        while(matchB.find())
                        {
                                //Performs the same function as the other while loop, but removes " user " from the patterns.
                                convert2 = matchB.group();
                                convert2 = convert2.replaceAll(" user ","");
                                lines2.put(innerloop2, convert2);
                                amount2.put(convert2, 0);
                                innerloop2++;
                        }
                }     
                amount2.remove(";unknown");//Removes ;unknown if "; user unknown" was picked up.
                if (Command == 1) //If 1 is enteredin the second input, every unqiue string that matches the first pattern will be listed.
                { 
			//This loop exploits the fact that HashMap addresses can only have one of each ID name.
			for (int compare1 = 0;compare1 < lines1.size();compare1++)
			{
				placeholderA = (String)lines1.get(compare1);
				increment1 = (Integer)amount1.get(placeholderA);
				if (increment1 != null) //Prevents a null value from occuring.
				{
                                        increment1++; //Tracks each time any given pattern match is found.
                                        amount1.put(placeholderA, increment1);
					if (increment1 > 1) //Removes duplicate entries after they have been recorded.
					{
						lines1.put(compare1, "");//Effectively removes the duplicate patterns.
					}
				}
			}
                        for (int printline1 = 0; printline1 < lines1.size(); printline1++)
                        {
                                convert1 = (String)lines1.get(printline1);
                                increment1 = (Integer)amount1.get(convert1);
                                //halts the printing process if the output is null. This stops repeating patterns.
                                if (increment1 != null)
                                {
                                        System.out.println(convert1 + ": " + increment1);
                                }
                        }
                }
                if (Command == 2) //If 2 is entered in the second input, every unique string pattern that matches the second pattern will be listed.
                {
			//Repeats the comparison process for the second pattern. Everything is the same but with different variables and loop lengths.
			for (int compare2 = 0;compare2 < lines2.size();compare2++)
			{
				placeholderB = (String)lines2.get(compare2);
				increment2 = (Integer)amount2.get(placeholderB);
				if (increment2 != null)
				{
                                        increment2++;
                                        amount2.put(placeholderB, increment2);
					if (increment2 > 1)
					{
						lines2.put(compare2, "");//Effectively removes the duplicate patterns.
					}
				}
			}
                        for (int printline2 = 0; printline2 < lines2.size(); printline2++)
                        {
                                String convert2 = (String) lines2.get(printline2);
                                increment2 = (Integer)amount2.get(convert2);
                                //Halts the process of printing repeating patterns for the second pattern.
                                if (increment2 != null)
                                {
                                        System.out.println(convert2 + ": " + increment2);
                                }
                        }
                }
                //Here, middleloop is reused to find the total number of unique IDs.
                innerloop1 = amount1.size();
                innerloop2 = amount2.size();
                //Prints out the number of lines parsed, the number of unique patterns found to match the first criteria, and the number of patterns found to matche the second criteria.
                System.out.println((Part + 1)+ " Lines of the log file were parsed.");
                System.out.println("There were " + innerloop1 + " unique IP Addresses in the log");
                System.out.print("There were " + innerloop2 + " unique users in the log");
        }
}
