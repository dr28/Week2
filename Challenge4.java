package week2.challenges;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Set;

/*Challenge 4 - Hash table word count
Write a program which takes as its input a String of natural language text and outputs a HashMap<String,Integer> whose keys are the
unique words in the input and whose values are the number of times those words occur. The algorithm should be case-insensitive
(e.g. "Program" and "program" would count as the same word) and ignore punctuation and whitespace.

Example: Given the input "To be or not to be, that is the question", the outputted HashMap would contain 8 entries, with two words having
a count of 2 and six words having a count of 1:

"to"        → 2
"be"        → 2
"or"        → 1
"not"       → 1
"that"      → 1
"is"        → 1
"the"       → 1
"question"  → 1*/
public class Challenge4 {

    HashMap<String, Integer> wordCount (String a) {

        HashMap<String, Integer> result = new HashMap<String, Integer>();

        String[] words = a.split(" ");
        System.out.println("words legth "+words.length);

        for( int i=0; i<words.length; i++) {
            String word = words[i];
            word = word.replaceAll("[^A-Za-z ]","");
            word = word.toLowerCase();

            if(result.containsKey(word)) {
                int count = result.get(word).intValue()+1;
                result.replace(word, count);
            }
            else
                result.put(word, 1);
        }

        return result;
    }
    public static void main(String args[])
    {
        String input = "To be or not to Be, that is the question";

        HashMap result = new Challenge4().wordCount(input);

        Iterator i = result.keySet().iterator();
        while (i.hasNext()) {
            String key = (String)i.next();
            System.out.println("key "+ key + " count "+result.get(key));

        }

    }

}
