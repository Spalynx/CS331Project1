# CS331Project1
# Direct copy paste of project document.

The tasks were divided by each step of the project.
  Step 1 which was the preprocessing step was completed first and was completed by Mark. The 
  Step 2 came next which is the frequency count step. This step was completed by Brandon. 
  Step 3 which was the step where the top 100 words in the corpus. 
    This step was broken up between two parts the first part was to find the top 100 words which was completed by Mark.
    The second step was to find the ratio to the number of words to the total words in the original text file which was created by        Brandon.
Instructions on how to run the program.
  Download the folder from the learning.semo.edu website link under Project 1
  Unzip the folder to desired directory
  Open IntelliJ IDEA
  Click Open
  Locate the folder that the project is located in the directory that you downloaded it in
  The folder should be under the top level of the folder under Project 1 folder
  Click Project1.iml and then click ok
  At the top in the toolbar click the Run tab
  A drop down menu should appear click Run ‘Project1’ from the drop down menu.
Explanation of the three steps.
  Step one the alice29.txt file was opened and read through where the punctuation was removed so that it would be easier to grab     
  each word. Then the stop words that we were instructed to take out of the alice29.txt were passed over and put into the file 
  stopwords.txt. The words were then read and put into the tokenized-corpus.txt which contains all the non-stop words.

  Step two was where we read the tokenized-corpus.txt which was created from step one. These words were read one at a time since they 
  were output in the text file one per line. The words were then put into a HashMap within a separate class. This class was in a 
  separate file from the main file. As the program was going through the tokenized-corpus.txt file and adding the words to the HashMap   if the program read a word what was already contained in the HashMap then the value associated with the word was increased by one.
 
   Step three we went through the HashMap and then took out the lowest value until there were only 100 left. This was done using for   each loops in which we would iterate through the HashMap and then remove the lowest keys from the HashMap. After there were one   
  hundred words left we printed out the highest value and key associated with the map to the output and then removed it from the 
  HashMap. Finally we then called a method where we obtained the size of the HashMap and then counted all of the total value 
  associated with each key. This was assigned to a total sum of the word within the file in which we assigned the ratio equal to the 
  size divided by the total sum of each word combined. Finally we printed everything out to the screen so that the user could see what   the program accomplished.
  
Corpus Size
  32612
Number of Words
  14594
Number of Stop Words
  18018
Number of Punctuations
  7633

If you desire to look at it in github.com click this link:
https://github.com/Spalynx/CS331Project1 
