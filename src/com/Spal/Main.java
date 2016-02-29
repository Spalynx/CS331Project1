package com.Spal;
import java.io.File;
import java.io.FileNotFoundException;

/** This program implements the items in CorpusOrder, and removes punctuations and stopwords.
 *  @brief After removing these items, the program then finds the frequency, the 100 most used
 *      words in the file, and the ratio of words in the file.
 *
 *  @author Mark Eikel
 *  @author Brandon Hathaway
 *  @version 0.0.3
 *  @since 0.0.1
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File    corpus       = new File("src\\com\\Spal\\alice29.txt", ""),
                tokenized   = new File("src\\com\\Spal\\tokenized-corpus.txt");

        //Takes the input file, and outputs a stripped version to the second file.
        CorpusOrder.preprocessorTokenization(corpus, tokenized);

        CorpusOrder corp = new CorpusOrder(tokenized);
        //Finding the top words, and the ratio of words in the file.
        corp.findTopWords();
        System.out.printf("Ratio of words: %.2f%n", corp.calculateRatio());
    }


}//class main
