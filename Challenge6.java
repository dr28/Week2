package week2.challenges;

import java.util.HashMap;
import java.util.HashSet;

/*Challenge 6 - Longest non-repeating substring
Write a program which takes as its input a String and returns the length of the longest substring that does not contain any repeated characters.

Example: Given the string "abcabcbb", the longest substring with no repeated characters is "abc", so the program would return a value of 3.
Given the string "aaaaaaaa", the longest non-repeating substring is "a" and thus the output would be 1.*/
public class Challenge6 {

    int longestNonRepeatingSubstring(String s) {
        int count = 0;
        // if space is not considered then
        //s = s.replaceAll("[^A-Za-z ]","");

        String [] letter = s.split("");
        HashSet nonrepeatSet = new HashSet();

        for(int i=0; i< letter.length; i++) {
           // System.out.println(letter[i]);

            if(nonrepeatSet.contains(letter[i])) break;
            else {
                nonrepeatSet.add(letter[i]);
                count++;
            }

        }

        return count;
    }
    public static void main(String args[])
    {
        String input = "abcabcbb";
        input = "aaaaaaaa";

        System.out.println(new Challenge6().longestNonRepeatingSubstring(input));

    }

}
