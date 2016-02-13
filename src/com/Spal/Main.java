package com.Spal;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/** Going off of memory, this program pretty much splits the words
 *  of a corpus, rids all non-uniques, delimiters, and stopwords.
 *
 *  @author Mark Eikel
 *  @author Brandon Hathaway
 *  @version 0.0.1
 *  @since 0.0.1
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Scanner scan = new Scanner(new FileReader("src\\com\\Spal\\alice29.txt"));
        String yes;
        for(int i =0; i < 20; i++){
            yes = scan.next();
            System.out.println(yes);
        }

    }

    public static void preprocessorTokenization(File toread, File towrite){
        try {
            FileWriter writer = new FileWriter(towrite);
            Scanner reader = new Scanner(toread);
            ArrayList<String> linewords = new ArrayList<>();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reader.useDelimeter(".");


    }
}
