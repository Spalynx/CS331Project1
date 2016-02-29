/* Count all of the words and find the frequency of those
*  words so that they are in a list and then print out
*  the words in a file.
*  */
package com.Spal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** Reads from the tokenized-corpus.txt file to obtain all of the words
 *  that are contained in the alice29.txt file. The class then assigns
 *  the word to a map and returns the map to the main program
 *
 * @since 0.5
 * @author Brandon Hathaway
 * @version 1.0
 */
public class CorpusOrder {


     /* Variable Declaration */
    /* The hash map is created with the key being a String
       and the value being an integer.
     */

    private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    /* Opens a File */
    File file = new File("");

    /* Temporary Key, values for the hashMap */
    private Integer i = 1;
    private String s;

    /*===================== Constructors: ===========================*/
    // Default Constructor
    CorpusOrder(){
        /* Initializes default values */
        file = new File("");
    }

    //Constructor with a file param
    CorpusOrder(File file){
        this.file = file;
    }
    //Constructor with a string param
    CorpusOrder(String filename){
        File file = new File(filename);
    }
    /*================================================================*/

    //Debug and statistic variables.
    private static final boolean verbose = false; //Allows debug messages to print.
    public static int numstopwords, numwords, numpunct;

    /** Reads lines from a file and puts the line which contains words into
     *  a HashMap as the key. Increases the value of the keys if the HashMap
     *  already contains the key.
     *
     * @since 0.5
     * @author Brandon Hathaway
     */
    public HashMap<String, Integer> AssignMap(){
        try{

            /* Creates a Scanner object to read from
               the file that was opened earlier.
             */
            Scanner read = new Scanner(file);

            /* Reads the file until the end of file
               is reached.
            */
            while(read.hasNextLine()){
                /* Reads the next line from the file */
                s = read.nextLine().toLowerCase();
                /* Checks to see if the HashMap already contains the line */
                if(hashMap.containsKey(s)){
                    /* Assigns the HashMaps value to the variable i */
                    i = hashMap.get(s);
                    /* Increases the value of the key by 1 */
                    hashMap.replace(s,i,++i);
                }
                else{
                    /* Sets the variable i equal to 1 */
                    i = 1;
                    /* Assigns the key and value to the HashMap */
                    hashMap.put(s,i);
                }
            }
            /* Closes the File */
            read.close();
        }

        /* if the file isn't able to open
           then the error exception is
           triggered.
         */
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return hashMap;
    }

    /** Finds the top 100 values of the treemap given in makeTreeMap, and prints them to the screen.
     * @since 0.9
     * @author Mark Eikel
     */
    public void findTopWords (){
        TreeMap<String, Integer> top = new TreeMap<String, Integer>(AssignMap());

        debugPrintln("Finding the 100 top words in the book.");
        /* This block recursively searches the map for the lowest value and deletes it, until
            the map is 100 values large.                                                        */
        while (top.size() > 100){
            String lowestkey = "";
            Integer lowest = Integer.MAX_VALUE;

            //Finds the lowest value in the map, and fills lowest/lowestkey.
            for(Map.Entry<String, Integer> findlowest : top.entrySet()){
                if(findlowest.getValue() < lowest){
                    lowest = findlowest.getValue();
                    lowestkey = findlowest.getKey();
                }
            }
            //removing the lowest value
            top.remove(lowestkey);
        }

        debugPrintln("Printing top 100 words.");
        System.out.println("The most used words in the corpus:");

        /* This block then proceeds to recursively find the largest of the values, and prints
                it to the screen.                                                               */
        for(int i = 0; i < top.size(); i++){
            String highestkey = "";
            Integer highest = 0;

            //Finds the highest value in the map, and fills highest/highestkey.
            for(Map.Entry<String, Integer> findhighest : top.entrySet()){
                if(findhighest.getValue() > highest) {
                    highest = findhighest.getValue();
                    highestkey = findhighest.getKey();
                }
            }
            //Prints the value to screen, and deletes the value.
            System.out.printf("%-15s%3d%n" , highestkey + ",", highest);
            top.remove(highestkey);
        }

        debugPrintln("Finished printing all top words.");

    }

    /** Adds up the value of every word in the HashMap and then calculates
     *  the ratio of the corpus to the alice or original text.
     *
     * @since 0.9
     * @author Brandon Hathaway
     */
    public double calculateRatio(){
        debugPrintln("Finding the ratio of words.");
        /* Declares variables */
        double ratio, sum = 0;
        /* For each loop that will iterate through the hashMap and then
           find the total sum.
         */
        for(Map.Entry<String, Integer> completeMap : AssignMap().entrySet()){
            /* Adds a running total of the the value of each key */
            sum += completeMap.getValue();
        }
        /* divides the size of the HashMap which is the size of corpus by the
           total sum that was calculated.
         */
        ratio = hashMap.size() / sum;
        return ratio;
    }

    /** Gets words from toread, removes all punctuation, and stopwords (given). It further
     * outputs the rest to towrite; one word per line.
     *
     * @param toread Lines from this file are removed of punctuation and stopwords.
     * @param towrite All non-stopword, punctuation free words from toread are outputted to this file.
     * @since 0.3
     * @author Mark Eikel
     */
    public static void preprocessorTokenization(File toread, File towrite){
        FileWriter writer;  Scanner reader;     //To read/write to toread/towrite.
        Scanner stopwordfile;   //These next two are used to remove stopwords from file.
        numstopwords = numwords = numpunct = 0; //stats for the readme

        ArrayList<String> stopwords = new ArrayList<>(); //Might as well, I'm not loading this more than once.

        try {
            //Initializing scanners and readers.
            stopwordfile = new Scanner(new File("src\\com\\Spal\\stopwords.txt"));
            writer       = new FileWriter(towrite);
            reader       = new Scanner(toread);
            String delim = "[^\\w\\s]|\\d"; //for the punct removal

            //Loading all stopwords into an ArrayList to compare
            while (stopwordfile.hasNext()){
                stopwords.add(stopwordfile.nextLine());
            }   stopwordfile.close();

            debugPrintln("Started removing stopwords.");

            //First layer of the loop strips all punctuation, and splits the file.
            while(reader.hasNextLine()){
                String rawline, templine;


                //Grabs a line of text, and removes all punct.
                rawline = reader.nextLine();
                templine = rawline.replaceAll("-", " ").replaceAll(delim, "");
                //Splits the line into an array.
                String[] line = templine.split(" ");

                numwords += line.length; //number of total words in file.
                numpunct += rawline.length() - templine.length();

                //This boolean stops a stopword from being written to the new file.
                boolean matches = true;

                //Cycles through the word, and checks to see if it is a stopword.
                for(String word : line){
                    //Cycling stopwords, and comparing to word.
                    for(String stop : stopwords){
                        //If the current word equals a stopword, or null, don't write to output.
                        if ( word.equalsIgnoreCase(stop) || word.equals("")){
                            matches = false;
                            if (!word.equals("")) numstopwords++; //the amount of stopwords in the file.

                        }
                    }

                    if(matches)    //Does it output to file?
                        writer.write(word + "\n");
                } //for
            } //while

            debugPrintln("Finished removing stopwords.");
            writer.close(); reader.close(); //Cleaning up!
        }//try
        //Just catches file creations errors.
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        debugPrintln("Total Words: " + numwords + ", Words: " + (numwords - numstopwords)
                + ", Stop Words: " + numstopwords +", Punctuation: " + numpunct);
    }//tokenization()

    /* Allows the printing of a string on the basis that verbose (global bool) is true.
       */
    private static void debugPrintln(String message){
        if(verbose)   System.out.println(message);
    }
}
