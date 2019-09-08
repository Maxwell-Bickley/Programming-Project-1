/**
* This collects any given text from Scanner, compares it using regular expressions, filters out repeating patterns, and tellies every tim a unique pattern successfully matches the pattern type that
it is being compared to. Then, it stores them into a HashMap, can optionally extract and print the usernames and IP Addresses from the HashMap, and print the lines of code parsed along with the number of
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
public static Integer TickmarkA = 0;
public static Integer TickmarkB = 0;
public static Integer Middleloop1 = 0;
public static Integer Middleloop2 = 0;
public static Integer Canceller1 = 0;
public static Integer Canceller2 = 0;
//Because the size of arrays cannot be reliably altered without negatively influencing the program's efficiency, I have elected to make the arrays-
//as large as possible without causing or risking any errors.
public static String Holder1[] = new String [9999999];
public static String Holder2[] = new String [9999999];
public static String comparer1[] = new String [9999999];
public static String comparer2[] = new String [9999999];
public static HashMap<Integer, String> lines1 = new HashMap<Integer, String>();
public static HashMap<Integer, String> lines2 = new HashMap<Integer, String>();
public static void main(String[]args)
{
Scan scan = new Scan(); //Declares Scanner so that some of its variables may be sent to this class.
//Collects variables from Scanner.
Integer Command = scan.sender();
Integer Part = scan.sender2();
String[] lineholder = scan.sender3();
//The outermost loop. This loop runs through each line of code and compares it to two patterns.
for (int outerloop = 0; outerloop <= Part; outerloop++)
{
//finds any pattern that matches 4 number groups with 1 to 3 digits in each group.
Pattern patternA = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
Matcher matchA = patternA.matcher(lineholder[outerloop]);
//The middle loop. Places every pattern match on a given line into a placeholder array for later.
while (matchA.find())
{
Middleloop1++;
comparer1[Middleloop1] = "";//Prevents any issues with comparing patterns to previous patterns.
Holder1[Middleloop1] = matchA.group();
//The inner loop. Compares previously recorded pattern matches for each pattern match so that repeats are not counted.
for (int innerloop1 = 0; innerloop1 <= Middleloop1; innerloop1++)
{
if (Holder1[Middleloop1].equals(comparer1[innerloop1]))//If a pattern matches a previously recorded pattern, Canceller will change and the process of storing the pattern will be skipped once.
{
Canceller1 = 1;
}
if (innerloop1 == Middleloop1 && Canceller1 == 0)//Proceeds if there are no duplicate pattern matches.
{
//Ensures that the proper line number is displayed in the output.
Integer Output1 = outerloop  + 1;
String show1 = Integer.toString(Output1);
lines1.put(TickmarkA,Holder1[Middleloop1] + ": " +show1); //Adds an identifier, a variable, a pieces of string that reads ": " and the line where the variable was found to lines1.
TickmarkA = TickmarkA + 1;//Increments the number of patterns held.
}
}
comparer1[Middleloop1] = Holder1[Middleloop1];//Stores the value in the comparer array to prevent the recording of multiple repeating patterns.
Holder1[Middleloop1] = "";//Clears the Holder array.
Canceller1 = 0;//Canceller must always atrt at zero.
}
//finds any pattern that matches an empty space, then "user", then another empty space, and then a word. A ; may also proceed the pattern.
Pattern patternB = Pattern.compile("\\;?\\suser\\s\\S+");
Matcher matchB = patternB.matcher(lineholder[outerloop]);
//Repeats the middle loop to find every username on a line.
while(matchB.find())
{
Middleloop2++;
comparer2[Middleloop2] = "";//Prevents comparison issues within the second middle loop.
Holder2[Middleloop2] = matchB.group();
Holder2[Middleloop2] = Holder2[Middleloop2].replaceAll(" user ","");//Removes the " user " part of the pattern for a cleaner output.
//The second inner loop. It behaves just like the first one with just a few extra lines of code.
for (int innerloop2 = 0; innerloop2 <= Middleloop2; innerloop2++)
{
//Stops the program from catching "; user unknown" in addition to its standard function.
if (Holder2[Middleloop2].equals(comparer2[innerloop2]) || Holder2[Middleloop2].equals(";unknown"))
{
Canceller2 = 1;
}
if (innerloop2 == Middleloop2 && Canceller2 == 0)
{
Integer Output2 = outerloop + 1;
String show2 = Integer.toString(Output2);
lines2.put(TickmarkB,Holder2[Middleloop2] + ": " +show2);
TickmarkB = TickmarkB + 1;
}
}
comparer2[Middleloop2] = Holder2[Middleloop2];
Holder2[Middleloop2] = "";
Canceller2 = 0;
}
}
if (Command == 1) //If 1 is enteredin the second input, every unqiue string that matches the first pattern will be listed.
{
for (int printline1 = 0; printline1 < lines1.size(); printline1++)
{
String character1 = (String)lines1.get(printline1);
System.out.println(character1);
}
}
if (Command == 2) //If 2 is entered in the second input, every unique string pattern that matches the second pattern will be listed.
{
for (int printline2 = 0; printline2 < lines2.size(); printline2++)
{
String character2 = (String) lines2.get(printline2);
System.out.println(character2);
}
}
//Prints out the number of lines parsed, the number of unique patterns found to match the first criteria, and the number of patterns found to matche the second criteria.
System.out.println((Part + 1)+ " Lines of the log file were parsed.");
System.out.println("There were " + TickmarkA + " unique IP Addresses in the log");
System.out.print("There were " +TickmarkB + " unique users in the log");
}
}
