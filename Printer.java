/**
* This collects any given text from Scanner, compares it using regular expressions, filters out repeating patterns, and tallies every time a unique pattern successfully matches the pattern$
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
        public static Integer incrementer = 1;
        public static Integer middleloop1 = 0;
        public static Integer middleloop2 = 0;
        public static Integer increment1;
        public static Integer increment2;
        public static String convert1;
        public static String convert2;
        public static String placeholderA;
        public static String placeholderB;
        public static String placeholderC;
        public static String placeholderD;
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
                                lines1.put(middleloop1, convert1);
                                amount1.put(convert1, 1);
                                middleloop1++;
                        }
                        //finds any pattern that matches an empty space, then "user", then another empty space, and then a word. A ; may also proceed the pattern.
                        Pattern patternB = Pattern.compile("\\;?\\suser\\s\\S+");
                        Matcher matchB = patternB.matcher(lineholder.get(outerloop));
                        //Repeats the middle loop to find every username on a line.
                        while(matchB.find())
                        {
                                //Performs the same function as the other while loop, but removes " user " from the patterns.
                                convert2 = matchB.group();
                                convert2 = matchB.group();
                                convert2 = convert2.replaceAll(" user ","");
                                lines2.put(middleloop2, convert2);
                                amount2.put(convert2, 1);
                                middleloop2++;
                        }
                }
                //the next structure removes any duplicate patterns within the line HashMaps.
                for (int outside1 = 0; outside1 < lines1.size();outside1++)
                {
                        //The outer loop stores the string into a placeholder.
                        placeholderA = (String)lines1.get(outside1);
                        for (int inside1 = 0; inside1 < outside1; inside1++)
                        {
                                //The inner loop stores the string in a different placeholder, compares the two codes, and removes any duplciates.
                                placeholderB = (String)lines1.get(inside1);
                                if (placeholderA.equals(placeholderB) && ! placeholderA.equals(""))
                                {
                                        lines1.put(inside1, "");//Effectively removes the duplicate patterns.
                                        //Tracks the duplicate patterns to show how many times a given patterns pops up.
                                        incrementer = (Integer)amount1.get(placeholderA);
                                        incrementer++;
                                        amount1.put(placeholderA, incrementer);
                                }
                        }
                        incrementer = 1;//Resets the incrementer for the next pattern.
                }
                //Repeats the comparison process for the second pattern. Everything is the same but with different variables and loop lengths.
                for (int outside2 = 0; outside2 < lines2.size();outside2++)
                {
                        placeholderC = (String)lines2.get(outside2);
                        for (int inside2 = 0; inside2 < outside2;inside2++)
                        {
                                placeholderD = (String)lines2.get(inside2);
                                if (placeholderC.equals(placeholderD) && ! placeholderC.equals(""))
                                {
                                        lines2.put(inside2, "");
                                        incrementer = (Integer)amount2.get(placeholderC);
                                        incrementer++;
                                        amount2.put(placeholderC, incrementer);
                                }
                        }
                        incrementer = 1;
                }
                amount2.remove(";unknown");//Removes ;unknown from "; user unknown".
                if (Command == 1) //If 1 is enteredin the second input, every unqiue string that matches the first pattern will be listed.
                {
                        for (int printline1 = 0; printline1 < lines1.size(); printline1++)
                        {
                                String convert1 = (String)lines1.get(printline1);
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
                middleloop1 = amount1.size();
                middleloop2 = amount2.size();
                //Prints out the number of lines parsed, the number of unique patterns found to match the first criteria, and the number of patterns found to matche the second criteria.
                System.out.println((Part + 1)+ " Lines of the log file were parsed.");
                System.out.println("There were " + middleloop1 + " unique IP Addresses in the log");
                System.out.print("There were " + middleloop2 + " unique users in the log");
        }
}



