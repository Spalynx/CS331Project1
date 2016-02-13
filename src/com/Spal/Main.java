package com.Spal;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.regex.Pattern;

/** Going off of memory, this program pretty much splits the words
 *  of a corpus, rids all non-uniques, delimiters, and stopwords.
 *
 *  @author Mark Eikel
 *  @author Brandon Hathaway
 *  @version 0.0.3
 *  @since 0.0.1
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        preprocessorTokenization(new File("src\\com\\Spal\\alice29.txt", ""),
                                new File("src\\com\\Spal\\tokenized-corpus.txt"));
    }

    /** Gets words from toread, removes all punctuation, and stopwords (given). It further
     * outputs the rest to towrite, one word per line.
     *
     * @param toread Lines from this file are removed of punctuation and stopwords.
     * @param towrite All non-stopword, punctuation free words from toread are outputted to this file.
     * @since 0.0.3
     * @author Mark Eikel
     */
    public static void preprocessorTokenization(File toread, File towrite){
        FileWriter writer;  Scanner reader;     //To read/write to toread/towrite.
        Scanner stopwordfile;   //These next two are used to remove stopwords from file.
        ArrayList<String> stopwords = new ArrayList<>();

        try {
            //Initializing scanners and readers.
            stopwordfile = new Scanner(new File("src\\com\\Spal\\stopwords.txt"));
            writer = new FileWriter(towrite);
            reader = new Scanner(toread);
                String delim = "[^\\w\\s]|\\d";

            //Loading all stopwords into an ArrayList to compare
            while (stopwordfile.hasNext()){
                stopwords.add(stopwordfile.nextLine());
            }   stopwordfile.close();

            //First layer of the loop strips all punctuation, and splits the file.
            while(reader.hasNextLine()){
                //Grabs a line of text, and removes all punct.
                String templine = reader.nextLine().replaceAll(delim, "");
                String[] line = templine.split(" "); //Splits the line into an array.

                boolean matches = true;     //Stops iteration if the word isn't to be put in the file.

                //Cycles through the word, and checks to see if it is a stopword.
                for(String word : line){
                    for(String stop : stopwords){  //Checking for stopwords.
                        //If the current word equals a stopword, or null, don't write to output.
                        if ( word.equalsIgnoreCase(stop) || word.equals("")){
                            matches = false;
                        }
                    }
                    //Does it output to file?
                    if(matches){
                        writer.write(word + "\n");
                    }
                }

            }
            //Cleaning up!
            writer.close(); reader.close();
        }//try
        //Just catches file creations errors.
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//tokenization()

}//class main
